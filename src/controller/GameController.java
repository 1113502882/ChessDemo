package controller;
import model.*;
import view.Chessboard;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static view.Chessboard.HuiQi;

public class GameController {
    private Chessboard chessboard;


    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }


    public List<String> loadGameFromFile(String path) {

        try {
            List<String> chessData = Files.readAllLines(Path.of(path));
            chessboard.loadGame(chessData);
            return chessData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> loadGameFromHuiQi() {
        List<String> chessDate = chessboard.getInfoOfChessBoard(HuiQi.get(HuiQi.size()-2));
        chessboard.loadGame(chessDate);
        return chessDate;
    }






    public Chessboard getChessboard() {
        return chessboard;
    }

}
