package ExplisWaitTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * 50 lesson Actions
 */

public class ExplicitlyWaitTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static WebDriverWait wait1;
    private static String pathChromedriver = "libs/chromedriver.exe";

    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", pathChromedriver);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='js-link-box-en']")));
        driver.findElement(By.xpath("//a[@id='js-link-box-en']")).click();
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='mw-headline']")));

        // WebElement el = driver.findElement((By.xpath("//div[@class='VfPpkd-Jh9lGc']")));
        //     System.out.println(el.getText());
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Далее']"))); //TODO 50 Lessons not work I think Idea don't support russian char
        driver.close();
    }
}
