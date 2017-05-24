import java.awt.Image;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Panel extends Sprite {
	private Image pic;
	public Panel(){
		super();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("panel.png");
		try{
			pic=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
		setVelocityY(0);
	}
	public int getWidth(){
		return pic.getWidth(null);
	}
	public int getHeight(){
		return pic.getHeight(null);
	}
	
	
	
	public Image getImage(){
		return pic;
	}

}
