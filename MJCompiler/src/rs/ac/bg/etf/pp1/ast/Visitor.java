// generated with ast extension for cup
// version 0.8
// 27/0/2020 20:37:8


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Unmatched Unmatched);
    public void visit(NoForCondition NoForCondition);
    public void visit(EnumDecl EnumDecl);
    public void visit(MyFor MyFor);
    public void visit(Matched Matched);
    public void visit(Relop Relop);
    public void visit(TermList TermList);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(StatementList StatementList);
    public void visit(ForCondition ForCondition);
    public void visit(OrOperator OrOperator);
    public void visit(NoDesignatorStatement NoDesignatorStatement);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(IfClose IfClose);
    public void visit(EnumParamsItem EnumParamsItem);
    public void visit(NoForThirdPart NoForThirdPart);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(IfCondition IfCondition);
    public void visit(ActualParamList ActualParamList);
    public void visit(ElseOperator ElseOperator);
    public void visit(IdentificatorSecond IdentificatorSecond);
    public void visit(TypeForMeth TypeForMeth);
    public void visit(VarDeclList VarDeclList);
    public void visit(FormalParamList FormalParamList);
    public void visit(Expr Expr);
    public void visit(ForDesignatorStatement ForDesignatorStatement);
    public void visit(ForThirdPart ForThirdPart);
    public void visit(DesignatorExprStatement DesignatorExprStatement);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ActualPars ActualPars);
    public void visit(Const Const);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Identificator Identificator);
    public void visit(FormPars FormPars);
    public void visit(IfOperator IfOperator);
    public void visit(IdentificatorError IdentificatorError);
    public void visit(NonTerminalIdent NonTerminalIdent);
    public void visit(ConstN ConstN);
    public void visit(ConstDeclListBool ConstDeclListBool);
    public void visit(ConstDeclListChar ConstDeclListChar);
    public void visit(ConstDeclListNumber ConstDeclListNumber);
    public void visit(ConstDeclListBoolComma ConstDeclListBoolComma);
    public void visit(ConstDeclListCharComma ConstDeclListCharComma);
    public void visit(ConstDeclListNumberComma ConstDeclListNumberComma);
    public void visit(BoolConstComma BoolConstComma);
    public void visit(CharConstComma CharConstComma);
    public void visit(NumConstComma NumConstComma);
    public void visit(BoolConst BoolConst);
    public void visit(CharConst CharConst);
    public void visit(NumConst NumConst);
    public void visit(OrOper OrOper);
    public void visit(CondTermSingle CondTermSingle);
    public void visit(CondTermMultiple CondTermMultiple);
    public void visit(CondFactSin CondFactSin);
    public void visit(CondFactMult CondFactMult);
    public void visit(LowerEqual LowerEqual);
    public void visit(Lower Lower);
    public void visit(GreaterEqual GreaterEqual);
    public void visit(Greater Greater);
    public void visit(IsNotEqual IsNotEqual);
    public void visit(IsEqual IsEqual);
    public void visit(CondFactSingle CondFactSingle);
    public void visit(CondFactRelop CondFactRelop);
    public void visit(EnumItemSingle EnumItemSingle);
    public void visit(EnumItemSingleComma EnumItemSingleComma);
    public void visit(EnumItemEqual EnumItemEqual);
    public void visit(EnumItemEqualComma EnumItemEqualComma);
    public void visit(EnumOperation EnumOperation);
    public void visit(Percentop Percentop);
    public void visit(Divop Divop);
    public void visit(Mulop Mulop);
    public void visit(Subop Subop);
    public void visit(Addop Addop);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(DesignatorExpr DesignatorExpr);
    public void visit(ActualParam ActualParam);
    public void visit(ActualParams ActualParams);
    public void visit(Actuals Actuals);
    public void visit(FuncCallDes FuncCallDes);
    public void visit(Var Var);
    public void visit(FuncCallWithoutPars FuncCallWithoutPars);
    public void visit(FuncCall FuncCall);
    public void visit(MyFactorNewExpr MyFactorNewExpr);
    public void visit(MyFactorNew MyFactorNew);
    public void visit(MyFactorExpr MyFactorExpr);
    public void visit(MyBool MyBool);
    public void visit(MyChar MyChar);
    public void visit(MyConst MyConst);
    public void visit(TermSingleFactor TermSingleFactor);
    public void visit(TermMultipleFactors TermMultipleFactors);
    public void visit(TermListSingleTerm TermListSingleTerm);
    public void visit(TermListMultipleTerms TermListMultipleTerms);
    public void visit(TermListSingleTermMinus TermListSingleTermMinus);
    public void visit(Minus Minus);
    public void visit(ExprNoMinus ExprNoMinus);
    public void visit(IdentificatorExprError IdentificatorExprError);
    public void visit(MyDesignatorExprStatement MyDesignatorExprStatement);
    public void visit(MyDStatementDec MyDStatementDec);
    public void visit(MyDStatementInc MyDStatementInc);
    public void visit(MyDStatementExpr MyDStatementExpr);
    public void visit(MyDStatementWithoutPars MyDStatementWithoutPars);
    public void visit(MyDStatementWithPars MyDStatementWithPars);
    public void visit(ElseOper ElseOper);
    public void visit(IfEndCondition IfEndCondition);
    public void visit(IfOper IfOper);
    public void visit(MyIfCondition MyIfCondition);
    public void visit(MyForWord MyForWord);
    public void visit(MyNoForThirdPart MyNoForThirdPart);
    public void visit(MyForThirdPart MyForThirdPart);
    public void visit(MyNoForCondition MyNoForCondition);
    public void visit(IdentificatorForError IdentificatorForError);
    public void visit(MyForCondtion MyForCondtion);
    public void visit(MyNoDesignatorStatement MyNoDesignatorStatement);
    public void visit(MyForDesignatorStatement MyForDesignatorStatement);
    public void visit(MyForMissStatement MyForMissStatement);
    public void visit(MyForOneCD2MissStatement MyForOneCD2MissStatement);
    public void visit(MyForOneD1D2MissStatement MyForOneD1D2MissStatement);
    public void visit(MyForOneD1CMissStatement MyForOneD1CMissStatement);
    public void visit(MyForTwoD2MissStatement MyForTwoD2MissStatement);
    public void visit(MyForTwoCMissStatement MyForTwoCMissStatement);
    public void visit(MyForTwoD1MissStatement MyForTwoD1MissStatement);
    public void visit(MyForThreeStatement MyForThreeStatement);
    public void visit(MyIfStatement MyIfStatement);
    public void visit(MyIfElseStatement MyIfElseStatement);
    public void visit(MySDStatement MySDStatement);
    public void visit(MyReadStatement MyReadStatement);
    public void visit(MyPrintStatement1 MyPrintStatement1);
    public void visit(MyPrintStatement2 MyPrintStatement2);
    public void visit(StatementBreak StatementBreak);
    public void visit(StatementContinue StatementContinue);
    public void visit(StatementReturnValue StatementReturnValue);
    public void visit(StatementReturn StatementReturn);
    public void visit(StatementWithStatement StatementWithStatement);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(IdentificatorFormalParamError IdentificatorFormalParamError);
    public void visit(MyFormalParamDecl MyFormalParamDecl);
    public void visit(FormalParamArray FormalParamArray);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(MethodTypeName1 MethodTypeName1);
    public void visit(MethodTypeVoid MethodTypeVoid);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(TypeMethod TypeMethod);
    public void visit(Type Type);
    public void visit(IdentificatorSecError IdentificatorSecError);
    public void visit(IdentificatorSec IdentificatorSec);
    public void visit(VarDeclBRACKETSCOMMA VarDeclBRACKETSCOMMA);
    public void visit(VarDeclBRACKETS VarDeclBRACKETS);
    public void visit(VarDeclCOMMA VarDeclCOMMA);
    public void visit(VarDeclar VarDeclar);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclConst VarDeclConst);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(VarDeclListEnum VarDeclListEnum);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
