// Autogenerated AST node on 08/11/2020, 15:59:03
package org.jpp.astnodes.base;
import org.antlr.runtime.Token;
import org.jpp.astnodes.AST;
import org.jpp.astnodes.PythonTree;
import org.jpp.heart.PyUnicode;
import org.jpp.heart.PyType;
import org.jpp.exposers.ExposedGet;
import org.jpp.exposers.ExposedType;

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
