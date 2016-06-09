package data;

import static data.Level.getMapHeight;
import static data.Level.getMapWidth;

public class Tile extends Sprite{
	
	private boolean solid, visible;
	private int index;
	
	public Tile(int index, int texture, boolean solid,	boolean visible) {
		super(16, 16, texture);
		this.solid = solid;
		this.visible = visible;
		this.index = index;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isVisible() {
		return visible;
	}

	public int getIndex() {
		return index;
	}

	public void setInex(int index) {
		this.index = index;
	}

	public int getX() {
		return index % getMapWidth();
	}

	public int getY() {
		return index / getMapWidth();
	}
}
