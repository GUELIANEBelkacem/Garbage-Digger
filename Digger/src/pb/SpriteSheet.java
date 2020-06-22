package pb;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage ss) {
		this.sheet=ss;
	}
	
	public BufferedImage grabImage(int w) {
		BufferedImage img = sheet.getSubimage(0, 0, w, w);
		return img;
	}
	                                           

}
