package LoadFileInput;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * 60 How to load a file if you have an input field?
 */
public class LoadFileInput {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://www.google.ru/imghp?hl=ru");
        driver.findElement(By.xpath("//div[@aria-label='Поиск по картинке']")).click();
        driver.findElement(By.xpath("//a[@href='about:invalid#zClosurez']")).click();
        WebElement inp = driver.findElement(By.xpath("//input[@id='awyMjb']"));
        inp.sendKeys("C:\\TEMP\\07_02_26.png");

        //driver.quit();
    }
}
