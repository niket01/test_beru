package beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SettingsPage {
    private WebDriver driver;

    public SettingsPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkRegionAndDeliveryRegion(){
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='layout layout_type_maya']" +
                "//span[@class='link__inner']")).getText(), "Хвалынск");

        //compare region and delivery region
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='layout layout_type_maya']//span" +
                "[@class='link__inner']")).getText().equals(driver.findElement(By.xpath("//div[@class=" +
                "'settings-list__title']//span[@class='link__inner']")).getText()));
    }
}
