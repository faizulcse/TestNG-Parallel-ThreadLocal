import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage extends BaseTest {
    public WebElement waitForElement(By locator) {
        return waitForElement(locator, config.getInteger("maxWait"));
    }

    public List<WebElement> waitForElementList(By locator) {
        return waitForElementList(locator, config.getInteger("maxWait"));
    }

    public WebElement waitForElement(By locator, long seconds) {
        new FluentWait<>("")
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(a -> driverThread.get().findElement(locator).isDisplayed());
        return driverThread.get().findElement(locator);
    }

    public List<WebElement> waitForElementList(By locator, int seconds) {
        new FluentWait<>("")
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(100))
                .until(a -> driverThread.get().findElements(locator).size() > 0);
        return driverThread.get().findElements(locator);
    }

    public boolean isPresent(By locator) {
        return new WebDriverWait(driverThread.get(), 5)
                .until(a -> driverThread.get().findElements(locator).size() > 0);
    }

    public void sleep(int seconds) {
        sleepInMillis(1000L * seconds);
    }

    protected void sleepInMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
