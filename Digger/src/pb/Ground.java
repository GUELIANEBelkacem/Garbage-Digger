package pb;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ground extends GameObject {
		Handler handler;
		private BufferedImage image;

		public Ground(int x, int y, ID id, Handler handler) {
			super(x, y, id);
			this.handler=handler;
			SpriteSheet sprite = new SpriteSheet(Game.ground);
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
				if(temp.id == ID.Player) {
					if(getBounds().intersects(temp.getBounds())) {
						handler.removeObject(this);
						int pox = (int)((x-(x%50))/50);
						int poy = (int)((y-(y%50))/50);
						Spawn.lvl[poy][pox] = 0;
						Enemy.map[poy+1][pox+1] = 0;
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



