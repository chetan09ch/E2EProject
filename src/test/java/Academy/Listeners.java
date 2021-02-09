package Academy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.BaseClass;
import resources.ExtentReporterNG;

public class Listeners extends BaseClass implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal <ExtentTest> extentTest=new ThreadLocal <ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		  //test=extent.createTest("Initial Test");
		  // this should not be hard coded & test object must be global so that we can use it anywhere just like screenshot method
		  test=extent.createTest(result.getMethod().getMethodName());
		  extentTest.set(test);
		}
	public void onTestSuccess(ITestResult arg0) {
		//test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//Screenshot 
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		WebDriver driver = null;
		String testMethodName=result.getMethod().getMethodName();
		
		// How to access Test case driver
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch (Exception e) {
			
		}
		try {
			//get the path of SS, get Name of TC & logs & added to report
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), result.getMethod().getMethodName());
			//getScreenShotPath(testMethodName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void onFinish(ITestContext arg0) {
	   extent.flush();
		
	}

	public void onStart(ITestContext arg0) {

		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
