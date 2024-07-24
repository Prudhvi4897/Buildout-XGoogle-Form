package demo;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
//import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;


public class TestCases {
    private static final String URL = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";
    ChromeDriver driver;
    Wrappers wrappers; 

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException {  
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Wrappers.navigateToUrl(driver, URL);
        Wrappers.verifyThePage(driver, "forms");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='rFrNMe k3kHxc RdH0ib yqQS1 zKHdkd']/div/div/div/input")));
        Wrappers.sendText(nameElement, "Crio Learner");
        Wrappers.verifyText(nameElement, "Crio Learner");
        
        WebElement practicingAutomation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='KHxj8b tL9Q4c']")));
        Wrappers.sendTextWithTime(practicingAutomation, "I want to be the best QA Engineer!");
        Wrappers.verifyText(practicingAutomation, "I want to be the best QA Engineer!");
        Wrappers.addExperience(driver, "3 - 5");
        Wrappers.selectTools(driver, "Java");
        Wrappers.selectTools(driver, "Selenium");
        Wrappers.selectTools(driver, "TestNG");
        Wrappers.selectAddress(driver, "Mr");
        Thread.sleep(2000);
        Wrappers.enterDate(driver);
        Wrappers.enterTime(driver);
        Wrappers.clickOnSubmitButton(driver);
        Thread.sleep(1000);
        Wrappers.successMessage(driver);
    }

    @AfterTest
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}

   