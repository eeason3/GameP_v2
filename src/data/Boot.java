package data;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;
import static helpers.Clock.*;

public class Boot {
	
	private int lastFPS;
	private int fps = 0;
	private Level level;
	private Player player;
	
	public Boot(){
		
		BeginSession();
		lastFPS = (int) getTime();
		LoadTexturePack("Textures");
		level = new Level();
		player = new Player(32, 64, level);
		player.setVx(1);

		
		while(!Display.isCloseRequested()){

			player.getInput();
			player.updatePosition();
            ScreenOffset();
			render();
			
			updateFPS();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		level.Draw(WIDTH, HEIGHT, XOFFSET, YOFFSET);
		player.Draw();
	}
	
	public void keyInput(){
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			XOFFSET--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			XOFFSET++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			YOFFSET--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			YOFFSET++;
		}
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle("Game: " + fps); 
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}

	private void ScreenOffset() {
		XOFFSET = (XOFFSET - player.getX() - player.getWidth()/2 + WIDTH/2)/2;
        YOFFSET = (YOFFSET - player.getY() - player.getHeight()/2 + HEIGHT/2)/2;
	}

	
	public static void main(String[] args) throws IOException{
		new Boot();
	}
	
}
