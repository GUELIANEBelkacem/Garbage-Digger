package pb;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
		
	public void clearEnemy() {
		for(int i = 0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			if(tempObject.id != ID.Player) removeObject(tempObject);
		}
	}
	public void clearAll() {
		for(int i = 0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			removeObject(tempObject);
		}
		for(int i = 0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			removeObject(tempObject);
		}
		for(int i = 0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			removeObject(tempObject);
		}
		for(int i = 0;i<object.size();i++) {
			GameObject tempObject = object.get(i);
			removeObject(tempObject);
		}
	}
	

}
