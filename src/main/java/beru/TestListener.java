package beru;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result){
        saveScreenShot(result);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenShot(ITestResult result){
        return ((TakesScreenshot) DriverClass.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
