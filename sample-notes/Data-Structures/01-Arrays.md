# Arrays

## Big Idea
An array holds a fixed number of values of the same type, found by index.

## Key Terms
- **Array**: a fixed-size list of values of one type
- **Index**: a value's position in the array, starting at 0
- **length**: the number of slots in the array

## Notes
Reading by index is fast, but the size can't change after the array is
created. The last index is always `length - 1`.

## Common Mistakes
- Using `nums.length()`: arrays use the field `length`, no parentheses
- Going past the end: index `length` doesn't exist and throws an exception

## Flashcards
Q: What happens if you access an index outside the array?
A: Java throws an `ArrayIndexOutOfBoundsException`.

Q: How do you get the size of an array named `nums`?
A: `nums.length` (a field, not a method, so no parentheses).

B: Array indexes start at {{0}}.

## Practice
### Create an int array of 1, 2, 3 and add up its values with a for-each loop
```java practice
int[] nums = {1, 2, 3};
int sum = 0;
for (int n : nums) {
    sum += n;
}
```
