package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    GridPane gridPane = new GridPane();

    public void getAllButtons(){
        int buttonNr = 0;
        for(int y = 0; y<=3; y++){
            for(int x =0; x<=3; x++){
            Button button = new Button("" + ++buttonNr);
            gridPane.add(button,x,y);
            }
        }
        gridPane.getChildren().remove(15);
    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Fifteen Puzzle");
        primaryStage.setScene(new Scene(gridPane, 300, 275));
        getAllButtons();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
