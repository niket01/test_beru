package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToothbrushesPage {
    private WebDriver driver;

    public ToothbrushesPage(WebDriver driver){
        this.driver = driver;
    }

    @Step
    public void clickElecticToothbrushesButton(){
        driver.findElement(By.linkText("Электрические зубные щетки")).click();
    }
}
