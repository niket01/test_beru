package beru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class chrome {
    public static WebDriver driver;

    public static void set_driver() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Nikita\\Documents\\GitHub\\beru_testing\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public static void get_beru(){
        driver.get("https://beru.ru/?ncrnd=3198");
    }

    public static void stop(){
        driver.quit();
    }
}
