package modules.random;

import heart.ClassDictInit;
import heart.Py;
import heart.PyObject;

public class RandomModule implements ClassDictInit {

    private RandomModule() {}

    public static void classDictInit(PyObject dict) {
        dict.invoke("clear");
        dict.__setitem__("Random", PyRandom.TYPE);
        dict.__setitem__("__name__", Py.newString("_random"));
    }
}
