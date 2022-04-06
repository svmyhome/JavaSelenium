package TakeScreenshot;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/** 59 How to take a screenshot? */
public class TakeScreenshot {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        WebElement elem = driver.findElement(By.xpath("//input[@name='search']"));
        elem.sendKeys("text text");
        elem.sendKeys(Keys.ENTER);
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh_mm_ss");
        String filename = format.format(dateNow) + ".png";
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\TEMP\\" + filename)); //нужно импортнуть зависимость  implementation 'commons-io:commons-io:2.11.0'
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
