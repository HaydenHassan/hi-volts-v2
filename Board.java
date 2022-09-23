import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private Main main;
    private Tile[][] board;
    private Player player;
    private List<Enemy> enemy_list;

    public int undoCounter;


    public int win_width;
    public int win_height;

    public int rows;
    public int cols;
    public int cellSize;

    public int enemies;
    public boolean ingame;

    public Button restart;
    private InputManager inputManager;

    public Board(int win_width, int win_height, int cellSize, Main main){
        this.win_width = win_width;
        this.win_height = win_height;
        this.cellSize = cellSize;
        this.inputManager = new InputManager(this);
        this.enemy_list = new ArrayList<Enemy>();
        this.main = main;
    }

    public void addTile(Tile tile){
        tile.addToBoard(this);
    }

    public void clearTile(int x, int y) {
        board[x][y] = null;
    }
    public void setTile(int x, int y, Tile tile) {
        board[x][y] = tile;
    }

    public static int random(int min, int max) {
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }

    public Tile[][] getBoard(){
        return board;
    }
    public Player getPlayer(){
        return player;
    }
    public InputManager getInputManager() { return inputManager; }
    public List<Enemy> getEnemy_list() { return enemy_list; }
    public Main getMain() { return main; }

    public Tuple randomLocation(int minx, int miny, int maxx, int maxy) {
        int randomx;
        int randomy;
        while (true) {
            randomx = random(minx, maxx);
            randomy = random(miny, maxy);
            if (board[randomx][randomy] == null) {
                break;
            }
        }
        return new Tuple(randomx,randomy);
    }
    public void restartGame() throws IOException {
        genBoard(win_width,win_height,cellSize, Board.random(9,30), Board.random(15,30));
        restart.hide();
    }



    public void genBoard(int width, int height, int cellSize, int enemies, int walls) throws IOException {
        int rows = width/cellSize;
        int cols = height/cellSize;

        this.win_width = width;
        this.win_height = height;

        this.board = new Tile[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.enemies = enemies;
        this.ingame = true;
        this.enemy_list = new ArrayList<Enemy>();


        this.restart = new Button(width,height,250,100, this);


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i >= (rows-1) || j >= (cols-1) ) {
                    addTile(new Wall(i,j));
                    //System.out.println(String.valueOf(rows) + " " + String.valueOf(cols));
                }
            }
        }

        int min = 0+1;
        int maxx = rows-1;
        int maxy = cols-1;

        Tuple tuple = null;

        // place walls
        for (int i = 0; i < walls; i++) {
            tuple = randomLocation(min,min,maxx,maxy);
            addTile(new Wall(tuple.value1,tuple.value2));
        }
        // place enemies

        Enemy enemy;
        for (int j = 0; j < enemies; j++) {
            tuple = randomLocation(min,min,maxx,maxy);
            enemy = new Enemy(tuple.value1,tuple.value2);
            addTile(enemy);
            enemy_list.add(enemy);
        }
        // place player
        boolean done = false;
        int[][] adjacent_list = {{1,1}, {1,0}, {1,-1},{0,1}, {0,-1}, {-1,1}, {-1,0}, {-1,-1}};
        int adjx;
        int adjy;

        while (!done) {
            tuple = randomLocation(min,min,maxx,maxy);

            done = true;
            for (int i = 0; i < adjacent_list.length; i++) {
                adjx = tuple.value1 + adjacent_list[i][0];
                adjy = tuple.value2 + adjacent_list[i][1];
                if (board[adjx][adjy] == null) {
                } else {
                    if (board[adjx][adjy].hostile) {
                        done = false;
                    }
                }

            }

        }
        this.player = new Player(tuple.value1,tuple.value2);
        addTile(player);
        player.health = (int) (enemies / 3);
    }
}
