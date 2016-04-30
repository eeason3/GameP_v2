package data;

public class Tile extends Sprite{
	
	private boolean solid, visible;
	
	public Tile(int texture, boolean solid,	boolean visible) {
		super(16, 16, texture);
		this.solid = solid;
		this.visible = visible;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isVisible() {
		return visible;
	}
}
