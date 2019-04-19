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

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

   /* @Step("Close Advertising")
    public void clickCloseAdvertisingButton(){
        driver.findElement(By.cssSelector("div._3BBUsZVSt0._3pvYcLe0Ew"));
    }*/

    @Step("Click loginButton on HomePage")
    public void clickLoginButton(){
        driver.findElement(By.xpath("//span[@title='Войти в аккаунт']")).click();
    }

    @Step("Check button My Profile")
    public void checkMyProfile(){
        Assert.assertEquals(driver.findElement(By.cssSelector("span.header2-nav-item__icon.header2-nav-item__icon" +
                "_type_profile")).getAttribute("title"),"Мой профиль");
    }

    public void clickCityButton(){
        driver.findElement(By.xpath("//div[@class='layout layout_type_maya']//span[@class='link__inner']")).click();
    }

    public void enterNewRegion(){
        driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys("хвалынск");
    }

    public void choiceRegion(){
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.region-suggest__list-item")));
        driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
    }

    public void clickContinueWithNewRegionButton(){
        driver.findElement(By.xpath("//div[@class='header2-region-popup']//button")).click();
    }

    public void checkChangeRegion(){
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='footer b-zone i-bem']")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='layout layout_type_maya']" +
                "//span[@class='link__inner']")).getText(), "Хвалынск");
    }

    public void clickSettingsButton(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("span.header2-nav-item__icon." +
                "header2-nav-item__icon_type_profile"))).build().perform();
        driver.findElement(By.cssSelector("li.header2-user-menu__item.header2-user-menu__item_type_settings")).click();
    }
}