import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Pup {

	public static void main(String[] args) {
		Pup b=new Pup();
		b.run();

	}
	private Animation a;
	private ScreenManager s;
	private Image bg;
	
	private static final DisplayMode modes1[]={
			new DisplayMode(800,600,32,0),
			new DisplayMode(800,600,24,0),
			new DisplayMode(800,600,16,0),
			new DisplayMode(640,480,32,0),
			new DisplayMode(640,480,24,0),
			new DisplayMode(640,480,16,0)
	};
	
	//load images
	public void loadImages(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("space2.jpg");
		InputStream input2 = classLoader.getResourceAsStream("Face1.png");
		InputStream input3 = classLoader.getResourceAsStream("Face2.png");
		
		
		try {
			
			bg =ImageIO.read(input1);
			Image face1=ImageIO.read(input2);
		    Image face2=ImageIO.read(input3);
		    a=new Animation();
			a.addScene(face1, 250);
			a.addScene(face2, 250);
		}catch(Exception e){}
	}
	public void run(){
		s=new ScreenManager();
		try{
			DisplayMode dm=s.findFirstCompatibleMode(modes1);
			s.setFullScreen(dm);
			loadImages();
			movieLoop();
		}finally{
			s.restoreScreen();
		}
	}
	public void movieLoop(){
		long startingTime=System.currentTimeMillis();
		long cumTime=startingTime;
		while(cumTime-startingTime<6000){
			long timePassed = System.currentTimeMillis()-cumTime;
			cumTime+=timePassed;
			a.update(timePassed);
			//draw screen
			
			Graphics2D g=s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){}
			
		}
	}
	public void draw(Graphics g){
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(), 0,0, null);
	}

	
}
