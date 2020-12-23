package ste.calculator;

class FirstOperandCalculatorState extends CalculatorState {
    private String firstNumber = new String();

    FirstOperandCalculatorState(Calculator context) {
        super(context);
    }

    private void parseFirstNumberThenSetState(char c, CalculatorState nextState) 
    		throws CalculatorException {
    	
    	this.context.setFirstOperand(Integer.parseInt(firstNumber));
        this.context.setCurrentState(nextState);
    }

    @Override
    void readChar(char ch) throws CalculatorException {
        if(Character.isDigit(ch)) {
            firstNumber += ch;
        } else if(Character.isSpaceChar(ch)) {
            parseFirstNumberThenSetState(ch, new FirstSpaceDetectedCalculatorState(context));
        } else if(Operators.isAnOperator(ch)) {
            this.context.setOperator(Operators.getOperatorByCharacter(ch));
            parseFirstNumberThenSetState(ch, new BinaryOperatorCalculatorState(context));
        } else {
            throw new CalculatorException(CalculatorFault.INVALID_CHARACTER);
        }
    }
}