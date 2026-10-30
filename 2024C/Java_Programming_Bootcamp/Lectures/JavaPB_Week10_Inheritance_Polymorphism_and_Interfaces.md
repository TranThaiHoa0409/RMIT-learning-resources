# Programming Bootcamp 1 — Week 10: Inheritance, Polymorphism and Interfaces

## Inheritance

### Children & Parents (analogy)

- Some of the **same** biological features (eyes, nose).
- Different personality, appearance (birthmark, mole).
- → Both share some properties.
- → Children have their own unique properties.

### Example: Employee hierarchy

- `Employee` (base class, shown in italics)
  - `SalariedEmployee`
  - `CommissionEmployee`
    - `BasePlusCommissionEmployee`
  - `HourlyEmployee`

### Example: Inheritance tree

- Tree: `GenericItem` → `ProduceItem` (→ `FruitItem`, `DairyItem`) and `BookItem` (→ `TextbookItem`, `AudiobookItem`).
- Selected class `TextbookItem` pseudocode (members available through inheritance):

```
private:
    private String itemName;        // from GenericItem
    private int itemQuantity;       // from GenericItem
    private String bookTitle;       // from BookItem
    private String editionName;     // from TextbookItem
public:
    public void setName(String newName)         // GenericItem
    public void setQuantity(int newQty)         // GenericItem
    public void printItem()                     // GenericItem
    public void setTitle(String newTitle)       // BookItem
    public String getTitle()                    // BookItem
    public void setEdition(String newEdition)   // TextbookItem
    public string getEdition()                  // TextbookItem
```

```java
public class GenericItem {
   private String itemName;
   private int itemQuantity;

   public void setName(String newName) {
      itemName = newName;
   }

   public void setQuantity(int newQty) {
      itemQuantity = newQty;
   }

   public void printItem() {
      System.out.println(itemName + " " +
                         itemQuantity);
   }
}
```

```java
public class BookItem extends GenericItem {
   private String bookTitle;

   public void setTitle(String newTitle) {
      bookTitle = newTitle;
   }

   public String getTitle() {
      return bookTitle;
   }
}
```

```java
public class TextbookItem extends BookItem {
   private String editionName;

   public void setEdition(String newEdition) {
      editionName = newEdition;
   }

   public String getEdition() {
      return editionName;
   }
}
```

## Access Modifiers

### Access specifiers for class members

| Specifier | Description |
|---|---|
| `private` | Accessible by self. |
| `protected` | Accessible by self, derived classes, and other classes in the same package. |
| `public` | Accessible by self, derived classes, and everyone else. |
| no specifier | Accessible by self and other classes in the same package. |

### Example: Access specifiers

```java
// Business.java
public class Business {
   protected String name;
   private String address;

   public void printMembers() {
      // Print information ...
   }
}
```

```java
// Restaurant.java
public class Restaurant extends Business {
   private int rating;

   public void displayRestaurant() {
      // Attempted accesses
      printMembers();            // OK
      name = "Gyro Hero";        // OK
      address = "5 Fifth St";    // ERROR
   }

   // Other class members ...
}
```

```java
// InheritanceAccessEx.java
public class InheritanceAccessEx {
   public static void main(String[] args) {
      Business business = new Business();
      Restaurant restaurant = new Restaurant();

      // Attempted accesses
      business.printMembers();             // OK
      business.name = "Gyro Hero";         // OK
      business.address = "5 Fifth St";     // ERROR

      restaurant.printMembers();           // OK
      restaurant.name = "Gyro Hero";       // OK
      restaurant.rating = 5;               // ERROR

      // Other instructions ...
   }
}
```

- `private` members of the base class (`address`) are **not** accessible from the derived class.
- `private` members of a class (`rating`) are not accessible from other classes such as `InheritanceAccessEx`.
- `protected` members (`name`) are accessible from the derived class (and, per the table above, from other classes in the same package).

## Overriding Methods

### Example: Overriding member methods

```java
// Business.java
public class Business {
   protected String name;
   protected String address;
   ...

   public String getDescription() {
      return name + " -- " + address;
   }
}
```

```java
// Restaurant.java
public class Restaurant extends Business {
   private int rating;
   ...

   @Override
   public String getDescription() {
      return name + " -- " + address +
         "\n  Rating: " + rating;
   }
}
```

```java
// OverridingExample
public class OverridingExample {
   public static void main(String[] args) {
      Restaurant favoritePlace = new Restaurant();
      favoritePlace.setName("Friends Cafe");
      favoritePlace.setAddress("500 W 2nd Ave");
      favoritePlace.setRating(5);
      System.out.println(favoritePlace.getDescription());
   }
}
```

Output:

```
Friends Cafe -- 500 W 2nd Ave
  Rating: 5
```

- The part `name + " -- " + address` in `Restaurant.getDescription()` is the **same** as the base class version — it can be replaced by a call to `super.getDescription()`.

### Example: Overriding vs. Overloading

| Case | Base class `Appliance` | Subclass `Toaster` | Result |
|---|---|---|---|
| Changed arguments | `boolean turnOn()`, `boolean turnOff()` | `boolean turnOn(int level)` | **NOT an override** — can't change the arguments in an overriding method. It is actually a legal **overLOAD**, but not an overRIDE. |
| Restricted access level | `public boolean turnOn()`, `public boolean turnOff()` | `private boolean turnOn()` | **NOT LEGAL** — not a legal override because you restricted the access level. Nor is it a legal overload, because you didn't change the arguments. |

## Object Class

### The Object class

- Serves as the **base class for all other classes** and does not have a base class.
- Just *some* of the methods of class `Object`:
  - `boolean equals()`
  - `Class getClass()`
  - `int hashCode()`
  - `String toString()`
- Every class you write inherits all the methods of class `Object` — including methods you didn't even know you had.

## Polymorphism

### Same method name, different behaviour

```java
class Animal {
   public void makeSound() {
      System.out.println("Animal sound");
   }
}

class Cat extends Animal {
   public void makeSound() {
      System.out.println("Meow meow!");
   }
}

class Dog extends Animal {
   public void makeSound() {
      System.out.println("Woof woof!");
   }
}
```
### Example: Runtime polymorphism

```java
// GenericItem.java
public class GenericItem {
   public void setName(String newName) {
      itemName = newName;
   }

   public void setQuantity(int newQty) {
      itemQuantity = newQty;
   }

   public void printItem() {
      System.out.println(itemName + " " + itemQuantity);
   }

   protected String itemName;
   protected int itemQuantity;
}
```

```java
// ProduceItem.java
public class ProduceItem extends GenericItem { // ProduceItem derived from GenericItem
   public void setExpiration(String newDate) {
      expirationDate = newDate;
   }

   public String getExpiration() {
      return expirationDate;
   }

   @Override
   public void printItem() {
      System.out.println(itemName + " " + itemQuantity
                         + " (Expires: " + expirationDate + ")");
   }

   private String expirationDate;
}
```

```java
// ItemInventory.java
import java.util.ArrayList;

public class ItemInventory {
   public static void main(String[] args) {
      GenericItem genericItem1;
      ProduceItem produceItem1;
      ArrayList<GenericItem> inventoryList = new ArrayList<GenericItem>();
      int i;

      genericItem1 = new GenericItem();
      genericItem1.setName("Smith Cereal");
      genericItem1.setQuantity(9);

      produceItem1 = new ProduceItem();
      produceItem1.setName("Apple");
      produceItem1.setQuantity(40);
      produceItem1.setExpiration("May 5, 2012");

      genericItem1.printItem();
      produceItem1.printItem();

      // More common: Collection (e.g., ArrayList) of objs
      // Polymorphism -- Correct printItem() called
      inventoryList.add(genericItem1);
      inventoryList.add(produceItem1);
      System.out.println("\nInventory: ");
      for (i = 0; i < inventoryList.size(); ++i) {
         inventoryList.get(i).printItem(); // Calls correct printItem()
      }
   }
}
```

Output:

```
Smith Cereal 9
Apple 40 (Expires: May 5, 2012)

Inventory:
Smith Cereal 9
Apple 40 (Expires: May 5, 2012)
```

- `inventoryList` holds `GenericItem` references, yet each `inventoryList.get(i).printItem()` call runs the **correct** version (`GenericItem` vs `ProduceItem`) for the actual object.

## Abstract Class

### Key idea

- **Method Declaration** from **parent**
- **Specific Implementation** by **children**

### Example: abstract `Vehicle`

```java
abstract class Vehicle

public abstract void move();
```

| Object | Call | How it moves |
|---|---|---|
| bus | `bus.move()` | Gasoline + Diesel Engine |
| electric train | `electricTrain.move()` | Pantograph + Electric Engine |

### Abstract class definition

- Guides the design of subclasses but **cannot itself be instantiated** as an object.

| Class | Members |
|---|---|
| `Shape` (abstract) | +Get/set name<br>+Get/set color<br>+Compute area *(abstract)* |
| `Circle` (derived from `Shape`) | +Get/set center point<br>+Get/set radius<br>+Compute area |

- Objects: `shape1`, `shape2` ✗ (cannot be created); `circle1`, `circle2` ✓.

### Example: A Shape abstract class

```java
// Shape.java implements the Shape base class
public abstract class Shape {

   protected Point position;

   abstract double computeArea();

   public Point getPosition() {
      return this.position;
   }

   public void setPosition(Point position) {
      this.position = position;
   }

   public void movePositionRelative(Point position) {
      double x = this.position.getX() + position.getX();
      double y = this.position.getY() + position.getY();

      this.position.setX(x);
      this.position.setY(y);
   }
}
```

```java
// Circle.java defines a Circle class
public class Circle extends Shape {

   private double radius;

   public Circle(Point center, double radius) {
      this.radius = radius;
      this.position = center;
   }

   @Override
   public double computeArea() {
      return (Math.PI * Math.pow(radius, 2));
   }
}
```

```java
// Rectangle.java defines a Rectangle class
public class Rectangle extends Shape {

   private double length, height;

   Rectangle(Point upperLeft, double length, double height)
   {
      this.position = upperLeft;
      this.length = length;
      this.height = height;
   }

   @Override
   public double computeArea() {
      return (length * height);
   }
}
```

## UML Class Diagrams

### Example: UML class diagrams

**Legend:** class name · attribute name · attribute type · method name · method return type · access

| Class | Attributes | Methods |
|---|---|---|
| `Circle` | `- radius: double`<br>`- center: Point` | `+ computeArea(): double` |
| `Square` | `- size: double`<br>`- center: Point` | `+ computeArea(): double` |

- Access symbols: `-` = private, `+` = public.
- **Inheritance:** a hollow-triangle arrow points from the subclass to the base class — "Circle inherits from Shape".
  - `Shape` (italic = abstract): `+computeArea(): double`
  - `Circle`: `-radius: double`, `-center: Point`, `+computeArea(): double`

## Interface

### Enforce Rules on Classes (plug analogy)

- German socket: **Type F** — Type A plug ✗; Type C ✓; Type F ✓.
- Vietnam socket: **Type A + C** — Type A ✓; Type C ✓; Type F ✓ (as shown on the slide).
- An interface works like the socket standard: it sets the **rules** a class must follow.

### Example: UML interfaces

**(a)** `ClassB` implements interface `ClassA`

```java
public interface ClassA {
   public void doNeatThings();
}

public class ClassB implements ClassA {
   @Override
   public void doNeatThings() {
      System.out.println("Does neat things!");
   }
}
```

**(b)** `ClassB` implements **two** interfaces (`ClassA`, `ClassC`)

```java
public interface ClassA {
   public void doNeatThings();
}

public interface ClassC {

}

public class ClassB implements ClassA, ClassC {
   @Override
   public void doNeatThings() {
      System.out.println("Does neat things!");
   }
}
```

**(c)** `ClassB` extends **abstract class** `ClassA` (solid line, for comparison)

```java
public abstract class ClassA {
   public abstract void doNeatThings();
}

public class ClassB extends ClassA {
   @Override
   public void doNeatThings() {
      System.out.println("Does neat things!");
   }
}
```

**(d)** `ClassB` extends abstract class `ClassA` **and** implements interface `ClassC`

```java
public abstract class ClassA {
   public abstract void doNeatThings();
}

public interface ClassC {

}

public class ClassB extends ClassA implements ClassC {
   @Override
   public void doNeatThings() {
      System.out.println("Does neat things!");
   }
}
```

- UML notation: interface = `<<interface>>` box; `implements` = **dashed** line with hollow arrow; `extends` = **solid** line with hollow arrow; abstract class/method names are in *italics*.

### Example

```java
public interface MultiMedia {
   public void printDuration();
}

public interface Movie {
   public void printDirector();
}

public class PrintMedia {
   public static void main(String[] args) {
      Media med = new Media();

      med.setDirector("Christopher Nolan");
      med.setDuration(152);

      med.printDuration();
      med.printDirector();
   }
}
```

```java
public class Media implements MultiMedia, Movie {
   int duration;
   String director;

   public void setDuration(int setDuration) {
      duration = setDuration;
   }

   public void setDirector(String setDirector) {
      director = setDirector;
   }

   @Override
   public void printDuration() {
      System.out.println(duration + " minutes");
   }

   @Override
   public void printDirector() {
      System.out.println("Director: " + director);
   }
}
```

Output:

```
152 minutes
Director: Christopher Nolan
```

## Further Reading

**Course admin**

- [RMIT Course Experience Survey](https://surveys.rmit.edu.au)
- [Java Programming Bootcamp — course overview](http://www1.rmit.edu.au/courses/054079)
- [COSC2801 (Melbourne) — assessment tasks](http://www1.rmit.edu.au/courses/COSC28012450)
- [COSC3054/COSC3055 (Vietnam) — assessment tasks](http://www1.rmit.edu.au/courses/COSC30542493)

**Exercises (from slides)**

- [W3Schools exercise — Java modifiers](https://www.w3schools.com/java/exercise.asp?x=xrcise_modifiers1)
- [W3Schools exercise — Java polymorphism](https://www.w3schools.com/java/exercise.asp?x=xrcise_polymorphism1)
- [W3Schools exercise — Java abstraction](https://www.w3schools.com/java/exercise.asp?x=xrcise_abstract1)
- [W3Schools exercise — Java interfaces](https://www.w3schools.com/java/exercise.asp?x=xrcise_interface1)

**Extra reading (Canvas)**

- [LinkedIn Learning — Java 11+ Essential Training: About inheritance](https://www.linkedin.com/learning/java-11-plus-essential-training/about-inheritance?resume=false&u=2104756)
- [W3Schools — Java Polymorphism](https://www.w3schools.com/java/java_polymorphism.asp)
- [W3Schools — Java Modifiers](https://www.w3schools.com/java/java_modifiers.asp)
- [W3Schools — Java Abstraction](https://www.w3schools.com/java/java_abstract.asp)
- [W3Schools — Java Interface](https://www.w3schools.com/java/java_interface.asp)
- [W3Schools — Java Enums](https://www.w3schools.com/java/java_enums.asp)
- [W3Schools — Java ArrayList](https://www.w3schools.com/java/java_arraylist.asp)
