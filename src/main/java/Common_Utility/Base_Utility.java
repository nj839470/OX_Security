package Common_Utility;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Utility implements Config_data_provider, extent_reports_generator, library {

	public static WebDriver driver;
	String configpath = System.getProperty("user.dir") + "\\Config_data\\config.properties";
	public static Logger log;
	public static listner lis;
	public static WebDriverWait wait;
	public static ExtentSparkReporter report;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeTest
	public void getLaunchURL() {
		try {

			driver = new ChromeDriver();
			driver.get(config_getdata("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			log = LogManager.getLogger("OX_Security");
			lis = new listner();
		} catch (Exception e) {
			System.out.println("Problem to launch browser" + e);
		}
	}

	@Override
	public String config_getdata(String key) {
		String value = "";
		try {
			FileInputStream fis = new FileInputStream(configpath);
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);

		} catch (Exception e) {
			System.out.println("Problem in read data from property file" + e);
		}
		return value;
	}

	@Override
	public ExtentReports getreports() {
		String currenttime = new SimpleDateFormat("dd.MM.YYYY.HH.mm.ss").format(new Date());
		String path = System.getProperty("user.dir") + "\\Report\\Test-Report -" + currenttime + ".html";
		report = new ExtentSparkReporter(path);
		report.config().setDocumentTitle("OX Security Test Report");
		report.config().setReportName("OX Security");
		report.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Project Name", "OX Security");
		extent.setSystemInfo("Laptop", "Dell intel core i7");
		extent.setSystemInfo("QA", "Nitesh Kumar");
		extent.setSystemInfo("Operating system", "Windows 10 pro");
		extent.setSystemInfo("BrowserName", "Android Studio");
		return extent;
	}

	@Override
	public void custom_sendkeys(WebElement element, String value, String fieldname) {
		try {
			if (element.isEnabled() || element.isDisplayed() == true) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				element.sendKeys(value);
				test.log(Status.PASS, fieldname + " send successfully =" + value);
				log.info(fieldname + " send successfully");
			}
		} catch (Exception e) {
			test.log(Status.FAIL, fieldname + " is not able to send" + e);
			test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
			log.error(fieldname + " is not able to send");
		}

	}

	@Override
	public void Custom_click(WebElement element, String fieldname) {
		try {
			if (element.isDisplayed() || element.isEnabled() == true) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				test.log(Status.PASS, "Successfully click on = " + fieldname);
				log.info(fieldname + " is clickable");
			}
		} catch (Exception e) {
			test.log(Status.FAIL, fieldname + " Unable To Click = " + e);
			test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
			log.error(fieldname + " is not clickable");

		}

	}

	@Override
	public void mouseOver(WebElement ele, String fieldname) {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(ele).perform();
			test.log(Status.PASS, "Mouse over on = " + fieldname);
			log.info("Mouse over on = " + fieldname);
		} catch (Exception e) {
			test.log(Status.FAIL, "Not able to mouse over on = " + fieldname + e);
			test.addScreenCaptureFromPath(lis.getcapcture(fieldname));
			log.error("Not able to mouse over on = " + fieldname);

		}
	}

	@Override
	public void msg(WebElement ele, String filedname) {
		try {
			if (ele.isDisplayed()) {
				wait = new WebDriverWait(driver, Duration.ofSeconds(12));
				wait.until(ExpectedConditions.visibilityOf(ele));
				String mes = ele.getText();
				test.log(Status.PASS, filedname + mes);
				log.info(filedname + mes);
			}
		} catch (Exception e) {
			test.log(Status.FAIL, filedname + " is not readable" + e);
			test.addScreenCaptureFromPath(lis.getcapcture(filedname));
			log.error(filedname + " is not readable" + e);
		}

	}

	@AfterTest
	public void close_browser() {
		driver.quit();
	}

}
