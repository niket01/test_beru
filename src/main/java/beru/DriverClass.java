package beru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public class DriverClass {
    private static EventFiringWebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod
    public void setUp(){
        EventListener ei = new EventListener();
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Nikita\\Documents\\GitHub\\beru_testing\\chromedriver.exe");
        ChromeDriver chDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(chDriver);
        driver.register(ei);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://beru.ru/?ncrnd=3198");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
