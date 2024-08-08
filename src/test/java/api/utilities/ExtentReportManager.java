package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentTest test;
    public ExtentReports extent;

    String repName;
    private int testCaseNumber = 0;

    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
        repName = "Test-Report-" + timeStamp + ".html"; // report name
        //sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName); // specify location
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/reports/" + repName); // specify location
        sparkReporter.config().setDocumentTitle("API Test Automation ReportDocumentTitle"); // title of report
        sparkReporter.config().setReportName("API Test Automation ReportName"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK); // set the theme

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Host Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "John Doe");
        extent.setSystemInfo("OS", "Windows 10");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest("Test Case " + (testCaseNumber + " - ") + result.getName()); // create new entry in the
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName()); // create new node in the report
        test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
        testCaseNumber++;
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest("Test Case " + (testCaseNumber + " - ") + result.getName()); // create new entry in the
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName()); // create new node in the report
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
        testCaseNumber++;
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest("Test Case " + (testCaseNumber + " - ") + result.getName()); // create new entry in the
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName()); // create new node in the report
        test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
        test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getThrowable());
        testCaseNumber++;
    }

    public void onFinish(ITestContext context) {
        extent.flush(); // close the report
    }


}
