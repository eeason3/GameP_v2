package data;

import static helpers.Artist.DrawTexture;
import static helpers.Artist.texturePack;

public class Sprite {
	
	protected int width, height;
	protected int texture;
	protected boolean flip;

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

    /**
     * Flips the direction the texture is facing over the y-axis
     *
     * @param flip Set true if the image is flipped
     */
	public void setFlip(boolean flip) {
		this.flip = flip;
	}

    /**
     * Draws sprite's texture to the display
     *
     * @param x X position
     * @param y Y position
     */
	public void Draw(int x, int y) {
		if (flip) {
			DrawTexture(texturePack[texture], x + width, y, -width, height);
		} else {
			DrawTexture(texturePack[texture], x, y, width, height);
		}
	}
}
