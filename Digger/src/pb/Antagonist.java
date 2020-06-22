package pb;

public abstract class Antagonist extends GameObject {
	
	public Antagonist(int x, int y, ID id) {
		super(x,y,id);
	}
	public double distance(int x1,int y1,int x2,int y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)* (y1-y2));
	}
	public abstract void strategy();

}
