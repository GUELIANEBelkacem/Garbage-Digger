package pb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import pb.Game.ETAT;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	//private HUD hud;
	Random r = new Random();
	
	public Menu(Game game, Handler handler,HUD hud) {
		this.game=game;
		this.handler=handler;
		//this.hud=hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
		if(game.gameEtat == ETAT.Menu) {
			if(mouseOver(mx,my,207, 110, 226, 64)) {
				HUD.score = 0;
				Spawn.lvlcount=1;
				game.gameEtat = ETAT.Play;
				game.handlerUpdate();
				AudioPlayer.getMusic("music").loop();
			}
			if(mouseOver(mx,my,207, 350, 226, 64)) System.exit(1);
			if(mouseOver(mx,my,207, 230, 226, 64)) { 
				game.gameEtat = ETAT.Help;
				AudioPlayer.stopMusic("music");
			}
		}
		
			
		if(game.gameEtat == ETAT.Help) {
				if(mouseOver(mx,my,207, 350, 226, 64)) game.gameEtat = ETAT.Menu;;
			}
		
				
		if(game.gameEtat == ETAT.End) {
					if(mouseOver(mx,my,207, 350, 226, 64)) { 
		                if(Spawn.lvlcount==1) HUD.score=0;
		                if(Spawn.lvlcount==2) HUD.score=6;
		                if(Spawn.lvlcount==3) HUD.score=12;
						game.gameEtat = ETAT.Play;
						game.handlerUpdate();
						AudioPlayer.getMusic("music").loop();
					}
					if(mouseOver(mx,my,207, 230, 226, 64)) {
						game.gameEtat = ETAT.Menu;
						AudioPlayer.stopMusic("music");
					
					}
				}
		if(game.gameEtat == ETAT.Win) {
            if(Spawn.lvlcount!=3) {
			if(mouseOver(mx,my,207, 350, 226, 64)) { 
				Spawn.lvlcount++;
				Spawn.lvlcount=Game.clamp(Spawn.lvlcount, 1, 3);
				game.gameEtat = ETAT.Play;
			
				game.handlerUpdate();
			}}
			if(mouseOver(mx,my,207, 230, 226, 64)) {
				game.gameEtat = ETAT.Menu;
				AudioPlayer.stopMusic("music");
			
			}
		}
				
					
		if(game.gameEtat == ETAT.Play) {
						if(mouseOver(mx,my,296, 2,40, 20)) { 
							game.gameEtat = ETAT.Menu;
							handler.clearAll();
							handler.clearEnemy();
							AudioPlayer.stopMusic("music");
							handler.clearAll();
							handler.clearEnemy();
						}
					}
					
					
				
		
		
			
	}
	public void mouseReleased(MouseEvent e) {
		
	} 

	
	public void tick() {
		
	} 
	
	private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
		if(mx>=x && mx <= x+w && my>=y && my<=y+h) return true;
		else return false;
	}
	public void render(Graphics g) {
		
		
		if(game.gameEtat == ETAT.Menu) {

			Font fnt = new Font("arial",1,50);
			Font fnt2 = new Font("arial",1,30);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Menu",255,60);
			
			g.setColor(Color.green);
			g.setFont(fnt2);
			g.drawRect(207, 110, 226, 64);
			g.drawString("Play",286,152);
			
			g.setColor(Color.orange);
			g.drawRect(207, 230, 226, 64);
			g.drawString("Help",288,272);
			
			g.setColor(Color.red);
			g.drawRect(207, 350, 226, 64);
			g.drawString("Quit",286,392);
		}
		
			
		if(game.gameEtat == ETAT.Help) {
				Font fnt = new Font("arial",1,50);
				Font fnt2 = new Font("arial",1,20);
				Font fnt3 = new Font("arial",1,40);
				g.setColor(Color.white);
				g.setFont(fnt);
				g.drawString("Help",260,60);
				
				g.setFont(fnt2);
				
				g.drawString("if you couldn't figure it out honestly just give up man you're seriously hopeless -_-",10,130);
				g.drawString("ps:pause with spacebar",10,160);
				g.setFont(fnt3);
				g.drawRect(207, 350, 226, 64);
				g.drawString("Retour",255,394);
				
			}
		
			
				
		if(game.gameEtat == ETAT.End) {
					Font fnt = new Font("arial",1,50);
					Font fnt3 = new Font("arial",1,40);
					g.setColor(Color.white);
					g.setFont(fnt);
					g.drawString("Game Over",190,60);
					
					g.setColor(Color.orange);
					
					g.drawRect(207, 230, 226, 64);
					g.drawString("Menu",254,275);
					g.setFont(fnt3);
					g.setColor(Color.green);
					g.drawRect(207, 350, 226, 64);
					g.drawString("Try Again",232,392);
					g.setColor(Color.gray);
					g.drawString("Score: "+HUD.score,240,100);
					
					
				}
		if(game.gameEtat == ETAT.Win) {
			Font fnt = new Font("arial",1,50);
			Font fnt3 = new Font("arial",1,40);
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Level Complete!",190,60);
			
			g.setColor(Color.orange);
			
			g.drawRect(207, 230, 226, 64);
			g.drawString("Menu",254,275);
			g.setFont(fnt3);
			if(Spawn.lvlcount!=3) {
				
			g.setColor(Color.green);
			g.drawRect(207, 350, 226, 64);
			g.drawString("Next Level",232,392);
			}
			g.setColor(Color.gray);
			g.drawString("Score: "+HUD.score,240,100);
			
			
		}
				
			
		
	}
}
