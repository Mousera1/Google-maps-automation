package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import Util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        TestUtil.initReport(); 
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/maps/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite() {
        TestUtil.flushReport(); 
    }
}
