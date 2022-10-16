package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class ListenerWD implements WebDriverListener {
    Logger logger = LoggerFactory.getLogger(ListenerWD.class);

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);
        logger.info("BeforeFindElement by ---> " + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        logger.info("AfterFindElement by ---> " + locator);
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElements(driver, locator);
        logger.info("BeforeFindElements by ---> " + locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        WebDriverListener.super.afterFindElements(driver, locator, result);
        logger.info("AfterFindElements by ---> " + locator);
        logger.info("List size: " + result.size());
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);
        logger.info("Error, method: " + method.getName());
        logger.info("Error, get cause: " + e.getCause());

        //screen shot
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        String link = "src/test/ScreenShot/" + i + ".png";
        logger.info("Error, screenshot: " + link);

        WebDriver wd = (ChromeDriver) target;
        File f = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(f, new File(link));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
