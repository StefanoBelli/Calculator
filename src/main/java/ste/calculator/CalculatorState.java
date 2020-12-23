package ste.calculator;

abstract class CalculatorState {
    protected Calculator context;

    CalculatorState(Calculator context) {
        this.context = context;
    }
    
    abstract void readChar(char ch) throws CalculatorException;
}