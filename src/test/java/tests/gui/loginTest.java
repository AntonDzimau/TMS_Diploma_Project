package tests.gui;

import BaseEntities.BaseTest;
import configuration.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class loginTest extends BaseTest {
    @Test
    public void loginSuccessful(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(ReadProperties.username());
        loginPage.setPsw(ReadProperties.password());
        loginPage.clickLoginButton();
        Assert.assertTrue(new DashboardPage(driver).isPageOpened());
    }

    @Test
    public void loginUnsuccessful(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("username");
        loginPage.setPsw(ReadProperties.password());
        loginPage.clickLoginButton();
/*        Assert.assertEquals(loginPage.getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");*/

        Assert.assertEquals(loginPage.getErrorTextElement().getText()
                , "incorrect email");
    }
}
