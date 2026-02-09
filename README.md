# Reflection 1

## 1. Clean Code

### Meaningful Naming
The project follows the principle of meaningful names. Classes like ProductController, ProductService, and ProductRepository used to describe their roles. Methods are also phrases to explain their function, such as createProductPage, findById, and findAll, making the code easy to understand by others.

### Functions
The functions in the code follow the core principles of modern software design.

*   Small & Do One Thing: Functions remain small and focused on a single responsibility.
    *   ProductController methods (createProductPost, editProductPost) only handle HTTP request/response logic. They receive data, appoint the logic work to the service, and determine which view to show.
    *   ProductRepository methods (create, delete) are responsible only for editing the productData list.

*   etc
### Objects and Data Structures
The code shows a clear difference between objects and data structures.
*   The Product class acts as a data structure. It is a simple Object with primary purpose of holding data and expose it through getters and setters. It contains no other logic.
*   Class like ProductService and ProductRepository are the real objects. They provide behavior through methods (create(), findAll(), etc).

## 2. Mistakes and Areas for Improvement

### Comments
no comments have applied in this code
### Error Handling: 
Error handling such as don't return null, use exceptions rather than return codes has not applied in this code.
### Lack of Input Validation
The code currently has no input validation. A user can submit a form with an empty product name or a negative quantity.

# Reflection 2

### Unit Testing

At first, writing tests feels like burden, but the value it provides is invaluable. It gave me a deeper understanding of what each class is supposed to do.

There is no specific number of unit tests for a class. The goal itself is to test a class thoroughly. A good metric maybe is to have at least one test for every method in the class. Furthermore, any conditional logic (like if/else) within a method provide multiple responses, and each path should be tested.

This is where coverage becomes used. Code coverage measure how much lines of your source code were executed by tests. If the coverage is low, it explains that most of the code are untested.

However, 100% code coverage does not mean the code don't have any bugs. Coverage only confirms that a line of code was executed, not that it is correct. 

### Functional Tests

In my opinion, creating a new functional test class with the same setup and variables would be a direct violation of the DRY (Don't Repeat Yourself) principle. It results in code that's not clean and decrease the code quality because it is repetitive.

However, this can be done better by creating a base test class to do the same thing. The same functional tests can use again by extending this base class. This way, its become much easier to manage and update the test code because one file change only needed.