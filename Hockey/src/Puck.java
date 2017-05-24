import java.awt.Image;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Puck extends Sprite {
	private Image pic;
	public Puck(){
		super();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("puck.png");
		try{
			pic=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
		setX(300);
		setY(300);
		setVelocityY(0);
		setVelocityX(0);
	}
	
	public void update(long timePassed){
		super.update(timePassed);
		/*if(getX()<0||getX()>800){
			setVelocityX(-getVelocityX());
		}*/
		if(getY()<0){
			setY(0);
			setVelocityY(-getVelocityY());
		}
		if(getY()>600){
			setY(600);
			setVelocityY(-getVelocityY());
		}
	}
	public Image getImage(){
		return pic;
	}

}
