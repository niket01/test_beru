package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    @Step
    public void checkDelivery(){
        WebElement delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("До бесплатной доставки"));
    }

    @Step
    public int checkTotalPrice(){
        int totalPrice = Integer.parseInt(driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\D",""));
        int totalItem = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\D",""));
        int totalDelivery = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-delivery')]//span[2]")).getText().replaceAll("\\D",""));
        Assert.assertEquals(totalItem + totalDelivery, totalPrice);
        return totalItem;
    }

    @Step
    public void addItemsForFreeDelivery(int totalItem){
        int freeDelivery = totalItem;
        while(freeDelivery <= 2999){
            driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA")).click();
            freeDelivery += totalItem;
        }
    }

    @Step
    public void checkFreeDelivery(){
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='бесплатно']")));

        WebElement delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("бесплатную доставку"));
    }

    @Step
    public void checkTotalPriceWithFreeDelivery(){
        int totalPrice = Integer.parseInt(driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\D",""));
        int totalItem = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\D",""));
        Assert.assertEquals(totalItem, totalPrice);
    }
}
