package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PawnChessComponent extends ChessComponent {
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;
    private Image PawnImage;
    public static boolean blackUp = false;
    public static boolean whiteUp = false;
    private int in = 0;

    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }


    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                PawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                PawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                in = 1;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                in = 0;
                repaint();
            }
        });
    }


    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();

        //选取黑兵且位于初始位置的情况
        if (chessComponents[source.getX()][source.getY()].getChessColor() == ChessColor.BLACK && source.getX() == 1) {

            //走1步或2步
            if (destination.getY() == source.getY() && (destination.getX() == 2 || destination.getX() == 3)) {

                if (destination.getX()-source.getX() == 1 && chessComponents[destination.getX()][destination.getY()]instanceof EmptySlotComponent){
                    return true;
                }
                if (destination.getX()-source.getX() == 2 && chessComponents[destination.getX()][destination.getY()]instanceof EmptySlotComponent&&chessComponents[destination.getX()-1][destination.getY()]instanceof EmptySlotComponent){
                    return true;
                }
                return false;
            }
            return destination.getX() == 2 && Math.abs(destination.getY() - source.getY()) == 1&& chessComponents[destination.getX()][destination.getY()].getChessColor()==ChessColor.WHITE;
        } else
            //选取黑兵但不位于初始位置
            if (chessComponents[source.getX()][source.getY()].getChessColor() == ChessColor.BLACK && source.getX() != 1) {
                //只能纵向走一步
                if (destination.getY() == source.getY() && destination.getX() - source.getX() == 1) {
                    //判断是否被堵
                    return chessComponents[destination.getX()][source.getY()] instanceof EmptySlotComponent;
                }
                return destination.getX() - source.getX() == 1 && Math.abs(destination.getY() - source.getY()) == 1 && chessComponents[destination.getX()][destination.getY()].getChessColor()==ChessColor.WHITE;
            }



        //选取白兵且初始位置
        if (chessComponents[source.getX()][source.getY()].getChessColor() == ChessColor.WHITE && source.getX() == 6) {

            //走1或2步
            if (destination.getY() == source.getY() && (destination.getX() - source.getX() == -1 || destination.getX() - source.getX() == -2)) {
                if (destination.getX()-source.getX() == -1 && chessComponents[destination.getX()][destination.getY()]instanceof EmptySlotComponent){
                    return true;
                }
                if (destination.getX()-source.getX() == -2 && chessComponents[destination.getX()][destination.getY()]instanceof EmptySlotComponent&&chessComponents[destination.getX()+1][destination.getY()]instanceof EmptySlotComponent){
                    return true;
                }
                return false;
            }
            return destination.getX() - source.getX() == -1 && Math.abs(destination.getY() - source.getY()) == 1 && chessComponents[destination.getX()][destination.getY()].getChessColor()==ChessColor.BLACK;

            //不在初始位置的白兵
        } else
            if (chessComponents[source.getX()][source.getY()].getChessColor() == ChessColor.WHITE && source.getX() != 6) {

            //走一步
            if (destination.getY() == source.getY() && destination.getX() - source.getX() == -1) {
                return chessComponents[destination.getX()][source.getY()] instanceof EmptySlotComponent;
            }
            return destination.getX() - source.getX() == -1 && Math.abs(destination.getY() - source.getY()) == 1 && chessComponents[destination.getX()][destination.getY()].getChessColor() == ChessColor.BLACK;
        }


        return false;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(PawnImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);

        if (in == 1 ){
            g.setColor(Color.CYAN);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.drawImage(PawnImage, 0, 0, getWidth() , getHeight(), this);
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }


}
