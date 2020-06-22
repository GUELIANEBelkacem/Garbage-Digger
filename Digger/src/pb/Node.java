package pb;

public class Node {
	
	public double g;
	public double h;
	public double f;
	private int x;
	private int y;
	public Node parent;
    
	
	public Node(int x, int y, Node parent, double g, double h) {
		this.x=x;
		this.y=y;
		this.g=g;
		this.h=h;
		this.f=g+h;
		this.parent=parent;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean egale(int pox, int poy) {
		if(x==pox && y==poy) return true;
		else return false;
	}
	public String toString() {
		return x+" "+y;
	}

}
