
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class AlienShot extends Sprite {
	private Image pic;
	private boolean load;
	
	public void activate(Float a, Float b){
		load=true;
		setX(a);
		setY(b);
		setVelocityX(0);
		setVelocityY(.15f);
	}
	public boolean loaded(){
		return load;
	}
	
	public AlienShot(){
		super();
		load=false;
		
		
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("evilShot.png");
		
		try{
			pic=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
	}
	public Image getImage(){
		return pic;
	}
}