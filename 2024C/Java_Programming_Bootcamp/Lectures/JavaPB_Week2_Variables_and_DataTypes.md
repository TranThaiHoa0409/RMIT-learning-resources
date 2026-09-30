# Week 2 — Variables and Data Types

## 1. Variable

### Variable: Item to hold value

A **variable** is like a box that holds a value. Each variable has a **type** that determines what kind of value it can hold.

| Value | Type |
|---|---|
| `"Bob"` | `String` |
| `true` | `boolean` |
| `35` | `int` |

```java
String name = "Bob";
boolean isActive = true;
int age = 35;
```

### Assignments with variable on left and right

A variable may appear on **both the left and right** of an assignment. The right side is evaluated first (using the variable's current value), then the result is stored back into the variable.

```
x = 1
x = x * 20        // x becomes 20
x = x * 20        // x becomes 400
Put "A person with measles may cause " to output
Put x to output
Put newline to output
Put "people to be infected in two weeks." to output
```

Output:

```
A person with measles may cause 400
people to be infected in two weeks.
```

### Common errors

**Which code segments have an error?**

| # | Code | Result |
|---|---|---|
| 1 | `21 = dogCount;` | **Error** — the left side of `=` must be a variable, not a literal |
| 2 | `int amountOwed = -999;` | **No error** — negative values are valid for `int` |
| 3 | `int numDays;`<br>`int numYears;`<br>`numDays = numYears * 365;` | **Error** — `numYears` is used before being initialized |

### Style guidelines for identifiers

| Style | Example |
|---|---|
| camelCase | `numDays`, `userName` |
| kebab-case | `num-days` (not valid in Java identifiers) |
| snake_case | `num_days` |

> **We use Camel case style.**

### More style guidelines

- Refer to [**Java Code Conventions**](JavaPB_JavaCodeConventions.md)
- Or use **Google's Java Style** (see Further Reading).

**Why have code conventions?**

- 80% of the lifetime cost of a piece of software goes to maintenance.
- Hardly any software is maintained for its whole life by the original author.
- Code conventions improve the readability of the software, allowing engineers to understand new code more quickly and thoroughly.
- If you ship your source code as a product, you need to make sure it is as well packaged and clean as any other product you create.

For the conventions to work, every person writing software must conform to the code conventions. Everyone.

## 2. Floating-point Number

### Floating-point number: PI

`Math.PI` is a **static final double constant** in Java, which equals 22/7.

> Note: the actual value of `Math.PI` is `3.141592653589793` — 22/7 (≈ 3.142857) is only an approximation.

```java
double area = Math.PI * radius * radius;
```

### How do floating-point numbers work?

Video: *Floating point numbers in 3 1/2 min* by Alex Mux (see Further Reading).

## 3. Type Casting

### Implicit and explicit type conversion

| | Implicit Type Conversion | Explicit Type Conversion |
|---|---|---|
| Direction | Lower data type → Higher data type (widening) | Higher data type → Lower data type (narrowing) |
| Who does it | Compiler, automatically | Programmer, using a cast `(type)` |
| Example | `int` → `double` | `double` → `int` |

Type hierarchy shown in the diagram (low → high): `bool` → `char` → `short int` → `int` → `unsigned int` → `long` → `unsigned` → `long long` → `float` → `double` → `long double`

### Implicit type conversion

```java
expectedMales = 0.504 * numBirths;
//  double       double   int
```

1. `0.504 * 316` → `double * int`: the compiler automatically performs **int-to-double** conversion.
2. `316` becomes `316.0`.
3. The program computes `0.504 * 316.0`, yielding `159.264`.
4. `expectedMales` is a `double` variable and is assigned with that result (`159.264`).

### Explicit type conversion

```java
public class Main {
    public static void main(String[] args) {
        double x = 1.2;

        // Explicit conversion from double to int
        int sum = (int) x + 1;

        System.out.println("sum = " + sum);
    }
}
```

Output: `sum = 2`

> **Compilation error if we do not have explicit type conversion.**

### Common error: Casting final result instead of operands

```java
examAvg = (double)((midtermScore + finalScore) / 2);
double                 int            int       int
```

| Step | Expression | Type |
|---|---|---|
| 1 | `90 + 85` | `int + int` |
| 2 | `175 / 2` | `int / int` → integer division |
| 3 | `(double)(87)` | `int → double` |
| 4 | `87.0` | `double` |

> **Common error:** Casting the result of integer division does not perform the desired floating-point division.

Correct approach — cast an **operand** before the division:

```java
examAvg = (double)(midtermScore + finalScore) / 2;   // 87.5
// or
examAvg = (midtermScore + finalScore) / 2.0;          // 87.5
```

## 4. Character

### A character is internally stored as a number

```java
char userLet;
userLet = 'a';
System.out.println(userLet);   // prints: a
```

In memory, `userLet` stores the number **97** — the encoding of `'a'`. When output, it is shown as the character `a`.

| Character | Encoding |
|---|---|
| `'a'` | 97 |
| `'b'` | 98 |
| `'c'` | 99 |
| ... | ... |

## 5. String

### A string is stored as a sequence of characters in memory

Example: the string `"Julia"` stored in memory locations 501 to 506:

| Memory address | Character |
|---|---|
| 501 | `J` |
| 502 | `u` |
| 503 | `l` |
| 504 | `i` |
| 505 | `a` |
| 506 | *(empty)* |

### next() vs. nextLine()

Combining `next()` and `nextLine()` can be tricky.

**Input** (`n` = newline, `s` = space — whitespace characters are normally invisible):

```
Kindness⏎
   is contagious⏎
```

| Attempt | Code | Result |
|---|---|---|
| Attempt 1 | `str1 = scnr.next();`<br>`str2 = scnr.next();` | `str1` = `"Kindness"`, `str2` = `"is"` |
| Attempt 2 | `str1 = scnr.next();`<br>`str2 = scnr.nextLine();` | `str1` = `"Kindness"`, `str2` = `""` (blank) |
| Solution | `str1 = scnr.next();`<br>// Skip newline<br>`tmpStr = scnr.nextLine();`<br>`str2 = scnr.nextLine();` | `str1` = `"Kindness"`, `tmpStr` = `""` (blank), `str2` = `"   is contagious"` |

- `next()` reads the next token (skips leading whitespace, stops at whitespace) and **leaves the newline** in the input.
- `nextLine()` reads everything up to and including the next newline — so right after `next()`, it only reads the leftover newline and returns a blank string.

## 6. Debugging

### Basics of Debugging Your Code in IntelliJ

Video: *Debug with Confidence* (see Further Reading).

Similar feature is available in Visual Studio Code.

## Further Reading

- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [How do floating-point numbers work? (Alex Mux, YouTube)](https://www.youtube.com/watch?v=lUdRbZDBgEE)
- [Basics of Debugging Your Code in IntelliJ (YouTube)](https://www.youtube.com/watch?v=Wqt8O7arL2Y)
- [W3Schools — Java Variables](https://www.w3schools.com/java/java_variables.asp)
- [W3Schools — Java Type Casting](https://www.w3schools.com/java/java_type_casting.asp)
- [W3Schools — Java Strings](https://www.w3schools.com/java/java_strings.asp)
- [LinkedIn Learning — Java 11+ Essential Training: Work with Primitive Variables](https://www.linkedin.com/learning/java-11-plus-essential-training/work-with-primitive-variables?u=2104756)
