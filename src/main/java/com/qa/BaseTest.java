package com.qa;


import static com.qa.reports.ExtentTestManager.getTest;
import com.qa.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {
	protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	
	private static AppiumDriverLocalService server;
	TestUtils utils = new TestUtils();
	
	  public AppiumDriver getDriver() {
		  return driver.get();
	  }
	  
	  public void setDriver(AppiumDriver driver2) {
		  driver.set(driver2);
	  }
	  
	  public Properties getProps() {
		  return props.get();
	  }
	  
	  public void setProps(Properties props2) {
		  props.set(props2);
	  }
	  
	  public HashMap<String, String> getStrings() {
		  return strings.get();
	  }
	  
	  public void setStrings(HashMap<String, String> strings2) {
		  strings.set(strings2);
	  }
	  
	 
	  public String getDateTime() {
		  return dateTime.get();
	  }
	  
	  public void setDateTime(String dateTime2) {
		  dateTime.set(dateTime2);
	  }
	  
	 
	
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		
		((CanRecordScreen) getDriver()).startRecordingScreen();
	}
	
	//stop video capturing and create *.mp4 file
	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {
		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
		
		Map <String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();		
		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName") 
		+ File.separator + getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName();
		
		File videoDir = new File(dirPath);
		
		synchronized(videoDir){
			if(!videoDir.exists()) {
				videoDir.mkdirs();
			}	
		}
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
			stream.close();
			utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
		} catch (Exception e) {
			utils.log().error("error during video capture" + e.toString());
		} finally {
			if(stream != null) {
				stream.close();
			}
		}		
	}
	
	
	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
	    boolean isAppiumServerRunning = false;
	    ServerSocket socket;
	    try {
	        socket = new ServerSocket(port);
	        socket.close();
	    } catch (IOException e) {
	    	System.out.println("1");
	        isAppiumServerRunning = true;
	    } finally {
	        socket = null;
	    }
	    return isAppiumServerRunning;
	}
	
	@AfterSuite
	public void afterSuite() {
		//server.stop();
		utils.log().info("Appium server stopped");
	}
	
	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}
	
	public AppiumDriverLocalService getAppiumService() {
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH", "\\Users\\ankit\\AppData\\Local\\Android\\Sdk:Users\\ankit\\AppData\\Local\\Android\\Sdk\\platform-tools" + System.getenv("PATH"));
		environment.put("ANDROID_HOME", "/Users/ankit/AppData/Local/Android/Sdk");
		return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\nodejs/"))
				.withAppiumJS(new File("\\Users\\ankit\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4723)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withEnvironment(environment)
				.withLogFile(new File("ServerLogs/server.log")));
	}
	
  
  @BeforeTest
  public void beforeTest() throws Exception {
	  
	  
	  Properties props = new Properties();
	  setDateTime(utils.dateTime());
	  
	  
	    URL url;
		InputStream inputStream = null;
		InputStream stringsis = null;
		
		AppiumDriver driver;
		
		String strFile = "logs" + File.separator + "_" + props.getProperty("deviceName");
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		//route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);
		
	  try {
		  props = new Properties();
		  String propFileName = "config.properties";
		  String xmlFileName = "strings/strings.xml";
		  
		  utils.log().info("load " + propFileName);
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  setProps(props);
		  
		  utils.log().info("load " + xmlFileName);
		  stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		  setStrings(utils.parseStringXML(stringsis));
		  
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("deviceName", props.getProperty("deviceName"));
			desiredCapabilities.setCapability("udid",props.getProperty("udid"));
			url = new URL(props.getProperty("appiumURL"));
			
			
				desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));	
				desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
				desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));
				
				//String androidAppUrl = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
				//utils.log().info("appUrl is" + androidAppUrl);
				//desiredCapabilities.setCapability("app", androidAppUrl);

				driver = new AndroidDriver(url, desiredCapabilities);
			
				
			
			setDriver(driver);
			utils.log().info("driver initialized: " + driver);
	  } catch (Exception e) {
		  utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
		  throw e;
	  } finally {
		  if(inputStream != null) {
			  inputStream.close();
		  }
		  if(stringsis != null) {
			  stringsis.close();
		  }
	  }
  }
  
  public void waitForVisibility(MobileElement e) {
	  WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
	  wait.until(ExpectedConditions.visibilityOf(e));
  }
  
  public void waitForVisibility(WebElement e){
	  Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
	  .withTimeout(Duration.ofSeconds(30))
	  .pollingEvery(Duration.ofSeconds(5))
	  .ignoring(NoSuchElementException.class);
	  
	  wait.until(ExpectedConditions.visibilityOf(e));
	  }
  
  public void clear(MobileElement e) {
	  waitForVisibility(e);
	  e.clear();
  }
  
  public void click(MobileElement e) {
	  waitForVisibility(e);
	  e.click();
  }
 
  public void click(MobileElement e, String msg) {
	  waitForVisibility(e);
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO,"Click Perform on "+e.getText(), msg);
	  e.click();
  }
  public void click(MobileElement e, String msg,String element) {
	  waitForVisibility(e);
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO,"Click Perform on "+element, msg);
	  e.click();
  }
  
  public void sendKeys(MobileElement e, String txt) {
	  waitForVisibility(e);
	  e.sendKeys(txt);
  }
  
  public void sendKeys(MobileElement e, String txt, String msg) {
	  System.out.println("my elment "+e.toString());
	  waitForVisibility(e);
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO, "Entered Value "+e.getTagName(),msg);
	  e.sendKeys(txt);
	  
  }
  public void sendKeys(MobileElement e, String txt, String msg,String element) {
	  System.out.println("my elment "+e.toString());
	  waitForVisibility(e);
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO, "Entered Value "+element,msg);
	  e.sendKeys(txt);
  }
  public void selectItem(MobileElement e, String item, String msg,String element) {
	  
	  waitForVisibility(e);
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO, "Selecting Value "+element,msg);
	  e.click();
	  WebElement i=getDriver().findElement(By.xpath("//*[@text='"+item+"']"));
	  i.click();
	 
	  
  }
 public void dynamicListItem(String item, String msg,String element) {
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO, "Selecting Value "+element,msg);
	  WebElement i=getDriver().findElement(By.xpath("//*[@text='"+item+"']"));
	  i.click();
  }
 public void sendRequestDynamic(String item, String msg) {
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO, "Sending Join Tournament Request to "+item,msg);
	  WebElement i=getDriver().findElement(By.xpath("\"(//*[@id='recycler']/*/*/*[@id='iv_more_option'])[1]\""));
	  i.click();
 } 
 
 public void selectBracketList(String item, String msg) {
		  utils.log().info(msg);
		  getTest().log(LogStatus.INFO, "Selecting Bracket "+item,msg);
		  WebElement i=getDriver().findElement(By.xpath("//*[@id='iv_more_option'])[1]\""));
		  i.click();
	} 
 
  public String getAttribute(MobileElement e, String attribute) {
	  waitForVisibility(e);
	  return e.getAttribute(attribute);
  }
  
  public String getText(MobileElement e, String msg) {
	  String txt = null;
	  
	  txt = getAttribute(e, "text");
		 
	  utils.log().info(msg + txt);
	  //getTest().log(LogStatus.INFO,"", msg + txt);
	  return txt;
  }
  
  public void scrollAndClick(String item,String msg) {
	  utils.log().info(msg);
	  getTest().log(LogStatus.INFO, "Sending Request to "+item,msg);
	  WebElement element = getDriver().findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+item+"\").instance(0))"));
	  element.click();
  }  
  public String getToast() {
		
		String  toast = getDriver().findElement(By.xpath("/hierarchy/android.widget.Toast")).getText();
		
		return toast;
	}

  public void closeApp() {
	  ((InteractsWithApps) getDriver()).closeApp();
  }
  
  public void launchApp() {
	  ((InteractsWithApps) getDriver()).launchApp();
  }
  
  public MobileElement scrollToElement() {	  
		return (MobileElement) ((FindsByAndroidUIAutomator) getDriver()).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
						+ "new UiSelector().description(\"test-Price\"));");
  }
  

  public static WaitOptions waitOptions(Duration duration) {
	  return new WaitOptions().withDuration(duration);
	}
  @AfterTest
  public void afterTest() {
	  getDriver().quit();
  }
}
