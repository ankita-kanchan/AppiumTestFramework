package com.qa.reports;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {
	
	public static String scenarioName="";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();
    public static ExtentReports            extent =  ExtentManager.createExtentReports(); 
    
    //extent =  ExtentManager.createExtentReports(scenarioName);//ExtentReporterNG.extent;
    
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
    	//extent =  ExtentManager.createExtentReports(scenarioName);//ExtentReporterNG.extent; 
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
}