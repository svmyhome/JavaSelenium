package ExamplePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class examplepage {
    public static WebDriver driver;
    static WebDriverWait wait;


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("sv@mail.ru");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'js-continue-button signup-continue-button')]")));
        driver.findElement(By.xpath("//button[contains(@class,'js-continue-button signup-continue-button')]")).click();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Vjkdnjsd1!ncjndj");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-continue-to='username-container']")));
        driver.findElement(By.xpath("//button[@data-continue-to='username-container']")).click();

    }
}
