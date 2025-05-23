package winnerXD.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import winnerXD.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReporterObject(); //since this method is static no object creation
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//extract unique threadID and creates a map.
		//No more error in parallel execution
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS,"Test passed");//now when it pull from the map
		//no more mishandling coz we have maped threadId with methodName
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL,"Test failed");
		extentTest.get().fail(result.getThrowable());
		 try {
			 //driver = (WebDriver) result.getInstance().getClass().getSuperclass().getDeclaredField("driver").get(result.getInstance());
			 driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String filePath = null;
		try {
			filePath =  getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	@Override
	public void onStart(ITestContext context) {
	
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
