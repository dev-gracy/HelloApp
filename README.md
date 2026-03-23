# HelloApp

HelloApp starts with a simple console greeting and is designed to evolve through incremental use cases.

## Summary

This project is a Maven-based Java console application. It begins with displaying "Hello World", then expands to user-name handling from command-line arguments and standard input, list management, refactoring, persistence, and banner-style output.

## Use Case Roadmap

1. UC1: Print a basic greeting in the console.
2. UC2: Accept one name from command-line input.
3. UC3: Add optional argument handling.
4. UC4: Handle multiple names in one run.
5. UC5: Read one name from standard input.
6. UC6: Read and process multiple names from standard input.
7. UC7: Store entered names and list them.
8. UC8: Add remove-name support.
9. UC9: Refactor input processing methods.
10. UC10: Move name management to separate class.
11. UC11: Persist names across runs.
12. UC12: Render output in banner format.

## Build and Run

```bash
mvn clean install
java -cp target/HelloApp-1.0.0-SNAPSHOT.jar HelloApp
```