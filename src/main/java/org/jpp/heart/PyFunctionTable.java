// Copyright (c) Corporation for National Research Initiatives
package org.jpp.heart;

import java.io.Serializable;

/**
 * An entry point for class that implements several function calls.
 * <P>
 * Used together with the PyTableCode class.
 *
 * @see PyTableCode
 */

public abstract class PyFunctionTable implements Serializable {
    abstract public PyObject call_function(int index, PyFrame frame, ThreadState ts);
}
