import java.awt.*;
import java.io.IOException;

public class Enemy extends Tile {
    public boolean move = false;
    public Enemy(int x, int y) throws IOException {
        super(x, y, Color.red, "enemy.png", true);
    }
    public void die() throws IOException {
        board.clearTile(this.x,this.y);
        board.enemies--;
        if (board.enemies <= 0 && board.getPlayer().health > 0) {
            board.restart.win_image();
            board.ingame = false;
            board.restart.show();
        }
    }
    public void moveToPlayer() throws IOException {
        int playerx = board.getPlayer().x;
        int playery = board.getPlayer().y;

        int dx = playerx - this.x;
        int dy = playery - this.y;

        int abs_dx = Math.abs(dx);
        int abs_dy = Math.abs(dy);




        if (dx > 0) {
            dx = 1;
        } else if (dx < 0) {
            dx = -1;
        }
        if (dy > 0) {
            dy = 1;
        } else if (dy < 0) {
            dy = -1;
        }

        if (abs_dx > abs_dy) {
            dy = 0;
        } else if (abs_dx < abs_dx) {
            dx = 0;
        }
        enemyMove(dx,dy);


    }
    public void enemyMove(int dx, int dy) throws IOException {
        if (move) {
            return;
        }
        int targetx = this.x + dx;
        int targety = this.y + dy;

        Tile tile = board.getBoard()[targetx][targety];
        if (tile instanceof Wall) {
            die();
        }
        if (tile instanceof Player) {
            board.getPlayer().playerDamage();
            die();
        }
        if (tile == null) {
            move = true;
            relativeMove(dx,dy);

            return;
        }

    }
}
