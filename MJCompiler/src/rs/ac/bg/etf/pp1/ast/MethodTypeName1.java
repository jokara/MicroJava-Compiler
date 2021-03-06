// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:7


package rs.ac.bg.etf.pp1.ast;

public class MethodTypeName1 extends MethodTypeName {

    private TypeForMeth TypeForMeth;
    private String methodName;

    public MethodTypeName1 (TypeForMeth TypeForMeth, String methodName) {
        this.TypeForMeth=TypeForMeth;
        if(TypeForMeth!=null) TypeForMeth.setParent(this);
        this.methodName=methodName;
    }

    public TypeForMeth getTypeForMeth() {
        return TypeForMeth;
    }

    public void setTypeForMeth(TypeForMeth TypeForMeth) {
        this.TypeForMeth=TypeForMeth;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName=methodName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeForMeth!=null) TypeForMeth.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeForMeth!=null) TypeForMeth.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeForMeth!=null) TypeForMeth.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodTypeName1(\n");

        if(TypeForMeth!=null)
            buffer.append(TypeForMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methodName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodTypeName1]");
        return buffer.toString();
    }
}
