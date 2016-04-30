package data;

import java.util.HashMap;

public class Level {
	
	private final static int DEFAULT_WIDTH = 200;
	private final static int DEFAULT_HEIGHT = 50;
	
	private int width, height;
	private HashMap<Integer, Tile> background;
	private HashMap<Integer, Tile> midground;
	private HashMap<Integer, Tile> foreground;
	private Tile voidTile;
	
	public Level() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		voidTile = new Tile(0, true, true);
		GenerateMap();
	}
	
	public void GenerateMap(){
		int max = 2;
		width = 100;
		height = 30;
		midground = new HashMap<Integer, Tile>(width * height);
		for (int Tx = 0; Tx < width; Tx++){
			double r;
			midground.put(Tx + width * (height - 1), new Tile(1, true, true));
			for(int Ty = 2; Ty < max; Ty++){
				midground.put(Tx + width * (height - Ty), new Tile(1, true, true));
			}
			r = Math.random();
			if (r >= 0.75) {
				midground.put(Tx + width * (height - max), new Tile(1, true, true));
				if(max < 10)
					max++;
			}else if (r <= 0.25){
				if(max > 2)
					max--;
			}
		}
	}
	
	public void Draw(int w, int h, int xOff, int yOff) {
		for (int y = 0; y < h / 16 + 1; y++) {
			for (int x = 0; x < w / 16 + 2; x++) {
				int xAdj = x - xOff / 16;
				int yAdj = y - yOff / 16;
				int i = xAdj + yAdj * width;
				if (xAdj < 0 || xAdj >= width || yAdj < 0 || yAdj >= height) {
					voidTile.Draw(x * 16 + xOff % 16, y * 16 + yOff % 16);
				} else if (getTile(i) != null) {
					getTile(i).Draw(x * 16 + xOff % 16, y * 16 + yOff % 16);
				}
			}
		}
	}
	
	public Tile getTile(int i) {
		return midground.get(i);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return voidTile;
		} else {
			return midground.get(y * width + x);
		}
	}
}
