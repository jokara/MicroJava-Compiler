package rs.ac.bg.etf.pp1;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean voidFound = false;
	boolean allowEqual= false;
	int nVars;
	public Struct boolStruct= null;
	private Struct currentMethodType;
	private Struct currentType;
	private Struct AddOpStruct;
	int relopOperator;
	int breakCounter = 0;
	
	ArrayList<ArrayList<Struct>> arguments;
	
	Logger log = Logger.getLogger(getClass());
	
	

	public SemanticAnalyzer() {
		boolStruct= new Struct(Struct.Bool);
		Tab.insert(Obj.Type, "bool", boolStruct); //dodavanje boolean tipa u tabelu simbola kao osnovnog tipa
		arguments= new ArrayList<>();
		//Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolStruct));
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(Program program) {		
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();     	
	}


	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			type.struct = Tab.noType;
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
		} 
		else {
			if (Obj.Type != typeNode.getKind()) {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.struct = Tab.noType;
			} 
			else {
				type.struct = typeNode.getType();
			}
		}
		currentType = type.struct;
	}
	
	
/*
	public void visit(MethodDecl methodDecl) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funcija " + currentMethod.getName() + " nema return iskaz!", null);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	}

	public void visit(MethodTypeName methodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), methodTypeName.getType().struct);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}

	
	public void visit(Const cnst){
		cnst.struct = Tab.intType;    	
	}
	
	public void visit(Var var) {
		var.struct = var.getDesignator().obj.getType();
	}

	public void visit(FuncCall funcCall){
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) { 
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
		} 
		else {
			report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			funcCall.struct = Tab.noType;
		}

	}
*/
	
	public boolean passed() {
		return !errorDetected;
	}

	
	@Override
	public void visit(NonTerminalIdent Identificator) {
		// TODO Auto-generated method stub
		
		if (Tab.currentScope.findSymbol(Identificator.getIdentName())==null) {
			if (currentMethod == null) {
				Tab.insert(Obj.Var, Identificator.getIdentName(), currentType);
				report_info("Deklarisana je promenljiva " + Identificator.getIdentName() + " tipa " + currentType.getKind(), Identificator);
			}
			else {
				if (!Identificator.getIdentName().equals(currentMethod.getName())) {
					Tab.insert(Obj.Var, Identificator.getIdentName(), currentType);
					report_info("Deklarisana je promenljiva " + Identificator.getIdentName() + " tipa " + currentType.getKind(), Identificator);
				}
				else {
					report_error("Semanticka greska na liniji " + Identificator.getLine() + " varijabla "+Identificator.getIdentName()+ " vec definisana", null);
				}
			}
			
		}
		else {
			report_error("Semanticka greska na liniji " + Identificator.getLine() + " varijabla "+Identificator.getIdentName()+ " vec definisana", null);
		}
	
	}
	

	@Override
	public void visit(IdentificatorSec IdentificatorSec) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(IdentificatorSec.getIdentName()) == null) {
			Tab.insert(Obj.Var, IdentificatorSec.getIdentName(), new Struct(Struct.Array, currentType));
			report_info("Deklarisana je promenljiva(niz) " + IdentificatorSec.getIdentName() + " tipa " + currentType.getKind(), IdentificatorSec);
		} else {
			report_error("Semanticka greska na liniji " + IdentificatorSec.getLine() + " varijabla(niz) "+IdentificatorSec.getIdentName()+" vec definisana", null);
		}
	}

	@Override
	public void visit(NumConst NumConst) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(NumConst.getConstName())== null) {
			if (currentType.getKind()==1) {
				Obj node=Tab.insert(Obj.Con, NumConst.getConstName(), currentType);
				node.setAdr(NumConst.getConstVal());
				report_info("Deklarisana je promenljiva " + NumConst.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ NumConst.getConstVal(), NumConst);
			}
			else {
				report_error("Semanticka greska na liniji " + NumConst.getLine() + " varijabli "+NumConst.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + NumConst.getLine() + " varijabla "+NumConst.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(BoolConst BoolConst) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(BoolConst.getConstName())== null) {
			if (currentType.getKind()==5) {
				Obj node =Tab.insert(Obj.Con, BoolConst.getConstName(), currentType);
				node.setAdr((BoolConst.getConstVal()==true) ? 1 : 0);
				report_info("Deklarisana je promenljiva " + BoolConst.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ BoolConst.getConstVal(), BoolConst);
			}
			else {
				report_error("Semanticka greska na liniji " + BoolConst.getLine() + " varijabli "+BoolConst.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + BoolConst.getLine() + " varijabla "+BoolConst.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(CharConst CharConst) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(CharConst.getConstName())== null) {
			if (currentType.getKind()==2) {
				Obj node=Tab.insert(Obj.Con, CharConst.getConstName(), currentType);
				node.setAdr(CharConst.getConstVal());
				report_info("Deklarisana je promenljiva " + CharConst.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ CharConst.getConstVal(), CharConst);
			}
			else {
				report_error("Semanticka greska na liniji " + CharConst.getLine() + " varijabli "+CharConst.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + CharConst.getLine() + " varijabla "+CharConst.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(BoolConstComma BoolConstComma) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(BoolConstComma.getConstName())== null) {
			if (currentType.getKind()==5) {
				Obj node =Tab.insert(Obj.Con, BoolConstComma.getConstName(), currentType);
				node.setAdr((BoolConstComma.getConstVal()==true) ? 1 : 0);
				report_info("Deklarisana je promenljiva " + BoolConstComma.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ BoolConstComma.getConstVal(), BoolConstComma);
			}
			else {
				report_error("Semanticka greska na liniji " + BoolConstComma.getLine() + " varijabli "+BoolConstComma.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + BoolConstComma.getLine() + " varijabla "+BoolConstComma.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(CharConstComma CharConstComma) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(CharConstComma.getConstName())== null) {
			if (currentType.getKind()==2) {
				Obj node = Tab.insert(Obj.Con, CharConstComma.getConstName(), currentType);
				node.setAdr(CharConstComma.getConstVal());
				report_info("Deklarisana je promenljiva " + CharConstComma.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ CharConstComma.getConstVal(), CharConstComma);
			}
			else {
				report_error("Semanticka greska na liniji " + CharConstComma.getLine() + " varijabli "+CharConstComma.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + CharConstComma.getLine() + " varijabla "+CharConstComma.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(NumConstComma NumConstComma) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(NumConstComma.getConstName())== null) {
			if (currentType.getKind()==1) {
				Obj node = Tab.insert(Obj.Con, NumConstComma.getConstName(), currentType);
				node.setAdr(NumConstComma.getConstVal());
				report_info("Deklarisana je promenljiva " + NumConstComma.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ NumConstComma.getConstVal(), NumConstComma);
			}
			else {
				report_error("Semanticka greska na liniji " + NumConstComma.getLine() + " varijabli "+NumConstComma.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + NumConstComma.getLine() + " varijabla "+NumConstComma.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(ConstDeclListBool ConstDeclListBool) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(ConstDeclListBool.getConstName())== null) {
			if (currentType.getKind()==5) {
				Obj node=Tab.insert(Obj.Con, ConstDeclListBool.getConstName(), currentType);
				node.setAdr((ConstDeclListBool.getConstVal()==true) ? 1 : 0);
				report_info("Deklarisana je promenljiva " + ConstDeclListBool.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ ConstDeclListBool.getConstVal(), ConstDeclListBool);
			}
			else {
				report_error("Semanticka greska na liniji " + ConstDeclListBool.getLine() + " varijabli "+ConstDeclListBool.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + ConstDeclListBool.getLine() + " varijabla "+ConstDeclListBool.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(ConstDeclListChar ConstDeclListChar) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(ConstDeclListChar.getConstName())== null) {
			if (currentType.getKind()==2) {
				Obj node=Tab.insert(Obj.Con, ConstDeclListChar.getConstName(), currentType);
				node.setAdr(ConstDeclListChar.getConstVal());
				report_info("Deklarisana je promenljiva " + ConstDeclListChar.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ ConstDeclListChar.getConstVal(), ConstDeclListChar);
			}
			else {
				report_error("Semanticka greska na liniji " + ConstDeclListChar.getLine() + " varijabli "+ConstDeclListChar.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + ConstDeclListChar.getLine() + " varijabla "+ConstDeclListChar.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(ConstDeclListNumber ConstDeclListNumber) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(ConstDeclListNumber.getConstName())== null) {
			if (currentType.getKind()==1) {
				Obj node=Tab.insert(Obj.Con, ConstDeclListNumber.getConstName(), currentType);
				node.setAdr(ConstDeclListNumber.getConstVal());
				report_info("Deklarisana je promenljiva " + ConstDeclListNumber.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ ConstDeclListNumber.getConstVal(), ConstDeclListNumber);
			}
			else {
				report_error("Semanticka greska na liniji " + ConstDeclListNumber.getLine() + " varijabli "+ConstDeclListNumber.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + ConstDeclListNumber.getLine() + " varijabla "+ConstDeclListNumber.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(ConstDeclListBoolComma ConstDeclListBoolComma) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(ConstDeclListBoolComma.getConstName())== null) {
			if (currentType.getKind()==5) {
				Obj node=Tab.insert(Obj.Con, ConstDeclListBoolComma.getConstName(), currentType);
				node.setAdr((ConstDeclListBoolComma.getConstVal()==true) ? 1 : 0);
				report_info("Deklarisana je promenljiva " + ConstDeclListBoolComma.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ ConstDeclListBoolComma.getConstVal(), ConstDeclListBoolComma);
			}
			else {
				report_error("Semanticka greska na liniji " + ConstDeclListBoolComma.getLine() + " varijabli "+ConstDeclListBoolComma.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + ConstDeclListBoolComma.getLine() + " varijabla "+ConstDeclListBoolComma.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(ConstDeclListCharComma ConstDeclListCharComma) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(ConstDeclListCharComma.getConstName())== null) {
			if (currentType.getKind()==2) {
				Obj node=Tab.insert(Obj.Con, ConstDeclListCharComma.getConstName(), currentType);
				node.setAdr(ConstDeclListCharComma.getConstVal());
				report_info("Deklarisana je promenljiva " + ConstDeclListCharComma.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ ConstDeclListCharComma.getConstVal(), ConstDeclListCharComma);
			}
			else {
				report_error("Semanticka greska na liniji " + ConstDeclListCharComma.getLine() + " varijabli "+ConstDeclListCharComma.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + ConstDeclListCharComma.getLine() + " varijabla "+ConstDeclListCharComma.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(ConstDeclListNumberComma ConstDeclListNumberComma) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(ConstDeclListNumberComma.getConstName())== null) {
			if (currentType.getKind()==1) {
				Obj node = Tab.insert(Obj.Con, ConstDeclListNumberComma.getConstName(), currentType);
				node.setAdr(ConstDeclListNumberComma.getConstVal());
				report_info("Deklarisana je promenljiva " + ConstDeclListNumberComma.getConstName() + " tipa " + currentType.getKind() + " i vrednosti "+ ConstDeclListNumberComma.getConstVal(), ConstDeclListNumberComma);
			}
			else {
				report_error("Semanticka greska na liniji " + ConstDeclListNumberComma.getLine() + " varijabli "+ConstDeclListNumberComma.getConstName()+" pogresno definisan tip", null);
				return;
			}
		} 
		else {
			report_error("Semanticka greska na liniji " + ConstDeclListNumberComma.getLine() + " varijabla "+ConstDeclListNumberComma.getConstName()+" vec definisana", null);
		}
		
	}

	@Override
	public void visit(MethodTypeName1 MethodTypeName) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(MethodTypeName.getMethodName()) == null) {
			
			currentMethod = Tab.insert(Obj.Meth, MethodTypeName.getMethodName(), currentMethodType);
			MethodTypeName.obj = currentMethod;
			//otvaramo novi opseg
			Tab.openScope();
			currentMethod.setLevel(0);
			voidFound = false;
			report_info("Procesira se metoda " + MethodTypeName.getMethodName() + " povratnog tipa " + currentMethodType.getKind(), MethodTypeName);
		} else {
			report_error("Semanticka greska na liniji " + MethodTypeName.getLine() + " varijabla " + MethodTypeName.getMethodName() + " vec definisana", null);
		}
		
	}

	@Override
	public void visit(TypeMethod TypeMethod) {
		// TODO Auto-generated method stub
		Obj typeNode = Tab.find(TypeMethod.getTypeName());
		if (typeNode == Tab.noObj) {
			TypeMethod.struct = Tab.noType;
			report_error("Nije pronadjen tip " + TypeMethod.getTypeName() + " u tabeli simbola", null);
		} 
		else {
			if (Obj.Type != typeNode.getKind()) {
				report_error("Greska: Ime " + TypeMethod.getTypeName() + " ne predstavlja tip ", TypeMethod);
				TypeMethod.struct = Tab.noType;
			} 
			else {
				TypeMethod.struct = typeNode.getType();
			}
		}
		currentMethodType = TypeMethod.struct;
		
	}

	@Override
	public void visit(MethodTypeVoid MethodTypeVoid) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(MethodTypeVoid.getMethodName()) == null) {
			currentMethodType=Tab.noType;
			currentMethod = Tab.insert(Obj.Meth, MethodTypeVoid.getMethodName(), currentMethodType);
			MethodTypeVoid.obj = currentMethod;
			//otvaramo novi opseg
			Tab.openScope();
			currentMethod.setLevel(0);
			
			voidFound=true;
			report_info("Procesira se metoda " + MethodTypeVoid.getMethodName() + " bez povratnog tipa " + "("+currentMethodType.getKind()+")", MethodTypeVoid);
		} else {
			report_error("Semanticka greska na liniji " + MethodTypeVoid.getLine() + " varijabla " + MethodTypeVoid.getMethodName() + " vec definisana", null);
		}

		
	}

	@Override
	public void visit(MyFormalParamDecl MyFormalParamDecl) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(MyFormalParamDecl.getFormalParams()) == null && !(MyFormalParamDecl.getFormalParams().equals(currentMethod.getName()))) {
			Obj node = Tab.insert(Obj.Var, MyFormalParamDecl.getFormalParams(), currentType);
			node.setFpPos(currentMethod.getLevel());
			int level = currentMethod.getLevel();
			report_info("Deklarisan je formalni parametar " + MyFormalParamDecl.getFormalParams() + " tipa "
					+ currentType.getKind() + " na poziciji " + level, MyFormalParamDecl);
			level += 1;
			currentMethod.setLevel(level);

		} else {
			report_error("Semanticka greska na liniji " + MyFormalParamDecl.getLine() + " formalni parametar "
					+ MyFormalParamDecl.getFormalParams() + " vec definisan", null);
		}
		
	}

	@Override
	public void visit(FormalParamArray FormalParamArray) {
		// TODO Auto-generated method stub
		if (Tab.currentScope.findSymbol(FormalParamArray.getFormalParams()) == null && !(FormalParamArray.getFormalParams().equals(currentMethod.getName()))) {
			Obj node = Tab.insert(Obj.Var, FormalParamArray.getFormalParams(), new Struct(Struct.Array, currentType));
			node.setFpPos(currentMethod.getLevel());
			int level = currentMethod.getLevel();
			report_info("Deklarisan je formalni parametar(niz)" + FormalParamArray.getFormalParams() + " tipa "
					+ currentType.getKind() + " na poziciji " + level, FormalParamArray);
			level += 1;
			currentMethod.setLevel(level);

		} else {
			report_error("Semanticka greska na liniji " + FormalParamArray.getLine() + " formalni parametar(niz) "
					+ FormalParamArray.getFormalParams() + " vec definisan", null);
		}
		
		
	}
	/*************************************************NOVO****************************************************/
	@Override
	public void visit(MethodDecl MethodDecl) {
		// TODO Auto-generated method stub
		if (!returnFound && currentMethodType != Tab.noType) {
			report_error("Semanticka greska na liniji " + MethodDecl.getLine() + " funkcija " + currentMethod.getName() +
					" nema return iskaz", null);
		}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		returnFound = false;
		currentMethod = null;
		
	}

	@Override
	public void visit(DesignatorIdent DesignatorIdent) {
		// TODO Auto-generated method stub
		Obj obj = Tab.find(DesignatorIdent.getDesignatorName());
		if (obj == Tab.noObj) {
			report_error("Semanticka greska na liniji " + DesignatorIdent.getLine() + " varijabla " + DesignatorIdent.getDesignatorName()+ " nije deklarisana", null);
		}
		DesignatorIdent.obj = obj;
		//report_info("Procesira se " + obj.getName() + " na liniji "+ DesignatorIdent.getLine(), DesignatorIdent);
		
	}
	

	@Override
	public void visit(FuncCall FuncCall) {
		// TODO Auto-generated method stub
		Obj func = FuncCall.getFactorDesignator().obj;
		if (Obj.Meth != func.getKind()) {
			report_error("Semanticka greska na liniji " + FuncCall.getLine() + " ime " + func.getName()+ " nije deklarisano kao funkcija!", null);
			FuncCall.struct = Tab.noType;
		}
		else {
			report_info("Pronadjen je poziv funkcije" + func.getName(), FuncCall);
			FuncCall.struct = func.getType();
		}
	}
	
	

	@Override
	public void visit(FuncCallWithoutPars FuncCallWithoutPars) {
		// TODO Auto-generated method stub
		Obj func = FuncCallWithoutPars.getFactorDesignator().obj;
		if (Obj.Meth != func.getKind()) {
			report_error("Semanticka greska na liniji " + FuncCallWithoutPars.getLine() + " ime " + func.getName()+ " nije deklarisano kao funkcija!", null);
			FuncCallWithoutPars.struct = Tab.noType;
		}
		else {
			report_info("Pronadjen je poziv funkcije" + func.getName(), FuncCallWithoutPars);
			FuncCallWithoutPars.struct = func.getType();
		}
	}

	@Override
	public void visit(TermSingleFactor TermSingleFactor) {
		// TODO Auto-generated method stub
		TermSingleFactor.struct = TermSingleFactor.getFactor().struct;
	}

	@Override
	public void visit(TermMultipleFactors TermMultipleFactors) {
		// TODO Auto-generated method stub
		Struct te= TermMultipleFactors.getTerm().struct;
		Struct factor=TermMultipleFactors.getFactor().struct;
		if (te.equals(factor) && (factor == Tab.intType)) {
			TermMultipleFactors.struct = factor;
		}
		else {
			report_error("Semanticka greska na liniji " + TermMultipleFactors.getLine() + " nisu tipovi kompatibilni u izrazu za mnozenje/deljenje", null);
			TermMultipleFactors.struct = Tab.noType;
		}
	}

	@Override
	public void visit(TermListSingleTerm TermListSingleTerm) {
		// TODO Auto-generated method stub
		TermListSingleTerm.struct = TermListSingleTerm.getTerm().struct;
		AddOpStruct = TermListSingleTerm.getTerm().struct;
	}
	

	@Override
	public void visit(TermListMultipleTerms TermListMultipleTerms) {
		// TODO Auto-generated method stub
		Struct te= TermListMultipleTerms.getTerm().struct;
		if (te.equals(AddOpStruct) && AddOpStruct == Tab.intType) {
			TermListMultipleTerms.struct = AddOpStruct;
			
		}
		else {
			report_error("Semanticka greska na liniji " + TermListMultipleTerms.getLine() + " nisu tipovi kompatibilni u izrazu za sabiranje/oduzimanje", null);
			TermListMultipleTerms.struct = Tab.noType;
		}
	}

	@Override
	public void visit(MyConst MyConst) {
		// TODO Auto-generated method stub
		MyConst.struct = Tab.intType;
	}
	
	

	@Override
	public void visit(MyBool MyBool) {
		// TODO Auto-generated method stub
		MyBool.struct = boolStruct;
	}

	@Override
	public void visit(MyChar MyChar) {
		// TODO Auto-generated method stub
		MyChar.struct = Tab.charType;
	}

	
	@Override
	public void visit(Var Var) {
		// TODO Auto-generated method stub
		Var.struct = Var.getDesignator().obj.getType();
	}

	@Override
	public void visit(StatementReturnValue StatementReturnValue) {
		// TODO Auto-generated method stub
		returnFound = true;
		Struct currMethodType= currentMethod.getType();
		if (!currMethodType.compatibleWith(StatementReturnValue.getExpr().struct)) {
			report_error("Semanticka greska na liniji " + StatementReturnValue.getLine() + " tip izraza u return se ne slaze sa tipom povratne vrednosti", null);
		}
		else {
			report_info("Return tip se slaze sa tipom funkcije", StatementReturnValue);
		}
	}

	@Override
	public void visit(MyDesignatorExprStatement MyDStatementExpr) {
		// TODO Auto-generated method stub
		Struct myDSStruct = MyDStatementExpr.getExpr().struct;
		Struct myStruct = MyDStatementExpr.getDesignator().obj.getType();

		if (MyDStatementExpr.getDesignator().obj.getKind() != Obj.Con) {
			if (myDSStruct.assignableTo(myStruct)) {
				report_info("Tipovi su kompatibilni ", MyDStatementExpr);
			}
			else {
				if (myDSStruct.getKind() == Struct.Array && allowEqual==true) {
					report_info("Tipovi su kompatibilni (dodela iz niza)", MyDStatementExpr);
					allowEqual=false;
				} 
				else {
					report_error("Semanticka greska na liniji " + MyDStatementExpr.getLine()
							+ " nekompatibilni tipovi u dodeli vrednosti", null);

				}
			}
		} else {
			report_error("Semanticka greska na liniji " + MyDStatementExpr.getLine()
					+ " konstanti se ne moze promeniti vrednost", null);
		}

	}

	@Override
	public void visit(ExprNoMinus ExprNoMinus) {
		// TODO Auto-generated method stub
		ExprNoMinus.struct = ExprNoMinus.getTermList().struct;
	}

	/*@Override
	public void visit(ExprMinus ExprMinus) {
		// TODO Auto-generated method stub
		if (ExprMinus.getTermList().struct==Tab.intType) {
			ExprMinus.struct = ExprMinus.getTermList().struct;
		}
		else {
			ExprMinus.struct = Tab.noType;
			report_error("Semanticka greska na liniji " + ExprMinus.getLine() + " nekompatibilni tipovi za operator minus", null);
		}
	}*/

	@Override
	public void visit(MyDStatementWithoutPars MyDStatementWithoutPars) {
		// TODO Auto-generated method stub
		Obj func = MyDStatementWithoutPars.getFactorDesignator().obj;
		if (func.getLevel()!= arguments.get(arguments.size()-1).size()) {
			report_error("Semanticka greska na liniji " + MyDStatementWithoutPars.getLine() + " ime " + func.getName()+ " Sintaksna greska:Broj parametara se ne poklapa!", null);
			MyDStatementWithoutPars.struct = Tab.noType;
			arguments.remove(arguments.size() - 1 );
		}
		
		
		
		
		if (Obj.Meth != func.getKind()) {
			report_error("Semanticka greska na liniji " + MyDStatementWithoutPars.getLine() + " ime " + func.getName()+ " nije deklarisano kao funkcija!", null);
			MyDStatementWithoutPars.struct = Tab.noType;
			arguments.remove(arguments.size() - 1 );
			return;
		}
		else {
			report_info("Pronadjen je poziv funkcije" + func.getName(), MyDStatementWithoutPars);
			MyDStatementWithoutPars.struct = func.getType();
			arguments.remove(arguments.size()-1);
		}
		
	}

	
	
	@Override
	public void visit(MyDStatementWithPars MyDStatementWithPars) {
		// TODO Auto-generated method stub
		Obj func = MyDStatementWithPars.getFactorDesignator().obj;
		
		if (func.getLevel()!= arguments.get(arguments.size()-1).size()) {
			report_error("Semanticka greska na liniji " + MyDStatementWithPars.getLine() + " ime " + func.getName()+ " Sintaksna greska:Broj parametara se ne poklapa!", null);
			MyDStatementWithPars.struct = Tab.noType;
			arguments.remove(arguments.size() - 1 );
			return;
		}
		
		Collection<Obj> locals = null;
		if (!func.getName().equals(currentMethod.getName())) {
			locals = func.getLocalSymbols();
		}
		else {
			locals= Tab.currentScope.values();
		}
		
		Obj[] localsArray= new Obj[locals.size()];
		locals.toArray(localsArray);
		
		for (int i=0; i< func.getLevel(); i++) {
			if (arguments.get(arguments.size() - 1).get(i).assignableTo(localsArray[i].getType())) {
				
			}
			else{
				report_error("Semanticka greska: tipovi formalnih i stvarnih argumenata se ne poklapaju", MyDStatementWithPars);
				arguments.remove(arguments.size()-1);
				return;
			}
		}
		
		
		if (Obj.Meth != func.getKind()) {
			report_error("Semanticka greska na liniji " + MyDStatementWithPars.getLine() + " ime " + func.getName()+ " nije deklarisano kao funkcija!", null);
			MyDStatementWithPars.struct = Tab.noType;
			arguments.remove(arguments.size() - 1 );
			return;
		}
		else {
			report_info("Pronadjen je poziv funkcije " + func.getName(), MyDStatementWithPars);
			MyDStatementWithPars.struct = func.getType();
			arguments.remove(arguments.size()-1);
		}
		
		
	}

	
	
	/*****************************************************30.12.2019*******************************************************/
	
	
	
	@Override
	public void visit(MyDStatementDec MyDStatementDec) {
		// TODO Auto-generated method stub
		Struct myStruct= MyDStatementDec.getDesignator().obj.getType();
		if (myStruct==Tab.intType) {
			MyDStatementDec.struct=Tab.intType;
			report_info("Tip je kompatibilan sa operatorom --" , MyDStatementDec);
		}
		else {
			MyDStatementDec.struct=Tab.noType;
			report_error("Semanticka greska na liniji " + MyDStatementDec.getLine() + " izraz nije kompatibilan sa operatorom --!", null);
		}
	}

	@Override
	public void visit(MyDStatementInc MyDStatementInc) {
		// TODO Auto-generated method stub
		Struct myStruct= MyDStatementInc.getDesignator().obj.getType();
		if (myStruct==Tab.intType) {
			MyDStatementInc.struct=Tab.intType;
			report_info("Tip je kompatibilan sa operatorom ++" , MyDStatementInc);
		}
		else {
			MyDStatementInc.struct=Tab.noType;
			report_error("Semanticka greska na liniji " + MyDStatementInc.getLine() + " izraz nije kompatibilan sa operatorom ++!", null);
		}
	}

	@Override
	public void visit(MyFactorNewExpr MyFactorNewExpr) {
		// TODO Auto-generated method stub
		if (MyFactorNewExpr.getExpr().struct==Tab.intType) {
			//MyFactorNewExpr.struct = MyFactorNewExpr.getType().struct;
			MyFactorNewExpr.struct= new Struct(Struct.Array, MyFactorNewExpr.getType().struct);
		}
		else {
			MyFactorNewExpr.struct = Tab.noType;
			report_error("Semanticka greska na liniji " + MyFactorNewExpr.getLine() + " izraz u uglastim zagradama mora biti tipa int!", null);
		}
		
		
	}

	@Override
	public void visit(MyFactorNew MyFactorNew) {
		// TODO Auto-generated method stub
		MyFactorNew.struct = MyFactorNew.getType().struct;
		
	}

	@Override
	public void visit(MyFactorExpr MyFactorExpr) {
		// TODO Auto-generated method stub
		MyFactorExpr.struct = MyFactorExpr.getExpr().struct;
		
	}

	@Override
	public void visit(MyPrintStatement1 MyPrintStatement1) {
		// TODO Auto-generated method stub
		if (MyPrintStatement1.getExpr().struct.getKind()!=Struct.Int && MyPrintStatement1.getExpr().struct.getKind()!=Struct.Char) {
			report_error("Printu ne odgovara tip argumenta", MyPrintStatement1);
		}
		printCallCount++;
	}

	@Override
	public void visit(MyPrintStatement2 MyPrintStatement2) {
		// TODO Auto-generated method stub
		if (MyPrintStatement2.getExpr().struct.getKind()!=Struct.Int && MyPrintStatement2.getExpr().struct.getKind()!=Struct.Char) {
			report_error("Printu ne odgovara tip argumenta", MyPrintStatement2);
		}
		printCallCount++;

	}

	@Override
	public void visit(DesignatorExpr DesignatorExpr) {
		// TODO Auto-generated method stub
		Obj obj = Tab.find(DesignatorExpr.getDesignator().obj.getName());
		if (obj == Tab.noObj) {
			report_error("Semanticka greska na liniji " + DesignatorExpr.getLine() + " varijabla " + DesignatorExpr.getDesignator().obj.getName()+ " nije deklarisana", null);
		}
		else {
			if (DesignatorExpr.getExpr().struct==Tab.intType) {
				if (obj.getType().getKind()==Struct.Array) {
					allowEqual = true;
					DesignatorExpr.obj = new Obj(Obj.Elem, "", DesignatorExpr.getDesignator().obj.getType().getElemType());
					report_info("Procesira se " + obj.getName() + " na liniji "+ DesignatorExpr.getLine(), DesignatorExpr);
				}
				else {
					DesignatorExpr.obj = Tab.noObj;
					report_error("Semanticka greska na liniji " + DesignatorExpr.getLine() + " varijabla " + DesignatorExpr.getDesignator().obj.getName()+ " nije deklarisana kao niz", null);
				}
			}
			else {
				DesignatorExpr.obj = Tab.noObj;
				report_error("Semanticka greska na liniji " + DesignatorExpr.getLine() + " indeksi niza moraju biti tipa int", null);
			}
		}
		
		
		
	}

	
	
	
	/*****************************************************22.01.2020*******************************************************/
	

	@Override
	public void visit(ActualParam ActualParam) {
		// TODO Auto-generated method stub
		arguments.get(arguments.size() - 1).add(ActualParam.getExpr().struct);
		
	}

	@Override
	public void visit(ActualParams ActualParams) {
		// TODO Auto-generated method stub
		arguments.get(arguments.size() - 1).add(ActualParams.getExpr().struct);
	}

	@Override
	public void visit(FuncCallDes FuncCallDes) {
		// TODO Auto-generated method stub
		FuncCallDes.obj=FuncCallDes.getDesignator().obj;
		arguments.add(new ArrayList<>());
	}

	

	
	
	/*****************************************************23.01.2020*******************************************************/
	
	
	@Override
	public void visit(MyReadStatement MyReadStatement) {
		// TODO Auto-generated method stub
		if(MyReadStatement.getDesignator().obj.getType()!=Tab.noType) {
			report_error("Ne odgovara dati tip instrukciji read", MyReadStatement);
		}
	}

	@Override
	public void visit(CondFactSingle CondFactSingle) {
		// TODO Auto-generated method stub
		CondFactSingle.struct=CondFactSingle.getExpr().struct;
	}

	/*
	 * 
	 * 	0 ==    1 !=   2 >    3 >=     4 <      5 <=		6 nista od navedenog
	 * 
	 * */
	
	@Override
	public void visit(CondFactRelop CondFactRelop) {
		// TODO Auto-generated method stub
		Struct struct1=CondFactRelop.getExpr().struct;
		Struct struct2=CondFactRelop.getExpr1().struct;
		
		if (struct1.compatibleWith(struct2) == true) {
			
		}
		else {
			report_error("Izrazi nisu kompatibilni", CondFactRelop);
			CondFactRelop.struct=Tab.noType;
			relopOperator=6;
			return;
		}
		
		
		if ((struct1.getKind()==Struct.Array || struct2.getKind()==Struct.Array) && (relopOperator!=0 && relopOperator!=1)) {
			report_error("Uz nizove je dozvoljeno jedino koriscenje == i !=", CondFactRelop);
			CondFactRelop.struct= Tab.noType;
			relopOperator=6;
			return;
		}
		
		relopOperator=6;
		CondFactRelop.struct= new Struct(Struct.Bool);
		
	}

	@Override
	public void visit(LowerEqual LowerEqual) {
		// TODO Auto-generated method stub
		relopOperator=5;
	}

	@Override
	public void visit(Lower Lower) {
		// TODO Auto-generated method stub
		relopOperator=4;
	}

	@Override
	public void visit(GreaterEqual GreaterEqual) {
		// TODO Auto-generated method stub
		relopOperator=3;
	}

	@Override
	public void visit(Greater Greater) {
		// TODO Auto-generated method stub
		relopOperator=2;
	}

	@Override
	public void visit(IsNotEqual IsNotEqual) {
		// TODO Auto-generated method stub
		relopOperator=1;
	}

	@Override
	public void visit(IsEqual IsEqual) {
		// TODO Auto-generated method stub
		relopOperator=0;
	}

	@Override
	public void visit(CondFactSin CondFactSin) {
		// TODO Auto-generated method stub
		int kindOfCondFactSin = CondFactSin.getCondFact().struct.getKind();
		if (kindOfCondFactSin == Struct.Bool) {
			CondFactSin.struct = CondFactSin.getCondFact().struct;
		}
		else {
			CondFactSin.struct = Tab.noType;
			//report_error("Izraz nije odgovarajuceg tipa", CondFactSin);
		}
	}

	@Override
	public void visit(CondFactMult CondFactMult) {
		// TODO Auto-generated method stub
		Struct struct1 = CondFactMult.getCondFact().struct;
		Struct struct2 = CondFactMult.getCondTerm().struct;

		int kind1 = struct1.getKind();
		int kind2 = struct2.getKind();
		if (kind1==Struct.Bool && kind2==Struct.Bool) {
			CondFactMult.struct = new Struct(Struct.Bool);
		} else {
			//report_error("Izrazi nisu kompatibilni", CondFactMult);
			CondFactMult.struct = Tab.noType;
			return;
		}
	}

	@Override
	public void visit(CondTermSingle CondTermSingle) {
		// TODO Auto-generated method stub
		int kindOfCondTermSingle = CondTermSingle.getCondTerm().struct.getKind();
		if (kindOfCondTermSingle == Struct.Bool) {
			CondTermSingle.struct = CondTermSingle.getCondTerm().struct;
		}
		else {
			CondTermSingle.struct = Tab.noType;
			//report_error("Izraz nije odgovarajuceg tipa", CondTermSingle);
		}
	}

	@Override
	public void visit(CondTermMultiple CondTermMultiple) {
		// TODO Auto-generated method stub
		Struct struct1 = CondTermMultiple.getCondTerm().struct;
		Struct struct2 = CondTermMultiple.getCondTerm1().struct;

		int kind1 = struct1.getKind();
		int kind2 = struct2.getKind();
		if (kind1==Struct.Bool && kind2==Struct.Bool) {
			CondTermMultiple.struct = new Struct(Struct.Bool);
		} else {
			//report_error("Izrazi nisu kompatibilni", CondTermMultiple);
			CondTermMultiple.struct = Tab.noType;
			return;
		}
	}

	@Override
	public void visit(MyIfCondition MyIfCondition) {
		// TODO Auto-generated method stub
		int kind = MyIfCondition.getCondition().struct.getKind();
		if (kind != Struct.Bool) {
			report_error("Izraz(i) u uslovu nisu odgovarajuceg tipa", MyIfCondition);
		}
	}

	@Override
	public void visit(MyForWord MyForWord) {
		// TODO Auto-generated method stub
		breakCounter++;
	}

	@Override
	public void visit(MyForMissStatement MyForMissStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(MyForOneCD2MissStatement MyForOneCD2MissStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(MyForOneD1D2MissStatement MyForOneD1D2MissStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(MyForOneD1CMissStatement MyForOneD1CMissStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(MyForTwoD2MissStatement MyForTwoD2MissStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(MyForTwoCMissStatement MyForTwoCMissStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(MyForTwoD1MissStatement MyForTwoD1MissStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(MyForThreeStatement MyForThreeStatement) {
		// TODO Auto-generated method stub
		breakCounter--;
	}

	@Override
	public void visit(StatementBreak StatementBreak) {
		// TODO Auto-generated method stub
		if (breakCounter == 0) {
			report_error("Ne nalazite se u petlji da biste iskocili iz nje", StatementBreak);
		}
		else {
			report_info("Uspesno ste iskocili iz petlje", StatementBreak);
		}
	}

	@Override
	public void visit(StatementContinue StatementContinue) {
		// TODO Auto-generated method stub
		if (breakCounter == 0) {
			report_error("Ne nalazite se u petlji da biste iskocili iz nje", StatementContinue);
		}
		else {
			report_info("Uspesno ste nastavili petlju", StatementContinue);
		}
	}

	@Override
	public void visit(TermListSingleTermMinus TermListSingleTermMinus) {
		// TODO Auto-generated method stub
		if (TermListSingleTermMinus.getTerm().struct==Tab.intType) {
			TermListSingleTermMinus.struct = TermListSingleTermMinus.getTerm().struct;
		}
		else {
			TermListSingleTermMinus.struct = Tab.noType;
			report_error("Semanticka greska na liniji " + TermListSingleTermMinus.getLine() + " nekompatibilni tipovi za operator minus", null);
		}
	}
	
	
	
	
	
	
	
	
	
}

