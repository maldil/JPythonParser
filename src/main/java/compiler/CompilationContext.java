
package compiler;

import astnodes.PythonTree;

public interface CompilationContext {

    public Future getFutures();
    public void error(String msg,boolean err,PythonTree node)
        throws Exception;

    public String getFilename();


    public ScopeInfo getScopeInfo(PythonTree node);
}
