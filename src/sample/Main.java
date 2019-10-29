package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
    private Button button;
    private Button newGameButton;
    private Button exitButton;
    private Button highScoreButton;
    private List<Button> buttonList = new ArrayList<>();

    private MusicPlayer musicPlayer;


    public void createAllButtons() {
        int buttonNr = 0;
        for (int i = 0; i < 15; i++){
            button = new Button("" + ++buttonNr);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    moveAction((Button)actionEvent.getSource());
                }
            });
            button.setMinWidth(50);
            if(buttonNr%2 == 0){
                button.setId("even");
            }else{
            button.setId("odd");
            }
            button.setMinHeight(50);
            buttonList.add(button);
        }

        designLayout.getChildren().add(puzzleLayout);
        newGameButton = new Button("New Game");
        newGameButton.setId("menuButton");
        exitButton = new Button("Exit");
        exitButton.setId("menuButton");
        highScoreButton = new Button("High Score");
        highScoreButton.setId("menuButton");
        buttonLayout.getChildren().add(newGameButton);
        buttonLayout.getChildren().add(exitButton);
        buttonLayout.getChildren().add(highScoreButton);
        designLayout.getChildren().add(buttonLayout);
    }

    public void moveAction(Button clickedButton){
        int columnIndex = GridPane.getColumnIndex(clickedButton);
        int rowIndex = GridPane.getRowIndex(clickedButton);

        musicPlayer.playSound();

        if(getButtonFromPuzzle(columnIndex-1,rowIndex) == null){
            if(columnIndex-1>=0){
            puzzleLayout.getChildren().remove(clickedButton);
            puzzleLayout.add(clickedButton,columnIndex-1,rowIndex);
            }
        }
        if(getButtonFromPuzzle(columnIndex+1,rowIndex) == null){
            if(columnIndex+1<=3){
            puzzleLayout.getChildren().remove(clickedButton);
            puzzleLayout.add(clickedButton,columnIndex+1,rowIndex);
            }
        }

        if(getButtonFromPuzzle(columnIndex,rowIndex-1) == null){
            if(rowIndex-1 >= 0){
            puzzleLayout.getChildren().remove(clickedButton);
            puzzleLayout.add(clickedButton,columnIndex,rowIndex-1);
            }
        }
        if (getButtonFromPuzzle(columnIndex,rowIndex+1) == null){
            if(rowIndex+1 <= 3){
            puzzleLayout.getChildren().remove(clickedButton);
            puzzleLayout.add(clickedButton,columnIndex,rowIndex+1);
            }
        }
    }

    public void addSoundsEffectWhenClicked() throws IllegalArgumentException{


    }

    public Node getButtonFromPuzzle(int col, int row){
        for(Node node: puzzleLayout.getChildren()){
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row){
                return node;
            }
        }
        return null;
    }


    public void addNewGameAction() {
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shuffleButton();
            }
        });
    }

    public void exitGame(){
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    public void getHighScoreList(){
        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

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
    public void start(Stage primaryStage) {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Fifteen Puzzle");
        puzzleLayout.setVgap(2);
        puzzleLayout.setHgap(2);
        puzzleLayout.setPadding(new Insets(5));
        buttonLayout.setSpacing(5);
        buttonLayout.setPadding(new Insets(10));
        Scene scene = new Scene(designLayout, 350, 305);
        primaryStage.setScene(scene);
        Image image = new Image("/pikachu.png");
        primaryStage.getIcons().add(image);
        scene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        createAllButtons();
        shuffleButton();
        addNewGameAction();
        addSoundsEffectWhenClicked();
        exitGame();
        primaryStage.show();

        musicPlayer = new MusicPlayer();
        musicPlayer.loadSound("/sounds/button_sound.mp3");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
