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
    private WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), 15);

    private By totalPriceText = By.cssSelector("span._1oBlNqVHPq");
    private By totalItemText = By.xpath("//div[contains(@data-auto, 'total-items')]//span[2]");
    private By deliveryText = By.cssSelector("span._3EX9adn_xp");
    private By cartInformation = By.cssSelector("div._1n63a5bOO8");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Check free delivery")
    public void checkDelivery(){
        WebElement delivery = driver.findElement(deliveryText);
        Assert.assertTrue(delivery.getText().contains("До бесплатной доставки"));
    }

    @Step("Check total price")
    public int checkTotalPrice(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartInformation));
        int totalPrice = Integer.parseInt(driver.findElement(totalPriceText).getText().
                replaceAll("\\D",""));
        int totalItem = Integer.parseInt
                (driver.findElement(totalItemText).getText().replaceAll("\\D",""));
        int totalDelivery = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-delivery')]//span[2]")).getText().replaceAll("\\D",""));
        Assert.assertEquals(totalItem + totalDelivery, totalPrice);
        return totalItem;
    }

    @Step("Add items in cart")
    public void addItemsForFreeDelivery(int totalItem){
        int freeDelivery = totalItem;
        while(freeDelivery <= 2999){
            driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA")).click();
            freeDelivery += totalItem;
        }
    }

    @Step("Check free delivery")
    public void checkFreeDelivery(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='бесплатно']")));
        WebElement delivery = driver.findElement(deliveryText);
        Assert.assertTrue(delivery.getText().contains("бесплатную доставку"));
    }

    @Step("Check total price with free delivery")
    public void checkTotalPriceWithFreeDelivery(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartInformation));
        int totalPrice = Integer.parseInt(driver.findElement(totalPriceText).getText().
                replaceAll("\\D",""));
        int totalItem = Integer.parseInt
                (driver.findElement(totalItemText).getText().replaceAll("\\D",""));
        Assert.assertEquals(totalItem, totalPrice);
    }
}
