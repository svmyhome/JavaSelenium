package EnabledSelectedDisplayed;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class EnabSelecDispl {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://stevefaulkner.github.io/html-mapping-tests/browser-tests/checkbox-states.html");
        WebElement check1 = driver.findElement(By.xpath("//input[1]"));
        WebElement check2 = driver.findElement(By.xpath("//input[2]"));
        System.out.println(check1.isSelected());
        System.out.println(check2.isSelected());
        driver.get("https://www.w3.org/WAI/UA/TS/html401/cp0101/0101-RADIO.html");
        WebElement radio1 = driver.findElement(By.xpath("//input[@id='button1']"));
        WebElement radio2 = driver.findElement(By.xpath("//input[@id='button2']"));
        System.out.println(radio1.isSelected());
        System.out.println(radio2.isSelected());
        driver.get("https://ru.ebay.com/");
        WebElement displ1 = driver.findElement(By.xpath("//a[@href='https://www.ebay.com/b/Electronics/bn_7000259124']"));
        WebElement displ2 = driver.findElement(By.xpath("//a[text()='Apple']"));
        System.out.println(displ1.isDisplayed());
        System.out.println(displ2.isDisplayed());
        driver.quit();
    }
}
