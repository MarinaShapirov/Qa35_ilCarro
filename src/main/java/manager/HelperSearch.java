package manager;

import com.sun.jdi.IntegerType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }
    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        //clear dates text box
        clearDatesTextBox();
        //call date-picker
        click(By.id("dates"));
        //enter dates
        typeDate(dateFrom);
        typeDate(dateTo);

    }

     public void searchNextMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        //clear dates
         WebElement el = wd.findElement(By.id("dates"));
         clearDatesTextBox();
        //call date-picker
        click(By.id("dates"));
        //enter dates
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

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        int diffY, diffM;

        typeCity(city);
        //clear 'dates'
        clearDatesTextBox();
        //call date-picker
        click(By.id("dates"));
        //enter dates
        LocalDate ld     = LocalDate.now();
        LocalDate ldFrom = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate ldTo   = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        //enter dateFrom
        diffY = ldFrom.getYear() - ld.getYear();
        if(diffY==0)
            diffM = ldFrom.getMonthValue() - ld.getMonthValue();
        else
            diffM = 12 - ld.getMonthValue() + ldFrom.getMonthValue();
        clickNextMonth(diffM);
        String locator = String.format("//div[text()= ' %s ']", ldFrom.getDayOfMonth());
        click(By.xpath(locator));

        //enter dateTo
        diffY = ldTo.getYear() - ldFrom.getYear();
        if(diffY==0)
            diffM = ldTo.getMonthValue() - ldFrom.getMonthValue();
        else
            diffM = 12 - ldFrom.getMonthValue() + ldTo.getMonthValue();
        clickNextMonth(diffM);
        locator = String.format("//div[text()= ' %s ']", ldTo.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void typePeriodInPast(String city, String dateFrom, String dateTo) {
        typeCity(city);
        //clear 'dates'
        clearDatesTextBox();
        String period = dateFrom + " - " + dateTo;
        WebElement el = wd.findElement(By.id("dates"));
        el.sendKeys(period);
        el.click();
        closeDatePickerWithoutDateSetting();
    }

    public void closeDatePickerWithoutDateSetting() {
        click(By.cssSelector(".cdk-overlay-container"));
    }


    public boolean carsSearchRes(){
        boolean res = false;
        //int cnt = wd.findElements(By.cssSelector(".car-card")).size();
        List<WebElement> list  = new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".car-card")));
        int cnt = list.size();

        if(cnt>0)
            res = true;
        return res;
    }

    private void clearDatesTextBox() {
        WebElement el = wd.findElement(By.id("dates"));
        String osName = System.getProperty("os.name");
        logger.info("The OS is : "+ osName);
        if(osName.startsWith("Mac"))
        {
            //command +a
            el.sendKeys(Keys.chord(Keys.COMMAND,"a"));
        }
        else
        {
            //Ctrl +a
            el.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        }
        el.sendKeys(Keys.DELETE);

    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        pause(500);
        click(By.cssSelector("div.pac-item"));
    }


    private void typeDate(String date){
        String[] d = date.split("/");
        String locator = String.format("//div[text()= ' %s ']", d[1]);
        click(By.xpath(locator));
    }

    private void changeMonth(int month) {
        LocalDate ld = LocalDate.now();
        logger.info("Now. Local date: " + ld.toString());
        int mm = ld.getMonthValue();
        int diff = month - mm;
        if(diff>0)
            for(int i=0; i<diff; i++)
                click(By.cssSelector("button[aria-label = 'Next month']"));
    }


    private void clickNextMonth(int cnt) {
        for(int i=0; i<cnt; i++)
            click(By.cssSelector("button[aria-label = 'Next month']"));
    }


    public boolean isSubmitBtnNotClickable() {
        return new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions
                        .not(ExpectedConditions.elementToBeClickable(wd.findElement(By.cssSelector("button[type='submit']")))));

    }

    public boolean IsErrMsgAppear() {
        boolean res = false;
        List<WebElement>  list = wd.findElements(By.cssSelector("div[class='ng-star-inserted']"));
        if(list.size()>0){
            String msg = list.get(0).getText();
            if(msg.contains("You can't pick date before today"))
                res = true;
        }
        return res;
    }

    public void clickLogo() {
        click(By.cssSelector(".header a.logo"));
    }
}
