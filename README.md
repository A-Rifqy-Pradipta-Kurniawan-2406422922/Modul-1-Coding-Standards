# Reflection

## 1. Clean Code

### Meaningful Naming
The project follows the principle of meaningful names. Classes like `ProductController`, `ProductService`, and `ProductRepository` used to describe their roles. Methods are also phrases to explain their function, such as `createProductPage`, `findById`, and `findAll`, making the code easy to understand by others.

### Functions
The functions in the code follow the core principles of modern software design.

*   **Small & Do One Thing:** Functions remain small and focused on a single responsibility.
    *   `ProductController` methods (`createProductPost`, `editProductPost`) only handle HTTP request/response logic. They receive data, appoint the logic work to the service, and determine which view to show.
    *   `ProductRepository` methods (`create`, `delete`) are responsible only for editing the `productData` list.

*   etc
### Objects and Data Structures
The code shows a clear difference between objects and data structures.
*   The `Product` class acts as a **data structure**. It is a simple Object with primary purpose of holding data and expose it through getters and setters. It contains no other logic.
*   Classes like `ProductService` and `ProductRepository` are true **objects**. They expose behavior through methods (`create()`, `findAll()`, etc).

## 2. Mistakes and Areas for Improvement

### Comments
no comments have applied in this code
### Error Handling: 
Error handling such as don't return null, use exceptions rather than return codes has not applied in this code.
### Lack of Input Validation
The code currently has no input validation. A user can submit a form with an empty product name or a negative quantity.