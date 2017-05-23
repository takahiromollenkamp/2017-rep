import java.awt.Image;
import java.io.InputStream;


import javax.imageio.ImageIO;


public class Bird extends Sprite {
	private Image pic;
	private float ay;
	private double angle;
	private boolean alive;
	
	public void terminate(){
		alive=false;
	}
	public boolean isAlive(){
		return alive;
	}
	public Bird(){
		super();
		setX(100);
		setY(-100);
		setVelocityX(0);
		setVelocityY(-.5f);
		angle=0;
		alive=true;
		ay=.0013f;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("dump.png");
		try{
			pic=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
	}
	public void update(long timePassed){
		super.update(timePassed);
		setVelocityY(getVelocityY()+ay*timePassed);
		if(getY()<-20&&getY()>-50){
			setY(50);
			setVelocityY(-.3f);
		}
		angle=Math.atan((getY())/200);
	}
	public double getAngle(){
		return angle;
	}
	public Image getImage(){
		return pic;
	}
}
