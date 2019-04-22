package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        driver.findElement(By.name("passwd")).sendKeys("Relax-22121");
    }

    /*@Step
    public void skipAdditionalEmail(){
        driver.findElement(By.cssSelector("button.control.button2.button2_view_classic.button2_size_l." +
                    "button2_theme_normal.button2_width_max.passp-form-button")).click();
    }*/

    @Step("Click login")
    public void clickLoginButton(){
        driver.findElement(By.cssSelector("button.control.button2.button2_view_classic.button2_size_l.button2_" +
                "theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
    }

}
