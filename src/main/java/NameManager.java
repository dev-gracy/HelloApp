import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class NameManager {
    private final Path storagePath;
    private final Set<String> names;

    NameManager(Path storagePath) {
        this.storagePath = storagePath;
        this.names = new LinkedHashSet<>();
        load();
    }

    void addName(String name) {
        String normalized = normalize(name);
        if (!normalized.isEmpty()) {
            names.add(normalized);
        }
    }

    void addNames(List<String> incomingNames) {
        for (String name : incomingNames) {
            addName(name);
        }
    }

    boolean removeName(String name) {
        String normalized = normalize(name);
        return names.remove(normalized);
    }

    List<String> getNames() {
        return new ArrayList<>(names);
    }

    boolean isEmpty() {
        return names.isEmpty();
    }

    String formatStoredNames() {
        if (names.isEmpty()) {
            return "No names stored.";
        }

        StringBuilder builder = new StringBuilder("Stored names:");
        List<String> orderedNames = getNames();
        for (int i = 0; i < orderedNames.size(); i++) {
            builder.append(System.lineSeparator())
                .append(i + 1)
                .append(". ")
                .append(orderedNames.get(i));
        }
        return builder.toString();
    }

    String toBanner(String text) {
        String message = normalize(text);
        if (message.isEmpty()) {
            message = "Hello World";
        }

        String border = "*".repeat(message.length() + 4);
        return border
            + System.lineSeparator()
            + "* " + message + " *"
            + System.lineSeparator()
            + border;
    }

    void save() {
        try {
            Path parent = storagePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.write(storagePath, getNames());
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to save names", ex);
        }
    }

    private void load() {
        if (!Files.exists(storagePath)) {
            return;
        }

        try {
            List<String> stored = Files.readAllLines(storagePath);
            for (String line : stored) {
                addName(line);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to load names", ex);
        }
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
