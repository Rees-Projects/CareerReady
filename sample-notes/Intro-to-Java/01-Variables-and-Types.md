# Variables and Types

## Big Idea
A variable is a named box that holds a value, and its type decides what it can hold.

## Key Terms
- **Variable**: a named place in memory that stores a value
- **Primitive type**: a basic type that holds the value directly (`int`, `double`, `boolean`, `char`)
- **Reference type**: a type that holds a reference to an object (`String`, arrays, classes)

## Notes
Declare a variable with its type, then its name: `int age = 20;`.

Primitives store the actual value. Reference types store where the object
lives, which is why two variables can point at the same object.

## Common Mistakes
- Using `=` to compare: `=` assigns, `==` compares
- Comparing Strings with `==`: it checks if they're the same object; use `.equals()`

## Flashcards
Q: What is the difference between a primitive type and a reference type?
A: A primitive holds the value itself. A reference type holds a reference (address) to an object.

Q: What is the default value of an `int` field?
A: 0

B: The {{final}} keyword makes a variable unable to be reassigned.

B: A `double` stores {{decimal}} numbers.

## Practice
### Declare an int named age set to 20, and a String named name set to "Sam"
```java practice
int age = 20;
String name = "Sam";
```
