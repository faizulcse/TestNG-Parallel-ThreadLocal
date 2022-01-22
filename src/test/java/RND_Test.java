import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class RND_Test extends BasePage {
    @Test
    public void googleTest() {
        By agreeButton = By.xpath("//*[text()='I agree']");
        By resultsLocator = By.xpath("//div[@role='option']");
        By searchLocator = By.name("q");

        WebElement searchField;
        List<WebElement> results;

        waitForElement(agreeButton).click();
        searchField = waitForElement(searchLocator);
        searchField.sendKeys("faizul");
        searchField.click();

        results = waitForElementList(resultsLocator);
        for (WebElement el : results) {
            System.out.println("-> " + el.getText());
        }
    }
}
