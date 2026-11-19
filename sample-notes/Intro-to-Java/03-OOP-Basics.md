# OOP Basics

## Big Idea
A class is a blueprint; an object is one thing built from it.

## Key Terms
- **Class**: a blueprint that defines fields and methods
- **Object**: an instance of a class, made with `new`
- **Constructor**: a special method that runs when an object is created
- **Encapsulation**: keeping fields `private` and using getters and setters

## Notes
Fields store an object's data. Methods are what the object can do.
Setters change a field (`setName`), and getters read one (`getName`).

## Common Mistakes
- Forgetting `new`: `Dog d;` only declares a variable, it makes no object
- Giving a constructor a return type: then it's a normal method, not a constructor

## Flashcards
Q: What are the four pillars of OOP?
A: Encapsulation, Inheritance, Polymorphism, Abstraction.

Q: What is a constructor?
A: A special method that runs when an object is created with `new`.
It sets up the object's fields.

B: A {{constructor}} has the same name as its class and no return type.

B: Methods that change a field usually start with {{set}}.

## Practice
### Write a Dog class with a private name, a constructor, and a getter
```java practice
public class Dog {
    private String name;
    public Dog(String name) { this.name = name; }
    public String getName() { return name; }
}
```
