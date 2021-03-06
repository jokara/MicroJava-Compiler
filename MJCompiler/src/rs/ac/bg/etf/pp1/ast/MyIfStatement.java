// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class MyIfStatement extends Statement {

    private IfOperator IfOperator;
    private IfCondition IfCondition;
    private IfClose IfClose;
    private Statement Statement;

    public MyIfStatement (IfOperator IfOperator, IfCondition IfCondition, IfClose IfClose, Statement Statement) {
        this.IfOperator=IfOperator;
        if(IfOperator!=null) IfOperator.setParent(this);
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.IfClose=IfClose;
        if(IfClose!=null) IfClose.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public IfOperator getIfOperator() {
        return IfOperator;
    }

    public void setIfOperator(IfOperator IfOperator) {
        this.IfOperator=IfOperator;
    }

    public IfCondition getIfCondition() {
        return IfCondition;
    }

    public void setIfCondition(IfCondition IfCondition) {
        this.IfCondition=IfCondition;
    }

    public IfClose getIfClose() {
        return IfClose;
    }

    public void setIfClose(IfClose IfClose) {
        this.IfClose=IfClose;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfOperator!=null) IfOperator.accept(visitor);
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(IfClose!=null) IfClose.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfOperator!=null) IfOperator.traverseTopDown(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(IfClose!=null) IfClose.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfOperator!=null) IfOperator.traverseBottomUp(visitor);
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(IfClose!=null) IfClose.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MyIfStatement(\n");

        if(IfOperator!=null)
            buffer.append(IfOperator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfCondition!=null)
            buffer.append(IfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfClose!=null)
            buffer.append(IfClose.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MyIfStatement]");
        return buffer.toString();
    }
}
