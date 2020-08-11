package astnodes;


import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import astnodes.ast.ErrorExpr;
import astnodes.ast.ErrorMod;
import astnodes.ast.ErrorSlice;
import astnodes.ast.ErrorStmt;
import astnodes.base.expr;
import astnodes.base.mod;
import astnodes.base.slice;
import astnodes.base.stmt;

import java.util.ArrayList;
import java.util.List;

public class RecordingErrorHandler implements ErrorHandler {

    public List<RecognitionException> errs = new ArrayList<RecognitionException>();

    public void reportError(BaseRecognizer br, RecognitionException re) {
        errs.add(re);
    }

    public void recover(Lexer lex, RecognitionException re) {
        lex.recover(re);
    }

    public void recover(BaseRecognizer br, IntStream input, RecognitionException re) {
        br.recover(input, re);
    }

    public boolean mismatch(BaseRecognizer br, IntStream input, int ttype, BitSet follow) {
        return true;
    }

    public Object recoverFromMismatchedToken(BaseRecognizer br, IntStream input,
                                             int ttype, BitSet follow) {
        return null;
    }

    public expr errorExpr(PythonTree t) {
        return new ErrorExpr(t);
    }

    public mod errorMod(PythonTree t) {
        return new ErrorMod(t);
    }

    public slice errorSlice(PythonTree t) {
        return new ErrorSlice(t);
    }

    public stmt errorStmt(PythonTree t) {
        return new ErrorStmt(t);
    }

    public void error(String message, PythonTree t) {
        System.err.println(message);
    }
}
