// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class MyForTwoD2MissStatement extends Statement {

    private MyFor MyFor;
    private ForDesignatorStatement ForDesignatorStatement;
    private ForCondition ForCondition;
    private NoForThirdPart NoForThirdPart;
    private Statement Statement;

    public MyForTwoD2MissStatement (MyFor MyFor, ForDesignatorStatement ForDesignatorStatement, ForCondition ForCondition, NoForThirdPart NoForThirdPart, Statement Statement) {
        this.MyFor=MyFor;
        if(MyFor!=null) MyFor.setParent(this);
        this.ForDesignatorStatement=ForDesignatorStatement;
        if(ForDesignatorStatement!=null) ForDesignatorStatement.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.NoForThirdPart=NoForThirdPart;
        if(NoForThirdPart!=null) NoForThirdPart.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public MyFor getMyFor() {
        return MyFor;
    }

    public void setMyFor(MyFor MyFor) {
        this.MyFor=MyFor;
    }

    public ForDesignatorStatement getForDesignatorStatement() {
        return ForDesignatorStatement;
    }

    public void setForDesignatorStatement(ForDesignatorStatement ForDesignatorStatement) {
        this.ForDesignatorStatement=ForDesignatorStatement;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public NoForThirdPart getNoForThirdPart() {
        return NoForThirdPart;
    }

    public void setNoForThirdPart(NoForThirdPart NoForThirdPart) {
        this.NoForThirdPart=NoForThirdPart;
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
        if(MyFor!=null) MyFor.accept(visitor);
        if(ForDesignatorStatement!=null) ForDesignatorStatement.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(NoForThirdPart!=null) NoForThirdPart.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MyFor!=null) MyFor.traverseTopDown(visitor);
        if(ForDesignatorStatement!=null) ForDesignatorStatement.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(NoForThirdPart!=null) NoForThirdPart.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MyFor!=null) MyFor.traverseBottomUp(visitor);
        if(ForDesignatorStatement!=null) ForDesignatorStatement.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(NoForThirdPart!=null) NoForThirdPart.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MyForTwoD2MissStatement(\n");

        if(MyFor!=null)
            buffer.append(MyFor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDesignatorStatement!=null)
            buffer.append(ForDesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NoForThirdPart!=null)
            buffer.append(NoForThirdPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MyForTwoD2MissStatement]");
        return buffer.toString();
    }
}
