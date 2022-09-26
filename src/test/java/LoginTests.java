import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }


    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm("marinas@gmail.com", "Mmarina12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }

    @Test
    public void loginSuccessModel() {
        User user = new User().withEmail("marinas@gmail.com").withPassword("Mmarina12345$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }

    @Test
    public void loginNegativeWrongEmail() {
        User user = new User().withEmail("marinasgmail.com").withPassword("Mmarina12345$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        // Assert errorMessagge
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        // Assert buttonYalla not active
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginNegativeWrongPassword() {
        User user = new User().withEmail("marinas@gmail.com").withPassword("marina12345$");
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Wrong email or password");
        // Assert text message "Authorization error"
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Authorization error");

    }

    @AfterMethod
    public void postCondition(){

        app.getHelperUser().clickOkButton();
    }


}
