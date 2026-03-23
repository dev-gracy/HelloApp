# HelloApp Use Cases

## UC1: Display "Hello World"

### Description
The application prints "Hello World" to the console when executed.

### Disadvantages of Previous Use Case (if any)
Not applicable. This is the first use case.

### Preconditions
- Java and Maven are installed.
- Project contains a valid `pom.xml`.
- `HelloApp.java` exists in `src/main/java`.

### Main Flow
1. User runs the application.
2. Program enters `main` method.
3. Program prints `Hello World`.
4. Program exits.

### Post Conditions
- Greeting is visible in the console.
- Build and execution complete without errors.

### Hints
- Keep class name and file name identical (`HelloApp`).
- Keep `main` method signature exact.
- Use `mvn clean install` to verify build.

### Code Snippet Example
```java
public class HelloApp {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

### Concepts Learned
- Maven project layout.
- Java entry point (`public static void main`).
- Console output using `System.out.println`.
- Build lifecycle basics with Maven.