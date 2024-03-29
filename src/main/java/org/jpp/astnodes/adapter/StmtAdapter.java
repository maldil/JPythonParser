package org.jpp.astnodes.adapter;

import java.util.ArrayList;
import java.util.List;

import org.jpp.astnodes.base.stmt;
import org.jpp.heart.Py;
import org.jpp.heart.PyObject;

public class StmtAdapter implements AstAdapter {

    public Object py2ast(PyObject o) {
        if (o instanceof stmt) {
            return o;
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
        List<stmt> stmts = new ArrayList<stmt>();
        if (iter != Py.None) {
            for(Object o : (Iterable)iter) {
                stmts.add((stmt)py2ast((PyObject)o));
            }
        }
        return stmts;
    }
}
