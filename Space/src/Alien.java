
import java.awt.Image;
import java.io.InputStream;


import javax.imageio.ImageIO;


public class Alien extends Sprite {
	private Image phot;
	private float initialX;
	private boolean alive;
	private AlienShot[] evil;
	private long shotTracker;
	private int shotCount;
	
	public void change(){
		if(getX()-initialX>200){
			setVelocityX(-(getVelocityX()+.03f));
			setX(getX()-10);
			setY(getY()+60);
		}
		if(getX()-initialX<0){
			setVelocityX(-(getVelocityX()-.03f));
			setX(getX()+10);
			setY(getY()+60);
		}
		
	}
	public AlienShot getEvilShot(int i){
		return evil[i];
	}
	public void update(long timePassed){
		super.update(timePassed);
		
		change();
		for(int i=0;i<10;i++){
			if(evil[i].loaded()){
				evil[i].update(timePassed);
			}
		}
		
	}
	
	public int getShot(){
		return shotCount;
	}
	public void incShot(){
		shotCount++;
		shotTracker=System.currentTimeMillis();
	}
	public boolean canShoot(){
		if(System.currentTimeMillis()-shotTracker>1000){
			return true;
		}
		return false;
	}
	
	
	
	public boolean isAlive(){
		return alive;
	}
	public void kill(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("explosion.jpg");
		try{
			phot=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
		delayDeath();
	}
	public void delayDeath(){
		long start=System.currentTimeMillis();
		long end=start;
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		              alive=false;
		            }
		        }, 
		        600 
		);
		

		
	}
	public Alien(float a, float b){
		super();
		alive=true;
		setX(a);
		setY(b);
		setVelocityX(.03f);
		setVelocityY(0f);
		initialX=a;
		evil=new AlienShot[10];
		for(int i=0;i<10; i++){
			evil[i]=new AlienShot();
		}
		shotTracker=(long) (System.currentTimeMillis()+1000*Math.random());
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("alien.jpg");
		
		try{
			phot=ImageIO.read(input1);
		} catch(Exception e){
			System.out.println("Where the picture?");
		}
	}
	public Image getImage(){
		return phot;
	}
}
