// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class FuncCall extends Factor {

    private FactorDesignator FactorDesignator;
    private ActualPars ActualPars;

    public FuncCall (FactorDesignator FactorDesignator, ActualPars ActualPars) {
        this.FactorDesignator=FactorDesignator;
        if(FactorDesignator!=null) FactorDesignator.setParent(this);
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
    }

    public FactorDesignator getFactorDesignator() {
        return FactorDesignator;
    }

    public void setFactorDesignator(FactorDesignator FactorDesignator) {
        this.FactorDesignator=FactorDesignator;
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorDesignator!=null) FactorDesignator.accept(visitor);
        if(ActualPars!=null) ActualPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorDesignator!=null) FactorDesignator.traverseTopDown(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorDesignator!=null) FactorDesignator.traverseBottomUp(visitor);
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FuncCall(\n");

        if(FactorDesignator!=null)
            buffer.append(FactorDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FuncCall]");
        return buffer.toString();
    }
}
