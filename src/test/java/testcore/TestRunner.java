package testcore;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestSuite.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.print(failure.toString());
			
		}
		System.out.print(result.wasSuccessful());
		System.out.print("top");
	}
	
}
