# Programming Bootcamp 1 — Week 11

## Searching

### Linear search

Start from the **beginning** of a list and checks **each** element until the search key is found or the end of the list is reached.

- Linear search starts at the first element and searches elements one-by-one.
- Linear search will compare all elements if the search key is not present.

### Binary search

Requires a **sorted** array.

- Binary search efficiently searches a sorted list by reducing the search space by half each iteration.

### Complexity – Binary vs. Linear search

| Algorithm | Complexity |
|---|---|
| Linear Search | O(n) |
| Binary Search | O(log₂ n) |

## O Notation

### Big O notation

A **mathematical** way of describing how a function generally behaves in relation to the input size.

Example (algorithm steps `5 + 13·N + 7·N²`):

```
Big O notation:  O(5 + 13·N + 7·N²) = O(7·N²) = O(N²)
```

- **Rule 1:** If f(x) is a sum of several terms, the highest order term is kept and others are discarded.
- **Rule 2:** If f(x) has a term that is a product of several factors, all constants are omitted.

### Rule for Big O notation

| Composite function | Big O notation |
|---|---|
| c · O(f(x)) | O(f(x)) |
| c + O(f(x)) | O(f(x)) |
| g(x) · O(f(x)) | O(g(x) · f(x)) |
| g(x) + O(f(x)) | O(g(x) + f(x)) |

### Growth rate of common functions

Computational complexity graphing tool: number of computations vs number of elements (n), for these functions: `1 → log n → n → n log n → n² → 2ⁿ → n!`

## Algorithm Analysis

### Worst-case algorithm analysis

The runtime complexity for an input that results in the **longest** execution.

Example: finding the max value.

```java
maxVal = numbers[0];
for (i = 0; i < N; ++i) {
    if (numbers[i] > maxVal) {
        maxVal = numbers[i];
    }
}
```

| Part of code | Operations |
|---|---|
| `maxVal = numbers[0];` | 1 operation |
| `i = 0` | 1 operation (only executed once) |
| Loop runs | N iterations |
| `i < N; ++i` (per iteration) | 2 operations |
| `if (numbers[i] > maxVal)` + `maxVal = numbers[i];` (per iteration) | 2 operations (worst-case) |
| Final loop comparison `i < N` | 1 final comparison operation |

```
f(N) = 1 + 1 + 1 + N(2 + 2) = 3 + 4N = O(N)
```

## Sorting

The process of **converting** a list of elements into **ascending** (or **descending**) order.

### Selection sort - General idea

- Treat the input as two parts: **sorted** and **unsorted** parts.
- Repeatedly select the proper **next** value to move from the unsorted part to the end of the sorted part.

### Insertion sort - General idea

- Treat the input as two parts: **sorted** and **unsorted** parts.
- Repeatedly insert the **next** value from the unsorted part into the correct location in the sorted part.

```java
for (i = 1; i < numbers.length; ++i) {
    j = i;
    // Insert numbers[i] into sorted part
    // stopping once numbers[i] in correct position
    while (j > 0 && numbers[j] < numbers[j - 1]) {

        // Swap numbers[j] and numbers[j - 1]
        temp = numbers[j];
        numbers[j] = numbers[j - 1];
        numbers[j - 1] = temp;
        --j;
    }
}
```

### Quick sort - General idea

Repeatedly partition the input into **low** and **high** parts (each part unsorted), and then **recursively** sorts each of those parts.

```java
void quicksort(int[] numbers, int i, int k) {
    int j;

    // Base case: If 1 or zero elements, partition is already sorted
    if (i >= k) {
        return;
    }

    /* Partition the array.
       Value j is the location of last element in low partition. */
    j = partition(numbers, i, k);

    // Recursively sort low and high partitions
    quicksort(numbers, i, j);
    quicksort(numbers, j + 1, k);
}
```

### Merge sort - General idea

Divide a list into **two** halves, recursively **sort** each half, and then **merge** the sorted halves to produce a sorted list.

## Further Reading

### Visualizers
- [Searching and Sorting Visualizer](https://algobook-io.github.io/Searching-Sorting-Visualizer/#/)

### W3Schools DSA – Algorithm pages
- [Linear Search](https://www.w3schools.com/dsa/dsa_algo_linearsearch.php)
- [Binary Search](https://www.w3schools.com/dsa/dsa_algo_binarysearch.php)
- [Bubble Sort](https://www.w3schools.com/dsa/dsa_algo_bubblesort.php)
- [Selection Sort](https://www.w3schools.com/dsa/dsa_algo_selectionsort.php)
- [Insertion Sort](https://www.w3schools.com/dsa/dsa_algo_insertionsort.php)
- [Quicksort](https://www.w3schools.com/dsa/dsa_algo_quicksort.php)
- [Merge Sort](https://www.w3schools.com/dsa/dsa_algo_mergesort.php)
- [Counting Sort](https://www.w3schools.com/dsa/dsa_algo_countingsort.php)
- [Radix Sort](https://www.w3schools.com/dsa/dsa_algo_radixsort.php)

### W3Schools DSA – Exercises
- [Linear Search exercise](https://www.w3schools.com/dsa/exercise.php?filename=exercise_algo_linearsearch1)
- [Binary Search exercise](https://www.w3schools.com/dsa/exercise.php?filename=exercise_algo_binarysearch1)
- [Selection Sort exercise](https://www.w3schools.com/dsa/exercise.php?filename=exercise_algo_selectionsort1)
- [Quicksort exercise](https://www.w3schools.com/dsa/exercise.php?filename=exercise_algo_quicksort1)

### Videos
- [AlgoRythmics - Selection Sort](https://www.youtube.com/watch?v=Ns4TPTC8whw)
- [AlgoRythmics - Insertion Sort](https://www.youtube.com/watch?v=ROalU379l3U)
- [Quick Sort (folk dance demo)](https://www.youtube.com/watch?v=3San3uKKHgg)
- [Merge Sort (folk dance demo)](https://www.youtube.com/watch?v=dENca26N6V4)

### LinkedIn Learning
- [Learning Java 8 - Sorting arrays](https://www.linkedin.com/learning/learning-java-8/sorting-arrays?u=2104756)
