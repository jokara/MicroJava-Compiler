// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class EnumItemEqual extends EnumParamsItem {

    private Identificator Identificator;
    private Integer N2;

    public EnumItemEqual (Identificator Identificator, Integer N2) {
        this.Identificator=Identificator;
        if(Identificator!=null) Identificator.setParent(this);
        this.N2=N2;
    }

    public Identificator getIdentificator() {
        return Identificator;
    }

    public void setIdentificator(Identificator Identificator) {
        this.Identificator=Identificator;
    }

    public Integer getN2() {
        return N2;
    }

    public void setN2(Integer N2) {
        this.N2=N2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Identificator!=null) Identificator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Identificator!=null) Identificator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Identificator!=null) Identificator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumItemEqual(\n");

        if(Identificator!=null)
            buffer.append(Identificator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+N2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumItemEqual]");
        return buffer.toString();
    }
}
