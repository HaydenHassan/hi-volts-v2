import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private Board board;
    private Font font;

    public DrawPanel(Board board, Font font) {
        super.setFocusable(true);
        this.addMouseListener((MouseListener) this);
        this.addKeyListener( (KeyListener) this);

        this.board = board;
        this.font = font;
    }

    @Override
    public void keyPressed(KeyEvent key) {board.getInputManager().keyPressed(key);}
    @Override
    public void keyReleased(KeyEvent key) {board.getInputManager().keyReleased(key);}
    @Override
    public void keyTyped(KeyEvent key) {board.getInputManager().keyTyped(key);}

    @Override
    public void mouseClicked(MouseEvent arg0) {board.getInputManager().mouseClicked(arg0);}
    @Override
    public void mouseExited(MouseEvent arg0) {board.getInputManager().mouseExited(arg0);}
    @Override
    public void mouseEntered(MouseEvent arg0) {board.getInputManager().mouseEntered(arg0);}
    @Override
    public void mouseReleased(MouseEvent arg0) {board.getInputManager().mouseReleased(arg0);}
    @Override
    public void mousePressed(MouseEvent arg0) {board.getInputManager().mousePressed(arg0);}


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScreen(g);
    }

    public void drawScreen(Graphics g) {
        g.setFont(font);
        // clear screen
        g.setColor(Color.WHITE);
        if (board.restart != null) {
            if (board.restart.visable) {
                if (board.getPlayer().health > 0) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.GRAY);
                }
            }
        }
        g.fillRect(0, 0, board.win_width, board.win_height);

        Tile[][] tempboard = board.getBoard();


        for (int i = 0; i < tempboard.length; i++) {
            for (int j = 0; j < tempboard[i].length; j++) {
                if (tempboard[i][j] == null){
                    g.setColor(Color.GRAY);
                    g.drawRect(i*board.cellSize, j*board.cellSize, board.cellSize, board.cellSize);
                } else {
                    //g.setColor(tempboard[i][j].color);
                    g.drawImage(tempboard[i][j].image, i*board.cellSize, j*board.cellSize, this); // see javadoc for more info on the parameters
                    //g.fillRect(i*board.cellSize, j*board.cellSize, board.cellSize, board.cellSize);
                }
            }
        }
        if (board.restart != null) {
            if (board.restart.visable) {
                g.drawImage(board.restart.image, board.restart.x, board.restart.y, this);
            }
        }
        if (board.getPlayer() != null) {
            g.setColor(Color.GREEN);
            g.drawString("Lives: " + String.valueOf(board.getPlayer().health),5,42);
        }

    }

    public void update() {
        this.repaint();
    }
}
