import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class Ship extends Sprite {
	private Image pic;
	private boolean alive;
	public boolean isalive(){
		return alive;
	}
	public void kill(){
		alive=false;
		setX(-2000);
		setY(-2000);
	}
	public Ship(){
		super();
		setX(400);
		setY(550);
		setVelocityX(0f);
		setVelocityY(0f);
		
		alive=true;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("ship.jpg");
		
		try{
			pic=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
	}
	
	public int getHeight(){
		return getImage().getHeight(null);
	}
	public int getWidth(){
		return getImage().getWidth(null);
	}
	public Image getImage(){
		return pic;
	}
}