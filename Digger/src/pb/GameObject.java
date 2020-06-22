package pb;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y; 
	protected ID id;
	protected double vitX, vitY;
	
	public GameObject(int x, int y, ID id) {
		this.x=x;
		this.y=y;
		this.id=id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();  //pour la detection des colisions ca marche seulment en un rectangle donc si l'object n'est pas un rectangle c'est pas correct mais meh
	
	public void setX(int x) {
		this.x=x;
	}
	public int getX() {
		return this.x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public int getY() {
		return this.y;
	}
	public void setId(ID id) {
		this.id=id;
	}
	public ID getId() {
		return this.id;
	}
	public void setVitX(double vitX) {
		this.vitX=vitX;
	}
	public double getVitX() {
		return this.vitX;
	}
	public void setVitY(double vitY) {
		this.vitY=vitY;
	}
	public double getVitY() {
		return this.vitY;
	}

	


}
