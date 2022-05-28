package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class KnightChessComponent extends ChessComponent{
    private static Image KNIGHT_WHITE;
    private static Image KNIGHT_BLACK;
    private Image KnightImage;


    private int in = 0;


    public void loadResource() throws IOException {
        if (KNIGHT_WHITE == null) {
            KNIGHT_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (KNIGHT_BLACK == null) {
            KNIGHT_BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }
    }







    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                KnightImage = KNIGHT_WHITE;
            } else if (color == ChessColor.BLACK) {
                KnightImage = KNIGHT_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKnightImage(color);
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
        int differenceOfX = Math.abs(source.getX() - destination.getX());
        int differenceOfY = Math.abs(source.getY() - destination.getY());
        return (differenceOfX == 1 && differenceOfY == 2) || (differenceOfX == 2 && differenceOfY == 1);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(KnightImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);

        if (in == 1 ){
            g.setColor(Color.CYAN);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.drawImage(KnightImage, 0, 0, getWidth() , getHeight(), this);
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

}
