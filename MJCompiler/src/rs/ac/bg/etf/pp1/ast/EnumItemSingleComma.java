// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class EnumItemSingleComma extends EnumParamsItem {

    private Identificator Identificator;
    private EnumParamsItem EnumParamsItem;

    public EnumItemSingleComma (Identificator Identificator, EnumParamsItem EnumParamsItem) {
        this.Identificator=Identificator;
        if(Identificator!=null) Identificator.setParent(this);
        this.EnumParamsItem=EnumParamsItem;
        if(EnumParamsItem!=null) EnumParamsItem.setParent(this);
    }

    public Identificator getIdentificator() {
        return Identificator;
    }

    public void setIdentificator(Identificator Identificator) {
        this.Identificator=Identificator;
    }

    public EnumParamsItem getEnumParamsItem() {
        return EnumParamsItem;
    }

    public void setEnumParamsItem(EnumParamsItem EnumParamsItem) {
        this.EnumParamsItem=EnumParamsItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Identificator!=null) Identificator.accept(visitor);
        if(EnumParamsItem!=null) EnumParamsItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Identificator!=null) Identificator.traverseTopDown(visitor);
        if(EnumParamsItem!=null) EnumParamsItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Identificator!=null) Identificator.traverseBottomUp(visitor);
        if(EnumParamsItem!=null) EnumParamsItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumItemSingleComma(\n");

        if(Identificator!=null)
            buffer.append(Identificator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumParamsItem!=null)
            buffer.append(EnumParamsItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumItemSingleComma]");
        return buffer.toString();
    }
}
