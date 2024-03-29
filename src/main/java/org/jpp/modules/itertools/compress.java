/* Copyright (c) 2012 Jython Developers */
package org.jpp.modules.itertools;

import org.jpp.heart.ArgParser;
import org.jpp.heart.Py;
import org.jpp.heart.PyIterator;
import org.jpp.heart.PyObject;
import org.jpp.heart.PyType;
import org.jpp.heart.Visitproc;
import org.jpp.exposers.ExposedMethod;
import org.jpp.exposers.ExposedNew;
import org.jpp.exposers.ExposedType;

@ExposedType(name = "itertools.compress", base = PyObject.class, doc = compress.compress_doc)
public class compress extends PyIterator {

    public static final PyType TYPE = PyType.fromClass(compress.class);
    private itertools.ItertoolsIterator iter;

    public static final String compress_doc =
        "compress(data, selectors) --> iterator over selected data\n\n" +
        "Return data elements corresponding to true selector elements.\n" +
        "Forms a shorter iterator from selected data elements using the\n" +
        "selectors to choose the data elements.";

    public compress() {
        super();
    }

    public compress(PyType subType) {
        super(subType);
    }

    public compress(PyObject dataIterable, PyObject selectorsIterable) {
        super();
        compress___init__(dataIterable.__iter__(), selectorsIterable.__iter__());
    }

    @ExposedNew
    @ExposedMethod
    final void compress___init__(final PyObject[] args, String[] kwds) {
        ArgParser ap = new ArgParser("compress", args, kwds, "data", "selectors");
        if (args.length > 2) {
            throw Py.TypeError(String.format("compress() takes at most 2 arguments (%s given)", args.length));
        }
        PyObject data = ap.getPyObject(0).__iter__();
        PyObject selectors = ap.getPyObject(1).__iter__();

        compress___init__(data, selectors);
    }

    private void compress___init__(final PyObject data, final PyObject selectors) {

        iter = new itertools.ItertoolsIterator() {
            @Override
            public PyObject __next__() {
                while (true) {
                    PyObject datum = nextElement(data);
                    if (datum == null) { return null; }
                    PyObject selector = nextElement(selectors);
                    if (selector == null) { return null; }
                    if (selector.__bool__()) {
                        return datum;
                    }
                }
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
