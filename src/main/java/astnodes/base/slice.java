// Autogenerated AST node on 08/11/2020, 15:59:03
package astnodes.base;
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

@ExposedType(name = "_ast.slice", base = AST.class)
public abstract class slice extends PythonTree {

    public static final PyType TYPE = PyType.fromClass(slice.class);
    private final static PyUnicode[] fields = new PyUnicode[0];
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes = new PyUnicode[0];
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public slice() {
    }

    public slice(PyType subType) {
    }

    public slice(int ttype, Token token) {
        super(ttype, token);
    }

    public slice(Token token) {
        super(token);
    }

    public slice(PythonTree node) {
        super(node);
    }

}
