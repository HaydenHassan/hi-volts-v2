import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Tile {
    public int x;
    public int y;
    public Color color;
    public Board board;

    public BufferedImage image;

    public boolean killOnTouch;
    public boolean hostile;

    public Tile(int x, int y, Color color, String filepath, boolean hostile) throws IOException {
        this.x = x;
        this.y = y;
        this.board = null;
        image = ImageIO.read(this.getClass().getResource("images/" + filepath));

        this.color = color;
        this.killOnTouch = killOnTouch;
        this.hostile = hostile;
    }

    public void relativeMove(int dx, int dy) {
        int movex = this.x + dx;
        int movey = this.y + dy;
        Tile cell = board.getBoard()[movex][movey];
        if (cell == null) {
            board.clearTile(this.x,this.y);
            this.x = movex;
            this.y = movey;
            board.setTile(this.x,this.y,this);
        }
        return;

    }

    public void addToBoard(Board board) {
        this.board = board;
        board.setTile(x,y,this);

    }
}
