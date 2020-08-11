// Copyright (c) Corporation for National Research Initiatives
package modules.thread;

import heart.PyObject;
import heart.ContextManager;
import heart.Py;
import heart.ThreadState;
import heart.PyException;
import heart.Untraversable;
import exposers.ExposedMethod;
import exposers.ExposedType;

@Untraversable
@ExposedType(name = "_thread.lock")
public class PyLock extends PyObject implements ContextManager {

    private boolean locked = false;

    public boolean acquire() {
        return acquire(true, -1);
    }

    @ExposedMethod(names = "acquire", defaults = {"true", "-1"})
    public synchronized boolean acquire(boolean blocking, int timeout) {
        if (blocking) {
            while (locked) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted thread");
                }
            }
            locked = true;
            return true;
        } else {
            if (locked) {
                return false;
            } else {
                locked = true;
                return true;
            }
        }
    }

    @ExposedMethod(names = "release")
    public synchronized void release() {
        if (locked) {
            locked = false;
            notifyAll();
        } else {
            throw Py.ValueError("lock not acquired");
        }
    }

    @ExposedMethod
    public boolean lock_locked() {
        return locked;
    }

    @ExposedMethod
    public PyObject lock___enter__() {
        return __enter__(Py.getThreadState());
    }

    @Override
    public PyObject __enter__(ThreadState ts) {
        acquire();
        return this;
    }

    @ExposedMethod
    public boolean lock___exit__(PyObject type, PyObject value, PyObject traceback) {
        return __exit__(Py.getThreadState(), null);
    }

    @Override
    public boolean __exit__(ThreadState ts, PyException exception) {
        release();
        return false;
    }
}
