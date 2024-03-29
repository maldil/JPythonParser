package org.jpp.astnodes;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.jpp.astnodes.base.expr;
import org.jpp.astnodes.base.mod;
import org.jpp.astnodes.base.slice;
import org.jpp.astnodes.base.stmt;

public interface ErrorHandler {
    void reportError(BaseRecognizer br, RecognitionException re);
    void recover(BaseRecognizer br, IntStream input, RecognitionException re);
    void recover(Lexer lex, RecognitionException re);

    /**
     * @return True if the caller should handle the mismatch
     */
    boolean mismatch(BaseRecognizer br, IntStream input, int ttype, BitSet follow)
        throws RecognitionException;

    /**
     * @return null if the caller should handle the mismatch
     */
    Object recoverFromMismatchedToken(BaseRecognizer br, IntStream input, int ttype, BitSet follow)
        throws RecognitionException;

    //expr, mod, slice, stmt
    expr errorExpr(PythonTree t);
    mod errorMod(PythonTree t);
    slice errorSlice(PythonTree t);
    stmt errorStmt(PythonTree t);

    //Exceptions
    void error(String message, PythonTree t);
}
