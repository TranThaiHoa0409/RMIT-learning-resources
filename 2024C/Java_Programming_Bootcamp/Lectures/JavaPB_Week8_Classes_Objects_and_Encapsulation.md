# Programming Bootcamp 1 – Week 8: Classes, Objects and Encapsulation

## Introduction

### Object-oriented concepts

The Four Pillars of OOP:
- Encapsulation
- Inheritance
- Polymorphism
- Abstraction

## Object

An **object** is a grouping of **data** (variables) and **operations** (methods) that can be performed on that data.

- Car class vs. Car objects: the `Car` class defines Model, Price, Color, Build year; each car object holds its own values (e.g. Model AAA / 10K / Orange / 2015, Model BBB / 15K / Blue / 2018, Model CCC / 45K / Green / 2015).
- Dog class vs. Dog objects: one `DOG` class (size, breed, name, `bark()`), many dog objects.

### Abstraction

**High-level representation** that **hides** internal low-level details.

## Class = Object definition

- **Class** represents the **object type**.
- **Class** declares **properties** and **methods** of an object.
- Properties are also called **Attributes** or **Fields**.

UML class diagram of `Student`:

| Section | Members |
|---|---|
| Name | `Student` |
| Properties / Attributes / Fields | `name: String`<br>`age: int`<br>`rmitId: String`<br>`energy: double` |
| Methods | `code(String[] problems, double hours): double`<br>`eat(String food): double`<br>`sleep(double hour): double` |

## Constructor

Used to **instantiate/create** an object.

```java
public class Student {
    String name;

    public Student() {
        name = "Default name";
    }
}

Student student = new Student();
```

### Constructor overloading

**Multiple constructor definitions** in the same class (different parameter lists).

```java
public class Student {
    String name;

    public Student() {
        name = "Default name";
    }

    public Student(String inputName) {
        name = inputName;
    }
}
```

```java
Student student1 = new Student();
Student student2 = new Student("Phong");
```

### Constructor overloading – Default constructor

If the programmer defines a constructor, the compiler does **not** automatically define a default no-arg constructor.

```java
public class Restaurant {
    public Restaurant(String initName, int initRating) {
        ...
    }

    // No other constructors
}

public class RestaurantFavorites {
    public static void main(String[] args) {
        Restaurant foodPlace = new Restaurant();   // Error
        ...
    }
}
```

Compiler error:

```
RestaurantFavorites.java:3: cannot find symbol
symbol  : constructor Restaurant()
location: class Restaurant
      Restaurant foodPlace = new Restaurant();
```

### Member access operator "."

Used to access **properties or methods** on an object.

```java
public class RestaurantFavorites {
    public static void main(String[] args) {
        Restaurant favLunchPlace = new Restaurant();
        Restaurant favDinnerPlace = new Restaurant();

        favLunchPlace.setName("Central Deli");
        favLunchPlace.setRating(4);

        favDinnerPlace.setName("Friends Cafe");
        favDinnerPlace.setRating(5);

        System.out.println("My favorite restaurants: ");
        favLunchPlace.print();
        favDinnerPlace.print();
    }
}
```

Output:

```
My favorite restaurants:
Central Deli -- 4
Friends Cafe -- 5
```

Memory: `favLunchPlace` holds "Central Deli" and 4; `favDinnerPlace` holds "Friends Cafe" and 5.

## Access modifier

Apply to the **properties**, **methods**, and **constructors**.

| Modifier | UML | Meaning |
|---|---|---|
| **Public** | `+` | accessible for class user |
| **Private** | `-` | accessible only for member methods, not class user |

### What is "this"?

Refers to the **current object** in a method or constructor.

```java
// ShapeSquare.java
public class ShapeSquare {
    // Private fields
    private double sideLength;

    // Public methods
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
        // Field member    Parameter
    }

    public double getArea() {
        return sideLength * sideLength; // Both refer to field
    }
}
```

```java
// ShapeTest.java
public class ShapeTest {
    public static void main(String[] args) {
        ShapeSquare square1 = new ShapeSquare();

        square1.setSideLength(1.2);
        System.out.println("Square's area: " + square1.getArea());
    }
}
```

Output:

```
Square's area: 1.44
```

## Accessor & Mutator

Both Accessor & Mutator are **methods**.

| Type | Purpose | Example |
|---|---|---|
| **Accessor** | **Get** the **value** of an object's **property** | `public int getAge() { return age; }` |
| **Mutator** | **Modify** the **value** of an object's **property** | `public void setAge(int age) { this.age = age; }` |

```java
public int getAge() {
    return age;
}
```

```java
public void setAge(int age) {
    this.age = age;
}
```

Getter and setter methods can be generated quickly by IntelliJ.

## Object within an Object

An object can have **another object as its property**.

| `Student` | `Lecturer` |
|---|---|
| `name: String`<br>`age: int`<br>`rmitId: String`<br>`energy: double`<br>`lecturer: Lecturer` | `name: String`<br>`age: int`<br>`rmitId: String`<br>`salary: double`<br>`energy: double` |
| `code(String[] problems, double hours): double`<br>`eat(String food): double`<br>`sleep(double hour): double` | `teach(int groupId, String roomName): double`<br>`eat(String food): double`<br>`sleep(double hour): double` |

## Objects and References

A **reference** is a variable type that refers to an **object**.

```java
// Reference variable does not refer
// to any object upon definition
TimeHrMin travelTime;

// New allocates memory for object, returns
// reference to object
travelTime = new TimeHrMin();

travelTime.setHour(2);
travelTime.setMinute(40);

travelTime.printTime();
```

Memory view:

| Step | Memory |
|---|---|
| After `TimeHrMin travelTime;` | `travelTime` (address 94) = `null` |
| After `new TimeHrMin()` and the setters | `travelTime` (address 94) = `96` (reference); object at 96–97: `hrVal` = 2, `minVal` = 40 |

- Memory for `TimeHrMin` object instance vs. memory for the reference variable are shown in different colours in the slide.

## Null Value

**Null** means **absence** of a value or a **non-existent** object.

```java
Student stu1;
stu1.eat("Hamburger");     // NullPointerException
```

```java
Student stu2 = new Student();
stu2.eat("Hotdog");
```

- Calling a method on a reference that is `null` causes a **NullPointerException**.

## Static Field and Method

### Static properties are shared among objects

A `static` field belongs to the class, so all objects share the same copy (it lives in static memory).

```java
// Store.java
public class Store {
    // Declare and initialize public static field
    public static int nextId = 101;

    // Define private fields
    private String name;
    private String type;
    private int id;

    public Store(String storeName, String storeType) {
        name = storeName;
        type = storeType;
        id = nextId;

        ++nextId;   // Increment each time a Store object is created
    }

    ...
}
```

```java
// NewStores.java
public class NewStores {
    public static void main(String[] args) {
        Store store1 = new Store("Macy's", "Department");
        Store store2 = new Store("Albertsons", "Grocery");
        Store store3 = new Store("Ace", "Hardware");

        System.out.println("Store 1's ID: " + store1.getId());
        System.out.println("Store 2's ID: " + store2.getId());
        System.out.println("Store 3's ID: " + store3.getId());
        System.out.println("Next ID: " + Store.nextId);
    }
}
```

Console output:

```
Store 1's ID: 101
Store 2's ID: 102
Store 3's ID: 103
Next ID: 104
```

| Memory | Contents |
|---|---|
| Static memory | `Store.nextId` = 104 |
| `store1` | name = Macy's, type = Department, id = 101 |
| `store2` | name = Albertsons, type = Grocery, id = 102 |
| `store3` | name = Ace, type = Hardware, id = 103 |

### Static method does not require object declaration to call

Static member method used to access a private static field.

```java
// Store.java
public class Store {
    // Declare and initialize private static field
    private static int nextId = 101;

    // Define private fields
    private String name;
    private String type;
    private int id;

    public Store(String storeName, String storeType) {
        name = storeName;
        type = storeType;
        id = nextId;

        ++nextId;   // Increment each time a Store object is created
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }
}
```

## ArrayList

Provides convenient array operations **without fixed size**.

| Method | Description | Example |
|---|---|---|
| `add(element)` | Create space for and add the element at the end of the list. | `// List originally empty`<br>`valsList.add(31); // List now: 31`<br>`valsList.add(41); // List now: 31 41` |
| `get(index)` | Returns the element at the specified list location known as the *index*. Indices start at 0. | `// List originally: 31 41 59. Assume x is an int.`<br>`x = valsList.get(0); // Assigns 31 to x`<br>`x = valsList.get(1); // Assigns 41`<br>`x = valsList.get(2); // Assigns 59`<br>`x = valsList.get(3); // Error: No such element` |
| `set(index, element)` | Replaces the element at the specified position in this list with the specified element. | `// List originally: 31 41 59`<br>`valsList.set(1, 119); // List now 31 119 59` |
| `size()` | Returns the number of list elements. | `// List originally: 31 41 59. Assume x is an int.`<br>`x = valsList.size(); // Assigns x with 3` |

## Further Reading

**Extra reading**

- [LinkedIn Learning – Java 11+ Essential Training: Declare and use custom classes](https://www.linkedin.com/learning/java-11-plus-essential-training/declare-and-use-custom-classes?u=2104756)
- [w3schools – Java OOP](https://www.w3schools.com/java/java_oop.asp)
- [w3schools – Java Classes and Objects](https://www.w3schools.com/java/java_classes.asp)
- [w3schools – Java Class Attributes](https://www.w3schools.com/java/java_class_attributes.asp)
- [w3schools – Java Class Methods](https://www.w3schools.com/java/java_class_methods.asp)
- [w3schools – Java Constructors](https://www.w3schools.com/java/java_constructors.asp)
- [w3schools – Java Modifiers](https://www.w3schools.com/java/java_modifiers.asp)
- [w3schools – Java Encapsulation](https://www.w3schools.com/java/java_encapsulation.asp)
- [w3schools – Java ArrayList](https://www.w3schools.com/java/java_arraylist.asp)

**Exercises (from slides)**

- [w3schools Exercise – Constructors](https://www.w3schools.com/java/exercise.asp?x=xrcise_constructors1)
- [w3schools Exercise – Modifiers](https://www.w3schools.com/java/exercise.asp?x=xrcise_modifiers1)
- [w3schools Exercise – Class Methods](https://www.w3schools.com/java/exercise.asp?x=xrcise_class_methods1)
- [w3schools Exercise – ArrayList](https://www.w3schools.com/java/exercise.asp?x=xrcise_arraylist1)

**Other**

- [RMIT Course Experience Survey (CES)](https://surveys.rmit.edu.au)
