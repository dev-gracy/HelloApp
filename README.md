# HelloApp

HelloApp is a small Java console project built with Maven. It starts with a basic greeting and grows step by step into a name-based app with persistence and formatted output.

## Summary

The app supports:
- default greeting
- one or many names from command-line
- names from standard input
- saved name list display
- removal of saved names
- persistence in a local data file
- banner style output

## Use Case Roadmap

1. UC1: Print a basic greeting in the console.
2. UC2: Accept one name from command-line input.
3. UC3: Add optional argument flags.
4. UC4: Handle multiple command-line names.
5. UC5: Read one name from standard input.
6. UC6: Read multiple names from standard input.
7. UC7: Store names and list them on request.
8. UC8: Remove stored names.
9. UC9: Refactor logic into reusable methods.
10. UC10: Move name management into a dedicated class.
11. UC11: Persist names between runs.
12. UC12: Render greeting output in banner mode.

## Build and Run

```bash
mvn clean install
mvn exec:java
```

## Commands

```bash
# UC1
mvn exec:java

# UC2 / UC4
mvn exec:java -Dexec.args="Ava Ben"

# UC5 / UC6 (stdin)
echo Ava, Ben | mvn exec:java -Dexec.args="--stdin"

# UC7 list stored names
mvn exec:java -Dexec.args="--list"

# UC8 remove a name
mvn exec:java -Dexec.args="--remove Ava"

# UC12 banner output
mvn exec:java -Dexec.args="Ava --banner"
```