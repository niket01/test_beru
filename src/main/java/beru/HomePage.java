package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), 15);

    private By profileButton = By.cssSelector("span.header2-nav-item__icon.header2-nav-item__icon_type_profile");
    private By regionList = By.cssSelector("div.region-suggest__list-item");

   public HomePage(WebDriver driver){
       this.driver = driver;
   }
    @Step("Click loginButton on HomePage")
    public void clickLoginButton(){
        driver.findElement(By.xpath("//span[@title='Войти в аккаунт']")).click();
    }

    @Step("Check visibility of login")
    public void checkVisibilityLogin(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(profileButton)).build().perform();
        Assert.assertEquals(driver.findElement(By.className("header2-user-menu__email")).getText(),
                "nikita.yadchuk@yandex.ru");
    }

    @Step("Check button MyProfile")
    public void checkMyProfile(){
        Assert.assertEquals(driver.findElement(profileButton).getAttribute("title"),"Мой профиль");
    }

    @Step("Click on region button")
    public void clickRegionButton(){
        driver.findElement(By.xpath("//div[@class='layout layout_type_maya']//span[@class='link__inner']")).click();
    }

    @Step("Enter new region")
    public void enterNewRegion(String region){
        driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys(region);
    }

    @Step("Choice")
    public void choiceRegion(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(regionList));
        driver.findElement(regionList).click();
    }

    @Step("Click ContinueWithNewRegionButton")
    public void clickContinueWithNewRegionButton(){
        driver.findElement(By.xpath("//div[@class='header2-region-popup']//button")).click();
    }

    @Step("Check changes in region")
    public void checkChangeRegion(String region){
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='footer b-zone i-bem']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='layout layout_type_maya']" +
                "//span[@class='link__inner']")).getText(), region);
    }

    @Step("Click settings")
    public void clickSettingsButton(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("span.header2-nav-item__icon." +
                "header2-nav-item__icon_type_profile"))).build().perform();
        driver.findElement(By.cssSelector("li.header2-user-menu__item.header2-user-menu__item_type_settings")).click();
    }

    @Step("Click catalog")
    public void clickCatalogButton(){
        driver.findElement(By.cssSelector("div.n-topmenu-new-vertical__left > div > button")).click();
    }

    @Step("Click toothbrush in catalog")
    public void clickToothbrushInCatalog(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.linkText("Бытовая техника"))).build().perform();
        driver.findElement(By.linkText("Зубные щетки и ирригаторы")).click();
    }
}
