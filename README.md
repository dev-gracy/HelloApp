# HelloApp

HelloApp is a Maven-based Java console app that now implements UC1 through UC12.

## Summary

The application starts with "Hello World", then supports command-line names, stdin names, list and remove operations, persistence across runs, and banner output.

## Use Case Roadmap

1. UC1: Print a basic greeting in the console.
2. UC2: Accept one name from command-line input.
3. UC3: Support optional argument flags.
4. UC4: Handle multiple command-line names.
5. UC5: Read one name from standard input.
6. UC6: Read multiple names from standard input.
7. UC7: Store names and list them on request.
8. UC8: Remove stored names.
9. UC9: Refactor logic into reusable methods.
10. UC10: Move name management into `NameManager`.
11. UC11: Persist names between runs in `data/names.txt`.
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