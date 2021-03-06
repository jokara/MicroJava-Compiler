// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class MyFormalParamDecl extends FormalParamDecl {

    private Type Type;
    private String formalParams;

    public MyFormalParamDecl (Type Type, String formalParams) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.formalParams=formalParams;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFormalParams() {
        return formalParams;
    }

    public void setFormalParams(String formalParams) {
        this.formalParams=formalParams;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MyFormalParamDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formalParams);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MyFormalParamDecl]");
        return buffer.toString();
    }
}
