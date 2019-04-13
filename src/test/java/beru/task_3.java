package beru;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class task_3 {
    @Test
    public void execute_task3(){
        chrome.set_driver();
        chrome.get_beru();

        //убрать рекламный банер
        chrome.driver.findElement(By.cssSelector("div._3BBUsZVSt0._3pvYcLe0Ew")).click();

        //перейти в каталог электрических зубных щеток
        chrome.driver.findElement(By.cssSelector("div.n-topmenu-new-vertical__left > div > button")).click();
        Actions actions = new Actions(chrome.driver);
        actions.moveToElement(chrome.driver.findElement(By.linkText("Бытовая техника"))).build().perform();
        chrome.driver.findElement(By.linkText("Зубные щетки и ирригаторы")).click();
        chrome.driver.findElement(By.linkText("Электрические зубные щетки")).click();

        //ввод диапазона цен
        chrome.driver.findElement(By.xpath("//input[@name='Цена от']")).sendKeys("999");
        chrome.driver.findElement(By.xpath("//input[@name='Цена до']")).sendKeys("1999");

        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.visibilityOfElementLocated(
                (By.cssSelector("div.NZiH_Kn8Fj"))));

        WebElement show_more = chrome.driver.findElement(By.xpath("//div[@class='n-pager-more__button " +
                "pager-loader_preload']"));

        //ищем все доступные щетки
        while(show_more.isDisplayed()){
            show_more.click();
            //ждем пока все ценники прогрузятся
            (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.
                    visibilityOfAllElementsLocatedBy(By.cssSelector("div.grid-snippet.grid-snippet_react.b-zone." +
                            "b-spy-visible")));
        }

        List<WebElement> price_list = chrome.driver.findElements(By.xpath("//div[@class='search-result-snippet']" +
                "//span[starts-with(@class, '_1u3j_pk1db _1pTV0mQZJz')]/span[1]"));

        for(int i = 1; i < price_list.size(); i++) {
            int price = Integer.parseInt(price_list.get(i).getText().replaceAll("\\s+",""));
            Assert.assertTrue(price >= 999 && price <= 1999);
        }

        //дообавляем в корзину предпоследнюю зубную щетку
        List<WebElement> item_list = chrome.driver.findElements(By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH." +
                "THqSbzx07u"));
        item_list.get(item_list.size() - 2).click();

        //переходим в корзину
        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.linkText("Перейти в корзину")));
        chrome.driver.findElement(By.linkText("Перейти в корзину")).click();
        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.cssSelector("div._3AlSA6AOKL")));

        //проверить до бесплатной доставки осталось
        WebElement delivery = chrome.driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("До бесплатной доставки"));

        //проверка, что итоговая стоимость равна <цене товара> + <доставка>
        int total_price = Integer.parseInt(chrome.driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\s+|\\D",""));
        int total_item = Integer.parseInt(chrome.driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\s+|\\D",""));
        int total_delivery = Integer.parseInt(chrome.driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-delivery')]//span[2]")).getText().replaceAll("\\s+|\\D",""));
        Assert.assertEquals(total_item + total_delivery, total_price);

        int free_delivery = total_item;
        while(free_delivery < 2999){
            chrome.driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA")).click();
            free_delivery += total_item;
        }

        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='бесплатно']")));

        //проверка на наличие бесплатной доставки
        delivery = chrome.driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("бесплатную доставку"));

        //проверка, что итоговая стоимость равна цене товара, т. к. доставка бесплатна
        total_price = Integer.parseInt(chrome.driver.findElement(By.cssSelector("span._1oBlNqVHPq")).getText().
                replaceAll("\\s+|\\D",""));
        total_item = Integer.parseInt(chrome.driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-items')]//span[2]")).getText().replaceAll("\\s+|\\D",""));
        Assert.assertEquals(total_item, total_price);

        //возврат к исходному состоянию
        chrome.driver.close();
    }
}