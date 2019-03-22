package beru;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class Account {
    public static void login(){
        //вход в аккаунт
        chrome.driver.findElement(By.className("header2-nav-item__icon-wrap")).click();
        chrome.driver.findElement(By.name("login")).sendKeys("nikita.yadchuk");
        chrome.driver.findElement(By.cssSelector("button.control.button2.button2_view_classic.button2_size_l." +
                "button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
        chrome.driver.findElement(By.name("passwd")).sendKeys("Relax-22121");
        chrome.driver.findElement(By.cssSelector("button.control.button2.button2_view_classic.button2_size_l." +
                "button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();

        //если появилось поле с адресом дополнительной почты
        try{
            //проверка имени личного кабинета
            chrome.driver.findElement(By.className("header2-nav-item__text"));
        }
        //скипаем ввод дополнительной почты
        catch(NoSuchElementException e){
            chrome.driver.findElement(By.cssSelector("button.control.button2.button2_view_classic.button2_" +
                    "size_l.button2_theme_normal.button2_width_max.passp-form-button")).click();
        }
        finally{
            Assert.assertEquals(chrome.driver.findElement(By.cssSelector("span.header2-nav-item__icon." +
                            "header2-nav-item__icon_type_profile")).getAttribute("title"),"Мой профиль");
        }
    }
}