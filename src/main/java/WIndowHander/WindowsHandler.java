package WIndowHander;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
/** 54 - 55 Work with different windows */
public class WindowsHandler {
    private static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize(); // установка максимального размера экрана
        driver.get("https://www.oracle.com/java/technologies/downloads/");
        String windowEbay = driver.getWindowHandle();
        System.out.println(windowEbay);
        JavascriptExecutor jsExec = (JavascriptExecutor)driver;
        jsExec.executeScript("window.scrollBy(0, 1000)", "");
        driver.findElement(By.xpath("//a[text()='Documentation License']")).click();
        for (String win : driver.getWindowHandles()
             ) {
            driver.switchTo().window(win);
        }
        String win2 = driver.getWindowHandle();
        System.out.println(win2);
        System.out.println(driver.findElement(By.xpath("//h3")).getText());
        driver.switchTo().window(windowEbay);
        System.out.println(driver.findElement(By.xpath("//h1")).getText());
        driver.quit();


    }
}
