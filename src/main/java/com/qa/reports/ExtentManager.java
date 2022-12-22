package com.qa.reports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    public static ExtentReports extentReports;
    public synchronized static ExtentReports createExtentReports() {
    	Date date = new Date();
    	String timestamp= date.getTime()+"";
    	extentReports = new ExtentReports("test-output/"+timestamp+"_extent-report.html");        
        return extentReports;
    }
}