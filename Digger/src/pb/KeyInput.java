package pb;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keySync = new boolean[4];
	private Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler=handler;
		this.game=game;
		keySync[0]=false;
		keySync[1]=false;
		keySync[2]=false;
		keySync[3]=false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_UP) {tempObject.setVitY(-3); keySync[0] = true;}
				if(key == KeyEvent.VK_DOWN) {tempObject.setVitY(3); keySync[1] = true;}
				if(key == KeyEvent.VK_RIGHT) {tempObject.setVitX(3); keySync[2] = true;}
				if(key == KeyEvent.VK_LEFT) {tempObject.setVitX(-3); keySync[3] = true;}
				
			}
           
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_P || key == KeyEvent.VK_SPACE) game.pause();
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_UP) keySync[0] = false;
				if(key == KeyEvent.VK_DOWN) keySync[1] = false;
				if(key == KeyEvent.VK_RIGHT) keySync[2] = false;
				if(key == KeyEvent.VK_LEFT) keySync[3] = false;
				
				//Vkey sync
				if(keySync[0]==false && keySync[1] == false) tempObject.setVitY(0);
				//Hkey sync
				if(keySync[2]==false && keySync[3] == false) tempObject.setVitX(0);
			}
		}
	}
	

}
