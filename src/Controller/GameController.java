package Controller;

import View.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Collections;
import java.util.Optional;

public class GameController {
    private MusicPlayer musicPlayer = new MusicPlayer();
    private Button highScoreButton = new Button();
    private GameView gv;
    private int currentMoves;
    private HighScoreController hc = new HighScoreController();


    public GameController(GameView gameView) {
        this.gv = gameView;
        musicPlayer.loadSound();
    }

    public void setUpButtons() {
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

        for (Button button : gv.buttonList) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    moveAction((Button) actionEvent.getSource());
                }
            });
        }

        gv.helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cheatingAction();
            }
        });
    }


    public void moveAction(Button clickedButton) {
        int columnIndex = GridPane.getColumnIndex(clickedButton);
        int rowIndex = GridPane.getRowIndex(clickedButton);

        musicPlayer.playSound();
        currentMoves++;

        if (getButtonFromPuzzle(columnIndex - 1, rowIndex) == null) {
            if (columnIndex - 1 >= 0) {
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton, columnIndex - 1, rowIndex);
            }
        }
        if (getButtonFromPuzzle(columnIndex + 1, rowIndex) == null) {
            if (columnIndex + 1 <= 3) {
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton, columnIndex + 1, rowIndex);
            }
        }

        if (getButtonFromPuzzle(columnIndex, rowIndex - 1) == null) {
            if (rowIndex - 1 >= 0) {
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton, columnIndex, rowIndex - 1);
            }
        }
        if (getButtonFromPuzzle(columnIndex, rowIndex + 1) == null) {
            if (rowIndex + 1 <= 3) {
                gv.puzzleLayout.getChildren().remove(clickedButton);
                gv.puzzleLayout.add(clickedButton, columnIndex, rowIndex + 1);
            }
        }
        if (checkIfWinning()) {
            win();
        }
    }

    private Node getButtonFromPuzzle(int col, int row) {
        for (Node node : gv.puzzleLayout.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public void getHighScoreList() {
        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hc.readHighScoreList();
            }
        });
    }

    public void shuffleButton() {
        Collections.shuffle(gv.buttonList);
        emptyPuzzleLayout();
        int counter = 0;
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                if (counter == 15) {
                    break;
                } else {
                    Button buttonToAdd = gv.buttonList.get(counter);
                    counter++;
                    gv.puzzleLayout.add(buttonToAdd, x, y);
                }
            }
        }
        currentMoves = 0;
    }

    private void emptyPuzzleLayout() {
        gv.puzzleLayout.getChildren().removeAll(gv.buttonList);
    }

    public void cheatingAction() {
        Button newButton;
        for (int i = 0; i < 15; i++) {
            newButton = gv.buttonList.get(i);
            newButton.setText(String.valueOf(i + 1));
        }

    }


    public boolean checkIfWinning() {
        int i = 1;
        for (int y = 0; y <= 3; y++) {
            for (int x = 0; x <= 3; x++) {
                Button b = (Button) getButtonFromPuzzle(x, y);
                if (i == 16 && b == null) {
                    continue;
                }
                if (b != null) {
                    if (!b.getText().equals("" + i)) {
                        return false;
                    }
                } else {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public void win() {
        Alert alert = new Alert(Alert.AlertType.NONE, "Congratulations! You won!", ButtonType.NEXT);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/cheers.png"));
        alert.showAndWait();


        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("New record");
        textInputDialog.setHeaderText("You have talent!");
        textInputDialog.setContentText("Please enter your name: ");

        Optional<String> result = textInputDialog.showAndWait();
        if(result.isPresent()){
            hc.writeToHighScoreList(result.get(),currentMoves);
        }

    }



}
