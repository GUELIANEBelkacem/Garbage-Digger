package pb;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Garbage extends GameObject {
	Handler handler;
	private BufferedImage image;

	public Garbage(int x, int y, ID id, Handler handler, BufferedImage image) {
		super(x, y, id);
        this.image=image;
		this.handler=handler;
		SpriteSheet sprite = new SpriteSheet(image);
		image = sprite.grabImage(40);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}

	public void tick() {
        collision();
	}
	
	public void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			if(temp.id == ID.Player) {
				if(getBounds().intersects(temp.getBounds())) {
					HUD.score++;
					handler.removeObject(this);
					AudioPlayer.getSound("coin").play();
				}
				
			}
		}
	}

	public void render(Graphics g) {

		g.drawImage(image, x, y, null);
	}
	


}
