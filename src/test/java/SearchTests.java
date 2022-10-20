import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{
    @Test
    public void searchCurrentMonth(){
        app.getHelperSearch().searchCurrentMonth("Tel Aviv", "10/25/2022", "10/30/2022");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");
    }
    @Test
    public void searchNextMonth(){
        app.getHelperSearch().searchNextMonth("Tel Aviv", "12/25/2022", "12/30/2022");
        app.getHelperSearch().submit();
        Assert.assertTrue(app.getHelperSearch().carsSearchRes());
        logger.info("ASSERT passed: 'Car count > 0");

    }
}
