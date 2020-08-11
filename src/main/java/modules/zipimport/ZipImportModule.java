/* Copyright (c) 2007 Jython Developers */
package modules.zipimport;

import heart.BuiltinDocs;
import heart.ClassDictInit;
import heart.Py;
import heart.PyDictionary;
import heart.PyException;
import heart.PyObject;
import heart.PyBytes;
import heart.PyStringMap;
import exposers.ExposedModule;
import exposers.ModuleInit;

/**
 * This module adds the ability to import Python modules (*.py,
 * *$py.class) and packages from ZIP-format archives.
 *
 * @author Philip Jenvey
 */
@ExposedModule(name = "zipimport", doc = BuiltinDocs.zipimport_doc)
public class ZipImportModule {

    public static PyObject ZipImportError;
    public static PyException ZipImportError(String message) {
        return new PyException(ZipImportError, message);
    }

    // FIXME this cache should be per PySystemState, but at the very least it should also be weakly referenced!
    // FIXME could also do this via a loading cache instead
    public static PyDictionary _zip_directory_cache = new PyDictionary();

    @ModuleInit
    public static void init(PyObject dict) {
        dict.__setitem__("zipimporter", PyZipImporter.TYPE);
        dict.__setitem__("_zip_directory_cache", _zip_directory_cache);
        dict.__setitem__("ZipImportError", ZipImportError);
    }

    /**
     * Initialize the ZipImportError exception during startup
     *
     */
    public static void initClassExceptions(PyObject exceptions) {
        PyObject ImportError = exceptions.__finditem__("ImportError");
        ZipImportError = Py.makeClass("zipimport.ZipImportError", ImportError,
                                      new PyStringMap() {{
                                          __setitem__("__module__", Py.newString("zipimport"));
                                      }});
    }
}
