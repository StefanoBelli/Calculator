package ste.calculator;

import java.util.HashMap;
import java.util.Map;

class SumOperator extends ArithmeticOperator {
    @Override
    int result(int a, int b) {
        return a + b;
    }
}

class SubOperator extends ArithmeticOperator {
    @Override
    int result(int a, int b) {
        return a - b;
    }
}

class MulOperator extends ArithmeticOperator {
    @Override
    int result(int a, int b) {
        return a * b;
    }
}

class DivOperator extends ArithmeticOperator {
    @Override
    int result(int a, int b) {
        return a / b;
    }
}

class Operators {
    private static final Map<Character, ArithmeticOperator> operators = new HashMap<>();

    static {
        operators.put('+', new SumOperator());
        operators.put('-', new SubOperator());
        operators.put('*', new MulOperator());
        operators.put('/', new DivOperator());
    }

    static ArithmeticOperator getOperatorByCharacter(char ch) {
        return operators.get(ch);
    }
    
    static boolean isAnOperator(char ch) {
        return operators.get(ch) != null;
    }
}