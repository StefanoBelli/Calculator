package ste.calculator;

class SecondOperandCalculatorState extends CalculatorState {
     private String secondNumber = new String();

    SecondOperandCalculatorState(Calculator context) {
        super(context);
    }

    @Override
    void readChar(char ch) throws CalculatorException {
        if(Character.isDigit(ch)) {
            secondNumber += ch;
        } else if(ch == ';') {
        	this.context.setSecondOperand(Integer.parseInt(secondNumber));

            try {
                this.context.result(
                    this.context.getOperator().result(
                        this.context.getFirstOperand(), 
                        this.context.getSecondOperand()));
            } catch(ArithmeticException e) {
                throw new CalculatorException(CalculatorFault.DIV_BY_ZERO);
            }

        } else {
            throw new CalculatorException(CalculatorFault.INVALID_CHARACTER);
        }
    }
}