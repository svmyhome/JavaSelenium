package ActionTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

/** 51 lesson Actions */

public class TestActionClass {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://ru.ebay.com/");
        WebElement el = driver.findElement(By.xpath("//li[@class='hl-cat-nav__js-tab']//a[@href='https://www.ebay.com/b/Electronics/bn_7000259124']"));
        Actions action = new Actions(driver);
        action.moveToElement(el).build().perform();

    }
}
