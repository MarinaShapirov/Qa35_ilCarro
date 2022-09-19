import org.testng.Assert;
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
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marinas@gmail.com", "Mmarina12345$");
        //app.getHelperUser().pause(2000);
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isSuccLoginMsgAppear());

    }


}
