package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;

public class Main extends Application {
    GridPane puzzleLayout = new GridPane();
    VBox designLayout = new VBox();
    HBox buttonLayout = new HBox();


    public void createAllButtons() {
        int buttonNr = 0;
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                Button button = new Button("" + ++buttonNr);
                puzzleLayout.add(button, x, y);
                button.setMinWidth(50);
                button.setMinHeight(50);
            }
        }
        puzzleLayout.getChildren().remove(15);

        designLayout.getChildren().add(puzzleLayout);
        Button newGameButton = new Button("New Game");
        newGameButton.setId("newGameButton");
        buttonLayout.getChildren().add(newGameButton);
        designLayout.getChildren().add(buttonLayout);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Fifteen Puzzle");
        Scene scene = new Scene(designLayout, 300, 275);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        createAllButtons();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
