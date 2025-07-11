package resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    public static ExtentReports getReport() {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String path = System.getProperty("user.dir") + "//reports//" + dateName + "extentreport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        // ExtentSparkReporter is used to create a HTML report
        // It accepts file as a parameter
        // The file represents the path at where we want our report to be placed
        reporter.config().setReportName("Test Report");
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "HarshaVardhan");
        extent.createTest(path);
        return extent;
    }

}