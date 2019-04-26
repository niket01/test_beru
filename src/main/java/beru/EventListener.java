package beru;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class EventListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        takeScreenShot();
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, java.lang.CharSequence[] keysToSend){
        takeScreenShot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] takeScreenShot(){
        return ((TakesScreenshot) DriverClass.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
