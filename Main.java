import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends JFrame {
    // game variables
    public int cellSize;
    public int rows;
    public int cols;

    public Board board;
    public List<Board> undoBoard;

    public boolean ingame;

    //window variables
    public int win_width;
    public int win_height;

    public DrawPanel drawPanel;

    public Font font;
    public int fps;


    public Main(int win_width, int win_height, int cellSize) {
        super("Hi Volts");
        this.win_width = win_width;
        this.win_height = win_height;
        this.cellSize = cellSize;
        this.board = new Board(win_width,win_height,cellSize,this);
        this.font = new Font("TimesRoman",Font.BOLD,45);
        this.fps = 100;

        drawPanel = new DrawPanel(board, font);

        setContentPane(drawPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(win_width , win_height + cellSize + 36);
        setVisible(true);

        this.undoBoard = new ArrayList<Board>();

    }

    public static int random(int min, int max) {
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }
    // GAME LOGIC AND STUFF

    public static void main(String args[]) throws InterruptedException, IOException {
        Main f = new Main(1000,1000, 50);
        f.game();
    }


    public void game() throws InterruptedException, IOException {

        board.restartGame();
        while (true) {
            Thread.sleep((int) (1000/fps));
            drawPanel.update();
        }
    }
}
