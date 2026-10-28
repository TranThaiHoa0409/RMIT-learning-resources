# Week 7: User-Defined Methods

## 1. Method

### How to make an avocado smoothie? — an analogy for methods

- **Ingredients** (avocado, milk, condensed milk, ice) → **Mix** (blender) → **Avocado Smoothie**

| Smoothie analogy | Programming term |
|---|---|
| Ingredients | **Parameters** |
| Mix | **Method** |
| Avocado Smoothie | **Result** |

### Method Definition

```java
public static String makeAvocadoSmoothie (
   String avocado, String milk, String condensedMilk, String ice
) {
   int rotateSpeed = 3;
   int durationInSec = 2;
   String output = "";

   System.out.printf("Put %s, %s, %s, and %s into the blender's container\n",
avocado, milk, condensedMilk, ice);

   System.out.println("Rotate the blades at speed level " + rotateSpeed);

   for (int i = 1; i <= durationInSec; ++i) {
      System.out.printf("Rotating blades for %d seconds\n", i);
   }

   System.out.println("Finish Mixing!");

   output = "Avocado Smoothie";

   return output;
}
```

### Calling Method

```java
makeAvocadoSmoothie("Avocado", "Milk", "Condensed Milk", "Ice");
```

### Assigning the Method's result to a variable

```java
String smoothie = makeAvocadoSmoothie("Avocado", "Milk", "Condensed Milk", "Ice");
```

## 2. Method Construct

All parts below are annotated on the same example method:

```java
public static double calcPizzaVolume(double pizzaDiameter, double pizzaHeight) {
   double pizzaRadius;
   double pizzaArea;
   double pizzaVolume;
   double piVal = 3.14159265;

   pizzaRadius = pizzaDiameter / 2.0;
   pizzaArea = piVal * pizzaRadius * pizzaRadius;
   pizzaVolume = pizzaArea * pizzaHeight;
   return pizzaVolume;
}
```

### Method name

- `calcPizzaVolume` is the method name.

### Method parameters

- Parameter(s): 0, 1, or more (separated by comma).
- In the example: `double pizzaDiameter, double pizzaHeight`.

### Return value

- **Return data type**: `double` in the example (`void` if no return value).
- **`return` keyword** followed by the **return value** (`pizzaVolume`).

### Access modifiers

- `public static` are the access modifiers.

### Method body

- **Local variables**: `pizzaRadius`, `pizzaArea`, `pizzaVolume`, `piVal`.
- **Statements**: the instructions that compute the result and return it.

### Summary table

| Part | In the example |
|---|---|
| Access modifiers | `public static` |
| Return data type | `double` (`void` if no return value) |
| Method name | `calcPizzaVolume` |
| Parameters (0, 1, or more, comma-separated) | `double pizzaDiameter, double pizzaHeight` |
| Local variables | `pizzaRadius`, `pizzaArea`, `pizzaVolume`, `piVal` |
| Statements | Assignments computing radius, area, volume |
| `return` keyword + return value | `return pizzaVolume;` |

## 3. Why Using Methods

- **Readability**
- **Modularity / Incremental Development**
- **Avoid Redundancy**

### Example

**Program A** — repeated code inside `main`:

```java
public static void main(String [] args) {
   ...
   // Read from input: word = "Show"
   System.out.print("--The Greatest *");
   System.out.print(word);
   System.out.println("* on Earth!--");
   ...
   // Read from input: word = "Song"
   System.out.print("--The Greatest *");
   System.out.print(word);
   System.out.println("* on Earth!--");
}
```

**Program B** — the repeated code is moved into a method:

```java
public static void printGreatest(String word) {
   System.out.print("--The Greatest *");
   System.out.print(word);
   System.out.println("* on Earth!--");
}

public static void main(String [] args) {
   ...
   // Read from input: word = "Show"
   printGreatest(word);
   ...
   // Read from input: word = "Song"
   printGreatest(word);
}
```

### Tips

- Use concise method names: starting with a verb.
- Keep the method short: one method – one task.

## 4. Passing Arguments

### Pass by value

```java
import java.util.Scanner;

public class TimeConverter {
   public static void convHrMin (int timeVal, int hrVal, int minVal) {
      hrVal  = timeVal / 60;
      minVal = timeVal % 60;
   }

   public static void main (String [] args) {
      Scanner scnr = new Scanner(System.in);
      int totTime;
      int usrHr;
      int usrMin;

      totTime = 0;
      usrHr = 0;
      usrMin = 0;

      System.out.print("Enter total minutes: ");
      totTime = scnr.nextInt();

      convHrMin(totTime, usrHr, usrMin);

      System.out.print("Equals: " + usrHr + " hrs " + usrMin + " mins");
   }
}
```

Output:

```
Enter total minutes: 156
Equals: 0 hrs 0 mins
```

- Upon return, `convHrMin`'s local variables are discarded.
- `hrVal` and `minVal` are local copies that do not impact `usrHr` and `usrMin`.

### Pass by reference

```java
import java.util.Scanner;

public class ArrayReversal {

   public static void reverseVals(int[] arrVals) {
      int i;              // Loop index
      int tempValue;

      for (i = 0; i < (arrVals.length / 2); ++i) {
         tempValue = arrVals[i]; // Do swap
         arrVals[i] = arrVals[arrVals.length - 1 - i];
         arrVals[arrVals.length - 1 - i] = tempValue;
      }
   }

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      final int NUM_VALUES = 8;               // Array size
      int[] userVals = new int[NUM_VALUES];   // User values
      int i;                                  // Loop index

      // Prompt user to populate array
      System.out.println("Enter " + userVals.length + " values...");
      for (i = 0; i < userVals.length; ++i) {
         System.out.print("Value:  ");
         userVals[i] = scnr.nextInt();
      }

      // Call method to reverse array values
      reverseVals(userVals);

      // Print updated arrays
      System.out.print("\nNew values: ");
      for (i = 0; i < userVals.length; ++i) {
         System.out.print(userVals[i] + " ");
      }
      System.out.println();
   }
}
```

Output:

```
Enter 8 values...
Value:  10
Value:  20
Value:  30
Value:  40
Value:  50
Value:  60
Value:  70
Value:  80

New values: 80 70 60 50 40 30 20 10
```


## 5. Testing

### Unit Testing

- Testing small units of a program, typically methods.
- Quick example: testing if `a + b` returns 3.

```java
int a = 1;
int b = 1;
assert (a + b == 3) : "a + b is not 3";
```

Result:

```
Exception in thread "main" java.lang.AssertionError: a + b is not 3
        at Day7.Main.main(Main.java:13)
```

- Note that assertions are **not enabled by default**. The command-line option `-ea` is necessary at run-time. Ex: `java -ea YourProgram`

## 6. Method Overloading

Methods can have the same name but different parameters and/or return types.

```java
public static int triple(int number) {
   return number * 3;
}

public static String triple(String pattern) {
   return pattern.repeat(3);
}

public static void main(String[] args) {
   int[] testVector = {-3, -1, 0, 5, 9};
   int[] outputs = {-9, -3, 0, 15, 27};

   // Test Bench
   for (int i = 0; i < testVector.length; i++) {
      assert ( triple(testVector[i]) == outputs[i] )
            : String.format("Input %d * 3 does not equal output %d", testVector[i], outputs[i]);
   }
}
```

## 7. Parameter Checking

```java
public class DatePrinterWithCheck {
   public static void printDate(int currDay, int currMonth, int currYear) {

      // Parameter error checking
      if ((currDay < 1) || (currDay > 31)) {
         System.out.println("Invalid day (" + currDay + "). Using 1.");
         currDay = 1;
      }

      if ((currMonth < 1) || (currMonth > 12)) {
         System.out.println("Invalid month (" + currMonth + "). Using 1.");
         currMonth = 1;
      }

      // Begin method's normal behavior
      System.out.print(currMonth + "/" + currDay + "/" + currYear);
   }

   public static void main(String[] args) {
      printDate(30, 7, 2012);
      System.out.print("\n\n");

      printDate(40, 7, 2012);
      System.out.print("\n\n");

      printDate(30, 13, 2012);
      System.out.print("\n\n");
   }
}
```

Output:

```
7/30/2012

Invalid day (40). Using 1.
7/1/2012

Invalid month (13). Using 1.
1/30/2012
```

## 8. Common Errors

- No Adjustment after Copy-Paste
- Return wrong data
- No return statement for non-void methods
- Misunderstood parameters references (See PA 9.19.2 & PA 9.19.7) and method scope

### Copy-paste error

The local variable `fahVal` / `celVal` is not adjusted after copy-paste, so `fah2Cel` returns the wrong variable (`fahVal`, the parameter, instead of the computed `celVal`).

```java
public static double cel2Fah(double celVal) {
   double convTmp;
   double fahVal;

   convTmp = (9.0 / 5.0) * celVal;
   fahVal = convTmp + 32;

   return fahVal;
}

public static double fah2Cel(double fahVal) {
   double convTmp;
   double celVal;

   convTmp = fahVal - 32;
   celVal = convTmp * (5.0 / 9.0);

   return fahVal;   // ERROR: should return celVal
}
```

### Array references cannot be changed inside methods

```java
double[] diameterTires = {25.3, 36.2, 48.1};

reverseWRONG(diameterTires);

...

public static void reverseWRONG(double[] arrayRef) {
   double[] result = new double[arrayRef.length];
   int index;

   // fill the result array from the tail to the head
   for (index = arrayRef.length - 1; index >= 0; --index){
      result[index] = arrayRef[arrayRef.length - index - 1];
   }

   arrayRef = result;
}
```

- `arrayRef = result;` only changes the **local copy of the reference** inside `reverseWRONG`; `diameterTires` in `main` still points to the original array.

## Further Reading

**Slide exercises (w3schools)**

- [Exercise: Method parameters](https://www.w3schools.com/java/exercise.asp?x=xrcise_methods_param3)
- [Exercise: Method return values](https://www.w3schools.com/java/exercise.asp?x=xrcise_methods_return4)
- [Exercise: Method overloading](https://www.w3schools.com/java/exercise.asp?x=xrcise_methods_overloading3)

**Extra reading (Canvas)**

- [LinkedIn Learning – Java 11+ Essential Training: Create reusable code with methods](https://www.linkedin.com/learning/java-11-plus-essential-training/create-reusable-code-with-methods?resume=false&u=2104756)
- [w3schools – Java Methods](https://www.w3schools.com/java/java_methods.asp)
- [w3schools – Java Method Parameters](https://www.w3schools.com/java/java_methods_param.asp)
- [w3schools – Java Method Overloading](https://www.w3schools.com/java/java_methods_overloading.asp)
- [w3schools – Java Scope](https://www.w3schools.com/java/java_scope.asp)
- [w3schools – Java Recursion](https://www.w3schools.com/java/java_recursion.asp)
