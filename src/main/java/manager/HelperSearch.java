package manager;

import com.sun.jdi.IntegerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        typeDate(dateFrom);
        typeDate(dateTo);
    }

    public void searchNextMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
        //set month
        String[] dFrom = dateFrom.split("/");
        String[] dTo = dateTo.split("/");
        if(dFrom[0].equals(dTo[0]))
            changeMonth(Integer.valueOf(dFrom[0]));
        //set days of month
        String locator = String.format("//div[text()= ' %s ']", dFrom[1]);
        click(By.xpath(locator));
        locator = String.format("//div[text()= ' %s ']", dTo[1]);
        click(By.xpath(locator));
    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));
        pause(500);
    }

    private void typeDate(String date){
        String[] d = date.split("/");
        //String locator = "//div[text()= ' "+ d[1] + " ']";
        String locator = String.format("//div[text()= ' %s ']", d[1]);
        click(By.xpath(locator));
    }

    private void changeMonth(int month) {
        LocalDate ld = LocalDate.now();
        int mm = ld.getMonthValue();
        int diff = month - mm;
        if(diff>0)
            for(int i=0; i<diff; i++)
                click(By.cssSelector("button[aria-label = 'Next month']"));
    }
}
