// Autogenerated AST node on 08/11/2020, 15:59:03
package astnodes.op;

import astnodes.AST;
import astnodes.base.cmpop;
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

@ExposedType(name = "_ast.Eq", base = cmpop.class)
public class Eq extends PythonTree {
    public static final PyType TYPE = PyType.fromClass(Eq.class);

public Eq() {
}

public Eq(PyType subType) {
    super(subType);
}

@ExposedNew
@ExposedMethod
public void Eq___init__(PyObject[] args, String[] keywords) {}

    private final static PyUnicode[] fields = new PyUnicode[0];
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes = new PyUnicode[0];
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    @ExposedMethod
    public PyObject __int__() {
        return Eq___int__();
    }

    final PyObject Eq___int__() {
        return Py.newInteger(1);
    }

}