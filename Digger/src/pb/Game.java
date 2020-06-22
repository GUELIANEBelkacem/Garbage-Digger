package pb;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 3259677787793803462L;
	public static final int width=1200 , height=width/10*6; 
	private Thread thread;
	private boolean running = false;
	private Menu menu;
	private Player player;
	
	private Handler handler;
	private int t = 0;
	
	private HUD hud;
	private int tim=0;
	
	private boolean paused = false;

	
	public enum ETAT{
		Menu(),
		Help(),
		End(),
		Win(),
		Play();
	}
	
	public ETAT gameEtat = ETAT.Menu;
	
	public static BufferedImage pizzabox;
	public static BufferedImage ground;
	public static BufferedImage hitlerpepe;
	public static BufferedImage playerimg;
	public static BufferedImage bottle;
	public static BufferedImage cokecan;
	public static BufferedImage dirtypaper;
	public static BufferedImage dirtyapple;
	public static BufferedImage fishbone;
	public static BufferedImage goalbin;
	public static BufferedImage goalbin2;
	public static BufferedImage terroristpepe;
	public static BufferedImage terroristpepe2;
	Random r;
	
	public Game() {
		handler = new Handler();
		hud = new HUD(this,handler);
		menu = new Menu(this,handler,hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		pizzabox = loader.loadImage("/pizzabox.png");
		ground = loader.loadImage("/floore.png");
		hitlerpepe = loader.loadImage("/hitlerpepe.png");
		playerimg = loader.loadImage("/player.png");
		fishbone = loader.loadImage("/fishbone.png");
		dirtypaper = loader.loadImage("/dirtypaper.png");
		cokecan = loader.loadImage("/cokecan.png");
		dirtyapple = loader.loadImage("/dirtyapple.png");
		bottle = loader.loadImage("/bottle.png");
		goalbin = loader.loadImage("/goalbin.png");
		goalbin2 = loader.loadImage("/goalbin2.png");
		terroristpepe = loader.loadImage("/terroristpepe.png");
		terroristpepe2 = loader.loadImage("/terroristpepe2.png");
		AudioPlayer.load();
		

		
		
		new Window(Game.width, Game.height, "garbage digger", this);
		
		

		
		//new spawn
		
		r = new Random();

		if(gameEtat == ETAT.Play) {
			this.handlerUpdate();
		}

	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    while(delta >=1)
                            {
                                tick();
                                delta--;
                            }
                            if(running)
                                render();
                            frames++;
                            
                            if(System.currentTimeMillis() - timer > 1000)
                            {
                                timer += 1000;
                                System.out.println("FPS: "+ frames);
                                frames = 0;
                            }
        }
                stop();
    }
	
	private void  tick() {
		
		
		if(paused == false) {
			if(gameEtat == ETAT.Play) {
				if(tim==0) {
					t=0;
					tim=1;
				}
				
				t++;
				
				if(t==300 || t==600) {
					handler.addObject(new Enemy(1100,0,ID.Enemy,player));
					
					
				}
				if(t==200) {
					handler.addObject(new DirtyFly(1100,0,ID.Enemy,handler,player,terroristpepe2));
				}
				handler.tick();
				hud.tick();
			}
			else{
				tim=0;
				if(gameEtat == ETAT.Menu || gameEtat == ETAT.End || gameEtat == ETAT.Win) menu.tick();
			}
		}
		/*
		for(int i=0; i<14;i++) {
			for(int j=0;j<24;j++) {
				System.out.print(Spawn.lvl[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		*/

	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		if(paused) {
			g.setColor(Color.white);
			g.drawString("paused", 550, 15);
			g.setColor(Color.black);
		}
		
		handler.render(g);
		if(gameEtat == ETAT.Play) {
			hud.render(g);
		}
		else {
				if(gameEtat == ETAT.Menu || gameEtat == ETAT.Help || gameEtat == ETAT.End || gameEtat == ETAT.Win) {
					menu.render(g);
				}
		}
		g.dispose();
		bs.show();
		
		
	}
	
	public static int clamp(int param, int min, int max) {
		if(param >= max) return max;
		else if(param <= min) return min;
		else return param;
	}
	
	public void pause() {
		if(gameEtat == ETAT.Play) {
			if(paused) paused = false;
			else paused = true;
		}
	}
	public void handlerUpdate() {
		Spawn.level1();
		int[][] dim = Spawn.lvl;
		for(int i=0; i<6; i++) {
		int xi,yi;
		xi=(int)((Math.random()*24));
		yi=(int)((Math.random()*14));
		while(Spawn.lvl[yi][xi]!=1) {
			xi=(int)((Math.random()*24));
			yi=(int)((Math.random()*14));
		}
		Spawn.lvl[yi][xi]=0;
		int tak = (int)(Math.random()*6);
		xi=50*xi+5;
		yi=50*yi+5;
		switch(tak) {
		case 0:
			handler.addObject(new Garbage(xi,yi,ID.Garbage,handler,pizzabox));
			break;
		case 1:
			handler.addObject(new Garbage(xi,yi,ID.Garbage,handler,bottle));
			break;
		case 2:
			handler.addObject(new Garbage(xi,yi,ID.Garbage,handler,cokecan));
			break;
		case 3:
			handler.addObject(new Garbage(xi,yi,ID.Garbage,handler,fishbone));
			break;
		case 4:
			handler.addObject(new Garbage(xi,yi,ID.Garbage,handler,dirtypaper));
			break;
		case 5:
			handler.addObject(new Garbage(xi,yi,ID.Garbage,handler,dirtyapple));
			break;		
			}
		}

		

		player = new Player(0,0,ID.Player,handler);
		handler.addObject(player);
		handler.addObject(new Enemy(1100,0,ID.Enemy,player));

		for(int i=0;i<14;i++) {
			for(int j=0; j<24; j++) {
				if(dim[i][j] == 1) {
					handler.addObject(new Ground(j*50,i*50,ID.Ground,handler));

				}
				
			}
		}
		handler.addObject(new GoalBin(1150,0,ID.GoalBin,handler,goalbin,goalbin2,this));
	}
	
	public static void main(String[] args) {
		new Game();
		
	}

}
