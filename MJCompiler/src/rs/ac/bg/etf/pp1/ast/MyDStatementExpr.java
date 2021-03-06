// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class MyDStatementExpr extends DesignatorStatement {

    private DesignatorExprStatement DesignatorExprStatement;

    public MyDStatementExpr (DesignatorExprStatement DesignatorExprStatement) {
        this.DesignatorExprStatement=DesignatorExprStatement;
        if(DesignatorExprStatement!=null) DesignatorExprStatement.setParent(this);
    }

    public DesignatorExprStatement getDesignatorExprStatement() {
        return DesignatorExprStatement;
    }

    public void setDesignatorExprStatement(DesignatorExprStatement DesignatorExprStatement) {
        this.DesignatorExprStatement=DesignatorExprStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorExprStatement!=null) DesignatorExprStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorExprStatement!=null) DesignatorExprStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorExprStatement!=null) DesignatorExprStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MyDStatementExpr(\n");

        if(DesignatorExprStatement!=null)
            buffer.append(DesignatorExprStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MyDStatementExpr]");
        return buffer.toString();
    }
}
