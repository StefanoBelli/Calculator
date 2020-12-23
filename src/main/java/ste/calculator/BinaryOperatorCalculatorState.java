package ste.calculator;

class BinaryOperatorCalculatorState extends CalculatorState {
    BinaryOperatorCalculatorState(Calculator context) {
        super(context);
    }

    @Override
    void readChar(char ch) throws CalculatorException {
        if(Character.isDigit(ch)) {
            CalculatorState nextState = new SecondOperandCalculatorState(context);
            this.context.setCurrentState(nextState);
            nextState.readChar(ch);
        } else if(!Character.isSpaceChar(ch)) {
            throw new CalculatorException(CalculatorFault.INVALID_CHARACTER);
        }
    }
}