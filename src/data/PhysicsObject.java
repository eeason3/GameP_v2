package data;

public class PhysicsObject extends GameObject {

	protected int mass;
    protected double Vx, Vy;
	protected Level level;
    protected double gravity = 0.5;
	
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
	
	public double getVx() {
		return Vx;
	}
	
	public double getVy() {
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

	/**
	 * Checks for collisions and updates object position
	 */
	public void updatePosition() {
        gravity();
		xCollision();
		x += (int)Vx;
		yCollision();
		y += (int)Vy;
	}

    /**
     * Checks for collisions along x axis and adjusts velocity
     */
	public void xCollision() {
		int startY = y / 16;
		int endY = (y + height - 1) / 16;
		Tile tile;
		if (Vx > 0) { //Rightward motion
            int[] corners = {(x + width - 1) / 16, (int)(x + width + Vx - 1) / 16,
                    startY, endY};
			tile = level.getSolid(corners, 1, true);
			if (tile != null) {
                if (level.getTile(tile.getX(), tile.getY()-1) == null) {
                    y += tile.getY() * 16 - (y + height);
                } else {
                    x += tile.getX() * 16 - x - width;
                    Vx = 0;
                }
			}
		} else if (Vx < 0) { //Leftward motion
			int[] corners = {(x / 16) + 1, (int)(x + Vx) / 16,	startY, endY};
			tile = level.getSolid(corners, -1, true);
			if (tile != null) {
                if (level.getTile(tile.getX(), tile.getY()-1) == null) {
                    y += tile.getY() * 16 - (y + height);
                } else {
                    x += tile.getX() * 16 + 16 - x;
                    Vx = 0;
                }
			}
		}

	}

    /**
     * Checks for collisions along y axis and adjusts velocity
     */
	public void yCollision() {
		int startX = (x + 1) / 16;
        int endX = (x + width - 1) / 16;
        Tile tile;
		if (Vy > 0) { //Downward motion
            int[] corners = {(y + height - 1) / 16, (int)(y + height + Vy - 1) / 16,
                    startX, endX};
            tile = level.getSolid(corners, 1, false);
            if (tile != null) {
                y += tile.getY() * 16 -  y - height;
                Vy = 0;
            }
		} else if (Vy < 0) { //Upward motion
            int[] corners = {y / 16, (int)(y + Vy) / 16,	startX, endX};
            tile = level.getSolid(corners, -1, false);
            if (tile != null) {
                y += tile.getY() * 16 - y;
                Vy =0;
            }
		}

	}

    /**
     * Checks for solid tiles in a given range of the tile grid
     *
     * @param startCol First column of tiles to check
     * @param endCol Last column of tiles
     * @param startRow First row of tiles
     * @param endRow Last row of tiles
     * @param dir Indicates if direction is positive or negative
     * @param vert Indicates if direction is vertical or horizontal
     * @return First solid tile encountered
     */
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
		return endCol + dir;
	}

    /**
     * Checks if the object is in contact with the ground
     *
     * @return True if object is in contact with ground
     */
    public boolean isGrounded() {
        int[] corners = {(x + 1) / 16,(x + width - 1) / 16,
                (y + height + 1) / 16, (y + height + 1) / 16};
        return level.getSolid(corners, 1, true) != null;
    }

    /**
     * Adds acceleration due to gravity to object's velocity
     */
    protected void gravity() {
        if (!isGrounded()) {
            Vy += gravity;
        } else if (Vy > 0) {
            Vy = 0;
        }
    }
}
