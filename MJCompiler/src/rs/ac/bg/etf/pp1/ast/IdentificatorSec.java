// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:7


package rs.ac.bg.etf.pp1.ast;

public class IdentificatorSec extends IdentificatorSecond {

    private String identName;

    public IdentificatorSec (String identName) {
        this.identName=identName;
    }

    public String getIdentName() {
        return identName;
    }

    public void setIdentName(String identName) {
        this.identName=identName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdentificatorSec(\n");

        buffer.append(" "+tab+identName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdentificatorSec]");
        return buffer.toString();
    }
}
