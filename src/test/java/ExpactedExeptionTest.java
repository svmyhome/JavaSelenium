import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpactedExeptionTest { //TODO Exeption in JUnit 5 Don't work. I need to understand how
    @Test
    public void assertTrowsTrue() {
        int[] expected = {1, 3, 2, 6};
        int[] actual = null;
//        Exception exception = assertThrows(NullPointerException.class, () ->
//                actual. , "don't get Nullpointexeption");
        Assertions.assertArrayEquals(expected, actual, "Arrays are not equals");
    }
}