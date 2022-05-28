import model.TimeCounter;
import view.ChessGameFrame;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

//        SwingUtilities.invokeLater(() -> {
//
//        });

        ChessGameFrame mainFrame = new ChessGameFrame(1300,800);
        mainFrame.setVisible(true);

        TimeCounter timeCounter = new TimeCounter(mainFrame);
        Thread timerThread = new Thread(timeCounter);



        timerThread.start();

    }

}
