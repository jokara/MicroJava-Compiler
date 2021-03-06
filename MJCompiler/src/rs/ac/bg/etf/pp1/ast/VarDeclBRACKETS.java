// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:7


package rs.ac.bg.etf.pp1.ast;

public class VarDeclBRACKETS extends VarDecl {

    private IdentificatorSecond IdentificatorSecond;

    public VarDeclBRACKETS (IdentificatorSecond IdentificatorSecond) {
        this.IdentificatorSecond=IdentificatorSecond;
        if(IdentificatorSecond!=null) IdentificatorSecond.setParent(this);
    }

    public IdentificatorSecond getIdentificatorSecond() {
        return IdentificatorSecond;
    }

    public void setIdentificatorSecond(IdentificatorSecond IdentificatorSecond) {
        this.IdentificatorSecond=IdentificatorSecond;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentificatorSecond!=null) IdentificatorSecond.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentificatorSecond!=null) IdentificatorSecond.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentificatorSecond!=null) IdentificatorSecond.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclBRACKETS(\n");

        if(IdentificatorSecond!=null)
            buffer.append(IdentificatorSecond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclBRACKETS]");
        return buffer.toString();
    }
}
