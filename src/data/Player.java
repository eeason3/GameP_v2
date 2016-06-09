package data;

import org.lwjgl.input.Keyboard;

/**
 * Created by Zachary on 5/26/2016.
 */
public class Player extends PhysicsObject {

    protected int spd = 1;
    protected double friction = 0.2;

    public Player(int width, int height, int x, int y, int mass, int texture, Level level) {
        super(width, height, x, y, mass, texture, level);
    }

    public Player(int x, int y, Level level) {
        this(32, 64, x, y, 80, 10, level);
    }

    public void getInput() {
        if(isGrounded()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                Vy = -10;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                Vx -= spd;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                Vx += spd;
            }
            if (Vx > 0 ) {
                Vx -= friction * Vx;
                System.out.println((int)Vx);
            } else if (Vx < 0) {
                Vx -= friction * Vx;
                System.out.println((int)Vx);
            }
        }
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
        if (Vx < 0) {
            setFlip(true);
        } else if (Vx > 0){
            setFlip(false);
        }
    }
}
