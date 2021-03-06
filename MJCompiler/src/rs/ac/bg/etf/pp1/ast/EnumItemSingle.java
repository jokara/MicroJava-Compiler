// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class EnumItemSingle extends EnumParamsItem {

    private Identificator Identificator;

    public EnumItemSingle (Identificator Identificator) {
        this.Identificator=Identificator;
        if(Identificator!=null) Identificator.setParent(this);
    }

    public Identificator getIdentificator() {
        return Identificator;
    }

    public void setIdentificator(Identificator Identificator) {
        this.Identificator=Identificator;
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
        buffer.append("EnumItemSingle(\n");

        if(Identificator!=null)
            buffer.append(Identificator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumItemSingle]");
        return buffer.toString();
    }
}
