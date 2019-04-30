package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter login")
    public void enterLogin(){
        driver.findElement(By.name("login")).sendKeys("nikita.yadchuk");
    }

    @Step("Enter password")
    public void enterPassword(){
        driver.findElement(By.name("passwd")).sendKeys("Berutest2019");
    }

    @Step("Click login")
    public void clickLoginButton(){
        driver.findElement(By.cssSelector("button.control.button2.button2_view_classic.button2_size_l.button2_" +
                "theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
    }
}






