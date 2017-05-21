import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.*;


public class MousePractice extends Core implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener{

	public static void main(String[] args) {
		new MousePractice().run();
		
	}
	private String mess="";
	
	public void init(){
		super.init();
		Window w=s.getFullScreenWindow();
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
		w.addMouseWheelListener(this);
		w.addKeyListener(this);
	}
	public synchronized void draw(Graphics2D g){
		Window w =s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0, 0, s.getWidth(), s.getHeight());
		g.setColor(w.getForeground());
		g.drawString(mess, 30, 30);
	}
	
	//mouse listener
	public void mousePressed(MouseEvent e){
		mess="you pressed on mouse";
	}
	public void mouseReleased(MouseEvent e){
		mess="you released the mouse";
	}
	public void mouseClicked(MouseEvent e){
		
	}
	public void mouseEntered(MouseEvent e){
		
	}
	public void mouseExited(MouseEvent e){
		
	}
	public void mouseWheelMoved(MouseWheelEvent e){
		mess="moving the wheel";
	}
	
	//mouse motion
	public void mouseDragged(MouseEvent e){
		mess="dragging the mouse";
	}
	public void mouseMoved(MouseEvent e){
		mess="moving the mouse";
	}
	//key pressed
		public void keyPressed(KeyEvent e){
			int keyCode=e.getKeyCode();
			if(keyCode==KeyEvent.VK_ESCAPE){
				stop();
			} else{
				mess="Pressed :"+KeyEvent.getKeyText(keyCode);
				e.consume();
			}
		}
		//key Released
		public void keyReleased(KeyEvent e){
			int keyCode=e.getKeyCode();
			mess="Released :"+KeyEvent.getKeyText(keyCode);
			e.consume();
		}
		
		//last method
		public void keyTyped(KeyEvent e){
			e.consume();
		}
		
	
	

}
