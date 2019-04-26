package beru;

import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BeruTestPage extends DriverClass {

    @DataProvider(name = "TestChangeRegion")
    public Object[][] createData(){
        return new Object[][]{
                {"Хвалынск"},
                {"Саратов"}
        };
    }

    @Test
    public void testLogin(){
        //login account
        HomePage home = new HomePage(getDriver());
        home.clickLoginButton();
        LoginPage login = new LoginPage(getDriver());
        login.enterLogin();
        login.clickLoginButton();
        login.enterPassword();
        login.clickLoginButton();

        //check login
        home.checkMyProfile();
    }

    @Test(dataProvider = "TestChangeRegion")
    public void testChangeRegion(String region){
        //change region
        HomePage home = new HomePage(getDriver());
        home.clickRegionButton();
        home.enterNewRegion(region);
        home.choiceRegion();
        home.clickContinueWithNewRegionButton();
        home.checkChangeRegion(region);

        //login account
        home.clickLoginButton();
        LoginPage login = new LoginPage(getDriver());
        login.enterLogin();
        login.clickLoginButton();
        login.enterPassword();
        login.clickLoginButton();

        //go to settings account
        home.clickSettingsButton();

        //compare region and delivery region
        SettingsPage settings = new SettingsPage(getDriver());
        settings.checkRegionAndDeliveryRegion(region);
    }

    @Test
    public void testBuyToothbrush(){
        //go to toothbrushes page in catalog
        HomePage home = new HomePage(getDriver());
        home.clickCatalogButton();
        home.clickToothbrushInCatalog();

        //go to electric toothbrushes page
        ToothbrushesPage toothbrush = new ToothbrushesPage(getDriver());
        toothbrush.clickElecticToothbrushesButton();

        //enter the price range and check price range
        ElectricToothbrushesPage electric = new ElectricToothbrushesPage(getDriver());
        electric.enterPriceRange();
        electric.openAllToothbrushes();
        electric.checkPriceRange();

        //add toothbrush and go to cart
        electric.addToothbrush();
        electric.clickCartButton();

        //check free delivery and total price
        CartPage cart = new CartPage(getDriver());
        cart.checkDelivery();
        int totalPrice = cart.checkTotalPrice();
        cart.addItemsForFreeDelivery(totalPrice);
        cart.checkFreeDelivery();
        cart.checkTotalPriceWithFreeDelivery();
    }
}
