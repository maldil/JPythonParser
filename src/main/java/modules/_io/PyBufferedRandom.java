package modules._io;

import heart.PyObject;
import heart.PyType;
import exposers.ExposedType;

@ExposedType(name = "_io.BufferedRandom")
public class PyBufferedRandom extends PyObject {

    public static final PyType TYPE = PyType.fromClass(PyBufferedRandom.class);

    public PyBufferedRandom() {
        super(TYPE);
    }
}
