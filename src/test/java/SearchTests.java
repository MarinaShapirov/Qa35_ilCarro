import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{
    @Test()
    public void searchCurrentMonth(){
        app.getHelperSearch().searchCurrentMonth("Tel Aviv", "10/29/2022", "10/31/2022");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");


    }
    @Test()
    public void searchNextMonth(){
        app.getHelperSearch().searchNextMonth("Haifa", "12/25/2022", "12/30/2022");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");

    }
    @Test()
    public void searchAnyPeriod(){
        app.getHelperSearch().searchAnyPeriod("Tel Aviv", "1/10/2023", "1/15/2023");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");

    }
    @Test(groups = {"smoke_group"})
    public void typePeriodInPast(){
        app.getHelperSearch().typePeriodInPast("Bat Yam", "10/10/2022", "10/15/2022");

        Assert.assertTrue(app.getHelperSearch().IsErrMsgAppear());
        logger.info("ASSERT passed: error message was displayed");
        Assert.assertTrue(app.getHelperSearch().isSubmitBtnNotClickable());
        logger.info("ASSERT passed: submit button not clickable");


    }
    @BeforeMethod(alwaysRun = true)
    public void returnToHomePage(){
        app.getHelperSearch().clickLogo();
    }

}
