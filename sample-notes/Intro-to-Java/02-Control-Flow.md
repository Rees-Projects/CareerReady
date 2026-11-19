# Control Flow

## Big Idea
Control flow decides which lines run and how many times.

## Key Terms
- **if / else**: runs one path or another based on a condition
- **for loop**: repeats code a set number of times
- **while loop**: repeats code until a condition becomes false

## Notes
`break` leaves a loop early. `continue` skips the rest of the current pass
and moves to the next one.

## Common Mistakes
- Off-by-one: `i <= arr.length` goes one past the end; use `i < arr.length`
- A `while` loop whose condition never changes runs forever

## Flashcards
Q: When should you use a `for` loop instead of a `while` loop?
A: Use `for` when you know how many times to loop. Use `while` when you loop until a condition changes.

Q: What does `continue` do inside a loop?
A: It skips the rest of the current pass and goes to the next one.

B: A {{do-while}} loop always runs at least once.

## Practice
### Print the numbers 1 to 5 using a for loop
```java practice
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```
