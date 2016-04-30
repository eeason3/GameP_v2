package helpers;

import org.newdawn.slick.opengl.Texture;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import static helpers.Artist.QuickLoad;

public class TextureHandler extends DefaultHandler {
	
	private Texture[] texturePack;
	private String[] data;
	private int index;
	private int count = 0;
	
	public TextureHandler() {
		data = new String[4];
	}

	@Override
	public void startElement(String uri, 
			   String localName, String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase("id")) {
			index = 0;
		} else if (qName.equalsIgnoreCase("name")) {
			index = 1;
		} else if (qName.equalsIgnoreCase("width")) {
			index = 2;
		} else if (qName.equalsIgnoreCase("height")) {
			index = 3;
		} else if (qName.equalsIgnoreCase("texturepack")) {
			String size = attributes.getValue("size");
			texturePack = new Texture[Integer.parseInt(size)];
			index = -1;
		} else {
			index = -1;
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		if (index != -1) {
			data[index] = new String(ch, start, length);
			index = -1;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase("texture")) {
			int i = Integer.parseInt(data[0]);
			texturePack[i] = QuickLoad(data[1]);
		}
	}
	
	public Texture[] getTextures() {
		return texturePack;
	}
}
