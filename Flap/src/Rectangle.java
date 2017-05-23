
public class Rectangle extends Sprite {
	private double random;
	private boolean visible;
	private int low;
	private int high;
	
	public int getLow(){
		return low;
	}
	public int getHigh(){
		return high;
	}
	
	public void setHigh(int x){
		high=x;
		
	}
	
	public void setLow(int y ){
		low=y;
	}
	
	public Rectangle(){
		super();
		visible=false;
		random=Math.random();
	}
	public boolean isVisible(){
		return visible;
	}
	public double lowBound(){
		return random;
	}
	public void activate(){
		random=Math.random()*.7+.05;
		setVelocityX(-.3f);
		setX(800);
		setY(0);
		setVelocityY(0);
		visible=true;
	}
}
