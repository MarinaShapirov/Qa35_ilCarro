package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AppManager {
    WebDriver wd;
    HelperUser helperUser;

    public void init(){
        wd = new ChromeDriver();
        //config
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro-1578153671498.web.app");

        helperUser = new HelperUser(wd);

    }
    public void stop(){
        //wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }
}
