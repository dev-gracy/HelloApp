# HelloApp Use Cases

## UC1: Display "Hello World"

Description: Run without inputs and print `Hello World`.

## UC2: Display Greeting for One Command-Line Name

Description: One argument prints a personalized greeting.

Example:
```bash
mvn exec:java -Dexec.args="Ava"
```

## UC3: Optional Argument Handling

Description: Optional flags are supported for input/output control.

Supported flags:
- `--stdin`
- `--list`
- `--remove`
- `--banner`

## UC4: Handle Multiple Command-Line Names

Description: Multiple positional names are greeted one-by-one.

Example:
```bash
mvn exec:java -Dexec.args="Ava Ben Mia"
```

## UC5: Read a Single Name from Standard Input

Description: With `--stdin`, one line can be read as one name.

## UC6: Read Multiple Names from Standard Input

Description: With `--stdin`, multiple lines or comma-separated names are accepted.

Example:
```bash
echo Ava, Ben, Mia | mvn exec:java -Dexec.args="--stdin"
```

## UC7: Store Names and List Them

Description: Names are kept in memory and can be listed.

Example:
```bash
mvn exec:java -Dexec.args="--list"
```

## UC8: Remove Stored Names

Description: Use `--remove <name>` to delete a stored name.

Example:
```bash
mvn exec:java -Dexec.args="--remove Ava"
```

## UC9: Refactor Logic into Methods

Description: Parsing, reading, and output responsibilities are split into methods.

## UC10: Move Name Management to a Separate Class

Description: Name operations are moved to `NameManager`.

Responsibilities moved:
- Add name(s)
- Remove name
- List names
- Banner formatting
- Save/load persistence

## UC11: Persistence Across Runs

Description: Names are saved to a local file and restored at startup.

## UC12: Banner-Style Display

Description: `--banner` prints a decorated greeting box.

Example:
```bash
mvn exec:java -Dexec.args="Ava --banner"
```

## Preconditions

- Java 25 and Maven installed.
- Valid `pom.xml`.

## Post Conditions

- UC1-UC12 behavior is available.
- Maven test/build validates functionality.

## Concepts Learned

- Maven lifecycle and plugins.
- Java CLI argument parsing.
- Standard input handling.
- State management and persistence.
- Unit testing with JUnit 5.