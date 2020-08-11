// Copyright (c) Corporation for National Research Initiatives
package heart;

public class StderrWrapper extends StdoutWrapper {
    public StderrWrapper() {
        this.name = "stderr";
    }

    protected PyObject getObject(PySystemState ss) {
        return ss.getStderr();
    }

    protected void setObject(PySystemState ss, PyObject obj) {
        ss.setStderr(obj);
    }
}
