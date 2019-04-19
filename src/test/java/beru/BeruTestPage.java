package beru;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BeruTestPage {
    private ChromeDriver driver;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Nikita\\Documents\\GitHub\\beru_testing\\chromedriver.exe");
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("https://beru.ru/?ncrnd=3198");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @Test
    public void testLogin(){
        //login account
        HomePage home = new HomePage(driver);
        home.clickLoginButton();
        LoginPage login = new LoginPage(driver);
        login.enterLogin();
        login.clickLoginButton();
        login.enterPassword();
        login.clickLoginButton();

        //check login
        home.checkMyProfile();
    }

    @Test
    public void testChangeCity(){
        //change region
        HomePage home = new HomePage(driver);
        home.clickCityButton();
        home.enterNewRegion();
        home.choiceRegion();
        home.clickContinueWithNewRegionButton();
        home.checkChangeRegion();

        //login account
        home.clickLoginButton();
        LoginPage login = new LoginPage(driver);
        login.enterLogin();
        login.clickLoginButton();
        login.enterPassword();
        login.clickLoginButton();

        //go to settings account
        home.clickSettingsButton();

        //compare region and delivery region
        SettingsPage settings = new SettingsPage(driver);
        settings.checkRegionAndDeliveryRegion();
    }
}
