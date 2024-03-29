/* Copyright (c) Jython Developers */
package org.jpp.modules.posix;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;

import jnr.constants.platform.Errno;
import jnr.posix.POSIXHandler;

import org.jpp.heart.imp;
import org.jpp.heart.Options;
import org.jpp.heart.Py;
import org.jpp.heart.PyObject;


/**
 * Jython specific hooks for our underlying POSIX library.
 */
public class PythonPOSIXHandler implements POSIXHandler {

    public void error(Errno error, String extraData) {
        throw Py.OSError(error, Py.newUnicode(extraData));
    }

    public void error(Errno error, String methodName, String extraData) {
        throw Py.OSError(error, Py.newUnicode(extraData));
    }

    public void unimplementedError(String methodName) {
        if (methodName.startsWith("stat.")) {
            // Ignore unimplemented FileStat methods
            return;
        }
        throw Py.NotImplementedError(methodName);
    }

    public void warn(WARNING_ID id, String message, Object... data) {
    }

    public boolean isVerbose() {
        return Options.verbose >= Py.DEBUG;
    }

    public File getCurrentWorkingDirectory() {
        return new File(Py.getSystemState().getCurrentWorkingDir());
    }

    public String[] getEnv() {
        PyObject items = imp.load("os").__getattr__("environ").invoke("items");
        String[] env = new String[items.__len__()];
        int i = 0;
        for (PyObject item : items.asIterable()) {
            env[i++] = String.format("%s=%s", item.__getitem__(0), item.__getitem__(1));
        }
        return env;
    }

    public InputStream getInputStream() {
        return System.in;
    }

    public PrintStream getOutputStream() {
        return System.out;
    }

    public int getPID() {
        return 0;
    }

    public PrintStream getErrorStream() {
        return System.err;
    }
}
