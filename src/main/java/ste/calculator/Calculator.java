package ste.calculator;

public class Calculator {
    //signal when result is available
    private final ResultPushable resultPushable;

    //calculator data
    private int firstOperand;
    private ArithmeticOperator operator;
    private int secondOperand;

    //current state
    private CalculatorState currentState;

    private boolean gotCalledBack;

    // BEGIN
    // package-private operand getters and setters
    //
    int getFirstOperand() {
        return this.firstOperand;
    }

    ArithmeticOperator getOperator() {
        return this.operator;
    }

    int getSecondOperand() {
        return this.secondOperand;
    }

    void setFirstOperand(int firstOperand) {
        this.firstOperand = firstOperand;
    }

    void setOperator(ArithmeticOperator operator) {
        this.operator = operator;
    }

    void setSecondOperand(int secondOperand) {
        this.secondOperand = secondOperand;
    }
    // END
    // of package-private operand getters and setters
    //


    // BEGIN
    // package-private current state setter
    //
    void setCurrentState(CalculatorState currentState) {
        this.currentState = currentState;
    }
    // END
    // of package-private states current state setter
    //

    // BEGIN
    // package-private """callback"""
    void result(int res) {
        this.gotCalledBack = true;
        this.resultPushable.onResult(res);
    }
    // END
    // of package-private """callback"""
    //

    // BEGIN
    // public interface
    //
    public Calculator(ResultPushable resultPushable) {
        this.resultPushable = resultPushable;
        this.currentState = new FirstOperandCalculatorState(this);
        this.gotCalledBack = false;
    }

    public ResultPushable getResultPushable() {
        return resultPushable;
    }

    public void eval(String expr) throws CalculatorException {
        char[] chBuf = expr.toCharArray();

        for(final char ch : chBuf) {
            currentState.readChar(ch);
        }

        this.currentState = new FirstOperandCalculatorState(this);

        if(!gotCalledBack)
            throw new CalculatorException(CalculatorFault.INVALID_STRING);
    }
    // END
    // of public interface
    //
}