package project9.tests;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import project9.classes.ArrayStream;
import project9.classes.SeededStream;
import project9.interfaces.Stream;

import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class StreamApplication extends Application {
    private ObservableList<String> items;
    private Label sum;

    private ArrayStream<Candidate> arrayStream;
    private TextField textField;

    private String[] firstNames = {
            "Bob",
            "Pascal",
            "Carole",
            "Cynthia",
            "Nora",
            "Fred",
            "Geraldine",
            "Eve",
            "Petra",
            "Hans",
    };

    private String[] lastNames = {
            "Meier",
            "Kunz",
            "Muster",
            "Müller",
            "Kurz",
            "Lang",
            "Ferdinand",
    };

    private String[] cities = {
            "Bern",
            "Biel",
            "Interlaken",
            "Thun"
    };

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        textField = new TextField();

        SeededStream<Candidate> candidates = new SeededStream<>(generateCandidate(), x -> generateCandidate());

        List<Candidate> candidateArrayList = candidates.filter(x -> x.getVotes() > 10).limit(10).toList();

        arrayStream = new ArrayStream<>(
                (Candidate[]) candidateArrayList.toArray(new Candidate[candidateArrayList.size()])
        );

        root.setTop(textField);

        ListView<String> list = new ListView<>();

        items = FXCollections.observableArrayList(arrayStream.map(Object::toString).toList());
        list.setItems(items);

        root.setCenter(list);

        sum = new Label();
        sum.setAlignment(Pos.BOTTOM_CENTER);

        HBox controls = new HBox(20);

        Button button = new Button("Neuer Kandidat");

        controls.getChildren().addAll(sum, button);
        button.setOnAction(event -> {
            arrayStream.getElements().add(generateCandidate());
            updateList();
        });

        updateList();

        root.setBottom(controls);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateList();
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("StreamApplication");
        primaryStage.show();
    }

    private Candidate generateCandidate() {
        String firstName = firstNames[(new Random()).nextInt(firstNames.length)];
        String lastName = lastNames[(new Random()).nextInt(lastNames.length)];
        String city = cities[(new Random()).nextInt(cities.length)];

        return new Candidate(firstName, lastName, city, new Random().nextInt(200), new GregorianCalendar(new Random().nextInt(50) + 1950, new Random().nextInt(11), new Random().nextInt(30)));
    }

    private void updateList() {
        Stream<Candidate> candidates = arrayStream.filter(item -> {
            return item.toString().toLowerCase().contains(textField.getText().toLowerCase());
        });

        items.setAll(candidates.map(Object::toString).toList());
        sum.setText(String.valueOf(candidates.map(Candidate::getVotes).reduce((x, y) -> x + y)));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
