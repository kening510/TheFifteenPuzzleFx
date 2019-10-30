package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HighScoreController {

    public void writeToHighScoreList(String name, int moves) {
        Path path = Paths.get("high_score_list.txt");
        BufferedWriter bufWt;
        try {
            if (Files.exists(path)) {
                bufWt = Files.newBufferedWriter(path, StandardOpenOption.APPEND);
            } else {
                bufWt = Files.newBufferedWriter(path, StandardOpenOption.CREATE);
            }
            bufWt.append(name + ": "+moves);
            bufWt.append("\n");
            bufWt.flush();
            bufWt.close();
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    public void readHighScoreList(){
        String records;
        try{
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("high_score_list.txt"));
        while((records = bufferedReader.readLine()) != null){
            System.out.println(records);
        }
        bufferedReader.close();
        }catch (IOException e){
            System.out.println("Error!");
        }
    }
}
