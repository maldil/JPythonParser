// Used to manage re-entrant context on the stack, as opposed to a thread-specific global state
package org.jpp.heart;

class ThreadContext {

    static ThreadLocal<Object[]> initializingProxy = new ThreadLocal<Object[]>() {
        protected Object[] initialValue() {
            return new Object[1];
        }
    };
}
