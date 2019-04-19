package beru;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class chrome {
    /*public ChromeDriver driver;

    @BeforeTest
    public void set_driver() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Nikita\\Documents\\GitHub\\beru_testing\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("https://beru.ru/?ncrnd=3198");
    }

    @AfterTest
    public void close(){
        driver.quit();
    }*/
}
