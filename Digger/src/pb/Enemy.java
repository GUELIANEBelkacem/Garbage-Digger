package pb;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Enemy extends Antagonist{
	private Player player;
	private int[][] lev = Spawn.lvl;
	private BufferedImage image;
	public static int[][] map; 
	private int[][] map2; 
	private int lock=0;
	private int posx,posy,pposx,pposy;
	private int hoha=0;
	private ArrayList<Node> AStar;
	private int vit;
	private Comparator<Node> sorter = new Comparator<Node>() {

		
		public int compare(Node n1, Node n2) {
			if(n1.f<n2.f) return -1;
			if(n1.f>n2.f) return 1;
			return 0;
		}
		
	};
	

	public Enemy(int x, int y, ID id, Player player) {
		super(x, y, id);
		if(Spawn.lvlcount==3)vit=5;
		else vit=2;
		this.player=player;
		vitX =  vit;
		vitY =  vit;
		SpriteSheet sprite = new SpriteSheet(Game.hitlerpepe);
		image = sprite.grabImage(40);
		map = new int[16][26];
		for(int i=1; i<15;i++) {
			for(int j=1;j<25;j++) {
				
				map[i][j]=lev[i-1][j-1];
			}
		}
		for(int j=0;j<26;j++) {
			map[0][j]=1;
			map[15][j]=1;
		}
		for(int j=1;j<15;j++) {
			map[j][0]=1;
			map[j][25]=1;
		}
		map2=map;

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}

	
	public void tick() {
		for(int i=1; i<16;i++) {
			for(int j=1;j<26;j++) {
				if(map2[i][j]==1 && map[i][j]==0)map2[i][j]=0;
			}
			}
		
		int pox = (int)((x-(x%50))/50)+1;
		int poy = (int)((y-(y%50))/50)+1;
		int ppox = (int)((player.x-(player.x%50))/50)+1;
		int ppoy = (int)((player.y-(player.y%50))/50)+1;
		
			


		if(hoha==0) {
			AStar=strategy(pox,poy,ppox,ppoy);
			for(int i=0; i<16;i++) {
			for(int j=0;j<26;j++) {

				for(int k = 0; k<AStar.size(); k++) {
					if(AStar.get(k).getX()==j && AStar.get(k).getY()==i) {
						map2[i][j]=2;
						
					}

				}

			}
			

		}
			hoha=1;
			pposx=ppox;
			pposy=ppoy;
			posx=pox;
			posy=poy;
		}
		
        //check before delete here
		if(pox!=posx || poy!=posy){
			
			if(x!=(pox-1)*50) {
				if(x<(pox-1)*50) x+=vit;
				if(x>(pox-1)*50) x-=vit;
				lock=1;
			}else {
			if(y!=(poy-1)*50) {
				if(y<(poy-1)*50) y+=vit;
				if(y>(poy-1)*50) y-=vit;
				lock=1;
			}else {
		    map2[posy][posx]=0;
			posx=pox;
			posy=poy;
			lock=0;
			}}
		}
		
		if(posx==pposx && posy==pposy) {
			

			pposx=ppox;
			pposy=ppoy;

			AStar=strategy(posx,posy,pposx,pposy);
			for(int i=0; i<16;i++) {
			for(int j=0;j<26;j++) {
                
                if(AStar!=null) {
				for(int k = 0; k<AStar.size(); k++) {
					if(AStar.get(k).getX()==j && AStar.get(k).getY()==i) {
						map2[i][j]=2;
						
					}

				}
				}
                else {
                	pposx=ppox;
                	pposy=ppoy;
                }

			}
		}
		}
		int dudade=0;
        if(lock==0) {
        if(map2[poy][pox+1]==2) {
        	x+=vitX;
        	dudade=1;
        }
        if(map2[poy][pox-1]==2) {
        	x-=vitX;
        	dudade=1;
        }
        if(map2[poy+1][pox]==2) {
        	y+=vitY;
        	dudade=1;
        }
        if(map2[poy-1][pox]==2) {
        	y-=vitY;
        	dudade=1;
        }
        if(dudade==0) {

        	System.out.println("fuuuuuuuck");
            if(pox<24 && map2[poy][pox+2]==2) {
            	x+=vitX;
            	dudade=1;
            }
            if(pox>1 && map2[poy][pox-2]==2) {
            	x-=vitX;
            	dudade=1;
            }
            if(poy<14 && map2[poy+2][pox]==2) {
            	y+=vitY;
            	
            	dudade=1;
            }
            if(poy>1 && map2[poy-2][pox]==2) {
            	y-=vitY;
            	dudade=1;
            }/*
            if(map2[poy+1][pox+1]==2) {
            	x+=vitX;
            	y+=vitY;
            	dudade=1;
            }
            if(map2[poy-1][pox-1]==2) {
            	x-=vitX;
            	y-=vitY;
            	dudade=1;
            }
            if(map2[poy+1][pox-1]==2) {
            	y+=vitY;
            	x-=vitX;
            	dudade=1;
            }
            if(map2[poy-1][pox+1]==2) {
            	y-=vitY;
            	x+=vitX;
            	dudade=1;
            }
            */
			
            /*
			pposx=ppox;
			pposy=ppoy;

			AStar=strategy(pox,poy,ppox,ppoy);
			for(int i=0; i<16;i++) {
			for(int j=0;j<26;j++) {
                
                if(AStar!=null) {
				for(int k = 0; k<AStar.size(); k++) {
					if(AStar.get(k).getX()==j && AStar.get(k).getY()==i) {
						map2[i][j]=2;
						
					}

				}}
                else {
                	pposx=ppox;
                	pposy=ppoy;
                }

			}
		}
			*/
        }
        
        }
       
		/*
		for(int i=0; i<16;i++) {
			
			for(int j=0;j<26;j++) {
				if(i==poy && j==pox)System.out.print("3");
				else System.out.print(map2[i][j]); 
	
			
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		for(int i=0; i<16;i++) {
			
			for(int j=0;j<26;j++) {
				int duk=0;
				for(int k = 0; k<AStar.size(); k++) {
					if(AStar.get(k).getX()==j && AStar.get(k).getY()==i) {
						System.out.print("1");
						duk=1;
					}

				}
				
				if(duk==0)System.out.print("0"); 
	
			
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		


		*/

	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect(x, y, 10, 10);
		g.drawImage(image, x, y, null);
	}
	

	

	
	public ArrayList<Node> strategy(int pox, int poy, int ppox, int ppoy) {
	
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		Node current = new Node(pox,poy,null,0,10*distance(pox,poy,ppox,ppoy));
		open.add(current);
		while(open.size()>0) {
			Collections.sort(open,sorter);
			current = open.get(0);
			if(current.egale(ppox, ppoy)) {
				ArrayList<Node> path = new ArrayList<Node>();
				while(current != null) { // current.parent
					path.add(current);
					current=current.parent;
				}
				open.clear();
				closed.clear();
				return path;
			}
			open.remove(current);
			closed.add(current);
			for(int i = 0; i<9; i++) {
				if(i==4) continue;
				int cx=current.getX();
				int cy=current.getY();
				int xi=(i%3)-1;
				int yi=(i/3)-1;
				if(xi==-1 && yi==-1) continue;
				if(xi==1 && yi==-1) continue;
				if(xi==-1 && yi==1) continue;
				if(xi==1 && yi==1) continue;
				int px=cx+xi;
				int py=cy+yi;
				if(map2[py][px]==1) continue;
				double g = current.g + 10*distance(px,py,current.getX(),current.getY());
				double h = 10*distance(px,py,ppox,ppoy);
				Node node = new Node(px,py,current,g,h);
				if(inList(closed,node) && g>=current.g ) continue;
				if(!inList(open,node) || g<current.g) open.add(node);
				
			}
			
			
		}
		closed.clear();
		return null;

		}
	
	
	public boolean inList(ArrayList<Node> l,Node n) {
		for(int i=0; i<l.size(); i++) {
			Node nn = l.get(i);
			if(n.getX() == nn.getX() && n.getY() == nn.getY()) return true;
		}
		return false;
	}


	public void strategy() {
		
	}
}
