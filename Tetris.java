import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JFrame{
	
	private int gameTime;
	private int bTime; // block time
	private int bDur; // block duration
	private int blockSize;
	
	private final int ROW = 18;
	private final int COL = 8;
	
	private int[][] iBoard = {
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
			};
	private Tetris.BTYPE[][] board = new Tetris.BTYPE[19][10];
		
	
	private Tetris.BTYPE[][] tmpBoard = new Tetris.BTYPE[19][10];
	
	int[][][] coordsTable = new int[][][]{
	        {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
	        {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
	        {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
	        {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
	        {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
	        {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
	        {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
	        {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
	};
	
	int[][] rotateP = new int[][] {
		{0, -1},
		{1, 0}
	};
	
	public enum BTYPE{
		VOID,
		BRICK,
		MOVE,
		WALL
	}
	
	public Tetris(){
		this.gameTime = 0;
		this.bTime = 0;
		this.bDur = 1000;
		this.blockSize = 20;
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TetrisGame game = new TetrisGame();
		setContentPane(game);
	}
	
	public int[][] dotProduct(int[][] x, int[][] p){
		int[][] result = new int[2][2];
		int n = x.length;
		int m = x[0].length;
		int m2 = p.length;
		int r = p[0].length;
		if(m!=m2) throw new RuntimeException("Dot product Error: array must have same length");
		
		return result;
	}
	class TetrisGame extends JPanel{
		
		public TetrisGame() {
			for(int i = 0 ; i < ROW + 1; i++) 
				for(int j = 0 ; j < COL + 2 ; j++){
					board[i][j] = iBoard[i][j] == 1 ? BTYPE.WALL : BTYPE.VOID;
					tmpBoard[i][j] = BTYPE.VOID;
				}
		}

		@Override
		public void paintComponent(Graphics g) {
			
			for(int y = 0 ; y < ROW + 1; y++) {
				for(int x = 0 ; x < COL + 2 ; x++) {
					if(tmpBoard[y][x] != board[y][x]) {
						switch(board[y][x]) {
						case VOID:
							g.setColor(getBackground());
							g.drawRect(x*blockSize, y*blockSize, blockSize, blockSize);
							break;
						case BRICK:
							g.setColor(Color.gray);
							g.fillRect(x*blockSize+1, y*blockSize+1, blockSize-2, blockSize-2);
							break;
						case MOVE:
							g.setColor(Color.gray);
							g.drawRect(x*blockSize, y*blockSize, blockSize, blockSize);
							break;
						case WALL:
							g.setColor(Color.black);
							g.fillRect(x*blockSize, y*blockSize, blockSize, blockSize);
						}
					}
				}
			}
		}
		
		public void drawBlock(int x, int y, Graphics g) {
			
			g.drawRect(x, y, blockSize, blockSize);
			this.update(g);
		}
	}
	
	static public void main(String[] args) {
		new Tetris();
		return;
	}
}
