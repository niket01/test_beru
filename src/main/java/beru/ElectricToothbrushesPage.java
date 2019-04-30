package beru;

import io.qameta.allure.Step;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ElectricToothbrushesPage {
    private WebDriver driver;
    private WebDriverWait wait = new WebDriverWait(DriverClass.getDriver(), 15);

    public ElectricToothbrushesPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter price range")
    public void enterPriceRange(){
        driver.findElement(By.xpath("//input[@name='Цена от']")).sendKeys("999");
        driver.findElement(By.xpath("//input[@name='Цена до']")).sendKeys("1999");
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div.NZiH_Kn8Fj"))));
    }

    @Step("Open all items")
    public void openAllToothbrushes(){
        WebElement showMoreButton = driver.findElement(By.xpath("//div[@class='n-pager-more__button " +
                "pager-loader_preload']"));

        while(showMoreButton.isDisplayed()){
            showMoreButton.click();
            //wait while all prices will be load
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector
                    ("div.grid-snippet.grid-snippet_react.b-zone.b-spy-visible")));
        }
    }

    @Step("Check price range")
    public void checkPriceRange() {
        List<WebElement> toothbrushList = driver.findElements(By.cssSelector("div.grid-snippet.grid-snippet_react." +
                "b-zone.b-spy-visible.i-bem.b-spy-visible_js_inited"));

        for(int i = 0; i < toothbrushList.size(); i++) {
            JSONObject obj = new JSONObject(toothbrushList.get(i).getAttribute("data-bem"));
            int price = obj.getJSONObject("grid-snippet").getInt("price");
            Assert.assertTrue(price >= 999 && price <= 1999);
        }
    }

    @Step("Add toothbrush")
    public void addToothbrush(){
        List<WebElement> cartButtonList = driver.findElements(By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH." +
                "THqSbzx07u"));
        cartButtonList.get(cartButtonList.size() - 2).click();
    }

    @Step("Click on cart button")
    public void clickCartButton(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText("Перейти в корзину")));
        driver.findElement(By.linkText("Перейти в корзину")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div._3AlSA6AOKL")));
    }
}