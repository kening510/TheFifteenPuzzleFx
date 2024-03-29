package sample;

import Controller.GameController;
import View.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameView gv = new GameView();
        GameController gc = new GameController(gv);
        primaryStage.setTitle("The Fifteen Puzzle");
        Scene scene = new Scene(gv.designLayout, 425, 375);
        primaryStage.setScene(scene);
        Image image = new Image("/pikachu.png");
        primaryStage.getIcons().add(image);
        scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        gv.setUp();
        gc.setUpButtons();
        gc.shuffleButton();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
