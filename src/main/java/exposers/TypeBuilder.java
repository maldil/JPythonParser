package exposers;

import heart.PyObject;
import heart.PyType;

/**
 * Contains the basic information needed to construct a builtin Python type.
 */
public interface TypeBuilder {

    public String getName();

    public PyObject getDict(PyType type);

    public Class getTypeClass();

    public Class getBase();

    public boolean getIsBaseType();

    public String getDoc();
}
