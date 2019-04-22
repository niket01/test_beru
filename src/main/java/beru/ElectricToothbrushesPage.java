package beru;

import io.qameta.allure.Step;
import org.json.JSONArray;
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

    public ElectricToothbrushesPage(WebDriver driver){
        this.driver = driver;
    }

    @Step
    public void enterPriceRange(){
        driver.findElement(By.xpath("//input[@name='Цена от']")).sendKeys("999");
        driver.findElement(By.xpath("//input[@name='Цена до']")).sendKeys("1999");
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated(
                (By.cssSelector("div.NZiH_Kn8Fj"))));
    }

    @Step
    public void openAllToothbrushes(){
        WebElement show_more = driver.findElement(By.xpath("//div[@class='n-pager-more__button " +
                "pager-loader_preload']"));

        while(show_more.isDisplayed()){
            show_more.click();
            //wait while all prices will be load
            (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                    visibilityOfAllElementsLocatedBy(By.cssSelector("div.grid-snippet.grid-snippet_react.b-zone." +
                            "b-spy-visible")));
        }
    }

    @Step
    public void checkPriceRange() {
        /*List<WebElement> price_list = driver.findElements(By.xpath("//div[@class='search-result-snippet']" +
                "//span[starts-with(@class, '_1u3j_pk1db _1pTV0mQZJz')]/span[1]"));*/

        List<WebElement> price_list = driver.findElements(By.cssSelector("div.grid-snippet.grid-snippet_react." +
                "b-zone.b-spy-visible.i-bem.b-spy-visible_js_inited"));

        for(int i = 0; i < price_list.size(); i++) {
            JSONObject obj = new JSONObject(price_list.get(i).getAttribute("data-bem"));
            int price = obj.getJSONObject("grid-snippet").getInt("price");
            /*int price = Integer.parseInt(price_list.get(i).getText().replaceAll("\\D",""));*/
            Assert.assertTrue(price >= 999 && price <= 1999);
        }
    }

    @Step
    public void addToothbrush(){
        List<WebElement> item_list = driver.findElements(By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH." +
                "THqSbzx07u"));
        item_list.get(item_list.size() - 2).click();
    }

    @Step
    public void clickCartButton(){
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.linkText("Перейти в корзину")));
        driver.findElement(By.linkText("Перейти в корзину")).click();
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.cssSelector("div._3AlSA6AOKL")));
    }
}