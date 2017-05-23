import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;
public class Explosion extends Sprite {
	private Image pic;
	private boolean alive;
	public boolean isAlive(){
		return alive;
	}
	public Image getImage(){
		return pic;
	}
	public void activate(){
		alive=true;
		disappear();
	}
	public Explosion(){
		
		super();
		alive=false;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("explosion.jpg");
		try{
			pic=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
	}
	public void disappear(){
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	setX(2000);
			              setY(2000);
		              alive=false;
		              
		            }
		        }, 
		        600 
		);
	}
}
