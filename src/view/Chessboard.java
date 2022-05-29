package view;


import Music.Click;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;
    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];


    public boolean isGameOver() {
        return GameOver;
    }

    private boolean GameOver = false;


    public static ChessColor currentColor = ChessColor.WHITE;
    private JLabel CurrentPlayerLabel;
    private JLabel WinnerLabel;
    private JLabel chessBoardError;
    private JLabel chessError;
    private JLabel currentPlayerError;
    private JLabel timeCounter;
    public static ArrayList<ChessComponent[][]> HuiQi  = new ArrayList<>();

    public void setChessBoardError(JLabel chessBoardError) {
        this.chessBoardError = chessBoardError;
    }

    public void setChessError(JLabel chessError) {
        this.chessError = chessError;
    }

    public void setCurrentPlayerError(JLabel currentPlayerError) {
        this.currentPlayerError = currentPlayerError;
    }

    public void setTimeCounter(JLabel timeCounter) {
        this.timeCounter = timeCounter;
    }

    public void setGameOver(boolean gameOver) {
        GameOver = gameOver;
    }

    public void setWinnerLabel(JLabel winnerLabel) {
        this.WinnerLabel = winnerLabel;
    }

    public void setCurrentPlayerLabel(JLabel currentPlayerLabel) {
        this.CurrentPlayerLabel = currentPlayerLabel;
    }

    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;

    public void swapQueenRook(ChessComponent chess1, ChessComponent chess2){

        int row =chess1.getChessboardPoint().getX();
        int col1=Math.min(chess1.getChessboardPoint().getY(),chess2.getChessboardPoint().getY())+2;
        int col2=0;
        if (chess2.getChessboardPoint().getY()==0){
            col2=3;
        }else if (chess2.getChessboardPoint().getY()==7){
            col2=5;
        }
        chess1.swapLocation(chess2);
        remove(chess1);
        remove(chess2);
        add(new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        add(new EmptySlotComponent(chess1.getChessboardPoint(), chess1.getLocation(), clickController, CHESS_SIZE));
        initQueenOnBoard(row,col1,currentColor);
        initRookOnBoard(row,col2,currentColor);

    }

    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);
        initiateEmptyChessboard();


        // FIXME: Initialize chessboard for testing only.
        // 初始化车
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(7, 0, ChessColor.WHITE);
        initRookOnBoard(7, CHESSBOARD_SIZE - 1, ChessColor.WHITE);


        //初始化象
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE);


        //初始化马
        initKnightOnBoard(0, 1, ChessColor.BLACK);
        initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);

        //初始化兵
        initPawnOnBoard(1, 0, ChessColor.BLACK);
        initPawnOnBoard(1, 1, ChessColor.BLACK);
        initPawnOnBoard(1, 2, ChessColor.BLACK);
        initPawnOnBoard(1, 3, ChessColor.BLACK);
        initPawnOnBoard(1, 4, ChessColor.BLACK);
        initPawnOnBoard(1, 5, ChessColor.BLACK);
        initPawnOnBoard(1, 6, ChessColor.BLACK);
        initPawnOnBoard(1, 7, ChessColor.BLACK);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 0, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 1, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 2, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 3, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 4, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 5, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 6, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 7, ChessColor.WHITE);


        initQueenOnBoard(0, 3, ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE);


        initKingOnBoard(0, 4, ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE);


    }


    public void ChangeRook(){
        for (int i = 0; i < 8; i++) {
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.BLACK){
                initRookOnBoard(0,i,ChessColor.BLACK);
            }
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.WHITE){
                initRookOnBoard(0,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.WHITE){
                initRookOnBoard(7,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.BLACK){
                initRookOnBoard(7,i,ChessColor.BLACK);
            }
        }
    }
    public void ChangeQueen(){
        for (int i = 0; i < 8; i++) {
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.BLACK){
                initQueenOnBoard(0,i,ChessColor.BLACK);
            }
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.WHITE){
                initQueenOnBoard(0,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.WHITE){
                initQueenOnBoard(7,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.BLACK){
                initQueenOnBoard(7,i,ChessColor.BLACK);
            }
        }
    }
    public void ChangeBishop(){
        for (int i = 0; i < 8; i++) {
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.BLACK){
                initBishopOnBoard(0,i,ChessColor.BLACK);
            }
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.WHITE){
                initBishopOnBoard(0,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.WHITE){
                initBishopOnBoard(7,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.BLACK){
                initBishopOnBoard(7,i,ChessColor.BLACK);
            }
        }
    }
    public void ChangeKnight(){
        for (int i = 0; i < 8; i++) {
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.BLACK){
                initKnightOnBoard(0,i,ChessColor.BLACK);
            }
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.WHITE){
                initKnightOnBoard(0,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.WHITE){
                initKnightOnBoard(7,i,ChessColor.WHITE);
            }
            if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.BLACK){
                initKnightOnBoard(7,i,ChessColor.BLACK);
            }
        }
    }



    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }


    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.

        if (!(chess2 instanceof EmptySlotComponent)) {
            if (chess2 instanceof KingChessComponent && chess2.getChessColor() == ChessColor.BLACK) {
                WinnerLabel.setText("Whiter player win");
                GameOver = true;
            }
            if (chess2 instanceof KingChessComponent && chess2.getChessColor() == ChessColor.WHITE) {
                WinnerLabel.setText("Black player win");
                GameOver = true;
            }
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;





        chess1.repaint();
        chess2.repaint();
    }



    public static int Counter1 = 0;

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    //更新当前行棋方
    public void swapColor() {

        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        CurrentPlayerLabel.setText("Current Player:" + currentColor.toString());
        ChessComponent[][] temp = new ChessComponent[chessComponents.length][chessComponents[0].length];
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j <chessComponents[0].length ; j++) {
                temp[i][j] = chessComponents[i][j];
            }
        }
        if (!HuiQi.contains(temp)){
            HuiQi.add(temp);
        }
        TimeCounter.time = 20;

        Click pilipala = new Click();
        for (int i = 0; i < 8; i++) {
            if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.BLACK){
                WinnerLabel.setText("Change the Pawn");
            }else if (chessComponents[0][i]instanceof PawnChessComponent&&chessComponents[0][i].getChessColor() == ChessColor.WHITE){
                WinnerLabel.setText("Change the Pawn");
            }else if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.WHITE){
                WinnerLabel.setText("Change the Pawn");
            }else if (chessComponents[7][i]instanceof PawnChessComponent&&chessComponents[7][i].getChessColor() == ChessColor.BLACK){
                WinnerLabel.setText("Change the Pawn");
            }
        }

    }


    public List<String> getInfoOfChessBoard (ChessComponent[][] chessComponents){

        String[] chessComponentsForFile = new String[9];
        for (int i = 0; i <9 ; i++) {
            chessComponentsForFile[i]="";
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessComponents[i][j] instanceof KingChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"K";
                }
                if (chessComponents[i][j] instanceof QueenChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"Q";
                }
                if (chessComponents[i][j] instanceof BishopChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"B";
                }
                if (chessComponents[i][j] instanceof PawnChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"P";
                }
                if (chessComponents[i][j] instanceof KnightChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"N";
                }
                if (chessComponents[i][j] instanceof RookChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"R";
                }
                if (chessComponents[i][j] instanceof RookChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"r";
                }
                if (chessComponents[i][j] instanceof KingChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"k";
                }
                if (chessComponents[i][j] instanceof QueenChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"q";
                }
                if (chessComponents[i][j] instanceof BishopChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"b";
                }
                if (chessComponents[i][j] instanceof PawnChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"p";
                }
                if (chessComponents[i][j] instanceof KnightChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"n";
                }
                if (chessComponents[i][j] instanceof EmptySlotComponent){
                    chessComponentsForFile[i] = chessComponentsForFile[i]+"0";
                }
            }

        }
        if (currentColor == ChessColor.BLACK){
            chessComponentsForFile[8] = chessComponentsForFile[8]+"b";
        }else {
            chessComponentsForFile[8] = chessComponentsForFile[8]+"w";
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <9 ; i++) {
            ans.add(chessComponentsForFile[i]);
        }

        return ans;
    }





















//已完成部分-----------------------------------------------------------------------------------------------------
    //一堆初始化
void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);

        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    //读档加判断数据正确性
    public void loadGame(List<String> chessData) {
        initiateEmptyChessboard();

        for (int i = 0; i < chessData.size(); i++) {
            for (int j = 0; j < chessData.get(i).length(); j++) {
                if (chessData.get(i).charAt(j) == 'P') {
                    initPawnOnBoard(i, j, ChessColor.BLACK);
                } else if (chessData.get(i).charAt(j) == 'p') {
                    initPawnOnBoard(i, j, ChessColor.WHITE);
                } else if (chessData.get(i).charAt(j) == 'R') {
                    initRookOnBoard(i, j, ChessColor.BLACK);
                } else if (chessData.get(i).charAt(j) == 'r') {
                    initRookOnBoard(i, j, ChessColor.WHITE);
                } else if (chessData.get(i).charAt(j) == 'K') {
                    initKingOnBoard(i, j, ChessColor.BLACK);
                } else if (chessData.get(i).charAt(j) == 'k') {
                    initKingOnBoard(i, j, ChessColor.WHITE);
                } else if (chessData.get(i).charAt(j) == 'Q') {
                    initQueenOnBoard(i, j, ChessColor.BLACK);
                } else if (chessData.get(i).charAt(j) == 'q') {
                    initQueenOnBoard(i, j, ChessColor.WHITE);
                } else if (chessData.get(i).charAt(j) == 'N') {
                    initKnightOnBoard(i, j, ChessColor.BLACK);
                } else if (chessData.get(i).charAt(j) == 'n') {
                    initKnightOnBoard(i, j, ChessColor.WHITE);
                } else if (chessData.get(i).charAt(j) == 'B') {
                    initBishopOnBoard(i, j, ChessColor.BLACK);
                } else if (chessData.get(i).charAt(j) == 'b') {
                    initBishopOnBoard(i, j, ChessColor.WHITE);
                }
            }
        }

        repaint();


        if (chessData.get(chessData.size()-1).charAt(0) != 'w' && chessData.get(chessData.size()-1).charAt(0) != 'b'){
            currentPlayerError.setText("Error code : 103");

            for (int i = 0; i <chessData.size() ; i++) {
                if (chessData.size() !=8 || chessData.get(i).length()!=8){
                    chessBoardError.setText("Error code : 101");

                }
            }
            for (int i = 0; i < chessData.size(); i++) {
                for (int j = 0; j < chessData.get(i).length(); j++) {
                    if (chessData.get(i).charAt(j) == 'P') {

                    } else if (chessData.get(i).charAt(j) == 'p') {

                    } else if (chessData.get(i).charAt(j) == 'R') {

                    } else if (chessData.get(i).charAt(j) == 'r') {

                    } else if (chessData.get(i).charAt(j) == 'K') {

                    } else if (chessData.get(i).charAt(j) == 'k') {

                    } else if (chessData.get(i).charAt(j) == 'Q') {

                    } else if (chessData.get(i).charAt(j) == 'q') {

                    } else if (chessData.get(i).charAt(j) == 'N') {

                    } else if (chessData.get(i).charAt(j) == 'n') {

                    } else if (chessData.get(i).charAt(j) == 'B') {

                    } else if (chessData.get(i).charAt(j) == 'b') {

                    } else if (chessData.get(i).charAt(j)== '0'){

                    }else{
                        chessError.setText("Error code : 102");

                    }
                }
            }

        }else {
            for (int i = 0; i <chessData.size()-1 ; i++) {
                if (chessData.size()-1 !=8 || chessData.get(i).length()!=8){
                    chessBoardError.setText("Error code : 101");

                }
            }
            for (int i = 0; i < chessData.size()-1; i++) {
                for (int j = 0; j < chessData.get(i).length(); j++) {
                    if (chessData.get(i).charAt(j) == 'P') {

                    } else if (chessData.get(i).charAt(j) == 'p') {

                    } else if (chessData.get(i).charAt(j) == 'R') {

                    } else if (chessData.get(i).charAt(j) == 'r') {

                    } else if (chessData.get(i).charAt(j) == 'K') {

                    } else if (chessData.get(i).charAt(j) == 'k') {

                    } else if (chessData.get(i).charAt(j) == 'Q') {

                    } else if (chessData.get(i).charAt(j) == 'q') {

                    } else if (chessData.get(i).charAt(j) == 'N') {

                    } else if (chessData.get(i).charAt(j) == 'n') {

                    } else if (chessData.get(i).charAt(j) == 'B') {

                    } else if (chessData.get(i).charAt(j) == 'b') {

                    } else if (chessData.get(i).charAt(j) == '0'){

                    }else{
                        chessError.setText("Error code : 102");

                    }
                }
            }
        }
        if (chessData.get(8).charAt(0)=='b'){
            currentColor=ChessColor.BLACK;
            CurrentPlayerLabel.setText("Current Player: BLACK" );
            repaint();
        }
        if (chessData.get(8).charAt(0)=='w'){
            currentColor=ChessColor.WHITE;
            CurrentPlayerLabel.setText("Current Player: WHITE" );
            repaint();
        }
    }

}
