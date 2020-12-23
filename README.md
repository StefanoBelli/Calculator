# Calculator
GoF state pattern example

### Little docs

![UML state diagram](CalculatorStates.jpg)

![UML class diagram](CalculatorCD.jpg)

### Clean, compile, run test and code coverage

```
$ mvn clean compile jacoco:prepare-agent test jacoco:report
```

### Main class

Typical main class 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ste.calculator.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Calculator c = new Calculator(new ResultPushable() {
			@Override
			public void onResult(int result) {
				System.out.println(result);
			}
		});
		
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(istream);
		
		while(true) {
			System.out.print("eval> ");
			try {
				c.eval(reader.readLine());
			} catch (CalculatorException e) {
				e.printStackTrace();
			}
		}
		
	}
}
```

