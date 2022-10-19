package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AppManager {
    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;
    HelperSearch helperSearch;
    Logger logger = LoggerFactory.getLogger(AppManager.class);

    public void init(){
        WebDriverListener listener = new ListenerWD();
        wd = new ChromeDriver();
        logger.info("Chrome driver was opened");
        wd = new EventFiringDecorator<>(listener).decorate(wd);
        //config
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro-1578153671498.web.app");
        logger.info("Current URL is ---> " + wd.getCurrentUrl());

        helperUser   = new HelperUser(wd);
        helperCar    = new HelperCar(wd);
        helperSearch = new HelperSearch(wd);

    }
    public void stop(){
        //wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar HelperCar() {
        return helperCar;
    }

    public HelperSearch getHelperSearch() {
        return helperSearch;
    }
}
