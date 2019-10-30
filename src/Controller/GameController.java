package Controller;

import View.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Collections;

public class GameController {
    private MusicPlayer musicPlayer = new MusicPlayer();
    private Button highScoreButton = new Button();
    private GameView gv;

    public GameController(GameView gameView){
        this.gv = gameView;
        musicPlayer.loadSound();
    }

    public void setUpButtons(){
        gv.newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                shuffleButton();
            }
        });

        gv.exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        for (Button button: gv.buttonList) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    moveAction((Button)actionEvent.getSource());
                }
            });
        }
    }


    public void moveAction(Button clickedButton){
        int columnIndex = GridPane.getColumnIndex(clickedButton);
        int rowIndex = GridPane.getRowIndex(clickedButton);

        musicPlayer.playSound();

        if(getButtonFromPuzzle(columnIndex-1,rowIndex) == null){
            if(columnIndex-1>=0){
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton,columnIndex-1,rowIndex);
            }
        }
        if(getButtonFromPuzzle(columnIndex+1,rowIndex) == null){
            if(columnIndex+1<=3){
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton,columnIndex+1,rowIndex);
            }
        }

        if(getButtonFromPuzzle(columnIndex,rowIndex-1) == null){
            if(rowIndex-1 >= 0){
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton,columnIndex,rowIndex-1);
            }
        }
        if (getButtonFromPuzzle(columnIndex,rowIndex+1) == null){
            if(rowIndex+1 <= 3){
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton,columnIndex,rowIndex+1);
            }
        }
    }

    private Node getButtonFromPuzzle(int col, int row){
        for(Node node: gv.puzzleLayout.getChildren()){
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row){
                return node;
            }
        }
        return null;
    }

    public void getHighScoreList(){
        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            }
        });
    }

    public void shuffleButton(){
        Collections.shuffle(gv.buttonList);
        emptyPuzzleLayout();
        int counter = 0;
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                if(counter == 15){
                    break;
                }else{
                    Button buttonToAdd = gv.buttonList.get(counter);
                    counter++;
                    gv.puzzleLayout.add(buttonToAdd, x, y);
                }
            }
        }
    }

    private void emptyPuzzleLayout() {
        gv.puzzleLayout.getChildren().removeAll(gv.buttonList);
    }
}
