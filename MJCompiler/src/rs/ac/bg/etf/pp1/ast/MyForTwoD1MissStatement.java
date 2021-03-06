// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public class MyForTwoD1MissStatement extends Statement {

    private MyFor MyFor;
    private NoDesignatorStatement NoDesignatorStatement;
    private ForCondition ForCondition;
    private ForThirdPart ForThirdPart;
    private Statement Statement;

    public MyForTwoD1MissStatement (MyFor MyFor, NoDesignatorStatement NoDesignatorStatement, ForCondition ForCondition, ForThirdPart ForThirdPart, Statement Statement) {
        this.MyFor=MyFor;
        if(MyFor!=null) MyFor.setParent(this);
        this.NoDesignatorStatement=NoDesignatorStatement;
        if(NoDesignatorStatement!=null) NoDesignatorStatement.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.ForThirdPart=ForThirdPart;
        if(ForThirdPart!=null) ForThirdPart.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public MyFor getMyFor() {
        return MyFor;
    }

    public void setMyFor(MyFor MyFor) {
        this.MyFor=MyFor;
    }

    public NoDesignatorStatement getNoDesignatorStatement() {
        return NoDesignatorStatement;
    }

    public void setNoDesignatorStatement(NoDesignatorStatement NoDesignatorStatement) {
        this.NoDesignatorStatement=NoDesignatorStatement;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public ForThirdPart getForThirdPart() {
        return ForThirdPart;
    }

    public void setForThirdPart(ForThirdPart ForThirdPart) {
        this.ForThirdPart=ForThirdPart;
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
        if(NoDesignatorStatement!=null) NoDesignatorStatement.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(ForThirdPart!=null) ForThirdPart.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MyFor!=null) MyFor.traverseTopDown(visitor);
        if(NoDesignatorStatement!=null) NoDesignatorStatement.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(ForThirdPart!=null) ForThirdPart.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MyFor!=null) MyFor.traverseBottomUp(visitor);
        if(NoDesignatorStatement!=null) NoDesignatorStatement.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(ForThirdPart!=null) ForThirdPart.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MyForTwoD1MissStatement(\n");

        if(MyFor!=null)
            buffer.append(MyFor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NoDesignatorStatement!=null)
            buffer.append(NoDesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForThirdPart!=null)
            buffer.append(ForThirdPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MyForTwoD1MissStatement]");
        return buffer.toString();
    }
}
