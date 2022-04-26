import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedTests {
    @BeforeAll
    static void beforeAllMethod() {
        System.out.println("All class");
    }

    @BeforeEach
    void setUp() {
        System.out.println("each method");
    }

    @DisplayName("Verify that the string length is greater then 0")
    @ParameterizedTest
    @ValueSource(strings = {"2323", "444545", "54545"})
    void parameterizedTestAssert(String arg) {
        assertTrue(arg.length() > 0);
    }

    @DisplayName("Verify that the Values in the Csv are equal")
    @ParameterizedTest
    @CsvSource(value = {"QWERTY, qwerty", "QWERTY, qwerty1", "123456, 123456"})
    @Step("Ожидаемое значение {expected} полученное значение {actual}")
    void parareterizedTestCsv(String actual, String expected) {
        assertEquals(expected, actual.toLowerCase(), "Value are not equal!!");
    }

    @DisplayName("Verify that the Values in the Csv are equal")
    @ParameterizedTest(name = "The value of {index} subtest ==> the rank of ''{0}'' is {2}")
    @CsvSource(value = {"QWERTY, qwerty, 1", "QWERTY, qwerty1, 2", "123456, 123456, 3"})
    void parareterizedTestCsvName(String actual, String expected, int rank) {
        assertEquals(expected, actual.toLowerCase(), "Value are not equal!!");
    }

    @DisplayName("Verify that the Values in the Csv are equal")
    @ParameterizedTest (name = "The value of {index} subtest ==> the rank of ''{0}'' is {1} the number is {2}")
    @CsvFileSource(resources = "/test.csv")
    void parareterizedTestCsvFile(String actual, String expected, int rank) {
        assertEquals(expected, actual.toLowerCase(), "Value are not equal!!");
    }


    @AfterEach
    void tearDown() {
        System.out.println("each method");
    }

    @AfterAll
    static void afterAllMethod() {
        System.out.println("All class");
    }
}
