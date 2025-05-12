🔷 What is enum in Kotlin?
* In Kotlin, an enum (short for enumeration) is a special class used to represent a fixed set of constant values. 
* Each value in an enum is an object (singleton). It's commonly used when you need to represent a predefined list of options.


✅ Example:--
    enum class Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    val today = Day.MONDAY

🔧 Enums can:-
* Have constructors
* Hold properties
* Implement interfaces
* Define methods
    enum class Status(val code: Int) {
        SUCCESS(200),
        ERROR(500),
        LOADING(102);

        fun isError() = this == ERROR
    }


🧩 1. Need for enum in Kotlin
* Enums (short for enumerations) represent a fixed set of constants. 
They are useful when a variable can only take one out of a small set of possible values.

✅ Use cases:
* Representing states (e.g., LOADING, SUCCESS, ERROR)
* User roles (e.g., ADMIN, USER, GUEST)
* HTTP Methods (e.g., GET, POST)
* Navigation routes in Jetpack Compose
* Avoiding magic strings/integers in business logic

✅ 2. Pros and Cons
✅ Pros:
* Type-safe constants (no accidental typos)
* Readable and maintainable
* Can have properties & functions
* Used in when expressions elegantly
* Enums are singletons by design for each constant
* Great for sealed modeling in a limited domain

❌ Cons:
* Heavy compared to sealed class (due to extra overhead like values array)
* Not extendable (you can’t inherit from an enum)
* Lacks flexibility vs sealed classes for polymorphism
* Serialization with libraries like Moshi or Gson can need adapters

💡 3. OOP Perspective
* Enums are full-blown classes in Kotlin:
* Can have constructors, properties, and methods
* Can implement interfaces
* Cannot be subclassed (inheritance is restricted)
    Example:
        enum class PaymentType(val feePercent: Int) {
            CREDIT_CARD(2),
            DEBIT_CARD(1),
            UPI(0);

            fun calculateFee(amount: Double): Double = (amount * feePercent) / 100
        }

🛠 4. SOLID Principles & Enums
* S - Single Responsibility: ✅ Enum handles a single responsibility — modeling a group of related constants.
* O - Open/Closed: ✅ Open for use (with methods, interfaces), closed for modification (cannot add enums at runtime).
* L - Liskov Substitution: ✅ An enum can substitute an interface it's implementing.
* I - Interface Segregation: ✅ Enums can implement specific interfaces.
* D - Dependency Inversion: ✅ Enums can be injected as dependencies via interfaces, helping inversion.

🔂 5. Enum vs Singleton
* Each enum constant is a Singleton.
* Enum is a thread-safe, lazy-loaded singleton by JVM.

    enum class Logger {
        INSTANCE;

        fun log(message: String) = println("Log: $message")
    }
Usage:
Logger.INSTANCE.log("App started")
➡️ This mimics the traditional Singleton pattern.


🔖 Summary Table
| Feature               | Enum                             |
| --------------------- | -------------------------------- |
| Type-safe             | ✅ Yes                            |
| Serializable          | ✅ (Needs adapter sometimes)      |
| Inheritance           | ❌ Not allowed                    |
| Implements Interfaces | ✅ Yes                            |
| Singleton per value   | ✅ Yes                            |
| Lightweight           | ❌ More heavy than `sealed class` |


🔍 Memory Usage: enum vs sealed class in Kotlin
| Feature               | `enum class`                                                                      | `sealed class`                                                                       |
| --------------------- | --------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| **Memory Usage**      | ✔️ **Higher** – All enum instances are created **eagerly** at class loading time. | ✅ **Lower** – Instances are created **lazily** when used.                            |
| **Instance Handling** | Singleton per value (all kept in memory).                                         | You control when and how many instances exist.                                       |
| **Flexibility**       | Fixed set of values. Can't carry complex state easily.                            | Highly flexible – can have multiple subclasses and rich hierarchies.                 |
| **Performance**       | Fast access but memory-heavy if unused.                                           | Slightly slower if instantiated dynamically, but better for large, sparse use cases. |


🧠 In Summary:
* Use **enum** when:
  You have a small fixed set of constants known at compile time, and memory overhead is acceptable.

* Use **sealed class** when:
  You want better memory control, richer hierarchies, or pass data with each type.


Key Characteristics of Enum Class:--
1. Fixed Set of Constants
    Enum classes are designed to represent a fixed set of predefined constants. 
    For instance, days of the week, colors, or state of a process. 
    Once an enum class is defined, the set of enum constant is fixed and can’t be modified.

2. Singleton Instances
    * Each enum constant is a singleton instance. 
    * This means that each constant represents a unique instance of the enum class, 
      and these instances are created automatically when the class is loaded.

3. No Instantiation
    * You can’t use the new keyword or any other mechanism to instantiate an enum class. 
    * The instances are created by the JVM when the enum class is first referenced.
    Example:-
        enum class Direction{ NORTH, SOUTH, EAST, WEST}
    fun main(){
        val direction: Direction = Direction.NORTH
        println(“Selected direction: $direction”)
    }

4, Abstract Methods
    * Enum in kotlin can have abstract methods that must be implemented by each ebum constant. 
    * This allow you to encapsulate behaviour within the enum constants, demonstrating abstraction.

5. Interface
    * Enums can implement interfaces, providing specific implementations for methods defined in the interface. 
    * This allows enums to abhere to a contract while defining unique behaviour for each constant.

6. Polymorphism
    * By using abstract methods or interfaces, enum can exhibit polymorphic behaviour, 
      where the behaviour is based on the specific enum constant in use.

7. Inheritance
    * Enum class can’t extend another class, because enum implicitly extend java.lang.Enum class and Java does’t support multiple inheritance. 
    * Enum can implement interfaces.


Feature of enum class:-
1. Enum in Constant: Each entry in enum class is a constant.
        enum class Color {
            RED,
            GREEN,
            BLUE
        }

2. Custom Properties and Methods: 
    * You can define properties and methods within an enum class, and each constant can override these properties or method.

        enum class DayOfWeek(val dayNumber: Int){
            MONDAY(1),
            TUESDAY(2),
            WEDNESDAY(3),
            THURSDAY(4),
            FRIDAY(5),
            SATURDAY(6),
            SUNDAY(7);

            fun isWeekend(): Boolean{
                return this == SATURDAY || this == SUNDAY
            }
        }

3. Custom Constructor: Enum can have custom constructors. These constructors are private and can be used to initialize properties.

        enum class Planet(val radius: Double){
            MERCURY(2439.7),
            VENUS(6051.8),
            EARTH(6371.0),
            MARS(3389.5),
            JUPITER(69911.0),
        }

4. Methods and Properties: 
* You can define methods and properties in an enum class. Methods can be used to perform operation or logic related to the constant.

        enum class Operation{
            ADD{
                override fun apply(x:Int, y:Int) = x + y
            },
            SUBTRACT{
                override fun apply(x:Int, y:Int) = x y
            },
            MULTIPLY{
                override fun apply(x:Int, y:Int) = x * y
            },
            DIVIDE{
                override fun apply(x:Int, y:Int) = x / y
            };

            abstract fun apply(x: Int, y: Int)
        }

5. values() and valueOf() Methods: Every enum class in Kotlin automatically gets two methods.
    values(): Returns an array of all enum constants.
        val allDays = DayOfWeek.values()
    valueOf(name: String): 
    * Returns the enum constant with the specified name. Throws an IllegalArgumentException if the name does not match any constant.
        val monday = DayOfWeek.valueOf("Monday")


Enum Classes in Practice:---------------
1. Switch Statement: In Kotlin, you can use when statement to handle different enum values.

fun describeDay(day: DayOfWeek){
  when(day){
    DayOfWeek.Monday -> println("Start tof the work week.")
    DayOfWeek.SAT, DayOfWeek.SAT -> prinltln("Weekend!")
    else -> println("Midweek")
  }
}

2. Serialization: Enum can be used with serialization libraries (e.g., Gson, Moshi) to serialize and deserialize enum values.

data class User(val name: String, val role: Role)

enum class Role{
  ADMIN, USER, GUEST
}
Example of enum :-
enum class Operation{
  ADD{
    override fun apply(x:Int, y:Int) = x + y
  },
  SUBTRACT{
    override fun apply(x:Int, y:Int) = x y
  };

  abstract fun apply(x: Int, y: Int)
}

fun main(){
  val x: Int = 5
  val y: Int = 5
  val result = Operation.ADD.apply(x, y)
  println(result)
}
interface Descriable{
  fun desc(): String
}

enum class TrafficLight: Descriable{
  RED{
    override fun desc() = "Stop"
  },
  GREEN{
    override fun desc() = "Go"
  },
  YELLOW{
    override fun desc() = "Caution"
  };
}

fun main(){
  TrafficLight.values().forEach{
    println("${it.name}: ${it.desc()}")
  }
}        
