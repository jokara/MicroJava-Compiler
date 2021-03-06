// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class CondTermMultiple extends Condition {

    private CondTerm CondTerm;
    private OrOperator OrOperator;
    private CondTerm CondTerm1;

    public CondTermMultiple (CondTerm CondTerm, OrOperator OrOperator, CondTerm CondTerm1) {
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.OrOperator=OrOperator;
        if(OrOperator!=null) OrOperator.setParent(this);
        this.CondTerm1=CondTerm1;
        if(CondTerm1!=null) CondTerm1.setParent(this);
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public OrOperator getOrOperator() {
        return OrOperator;
    }

    public void setOrOperator(OrOperator OrOperator) {
        this.OrOperator=OrOperator;
    }

    public CondTerm getCondTerm1() {
        return CondTerm1;
    }

    public void setCondTerm1(CondTerm CondTerm1) {
        this.CondTerm1=CondTerm1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(OrOperator!=null) OrOperator.accept(visitor);
        if(CondTerm1!=null) CondTerm1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(OrOperator!=null) OrOperator.traverseTopDown(visitor);
        if(CondTerm1!=null) CondTerm1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(OrOperator!=null) OrOperator.traverseBottomUp(visitor);
        if(CondTerm1!=null) CondTerm1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondTermMultiple(\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrOperator!=null)
            buffer.append(OrOperator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm1!=null)
            buffer.append(CondTerm1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondTermMultiple]");
        return buffer.toString();
    }
}
