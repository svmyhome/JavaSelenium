package GetTable;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainTable {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\MyPetProject\\FistAutomatization\\src\\main\\resources\\webdriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // принудительное ожидание
        // Установка размера браузера
        driver.manage().window().setSize(new Dimension(500, 600)); // установка конкретного разрешения
        Thread.sleep(1000);
        driver.manage().window().maximize(); // установка максимального размера экрана

        // Переход по ссылкам и навигация
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        WebElement table = driver.findElement(By.xpath("//table[@id='customers']"));

        Table table1 = new Table(driver, table);

        System.out.println("++++++ Размер таблицы " + table1.getRows().size() + 1);
        System.out.println(table1.getRows().get(0).getText());
        System.out.println(table1.getHeadiing().get(0).getText());
        System.out.println("=====");
        System.out.println(table1.getRowsWithColumns().get(0).get(0).getText());
        System.out.println(table1.getValueFromCell(2,2));
        System.out.println(table1.getValueFromCell(1, "Contact"));

        driver.quit();
    }
}
