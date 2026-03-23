import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloApp {
    private static final Path DEFAULT_STORAGE = Path.of("data", "names.txt");

    public static void main(String[] args) {
        NameManager manager = new NameManager(DEFAULT_STORAGE);
        run(args, new Scanner(System.in), System.out, manager);
        manager.save();
    }

    static void run(String[] args, Scanner scanner, PrintStream out, NameManager manager) {
        List<String> output = execute(args, scanner, manager);
        for (String line : output) {
            out.println(line);
        }
    }

    static List<String> execute(String[] args, Scanner scanner, NameManager manager) {
        CommandOptions options = CommandOptions.parse(args);
        List<String> sessionNames = new ArrayList<>(options.positionalNames);

        if (options.readFromStdin) {
            sessionNames.addAll(readNamesFromStdin(scanner));
        }

        manager.addNames(sessionNames);

        List<String> output = new ArrayList<>();

        for (String removeTarget : options.removals) {
            boolean removed = manager.removeName(removeTarget);
            if (removed) {
                output.add("Removed: " + removeTarget);
            } else {
                output.add("Not found: " + removeTarget);
            }
        }

        if (options.listStored) {
            output.add(manager.formatStoredNames());
            return output;
        }

        if (sessionNames.isEmpty()) {
            output.add("Hello World");
        } else {
            for (String name : sessionNames) {
                output.add("Hello " + name);
            }
        }

        if (options.bannerMode) {
            String bannerText = sessionNames.isEmpty()
                ? "Hello World"
                : "Hello " + String.join(", ", sessionNames);
            output.add(manager.toBanner(bannerText));
        }

        return output;
    }

    private static List<String> readNamesFromStdin(Scanner scanner) {
        List<String> names = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            if (line.contains(",")) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    String name = part.trim();
                    if (!name.isEmpty()) {
                        names.add(name);
                    }
                }
            } else {
                names.add(line);
            }
        }

        return names;
    }

    private static class CommandOptions {
        private final List<String> positionalNames = new ArrayList<>();
        private final List<String> removals = new ArrayList<>();
        private boolean readFromStdin;
        private boolean listStored;
        private boolean bannerMode;

        static CommandOptions parse(String[] args) {
            CommandOptions options = new CommandOptions();

            for (int i = 0; i < args.length; i++) {
                String token = args[i];
                switch (token) {
                    case "--stdin":
                        options.readFromStdin = true;
                        break;
                    case "--list":
                        options.listStored = true;
                        break;
                    case "--banner":
                        options.bannerMode = true;
                        break;
                    case "--remove":
                        if (i + 1 < args.length) {
                            options.removals.add(args[++i]);
                        }
                        break;
                    default:
                        options.positionalNames.add(token);
                        break;
                }
            }

            return options;
        }
    }
}