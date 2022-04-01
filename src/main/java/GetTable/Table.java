package GetTable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private WebDriver driver;
    private WebElement tableElement;

    public Table(WebDriver driver, WebElement tableElement) {
        this.driver = driver;
        this.tableElement = tableElement;
    }

    public List<WebElement> getRows() {
        List<WebElement> rows = tableElement.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    public List<WebElement> getHeadiing() {
        WebElement getheadingRow = tableElement.findElement(By.xpath(".//tr[1]"));
        List<WebElement> getHeadingColumns = tableElement.findElements(By.xpath(".//th"));
        return getHeadingColumns;
    }

    public List<List<WebElement>> getRowsWithColumns() {
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for (WebElement row : rows) {
            List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }

    public List<Map<String, WebElement>> getRowsWithColumnsbyHeading() {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeading = new ArrayList<Map<String, WebElement>>();
        Map<String, WebElement> rowByHeading;
        List<WebElement> headingColumns = getHeadiing();

        for (List<WebElement> row : rowsWithColumns) {
            rowByHeading = new HashMap<String, WebElement>();
            for (int i = 0; i < headingColumns.size(); i++) {
                String heading = headingColumns.get(i).getText();
                WebElement cell = row.get(i);
                rowByHeading.put(heading, cell);
            }
            rowsWithColumnsByHeading.add(rowByHeading);
        }
        return rowsWithColumnsByHeading;
    }

    public String getValueFromCell(int rowNumber, int colNumber) {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        WebElement cell = rowsWithColumns.get(rowNumber - 1).get(colNumber - 1);
        return cell.getText();
    }

    public String getValueFromCell(int rowNumber, String colName) {
        List<Map<String, WebElement>> rowsWithColumnsByHeading = getRowsWithColumnsbyHeading();
        return rowsWithColumnsByHeading.get(rowNumber - 1).get(colName).getText();
    }

}
