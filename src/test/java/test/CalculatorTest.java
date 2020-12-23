package test;

import ste.calculator.Calculator;
import ste.calculator.CalculatorException;
import ste.calculator.CalculatorFault;
import ste.calculator.ResultPushable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    
    private static class IntHolder {
        private int value = 0;

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public int evalBaseForTest(String expr) throws CalculatorException {
        IntHolder valueHolder = new IntHolder();

        Calculator c = new Calculator(new ResultPushable() {
            @Override
            public void onResult(int res) {
                valueHolder.setValue(res);
            }
        });

        c.eval(expr);

        return valueHolder.getValue();
    }
    
}