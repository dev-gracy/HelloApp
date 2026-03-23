# HelloApp Use Cases

## UC1: Display "Hello World"

Description: Running with no arguments prints `Hello World`.

## UC2: Display Greeting for One Command-Line Name

Description: Running with one positional argument prints `Hello <name>`.

Example:
```bash
mvn exec:java -Dexec.args="Ava"
```

## UC3: Optional Argument Handling

Description: Flags are optional and can be combined.

Supported flags:
- `--default`
- `--stdin`
- `--add`
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

Description: With `--stdin`, one input line is treated as one name.

## UC6: Read Multiple Names from Standard Input

Description: With `--stdin`, multiple input lines or comma-separated names are supported.

Example:
```bash
echo Ava, Ben, Mia | mvn exec:java -Dexec.args="--stdin"
```

## UC7: Store Names and List Them

Description: Entered names are stored and can be viewed with `--list`.

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

Description: Input parsing, stdin processing, and execution are separated into dedicated methods.

## UC10: Move Name Management to a Separate Class

Description: Name list operations are handled by `NameManager`.

Responsibilities moved:
- Add name(s)
- Remove name
- List names
- Banner formatting
- Save/load persistence

## UC11: Persistence Across Runs

Description: Names are stored in `data/names.txt` and reloaded on startup.

## UC12: Banner-Style Display

Description: `--banner` prints decorated greeting output.

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