package ste.calculator;

public class CalculatorException extends Exception {
    public static final long serialVersionUID = 1L;

    private CalculatorFault calculatorFault;

    public CalculatorException(CalculatorFault calculatorFault) {
        this.calculatorFault = calculatorFault;
    }

    public CalculatorFault getCalculatorFault() {
        return calculatorFault;
    }
}