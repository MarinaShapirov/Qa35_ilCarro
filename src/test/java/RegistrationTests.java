import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logOut();
    }

    @Test
    public void regstrSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withName("Sasha").withLastName("Sasha").withEmail("sa" + i + "@gmail.com").withPassword("Sa12345$");
        app.getHelperUser().openRegstrFormFromHeader();
        app.getHelperUser().fillRegstrForm(user);
        app.getHelperUser().CheckPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Registered");
    }

    @Test
    public void regstrWrongEmail(){

        User user = new User().withName("Sasha").withLastName("Sasha").withEmail("sagmail.com").withPassword("Sa12345$");
        app.getHelperUser().openRegstrFormFromHeader();
        app.getHelperUser().fillRegstrForm(user);
        app.getHelperUser().CheckPolicyXY();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "Wrong email format");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void regstrWrongPsw(){

        User user = new User().withName("Sasha").withLastName("Sasha").withEmail("sa@gmail.com").withPassword("Sa");
        app.getHelperUser().openRegstrFormFromHeader();
        app.getHelperUser().fillRegstrForm(user);
        app.getHelperUser().CheckPolicyXY();
        String err = app.getHelperUser().getErrorText();
        Assert.assertTrue(err.contains("Password must contain"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @AfterMethod
    public void postCondition(){

        app.getHelperUser().clickOkButton();
    }

}
