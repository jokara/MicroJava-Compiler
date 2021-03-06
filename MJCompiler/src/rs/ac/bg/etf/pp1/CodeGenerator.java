package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.ActualParam;
import rs.ac.bg.etf.pp1.ast.CondFactRelop;
import rs.ac.bg.etf.pp1.ast.CondFactSingle;
import rs.ac.bg.etf.pp1.ast.DesignatorIdent;
import rs.ac.bg.etf.pp1.ast.Divop;
import rs.ac.bg.etf.pp1.ast.ElseOper;
import rs.ac.bg.etf.pp1.ast.FuncCall;
import rs.ac.bg.etf.pp1.ast.FuncCallWithoutPars;
import rs.ac.bg.etf.pp1.ast.Greater;
import rs.ac.bg.etf.pp1.ast.GreaterEqual;
import rs.ac.bg.etf.pp1.ast.IfClose;
import rs.ac.bg.etf.pp1.ast.IfEndCondition;
import rs.ac.bg.etf.pp1.ast.IfOper;
import rs.ac.bg.etf.pp1.ast.IsEqual;
import rs.ac.bg.etf.pp1.ast.IsNotEqual;
import rs.ac.bg.etf.pp1.ast.Lower;
import rs.ac.bg.etf.pp1.ast.LowerEqual;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeName1;
import rs.ac.bg.etf.pp1.ast.MethodTypeVoid;
import rs.ac.bg.etf.pp1.ast.MyBool;
import rs.ac.bg.etf.pp1.ast.MyChar;
import rs.ac.bg.etf.pp1.ast.MyConst;
import rs.ac.bg.etf.pp1.ast.MyDStatementDec;
import rs.ac.bg.etf.pp1.ast.MyDStatementInc;
import rs.ac.bg.etf.pp1.ast.MyDStatementWithPars;
import rs.ac.bg.etf.pp1.ast.MyDStatementWithoutPars;
import rs.ac.bg.etf.pp1.ast.MyDesignatorExprStatement;
import rs.ac.bg.etf.pp1.ast.MyFactorNewExpr;
import rs.ac.bg.etf.pp1.ast.MyForCondtion;
import rs.ac.bg.etf.pp1.ast.MyForDesignatorStatement;
import rs.ac.bg.etf.pp1.ast.MyForMissStatement;
import rs.ac.bg.etf.pp1.ast.MyForOneCD2MissStatement;
import rs.ac.bg.etf.pp1.ast.MyForOneD1CMissStatement;
import rs.ac.bg.etf.pp1.ast.MyForOneD1D2MissStatement;
import rs.ac.bg.etf.pp1.ast.MyForThirdPart;
import rs.ac.bg.etf.pp1.ast.MyForThreeStatement;
import rs.ac.bg.etf.pp1.ast.MyForTwoCMissStatement;
import rs.ac.bg.etf.pp1.ast.MyForTwoD1MissStatement;
import rs.ac.bg.etf.pp1.ast.MyForTwoD2MissStatement;
import rs.ac.bg.etf.pp1.ast.MyForWord;
import rs.ac.bg.etf.pp1.ast.MyIfElseStatement;
import rs.ac.bg.etf.pp1.ast.MyIfStatement;
import rs.ac.bg.etf.pp1.ast.MyNoDesignatorStatement;
import rs.ac.bg.etf.pp1.ast.MyNoForCondition;
import rs.ac.bg.etf.pp1.ast.MyNoForThirdPart;
import rs.ac.bg.etf.pp1.ast.MyPrintStatement1;
import rs.ac.bg.etf.pp1.ast.MyPrintStatement2;
import rs.ac.bg.etf.pp1.ast.MyReadStatement;
import rs.ac.bg.etf.pp1.ast.OrOper;
import rs.ac.bg.etf.pp1.ast.Percentop;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Relop;
import rs.ac.bg.etf.pp1.ast.StatementBreak;
import rs.ac.bg.etf.pp1.ast.StatementContinue;
import rs.ac.bg.etf.pp1.ast.StatementReturn;
import rs.ac.bg.etf.pp1.ast.StatementReturnValue;
import rs.ac.bg.etf.pp1.ast.Subop;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermListMultipleTerms;
import rs.ac.bg.etf.pp1.ast.TermListSingleTermMinus;
import rs.ac.bg.etf.pp1.ast.TermMultipleFactors;
import rs.ac.bg.etf.pp1.ast.Var;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int varCount;

	private int paramCnt=0;

	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}

	Obj currentMethod = null;

	private Stack<ArrayList<Integer>> ifConditionTrue;
	private Stack<ArrayList<Integer>> ifConditionFalse;
	private ArrayList<Integer> thenEnd;

	private ArrayList<Integer> forEnd;
	private ArrayList<Integer> forStart;;
	private Stack<ArrayList<Integer>> forContinueList;
	private Stack<ArrayList<Integer>> forBreakList;
	private ArrayList<Integer> forCondition;

	public CodeGenerator() {
		ifConditionTrue = new Stack<ArrayList<Integer>>();
		ifConditionFalse = new Stack<ArrayList<Integer>>();
		thenEnd = new ArrayList<>();

		forEnd = new ArrayList<>();
		forStart = new ArrayList<>();
		forContinueList = new Stack<ArrayList<Integer>>();
		forBreakList = new Stack<ArrayList<Integer>>();
		forCondition = new ArrayList<>();
	}

	/*
	 * 
	 * @Override public void visit(VarDecl VarDecl) { varCount++; }
	 * 
	 * @Override public void visit(FormalParamDecl FormalParam) { paramCnt++; }
	 * 
	 * @Override public void visit(MethodDecl MethodDecl) { Code.put(Code.exit);
	 * Code.put(Code.return_); }
	 * 
	 */

	@Override
	public void visit(MyPrintStatement1 MyPrintStatement1) {
		// TODO Auto-generated method stub
		if (MyPrintStatement1.getExpr().struct.getKind() == Struct.Char) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else {
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}

	@Override
	public void visit(MyConst MyConst) {
		// TODO Auto-generated method stub
		Obj constant = Tab.insert(Obj.Con, "$", MyConst.struct);
		constant.setLevel(0);
		constant.setAdr(MyConst.getN1());
		Code.load(constant);
	}

	@Override
	public void visit(MethodTypeName1 MethodTypeName1) {
		// TODO Auto-generated method stub
		if ("main".equalsIgnoreCase(MethodTypeName1.getMethodName())) {
			mainPc = Code.pc;
		}
		MethodTypeName1.obj.setAdr(Code.pc);
		
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(MethodTypeName1.obj.getLevel());
		Code.put(MethodTypeName1.obj.getLocalSymbols().size());

		currentMethod = MethodTypeName1.obj;
	}

	@Override
	public void visit(MethodTypeVoid MethodTypeVoid) {
		// TODO Auto-generated method stub
		if ("main".equalsIgnoreCase(MethodTypeVoid.getMethodName())) {
			mainPc = Code.pc;
		}
		MethodTypeVoid.obj.setAdr(Code.pc);

		// Generate the entry.
		Code.put(Code.enter);
		Code.put(MethodTypeVoid.obj.getLevel());
		Code.put(MethodTypeVoid.obj.getLocalSymbols().size());
		
///////////////////////////////////////////mozda ne treba
		currentMethod = MethodTypeVoid.obj;
	}

	@Override
	public void visit(MethodDecl MethodDecl) {
		// TODO Auto-generated method stub
		if (currentMethod.getType() != Tab.noType) {
			Code.put(Code.trap);
			Code.put(1);
		} else {
			Code.put(Code.exit);
			Code.put(Code.return_);
		}

	}

	@Override
	public void visit(MyDesignatorExprStatement MyDStatementExpr) {
		// TODO Auto-generated method stub
		Code.store(MyDStatementExpr.getDesignator().obj);
	}

	@Override
	public void visit(DesignatorIdent DesignatorIdent) {
		// TODO Auto-generated method stub
		SyntaxNode parent = DesignatorIdent.getParent();
		if ((DesignatorIdent.obj.getType().getKind() == Struct.Array)) {
			Code.load(DesignatorIdent.obj);
		}
	}

	@Override
	public void visit(MyDStatementWithPars MyDStatementWithPars) {
		// TODO Auto-generated method stub
		Obj functionObj = MyDStatementWithPars.getFactorDesignator().obj;
		int functionOffset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(functionOffset);

		if (functionObj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}

	}

	@Override
	public void visit(FuncCall FuncCall) {
		// TODO Auto-generated method stub
		Obj functionObj = FuncCall.getFactorDesignator().obj;
		int functionOffset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(functionOffset);
		

	}

	@Override
	public void visit(StatementReturnValue StatementReturnValue) {
		// TODO Auto-generated method stub
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(StatementReturn StatementReturn) {
		// TODO Auto-generated method stub
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(TermListMultipleTerms TermListMultipleTerms) {
		// TODO Auto-generated method stub
		if (TermListMultipleTerms.getAddop() instanceof Subop) {
			Code.put(Code.sub);
		} else {
			Code.put(Code.add);
		}
	}

	@Override
	public void visit(ProgName ProgName) {
		// TODO Auto-generated method stub

		Obj methodObj = Tab.noObj;
		int numArgs = 1;// svaka od ovih fja ima samo jedan argument
		int numArgsAndLocals = 0;// i nema lokalne

		methodObj = Tab.lenObj;
		methodObj.setAdr(Code.pc);
		numArgsAndLocals = methodObj.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(numArgs);
		Code.put(numArgsAndLocals);
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);

		methodObj = Tab.ordObj;
		methodObj.setAdr(Code.pc);
		numArgsAndLocals = methodObj.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(numArgs);
		Code.put(numArgsAndLocals);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);

		methodObj = Tab.chrObj;
		methodObj.setAdr(Code.pc);
		numArgsAndLocals = methodObj.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(numArgs);
		Code.put(numArgsAndLocals);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);

		Obj progObj = ProgName.obj;

		Collection<Obj> programLocalSymbols = progObj.getLocalSymbols();
		int count = 0;

		for (Obj symObj : programLocalSymbols) {
			int symKind = symObj.getKind();
			if (symKind == Obj.Var) {
				count = count + 1;
			}
		}
		int dataSize = count;
		Code.dataSize = dataSize;
	}

	@Override
	public void visit(MyPrintStatement2 MyPrintStatement2) {
		// TODO Auto-generated method stub
		int num = MyPrintStatement2.getN2();
		Code.loadConst(num);
		if (MyPrintStatement2.getExpr().struct.getKind() == Struct.Char) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}

	@Override
	public void visit(MyReadStatement MyReadStatement) {
		// TODO Auto-generated method stub
		Obj objRead = MyReadStatement.getDesignator().obj;
		if (objRead.getType().getKind() == Struct.Int) {
			Code.put(Code.read);
		} else {
			Code.put(Code.bread);
		}
		Code.store(objRead);
	}

	@Override
	public void visit(MyBool MyBool) {
		// TODO Auto-generated method stub
		Obj constant = Tab.insert(Obj.Con, "", new Struct(Struct.Bool));
		constant.setAdr(MyBool.getB1() ? 1 : 0);
		Code.load(constant);
	}

	@Override
	public void visit(MyChar MyChar) {
		// TODO Auto-generated method stub
		Obj constant = Tab.insert(Obj.Con, "", new Struct(Struct.Char));
		constant.setAdr(MyChar.getC1());
		Code.load(constant);
	}

	@Override
	public void visit(MyFactorNewExpr MyFactorNewExpr) {
		// TODO Auto-generated method stub
		Code.put(Code.newarray);
		if (MyFactorNewExpr.getExpr().struct.getKind() == Struct.Char) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}

	@Override
	public void visit(Var Var) {
		// TODO Auto-generated method stub
		Code.load(Var.getDesignator().obj);
	}

	@Override
	public void visit(MyDStatementDec MyDStatementDec) {
		// TODO Auto-generated method stub
		Decrement(MyDStatementDec);
	}

	private void Decrement(MyDStatementDec MyDStatementDec) {
		Obj incObject = MyDStatementDec.getDesignator().obj;

		if (incObject.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}

		Code.load(incObject);
		Code.loadConst(-1);
		Code.put(Code.add);
		Code.store(incObject);
	}

	@Override
	public void visit(MyDStatementInc MyDStatementInc) {
		// TODO Auto-generated method stub
		Increment(MyDStatementInc);
	}

	private void Increment(MyDStatementInc MyDStatementInc) {
		Obj incObject = MyDStatementInc.getDesignator().obj;

		if (incObject.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}

		Code.load(incObject);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(incObject);
	}

	@Override
	public void visit(TermMultipleFactors TermMultipleFactors) {
		// TODO Auto-generated method stub
		if (TermMultipleFactors.getMulop() instanceof Divop) {
			Code.put(Code.div);
			return;
		}
		if (TermMultipleFactors.getMulop() instanceof Percentop) {
			Code.put(Code.rem);
			return;
		}

		Code.put(Code.mul);

	}

	@Override
	public void visit(TermListSingleTermMinus TermListSingleTermMinus) {
		// TODO Auto-generated method stub
		Code.put(Code.neg);
	}

	/***************************************************
	 * 25.01.2020
	 *******************************************************************************/

	@Override
	public void visit(CondFactRelop CondFactRelop) {
		// TODO Auto-generated method stub
		Relop relop = CondFactRelop.getRelop();

		if (relop instanceof IsEqual) {
			Code.putFalseJump(Code.eq, 0);
			ArrayList<Integer> list = ifConditionFalse.peek();
			list.add(Code.pc - 2);
			ifConditionFalse.pop();
			ifConditionFalse.push(list);
			return;
		}

		if (relop instanceof IsNotEqual) {
			Code.putFalseJump(Code.ne, 0);
			ArrayList<Integer> list = ifConditionFalse.peek();
			list.add(Code.pc - 2);
			ifConditionFalse.pop();
			ifConditionFalse.push(list);
			return;
		}

		if (relop instanceof Greater) {
			Code.putFalseJump(Code.gt, 0);
			ArrayList<Integer> list = ifConditionFalse.peek();
			list.add(Code.pc - 2);
			ifConditionFalse.pop();
			ifConditionFalse.push(list);
			return;
		}

		if (relop instanceof GreaterEqual) {
			Code.putFalseJump(Code.ge, 0);
			ArrayList<Integer> list = ifConditionFalse.peek();
			list.add(Code.pc - 2);
			ifConditionFalse.pop();
			ifConditionFalse.push(list);
			return;
		}

		if (relop instanceof Lower) {
			Code.putFalseJump(Code.lt, 0);
			ArrayList<Integer> list = ifConditionFalse.peek();
			list.add(Code.pc - 2);
			ifConditionFalse.pop();
			ifConditionFalse.push(list);
			return;
		}

		if (relop instanceof LowerEqual) {
			Code.putFalseJump(Code.le, 0);
			ArrayList<Integer> list = ifConditionFalse.peek();
			list.add(Code.pc - 2);
			ifConditionFalse.pop();
			ifConditionFalse.push(list);
			return;
		}
	}

	@Override
	public void visit(CondFactSingle CondFactSingle) {
		// TODO Auto-generated method stub
		CondFactorSingleHelper();
		ArrayList<Integer> list = ifConditionFalse.peek();
		list.add(Code.pc - 2);
		ifConditionFalse.pop();
		ifConditionFalse.push(list);
	}

	private void CondFactorSingleHelper() {
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
	}

	@Override
	public void visit(OrOper OrOper) {
		// TODO Auto-generated method stub
		Code.putJump(0);
		ArrayList<Integer> listTrue = ifConditionTrue.peek();
		listTrue.add(Code.pc - 2);
		ifConditionTrue.pop();
		ifConditionTrue.push(listTrue);

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();
		ifConditionFalse.push(new ArrayList<Integer>());
	}

	@Override
	public void visit(IfEndCondition IfClose) {
		// TODO Auto-generated method stub
		ArrayList<Integer> listTrue = ifConditionTrue.peek();

		for (Integer i : listTrue) {
			Code.fixup(i);
		}

		return;
	}

	@Override
	public void visit(ElseOper ElseOper) {
		// TODO Auto-generated method stub
		Code.putJump(0);
		thenEnd.add(Code.pc - 2);

		ArrayList<Integer> listaFalse = ifConditionFalse.peek();

		for (Integer i : listaFalse) {
			Code.fixup(i);
		}

		return;
	}

	@Override
	public void visit(IfOper IfOper) {
		// TODO Auto-generated method stub
		ifConditionTrue.push(new ArrayList<Integer>());
		ifConditionFalse.push(new ArrayList<Integer>());
	}

	@Override
	public void visit(MyIfStatement MyIfStatement) {
		// TODO Auto-generated method stub
		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer i : listFalse) {
			Code.fixup(i);
		}

		ifConditionFalse.pop();
		ifConditionTrue.pop();
	}

	@Override
	public void visit(MyIfElseStatement MyIfElseStatement) {
		// TODO Auto-generated method stub
		int position = thenEnd.size() - 1;
		Code.fixup(thenEnd.get(position));
		thenEnd.remove(position);

		ifConditionFalse.pop();
		ifConditionTrue.pop();
	}
	/*
	 * 
	 * ********************************FOR STATEMENT*************************************************************************
	 * 
	 * */
	@Override
	public void visit(MyForMissStatement MyForMissStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);
	}

	@Override
	public void visit(MyForOneCD2MissStatement MyForOneCD2MissStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);
	}

	@Override
	public void visit(MyForOneD1D2MissStatement MyForOneD1D2MissStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);
	}

	@Override
	public void visit(MyForOneD1CMissStatement MyForOneD1CMissStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);
	}

	@Override
	public void visit(MyForTwoD2MissStatement MyForTwoD2MissStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);
	}

	@Override
	public void visit(MyForTwoCMissStatement MyForTwoCMissStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);
	}

	@Override
	public void visit(MyForTwoD1MissStatement MyForTwoD1MissStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);
	}

	@Override
	public void visit(MyForThreeStatement MyForThreeStatement) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forEnd.size() - 1;

		Code.put2(forEnd.get(position) - Code.pc + 1);

		ifConditionTrue.pop();

		ArrayList<Integer> listFalse = ifConditionFalse.peek();

		for (Integer integer : listFalse) {
			Code.fixup(integer);
		}

		ifConditionFalse.pop();

		ArrayList<Integer> breakList = forBreakList.peek();

		for (Integer i : breakList) {
			Code.fixup(i);
		}

		forBreakList.pop();
		forContinueList.pop();

		int lastIndexStart = forStart.size() - 1;
		forStart.remove(lastIndexStart);

		int lastIndexEnd = forEnd.size() - 1;
		forEnd.remove(lastIndexEnd);

		int lastIndexCondition = forCondition.size() - 1;
		forCondition.remove(lastIndexCondition);

	}

	@Override
	public void visit(MyForWord MyForWord) {
		// TODO Auto-generated method stub
		ifConditionFalse.push(new ArrayList<Integer>());
		ifConditionTrue.push(new ArrayList<Integer>());
		forContinueList.push(new ArrayList<Integer>());
		forBreakList.push(new ArrayList<Integer>());
		forEnd.add(1);
		forCondition.add(1);
	}

	@Override
	public void visit(MyNoDesignatorStatement MyNoDesignatorStatement) {
		// TODO Auto-generated method stub
		forStart.add(Code.pc);
	}

	@Override
	public void visit(MyForDesignatorStatement MyForDesignatorStatement) {
		// TODO Auto-generated method stub
		forStart.add(Code.pc);
	}

	@Override
	public void visit(MyNoForCondition MyNoForCondition) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		forCondition.set(forCondition.size() - 1, Code.pc);
		Code.put2(0);

		forEnd.set(forEnd.size() - 1, Code.pc);
	}

	@Override
	public void visit(MyForCondtion MyForCondtion) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		forCondition.set(forCondition.size() - 1, Code.pc);
		Code.put2(0);

		forEnd.set(forEnd.size() - 1, Code.pc);
	}

	@Override
	public void visit(StatementBreak StatementBreak) {
		// TODO Auto-generated method stub
		Code.putJump(0);
		ArrayList<Integer> breakList = forBreakList.peek();
		breakList.add(Code.pc - 2);
		forBreakList.pop();
		forBreakList.push(breakList);
	}

	@Override
	public void visit(StatementContinue StatementContinue) {
		// TODO Auto-generated method stub
		int position = forEnd.size() - 1;
		Code.putJump(forEnd.get(position));
		ArrayList<Integer> continueList = forContinueList.peek();
		continueList.add(Code.pc - 2);
		forContinueList.pop();
		forContinueList.push(continueList);
	}

	@Override
	public void visit(MyNoForThirdPart MyNoForThirdPart) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forStart.size() - 1;
		Code.put2(forStart.get(position) - Code.pc +1);
		
		
		int positionCond = forCondition.size() - 1;
		Code.fixup(forCondition.get(positionCond));
	}

	@Override
	public void visit(MyForThirdPart MyForThirdPart) {
		// TODO Auto-generated method stub
		Code.put(Code.jmp);
		int position = forStart.size() - 1;
		Code.put2(forStart.get(position) - Code.pc +1);
		
		
		int positionCond = forCondition.size() - 1;
		Code.fixup(forCondition.get(positionCond));
		
	}

	@Override
	public void visit(FuncCallWithoutPars FuncCallWithoutPars) {
		// TODO Auto-generated method stub
		Obj functionObj = FuncCallWithoutPars.getFactorDesignator().obj;
		int functionOffset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(functionOffset);
	}

	@Override
	public void visit(MyDStatementWithoutPars MyDStatementWithoutPars) {
		// TODO Auto-generated method stub
		Obj functionObj = MyDStatementWithoutPars.getFactorDesignator().obj;
		int functionOffset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(functionOffset);
	}

	

	
	
	
	
	

}
