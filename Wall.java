import java.awt.*;
import java.io.IOException;

public class Wall extends Tile {
    public Wall(int x, int y) throws IOException {
        super(x, y, Color.black, "wall.png", false);
    }
}
