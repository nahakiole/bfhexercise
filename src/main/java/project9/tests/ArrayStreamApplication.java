package project9.tests;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import project9.classes.ArrayStream;

public class ArrayStreamApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        TextField textField = new TextField();

        ArrayStream<String> arrayStream = new ArrayStream<>("Fred", "Eve", "Alice", "Bob", "Berta", "Bobin", "Hans", "Bob");

        root.setTop(textField);

        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(arrayStream.toList());
        list.setItems(items);

        root.setCenter(list);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            items.setAll(arrayStream.filter(item -> {
                return item.contains(textField.getText());
            }).toList());
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ArrayStreamApplication");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
