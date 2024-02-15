/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package io.github.yokigroup.view;


import javafx.application.Application;

/** Main application entry-point's class. */

public final class App {
    private App() { }

    /**
     * Main application entry-point.
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(GameFX.class, args);
        // The following line raises: Error: class io.github.yokigroup.view.App
        // is not a subclass of javafx.application.Application
        // JavaFXApp.launch(args);
        // While the following would do just fine:
        GameFX.run(args);
    }
}
