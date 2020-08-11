// Autogenerated AST node on 08/11/2020, 15:59:03
package astnodes.ast;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import astnodes.AST;
import astnodes.PythonTree;
import astnodes.adapter.AstAdapters;
import astnodes.base.excepthandler;
import astnodes.base.expr;
import astnodes.base.mod;
import astnodes.base.slice;
import astnodes.base.stmt;
import heart.ArgParser;
import heart.AstList;
import heart.Py;
import heart.PyObject;
import heart.PyUnicode;
import heart.PyStringMap;
import heart.PyType;
import heart.Visitproc;
import exposers.ExposedGet;
import exposers.ExposedMethod;
import exposers.ExposedNew;
import exposers.ExposedSet;
import exposers.ExposedType;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@ExposedType(name = "_ast.Raise", base = stmt.class)
public class Raise extends stmt {
public static final PyType TYPE = PyType.fromClass(Raise.class);
    private expr exc;
    public expr getInternalExc() {
        return exc;
    }
    @ExposedGet(name = "exc")
    public PyObject getExc() {
        return exc;
    }
    @ExposedSet(name = "exc")
    public void setExc(PyObject exc) {
        this.exc = AstAdapters.py2expr(exc);
    }

    private expr cause;
    public expr getInternalCause() {
        return cause;
    }
    @ExposedGet(name = "cause")
    public PyObject getCause() {
        return cause;
    }
    @ExposedSet(name = "cause")
    public void setCause(PyObject cause) {
        this.cause = AstAdapters.py2expr(cause);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("exc"), new PyUnicode("cause")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Raise(PyType subType) {
        super(subType);
    }
    public Raise() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void Raise___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Raise", args, keywords, new String[]
            {"exc", "cause", "lineno", "col_offset"}, 2, true);
        setExc(ap.getPyObject(0, Py.None));
        setCause(ap.getPyObject(1, Py.None));
        int lin = ap.getInt(2, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(3, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public Raise(PyObject exc, PyObject cause) {
        setExc(exc);
        setCause(cause);
    }

    public Raise(Token token, expr exc, expr cause) {
        super(token);
        this.exc = exc;
        addChild(exc);
        this.cause = cause;
        addChild(cause);
    }

    public Raise(Integer ttype, Token token, expr exc, expr cause) {
        super(ttype, token);
        this.exc = exc;
        addChild(exc);
        this.cause = cause;
        addChild(cause);
    }

    public Raise(PythonTree tree, expr exc, expr cause) {
        super(tree);
        this.exc = exc;
        addChild(exc);
        this.cause = cause;
        addChild(cause);
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "Raise";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Raise(");
        sb.append("exc=");
        sb.append(dumpThis(exc));
        sb.append(",");
        sb.append("cause=");
        sb.append(dumpThis(cause));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitRaise(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (exc != null)
            exc.accept(visitor);
        if (cause != null)
            cause.accept(visitor);
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
