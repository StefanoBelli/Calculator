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
    
    @Test
    public void testSumShouldWork() throws CalculatorException {
    	assertEquals(7, evalBaseForTest("3+4;"));
    }
    
    @Test
    public void testSumWithSpacesShouldWork() throws CalculatorException {
    	assertEquals(7, evalBaseForTest("3 + 4;"));
    }
    
    @Test
    public void testSumWithMoreSpacesShouldWork() throws CalculatorException {
    	assertEquals(7, evalBaseForTest("3  +  4;"));
    }
    
    @Test
    public void testMulShouldWork() throws CalculatorException {
    	assertEquals(12, evalBaseForTest("3*4;"));
    }
    
    @Test
    public void testMulWithSpacesShouldWork() throws CalculatorException {
    	assertEquals(12, evalBaseForTest("3 * 4;"));
    }
    
    @Test
    public void testMulWithMoreSpacesShouldWork() throws CalculatorException {
    	assertEquals(12, evalBaseForTest("3  *  4;"));
    }
    
    @Test
    public void testSubShouldWork() throws CalculatorException {
    	assertEquals(-1, evalBaseForTest("3-4;"));
    }
    
    @Test
    public void testSubWithSpacesShouldWork() throws CalculatorException {
    	assertEquals(-1, evalBaseForTest("3 - 4;"));
    }
    
    @Test
    public void testSubWithMoreSpacesShouldWork() throws CalculatorException {
    	assertEquals(-1, evalBaseForTest("3  -  4;"));
    }
    
    @Test
    public void testDivShouldWork() throws CalculatorException {
    	assertEquals(2, evalBaseForTest("4/2;"));
    }
    
    @Test
    public void testDivWithSpacesShouldWork() throws CalculatorException {
    	assertEquals(2, evalBaseForTest("4 / 2;"));
    }
    
    @Test
    public void testDivWithMoreSpacesShouldWork() throws CalculatorException {
    	assertEquals(2, evalBaseForTest("4  /  2;"));
    }
    
    @Test
    public void testDivByZero() {
    	try {
    		evalBaseForTest("1 / 0;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.DIV_BY_ZERO, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacter() {
    	try {
    		evalBaseForTest("1 a 0;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant1() {
    	try {
    		evalBaseForTest("1 a + 0;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant2() {
    	try {
    		evalBaseForTest("1 + 0 b;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant3() {
    	try {
    		evalBaseForTest("1 + c 0;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant4() {
    	try {
    		evalBaseForTest("1 + a 0 b;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant5() {
    	try {
    		evalBaseForTest("1 c + a 0;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant6() {
    	try {
    		evalBaseForTest("1 c + a 0 b;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant7() {
    	try {
    		evalBaseForTest("1 c + 0 b;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidCharacterVariant8() {
    	try {
    		evalBaseForTest("12c + 10;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_CHARACTER, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidString() throws CalculatorException {
    	try {
    		evalBaseForTest("500 + 500");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_STRING, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testInvalidStringAsEmptyString() throws CalculatorException {
    	try {
    		evalBaseForTest("");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.INVALID_STRING, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testSumShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(70, evalBaseForTest("30+40;"));
    }
    
    @Test
    public void testSumWithSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(70, evalBaseForTest("30 + 40;"));
    }
    
    @Test
    public void testSumWithMoreSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(70, evalBaseForTest("30  +  40;"));
    }
    
    @Test
    public void testMulShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(1200, evalBaseForTest("30*40;"));
    }
    
    @Test
    public void testMulWithSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(1200, evalBaseForTest("30 * 40;"));
    }
    
    @Test
    public void testMulWithMoreSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(1200, evalBaseForTest("30  *  40;"));
    }
    
    @Test
    public void testSubShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(-10, evalBaseForTest("30-40;"));
    }
    
    @Test
    public void testSubWithSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(-10, evalBaseForTest("30 - 40;"));
    }
    
    @Test
    public void testSubWithMoreSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(-10, evalBaseForTest("30  -  40;"));
    }
    
    @Test
    public void testDivShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(2, evalBaseForTest("40/20;"));
    }
    
    @Test
    public void testDivWithSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(2, evalBaseForTest("40 / 20;"));
    }
    
    @Test
    public void testDivWithMoreSpacesShouldWorkMultipleDigits() throws CalculatorException {
    	assertEquals(2, evalBaseForTest("40  /  20;"));
    }
    
    @Test
    public void testDivByZeroMultipleDigits() {
    	try {
    		evalBaseForTest("100 / 0;");
    	} catch(CalculatorException e) {
    		assertEquals(CalculatorFault.DIV_BY_ZERO, e.getCalculatorFault());
    	}
    }
    
    @Test
    public void testGetResultPushable() {
    	Calculator c = new Calculator(new ResultPushable() {
			@Override
			public void onResult(int result) {
				
			}
		});
    	
    	assertNotEquals(null, c.getResultPushable());
    }
}