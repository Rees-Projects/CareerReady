# 01 — JavaFX App Basics

**Date:** 2026-11-10 · **Phase:** 0 · **File:** `src/main/java/com/careerready/App.java`

## The Big Idea

A JavaFX app is like a theater:

| JavaFX | Theater | What it is |
|---|---|---|
| `Stage` | the stage | the window |
| `Scene` | the set | the content inside the window |
| Node (`Label`, `Button`, ...) | the actors | the things you see |

**Stage holds a Scene → Scene holds one root Node.**

## How the app starts

1. `main()` runs and calls `launch(args)`
2. JavaFX starts up and creates a `Stage` (window) for you
3. JavaFX calls `start(stage)` and passes that window in
4. Inside `start()`, you build a Scene, put it on the Stage, and show it

`launch()` and `start()` come from `Application`. My class `extends Application`, so it gets them. `@Override` means "I'm replacing a method the parent class already has."

## Concept: keep objects in variables

```java
new Label("Hi");                 // creates a Label, then it's thrown away
Label label = new Label("Hi");   // creates it AND keeps it to use later
```

Pattern: `Type name = new Type(...);`

## Concept: calling methods on objects

```java
objectVariable.methodName(argument);
```

The dot means "ask this object to do something." Classes come with methods that are already written, and you call them.

- **Setters** change a property: `setSomething(value)`
- **Getters** read a property: `getSomething()`

## Mistakes I Made

| Mistake | Why it's wrong | Fix |
|---|---|---|
| `new Label("Hello");` with no variable | The object gets thrown away right away | `Label label = new Label("Hello");` |
| `new Scene(rootNode, width, height);` | Those names were placeholders from a hint, not real variables, so you get "cannot find symbol" | Pass the real label variable and real numbers, and store the Scene in a variable |

## Tips

- **Finding methods:** type `stage.set` in IntelliJ and wait. Autocomplete lists every setter.
- **Run the app:** `mvn javafx:run` from the project root. The main class is set in `pom.xml`.

## Finished Code

Ran with `mvn javafx:run`. It opened a 400×300 window titled "Flash Cards". (2026-11-12)

```java
@Override
public void start(Stage stage) {
    Label label = new Label("CareerReady!");      // make the content
    Scene scene = new Scene(label, 400, 300);     // the Scene holds the label as its root
    stage.setTitle("Flash Cards");                // setter on the window
    stage.setScene(scene);                        // put the Scene on the Stage
    stage.show();                                 // nothing shows until this runs
}

public static void main(String[] args) {
    launch(args);                                 // starts JavaFX, which then calls start()
}
```

**Note:** `launch();` without `args` also works, because `launch` takes a variable number of arguments (varargs). `launch(args)` is the standard way to write it, since it passes along the command-line arguments.

## Quick Check

1. Which one is the window: Stage, Scene, or Node?
2. Who calls `start()`, me or JavaFX?
3. Why does `new Label("Hi");` on its own line do nothing useful?
4. What do setter methods usually start with?

<details><summary>Answers</summary>

1. Stage
2. JavaFX calls it, after `launch()`
3. Nothing stores it in a variable, so you can't use it again
4. `set`

</details>
