import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        logger.info("Authorization check");
        if (app.getHelperUser().isLogged()) {
            logger.info("Authorized. Log out...");
            app.getHelperUser().logOut();
        }
        else
            logger.info("Not authorized");
    }

    public void regstrSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withName("Sasha").withLastName("Sasha").withEmail("sa" + i + "@gmail.com").withPassword("Sa12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openRegstrFormFromHeader();
        app.getHelperUser().fillRegstrForm(user);
        app.getHelperUser().CheckPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");
        logger.info("ASSERT passed: 'Registered' msg is appear");
    }

    @Test(dataProvider ="regDataValid", dataProviderClass = DataProviderUser.class)
    public void regstrSuccessDP(User user){
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openRegstrFormFromHeader();
        app.getHelperUser().fillRegstrForm(user);
        app.getHelperUser().CheckPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");
        logger.info("ASSERT passed: 'Registered' msg is appear");
    }

    @Test
    public void regstrWrongEmail(){

        User user = new User().withName("Sasha").withLastName("Sasha").withEmail("sagmail.com").withPassword("Sa12345$");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openRegstrFormFromHeader();
        app.getHelperUser().fillRegstrForm(user);
        app.getHelperUser().CheckPolicyXY();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong email format");
        logger.info("ASSERT passed: 'Wrong email format' is appear");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("ASSERT passed: isYallaButtonNotActive");
    }

    @Test
    public void regstrWrongPsw(){

        User user = new User().withName("Sasha").withLastName("Sasha").withEmail("sa@gmail.com").withPassword("Sa");
        logger.info("User login: " + user.getEmail()+ " " + user.getPassword());

        app.getHelperUser().openRegstrFormFromHeader();
        app.getHelperUser().fillRegstrForm(user);
        app.getHelperUser().CheckPolicyXY();

        String err = app.getHelperUser().getErrorText();
        Assert.assertTrue(err.contains("Password must contain"));
        logger.info("ASSERT passed: error text is appear");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("ASSERT passed: isYallaButtonNotActive");
    }

    @AfterMethod
    public void postCondition(){

        app.getHelperUser().clickOkButton();
    }

}
