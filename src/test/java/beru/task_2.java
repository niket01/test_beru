package beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class task_2 {
    @Test
    public void execute_task2() {
        /*chrome.set_driver();
        chrome.get_beru();

        //убрать рекламный банер
        chrome.driver.findElement(By.cssSelector("div._3BBUsZVSt0._3pvYcLe0Ew")).click();

        //меняем город
        chrome.driver.findElement(By.xpath("//div[@class='layout layout_type_maya']" +
                "//span[@class='link__inner']")).click();
        chrome.driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys("хвалынск");
        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.region-suggest__list-item")));
        chrome.driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
        chrome.driver.findElement(By.xpath("//div[@class='header2-region-popup']//button")).click();

        //проверяем изменился ли город
        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='footer b-zone i-bem']")));
        Assert.assertEquals(chrome.driver.findElement(By.xpath("//div[@class='layout layout_type_maya']" +
                "//span[@class='link__inner']")).getText(), "Хвалынск");

        //вход в аккаунт
        Account.login();

        //переход в настройки
        chrome.driver.get("https://beru.ru/my/settings?track=menu");
        Assert.assertEquals(chrome.driver.findElement(By.xpath("//div[@class='layout layout_type_maya']" +
                "//span[@class='link__inner']")).getText(), "Хвалынск");

        //сравнение местоположения с городом доставки
        Assert.assertTrue(chrome.driver.findElement(By.xpath("//div[@class='layout layout_type_maya']//span" +
                "[@class='link__inner']")).getText().equals(chrome.driver.findElement(By.xpath("//div[@class=" +
                "'settings-list__title']//span[@class='link__inner']")).getText()));

        //возврат к исходному состоянию
        chrome.driver.get("https://beru.ru/logout?retpath=https://beru.ru/?ncrnd=3082..");
        chrome.driver.close();*/
    }
}