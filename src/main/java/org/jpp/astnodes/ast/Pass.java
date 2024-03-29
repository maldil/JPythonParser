// Autogenerated AST node on 08/11/2020, 15:59:03
package org.jpp.astnodes.ast;
import org.antlr.runtime.Token;
import org.jpp.astnodes.PythonTree;
import org.jpp.astnodes.base.stmt;
import org.jpp.heart.ArgParser;
import org.jpp.heart.PyObject;
import org.jpp.heart.PyUnicode;
import org.jpp.heart.PyStringMap;
import org.jpp.heart.PyType;
import org.jpp.exposers.ExposedGet;
import org.jpp.exposers.ExposedMethod;
import org.jpp.exposers.ExposedNew;
import org.jpp.exposers.ExposedSet;
import org.jpp.exposers.ExposedType;

@ExposedType(name = "_ast.Pass", base = stmt.class)
public class Pass extends stmt {
public static final PyType TYPE = PyType.fromClass(Pass.class);

    private final static PyUnicode[] fields = new PyUnicode[0];
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes =
    new PyUnicode[] {new PyUnicode("lineno"), new PyUnicode("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public Pass(PyType subType) {
        super(subType);
    }
    @ExposedNew
    @ExposedMethod
    public void Pass___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("Pass", args, keywords, new String[]
            {"lineno", "col_offset"}, 0, true);
        int lin = ap.getInt(0, -1);
        if (lin != -1) {
            setLineno(lin);
        }

        int col = ap.getInt(1, -1);
        if (col != -1) {
            setLineno(col);
        }

    }

    public Pass() {
    }

    public Pass(Token token) {
        super(token);
    }

    public Pass(Integer ttype, Token token) {
        super(ttype, token);
    }

    public Pass(PythonTree tree) {
        super(tree);
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "Pass";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("Pass(");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        return visitor.visitPass(this);
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
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
