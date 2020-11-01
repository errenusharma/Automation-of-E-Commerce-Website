package com.automation.test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
 
@Override
public void onTestStart(ITestResult result) {
	System.out.println(result.getName()+ "Test started");
	
}
@Override
public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub
	
}
@Override
public void onTestFailure(ITestResult result) {
	System.out.println("Name of failed test is : "+ result.getName());
	
}
@Override
public void onTestSkipped(ITestResult result) {
	System.out.println("Name of skipped test is : "+ result.getName());
	
}
@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	System.out.println("Test Case with %");
	
}
@Override
public void onStart(ITestContext context) {
	System.out.println("Test Started : "+ context.getName());
	
}
@Override
public void onFinish(ITestContext context) {
	System.out.println("Test Finished: "+ context.getName());
	
}
}
