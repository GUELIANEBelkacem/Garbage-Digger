package pb;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
	Handler handler;
	private boolean col=true;
	private BufferedImage image;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler=handler;
		SpriteSheet sprite = new SpriteSheet(Game.playerimg);
		image = sprite.grabImage(40);
		

	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}

	public void tick() {
		x+=vitX;
		y+=vitY;
        x = Game.clamp(x, 0, Game.width-66);
        y = Game.clamp(y, 0, Game.height-70);
        collision();
	}
	
	public void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			if(temp.id == ID.Enemy) {
				if(getBounds().intersects(temp.getBounds()) && col==true) {
                      HUD.looser=0;
                      AudioPlayer.stopMusic("music");
                      AudioPlayer.getSound("lost").play();
				
			}
		}
	}
	}

	public void render(Graphics g) {
		
		//exemple de code 
		//Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.red);
		//g2d.draw(getBounds());
		//g.setColor(Color.green);
		//g.fillRect(x, y, 30, 30);
		g.drawImage(image, x, y, null);
	}
	


}
