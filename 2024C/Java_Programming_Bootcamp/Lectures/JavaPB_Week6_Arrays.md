# Week 6 — Arrays

## 1. Array

### What is an array?

**Array = List of data having the same type**

| Declaration | Content |
|---|---|
| `int[] array` | `1`, `7`, `3`, `9` |
| `char[] array` | `'A'`, `'c'`, `'e'` |
| `String[] array` | `"Phong"`, `"Tung"`, `"Minh"`, `"Tri"`, `"Dung"` |

### Memory allocation: primitive vs array (non-primitive)

```java
// Primitive types
int a = 3;
int b = a;
b = 100;

// Non-primitive types
int[] c = { 1, 2, 3, 4 };
int[] d = c;
int[] e = { 5, 6, 7 };
int[] f = { 5, 6, 7 };
String g = "hello";
```

- **Primitive types**: the variable lives on the **Stack** and holds the value itself. `int b = a;` makes the JVM **copy** the data from `a` to `b`, so `b = 100;` only changes `b` (`a` stays `3`).
- **Non-primitive types (arrays, String)**: the variable on the **Stack** holds a **reference** pointing to the data on the **Heap**.
  - `c` and `d` point to the **same** array `{1, 2, 3, 4}` on the Heap (`int[] d = c;` copies the reference, not the data).
  - `e` and `f` point to **two separate** arrays, even though both contain `{5, 6, 7}`.
  - `g` points to the `"hello"` object on the Heap.

### Constructing an array

**Array reference variable declaration and array allocation.**

`dataType[] arrayName = new dataType[numElements];`

Initialize an array using known element values: `int[] myArray = {5, 7, 11}`

### Accessing array elements

```java
int[] myArray = {5, 7, 11}

myArray[0] // value: 5
myArray[2] // value: 11
```

## 2. Array Looping

### Loop through an array

**Common for loop structure for iterating through an array.**

```java
// Iterating through myArray
for (i = 0; i < myArray.length; ++i) {
   // Loop body accessing myArray[i]
}
```

### Loop through an array with for-each

`for ( datatype elementVariableName : array )`

```java
int[] numbers = {6, 5, 3, 2, 10, 1};

for ( int number : numbers ) {
   System.out.println(number);
}
```

## 3. Swapping 2 Variables

**Initial:** `x = 5` and `y = 7`
**Aim:** `y = 5` and `x = 7`

| ❌ Wrong | ✅ Correct (use a temporary variable) |
|---|---|
| `int x = 5;`<br>`int y = 7;`<br><br>`x = y;`<br>`y = x;` | `int x = 5;`<br>`int y = 7;`<br>`int temp = x;`<br><br>`x = y;`<br>`y = temp;` |

### Interview question

**How to swap 2 variables without using any intermediate variable?**

```java
int x = 5;
int y = 7 + x;

x = y - x;
y = y - x;
```

## 4. Modify Array

```java
int[] numbers = { 1, 2, 3, 4 ,5 };

for (int i = 0; i < numbers.length; ++i) {
   numbers[i] = numbers[i] + 1;
   System.out.print( numbers[i] + " " );

}
```

## 5. Copy Array

### Can we do this?

```java
int[] numbers = { 1, 2, 3, 4 ,5 };

int[] secondNumbers = numbers;
```

**No** — both `secondNumbers` and `numbers` array **share the same data**.

```java
int[] numbers = { 1, 2, 3, 4 ,5 };

int[] secondNumbers = numbers;

for (int i = 0; i < numbers.length; ++i) {
   secondNumbers[i] += 10;
}

System.out.println(Arrays.toString(numbers));
```

**Output:** `[11, 12, 13, 14, 15]`

## 6. 2D Arrays

```java
int row = 2;
int column = 3;

int[][] table = new int[row][column];

table[0][0] = 55;
table[1][1] = 77;
table[1][2] = 99;
```

| | **0** | **1** | **2** |
|---|---|---|---|
| **0** | `55` — `[0][0]` | `[0][1]` | `[0][2]` |
| **1** | `[1][0]` | `77` — `[1][1]` | `99` — `[1][2]` |

**What is the value at `table[0][1]`?**

→ `0` — it was never assigned, so it keeps the default value of an `int` array element.

## Further Reading

- [LinkedIn Learning — Java 11+ Essential Training: Store values in simple arrays](https://www.linkedin.com/learning/java-11-plus-essential-training/store-values-in-simple-arrays?u=2104756)
- [W3Schools — Java Arrays](https://www.w3schools.com/java/java_arrays.asp)
- [W3Schools — Java Multi-Dimensional Arrays](https://www.w3schools.com/java/java_arrays_multi.asp)
- [W3Schools Exercise — Array looping](https://www.w3schools.com/java/exercise.asp?x=xrcise_arrays_loop1)
- [W3Schools Exercise — Multi-dimensional arrays](https://www.w3schools.com/java/exercise.asp?x=xrcise_arrays_multi1)
