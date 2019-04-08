package beru;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class task_1 {
    @Test
    public void execute_task1(){
        chrome.set_driver();
        chrome.get_beru();
        //убрать рекламный банер
        chrome.driver.findElement(By.cssSelector("div._3BBUsZVSt0._3pvYcLe0Ew")).click();
        //вход на сайт
        Account.login();
        chrome.driver.get("https://beru.ru/logout?retpath=https%3A%2F%2Fberu.ru%2F%3Fncrnd%3D3082%26loggedin%3D1");
        chrome.driver.close();
    }
}
