import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloAppTest {
    @Test
    void sanityCheck() {
        assertEquals("Hello" + " " + "World", "Hello World");
    }
}