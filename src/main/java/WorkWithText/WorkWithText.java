package WorkWithText;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
/** 58 Work with text (Shift, control, cut, paste) */
public class WorkWithText {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        WebElement elem = driver.findElement(By.xpath("//input[@name='search']"));
        elem.sendKeys("text text");
        elem.sendKeys(Keys.ENTER);
        driver.get("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        WebElement elem1 = driver.findElement(By.xpath("//input[@name='search']"));
        String select = Keys.chord(Keys.CONTROL, "a");
        String cut = Keys.chord(Keys.CONTROL, "x");
        String paste = Keys.chord(Keys.CONTROL, "v");
        elem1.sendKeys(Keys.chord(Keys.SHIFT, "text text"));
        elem1.sendKeys(Keys.chord(select));
        elem1.sendKeys(Keys.chord(cut));
        elem1.sendKeys(Keys.chord(paste));
        elem1.sendKeys(Keys.ENTER);

        //driver.quit();
    }
}
