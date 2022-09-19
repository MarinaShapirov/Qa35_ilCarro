package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        List<WebElement> list = wd.findElements(By.xpath("//a[text() = ' Logout ']"));
        return list.size() > 0;
    }

    public void logOut() {
        wd.findElement(By.xpath("//a[text() = ' Logout ']")).click();
    }

    public void openLoginForm(){
        WebElement loginTab = wd.findElement(By.xpath("//*[text() = ' Log in ']"));
        loginTab.click();
    }

    public void fillLoginForm(String email, String password ){
        type(By.xpath("//input[@id = 'email']"), email);
        type(By.xpath("//input[@id = 'password']"), password);
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
            WebElement btn = wd.findElement(By.xpath("//div/button[text() = 'Ok']"));
            //pause(2000);
            btn.click();
            res = true;
        }
        return res;
    }


}
