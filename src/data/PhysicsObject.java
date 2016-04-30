package data;

public class PhysicsObject extends GameObject {

	protected int mass, Vx, Vy;
	protected Level level;
	
	public PhysicsObject(int width, int height, int x, int y, int theta,
			int mass, int Vx, int Vy, int texture, Level level) {
		super(width, height, x, y, theta, texture);
		this.mass = mass;
		this.Vx = Vx;
		this.Vy = Vy;
		this.level = level;
	}

	public PhysicsObject(int width, int height, int x, int y, int mass,
			int texture, Level level) {
		this(width, height, x, y, 0, mass, 0, 0, texture, level);
	}
	
	public int getMass() {
		return mass;
	}
	
	public int getVx() {
		return Vx;
	}
	
	public int getVy() {
		return Vy;
	}
	
	public void setMass(int mass) {
		this.mass = mass;
	}
	
	public void setVx(int Vx) {
		this.Vx = Vx;
	}
	
	public void setVy(int Vy) {
		this.Vy = Vy;
	}

	public void updatePosition() {
		xCollision();
		x += Vx;
		yCollision();
		y += Vy;
	}

	public void xCollision() {
		int startX, endX;
		int startY = y / 16;
		int endY = (y + height - 1) / 16;
		int collision;
		if (Vx > 0) { //Rightward motion
			startX = (x + width - 1) / 16;
			endX = (x + width + Vx - 1) / 16;
			collision = collisionTile(startX, endX, startY, endY, 1, false);
			if (collision <= endX) {
				Vx = collision * 16 - x - width;
			}
		} else if (Vx < 0) { //Leftward motion
			startX = x / 16;
			endX = (x + Vx) / 16;
			collision = collisionTile(startX, endX, startY, endY, -1, false);
			if (collision >= endX) {
				Vx = x - collision * 16 - 16;
			}
		}

	}

	public void yCollision() {
		int startY, endY;
		int startX = x / 16;
		int endX = (x + width - 1) / 16;
		int collision;
		if (Vy > 0) { //Downward motion
			startY = (y + height - 1) / 16;
			endY = (y + height + Vy - 1) / 16;
			collision = collisionTile(startY, endY, startX, endX, 1, true);
			//System.out.println(startY + ", " + endY);
			if (collision <= endY) {
				Vy = collision * 16 - y - height;
			}
		} else if (Vy < 0) { //Upward motion
			startY = y / 16;
			endY = (y + Vy) / 16;
			collision = collisionTile(startY, endY, startX, endX, -1, true);
			if (collision >= endY) {
				Vy = y - collision * 16 - 16;
			}
		}

	}

	private int collisionTile(int startCol, int endCol, int startRow, int endRow, int dir, boolean vert) {
		Tile tile;
		for (int xi = startCol; xi*dir <= endCol*dir; xi+=dir) {
			for (int yi = startRow; yi <= endRow; yi++) {
				if (vert) {
					tile = level.getTile(yi, xi);
				} else {
					tile = level.getTile(xi, yi);
				}
				if (tile != null && tile.isSolid()) {
					return xi;
				}
			}
		}
		return endCol + 1;
	}
}
