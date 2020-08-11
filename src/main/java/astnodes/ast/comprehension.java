// Autogenerated AST node on 08/11/2020, 15:59:03
package astnodes.ast;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import astnodes.AST;
import astnodes.PythonTree;
import astnodes.adapter.AstAdapters;
import astnodes.base.excepthandler;
import astnodes.base.expr;
import astnodes.base.mod;
import astnodes.base.slice;
import astnodes.base.stmt;
import heart.ArgParser;
import heart.AstList;
import heart.Py;
import heart.PyObject;
import heart.PyUnicode;
import heart.PyStringMap;
import heart.PyType;
import heart.Visitproc;
import exposers.ExposedGet;
import exposers.ExposedMethod;
import exposers.ExposedNew;
import exposers.ExposedSet;
import exposers.ExposedType;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@ExposedType(name = "_ast.comprehension", base = AST.class)
public class comprehension extends PythonTree {
    public static final PyType TYPE = PyType.fromClass(comprehension.class);
    private expr target;
    public expr getInternalTarget() {
        return target;
    }
    @ExposedGet(name = "target")
    public PyObject getTarget() {
        return target;
    }
    @ExposedSet(name = "target")
    public void setTarget(PyObject target) {
        this.target = AstAdapters.py2expr(target);
    }

    private expr iter;
    public expr getInternalIter() {
        return iter;
    }
    @ExposedGet(name = "iter")
    public PyObject getIter() {
        return iter;
    }
    @ExposedSet(name = "iter")
    public void setIter(PyObject iter) {
        this.iter = AstAdapters.py2expr(iter);
    }

    private java.util.List<expr> ifs;
    public java.util.List<expr> getInternalIfs() {
        return ifs;
    }
    @ExposedGet(name = "ifs")
    public PyObject getIfs() {
        return new AstList(ifs, AstAdapters.exprAdapter);
    }
    @ExposedSet(name = "ifs")
    public void setIfs(PyObject ifs) {
        this.ifs = AstAdapters.py2exprList(ifs);
    }


    private final static PyUnicode[] fields =
    new PyUnicode[] {new PyUnicode("target"), new PyUnicode("iter"), new PyUnicode("ifs")};
    @ExposedGet(name = "_fields")
    public PyUnicode[] get_fields() { return fields; }

    private final static PyUnicode[] attributes = new PyUnicode[0];
    @ExposedGet(name = "_attributes")
    public PyUnicode[] get_attributes() { return attributes; }

    public comprehension(PyType subType) {
        super(subType);
    }
    public comprehension() {
        this(TYPE);
    }
    @ExposedNew
    @ExposedMethod
    public void comprehension___init__(PyObject[] args, String[] keywords) {
        ArgParser ap = new ArgParser("comprehension", args, keywords, new String[]
            {"target", "iter", "ifs"}, 3, true);
        setTarget(ap.getPyObject(0, Py.None));
        setIter(ap.getPyObject(1, Py.None));
        setIfs(ap.getPyObject(2, Py.None));
    }

    public comprehension(PyObject target, PyObject iter, PyObject ifs) {
        setTarget(target);
        setIter(iter);
        setIfs(ifs);
    }

    public comprehension(Token token, expr target, expr iter, java.util.List<expr> ifs) {
        super(token);
        this.target = target;
        addChild(target);
        this.iter = iter;
        addChild(iter);
        this.ifs = ifs;
        if (ifs == null) {
            this.ifs = new ArrayList<expr>();
        }
        for(PythonTree t : this.ifs) {
            addChild(t);
        }
    }

    public comprehension(Integer ttype, Token token, expr target, expr iter, java.util.List<expr>
    ifs) {
        super(ttype, token);
        this.target = target;
        addChild(target);
        this.iter = iter;
        addChild(iter);
        this.ifs = ifs;
        if (ifs == null) {
            this.ifs = new ArrayList<expr>();
        }
        for(PythonTree t : this.ifs) {
            addChild(t);
        }
    }

    public comprehension(PythonTree tree, expr target, expr iter, java.util.List<expr> ifs) {
        super(tree);
        this.target = target;
        addChild(target);
        this.iter = iter;
        addChild(iter);
        this.ifs = ifs;
        if (ifs == null) {
            this.ifs = new ArrayList<expr>();
        }
        for(PythonTree t : this.ifs) {
            addChild(t);
        }
    }

    @ExposedGet(name = "repr")
    public String toString() {
        return "comprehension";
    }

    public String toStringTree() {
        StringBuffer sb = new StringBuffer("comprehension(");
        sb.append("target=");
        sb.append(dumpThis(target));
        sb.append(",");
        sb.append("iter=");
        sb.append(dumpThis(iter));
        sb.append(",");
        sb.append("ifs=");
        sb.append(dumpThis(ifs));
        sb.append(",");
        sb.append(")");
        return sb.toString();
    }

    public <R> R accept(VisitorIF<R> visitor) throws Exception {
        traverse(visitor);
        return null;
    }

    public void traverse(VisitorIF<?> visitor) throws Exception {
        if (target != null)
            target.accept(visitor);
        if (iter != null)
            iter.accept(visitor);
        if (ifs != null) {
            for (PythonTree t : ifs) {
                if (t != null)
                    t.accept(visitor);
            }
        }
    }

    public PyObject __dict__;

    @Override
    public PyObject fastGetDict() {
        ensureDict();
        return __dict__;
    }

    @ExposedGet(name = "__dict__")
    public PyObject getDict() {
        return fastGetDict();
    }

    private void ensureDict() {
        if (__dict__ == null) {
            __dict__ = new PyStringMap();
        }
    }


    /* Traverseproc implementation */
    @Override
    public int traverse(Visitproc visit, Object arg) {
        int retVal = super.traverse(visit, arg);
        if (retVal != 0) {
            return retVal;
        }
        if (iter != null) {
            retVal = visit.visit(iter, arg);
            if (retVal != 0) {
                return retVal;
            }
        }
        if (ifs != null) {
            for (PyObject ob: ifs) {
                if (ob != null) {
                    retVal = visit.visit(ob, arg);
                    if (retVal != 0) {
                        return retVal;
                    }
                }
            }
        }
        
        return target != null ? visit.visit(target,  arg) : 0;
    }
    @Override
    public boolean refersDirectlyTo(PyObject ob) {
        if (ob == null) {
            return false;
        } else if (ifs != null && ifs.contains(ob)) {
            return true;
        } else {
            return ob == iter || ob == target || super.refersDirectlyTo(ob);
        }
    }
}
