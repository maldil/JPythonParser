package astnodes.adapter;

import java.util.ArrayList;
import java.util.List;

import astnodes.ast.Num;
import astnodes.ast.Str;
import astnodes.base.expr;
import heart.Py;
import heart.PyBytes;
import heart.PyComplex;
import heart.PyFloat;
import heart.PyInteger;
import heart.PyLong;
import heart.PyObject;
import heart.PyUnicode;

public class ExprAdapter implements AstAdapter {

    public Object py2ast(PyObject o) {
        if (o instanceof expr) {
            return o;
        }
        if (o instanceof PyInteger || o instanceof PyLong || o instanceof PyFloat || o instanceof PyComplex) {
            return new Num(o);
        }
        if (o instanceof PyBytes || o instanceof PyUnicode) {
            return new Str(o);
        }
        return null;
    }

    public PyObject ast2py(Object o) {
        if (o == null) {
            return Py.None;
        }
        return (PyObject)o;
    }

    public List iter2ast(PyObject iter) {
        List<expr> exprs = new ArrayList<expr>();
        if (iter != Py.None) {
            for(Object o : (Iterable)iter) {
                exprs.add((expr)py2ast((PyObject)o));
            }
        }
        return exprs;
    }
}
