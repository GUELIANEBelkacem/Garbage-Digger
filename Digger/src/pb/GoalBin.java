package pb;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import pb.Game.ETAT;

public class GoalBin extends GameObject {
	Handler handler;
	private BufferedImage image;
	private BufferedImage image2;
	private Game game;

	public GoalBin(int x, int y, ID id, Handler handler, BufferedImage image, BufferedImage image2, Game game) {
		super(x, y, id);
		this.handler=handler;
		this.game=game;
        this.image=image;
        this.image2=image2;
		SpriteSheet sprite = new SpriteSheet(image);
		image = sprite.grabImage(50);
		

	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
	}

	public void tick() {
        collision();
	}
	
	public void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			if(temp.id == ID.Player && ((HUD.score==6 && Spawn.lvlcount==1)||(HUD.score==12 && Spawn.lvlcount==2)||(HUD.score==18 && Spawn.lvlcount==3))) {
				if(getBounds().intersects(temp.getBounds())) {
					
					AudioPlayer.getSound("victory").play();
					game.gameEtat=ETAT.Win;
					handler.clearAll();
					handler.clearEnemy();
					handler.clearAll();
					handler.clearEnemy();
				}
				
			}
		}
	}

	public void render(Graphics g) {
		if((HUD.score==6 && Spawn.lvlcount==1)||(HUD.score==12 && Spawn.lvlcount==2)||(HUD.score==18 && Spawn.lvlcount==3) )g.drawImage(image2, x, y, null);
		else g.drawImage(image, x, y, null);
	}
	


}
