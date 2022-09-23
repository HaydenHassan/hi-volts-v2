import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Button {
    public int x;
    public int y;

    public Board board;

    public int width;
    public int height;

    public boolean visable;
    public BufferedImage image;
    public BufferedImage win_image;
    public BufferedImage restart_image;

    public Button(int win_width, int win_height, int width, int height, Board board) throws IOException {
        this.x = (win_width / 2) - (width / 2);
        this.y = (win_height / 5);

        this.board = board;
        this.width = width;
        this.height = height;

        this.win_image = ImageIO.read(this.getClass().getResource("images/" + "win.png"));
        this.restart_image = ImageIO.read(this.getClass().getResource("images/" + "restart.png"));
        this.image = restart_image;
    }
    public void click(int clickx, int clicky) throws IOException {
        if (clickx > x && clickx < x + width) {
            if (clicky > y && clicky < y + height) {
                if (visable) {
                    board.restartGame();
                    hide();
                }
            }
        }
    }
    public void show(){
        visable = true;
    }
    public void hide(){
        visable = false;
    }
    public void win_image(){
        image = win_image;
    }
    public void restart_image(){
        image = restart_image;
    }
}
