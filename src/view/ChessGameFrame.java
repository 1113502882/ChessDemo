package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    JLabel CurrentPlayerLabel;
    JLabel WinnerLabel;
    JLabel chessBoardError;
    JLabel chessError;
    JLabel currentPlayerError;
    JLabel formatError;
    JLabel timeCounter;
    JPanel upBian;
    public Chessboard chessboard;
    int Counter = 0;

    public JLabel getCurrentPlayerLabel() {
        return CurrentPlayerLabel;
    }

    public JLabel getWinnerLabel() {
        return WinnerLabel;
    }

    public JLabel getChessBoardError() {
        return chessBoardError;
    }

    public JLabel getChessError() {
        return chessError;
    }

    public JLabel getCurrentPlayerError() {
        return currentPlayerError;
    }

    public JLabel getFormatError() {
        return formatError;
    }

    public JLabel getTimeCounter() {
        return timeCounter;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getFileButton() {
        return fileButton;
    }


    Users player1 = new Users(123,"123",0,"LiShuai");
    Users player2 = new Users(456,"456",7,"LiuChunHong");
    Users player3 = new Users(789,"789",9,"linJiDong");


    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessError();
        addChessBoardError();
        addCurrentPlayerError();
        addCurrentPlayerLabel();
        addFormatError();
        addWinnerLabel();
        addTimeLabel();
        addChessboard();
        addLeaderBoardButton();
        addHelloButton();
        addHuiQiButton();
        addLoadButton();
        addFileButton();


        addPlayGame();
        repaint();


//        if (PawnChessComponent.whiteUp || PawnChessComponent.blackUp){
//            addUpBian();
//            setVisible(true);
//        }





    }


    /**
     * 在游戏面板中添加棋盘
     */

    private void addChessboard() {

        chessboard.setCurrentPlayerError(currentPlayerError);
        chessboard.setChessError(chessError);
        chessboard.setChessBoardError(chessBoardError);
        chessboard.setCurrentPlayerLabel(CurrentPlayerLabel);
        chessboard.setWinnerLabel(WinnerLabel);
        chessboard.setTimeCounter(timeCounter);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        gameController = new GameController(chessboard);
        add(chessboard);

    }

    public String[][] getInfoOfChessBoard (ChessComponent[][] chessComponents){
        String[][] chessComponentsForFile = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessComponents[i][j] instanceof KingChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i][j] = "K";
                }
                if (chessComponents[i][j] instanceof QueenChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i][j] = "Q";
                }
                if (chessComponents[i][j] instanceof BishopChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i][j] = "B";
                }
                if (chessComponents[i][j] instanceof PawnChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i][j] = "P";
                }
                if (chessComponents[i][j] instanceof KnightChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i][j] = "N";
                }
                if (chessComponents[i][j] instanceof RookChessComponent && chessComponents[i][j].getChessColor() == ChessColor.BLACK){
                    chessComponentsForFile[i][j] = "R";
                }
                if (chessComponents[i][j] instanceof RookChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i][j] = "r";
                }
                if (chessComponents[i][j] instanceof KingChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i][j] = "k";
                }
                if (chessComponents[i][j] instanceof QueenChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i][j] = "q";
                }
                if (chessComponents[i][j] instanceof BishopChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i][j] = "b";
                }
                if (chessComponents[i][j] instanceof PawnChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i][j] = "p";
                }
                if (chessComponents[i][j] instanceof KnightChessComponent && chessComponents[i][j].getChessColor() == ChessColor.WHITE){
                    chessComponentsForFile[i][j] = "n";
                }
                if (chessComponents[i][j] instanceof EmptySlotComponent){
                    chessComponentsForFile[i][j] = "0";
                }
            }
        }
        return chessComponentsForFile;
    }


    /**
     * 在游戏面板中添加标签
     */
    private void addCurrentPlayerLabel() {
        CurrentPlayerLabel = new JLabel("Current Player: WHITE");
        CurrentPlayerLabel.setLocation(HEIGTH, HEIGTH / 10);
        CurrentPlayerLabel.setSize(200, 60);
        CurrentPlayerLabel.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(CurrentPlayerLabel);
    }

    private void addWinnerLabel() {
        WinnerLabel = new JLabel("");
        WinnerLabel.setLocation(HEIGTH, HEIGTH / 12);
        WinnerLabel.setSize(200, 60);
        WinnerLabel.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(WinnerLabel);
    }

    private void addChessBoardError() {
        chessBoardError = new JLabel("");
        chessBoardError.setLocation(HEIGTH, HEIGTH / 12-15);
        chessBoardError.setSize(200, 60);
        chessBoardError.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(chessBoardError);
    }

    private void addChessError() {
        chessError = new JLabel("");
        chessError.setLocation(HEIGTH, HEIGTH / 12-30);
        chessError.setSize(200, 60);
        chessError.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(chessError);
    }

    private void addCurrentPlayerError() {
        currentPlayerError= new JLabel("");
        currentPlayerError.setLocation(HEIGTH, HEIGTH / 12-45);
        currentPlayerError.setSize(200, 60);
        currentPlayerError.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(currentPlayerError);
    }
    private void addFormatError() {
        formatError= new JLabel("");
        formatError.setLocation(HEIGTH, HEIGTH / 12-60);
        formatError.setSize(200, 60);
        formatError.setFont(new Font("Rockwell", Font.BOLD, 15));
        add(formatError);
    }
    private void addTimeLabel()  {
        timeCounter = new JLabel("With 20s left");
        timeCounter.setLocation(HEIGTH,HEIGTH/12-75);
        timeCounter.setSize(300,100);
        timeCounter.setFont(new Font("Rockwell", Font.BOLD, 20));
        repaint();
        add(timeCounter);
    }



    public void setTimer(int i){
        timeCounter.setText("With "+i + "s left");
        repaint();
    }



    public void addPlayGame(){
        JButton button = new JButton("Play Game");
        button.setLocation(WIDTH/2-50, HEIGTH / 2);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
//        Image image = background2.getImage();
//        Image smallImage = image.getScaledInstance(WIDTH, HEIGTH, Image.SCALE_FAST);
//        ImageIcon backgrounds = new ImageIcon(smallImage);

//        JLabel jlabel = new JLabel(backgrounds);
//        jlabel.setBounds(0, 0, WIDTH, HEIGTH);
//        add(jlabel);

        button.addActionListener(e -> {
            String account = JOptionPane.showInputDialog(this, "Input your account here");
            String key = JOptionPane.showInputDialog(this, "Input your key here");
            if (account.equals(player1.getId()) && key.equals(player1.getKey())){
                JOptionPane.showMessageDialog(this,"Success!");
                player1.setPoints(player1.getPoints()+1);
                setVisible(true);
            }else  if (account.equals(player2.getId()) && key.equals(player2.getKey())){
                JOptionPane.showMessageDialog(this,"Success!");
                player2.setPoints(player2.getPoints()+1);
                setVisible(true);
            }else if (account.equals(player3.getId()) && key.equals(player3.getKey())){
                JOptionPane.showMessageDialog(this,"Success!");
                player3.setPoints(player3.getPoints()+1);
                setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(this,"Wrong account or key !");
                setVisible(false);
            }

//            jlabel.setVisible(false);
//            Music.audioPlayWave.start();
            @SuppressWarnings("unused")
            int musicOpenLab = 1;
            repaint();
            addCurrentPlayerLabel();
            addWinnerLabel();
            addChessboard();
            addHelloButton();
            addLoadButton();
//            addCloseMusicButton();
            addFileButton();
//            addBackground();
            button.setVisible(false);
            repaint();
        });
    }







    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */
//    private void addUpBian() {
//        JPanel upBian = new JPanel();
//        upBian.setLayout(new GridLayout(2,2,10,10 ));
//        JButton chooseQueen = new JButton("Queen");
//        JButton chooseBishop = new JButton("Bishop");
//        JButton chooseKnight = new JButton("Knight");
//        JButton chooseRook = new JButton("Rook");
//
//        upBian.setLocation(HEIGTH+250,HEIGTH/10+60);
//        upBian.setSize(250,120);
//        upBian.add(chooseQueen);
//        upBian.add(chooseBishop);
//        upBian.add(chooseKnight);
//        upBian.add(chooseRook);
//        add(upBian);
//        setVisible(true);
//
//    }
    private void addHelloButton() {
        JButton button = new JButton("Show Hello Here");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addLeaderBoardButton() {
        JButton button = new JButton("LeaderBoard");
        String LeaderBoard = player3.getId()+": " + player3.getPoints() +"\n" + player2.getId()+": " + player2.getPoints() +"\n"+ player1.getId()+": "+player1.getPoints();
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, LeaderBoard));
        button.setLocation(HEIGTH, HEIGTH / 10 + 600);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }


    JButton loadButton;
    JButton fileButton;


    private void addLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setLocation(HEIGTH, HEIGTH / 10 + 240);
        loadButton.setSize(200, 60);
        loadButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(loadButton);


        loadButton.addActionListener(e -> {
            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this, "Input Path here");
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileFilter(new FileNameExtensionFilter("txt","txt"));
            int result = chooser.showOpenDialog(getParent());
            if (result == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                gameController.loadGameFromFile(file.getAbsolutePath());
            }

//            if (!path.contains("txt")){
//                formatError.setText("Error code : 104");
//            }
//            gameController.loadGameFromFile(path);
        });
//        this.loadButton = loadButton;
    }
    private void addHuiQiButton() {

        JButton button = new JButton("Withdraw");
        button.addActionListener(e -> {
            gameController.loadGameFromHuiQi();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }


    private void addFileButton() {

        JButton button = new JButton("FileGame");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            ChessComponent [][] kao = gameController.getChessboard().getChessComponents();
            String[][] chessComponentsForFile = getInfoOfChessBoard(kao);

            File file = null;
            FileWriter fw = null;
                file = new File("D:/ChessDemo/resource/存档" + Counter + ".txt");


                if (!file.exists()){
                    try {
                        file.createNewFile();
                        fw = new FileWriter(file);
                        for (int i = 0; i <8 ; i++) {
                            for (int j = 0; j <8 ; j++) {
                                fw.write(chessComponentsForFile[i][j]);
                            }
                            fw.write("\n");
                            fw.flush();
                        }
                        if (gameController.getChessboard().getCurrentColor() == ChessColor.WHITE){
                            fw.write("w");
                            fw.flush();
                        }else {
                            fw.write("b");
                            fw.flush();
                        }
                        Counter++;
                        JOptionPane.showMessageDialog(this,"Success!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        });
        this.fileButton = button;
    }


}
