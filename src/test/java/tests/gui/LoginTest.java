package tests.gui;

import BaseEntities.BaseTest;
import configuration.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test
    public void loginSuccessful(){
        loginStep.loginSuccessful(ReadProperties.username(),ReadProperties.password());
        Assert.assertTrue(new DashboardPage(driver).isPageOpened());
    }

    @Test
    public void loginUnsuccessful(){
        loginStep.loginIncorrect("some name",ReadProperties.password());
        Assert.assertTrue(new DashboardPage(driver).isPageOpened());
    }
    @Test
    public void someTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("username");
        loginPage.setPsw(ReadProperties.password());
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");
    }
}
