/* Copyright (c) 2012 Jython Developers */
package org.jpp.modules.itertools;

import org.jpp.heart.Py;
import org.jpp.heart.PyIterator;
import org.jpp.heart.PyObject;
import org.jpp.heart.PyTuple;
import org.jpp.heart.PyType;
import org.jpp.heart.Visitproc;
import org.jpp.exposers.ExposedMethod;
import org.jpp.exposers.ExposedNew;
import org.jpp.exposers.ExposedType;

@ExposedType(name = "itertools.starmap", base = PyObject.class, doc = starmap.starmap_doc)
public class starmap extends PyIterator {

    public static final PyType TYPE = PyType.fromClass(starmap.class);
    private PyIterator iter;

    public static final String starmap_doc =
        "starmap(function, sequence) --> starmap object\n\n" +
        "Return an iterator whose values are returned from the function evaluated\n" +
        "with an argument tuple taken from the given sequence.";

    public starmap() {
        super();
    }

    public starmap(PyType subType) {
        super(subType);
    }

    public starmap(PyObject callable, PyObject iterator) {
        super();
        starmap___init__(callable, iterator);
    }

    /**
     * Create an iterator whose <code>next()</code> method returns the result
     * of calling the function (first argument) with a tuple of arguments
     * returned from the iterable (second argument).
     *
     * @param starargs
     *            [0] = callable function, [1] = iterable with argument tuples
     */
    @ExposedNew
    @ExposedMethod
    final void starmap___init__(PyObject[] starargs, String[] kwds) {
        if (starargs.length != 2) {
            throw Py.TypeError("starmap requires 2 arguments, got "
                    + starargs.length);
        }
        final PyObject callable = starargs[0];
        final PyObject iterator = starargs[1].__iter__();

        starmap___init__(callable, iterator);
    }

    private void starmap___init__(final PyObject callable, final PyObject iterator) {
        iter = new itertools.ItertoolsIterator() {

            public PyObject __next__() {
                PyObject args = nextElement(iterator);
                PyObject result = null;

                if (args != null) {
                    PyTuple argTuple = PyTuple.fromIterable(args);
                    // convert to array of PyObjects in call to function
                    result = callable.__call__(argTuple.getArray());
                }
                return result;
            }

        };
    }

    public PyObject __next__() {
        return iter.__next__();
    }

    @ExposedMethod
    @Override
    public PyObject next() {
        return doNext(__next__());
    }


    /* Traverseproc implementation */
    @Override
    public int traverse(Visitproc visit, Object arg) {
        int retVal = super.traverse(visit, arg);
        if (retVal != 0) {
            return retVal;
        }
        return iter != null ? visit.visit(iter, arg) : 0;
    }

    @Override
    public boolean refersDirectlyTo(PyObject ob) {
        return ob != null && (iter == ob || super.refersDirectlyTo(ob));
    }
}
