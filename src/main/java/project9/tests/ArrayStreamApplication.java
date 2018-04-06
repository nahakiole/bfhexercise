package project9.tests;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import project9.classes.ArrayStream;
import project9.interfaces.Stream;

public class ArrayStreamApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        TextField textField = new TextField();

        ArrayStream<Candidate> arrayStream = new ArrayStream<>(
                new Candidate("Bob", "Meier", "Thun", 10),
                new Candidate("Pascal", "Schütz", "Thun", 10),
                new Candidate("Fred", "Muster", "Bern", 20),
                new Candidate("Hans", "Zug", "Bern", 30),
                new Candidate("Eve", "Meier", "Biel", 15)
        );

        root.setTop(textField);

        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(arrayStream.map(Object::toString).toList());
        list.setItems(items);

        root.setCenter(list);

        Label sum = new Label();
        sum.setAlignment(Pos.CENTER);
        sum.setText(String.valueOf(arrayStream.filter(item -> {
            return item.toString().toLowerCase().contains(textField.getText().toLowerCase());
        }).map(Candidate::getVotes).reduce((x, y) -> x + y)));

        root.setBottom(sum);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            Stream<Candidate> candidates = arrayStream.filter(item -> {
                return item.toString().toLowerCase().contains(textField.getText().toLowerCase());
            });

            items.setAll(candidates.map(Object::toString).toList());
            sum.setText(String.valueOf(candidates.map(Candidate::getVotes).reduce((x, y) -> x + y)));
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
