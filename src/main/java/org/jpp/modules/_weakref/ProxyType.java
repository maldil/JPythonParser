/* Copyright (c) Jython Developers */
package org.jpp.modules._weakref;

import org.jpp.heart.PyComplex;
import org.jpp.heart.PyFloat;
import org.jpp.heart.PyObject;
import org.jpp.heart.PyType;
import org.jpp.heart.PyUnicode;
import org.jpp.exposers.ExposedType;

/**
 * A weak reference proxy object.
 */
@ExposedType(name = "weakproxy", isBaseType = false)
public class ProxyType extends AbstractReference {

    public static final PyType TYPE = PyType.fromClass(ProxyType.class);

    public ProxyType(PyType subType, GlobalRef ref, PyObject callback) {
        super(subType, ref, callback);
    }

    public ProxyType(GlobalRef ref, PyObject callback) {
        this(TYPE, ref, callback);
    }

    public boolean __bool__() { return py().__bool__(); }
    public int __len__() { return py().__len__(); }

    public PyObject __finditem__(PyObject key) { return py().__finditem__(key); }
    public void __setitem__(PyObject key, PyObject value) { py().__setitem__(key, value); }
    public void __delitem__(PyObject key) { py().__delitem__(key); }

    public PyObject __findattr_ex__(String name) { return py().__findattr_ex__(name); }
    public void __setattr__(String name, PyObject value) { py().__setattr__(name, value); }
    public void __delattr__(String name) { py().__delattr__(name); }

    public PyObject __iter__() { return py().__iter__(); }
    public PyUnicode __str__() { return py().__str__(); }
    public PyFloat __float__() { return py().__float__(); }
    public PyObject __int__() { return py().__int__(); }
    public PyComplex __complex__() { return py().__complex__(); }
    public PyObject __pos__() { return py().__pos__(); }
    public PyObject __neg__() { return py().__neg__(); }
    public PyObject __abs__() { return py().__abs__(); }
    public PyObject __invert__() { return py().__invert__(); }


    public boolean __contains__(PyObject o) { return py().__contains__(o); }
    public PyObject __index__() { return py().__index__(); }

    public PyObject __add__(PyObject o) { return py().__add__(o); }
    public PyObject __radd__(PyObject o) { return py().__radd__(o); }
    public PyObject __iadd__(PyObject o) { return py().__iadd__(o); }
    public PyObject __sub__(PyObject o) { return py().__sub__(o); }
    public PyObject __rsub__(PyObject o) { return py().__rsub__(o); }
    public PyObject __isub__(PyObject o) { return py().__isub__(o); }
    public PyObject __mul__(PyObject o) { return py().__mul__(o); }
    public PyObject __rmul__(PyObject o) { return py().__rmul__(o); }
    public PyObject __imul__(PyObject o) { return py().__imul__(o); }
    public PyObject __truediv__(PyObject o) { return py().__truediv__(o); }
    public PyObject __floordiv__(PyObject o) { return py().__floordiv__(o); }
    public PyObject __rtruediv__(PyObject o) { return py().__rtruediv__(o); }
    public PyObject __idiv__(PyObject o) { return py().__idiv__(o); }
    public PyObject __ifloordiv__(PyObject o) { return py().__ifloordiv__(o); }
    public PyObject __mod__(PyObject o) { return py().__mod__(o); }
    public PyObject __rmod__(PyObject o) { return py().__rmod__(o); }
    public PyObject __imod__(PyObject o) { return py().__imod__(o); }
    public PyObject __divmod__(PyObject o) { return py().__divmod__(o); }
    public PyObject __rdivmod__(PyObject o) { return py().__rdivmod__(o);}
    public PyObject __pow__(PyObject o) { return py().__pow__(o); }
    public PyObject __rpow__(PyObject o) { return py().__rpow__(o); }
    public PyObject __ipow__(PyObject o) { return py().__ipow__(o); }
    public PyObject __lshift__(PyObject o) { return py().__lshift__(o); }
    public PyObject __rlshift__(PyObject o) { return py().__rlshift__(o);}
    public PyObject __ilshift__(PyObject o) { return py().__ilshift__(o);}
    public PyObject __rshift__(PyObject o) { return py().__rshift__(o); }
    public PyObject __rrshift__(PyObject o) { return py().__rrshift__(o);}
    public PyObject __irshift__(PyObject o) { return py().__irshift__(o);}
    public PyObject __and__(PyObject o) { return py().__and__(o); }
    public PyObject __rand__(PyObject o) { return py().__rand__(o); }
    public PyObject __iand__(PyObject o) { return py().__iand__(o); }
    public PyObject __or__(PyObject o) { return py().__or__(o); }
    public PyObject __ror__(PyObject o) { return py().__ror__(o); }
    public PyObject __ior__(PyObject o) { return py().__ior__(o); }
    public PyObject __xor__(PyObject o) { return py().__xor__(o); }
    public PyObject __rxor__(PyObject o) { return py().__rxor__(o); }
    public PyObject __ixor__(PyObject o) { return py().__ixor__(o); }
}
