# Week 3 — Math Operations & Streams

## 1. Math Methods

### A few common methods from the standard `Math` class

| Method | Behavior | Example |
|---|---|---|
| `sqrt(x)` | Square root of x | `Math.sqrt(9.0)` evaluates to `3.0`. |
| `pow(x, y)` | Power: x<sup>y</sup> | `Math.pow(6.0, 2.0)` evaluates to `36.0`. |
| `abs(x)` | Absolute value of x | `Math.abs(-99.5)` evaluates to `99.5`. |

**Other available methods:** `exp()`, `log()`, `ceil()`, `floor()`, etc.

## 2. Numbers

### Boundary of Integer Data Types

| Declaration | Size | Supported number range |
|---|---|---|
| `byte myVar;` | 8 bits | -128 to 127 |
| `short myVar;` | 16 bits | -32,768 to 32,767 |
| `int myVar;` | 32 bits | -2,147,483,648 to 2,147,483,647 |
| `long myVar;` | 64 bits | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 |

### Boundary of Floating-point Numeric Data Types

| Declaration | Size | Supported number range |
|---|---|---|
| `float x;` | 32 bits | -3.4x10<sup>38</sup> to 3.4x10<sup>38</sup> |
| `double x;` | 64 bits | -1.7x10<sup>308</sup> to 1.7x10<sup>308</sup> |

### Beware of Division by Zero!

```java
import java.util.Scanner;

public class DailySalary {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int salaryPerYear; // User input: Yearly salary
      int daysPerYear;   // User input: Days worked per year
      int salaryPerDay;  // Output:     Salary per day

      System.out.print("Enter yearly salary: ");
      salaryPerYear = scnr.nextInt();

      System.out.print("Enter days worked per year: ");
      daysPerYear = scnr.nextInt();

      // If daysPerYear is 0, then divide-by-zero causes program termination.
      salaryPerDay = salaryPerYear / daysPerYear;

      System.out.println("Salary per day is: " + salaryPerDay);
   }
}
```

Output:

```
Enter yearly salary: 60000
Enter days worked per year: 0
Exception in thread "main" java.lang.ArithmeticException: / by zero
        at DailySalary.main(DailySalary.java:17)
```

## 3. Random Number

### Computers generate pseudo-random numbers

- `Random()` uses the current time as the seed to generate a sequence of numbers
- `Random(28)` uses 28 as the seed

### Custom Random Boundary

- A Random range starts from 0;
- To make a **min-max** Random range:

```java
Random rand = new Random();
rand.nextInt(max - min + 1) + min;
```

## 4. Modulo

### Filtering Digits

~~789,546~~,**451**

```java
int a = 789_546_451;
int lastThree = a % 1000; // 451
int firstSix = a / 1000; // 789_546
```

### Finding Odd / Even Numbers

`| 1 | 2 | 7 | 10 |`

- **Odd:** `% 2 = 1`
- **Even:** `% 2 = 0`

```java
int even = 10;
int odd = 7;

int b = even % 2; // b = 0
int c = odd % 2; // c = 1
```

### Modulo & Random

**Limit the positive and negative max value**

```java
import java.util.Random;
import java.lang.Math;

Random rand = new Random();
rand.nextInt() % 10; // a value from -9 to 9
Math.abs( rand.nextInt() % 10 ); // a value from 0 to 9
```

## 5. Format Specifier

### Format specifiers for the `printf()` and `format()` methods

| Format specifier | Data type(s) | Notes |
|---|---|---|
| `%c` | char | Prints a single Unicode character |
| `%d` | int, long, short | Prints a decimal integer value. |
| `%o` | int, long, short | Prints an octal integer value. |
| `%h` | int, char, long, short | Prints a hexadecimal integer value. |
| `%f` | float, double | Prints a floating-point value. |
| `%e` | float, double | Prints a floating-point value in scientific notation. |
| `%s` | String | Prints the characters in a String variable or literal. |
| `%%` | | Prints the "%" character. |
| `%n` | | Prints the platform-specific new-line character. |

### Format specifiers and sub-specifiers

`%(flags)(width)(.precision)specifier`

| Sub-specifier | Description | Example |
|---|---|---|
| width | Specifies the minimum number of characters to print. If the formatted value has more characters than the width, the value will not be truncated. If the formatted value has fewer characters than the width, the output will be padded with spaces (or 0's if the '0' flag is specified). | `printf("Value: %7.2f", myFloat);` → `Value:   12.34` |
| .precision | Specifies the number of digits to print following the decimal point. If the precision is not specified, a default precision of 6 is used. | `printf("%.4f", myFloat);` → `12.3400`<br>`printf("%3.4e", myFloat);` → `1.2340e+01` |
| flags | `-`: Left aligns the output given the specified width, padding the output with spaces.<br>`+`: Prints a preceding + sign for positive values. Negative numbers are always printed with the - sign.<br>`0`: Pads the output with 0's when the formatted value has fewer characters than the width.<br>`space`: Prints a preceding space for positive value. | `printf("%+f", myFloat);` → `+12.340000`<br>`printf("%08.2f", myFloat);` → `00012.34` |

### String formatting

Method calls to `printf()` apply to `PrintStream` objects like `System.out`.

| Sub-specifier | Description | Example |
|---|---|---|
| width | Specifies the minimum number of characters to print. If the string has more characters than the width, the value will not be truncated. If the formatted value has fewer characters than the width, the output will be padded with spaces. | `printf("%20s String", myString);` → `          Formatting String` |
| .precision | Specifies the maximum number of characters to print. If the string has more characters than the precision, the string will be truncated. | `printf("%.6s", myString);` → `Format` |
| flags | `-`: Left aligns the output given the specified width, padding the output with spaces. | `printf("%-20s String", myString);` → `Formatting           String` |

## 6. String Streams

### System Input Stream

Characters typed on the keyboard (e.g. `W`, `H`, `O`, ...) are placed into the **buffer** of the System Input Stream, indexed from 0 to 8 for `Who am I?`.

### String Input Stream

`scanner.nextLine()` reads the whole line from the System Input Stream buffer into a String:

`String question = scanner.nextLine(); // question = "Who am I?"`

The String can then be used as the source of a new **String Input Stream**, with its own buffer: `new Scanner(question);`

The same String can also be sent to the **System Output Stream**: `System.out.println(question);`

### PrintWriter & Output Stream

A **String Output Stream** (created by `PrintWriter`) holds characters in its buffer, which are then transferred to the **System Output Stream** buffer.

## Further Reading

- [Online slot machines (image source for Random Number slide) — wikiHow](https://www.wikihow.com/Play-Online-Slot-Machines)
- [Math operators and the Math class — LinkedIn Learning: Java 11+ Essential Training](https://www.linkedin.com/learning/java-11-plus-essential-training/math-operators-and-the-math-class?u=2104756)
- [Agile Software Development: Pair and Mob Programming — LinkedIn Learning](https://www.linkedin.com/learning/agile-software-development-pair-and-mob-programming)
- [Java Math — W3Schools](https://www.w3schools.com/java/java_math.asp)
- [Java Files — W3Schools](https://www.w3schools.com/java/java_files.asp)
- [Java Method Parameters — W3Schools](https://www.w3schools.com/java/java_methods_param.asp)
