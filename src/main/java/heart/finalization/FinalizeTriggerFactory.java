package heart.finalization;

import heart.PyObject;

/**
 * Reserved for use by JyNI.
 */
public interface FinalizeTriggerFactory {

    public FinalizeTrigger makeTrigger(PyObject toFinalize);
}
