import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Sprite {
	
	private Animation a;
	private float x;
	private float y;
	private float vx;
	private float vy;
	/*private Image constantImage;
	public void setImage(String name){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream(name);
		try{
			constantImage=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
	}
	public Image getConstantImage(){
		return constantImage;
	}*/
	
	
	public Sprite(Animation a){
		this.a=a;
	}
	public Sprite(){
		
	}
	
	//change position
	public void update(long timePassed){
		x+=vx*timePassed;
		y+=vy*timePassed;
		if(a!=null){
		a.update(timePassed);
		}
		
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x=x;
	}
	public void setY(float y){
		this.y=y;
	}
	public int getHeight(){
		return a.getImage().getHeight(null);
	}
	public int getWidth(){
		return a.getImage().getWidth(null);
	}
	public float getVelocityX(){
		return vx;
	}
	public float getVelocityY(){
		return vy;
	}
	public void setVelocityX(float vx){
		this.vx=vx;
	}
	public void setVelocityY(float vy){
		this.vy=vy;
	}
	
	public Image getImage(){
		return a.getImage();
	}
	
	
}
