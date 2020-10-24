package it.unibs.pajc;
import java.util.*;

import it.unibs.pajc.core.*;

public class CalcModel extends BaseModel {
	private HashMap<String, Operatore> calc;
	
	private class Op {
		final String name;
		final Operatore operatore;
		final Double value;
		final boolean isOperator;
		
		Op(Double value) {
			this.value = value;
			this.isOperator = false;
			this.name = null;
			this.operatore = null;
		}
		
		Op(String name, Operatore operatore) {
			this.name = name;
			this.operatore = operatore;
			this.isOperator = true;
			this.value = null;
		}
		
		public String toString() {
			return isOperator ? name : Double.toString(value);
		}
		
		
	}
	
	public CalcModel() {
		initCalc(); 
	}
	
	public void addOperator(String symbol, OperatoreBinario operatore) {
		calc.put(symbol, operatore);
	}
	
	public void addOperator(String symbol, OperatoreUnario operatore) {
		calc.put(symbol, operatore);
	}

	private void initCalc() {
		calc = new HashMap<String, Operatore>();
		
		addOperator("+", (x, y) -> { return x + y; }); 
		addOperator("-", (x, y) -> x - y);
		addOperator("*", (x, y) -> x * y);
		addOperator("/", (x, y) -> x / y);
		addOperator("§", (x, y) -> x + y * 2);
		
		addOperator("^", (x, y) -> (int) Math.pow(x, y));
		
		addOperator("#", (x) -> x * x);
		
	}
	
	public Operatore getOperator(String opSymbol) {
		return calc.get(opSymbol);
	}
	
	public Set<String> getOperatorsSymbol() {
		return calc.keySet();
	}

	///
	/// Stack di calcolo
	///
	private Stack<Op> opStack = new Stack();
	
	public void pushOperator(String operatorSymbol) {
		Operatore operatore = calc.get(operatorSymbol);
	
		if(operatore != null) {
			opStack.add(new Op(operatorSymbol, operatore));
		}
		
	}
	
	public void pushOperand(Double value) {
		opStack.add(new Op(value));
	}
	
	public void clearAll() {
		opStack.clear();
	}
	
	public Double evaluate() {
		// OPERATORE
		// OPERANDO A
		// OPERANDO B
		
		
		try {
		Stack<Op> pendingOp = (Stack<Op>)opStack.clone();
		
		OperatoreBinario operatore = (OperatoreBinario) pendingOp.pop().operatore;
		Double a = pendingOp.pop().value;
		Double b = pendingOp.pop().value;
		
		return operatore.eval(a, b);
		
		} catch(Exception ex) {
			
		}
		
		return null;
	}
	
	public String dump() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(Op op: opStack) {
			sb.append(op);
			sb.append(", ");
			
		}
		sb.append("]");
		
		
		return sb.toString();
	}
	
	
}

