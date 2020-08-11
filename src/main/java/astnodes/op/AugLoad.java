// Autogenerated AST node on 08/11/2020, 15:59:03
package astnodes.op;

import astnodes.AST;
import astnodes.base.expr_context;
import astnodes.PythonTree;
import heart.Py;
import heart.PyObject;
import heart.PyUnicode;
import heart.PyType;
import exposers.ExposedGet;
import exposers.ExposedMethod;
import exposers.ExposedNew;
import exposers.ExposedSet;
import exposers.ExposedType;

@ExposedType(name = "_ast.AugLoad", base = expr_context.class)
public class AugLoad extends PythonTree {
    public static final PyType TYPE = PyType.fromClass(AugLoad.class);

public AugLoad() {
}

public AugLoad(PyType subType) {
    super(subType);
}

@ExposedNew
@ExposedMethod
public void AugLoad___init__(PyObject[] args, String[] keywords) {}

    private final static PyUnicode[] fields = new PyUnicode[0];
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes = new PyUnicode[0];
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    @ExposedMethod
    public PyObject __int__() {
        return AugLoad___int__();
    }

    final PyObject AugLoad___int__() {
        return Py.newInteger(4);
    }

}
