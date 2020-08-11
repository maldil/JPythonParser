// Hand copied from stmt.
// XXX: autogenerate this.
package astnodes.base;
import org.antlr.runtime.Token;
import astnodes.AST;
import astnodes.PythonTree;
import heart.PyBytes;
import heart.PyType;
import exposers.ExposedGet;
import exposers.ExposedType;

@ExposedType(name = "_ast.operator", base = AST.class)
public abstract class operator extends PythonTree {

    public static final PyType TYPE = PyType.fromClass(operator.class);
    private final static PyBytes[] fields = new PyBytes[0];
    @ExposedGet(name = "_fields")
    public PyBytes[] get_fields() { return fields; }

    private final static PyBytes[] attributes =
    new PyBytes[] {new PyBytes("lineno"), new PyBytes("col_offset")};
    @ExposedGet(name = "_attributes")
    public PyBytes[] get_attributes() { return attributes; }

    public operator() {
    }

    public operator(PyType subType) {
    }

    public operator(int ttype, Token token) {
        super(ttype, token);
    }

    public operator(Token token) {
        super(token);
    }

    public operator(PythonTree node) {
        super(node);
    }

}
