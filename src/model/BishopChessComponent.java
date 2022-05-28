package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class BishopChessComponent extends ChessComponent {
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;
    private Image BishopImage;
    private int in = 0 ;




    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }







    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                BishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                BishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
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

        if (Math.abs(source.getX() - destination.getX()) != Math.abs(source.getY() - destination.getY())){
            return false;
        }else if (destination.getY()-source.getY() >0&& destination.getX() - source.getX()>0){
            int row = source.getX()+1;
            for (int col = source.getY()+1; col <destination.getY() ; col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
                row++;
            }
            return true;
        }else if (destination.getY()-source.getY()>0&& destination.getX() - source.getX()<0){
            int row = source.getX()-1;
            for (int col = source.getY()+1; col <destination.getY() ; col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
                row--;
            }
            return true;
        }else if (destination.getY()-source.getY()<0 && destination.getX() - source.getX() <0){
            int row = source.getX()-1;
            for (int col = source.getY()-1; col >destination.getY() ; col--) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
                row--;
            }
            return true;
        }else if (destination.getX() - source.getX()>0 && destination.getY() - source.getY()<0){
            int row = source.getX()+1;
            for (int col = source.getY()-1; col >destination.getY() ; col--) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
                row++;
            }
            return true;
        }else {
            return false;
        }

    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(BishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);

        if (in == 1 ){
            g.setColor(Color.CYAN);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.drawImage(BishopImage, 0, 0, getWidth() , getHeight(), this);
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }



}
