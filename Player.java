import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Tile {
    public int health = 3;

    public BufferedImage robo_right = ImageIO.read(this.getClass().getResource("images/robot_right_transparent.png"));
    public BufferedImage robo_left = ImageIO.read(this.getClass().getResource("images/robot_left_transparent.png"));

    public Player(int x, int y) throws IOException {
        super(x, y, Color.yellow, "robot_right_transparent.png", false);
    }

    public void jump() throws IOException {
        Tuple tuple = board.randomLocation(1,1,board.rows-1,board.cols-1);
        int x = tuple.value1-this.x;
        int y = tuple.value2-this.y;
        move(x,y);
    }
    public void move(int x, int y) throws IOException {
        if (x > 0) {
            this.image = robo_right;
        } else if (x < 0) {
            this.image = robo_left;
        }
        relativeMove(x,y);
        for (int i = 0; i < board.rows; i++) {
            for (int j = 0; j < board.cols; j++) {
                if (board.getBoard()[i][j] instanceof Enemy) {
                    ((Enemy) board.getBoard()[i][j]).moveToPlayer();
                }
            }
        }
        for (int i = 0; i < board.rows; i++) {
            for (int j = 0; j < board.cols; j++) {
                if (board.getBoard()[i][j] instanceof Enemy) {
                    ((Enemy) board.getBoard()[i][j]).move = false;
                }
            }
        }


    }
    public void playerDamage() throws IOException {
        if (this.health > 0) {
            this.health--;
        }
        if (this.health <= 0) {
            System.out.println("You died!");
            board.restart.restart_image();
            board.ingame = false;
            board.restart.show();
        }
    }
}
