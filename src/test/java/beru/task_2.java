package beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class task_2 {
    @Test
    public void execute_task2(){
        chrome.set_driver();
        chrome.get_beru();
        //убрать рекламный банер
        chrome.driver.findElement(By.cssSelector("div._3BBUsZVSt0._3pvYcLe0Ew")).click();
        //меняем город
        chrome.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/noindex/" +
                "div/div[1]/div/div/div[1]/span/span[2]/span")).click();
        chrome.driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys("хвалынск");
        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.region-suggest__list-item")));
        chrome.driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
        chrome.driver.findElement(By.xpath("/html/body/div[8]/div/div/div[1]/div[1]/form/div/button")).click();
        //проверяем изменился ли город
        (new WebDriverWait(chrome.driver, 15)).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("/html/body/div[1]/div/div[2]/div[2]/noindex/" +
                        "div/div[1]/div/div/div[1]/span/span[2]/span/span")));
        Assert.assertEquals(chrome.driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/noindex/" +
                "div/div[1]/div/div/div[1]/span/span[2]/span/span")).getText(),"Хвалынск");
        //вход в аккаунт
        Account.login();
        //переход в настройки
        chrome.driver.get("https://beru.ru/my/settings?track=menu");
        Assert.assertEquals(chrome.driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/noindex/" +
                "div/div[1]/div/div/div[1]/span/span[2]/span/span")).getText(),"Хвалынск");
        Assert.assertTrue(chrome.driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/noindex/" +
                "div/div[1]/div/div/div[1]/span/span[2]/span/span")).getText().equals(chrome.driver.findElement
                (By.xpath("//*[@id='region']/div/div/h2/span")).getText()));
        //выход из аккаунт
        chrome.driver.get("https://beru.ru/logout?retpath=https%3A%2F%2Fberu.ru%2F%3Fncrnd%3D3082%26loggedin%3D1");
        chrome.driver.close();
    }
}