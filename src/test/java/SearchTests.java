import org.testng.annotations.Test;

public class SearchTests extends TestBase{
    @Test
    public void searchCurrentMonth(){
        app.getHelperSearch().searchCurrentMonth("Tel Aviv", "10/25/2022", "10/30/2022");
        app.getHelperSearch().submit();
    }
    @Test
    public void searchNextMonth(){
        app.getHelperSearch().searchNextMonth("Tel Aviv", "11/25/2022", "11/30/2022");
        app.getHelperSearch().submit();

    }
}
