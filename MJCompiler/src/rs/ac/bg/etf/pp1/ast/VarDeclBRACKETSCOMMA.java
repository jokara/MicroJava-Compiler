// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:7


package rs.ac.bg.etf.pp1.ast;

public class VarDeclBRACKETSCOMMA extends VarDecl {

    private IdentificatorSecond IdentificatorSecond;
    private VarDecl VarDecl;

    public VarDeclBRACKETSCOMMA (IdentificatorSecond IdentificatorSecond, VarDecl VarDecl) {
        this.IdentificatorSecond=IdentificatorSecond;
        if(IdentificatorSecond!=null) IdentificatorSecond.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public IdentificatorSecond getIdentificatorSecond() {
        return IdentificatorSecond;
    }

    public void setIdentificatorSecond(IdentificatorSecond IdentificatorSecond) {
        this.IdentificatorSecond=IdentificatorSecond;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentificatorSecond!=null) IdentificatorSecond.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentificatorSecond!=null) IdentificatorSecond.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentificatorSecond!=null) IdentificatorSecond.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclBRACKETSCOMMA(\n");

        if(IdentificatorSecond!=null)
            buffer.append(IdentificatorSecond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclBRACKETSCOMMA]");
        return buffer.toString();
    }
}
