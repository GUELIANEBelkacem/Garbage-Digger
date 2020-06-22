package pb;

import java.awt.Color;
import java.awt.Graphics;

import pb.Game.ETAT;

public class HUD {
	public static int max_score=6;
	public static int score=0;
	private Game game;
	private Handler handler;
	public static int looser=1;
	public HUD(Game game, Handler handler) {
		this.game=game;
		this.handler=handler;
	}
	
	public void tick() {
		
		if(looser==0) {
			game.gameEtat = ETAT.End;
			
			handler.clearAll();
			handler.clearEnemy();
			handler.clearAll();
			handler.clearEnemy();
			looser=1;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawString(String.valueOf(score), 15, 15);

	    g.setColor(Color.white);
		g.drawRect(296, 2,40, 20);

		g.drawString("Menu",302,15);
		
	}

}
