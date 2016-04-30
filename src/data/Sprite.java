package data;

import static helpers.Artist.DrawTexture;
import static helpers.Artist.texturePack;

public class Sprite {
	
	protected int width, height;
	protected int texture;
	
	public Sprite(int width, int height, int texture) {
		this.width = width;
		this.height = height;
		this.texture = texture;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void Draw(int x, int y) {
		DrawTexture(texturePack[texture], x, y, width, height);
	}
}
