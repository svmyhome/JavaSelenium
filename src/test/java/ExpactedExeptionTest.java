import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
//TODO must be added to some methods @RepeatedTest
public class ExpactedExeptionTest { //TODO Exeption in JUnit 5 Don't work. I need to understand how   Chaper 23
    @Test
    @DisplayName("Verify that the method is throwing as an right exception")
    void assertTrowsTrue() {
        int[] expected = {1, 3, 2, 6};
        int[] actual = null;
        // assertThrows(NullPointerException.class, () -> actual), "don't get Nullpointexeption");
    }
}
