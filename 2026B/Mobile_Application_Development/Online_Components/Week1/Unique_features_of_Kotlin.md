# Kotlin vs Java — Sự khác nhau

Tài liệu tổng hợp 10 điểm khác biệt chính giữa Kotlin và Java, minh họa bằng code Kotlin thực tế và code Java tương đương để so sánh.

---

## 1. Null Safety

Kotlin phân biệt rõ kiểu **có thể null** (`String?`) và **không thể null** (`String`) ngay ở tầng compiler. Java thì không — mọi object reference đều có thể là `null`, dẫn đến `NullPointerException` (NPE) khi runtime.

**Kotlin:**
```kotlin
var name1: String = "Minh"
var name2: String? = "Minh" // dấu ? cho phép biến này giữ giá trị null

name1 = null // Lỗi compile-time: Not allowed
name2 = null // Hợp lệ
```

**Java (tương đương):**
```java
String name1 = "Minh";
name1 = null; // Biên dịch OK, nhưng có thể gây NPE lúc runtime
```

> Khác biệt cốt lõi: Kotlin bắt lỗi null **ngay lúc compile**, Java chỉ phát hiện **lúc chạy**.

---

## 2. Extension Function

Kotlin cho phép "thêm" hàm mới vào một class có sẵn (kể cả class thư viện chuẩn) mà không cần kế thừa hay sửa source gốc.

**Kotlin:**
```kotlin
fun String.lastChar() = this[this.length - 1]

val lastChar = "Kotlin".lastChar()
println(lastChar)
```

**Java (tương đương — phải viết static method riêng):**
```java
public static char lastChar(String s) {
    return s.charAt(s.length() - 1);
}

char lastChar = lastChar("Kotlin"); // gọi kiểu utility, không gọi trực tiếp trên String
```

> Java không có extension function; phải dùng static utility method hoặc pattern như Helper class.

---

## 3. Data Class

Kotlin sinh tự động `equals()`, `hashCode()`, `toString()`, `copy()` chỉ với từ khóa `data class`.

**Kotlin:**
```kotlin
data class User(val name: String)

println(User("Minh")) // In ra: User(name=Minh)
```

**Java (tương đương — phải viết tay hoặc dùng Lombok):**
```java
public class User {
    private final String name;

    public User(String name) { this.name = name; }
    public String getName() { return name; }

    @Override
    public String toString() { return "User(name=" + name + ")"; }

    @Override
    public boolean equals(Object o) { /* ... phải tự viết ... */ }

    @Override
    public int hashCode() { /* ... phải tự viết ... */ }
}
```

> Java cần ~15-20 dòng boilerplate (hoặc thư viện ngoài như Lombok) để đạt được điều Kotlin làm trong 1 dòng.

---

## 4. Smart Cast

Sau khi Kotlin kiểm tra kiểu bằng `is`, biến tự động được cast trong phạm vi đó — không cần ép kiểu thủ công.

**Kotlin:**
```kotlin
fun printLength(obj: Any) {
    if (obj is String)
        println(obj.length) // obj tự động được coi là String ở đây
}
```

**Java (tương đương — phải cast thủ công):**
```java
public static void printLength(Object obj) {
    if (obj instanceof String) {
        String s = (String) obj; // phải cast tay
        System.out.println(s.length());
    }
}
```
*(Từ Java 16 trở lên có Pattern Matching for `instanceof` giúp rút gọn, nhưng đây là tính năng mới bổ sung sau, không phải mặc định như Kotlin.)*

---

## 5. Coroutines

Kotlin có coroutines tích hợp sẵn để viết code bất đồng bộ (asynchronous) theo phong cách tuần tự (sequential-looking), nhẹ hơn nhiều so với thread thật.

**Kotlin:**
```kotlin
suspend fun doWorld() {
    delay(1000L)
    println("World")
}

fun main() = runBlocking {
    launch {
        doWorld()
    }
    println("Hello")
}
```

**Java (tương đương — dùng Thread hoặc CompletableFuture):**
```java
public static void doWorld() throws InterruptedException {
    Thread.sleep(1000L);
    System.out.println("World");
}

public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
        try { doWorld(); } catch (InterruptedException e) {}
    }).start();
    System.out.println("Hello");
}
```

> Java không có coroutine built-in (trước Project Loom/Virtual Threads ở Java 21); phải dùng Thread nặng hơn hoặc CompletableFuture phức tạp hơn.

---

## 6. Sealed Class

`sealed class` giới hạn tất cả các subclass phải khai báo trong cùng file/module, giúp `when` có thể kiểm tra **đầy đủ (exhaustive)** mà không cần nhánh `else`.

**Kotlin:**
```kotlin
sealed class Result

data class Success(val data: String) : Result()
data class Error(val exception: Exception) : Result()
object Loading : Result()

fun handle(result: Result) = when (result) {
    is Success -> println("Data: ${result.data}")
    is Error -> println("Error: ${result.exception.message}")
    Loading -> println("Loading...")
}
```

**Java (tương đương — dùng interface + switch, hoặc `sealed interface` từ Java 17):**
```java
public sealed interface Result permits Success, Error, Loading {}
public record Success(String data) implements Result {}
public record Error(Exception exception) implements Result {}
public record Loading() implements Result {}

public static void handle(Result result) {
    switch (result) {
        case Success s -> System.out.println("Data: " + s.data());
        case Error e -> System.out.println("Error: " + e.exception().getMessage());
        case Loading l -> System.out.println("Loading...");
    }
}
```

> Java chỉ có `sealed` từ Java 17 trở lên; các phiên bản cũ hơn phải mô phỏng bằng interface thường + `if/else instanceof`, dễ thiếu sót case mà compiler không cảnh báo.

---

## 7. Default & Named Arguments

Kotlin cho phép đặt giá trị mặc định cho tham số và gọi hàm bằng tên tham số, giảm nhu cầu overload method.

**Kotlin:**
```kotlin
fun greet(name: String = "Guest") {
    println("Hello, $name")
}

greet()
greet(name = "Minh")
```

**Java (tương đương — phải overload method):**
```java
public static void greet() {
    greet("Guest");
}

public static void greet(String name) {
    System.out.println("Hello, " + name);
}

greet();
greet("Minh"); // Java không hỗ trợ named argument
```

> Java không có named argument; muốn linh hoạt tham số phải viết nhiều overload hoặc dùng Builder pattern.

---

## 8. Destructuring Declaration

Kotlin cho phép "tách" một object thành nhiều biến cùng lúc (dựa trên `componentN()`, tự sinh với `data class`).

**Kotlin:**
```kotlin
val user = User("Minh")
val (name) = user
println(name) // "Minh"
```

**Java (tương đương — không có destructuring, phải truy cập từng field):**
```java
User user = new User("Minh");
String name = user.getName();
System.out.println(name);
```

> Java (kể cả với `record`) chưa hỗ trợ destructuring declaration ở cấp ngôn ngữ như Kotlin.

---

## 9. Higher-Order Functions & Lambda cuối cùng (Trailing Lambda)

Kotlin cho phép truyền hàm như tham số và viết lambda gọn ngoài dấu ngoặc khi nó là tham số cuối.

**Kotlin:**
```kotlin
fun operate(x: Int, y: Int, op: (Int, Int) -> Int): Int = op(x, y)

val sum = operate(3, 4) { a, b -> a + b }
```

**Java (tương đương — dùng functional interface `BiFunction`):**
```java
import java.util.function.BiFunction;

public static int operate(int x, int y, BiFunction<Integer, Integer, Integer> op) {
    return op.apply(x, y);
}

int sum = operate(3, 4, (a, b) -> a + b); // không có cú pháp trailing lambda gọn như Kotlin
```

> Java hỗ trợ lambda từ Java 8 nhưng cú pháp rườm rà hơn (cần khai báo functional interface, không có trailing lambda ngoài ngoặc).

---

## 10. Type Inference

Cả hai ngôn ngữ đều hỗ trợ suy luận kiểu, nhưng Kotlin áp dụng mặc định cho `var`/`val` từ đầu, còn Java cần từ khóa `var` (Java 10+) và có một số giới hạn hơn.

**Kotlin:**
```kotlin
var integerNumber = 30
var stringValue = "Minh"
```

**Java (tương đương, từ Java 10+):**
```java
var integerNumber = 30;
var stringValue = "Minh";
```

> Ở điểm này hai ngôn ngữ khá tương đồng (từ Java 10 trở đi), khác biệt chủ yếu là Kotlin dùng `val`/`var` cho mọi biến ngay từ đầu (immutable vs mutable rõ ràng), trong khi Java cần `final var` để đạt tương tự `val`.

---

## Bảng tổng hợp nhanh

| # | Tính năng | Kotlin | Java |
|---|---|---|---|
| 1 | Null Safety | `String?` kiểm tra compile-time | Mọi reference đều có thể null, lỗi runtime (NPE) |
| 2 | Extension Function | Có, thêm hàm vào class có sẵn | Không có, phải dùng static utility method |
| 3 | Data Class | `data class` tự sinh equals/hashCode/toString/copy | Phải viết tay hoặc dùng Lombok |
| 4 | Smart Cast | Tự động cast sau `is` | Phải cast thủ công (hoặc pattern matching từ Java 16+) |
| 5 | Coroutines | Có sẵn (`suspend`, `launch`, `delay`) | Không có sẵn, dùng Thread/CompletableFuture (hoặc Virtual Threads từ Java 21) |
| 6 | Sealed Class | Có từ đầu, `when` exhaustive | Chỉ có `sealed` từ Java 17+ |
| 7 | Default/Named Arguments | Có | Không, phải overload method |
| 8 | Destructuring | Có (`val (name) = user`) | Không có |
| 9 | Higher-Order Functions | Cú pháp gọn, trailing lambda | Cần functional interface, cú pháp rườm rà hơn |
| 10 | Type Inference | `var`/`val` mặc định | Cần từ khóa `var` (Java 10+) |
