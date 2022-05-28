package model;

import view.ChessGameFrame;

import java.awt.*;
import java.util.Timer;

public class TimeCounter implements Runnable{
    @Override
    public void run() {
        getTime();
    }


    public TimeCounter(ChessGameFrame chessGameFrame) {
        this.chessGameFrame = chessGameFrame;
    }


    ChessGameFrame chessGameFrame;


    public static int time = 20;
    public void getTime(){
        while (true){
            Label timeCounter = new Label("With 30s left");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (time <= 0){
               if (chessGameFrame.chessboard.getCurrentColor()==ChessColor.BLACK){
                   chessGameFrame.chessboard.setCurrentColor( ChessColor.WHITE);
                   chessGameFrame.getCurrentPlayerLabel().setText("Current Player : WHITE");
               } else {
                   chessGameFrame.getCurrentPlayerLabel().setText("Current Player : BLACK");
                   chessGameFrame.chessboard.setCurrentColor(ChessColor.BLACK);
               }
//               chessGameFrame.repaint();
               time = 20;
            }


            chessGameFrame.setTimer(--time);
        }

    }


}
