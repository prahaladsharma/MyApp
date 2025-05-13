
Sealed Class:--
* In Kotlin, a sealed class is a special type of class that retricts class hierarchies to a limited set of types. 
* This feature particularly useful for representing restricted class hierarchies and ensures that all subclass are known 
  and can be checked at compile time.

Key Characteristics of Sealed class:---
1. Restricting Hierarchies:- 
   * A sealed class can only be subclassed within the same file where it is declared. 
   * This restriction means that you cam define a closed set of subclasses and ensure that no other classes can extend it outside this file.

2. Compile-Time Checking:- 
   * All subclasses of a sealed class are known at compile time, when you use ‘when’ expression or statements to handle different cases, 
     the compiler can check that all possible cases are covered. 
   * This helps avoid runtime errors by ensuring exhaustive checks.

3. Inheritance:- 
   * Sealed class can be abstract or have concrete implementation. 
   * You can define methods and properties within a sealed class, and subclasses can override or implement these. 
   * A sealed class can inherit from another sealed class.

4. Subclasses:- 
   * The subclasses of a sealed class can be either classes or data classes. 
   * They can also have their own properties and methods.

5. No External Subclassing:- 
   * Sealed classes cannot be subclasses outside theire own file. 
   * This is a deliberate design choice to keep the hierarchy well-defined and contained.

        sealed class Result{
            data class Success(val data:String): Result()
            data class Error(val message:String): Result()
            object Loading: Result()
        }

        // function using polymorphism
            fun handlingResult(result: Result){
            when(result){
                is Result.Success -> println("Success with data: ${result.data}")
                is Result.Error -> println("Error occurred: ${result.message}")
                Result.Loading -> println("Loading....")
            }
        }

        fun main(){
            val successResult = Result.Success("Data Loaded Successfully")
            val errorResult = Result.Error("Failed to loas data")
            val loadingResult = Result.Loading 

            // Handle results polymorphism
            handlingResult(loadingResult)
            handlingResult(successResult)
            handlingResult(errorResult)    
        }


Sealed class with abstract and polymorphic behaviour:-
        sealed class Operation{
            abstract fun execute(): String

            data class Addition(val a:Int, val b:Int): Operation(){
                override fun execute(): String = "Addition result: ${a + b}"
            }

            data class Subtraction(val a:Int, val b:Int): Operation(){
                override fun execute(): String = "Subtraction result: ${a - b}"
            }

            data class NoOperation(): Operation(){
                override fun execute(): String = "No operation performed"
            }  
        }

        //Function that works with Operation polymorphically
        fun performOperation(operation: Operation):String{
            return operation.execute()
        }

        fun main(){
            val addition = Operation.Addition(5,3)
            val subtraction = Operation.Subtraction(10,3)
            val noOp = Operation.NoOperation()

            //Perform operation polymorphically
            performOperation(addition) // Output: Addition result: 8
            performOperation(subtraction) // Output: Subtraction result: 7
            performOperation(noOp) // Output: No operation performed
        }


Sealed class with Interface:---
        interface Drawable{
            fun draw(): String
        }

        sealed class Shape: Drawable{
            data class Circle(val radius: Double): Shape(){
                override fun draw(): String = "Drawing a Circle with radius $radius"
            }

            data class Rectangle(val radius: Double): Shape(){
                override fun draw(): String = "Drawing a Rectangle with width $width and height $height"
            }
        }

        // Function to demonstrate polymorphism with sealed class
        fun renderShape(shape: Shape)String{
            return shape.draw()
        }

        fun main(){
            val circle = Shape.Circle(radius = 5.0)
            val rectangle = Shape.Rectangle(width = 4.0, height = 6.0)

            println(renderShape(circle))
            println(renderShape(rectangle)) 
        }


Sealed Class Advantages:---
1. Exhaustive when Expressions: 
    * The compiler checks that all subclasses are handled, reducing the risk of runtime errors.

2. Clear and Concise Code: 
    * Sealed classes make your code more readable and maintainable by grouping related types and ensuring that they are used correctly.

3. Encapsulation: 
    * Encapsulations the details of the subclass hierarchy, making it eaiser to manage and evolve.

Sealed Class DisAdvantages:
1. Limited Subclassing:- 
    * The requirement that subclasses must be defined in the same file can be restrictive, 
      especially in larger projects with complex hierarchies.

2. No External Extensibility:- 
    * If you need to extend the hierarchy in different modules or files, sealed classes might not be suitable. 
    * For such cases, open classes or interfaces might be more appropriate.

Enum vs Sealed:-
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|   Terms                                       ENUM                                                                    SEALED
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Definition     |      An enum a collection of constants.                                             |  Sealed have restricted set of subclasses
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Instantiation  |      You can instantiate by accessing their constants directly                      |  You cannot instantiate a sealed class directly
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Use Cases      |      fixed set of named constants that are known at compile time                    |  Used for set of types that can have different structures or behaviors
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Extensibility  |      It can't be extended,can't add new values to an enum type once it is defined   |  It can't be extended but can add new subclasses to a sealed class
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Exhaustiveness |      you do not get exhaustiveness checking if the when is not exhaustive           |  when expressions are exhaustive when working with sealed classes
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Memory         |      Due to singleton consume less memory but work on fixed set of simple values    |  Consume more memory but provide necessary flexibility
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Flexibility    |      No flexible due to fixed set of constant                                       |  Flexibility due to in terms of data representation and behavior for each subclass
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Inheritance    |      Two enum can't inherit each other                                              |  Two Sealed class can inherit each other
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Access Modifier|     enum can be public, private and internal(protected depends on use cases)        |  For sealed, access modifier depends on the use cases, directly can't say any modifier
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Constructor    |   enum Constructor are default private, so they can't instantiate.                  |  constructor are private & protected, make enable flexible initialization through subclasses.
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



Sealed Class vs Sealed Interface:-
1. Inheritance vs Implementation:-
    Sealed classes:- 
        * Can be subclasses to create concrete implementations. Subclasses are defined as sata class, or object.
    Sealed Interface:- 
        * Can be implemented by classws and other interfaces. They allow for more flexibility in terms of how implementations are oraganized.

2. Flexibility:-
    Sealed Class:- 
        * Provide a closed set of subclasses, which can include various types of concrete classes and objects.
    Sealed Interface: 
        * Allow for a more flexible type hierarchy where you can have both concrete classes and other interfaces implementing the sealed interface.

3. Instantiation:-
    Sealed Classes:- 
        * Instance of sealed classes can be created directly from their concrete subclasses.
    Sealed Interface:- Instances must be created from implementing classes or objects.

Use Cases:-
    Sealed Classes:- 
        * Best used when you need to model a limited set of states or types with a concrete implementation.

    Sealed Interface:- 
        * Best used when you want to define a restricted set of implementations or behaviours that adhere to a common interface.

    Sealed Classes:- 
        * Ideal for representing stateful response like success, error, or loading states, where each state can hold data.
    Sealed Interface:- 
        * Suitable for defining a contract for multiple types (e.g., different shapes) without enforcing a specific class structure, 
          allowing for more flexibility in implementation.



|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
       | sealed class NetworkResponse {                                                             |    sealed interface Shape {
       |     data class Success(val data: String) : NetworkResponse()                               |        fun area(): Double
       |     data class Error(val message: String) : NetworkResponse()                              |    }
       |     object Loading : NetworkResponse()                                                     |
       | }                                                                                          |    data class Circle(val radius: Double) : Shape {
       |                                                                                            |        override fun area(): Double {
       | // Usage                                                                                   |            return Math.PI * radius * radius
       | fun handleResponse(response: NetworkResponse) {                                            |        }
       |     when (response) {                                                                      |    }
       |         is NetworkResponse.Success -> {                                                    |
       |             println("Data received: ${response.data}")                                     |    data class Rectangle(val width: Double, val height: Double) : Shape {
       |         }                                                                                  |        override fun area(): Double {
       |         is NetworkResponse.Error -> {                                                      |            return width * height
       |             println("Error: ${response.message}")                                          |        }
       |         }                                                                                  |    }
       |         NetworkResponse.Loading -> {                                                       |
       |             println("Loading...")                                                          |    // Usage
       |         }                                                                                  |    fun printArea(shape: Shape) {
       |     }                                                                                      |        println("Area: ${shape.area()}")
       | }                                                                                          |    }
                                                                                                    |
                                                                                                    |    fun main() {
                                                                                                    |        val circle = Circle(5.0)
                                                                                                    |        val rectangle = Rectangle(4.0, 6.0)
                                                                                                    |
                                                                                                    |        printArea(circle)      // Output: Area: 78.53981633974483
                                                                                                    |        printArea(rectangle)   // Output: Area: 24.0
                                                                                                    |    }
      ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
      
