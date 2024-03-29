package org.jpp.astnodes;


import org.antlr.runtime.Token;
import org.jpp.astnodes.ast.AsyncFor;
import org.jpp.astnodes.ast.AsyncFunctionDef;
import org.jpp.astnodes.ast.AsyncWith;
import org.jpp.astnodes.ast.Attribute;
import org.jpp.astnodes.ast.BinOp;
import org.jpp.astnodes.ast.BoolOp;
import org.jpp.astnodes.ast.Call;
import org.jpp.astnodes.ast.ClassDef;
import org.jpp.astnodes.ast.Context;
import org.jpp.astnodes.ast.DictComp;
import org.jpp.astnodes.ast.ExtSlice;
import org.jpp.astnodes.ast.For;
import org.jpp.astnodes.ast.FunctionDef;
import org.jpp.astnodes.ast.GeneratorExp;
import org.jpp.astnodes.ast.IfExp;
import org.jpp.astnodes.ast.Index;
import org.jpp.astnodes.ast.Lambda;
import org.jpp.astnodes.ast.ListComp;
import org.jpp.astnodes.ast.Name;
import org.jpp.astnodes.ast.Num;
import org.jpp.astnodes.ast.SetComp;
import org.jpp.astnodes.ast.Slice;
import org.jpp.astnodes.ast.Str;
import org.jpp.astnodes.ast.TryExcept;
import org.jpp.astnodes.ast.TryFinally;
import org.jpp.astnodes.ast.Tuple;
import org.jpp.astnodes.ast.UnaryOp;
import org.jpp.astnodes.ast.While;
import org.jpp.astnodes.ast.With;
import org.jpp.astnodes.ast.Yield;
import org.jpp.astnodes.ast.alias;
import org.jpp.astnodes.ast.arg;
import org.jpp.astnodes.ast.arguments;
import org.jpp.astnodes.ast.boolopType;
import org.jpp.astnodes.ast.cmpopType;
import org.jpp.astnodes.ast.expr_contextType;
import org.jpp.astnodes.ast.keyword;
import org.jpp.astnodes.ast.operatorType;
import org.jpp.astnodes.ast.unaryopType;
import org.jpp.astnodes.ast.withitem;
import org.jpp.astnodes.base.excepthandler;
import org.jpp.astnodes.base.expr;
import org.jpp.astnodes.base.slice;
import org.jpp.astnodes.base.stmt;
import org.jpp.heart.Py;
import org.jpp.heart.PyComplex;
import org.jpp.heart.PyFloat;
import org.jpp.heart.PyInteger;
import org.jpp.heart.PyLong;
import org.jpp.heart.PyUnicode;
import org.jpp.heart.stringlib.Encoding;
import org.jpp.heart.util.StringUtil;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GrammarActions {
    private ErrorHandler errorHandler = null;
    public GrammarActions() {
    }

    public void setErrorHandler(ErrorHandler eh) {
        this.errorHandler = eh;
    }

    String makeFromText(List dots, List<Name> names) {
        StringBuilder d = new StringBuilder();
        d.append(PythonTree.dottedNameListToString(names));
        return d.toString();
    }

    List<Name> makeModuleNameNode(List dots, List<Name> names) {
        List<Name> result = new ArrayList<Name>();
        if (dots != null) {
            for (Object o : dots) {
                Token tok = (Token)o;
                result.add(new Name(tok, tok.getText(), expr_contextType.Load));
            }
        }
        if (null != names) {
        	result.addAll(names);
        }
        return result;
    }

    List<Name> makeDottedName(Token top, List<PythonTree> attrs) {
      List<Name> result = new ArrayList<Name>();
      result.add(new Name(top, top.getText(), expr_contextType.Load));
      if (attrs != null) {
        for (PythonTree attr : attrs) {
          Token token = attr.getToken();
          result.add(new Name(token, token.getText(), expr_contextType.Load));
        }
      }
      return result;
    }

    int makeLevel(List lev) {
        if (lev == null) {
            return 0;
        }
        return lev.size();
    }

    List<alias> makeStarAlias(Token t) {
        List<alias> result = new ArrayList<alias>();
        result.add(new alias(t, "*", null));
        return result;
    }

    List<alias> makeAliases(List<alias> atypes) {
        if (atypes == null) {
            return new ArrayList<alias>();
        }
        return atypes;
    }

    List<expr> makeBases(expr etype) {
        List<expr> result = new ArrayList<expr>();
        if (etype != null) {
            if (etype instanceof Tuple) {
                return ((Tuple)etype).getInternalElts();
            }
            result.add(etype);
        }
        return result;
    }

    List<String> makeNames(List names) {
        List<String> s = new ArrayList<String>();
        for(int i=0;i<names.size();i++) {
            s.add(((Token)names.get(i)).getText());
        }
        return s;
    }

    Name makeNameNode(Token t) {
        if (t == null) {
            return null;
        }
        return new Name(t, t.getText(), expr_contextType.Load);
    }

    List<Name> makeNameNodes(List<Token> names) {
        List<Name> s = new ArrayList<Name>();
        for (int i=0; i<names.size(); i++) {
            s.add(makeNameNode(names.get(i)));
        }
        return s;
    }

    void errorGenExpNotSoleArg(PythonTree t) {
        errorHandler.error("Generator expression must be parenthesized if not sole argument", t);
    }

    void errorPositionalArgFollowsKeywordArg(PythonTree t) {
        errorHandler.error("positional argument follows keyword argument", t);
    }

    void errorNoNamedArguments(PythonTree t) {
        errorHandler.error("named arguments must follow bare *", t);
    }

    arg castArg(Object o) {
        if (o instanceof arg) {
            return (arg)o;
        } else if (o instanceof Name) {
            Name name = (Name) o;
            return new arg(name.getToken(), name.getInternalId(), null);
        }
        return null;
    }

    List<arg> castArgs(List<Object> args) {
        List<arg> ret = new ArrayList<>();
        if (args == null) {
            return ret;
        }
        for (Object o : args) {
            ret.add(castArg(o));
        }
        return ret;
    }

    expr castExpr(Object o) {
        if (o instanceof expr) {
            return (expr)o;
        }
        if (o instanceof PythonTree) {
            return errorHandler.errorExpr((PythonTree)o);
        }
        return null;
    }


    List<expr> castExprs(List exprs) {
        return castExprs(exprs, 0);
    }

    List<expr> castExprs(List exprs, int start) {
        List<expr> result = new ArrayList<expr>();
        if (exprs != null) {
            for (int i=start; i<exprs.size(); i++) {
                Object o = exprs.get(i);
                if (o instanceof expr) {
                    result.add((expr)o);
                } else if (o instanceof PythonParser.test_return) {
                    result.add((expr)((PythonParser.test_return)o).tree);
                }
            }
        }
        return result;
    }

    List<expr> castExprs(List exprs1, List exprs2) {
        List exprs = exprs1;
        if (exprs == null) {
            exprs = exprs2;
        } else if (exprs2 != null) {
            exprs.addAll(exprs2);
        }
        return castExprs(exprs);
    }

    List<stmt> makeElse(List elseSuite, PythonTree elif) {
        if (elseSuite != null) {
            return castStmts(elseSuite);
        } else if (elif == null) {
            return new ArrayList<stmt>();
        }
        List <stmt> s = new ArrayList<stmt>();
        s.add(castStmt(elif));
        return s;
    }

    stmt castStmt(Object o) {
        if (o instanceof stmt) {
            return (stmt)o;
        } else if (o instanceof PythonParser.stmt_return) {
            return (stmt)((PythonParser.stmt_return)o).tree;
        } else if (o instanceof PythonTree) {
            return errorHandler.errorStmt((PythonTree)o);
        }
        return null;
    }

    List<stmt> castStmts(PythonTree t) {
        stmt s = (stmt)t;
        List<stmt> stmts = new ArrayList<stmt>();
        stmts.add(s);
        return stmts;
    }

    public List<stmt> castStmts(List stmts) {
        if (stmts != null) {
            List<stmt> result = new ArrayList<stmt>();
            for (Object o:stmts) {
                result.add(castStmt(o));
            }
            return result;
        }
        return new ArrayList<stmt>();
    }

    expr makeDottedAttr(Token nameToken, List attrs) {
        expr current = new Name(nameToken, nameToken.getText(), expr_contextType.Load);
        for (Object o: attrs) {
            Token t = (Token)o;
            current = new Attribute(t, current, cantBeNoneName(t),
                expr_contextType.Load);
        }
        return current;
    }

    stmt makeWhile(Token t, expr test, List body, List orelse) {
        if (test == null) {
            return errorHandler.errorStmt(new PythonTree(t));
        }
        List<stmt> o = castStmts(orelse);
        List<stmt> b = castStmts(body);
        return new While(t, test, b, o);
    }

    stmt makeAsyncWith(Token t, List<withitem> items, List<stmt> body) {
        int last = items.size() - 1;
        AsyncWith result = null;
        for (int i = last; i>=0; i--) {
            withitem current = items.get(i);
            if (i != last) {
                body = new ArrayList<stmt>();
                body.add(result);
            }
            result = new AsyncWith(current.getToken(), Arrays.asList(current), body);
        }
        return result;
    }

    stmt makeWith(Token t, List<withitem> items, List<stmt> body) {
        int last = items.size() - 1;
        With result = null;
        for (int i = last; i>=0; i--) {
            withitem current = items.get(i);
            if (i != last) {
                body = new ArrayList<stmt>();
                body.add(result);
            }
            result = new With(current.getToken(), Arrays.asList(current), body);
        }
        return result;
    }

    stmt makeAsyncFor(Token t, Object stmt) {
        For forStmt = (For) castStmt(stmt);
        return new AsyncFor(t, forStmt.getInternalTarget(), forStmt.getInternalIter(),
                forStmt.getInternalBody(), forStmt.getInternalOrelse());
    }

    stmt makeFor(Token t, expr target, expr iter, List body, List orelse) {
        if (target == null || iter == null) {
            return errorHandler.errorStmt(new PythonTree(t));
        }
        cantBeNone(target);

        List<stmt> o = castStmts(orelse);
        List<stmt> b = castStmts(body);
        return new For(t, target, iter, b, o);
    }

    stmt makeTryExcept(Token t, List body, List<excepthandler> handlers, List orelse, List finBody) {
        List<stmt> b = castStmts(body);
        List<excepthandler> e = handlers;
        List<stmt> o = castStmts(orelse);
        stmt te = new TryExcept(t, b, e, o);
        if (finBody == null) {
            return te;
        }
        List<stmt> f = castStmts(finBody);
        List<stmt> mainBody = new ArrayList<stmt>();
        mainBody.add(te);
        return new TryFinally(t, mainBody, f);
    }

    TryFinally makeTryFinally(Token t,  List body, List finBody) {
        List<stmt> b = castStmts(body);
        List<stmt> f = castStmts(finBody);
        return new TryFinally(t, b, f);
    }

    stmt makeAsyncFuncdef(Token t, Object def) {
        FunctionDef func = (FunctionDef) castStmt(def);
        return new AsyncFunctionDef(t, func.getInternalNameNode(), func.getInternalArgs(),
                func.getInternalBody(), func.getInternalReturnNode());
    }

    stmt makeFuncdef(Token t, Token nameToken, arguments args, List funcStatements, expr returnNode) {
        if (nameToken == null) {
            return errorHandler.errorStmt(new PythonTree(t));
        }
        Name n = cantBeNoneName(nameToken);
        arguments a = args;
        if (a == null) {
            a = new arguments(t, new ArrayList<arg>(), (arg)null,
                    // kwonlyargs, kw_defaults, kwarg, defaults
                    new ArrayList<arg>(), new ArrayList<expr>(), (arg) null, new ArrayList<expr>());
        }
        List<stmt> s = castStmts(funcStatements);
        return new FunctionDef(t, n, a, s, returnNode);
    }

    List<expr> makeAssignTargets(expr lhs, List rhs) {
        List<expr> e = new ArrayList<expr>();
        checkAssign(lhs);
        e.add(lhs);
        for(int i=0;i<rhs.size() - 1;i++) {
            expr r = castExpr(rhs.get(i));
            checkAssign(r);
            e.add(r);
        }
        return e;
    }

    expr makeAssignValue(List rhs) {
        expr value = castExpr(rhs.get(rhs.size() -1));
        recurseSetContext(value, expr_contextType.Load);
        return value;
    }

    void recurseSetContext(PythonTree tree, expr_contextType context) {
        if (tree instanceof Context) {
            ((Context)tree).setContext(context);
        }
        if (tree instanceof GeneratorExp) {
            GeneratorExp g = (GeneratorExp)tree;
            recurseSetContext(g.getInternalElt(), context);
        } else if (tree instanceof ListComp) {
            ListComp lc = (ListComp)tree;
            recurseSetContext(lc.getInternalElt(), context);
        } else if (tree instanceof SetComp) {
            SetComp sc = (SetComp)tree;
            recurseSetContext(sc.getInternalElt(), context);
        } else if (tree instanceof DictComp) {
            DictComp dc = (DictComp)tree;
            recurseSetContext(dc.getInternalKey(), context);
            recurseSetContext(dc.getInternalValue(), context);
        } else if (!(tree instanceof ListComp) &&
                  (!(tree instanceof DictComp)) &&
                  (!(tree instanceof SetComp))) {
            for (int i=0; i<tree.getChildCount(); i++) {
                recurseSetContext(tree.getChild(i), context);
            }
        }
    }

    List<expr> extractArgs(List args) {
        return castExprs(args);
    }

    List<keyword> makeKeywords(List args) {
        List<keyword> keywords = new ArrayList<keyword>();
        if (args != null) {
            for (Object o : args) {
                List e = (List)o;
                Object k = e.get(0);
                Object v = e.get(1);
                checkAssign(castExpr(k));
                if (k instanceof Name) {
                    Name arg = (Name) k;
                    keywords.add(new keyword(arg, arg.getInternalId(), castExpr(v)));
                } else if (k == null) {
                    keywords.add(new keyword(null, castExpr(v)));
                } else {
                    errorHandler.error("keyword must be a name", (PythonTree)k);
                }
            }
        }
        return keywords;
    }

    Object makeFloat(Token t) {
        return Py.newFloat(Double.valueOf(t.getText()));
    }

    Object makeComplex(Token t) {
        String s = t.getText();
        s = s.substring(0, s.length() - 1);
        return Py.newImaginary(Double.valueOf(s));
    }

    //XXX: needs to handle NumberFormatException (on input like 0b2) and needs
    //     a better long guard than ndigits > 11 (this is much to short for
    //     binary for example)
    Object makeInt(Token t) {
        String s = t.getText();
        int radix = 10;
        if (s.startsWith("0x") || s.startsWith("0X")) {
            radix = 16;
            s = s.substring(2, s.length());
        } else if (s.startsWith("0o") || s.startsWith("0O")) {
            radix = 8;
            s = s.substring(2, s.length());
        } else if (s.startsWith("0b") || s.startsWith("0B")) {
            radix = 2;
            s = s.substring(2, s.length());
        } else if (s.startsWith("0")) {
            radix = 8;
        }
        if (s.endsWith("L") || s.endsWith("l")) {
            s = s.substring(0, s.length()-1);
            return Py.newLong(new BigInteger(s, radix));
        }
        int ndigits = s.length();
        int i=0;
        while (i < ndigits && s.charAt(i) == '0') {
            i++;
        }
        if ((ndigits - i) > 11) {
            return Py.newLong(new BigInteger(s, radix));
        }

        long l = Long.valueOf(s, radix).longValue();
        if (l > 0xffffffffl || (l > Integer.MAX_VALUE)) {
            return Py.newLong(new BigInteger(s, radix));
        }
        return Py.newInteger((int) l);
    }

    class StringPair {
        private String s;
        private boolean unicode;

        StringPair(String s, boolean unicode) {
            this.s = s;
            this.unicode = unicode;
        }
        String getString() {
            return s;
        }

        boolean isUnicode() {
            return unicode;
        }
    }

    Object extractStrings(List s, String encoding, boolean unicodeLiterals) {
        boolean ustring = false;
        Token last = null;
        StringBuffer sb = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            last = (Token)iter.next();
            StringPair sp = extractString(last, encoding, unicodeLiterals);
            if (sp.isUnicode()) {
                ustring = true;
            }
            sb.append(sp.getString());
        }
        if (ustring) {
            return new PyUnicode(sb.toString(), false);
        }
        return sb.toString();
    }

    StringPair extractString(Token t, String encoding, boolean unicodeLiterals) {
        String string = t.getText();
        char quoteChar = string.charAt(0);
        int start = 0;
        int end;
        boolean ustring = unicodeLiterals;

        if (quoteChar == 'u' || quoteChar == 'U') {
            ustring = true;
            start++;
        }
        if (quoteChar == 'b' || quoteChar == 'B') {
            // In 2.x this is just a str, and the parser prevents a 'u' and a
            // 'b' in the same identifier, so just advance start.
            ustring = false;
            start++;
        }
        quoteChar = string.charAt(start);
        boolean raw = false;
        if (quoteChar == 'r' || quoteChar == 'R') {
            raw = true;
            start++;
        }
        int quotes = 3;
        if (string.length() - start == 2) {
            quotes = 1;
        }
        if (string.charAt(start) != string.charAt(start+1)) {
            quotes = 1;
        }

        start = quotes + start;
        end = string.length() - quotes;
        // string is properly decoded according to the source encoding
        // XXX: No need to re-encode when the encoding is iso-8859-1, but ParserFacade
        // needs to normalize the encoding name
        if (!ustring && encoding != null) {
            // The parser used a non-latin encoding: re-encode chars to bytes.
            Charset cs = Charset.forName(encoding);
            ByteBuffer decoded = cs.encode(string.substring(start, end));
            string = StringUtil.fromBytes(decoded);
            if (!raw) {
                // Handle escapes in non-raw strs
                string = Encoding.decode_UnicodeEscape(string, 0, string.length(), "strict", ustring);
            }
        } else if (raw) {
            // Raw bytes
            string = string.substring(start, end);
//            if (ustring) {
//                // Raw unicode: handle unicode escapes
//                string = codecs.PyUnicode_DecodeRawUnicodeEscape(string, "strict");
//            }
        } else {
            // Plain unicode: already decoded, just handle escapes
            string = Encoding.decode_UnicodeEscape(string, start, end, "strict", ustring);
        }
        return new StringPair(string, ustring);
    }

    Token extractStringToken(List s) {
        return (Token)s.get(0);
        //return (Token)s.get(s.size() - 1);
    }

    expr makeCall(Token t, expr func) {
        return makeCall(t, func, null, null, null, null);
    }

    expr makeCall(Token t, expr func, List args, List keywords) {
        if (func == null) {
            return errorHandler.errorExpr(new PythonTree(t));
        }
        List<keyword> k = makeKeywords(keywords);
        List<expr> a = castExprs(args);
        return new Call(t, func, a, k);
    }

    expr makeCall(Token t, expr func, List args, List keywords, expr starargs, expr kwargs) {
        return makeCall(t, func, args, keywords);
    }

    stmt makeClass(Token t, Token nameToken, List args, List ktypes, List stypes) {
        String name = cantBeNone(nameToken);
        List<expr> bases = castExprs(args);
        List<stmt> statements = castStmts(stypes);
        List<keyword> keywords = makeKeywords(ktypes);
        return new ClassDef(t, name, bases, keywords, statements, new ArrayList<expr>());
    }

    expr negate(Token t, expr o) {
        return negate(new PythonTree(t), o);
    }

    expr negate(PythonTree t, expr o) {
        if (o instanceof Num) {
            Num num = (Num)o;
            if (num.getInternalN() instanceof PyInteger) {
                int v = ((PyInteger)num.getInternalN()).getValue();
                if (v >= 0) {
                    num.setN(new PyInteger(-v));
                    return num;
                }
            } else if (num.getInternalN() instanceof PyLong) {
                BigInteger v = ((PyLong)num.getInternalN()).getValue();
                if (v.compareTo(BigInteger.ZERO) == 1) {
                    num.setN(new PyLong(v.negate()));
                    return num;
                }
            } else if (num.getInternalN() instanceof PyFloat) {
                double v = ((PyFloat)num.getInternalN()).getValue();
                if (v >= 0) {
                    num.setN(new PyFloat(-v));
                    return num;
                }
            } else if (num.getInternalN() instanceof PyComplex) {
                double v = ((PyComplex)num.getInternalN()).imag;
                if (v >= 0) {
                    num.setN(new PyComplex(0,-v));
                    return num;
                }
            }
        }
        return new UnaryOp(t, unaryopType.USub, o);
    }

    String cantBeNone(Token t) {
        if (t == null || t.getText().equals("None")) {
            errorHandler.error("can't be None", new PythonTree(t));
        }
        return t.getText();
    }

    Name cantBeNoneName(Token t) {
        if (t == null || t.getText().equals("None")) {
            errorHandler.error("can't be None", new PythonTree(t));
        }
        return new Name(t, t.getText(), expr_contextType.Load);
    }

    void cantBeNone(PythonTree e) {
        if (e.getText().equals("None")) {
            errorHandler.error("can't be None", e);
        }
    }

    private void checkGenericAssign(expr e) {
        if (e instanceof Name && ((Name)e).getInternalId().equals("None")) {
            errorHandler.error("assignment to None", e);
        } else if (e instanceof GeneratorExp) {
            errorHandler.error("can't assign to generator expression", e);
        } else if (e instanceof Num) {
            errorHandler.error("can't assign to number", e);
        } else if (e instanceof Str) {
            errorHandler.error("can't assign to string", e);
        } else if (e instanceof Yield) {
            errorHandler.error("can't assign to yield expression", e);
        } else if (e instanceof BinOp) {
            errorHandler.error("can't assign to operator", e);
        } else if (e instanceof BoolOp) {
            errorHandler.error("can't assign to operator", e);
        } else if (e instanceof Lambda) {
            errorHandler.error("can't assign to lambda", e);
        } else if (e instanceof Call) {
            errorHandler.error("can't assign to function call", e);
        } else if (e instanceof IfExp) {
            errorHandler.error("can't assign to conditional expression", e);
        } else if (e instanceof ListComp) {
            errorHandler.error("can't assign to list comprehension", e);
        } else if (e instanceof SetComp) {
            errorHandler.error("can't assign to set comprehension", e);
        } else if (e instanceof DictComp) {
            errorHandler.error("can't assign to dict comprehension", e);
        }
     }

    void checkAugAssign(expr e) {
        checkGenericAssign(e);
        if (e instanceof Tuple) {
            errorHandler.error("assignment to tuple illegal for augmented assignment", e);
        } else if (e instanceof org.jpp.astnodes.ast.List) {
            errorHandler.error("assignment to list illegal for augmented assignment", e);
        }
    }

    void checkAssign(expr e) {
        checkGenericAssign(e);
        if (e instanceof Tuple) {
            //XXX: performance problem?  Any way to do this better?
            List<expr> elts = ((Tuple)e).getInternalElts();
            if (elts.size() == 0) {
                errorHandler.error("can't assign to ()", e);
            }
            for (int i=0;i<elts.size();i++) {
                checkAssign(elts.get(i));
            }
        } else if (e instanceof org.jpp.astnodes.ast.List) {
            //XXX: performance problem?  Any way to do this better?
            List<expr> elts = ((org.jpp.astnodes.ast.List)e).getInternalElts();
            for (int i=0;i<elts.size();i++) {
                checkAssign(elts.get(i));
            }
        }
    }

    List<expr> makeDeleteList(List deletes) {
        List<expr> exprs = castExprs(deletes);
        for(expr e : exprs) {
            checkDelete(e);
        }
        return exprs;
    }

    void checkDelete(expr e) {
        if (e instanceof Call) {
            errorHandler.error("can't delete function call", e);
        } else if (e instanceof Num) {
            errorHandler.error("can't delete number", e);
        } else if (e instanceof Str) {
            errorHandler.error("can't delete string", e);
        } else if (e instanceof Tuple) {
            //XXX: performance problem?  Any way to do this better?
            List<expr> elts = ((Tuple)e).getInternalElts();
            if (elts.size() == 0) {
                errorHandler.error("can't delete ()", e);
            }
            for (int i=0;i<elts.size();i++) {
                checkDelete(elts.get(i));
            }
        } else if (e instanceof org.jpp.astnodes.ast.List) {
            //XXX: performance problem?  Any way to do this better?
            List<expr> elts = ((org.jpp.astnodes.ast.List)e).getInternalElts();
            for (int i=0;i<elts.size();i++) {
                checkDelete(elts.get(i));
            }
        }
    }

    slice makeSubscript(PythonTree lower, Token colon, PythonTree upper, PythonTree sliceop) {
            boolean isSlice = false;
        expr s = null;
        expr e = null;
        expr o = null;
        if (lower != null) {
            s = castExpr(lower);
        }
        if (colon != null) {
            isSlice = true;
            if (upper != null) {
                e = castExpr(upper);
            }
        }
        if (sliceop != null) {
            isSlice = true;
            if (sliceop != null) {
                o = castExpr(sliceop);
            } else {
                o = new Name(sliceop, "None", expr_contextType.Load);
            }
        }

        PythonTree tok = lower;
        if (lower == null) {
            tok = new PythonTree(colon);
        }
        if (isSlice) {
           return new Slice(tok, s, e, o);
        }
        else {
           return new Index(tok, s);
        }
    }

    List<cmpopType> makeCmpOps(List cmps) {
        List<cmpopType> result = new ArrayList<cmpopType>();
        if (cmps != null) {
            for (Object o: cmps) {
                result.add((cmpopType)o);
            }
        }
        return result;
    }

    BoolOp makeBoolOp(Token t, PythonTree left, boolopType op, List right) {
        List values = new ArrayList();
        values.add(left);
        values.addAll(right);
        return new BoolOp(t, op, castExprs(values));
    }

    BinOp makeBinOp(Token t, PythonTree left, operatorType op, List rights) {
        BinOp current = new BinOp(t, castExpr(left), op, castExpr(rights.get(0)));
        for (int i = 1; i< rights.size(); i++) {
            expr right = castExpr(rights.get(i));
            current = new BinOp(left, current, op, right);
        }
        return current;
    }

    BinOp makeBinOp(Token t, PythonTree left, List ops, List rights, List toks) {
        BinOp current = new BinOp(t, castExpr(left), (operatorType)ops.get(0), castExpr(rights.get(0)));
        for (int i = 1; i< rights.size(); i++) {
            expr right = castExpr(rights.get(i));
            operatorType op = (operatorType)ops.get(i);
            current = new BinOp((Token)toks.get(i), current, op, right);
        }
        return current;
    }

    List<slice> castSlices(List slices) {
        List<slice> result = new ArrayList<slice>();
        if (slices != null) {
            for (Object o:slices) {
                result.add(castSlice(o));
            }
        }
        return result;
    }

    slice castSlice(Object o) {
        if (o instanceof slice) {
            return (slice)o;
        }
        return errorHandler.errorSlice((PythonTree)o);
    }

    slice makeSliceType(Token begin, Token c1, Token c2, List sltypes) {
        boolean isTuple = false;
        if (c1 != null || c2 != null) {
            isTuple = true;
        }
        slice s = null;
        boolean extslice = false;

        if (isTuple) {
            List<slice> st;
            List<expr> etypes = new ArrayList<expr>();
            for (Object o : sltypes) {
                if (o instanceof Index) {
                    Index i = (Index)o;
                    etypes.add(i.getInternalValue());
                } else {
                    extslice = true;
                    break;
                }
            }
            if (!extslice) {
                expr t = new Tuple(begin, etypes, expr_contextType.Load);
                s = new Index(begin, t);
            }
        } else if (sltypes.size() == 1) {
            s = castSlice(sltypes.get(0));
        } else if (sltypes.size() != 0) {
            extslice = true;
        }
        if (extslice) {
            List<slice> st = castSlices(sltypes);
            s = new ExtSlice(begin, st);
        }
        return s;
    }
}
