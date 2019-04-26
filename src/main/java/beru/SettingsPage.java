package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SettingsPage {
    private WebDriver driver;

    public SettingsPage(WebDriver driver){
        this.driver = driver;
    }

    @Step
    public void checkRegionAndDeliveryRegion(String region){
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='layout layout_type_maya']" +
                "//span[@class='link__inner']")).getText(), region);

        //compare region and delivery region
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='layout layout_type_maya']//span" +
                "[@class='link__inner']")).getText().equals(driver.findElement(By.xpath("//div[@class=" +
                "'settings-list__title']//span[@class='link__inner']")).getText()));
    }
}
