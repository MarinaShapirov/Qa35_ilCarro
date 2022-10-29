package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AppManager {
    Logger logger = LoggerFactory.getLogger(AppManager.class);
    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;
    HelperSearch helperSearch;
    String browser;

    public AppManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        if(browser.equals(Browser.CHROME.browserName())){
            wd = new ChromeDriver();
            logger.info(Browser.CHROME.browserName().toUpperCase() + " driver was opened");
        }
        else if(browser.equals(Browser.FIREFOX.browserName())){
            wd = new FirefoxDriver();
            logger.info(Browser.FIREFOX.browserName().toUpperCase() + " driver was opened");
        }
        else if(browser.equals(Browser.EDGE.browserName())){
            wd = new EdgeDriver();
            logger.info(Browser.EDGE.browserName().toUpperCase() + " driver was opened");
        }


        WebDriverListener listener = new ListenerWD();
        //wd = new ChromeDriver();
        //logger.info("Chrome driver was opened");
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
        wd.quit();
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
