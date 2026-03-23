import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloAppTest {
    @TempDir
    Path tempDir;

    @Test
    void uc1PrintsHelloWorldWhenNoInput() {
        NameManager manager = new NameManager(tempDir.resolve("names.txt"));
        List<String> output = HelloApp.execute(new String[]{}, new Scanner(""), manager);
        assertEquals(List.of("Hello World"), output);
    }

    @Test
    void uc2AndUc4GreetsNamesFromArgs() {
        NameManager manager = new NameManager(tempDir.resolve("names.txt"));
        List<String> output = HelloApp.execute(new String[]{"Ava", "Ben"}, new Scanner(""), manager);

        assertEquals(List.of("Hello Ava", "Hello Ben"), output);
        assertEquals(List.of("Ava", "Ben"), manager.getNames());
    }

    @Test
    void uc5AndUc6ReadsNamesFromStdin() {
        NameManager manager = new NameManager(tempDir.resolve("names.txt"));
        Scanner scanner = new Scanner("Mia\nNoah, Liam\n");

        List<String> output = HelloApp.execute(new String[]{"--stdin"}, scanner, manager);

        assertEquals(List.of("Hello Mia", "Hello Noah", "Hello Liam"), output);
        assertEquals(List.of("Mia", "Noah", "Liam"), manager.getNames());
    }

    @Test
    void uc7Uc8Uc11StoresRemovesAndPersistsNames() {
        Path storage = tempDir.resolve("names.txt");
        NameManager firstRun = new NameManager(storage);

        HelloApp.execute(new String[]{"Ava", "Ben"}, new Scanner(""), firstRun);
        HelloApp.execute(new String[]{"--remove", "Ava"}, new Scanner(""), firstRun);
        firstRun.save();

        NameManager secondRun = new NameManager(storage);
        assertEquals(List.of("Ben"), secondRun.getNames());
    }

    @Test
    void uc12BannerModePrintsDecoratedOutput() {
        NameManager manager = new NameManager(tempDir.resolve("names.txt"));
        List<String> output = HelloApp.execute(new String[]{"Ava", "--banner"}, new Scanner(""), manager);

        assertEquals("Hello Ava", output.get(0));
        assertTrue(output.get(1).contains("* Hello Ava *"));
    }

    @Test
    void listCommandShowsStoredNames() {
        NameManager manager = new NameManager(tempDir.resolve("names.txt"));
        HelloApp.execute(new String[]{"Ava", "Ben"}, new Scanner(""), manager);

        List<String> output = HelloApp.execute(new String[]{"--list"}, new Scanner(""), manager);
        assertEquals(1, output.size());
        assertTrue(output.get(0).contains("1. Ava"));
        assertTrue(output.get(0).contains("2. Ben"));
    }

    @Test
    void runWritesToProvidedOutputStream() {
        NameManager manager = new NameManager(tempDir.resolve("names.txt"));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(buffer);

        HelloApp.run(new String[]{"Ava"}, new Scanner(""), out, manager);

        assertTrue(buffer.toString().contains("Hello Ava"));
        assertFalse(buffer.toString().contains("Hello World"));
    }
}