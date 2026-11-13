// The package must match the folder: src/main/java/com/careerready/
// pom.xml runs "com.careerready.App", so the package name and class name
// both have to match that exactly.
package com.careerready;

// Imports tell Java where to find the JavaFX classes you use.
// You'll need these four (they're all in the javafx-controls dependency):
import javafx.application.Application; // the base class every JavaFX app extends
import javafx.scene.Scene;             // the content that goes inside a window
import javafx.scene.control.Label;     // a simple piece of text on screen
import javafx.stage.Stage;             // the window itself

/*
 * How JavaFX is put together (think of a theater):
 *
 *   Stage  = the window (the theater stage)
 *   Scene  = what's showing in the window right now (the set)
 *   Nodes  = the things in the scene: Labels, Buttons, layouts (the actors)
 *
 *   So: Stage holds a Scene, and the Scene holds a "root" Node.
 *
 * Startup order:
 *   1. main() runs and calls launch(args)
 *   2. JavaFX starts itself up and creates a Stage (a window) for you
 *   3. JavaFX calls start(stage) and passes that window in
 *   4. Your job inside start() is to build a Scene, put it on the Stage, and show it
 */
public class App extends Application {

    // @Override means "I'm replacing a method that Application already has."
    // JavaFX calls start(), so you never call it yourself.
    @Override
    public void start(Stage stage) {

        // TODO 1: Make a Label with some text, e.g. "Hello, CareerReady!"
        //   Hint: new Label("...")
        Label label = new Label("CareerReady!");
        // TODO 2: Make a Scene that holds your label, with a width and height.
        //   Hint: new Scene(rootNode, width, height). Try 400 by 300.
        Scene scene = new Scene(label,400,300);
        // TODO 3: Give the window a title.
        //   Hint: look for a "set..." method on stage.
        stage.setTitle("Flash Cards");

        // TODO 4: Put the scene on the stage.
        stage.setScene(scene);
        // TODO 5: Show the window. Nothing shows up until you do this.
        stage.show();
    }

    // main is the normal Java entry point.
    // launch() is inherited from Application. It starts JavaFX,
    // which then calls start() above.
    public static void main(String[] args) {
        // TODO 6: Start JavaFX here.
        launch(args);
    }
}

/*
 * Running it:
 *   In a terminal at the project root:   mvn javafx:run
 *   (pom.xml already has the javafx-maven-plugin set to com.careerready.App)
 *
 * Things to try after it works:
 *   - Change the window size or the text
 *   - Swap the Label for a Button
 *   - Look up StackPane or VBox, put the Label inside one, and use that as the root
 */
