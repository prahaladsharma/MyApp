#📘 What is a data class in Kotlin?
#A data class in Kotlin is a class primarily intended to hold data/state. It automatically provides boilerplate functionalities such as equals(), hashCode(), toString(), copy(), and componentN() functions for free.

    data class User(val name: String, val age: Int)

#🧠 Why do we need data class?
#In traditional Java or verbose Kotlin classes, you often need to manually implement:

equals() — to compare object values
hashCode() — for hashing collections
toString() — for string representation
copy() — for immutability/clone with change
getters / destructuring — to retrieve values

data class eliminates this boilerplate, making code more concise, readable, and maintainable.

✅ Requirements for data class
1.Must have at least one primary constructor parameter.
2.All constructor parameters should be val or var.
3. Cannot be abstract, open, sealed, or inner.
Example (Valid):
    data class Product(val id: Int, val name: String)

Example(Invalid):
    data class InvalidClass() // ❌ No properties


🔧 Compiler-generated Functions
For Below Example:
    data class Book(val title: String, val author: String)
The Kotlin compiler generates:
* equals()
* hashCode()
* toString() → Book(title=..., author=...)
* copy() → book.copy(title = "New Title")
* component1(), component2() → for destructuring    

🌍 Real-World Use Cases
Case-1: API Response Models:
    data class ApiResponse(val status: String, val data: List<User>)

Case:-2  State Management in MVI:
    data class LoginViewState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val isLoggedIn: Boolean = false
    )

Case:-3  Room Entity (with annotations):
    @Entity
    data class UserEntity(
        @PrimaryKey val id: Int,
        val name: String
    )

Case:4  Passing between screens:
    val user = User("Prahalad", 28)
    navController.navigate("details/${user.name}")

🔄 Copy Function Example
    val original = User("John", 30)
    val updated = original.copy(age = 31)
Useful for immutability and functional programming.


✅ Pros
* 📉 Less boilerplate
* 🧪 Great for testing (easy comparisons)
* ✍️ Readable string representation
* 🚀 Functional style compatibility
* 💡 Destructuring support
* ♻️ copy() helps manage immutability


⚠️ Cons / Considerations
| Concern                | Details                                                              |
| ---------------------- | -------------------------------------------------------------------- |
| Not suitable for logic | Should not contain business logic; for **state only**                |
| Overhead               | Automatic functions can cause minor performance costs in tight loops |
| Equals/hashCode traps  | Comparison is based on **all primary constructor properties only**   |
| Cannot inherit         | You can't extend a `data class` (not open)                           |



🧩 Destructuring Declarations
    val (name, age) = User("Prahalad", 28)
    println(name) // Prahalad
The compiler maps constructor parameters to componentN() functions for this purpose.


🔥 Pro Tip
To prevent data class misuse for business logic, separate state and behavior:
    // Good
    data class CartState(val items: List<Item>, val total: Double)

    // Bad
    data class Cart(val items: List<Item>) {
        fun calculateDiscount() { /* logic */ } // ❌ Don't add logic
    }



🧪 Equals vs Referential Equality
    val u1 = User("A", 25)
    val u2 = User("A", 25)

    println(u1 == u2) // true (structural)
    println(u1 === u2) // false (referential)



📌 Summary Table
| Feature             | Data Class       |
| ------------------- | ---------------- |
| Boilerplate Removed | ✅                |
| Equals/HashCode     | ✅ Auto-generated |
| Use in MVI          | ✅ Ideal          |
| Extendable          | ❌                |
| Default Constructor | ✅                |
| Destructuring       | ✅ `componentN()` |


Notes:------------
* Data classes in Kotlin are primarily used to hold data.

For each data class, the compiler automatically generates additional member functions that allow you to print an instance to readable output,
compare instances, copy instances, and more.

Data classes are marked with data:
    data class User(val name: String, val age: Int)

To ensure consistency and meaningful behavior of the generated code, data classes have to fulfill the following requirements:
1. The primary constructor must have at least one parameter.
2. All primary constructor parameters must be marked as val or var.
3. Data classes can’t be abstract, open, sealed, or inner.

Additionally, the generation of data class members follows these rules with regard to the members’ inheritance:

If there are explicit implementations of .equals(), .hashCode(), or .toString() in the data class body or final implementations 
in a superclass, then these functions are not generated, and the existing implementations are used.

If a supertype has .componentN() functions that are open and return compatible types, 
the corresponding functions are generated for the data class and override those of the supertype. 
If the functions of the supertype cannot be overridden due to incompatible signatures or due to their being final, an error is reported.
Providing explicit implementations for the .componentN() and .copy() functions is not allowed.

Properties declared in the class body
The compiler only uses the properties defined inside the primary constructor for the automatically generated functions. 
To exclude a property from the generated implementations, declare it inside the class body:

    data class Person(val name: String) {
    var age: Int = 0
    }


In the example below, only the name property is used by default inside the .toString(), .equals(), .hashCode(), and .copy() implementations, 
and there is only one component function, .component1().

The age property is declared inside the class body and is excluded. 
Therefore, two Person objects with the same name but different age values are considered equal 
since .equals() only evaluates properties from the primary constructor:
    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20

println("person1 == person2: ${person1 == person2}")
// person1 == person2: true

println("person1 with age ${person1.age}: ${person1}")
// person1 with age 10: Person(name=John)

println("person2 with age ${person2.age}: ${person2}")
// person2 with age 20: Person(name=John)


Copying
Use the .copy() function to copy an object, allowing you to alter some of its properties while keeping the rest unchanged. 
The implementation of this function for the User class above would be as follows:
    fun copy(name: String = this.name, age: Int = this.age) = User(name, age)
    You can then write the following:

    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)


Access Modifiers for Data Classes
1. public (default): The data class is accessible from anywhere.
2. private: The data class is accessible only within the file it is defined.
3. protected: This is not applicable to top-level classes and is used within class hierarchies.
4. internal: The data class is accessible only within the same module.


Abstraction:
Data classes offer a limited form of abstraction, focusing on data representation rather than complex behavior. 
For richer abstractions, you should use regular classes or interfaces alongside data classes.

Polymorphism:
Data classes themselves cannot be extended, but they can implement interfaces, 
which allows them to participate in polymorphic behavior through the interfaces. 
They provide a way to interact with different types of objects in a uniform manner if those objects implement the same interface.
// Interface with polymorphic behavior
            interface Vehicle {
                fun start(): String
            }

            // Data class implementing the interface
            data class Car(val model: String) : Vehicle {
                override fun start(): String {
                    return "Car $model is starting."
                }
            }

            // Another data class implementing the interface
            data class Bike(val model: String) : Vehicle {
                override fun start(): String {
                    return "Bike $model is starting."
                }
            }

            // Function using polymorphism
            fun startVehicle(vehicle: Vehicle) {
                println(vehicle.start())
            }

            fun main() {
                val car = Car("Toyota")
                val bike = Bike("Yamaha")

                startVehicle(car)  // Output: Car Toyota is starting.
                startVehicle(bike) // Output: Bike Yamaha is starting.
            }


4. Inheritance Restrictions for Data Classes:-
Can two data class extends to each other In Kotlin?, data classes cannot extend each other or inherit from one another. 
This is a fundamental restriction imposed by the design of data classes.

A. Data Classes in Kotlin Final by Default:
Data classes in Kotlin are implicitly final, meaning they cannot be subclassed.
This design choice ensures that the data class maintains a fixed structure and behavior, which is crucial for maintaining data integrity and predictability.

Purpose:
Data classes are intended to serve as simple containers for data with automatic implementations of methods such as equals(), hashCode(), and toString().
They are not meant to be part of an inheritance hierarchy.

B. Why No Inheritance:
Immutability:
One of the core features of data classes is their emphasis on immutability.
Allowing inheritance could complicate the immutable nature of data classes and introduce inconsistencies.
Simple Data Handling:
Data classes are designed to be straightforward and efficient for representing data without complex behaviors or hierarchies.

Why data class Automatically Generated Methods in kotlin android:-
1. equals() Method:
Purpose:
The equals() method is used to compare two instances of a data class for equality based on their property values.

Why Automatically Generated:
By default, the equals() method is generated to compare instances based on all properties in the primary constructor.
This ensures that two data objects with the same property values are considered equal,
which is useful for comparisons and operations that depend on object equality.

data class Person(val name: String, val age: Int)
fun main() {
  val person1 = Person("Alice", 30)
  val person2 = Person("Alice", 30)
  println(person1 == person2)  // Output: true (since both have the same property values)
}

2. hashCode() Method:
Purpose:
The hashCode() method returns an integer hash code value for the object. It is used in hash-based collections like HashMap and HashSet.

Why Automatically Generated:
The hashCode() method is generated based on the same properties used in equals().
This ensures that equal objects (as defined by equals()) have the same hash code, 
which is essential for the correct behavior of hash-based collections.

val person = Person("Alice", 30)
val map = mutableMapOf(person to "Data")
println(map[Person("Alice", 30)]) // Output: Data
3. toString() Method:
Purpose:
The toString() method returns a string representation of the object, which includes the class name and its property values.

Why Automatically Generated:
The default implementation of toString() provides a readable representation of the object, which is useful for debugging and logging.

This method helps developers quickly understand the state of an object without manually defining a string format.

data class Person(val name: String, val age: Int)
    fun main() {
    val person = Person("Alice", 30)
    println(person) // Output: Person(name=Alice, age=30)
    }

4. copy() Method:
Purpose:
The copy() method creates a new instance of the data class with some properties modified.

This is useful for immutability, where you want to create a new object with some updated properties while 
keeping the original object unchanged.

Why Automatically Generated:
The copy() method supports immutability by allowing modifications to an object’s properties in a safe and concise manner.

It provides an easy way to create a modified copy of an object without manually constructing a new instance.

5. Component Functions:- Component Functions (componentN()):
Purpose:
These functions are generated for each property in the primary constructor and are used for destructuring declarations.

Why Automatically Generated:
The componentN() functions facilitate destructuring declarations,
which allow you to unpack data class instances into individual variables easily.

This enhances readability and usability when working with data classes.

data class Person(val name: String, val age: Int)
    fun main() {
        val (name, age) = Person("Alice", 30)
        println("Name: $name, Age: $age") // Output: Name: Alice, Age: 30
    }

Benefits of Automatically Generated Methods
1. Reduces Boilerplate Code:
   Automatically generating these methods eliminates the need for boilerplate code, 
   allowing developers to focus on the business logic rather than repetitive method implementations.

2. Ensures Consistency:
   The generated methods follow Kotlin’s conventions for data classes, ensuring consistent behavior for equality checks, 
   hashing, and object representation.

3. Supports Immutability:
   Methods like copy() support immutable data patterns by allowing safe modifications to data objects without altering the original instance.

4. Simplifies Data Handling:
   Methods like toString() and component functions simplify debugging, logging, and data manipulation tasks, 
   making it easier to work with data objects.


Conclusion:
* Kotlin’s data classes are designed to streamline the creation and management of data-centric objects
* The automatic generation of methods such as equals(), hashCode(), toString(), copy(), 
  and component functions significantly reduces boilerplate code and enhances the functionality and usability of data classes.
* These features help developers efficiently manage immutable data objects and perform common operations with minimal effort.

