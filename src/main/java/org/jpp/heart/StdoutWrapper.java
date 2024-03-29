// Copyright (c) Corporation for National Research Initiatives
package org.jpp.heart;

import java.io.OutputStream;

import org.jpp.heart.util.StringUtil;

public class StdoutWrapper extends OutputStream {
    protected String name;

    public StdoutWrapper() {
        this.name = "stdout";
    }

    protected PyObject getObject(PySystemState ss) {
        return ss.getStdout();
    }

    protected void setObject(PySystemState ss, PyObject out) {
        ss.sysdict.__setitem__("stdout", out);
    }

    protected PyObject myFile() {
        PySystemState ss = Py.getSystemState();
        PyObject out = getObject(ss);
        if (out == null) {
            throw Py.AttributeError("missing sys." + this.name);

        } else if (out instanceof PyAttributeDeleted) {
            throw Py.RuntimeError("lost sys." + this.name);

        } else if (out.getJavaProxy() != null) {
            PyFile f = null;
            Object tojava = out.__tojava__(OutputStream.class);
            if (tojava != null && tojava != Py.NoConversion) {
                f = new PyFile((OutputStream)tojava);
            }
            if (f != null) {
                setObject(ss, f);
                return f;
            }
        }
        return out;
    }

    @Override
    public void flush() {
        PyObject out = myFile();
        if (out instanceof PyFile) {
            ((PyFile) out).flush();
        } else {
            try {
                out.invoke("flush");
            } catch (PyException pye) {
                // ok
            }
        }
    }

    public void write(String s) {
        PyObject out = myFile();

        if (out instanceof PyFile) {
            ((PyFile) out).write(s);
        } else {
            out.invoke("write", new PyUnicode(s));
        }
    }

    @Override
    public void write(int i) {
        write(new String(new char[] { (char) i }));
    }

    @Override
    public void write(byte[] data, int off, int len) {
        write(StringUtil.fromBytes(data, off, len));
    }

    public void flushLine() {
        PyObject out = myFile();

        if (out instanceof PyFile) {
            PyFile file = (PyFile) out;
            if (file.softspace) {
                file.write("\n");
            }
            file.flush();
            file.softspace = false;
        } else {
            PyObject ss = out.__findattr__("softspace");
            if (ss != null && ss.__bool__()) {
                out.invoke("write", Py.Newline);
            }
            try {
                out.invoke("flush");
            } catch (PyException pye) {
                // ok
            }
            out.__setattr__("softspace", Py.Zero);
        }
    }

    private String printToFile(PyFile file, PyObject o) {
        String s;
        if (o instanceof PyUnicode) {
            // Use the encoding and policy defined for the stream. (Each may be null.)
            s = ((PyUnicode)o).encode(file.encoding, file.errors);
        } else {
            s = o.__str__().toString();
        }
        file.write(s);
        return s;
    }

    private String printToFileWriter(PyFileWriter file, PyObject o) {
        // since we are outputting directly to a character stream,
        // avoid doing an encoding
        String s;
        if (o instanceof PyUnicode) {
            s = ((PyUnicode) o).getString();
        } else {
            s = o.__str__().toString();
        }
        file.write(s);
        return s;
    }

    private void printToFileObject(PyObject file, PyObject o) {
        file.invoke("write", o.__str__());
    }

    /**
     * For __future__ print_function.
     */
    public void print(PyObject[] args, PyObject sep, PyObject end) {
        PyObject out = myFile();

        if (out instanceof PyFile) {
            PyFile file = (PyFile)out;
            for (int i=0;i<args.length;i++) {
                printToFile(file, args[i]);
                if (i < args.length -1) {
                    printToFile(file, sep);
                }
            }
            printToFile(file, end);
        } else if (out instanceof PyFileWriter) {
            PyFileWriter file = (PyFileWriter)out;
            for (int i=0;i<args.length;i++) {
                printToFileWriter(file, args[i]);
                if (i < args.length -1) {
                    printToFileWriter(file, sep);
                }
            }
            printToFileWriter(file, end);
        } else {
            for (int i=0;i<args.length;i++) {
                printToFileObject(out, args[i]);
                if (i < args.length -1) {
                    printToFileObject(out, sep);
                }
            }
            printToFileObject(out, end);
        }
    }

    public void print(PyObject o, boolean space, boolean newline) {
        PyObject out = myFile();

        if (out instanceof PyFile) {
            PyFile file = (PyFile)out;
            if (file.softspace) {
                file.write(" ");
                file.softspace = false;
            }

            String s = printToFile(file, o);

            if (o instanceof PyUnicode) {
                int len = s.length();
                if (len == 0 || !Character.isWhitespace(s.charAt(len - 1))
                    || s.charAt(len - 1) == ' ') {
                    file.softspace = space;
                }
            } else {
                file.softspace = space;
            }

            if (newline) {
                file.write("\n");
                file.softspace = false;
            }
            file.flush();

        } else if (out instanceof PyFileWriter) {
            PyFileWriter file = (PyFileWriter)out;
            if (file.softspace) {
                file.write(" ");
                file.softspace = false;
            }
            String s = printToFileWriter(file, o);

            if (o instanceof PyUnicode) {
                int len = s.length();
                if (len == 0 || !Character.isWhitespace(s.charAt(len - 1))
                    || s.charAt(len - 1) == ' ') {
                    file.softspace = space;
                }
            } else {
                file.softspace = space;
            }

            if (newline) {
                file.write("\n");
                file.softspace = false;
            }
            file.flush();

        } else {
            PyObject ss = out.__findattr__("softspace");
            if (ss != null && ss.__bool__()) {
                out.invoke("write", Py.Space);
                out.__setattr__("softspace", Py.Zero);
            }

            printToFileObject(out, o);

            if (o instanceof PyBytes) {
                String s = o.toString();
                int len = s.length();
                if (len == 0 || !Character.isWhitespace(s.charAt(len - 1))
                    || s.charAt(len - 1) == ' ') {
                    out.__setattr__("softspace", space ? Py.One : Py.Zero);
                }
            } else {
                out.__setattr__("softspace", space ? Py.One : Py.Zero);
            }

            if (newline) {
                out.invoke("write", Py.Newline);
                out.__setattr__("softspace", Py.Zero);
            }
        }
    }

    public void print(String s) {
        print(Py.newUnicode(s), false, false);
    }

    public void println(String s) {
        print(Py.newUnicode(s), false, true);
    }

    public void print(PyObject o) {
        print(o, false, false);
    }

    public void printComma(PyObject o) {
        print(o, true, false);
    }

    public void println(PyObject o) {
        print(o, false, true);
    }

    public void println() {
        println(false);
    }

    public void println(boolean useUnicode) {
        PyObject out = myFile();

        if (out instanceof PyFile) {
            PyFile file = (PyFile) out;
            file.write("\n");
            file.flush();
            file.softspace = false;
        } else {
            if (useUnicode) {
                out.invoke("write", Py.UnicodeNewline);
            } else {
                out.invoke("write", Py.Newline);
            }
            out.__setattr__("softspace", Py.Zero);
        }
    }
}
