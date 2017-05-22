import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Play2 extends Core implements KeyListener{
	public static void main(String[] args) {
		new Play2().run();
	}
	
	private Ship ship;
	private Alien[] empire;
	private ScreenManager s;
	private Image bg;
	private Shot[] beam;
	private int shotCount;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean spacePressed;
	private long shotTracker;
	private int score;
	private boolean keep;
	
	public boolean shoot(){
		if(System.currentTimeMillis()-shotTracker>200){
			return true;
		}
		return false;
	}
	
	
	public synchronized void draw(Graphics2D g){
		
		g.drawImage(bg,0,0,null);
		g.drawImage(empire[0].getEvilShot(0).getImage(), Math.round(empire[0].getEvilShot(0).getX()), Math.round(empire[0].getEvilShot(0).getY()),null);
		g.drawImage(ship.getImage(), Math.round(ship.getX()), Math.round(ship.getY()),null);
		for(int i=0;i<10;i++){
			if(beam[i].loaded()){
				g.drawImage(beam[i].getImage(), Math.round(beam[i].getX()), Math.round(beam[i].getY()),null);
			}
		}
		for(int j=0;j<10;j++){
			if(empire[j].isAlive()){
				g.drawImage(empire[j].getImage(), Math.round(empire[j].getX()), Math.round(empire[j].getY()),null);
				for(int i=0;i<10;i++){
					if(empire[j].getEvilShot(i).loaded()){
						g.drawImage(empire[j].getEvilShot(i).getImage(), Math.round(empire[j].getEvilShot(i).getX()), Math.round(empire[j].getEvilShot(i).getY()),null);
					}
				}
			}
		}
		
	}
	public void update(long timePassed){
		
		
		if(spacePressed&&shoot()){
			beam[shotCount%10].activate(ship.getX()+22,ship.getY()-30);
			shotCount++;
			shotTracker=System.currentTimeMillis();
			
		}
		for(int k=0;k<10;k++){
			if(empire[k].isAlive()&&empire[k].canShoot()){
				
				empire[k].getEvilShot(empire[k].getShot()%10).activate(empire[k].getX()+20, empire[k].getY()+30);
				empire[k].incShot();
				
			}
		}
		if(rightPressed){
			ship.setVelocityX(.4f);
		}
		if(leftPressed){
			ship.setVelocityX(-.4f);
		}
		if(rightPressed&&leftPressed){
			ship.setVelocityX(0);
		}
		
		if(ship.getX()<0){
			ship.setX(10);
			ship.setVelocityX(.001f);
		}else if(ship.getX()+ship.getWidth()>s.getWidth()){
			ship.setX(s.getWidth()-.001f-ship.getWidth());
			ship.setVelocityX(0);
		}
		
		if(!empire[0].isAlive()&&!empire[1].isAlive()&&!empire[2].isAlive()&&!empire[3].isAlive()&&!empire[4].isAlive()&&!empire[5].isAlive()&&!empire[6].isAlive()&&!empire[7].isAlive()&&!empire[8].isAlive()&&!empire[9].isAlive()){
			spawnAliens();
		}
		ship.update(timePassed);
		for(int i=0;i<10;i++){
			if(beam[i].loaded()){
				beam[i].update(timePassed);
			}	
		}
		for(int j=0;j<10;j++){
			{
				empire[j].update(timePassed);
			}	
		}
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				float aa=empire[i].getX()+15;
				float bb=empire[i].getY()+10;
				float cc=beam[j].getX();
				float dd=beam[j].getY();
				float ee=ship.getX();
				float ff=ship.getY();
				if(Math.abs(aa-cc)<18&&Math.abs(bb-dd)<10){
					//destroy ship
					beam[i].setX(-20);
					beam[i].setY(-20);
					empire[i].kill();
					
					score+=5;
					
				}
				
				
			}
		}
	}
	public void spawnAliens(){
		empire=new Alien[10];
		for(int i=0;i<10;i++){
			empire[i]=new Alien(20+60*i, 20);
		}
	}
	
	public void init(){
		super.init();
		s=getS();
		Window w=s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input1 = classLoader.getResourceAsStream("space2.jpg");
		
		try{
			bg=ImageIO.read(input1);
			
		} catch(Exception e){}
		
		beam=new Shot[10];
		for(int i=0;i<10;i++){
			beam[i]=new Shot();
			
		}
		
		empire=new Alien[10];
		for(int i=0;i<10;i++){
			empire[i]=new Alien(20+60*i, 20);
		}
		shotCount=0;
		leftPressed=false;
		rightPressed=false;
		spacePressed=false;
		
		shotTracker=0;
		
		ship =new Ship();
		
	}
	public void keyPressed(KeyEvent e){
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_ESCAPE){
			System.exit(0);
			stop();
		} else if(keyCode==KeyEvent.VK_RIGHT){
			rightPressed=true;
			leftPressed=false;
			
		} else if(keyCode==KeyEvent.VK_LEFT){
			leftPressed=true;
			rightPressed=false;
			
		}  
		if(keyCode==KeyEvent.VK_SPACE){
			spacePressed=true;
			
		}
		e.consume();
	}
	public void keyTyped(KeyEvent e){
		e.consume();
	}
	public void keyReleased(KeyEvent e){
		int keyCode=e.getKeyCode();
		if(keyCode==KeyEvent.VK_RIGHT){
			rightPressed=false;
			ship.setVelocityX(0);	
		} else if(keyCode==KeyEvent.VK_LEFT){
			leftPressed=false;
			ship.setVelocityX(0);
		}  
		if(keyCode==KeyEvent.VK_SPACE){
			spacePressed=false;
		}
	}
	
}
