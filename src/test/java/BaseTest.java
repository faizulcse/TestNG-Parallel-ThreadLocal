import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseTest {
    public static ThreadLocal<WebDriver> driverThread;
    public static ThreadLocal<String> testNameThread;

    @BeforeSuite
    public void setUp() {
        driverThread = new ThreadLocal<>();
        testNameThread = new ThreadLocal<>();
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("user.dir") + "/driver/chromedriver.exe");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
    }

    @AfterSuite
    public void tearDown() {
        testNameThread.remove();
        driverThread.remove();
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
//        openBrowser();
        testNameThread.set(method.getName());
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println(testNameThread.get());
//        closeBrowser();
    }

    public void openBrowser() {
        try {
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            service.sendOutputTo(new FileOutputStream("chromedriver_log.txt"));
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);

            WebDriver driver = new ChromeDriver(service, options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://google.com");
            driverThread.set(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBrowser() {
        // take screenshot
        driverThread.get().quit();
    }
}
