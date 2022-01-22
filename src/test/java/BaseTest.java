import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import utils.ResourceHelper;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseTest {
    public static ResourceHelper config = new ResourceHelper().getResource("config");
    public static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    public static ThreadLocal<String> testNameThread = new ThreadLocal<>();

    @BeforeSuite
    public void setUp() {
    }

    @AfterSuite
    public void tearDown() {
    }

    @BeforeTest
    public void beforeTest() {
    }

    @AfterTest
    public void afterTest() {
    }

    @BeforeClass
    public void beforeClass() {
    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method method, Object[] data) {
        openBrowser();
    }

    @AfterMethod
    public void afterMethod() {
        closeBrowser();
    }

    public void openBrowser() {
        try {
            Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("user.dir") + "/driver/chromedriver.exe");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

            ChromeDriverService service = new ChromeDriverService.Builder().build();
            service.sendOutputTo(new FileOutputStream("chromedriver_log.txt"));
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(config.getBoolean("headless"));

            WebDriver driver = new ChromeDriver(service, options);
            driver.manage().timeouts().implicitlyWait(config.getInteger("wait"), TimeUnit.SECONDS);
            driver.get(config.getString("url"));
            driverThread.set(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBrowser() {
        driverThread.get().quit();
    }
}
