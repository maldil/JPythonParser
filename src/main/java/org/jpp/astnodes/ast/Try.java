// Autogenerated AST node on 08/11/2020, 15:59:03
package org.jpp.astnodes.ast;
import org.antlr.runtime.Token;
import org.jpp.astnodes.PythonTree;
import org.jpp.astnodes.adapter.AstAdapters;
import org.jpp.astnodes.base.excepthandler;
import org.jpp.astnodes.base.stmt;
import org.jpp.heart.ArgParser;
import org.jpp.heart.AstList;
import org.jpp.heart.Py;
import org.jpp.heart.PyObject;
import org.jpp.heart.PyUnicode;
import org.jpp.heart.PyStringMap;
import org.jpp.heart.PyType;
import org.jpp.exposers.ExposedGet;
import org.jpp.exposers.ExposedMethod;
import org.jpp.exposers.ExposedNew;
import org.jpp.exposers.ExposedSet;
import org.jpp.exposers.ExposedType;

import java.util.ArrayList;

@ExposedType(name = "_ast.Try", base = stmt.class)
public class Try extends stmt {
public static final PyType TYPE = PyType.fromClass(Try.class);
    private java.util.List<stmt> body;
    public java.util.List<stmt> getInternalBody() {
        return body;
    }
    @ExposedGet(name = "body")
    public PyObject getBody() {
        return new AstList(body, AstAdapters.stmtAdapter);
    }
    @ExposedSet(name = "body")
    public void setBody(PyObject body) {
        this.body = AstAdapters.py2stmtList(body);
    }

    private java.util.List<excepthandler> handlers;
    public java.util.List<excepthandler> getInternalHandlers() {
        return handlers;
    }
    @ExposedGet(name = "handlers")
    public PyObject getHandlers() {
        return new AstList(handlers, AstAdapters.excepthandlerAdapter);
    }
    @ExposedSet(name = "handlers")
    public void setHandlers(PyObject handlers) {
        this.handlers = AstAdapters.py2excepthandlerList(handlers);
    }

    private java.util.List<stmt> orelse;
    public java.util.List<stmt> getInternalOrelse() {
        return orelse;
    }
    @ExposedGet(name = "orelse")
    public PyObject getOrelse() {
        return new AstList(orelse, AstAdapters.stmtAdapter);
    }
    @ExposedSet(name = "orelse")
    public void setOrelse(PyObject orelse) {
        this.orelse = AstAdapters.py2stmtList(orelse);
    }

    private java.util.List<stmt> finalbody;
    public java.util.List<stmt> getInternalFinalbody() {
        return finalbody;
    }
    @ExposedGet(name = "finalbody")
    public PyObject getFinalbody() {
        return new AstList(finalbody, AstAdapters.stmtAdapter);
    }
    @ExposedSet(name = "finalbody")
    public void setFinalbody(PyObject finalbody) {
        this.finalbody = AstAdapters.py2stmtList(finalbody);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("body"), new PyUnicode("handlers"), new PyUnicode("orelse"), new
                      PyUnicode("finalbody")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Try(PyType subType) {
        super(subType);
    }
    public Try() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void Try___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Try", args, keywords, new String[]
            {"body", "handlers", "orelse", "finalbody", "lineno", "col_offset"}, 4, true);
        setBody(ap.getPyObject(0, Py.None));
        setHandlers(ap.getPyObject(1, Py.None));
        setOrelse(ap.getPyObject(2, Py.None));
        setFinalbody(ap.getPyObject(3, Py.None));
        int lin = ap.getInt(4, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(5, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public Try(PyObject body, PyObject handlers, PyObject orelse, PyObject finalbody) {
        setBody(body);
        setHandlers(handlers);
        setOrelse(orelse);
        setFinalbody(finalbody);
    }

    public Try(Token token, java.util.List<stmt> body, java.util.List<excepthandler> handlers,
    java.util.List<stmt> orelse, java.util.List<stmt> finalbody) {
        super(token);
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
        this.handlers = handlers;
        if (handlers == null) {
            this.handlers = new ArrayList<excepthandler>();
        }
        for(PythonTree t : this.handlers) {
            addChild(t);
        }
        this.orelse = orelse;
        if (orelse == null) {
            this.orelse = new ArrayList<stmt>();
        }
        for(PythonTree t : this.orelse) {
            addChild(t);
        }
        this.finalbody = finalbody;
        if (finalbody == null) {
            this.finalbody = new ArrayList<stmt>();
        }
        for(PythonTree t : this.finalbody) {
            addChild(t);
        }
    }

    public Try(Integer ttype, Token token, java.util.List<stmt> body, java.util.List<excepthandler>
    handlers, java.util.List<stmt> orelse, java.util.List<stmt> finalbody) {
        super(ttype, token);
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
        this.handlers = handlers;
        if (handlers == null) {
            this.handlers = new ArrayList<excepthandler>();
        }
        for(PythonTree t : this.handlers) {
            addChild(t);
        }
        this.orelse = orelse;
        if (orelse == null) {
            this.orelse = new ArrayList<stmt>();
        }
        for(PythonTree t : this.orelse) {
            addChild(t);
        }
        this.finalbody = finalbody;
        if (finalbody == null) {
            this.finalbody = new ArrayList<stmt>();
        }
        for(PythonTree t : this.finalbody) {
            addChild(t);
        }
    }

    public Try(PythonTree tree, java.util.List<stmt> body, java.util.List<excepthandler> handlers,
    java.util.List<stmt> orelse, java.util.List<stmt> finalbody) {
        super(tree);
        this.body = body;
        if (body == null) {
            this.body = new ArrayList<stmt>();
        }
        for(PythonTree t : this.body) {
            addChild(t);
        }
        this.handlers = handlers;
        if (handlers == null) {
            this.handlers = new ArrayList<excepthandler>();
        }
        for(PythonTree t : this.handlers) {
            addChild(t);
        }
        this.orelse = orelse;
        if (orelse == null) {
            this.orelse = new ArrayList<stmt>();
        }
        for(PythonTree t : this.orelse) {
            addChild(t);
        }
        this.finalbody = finalbody;
        if (finalbody == null) {
            this.finalbody = new ArrayList<stmt>();
        }
        for(PythonTree t : this.finalbody) {
            addChild(t);
        }
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "Try";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Try(");
        sb.append("body=");
        sb.append(dumpThis(body));
        sb.append(",");
        sb.append("handlers=");
        sb.append(dumpThis(handlers));
        sb.append(",");
        sb.append("orelse=");
        sb.append(dumpThis(orelse));
        sb.append(",");
        sb.append("finalbody=");
        sb.append(dumpThis(finalbody));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitTry(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (body != null) {
            for (PythonTree t : body) {
                if (t != null)
                    t.accept(visitor);
            }
        }
        if (handlers != null) {
            for (PythonTree t : handlers) {
                if (t != null)
                    t.accept(visitor);
            }
        }
        if (orelse != null) {
            for (PythonTree t : orelse) {
                if (t != null)
                    t.accept(visitor);
            }
        }
        if (finalbody != null) {
            for (PythonTree t : finalbody) {
                if (t != null)
                    t.accept(visitor);
            }
        }
    }

    public PyObject __dict__;

    @Override
    public PyObject fastGetDict() {
        ensureDict();
        return __dict__;
    }

    @ExposedGet(name = "__dict__")
    public PyObject getDict() {
        return fastGetDict();
    }

    private void ensureDict() {
        if (__dict__ == null) {
            __dict__ = new PyStringMap();
        }
    }

    private int lineno = -1;
    @ExposedGet(name = "lineno")
    public int getLineno() {
        if (lineno != -1) {
            return lineno;
        }
        return getLine();
    }

    @ExposedSet(name = "lineno")
    public void setLineno(int num) {
        lineno = num;
    }

    private int col_offset = -1;
    @ExposedGet(name = "col_offset")
    public int getCol_offset() {
        if (col_offset != -1) {
            return col_offset;
        }
        return getCharPositionInLine();
    }

    @ExposedSet(name = "col_offset")
    public void setCol_offset(int num) {
        col_offset = num;
    }

}
