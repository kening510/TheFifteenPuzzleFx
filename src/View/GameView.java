package View;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GameView {

    public Button newGameButton;
    public Button exitButton;
    public List<Button> buttonList = new ArrayList<>();
    public GridPane puzzleLayout = new GridPane();
    public VBox designLayout = new VBox();
    private HBox buttonLayout = new HBox();
    public Button highScoreButton;

    public void createAllButtons() {
        int buttonNr = 0;
        for (int i = 0; i < 15; i++){
            Button button = new Button("" + ++buttonNr);
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

        puzzleLayout.setVgap(2);
        puzzleLayout.setHgap(2);
        puzzleLayout.setPadding(new Insets(5));
        buttonLayout.setSpacing(5);
        buttonLayout.setPadding(new Insets(10));
    }



}
