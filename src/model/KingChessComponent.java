package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class KingChessComponent extends ChessComponent {
    //黑王和白王图片
    private static Image KING_WHITE;
    private static Image KING_BLACK;
    //王棋子对象自身的图片，是上面两种中的一种
    private Image kingImage;



    private int in = 0;
    //读取加载后棋子的图片
    public void loadResource() throws IOException {
        if (KING_WHITE == null) {
            KING_WHITE = ImageIO.read(new File("./images/king-white.png"));
        }
        if (KING_BLACK == null) {
            KING_BLACK = ImageIO.read(new File("./images/king-black.png"));
        }
    }

    //在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
    private void initiateRookImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                kingImage = KING_WHITE;
            } else if (color == ChessColor.BLACK) {
                kingImage = KING_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateRookImage(color);
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
        if (source.getX() == destination.getX() && destination.getY() == source.getY() + 1) {
            return true;
        } else if (source.getX() == destination.getX() && destination.getY() == source.getY() - 1) {
            return true;
        } else if (source.getY() == destination.getY() && destination.getX() == source.getX() + 1) {
            return true;
        } else if (source.getY() == destination.getY() && destination.getX() == source.getX() - 1) {
            return true;
        } else if (destination.getX() == source.getX() + 1 && destination.getY() == source.getY() + 1) {
            return true;
        } else if (destination.getX() == source.getX() + 1 && destination.getY() == source.getY() - 1) {
            return true;
        } else if (destination.getX() == source.getX() - 1 && destination.getY() == source.getY() + 1) {
            return true;
        } else return destination.getX() == source.getX() - 1 && destination.getY() == source.getY() - 1;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(kingImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);

        if (in == 1 ){
            g.setColor(Color.CYAN);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.drawImage(kingImage, 0, 0, getWidth() , getHeight(), this);
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }

    }
}
