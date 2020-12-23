package ste.calculator;

class FirstSpaceDetectedCalculatorState extends CalculatorState {

    FirstSpaceDetectedCalculatorState(Calculator context) {
        super(context);
    }

    @Override
    void readChar(char ch) throws CalculatorException {
        if(Operators.isAnOperator(ch)) {
            this.context.setOperator(Operators.getOperatorByCharacter(ch));
            this.context.setCurrentState(new BinaryOperatorCalculatorState(context));
        } else if(!Character.isSpaceChar(ch)) {
            throw new CalculatorException(CalculatorFault.INVALID_CHARACTER);
        }
    }
}