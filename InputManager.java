import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class InputManager {
    private Board board;

    public InputManager(Board inputBoard) {
        this.board = inputBoard;
    }

    public void keyPressed(KeyEvent key) {
        try {
            if (board.ingame) {
                if (board.getInputManager() != null) {


                    if (key.getKeyCode() == KeyEvent.VK_W) board.getPlayer().move(0, -1);
                    if (key.getKeyCode() == KeyEvent.VK_S) board.getPlayer().move(0, 1);
                    if (key.getKeyCode() == KeyEvent.VK_D) board.getPlayer().move(1, 0);
                    if (key.getKeyCode() == KeyEvent.VK_A) board.getPlayer().move(-1, 0);

                    if (key.getKeyCode() == KeyEvent.VK_SPACE) board.getPlayer().move(0, 0);
                    if (key.getKeyCode() == KeyEvent.VK_J) board.getPlayer().jump();

                }
            }
            if (key.getKeyCode() == KeyEvent.VK_R) board.restartGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void keyReleased(KeyEvent key) { ; }
    public void keyTyped(KeyEvent key) { ; }

    public void mouseClicked(MouseEvent arg0) { ; }
    public void mouseExited(MouseEvent arg0) { ; }
    public void mouseEntered(MouseEvent arg0) { ; }
    public void mouseReleased(MouseEvent arg0) { ; }

    public void mousePressed(MouseEvent arg0) {
        Point p = arg0.getPoint();
        try {
            board.restart.click(p.x,p.y);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
