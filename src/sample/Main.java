package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    GridPane puzzleLayout = new GridPane();
    VBox designLayout = new VBox();
    HBox buttonLayout = new HBox();
    private Button newGameButton;
    private List<Button> buttonList = new ArrayList<>();


    public void createAllButtons() {
        int buttonNr = 0;
        for (int i = 0; i < 15; i++){
            Button button = new Button("" + ++buttonNr);
            button.setMinWidth(50);
            button.setMinHeight(50);
            buttonList.add(button);
        }

        designLayout.getChildren().add(puzzleLayout);
        newGameButton = new Button("New Game");
        newGameButton.setId("newGameButton");
        buttonLayout.getChildren().add(newGameButton);
        designLayout.getChildren().add(buttonLayout);
    }

    public void addNewGameAction() {
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shuffleButton();
            }
        });
    }

    public void shuffleButton() {
        Collections.shuffle(buttonList);
        emptyPuzzleLayout();
        int counter = 0;
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                if(counter == 15){
                    break;
                }else{
                Button buttonToAdd = buttonList.get(counter);
                counter++;
                puzzleLayout.add(buttonToAdd, x, y);
                }
            }
        }
    }

    public void emptyPuzzleLayout() {
        puzzleLayout.getChildren().removeAll(buttonList);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Fifteen Puzzle");
        puzzleLayout.setVgap(2);
        puzzleLayout.setHgap(2);
        puzzleLayout.setPadding(new Insets(5));
        Scene scene = new Scene(designLayout, 300, 275);
        primaryStage.setScene(scene);

        scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        createAllButtons();
        shuffleButton();
        addNewGameAction();
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
