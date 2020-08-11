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

@ExposedType(name = "_ast.Await", base = expr.class)
public class Await extends expr {
public static final PyType TYPE = PyType.fromClass(Await.class);
    private expr value;
    public expr getInternalValue() {
        return value;
    }
    @ExposedGet(name = "value")
    public PyObject getValue() {
        return value;
    }
    @ExposedSet(name = "value")
    public void setValue(PyObject value) {
        this.value = AstAdapters.py2expr(value);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("value")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Await(PyType subType) {
        super(subType);
    }
    public Await() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void Await___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Await", args, keywords, new String[]
            {"value", "lineno", "col_offset"}, 1, true);
        setValue(ap.getPyObject(0, Py.None));
        int lin = ap.getInt(1, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(2, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public Await(PyObject value) {
        setValue(value);
    }

    public Await(Token token, expr value) {
        super(token);
        this.value = value;
        addChild(value);
    }

    public Await(Integer ttype, Token token, expr value) {
        super(ttype, token);
        this.value = value;
        addChild(value);
    }

    public Await(PythonTree tree, expr value) {
        super(tree);
        this.value = value;
        addChild(value);
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "Await";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Await(");
        sb.append("value=");
        sb.append(dumpThis(value));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitAwait(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (value != null)
            value.accept(visitor);
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