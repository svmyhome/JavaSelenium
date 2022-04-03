package JSExecutorTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
/** 52 lesson Allerts */
/** 53 lesson AcceptDismiss */

public class JsExecutorTest {
    private static WebDriver driver;

    public static void main(String[] args) {

       // TestAllert();
       // TestScroll();
        TestSwitchToAlertAcceptDismiss();
    }

    public static void TestAllert() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize();
        JavascriptExecutor jsExec = (JavascriptExecutor)driver;
        jsExec.executeScript("alert('Hello World!!!');");
    }

    public static void TestScroll() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize();
        JavascriptExecutor jsExec = (JavascriptExecutor)driver;
        driver.get("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
        jsExec.executeScript("window.scrollBy(0, 1000)", "");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jsExec.executeScript("window.scrollBy(0, -1000)", "");
    }

    public static void TestSwitchToAlertAcceptDismiss() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        driver.manage().window().maximize();
        JavascriptExecutor jsExec = (JavascriptExecutor)driver;
        driver.get("https://www.google.com/");
        jsExec.executeScript("confirm('Hello World!!!');");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().alert().accept();
        //driver.switchTo().alert().dismiss();
    }

}
