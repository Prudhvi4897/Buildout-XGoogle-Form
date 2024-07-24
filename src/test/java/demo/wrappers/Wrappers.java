package demo.wrappers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

   // WebDriver driver;
   // WebDriverWait wait;

   public static void navigateToUrl(WebDriver driver, String url) {
    driver.get(url);
    System.out.println("Navigated to the URL: " + url);
}

public static void verifyThePage(WebDriver driver, String pageName) {
    if (driver.getCurrentUrl().contains(pageName)) {
        System.out.println("Page is verified");
    } else {
        System.out.println("Page is not verified");
    }
}

public static void clickElement(WebElement element) {
    if (element.isDisplayed() && element.isEnabled()) {
        element.click();
    } else {
        System.out.println("Element is not clickable");
    }
}

public static void sendText(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
    System.out.println(text + " is filled successfully");
}

public static void verifyText(WebElement element, String text) {
    if (element.getAttribute("data-initial-value").contains(text)) {
        System.out.println("Text is verified successfully");
    } else {
        System.out.println("Text is not verified");
    }
}

public static void sendTextWithTime(WebElement element, String text) {
    long timestamp = System.currentTimeMillis() / 1000;
    element.sendKeys(text + timestamp);
    System.out.println("Text with timestamp is filled successfully");
}

public static void addExperience(WebDriver driver, String experience) {
    List<WebElement> experienceElements = driver.findElements(By.xpath("//div[@class='nWQGrd zwllIb']/label/div/div[2]/div/span"));
    for (WebElement element : experienceElements) {
        if (element.getText().contains(experience)) {
            clickElement(element);
            System.out.println("Experience is selected successfully");
            break;
        }
    }
}

public static void selectTools(WebDriver driver, String tool) {
    List<WebElement> tools = driver.findElements(By.xpath("//span[@class='aDTYNe snByac n5vBHf OIC90c']"));
    for (WebElement element : tools) {
        if (element.getText().contains(tool)) {
            clickElement(element);
            System.out.println(tool + " is selected successfully");
            break;
        }
    }
}

public static void selectAddress(WebDriver driver, String text) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb DEh1R']")));
    addressElement.click();
    WebElement addressOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']/div/span[text()='" + text + "']")));
    clickElement(addressOption);
}

public static void enterDate(WebDriver driver) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, -7);
    Date previousDate = calendar.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String formattedDate = dateFormat.format(previousDate);

    WebElement dateElement = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[2]"));
    dateElement.sendKeys(formattedDate);
    System.out.println("Date entered successfully: " + formattedDate);
}

public static void enterTime(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    WebElement hourElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Hour']")));
    hourElement.sendKeys("07");

    WebElement minuteElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Minute']")));
    minuteElement.sendKeys("30");

    System.out.println("Time entered successfully: 07:30");
}

public static void clickOnSubmitButton(WebDriver driver) {
    WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']"));
    clickElement(submitButton);
}

public static void successMessage(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vHW8K']")));
    String actualMessage = messageElement.getText();
    System.out.println("Success message: " + actualMessage);
    if (actualMessage.contains("Thanks for your response")) {
        System.out.println("Success message verified: Thanks for your response");
    } else {
        System.out.println("Success message not found");
    }
}
}