import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{
    @Test(enabled = false)
    public void searchCurrentMonth(){
        app.getHelperSearch().searchCurrentMonth("Tel Aviv", "10/28/2022", "10/30/2022");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");


    }
    @Test(enabled = false)
    public void searchNextMonth(){
        app.getHelperSearch().searchNextMonth("Haifa", "12/25/2022", "12/30/2022");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");

    }
    @Test(invocationCount = 1, priority = 2)
    public void searchAnyPeriod(){
        app.getHelperSearch().searchAnyPeriod("Tel Aviv", "1/10/2023", "1/15/2023");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");

    }
    @Test(enabled = true, priority = 1)
    public void typePeriodInPast(){
        app.getHelperSearch().typePeriodInPast("Bat Yam", "10/10/2022", "10/15/2022");

        Assert.assertTrue(app.getHelperSearch().IsErrMsgAppear());
        logger.info("ASSERT passed: error message was displayed");
        Assert.assertTrue(app.getHelperSearch().isSubmitBtnNotClickable());
        logger.info("ASSERT passed: submit button not clickable");


    }

}
