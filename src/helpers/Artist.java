package helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	
	public static final int WIDTH = 900, HEIGHT = 600;
	public static int XOFFSET = 0, YOFFSET = 0;
	public static Texture background;
	public static Texture[] texturePack;

	/**
	 * Instantiate display
	 */
	public static void BeginSession(){
		Display.setTitle("Game");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	/**
	 * Draw quad to display
	 *
	 * @param x X position to draw to
	 * @param y Y position to draw to
	 * @param width Width of quad
     * @param height Height of quad
     */
	public static void DrawQuad(int x, int y, int width, int height){
		glBegin(GL_QUADS);
		glVertex2f(x,y);
		glVertex2f(x+width, y);
		glVertex2f(x+width, y+height);
		glVertex2f(x, y+height);
		glEnd();
	}

	/**
	 * Draw line between two points
	 *
	 * @param init_x X position of first point
	 * @param init_y Y position of first point
	 * @param fin_x X position of second point
     * @param fin_y Y position of second point
     */
	public static void DrawLine(int init_x, int init_y, int fin_x, int fin_y){
		glBegin(GL_LINES);
		glVertex2f(init_x, init_y);
		glVertex2f(fin_x, fin_y);
		glEnd();
	}

	/**
	 * Draw a quad with a texture ot the screen
	 *
	 * @param texture Texture of quad
	 * @param x X position
	 * @param y Y position
	 * @param width Width
     * @param height Height
     */
	public static void DrawTexture(Texture texture, int x, int y, int width, int height){
		texture.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0 ,0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}

	/**
	 * Load texture from an image file
	 *
	 * @param path Path to file
	 * @param fileType Extension of file
     * @return Texture object containing the image
     */
	public static Texture LoadTexture(String path, String fileType){
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}

	/**
	 * Loads PNG image from res folder to texture
	 *
	 * @param Name Name of file in res folder
	 * @return Texture object containing loaded image
     */
	public static Texture QuickLoad(String Name){
		return LoadTexture("src/res/Textures/"+Name+".png", "PNG");
	}

	/**
	 * Loads all textures in XML file
	 *
	 * @param file XML file containing texture information
     */
	public static void LoadTexturePack(String file) {
		try {
			TextureHandler loader = new TextureHandler();
			File xmlFile = new File("src/res/data/"+file+".xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlFile, loader);
			texturePack = loader.getTextures();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
