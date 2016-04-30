package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.WIDTH;
import static helpers.Artist.XOFFSET;
import static helpers.Artist.YOFFSET;


public class GameObject extends Sprite{
	
	protected int x, y, theta;

	public GameObject(int width, int height, int x, int y, int theta, int texture) {
		super(width, height, texture);
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	
	public GameObject(int width, int height, int x, int y, int texture) {
		this(width, height, x, y, 0, texture);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getRotation() {
		return theta;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setRotation(int theta) {
		this.theta = theta;
	}
	
	public void Draw() {
		if (OnScreen()) {
			Draw(x + XOFFSET, y + YOFFSET);
		}
	}
	
	public boolean OnScreen() {
		return x + width + XOFFSET > 0 && x + XOFFSET < WIDTH
				&& y + height + YOFFSET > 0 && y + YOFFSET < HEIGHT;
	}
}
