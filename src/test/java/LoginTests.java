import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        logger.info("Authorization check");
        if (app.getHelperUser().isLogged()) {
            logger.info("Authorized. Log out...");
            app.getHelperUser().logOut();
        }
        else
            logger.info("Not authorized");
    }


    @Test(dataProvider = "logindata", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String psw) {
        logger.info("User login: " + email +"" + psw);

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(email, psw);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("ASSERT passed: 'Logged in success' msg is appear");
    }



    @Test(groups = {"smoke_group"})
    public void loginSuccessModel() {
        User user = new User().withEmail("marinas@gmail.com").withPassword("Mmarina12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("ASSERT passed: 'Logged in success' msg is appear");
    }

    @Test(dataProvider = "logindataModel", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelDP(User user) {
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("ASSERT passed: 'Logged in success' msg is appear");
    }

    @Test
    public void loginNegativeWrongEmail() {
        User user = new User().withEmail("marinasgmail.com").withPassword("Mmarina12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        // Assert errorMessage
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        logger.info("ASSERT passed: 'It'snot look like email' is appear");
        // Assert buttonYalla not active
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("ASSERT passed: 'isYallaButtonNotActive'");
    }

    @Test
    public void loginNegativeWrongPassword() {
        User user = new User().withEmail("marinas@gmail.com").withPassword("marina12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Wrong email or password");
        logger.info("ASSERT passed: 'Wrong email or password' is appear");
        // Assert text message "Authorization error"
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Authorization error");
        logger.info("ASSERT passed: 'Authorization error' is appear");

    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){

        app.getHelperUser().clickOkButton();
    }


}
