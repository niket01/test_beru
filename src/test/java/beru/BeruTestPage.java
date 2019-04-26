package beru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class})
public class BeruTestPage {
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
        home.clickRegionButton();
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

    @Test
    public void testBuyToothbrush(){
        //go to toothbrushes page in catalog
        HomePage home = new HomePage(driver);
        home.clickCatalogButton();
        home.clickToothbrushInCatalog();

        //go to electric toothbrushes page
        ToothbrushesPage toothbrush = new ToothbrushesPage(driver);
        toothbrush.clickElecticToothbrushesButton();

        //enter the price range and check price range
        ElectricToothbrushesPage electric = new ElectricToothbrushesPage(driver);
        electric.enterPriceRange();
        electric.openAllToothbrushes();
        electric.checkPriceRange();

        //add toothbrush and go to cart
        electric.addToothbrush();
        electric.clickCartButton();

        //check free delivery and total price
        CartPage cart = new CartPage(driver);
        cart.checkDelivery();
        int totalPrice = cart.checkTotalPrice();
        cart.addItemsForFreeDelivery(totalPrice);
        cart.checkFreeDelivery();
        cart.checkTotalPriceWithFreeDelivery();
    }
}
