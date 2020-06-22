package pb;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DirtyFly extends Antagonist {
	private Handler handler;
	private Player player;
	private BufferedImage image;
	private int vit;

	public DirtyFly(int x, int y, ID id, Handler handler, Player player, BufferedImage image) {
		super(x, y, id);
		this.handler=handler;
		this.player=player;
		this.image=image;
		SpriteSheet sprite = new SpriteSheet(image);
		image = sprite.grabImage(40);
		vit=Spawn.lvlcount+1;
		

	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}

	public void tick() {
		strategy();
		x+=vitX;
		y+=vitY;
        collision();
	}
	
	public void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			if(temp.id == ID.Player) {
				if(getBounds().intersects(temp.getBounds())) {
                         //code
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	

	
	public void strategy() {
		
		vitX=(int)((player.getX()-x)/distance(x,y,player.getX(),player.getY())*vit);
		vitY=(int)((player.getY()-y)/distance(x,y,player.getX(),player.getY())*vit);
		
	}
	



}
