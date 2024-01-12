package com.abdelhakim.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    private StringBuilder inputBuffer = new StringBuilder();
    private Label display;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Calculator");

        // Create the UI components
        GridPane gridPane = createGridPane();
        display = createDisplay();

        // Add components to the grid
        gridPane.add(display, 0, 0, 4, 1);
        gridPane.add(createButton("7"), 0, 1);
        gridPane.add(createButton("8"), 1, 1);
        gridPane.add(createButton("9"), 2, 1);
        gridPane.add(createButton("/"), 3, 1);
        gridPane.add(createButton("4"), 0, 2);
        gridPane.add(createButton("5"), 1, 2);
        gridPane.add(createButton("6"), 2, 2);
        gridPane.add(createButton("*"), 3, 2);
        gridPane.add(createButton("1"), 0, 3);
        gridPane.add(createButton("2"), 1, 3);
        gridPane.add(createButton("3"), 2, 3);
        gridPane.add(createButton("-"), 3, 3);
        gridPane.add(createButton("0"), 0, 4);
        gridPane.add(createButton("."), 1, 4);
        gridPane.add(createButton("="), 2, 4);
        gridPane.add(createButton("+"), 3, 4);

        // Set up the scene
        Scene scene = new Scene(gridPane, 300, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        return gridPane;
    }

    private Label createDisplay() {
        Label display = new Label("0");
        display.setMinHeight(40);
        display.setStyle("-fx-border-color: #000; -fx-background-color: #fff;");
        return display;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinSize(70, 70);
        button.setOnAction(e -> handleButtonClick(text));
        return button;
    }

    private void handleButtonClick(String value) {
        if (value.matches("[0-9.]")) {
            inputBuffer.append(value);
        } else if (value.matches("[*/+-]")) {
            inputBuffer.append(" ").append(value).append(" ");
        } else if (value.equals("=")) {
            calculateResult();
        }

        display.setText(inputBuffer.toString());
    }

    private void calculateResult() {
        try {
            String expression = inputBuffer.toString();
            String[] parts = expression.split(" ");
            double result = Double.parseDouble(parts[0]);

            for (int i = 1; i < parts.length - 1; i += 2) {
                String operator = parts[i];
                double operand = Double.parseDouble(parts[i + 1]);

                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                }
            }

            inputBuffer.setLength(0);
            inputBuffer.append(result);
        } catch (NumberFormatException | ArithmeticException | ArrayIndexOutOfBoundsException ex) {
            inputBuffer.setLength(0);
            inputBuffer.append("Error");
        }
    }
}
