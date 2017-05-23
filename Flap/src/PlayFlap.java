import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class PlayFlap extends Core implements KeyListener {
	public static void main(String[] args) {
		/*new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	System.exit(0);
		              
		            }
		        }, 
		        15000
		);*/
		new PlayFlap().run();
	}
	private Bird trump;
	private Rectangle[] rect;
	private int rectCount;
	private long time;
	private int screenLength;
	private boolean first;
	private float ty;
	private Explosion explosion;
	private int score;
	private long initTime;
	private int finalScore;
	
	
	public synchronized void draw(Graphics2D g) {
		g.clearRect(0,0,1000,1000);
		if(trump.isAlive()){
		if(explosion.isAlive()){
			g.drawImage(explosion.getImage(), Math.round(explosion.getX()),Math.round(explosion.getY()), null);
		}
		g.drawString(" "+score, 50, 50);
		BufferedImage buffered = (BufferedImage) trump.getImage();;
		int drawLocationX = Math.round(trump.getX());
		int drawLocationY = Math.round(trump.getY());
		double rotationRequired =Math.atan((trump.getY()-ty)/200);
		double locationX = buffered.getWidth() / 2;
		double locationY = buffered.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g.drawImage(op.filter(buffered, null), drawLocationX, drawLocationY, null);
		
		for(int i=0;i<5; i++){
			if(rect[i].isVisible()){
				double r=rect[i].lowBound();
				int low=(int)(r*screenLength);
				double r2=r+.25;
				int high=(int)(r2*screenLength);
				rect[i].setLow(low);
				rect[i].setHigh(high);
				g.fillRect(Math.round(rect[i].getX()),high , 60,(screenLength-high));
				g.fillRect(Math.round(rect[i].getX()),0 , 60,low);
				
				
				
			}
		}
		} else {
			g.drawString("Your score was "+finalScore, 50,50);
		}
		
		
	}
	public void update(long timePassed){
		trump.update(timePassed);
		for(int i=0;i<5;i++){
			rect[i].update(timePassed);
		}
			
			 if(System.currentTimeMillis()-time>1500){
				rect[rectCount%5].activate();
				rectCount++;
				time=System.currentTimeMillis();
			}
	if(!trump.isAlive()){
		finalScore=score;
	} else{
		score=(int)((System.currentTimeMillis()-initTime-3000)/1500);
	}
			
			
		
		for(int i=0; i<5; i++){
			int a=rect[i].getLow();
			int b=rect[i].getHigh();
			int c=(int)rect[i].getX();
			int d=(int)trump.getX()+15;
			int e=(int)trump.getY()+15;
			if(Math.abs(d-c)<30&&(e-b>-15||e-a<15)){
				trump.terminate();
				trump.setX(2000);
				explosion.activate();
				explosion.setX(d);
				explosion.setY(e);
				
				
			}
			
		}
	}
	public void init(){
		super.init();
		ty=100;
		s=getS();
		Window w=s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
		trump=new Bird();
		rect=new Rectangle[5];
		explosion=new Explosion();
		for(int i=0;i<5;i++){
			rect[i]=new Rectangle();
		}
		first=true;
		rectCount=0;
		time=System.currentTimeMillis();
		initTime=System.currentTimeMillis();
		screenLength=s.getHeight();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		e.consume();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_SPACE){
			trump.setVelocityY(-.4f);
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	ty=trump.getY();
			              
			            }
			        }, 
			        40
			);
			
		}
		e.consume();
		
	}
}
