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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ArrayStreamApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        TextField textField = new TextField();

        ArrayStream<Person> arrayStream = new ArrayStream<>(
                new Person("Bob", "Meier", "Thun"),
                new Person("Fred", "Muster", "Bern"),
                new Person("Eve", "Meier", "Biel")
        );

        root.setTop(textField);

        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(arrayStream.map(Object::toString).toList());
        list.setItems(items);

        root.setCenter(list);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            items.setAll(arrayStream.filter(item -> {
                return item.toString().contains(textField.getText());
            }).map(Object::toString).toList());
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
