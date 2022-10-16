package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {

        return isElementPresent(By.xpath("//a[text() = ' Logout ']"));
    }

    public void logOut() {

        wd.findElement(By.xpath("//a[text() = ' Logout ']")).click();
    }

    public void clickOkButton() {
        if(isElementPresent(By.xpath("//button[text()='Ok']")))
            click(By.xpath("//button[text()='Ok']"));
    }

    public void openLoginFormHeader() {

        wd.findElement(By.cssSelector("a[href ^='/login']")).click();
        // //a[text()=' Log in ']
    }
    public void openLoginFormFooter() {
        // a[href ^='/login']
        wd.findElement(By.xpath("//a[text()='Log in']")).click();
    }

    public void fillLoginForm(String email, String password ){
        type(By.xpath("//input[@id = 'email']"), email);
        type(By.xpath("//input[@id = 'password']"), password);
    }

    public void fillLoginForm(User user ){
        type(By.xpath("//input[@id = 'email']"), user.getEmail());
        type(By.xpath("//input[@id = 'password']"), user.getPassword());
    }

    public void submit(){
        //login submit
        WebElement btn = wd.findElement(By.xpath("//button[contains(text(),'alla!')]"));
        btn.click();
    }

    public boolean isSuccLoginMsgAppear() {
        boolean res = false;
        List<WebElement> list = wd.findElements(By.xpath("//div/h1[text() = 'Logged in']"));
        if(list.size() > 0){
            //accept it
            //WebElement btn = wd.findElement(By.xpath("//div/button[text() = 'Ok']"));
            //pause(2000);
            //btn.click();
            click(By.xpath("//div/button[text() = 'Ok']"));
            res = true;
        }
        return res;
    }
    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));
        return wd.findElement(By.cssSelector("h2.message")).getText();
    }

    public String getTitleMessage() {
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector("div.dialog-container"))));
        return wd.findElement(By.cssSelector("div.dialog-container>h1")).getText();
    }

    public boolean isYallaButtonNotActive() {
        boolean res =isElementPresent(By.cssSelector("button[disabled]"));
        return res && !wd.findElement(By.cssSelector("[type='submit']")).isEnabled();
    }

    public String  getErrorText() {
        return wd.findElement(By.cssSelector("div.error>div")).getText();
    }


    //Registration

    public void openRegstrFormFromHeader() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegstrForm(User user) {
        type(By.xpath("//input[@id = 'name']"), user.getName());
        type(By.xpath("//input[@id = 'lastName']"), user.getLastName());
        type(By.xpath("//input[@id = 'email']"), user.getEmail());
        type(By.xpath("//input[@id = 'password']"), user.getPassword());
    }

    public void CheckPolicy() {
        //click(By.id("terms-of-use"));
        click(By.cssSelector("label[for='terms-of-use']"));

    }
    public void CheckPolicyXY() {
        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int w = rect.getWidth();
        int h = rect.getHeight();
        int x = rect.getX();
        int y = rect.getY();
        Actions action = new Actions(wd);
        action.moveToElement(label, -w/2, 0).click().release().perform();
    }

    public void login(User user) {
        openLoginFormHeader();
        fillLoginForm(user);
        submit();
        clickOkButton();
    }
}
