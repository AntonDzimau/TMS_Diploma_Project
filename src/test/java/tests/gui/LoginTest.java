package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void loginSuccessful() {
        Assert.assertTrue(
                loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password())
                        .isPageOpened());
    }

    @Test
    public void loginUnsuccessful() {
        Assert.assertEquals(
                loginStep.loginIncorrect("some name", ReadProperties.password())
                        .getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");
    }

    @Test
    public void loginUnsuccessfulWrongPassword() {
        Assert.assertEquals(
                loginStep.loginIncorrect(ReadProperties.username(), "some text")
                        .getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");
    }

    @Test
    public void loginUnsuccessfulEmptyLogin() {
        Assert.assertEquals(
                loginStep.loginIncorrect("", ReadProperties.password()).
                        getErrorTextNoLoginLocator().getText()
                , "Email/Login is required.");
    }

    @Test
    public void loginUnsuccessfulEmptyPassword() {
        Assert.assertEquals(
                loginStep.loginIncorrect(ReadProperties.password(), "").
                        getErrorTextNoPasswordLocator().getText()
                , "Password is required.");
    }
}
