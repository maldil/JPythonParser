/* Copyright (c) Jython Developers */
package modules._weakref;

import heart.ArgParser;
import heart.ClassDictInit;
import heart.Py;
import heart.PyList;
import heart.PyObject;
import heart.PyStringMap;
import exposers.ExposedFunction;
import exposers.ExposedModule;
import exposers.ModuleInit;

/**
 * The _weakref module.
 */
@ExposedModule(name = "_weakref", doc = "Weak-reference support module.")
public class WeakrefModule {
    @ModuleInit
    public static void classDictInit(PyObject dict) {
        dict.__setitem__("ref", ReferenceType.TYPE);
        dict.__setitem__("ReferenceType", ReferenceType.TYPE);
        dict.__setitem__("ProxyType", ProxyType.TYPE);
        dict.__setitem__("CallableProxyType", CallableProxyType.TYPE);
    }

    @ExposedFunction
    public static ProxyType proxy(PyObject[] args, String[] keywords)  {
        ArgParser ap = new ArgParser("proxy", args, keywords, "object", "callback");
        PyObject object = ap.getPyObject(0);
        PyObject callback = ap.getPyObject(1, Py.None);
        if (callback != Py.None) {
            if (object.isCallable()) {
                return new CallableProxyType(GlobalRef.newInstance(object), callback);
            }
            return new ProxyType(GlobalRef.newInstance(object), callback);
        }
        GlobalRef gref = GlobalRef.newInstance(object);
        boolean callable = object.isCallable();
        ProxyType ret = (ProxyType)gref.find(callable ? CallableProxyType.class : ProxyType.class);
        if (ret != null) {
            return ret;
        }
        if (callable) {
            return new CallableProxyType(GlobalRef.newInstance(object), null);
        }
        return new ProxyType(GlobalRef.newInstance(object), null);
    }

    @ExposedFunction
    public static int getweakrefcount(PyObject object) {
        return GlobalRef.getCount(object);
    }

    @ExposedFunction
    public static PyList getweakrefs(PyObject object) {
        return GlobalRef.getRefs(object);
    }
}
