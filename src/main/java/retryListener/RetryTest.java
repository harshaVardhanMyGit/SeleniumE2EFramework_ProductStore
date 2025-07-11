package retryListener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{
	int count =0;
	int maxTry = 2;
	//how many times you want to repeat is the max try
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry)
		{
			//count less than 1 for first time so it will return true
			//if it returns true that means the loop will repeat again
			//then 1<1 so condition is wrong
			//it returns false now 
			//then it will goes out of loop
			count++;
			return true;
		}
		return false;
	}
	
}