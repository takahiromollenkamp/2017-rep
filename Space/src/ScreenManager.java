import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.lang.reflect.InvocationTargetException;



public class ScreenManager {
	
	private GraphicsDevice vc;
	
	//give vc access to monitor
	public ScreenManager(){
		GraphicsEnvironment e=GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc=e.getDefaultScreenDevice();
	}
	
	public DisplayMode[] getCompatibleDisplayModes(){
		return vc.getDisplayModes();
	}
	
	//compare DM passed inn to see match
	public DisplayMode findFirstCompatibleMode(DisplayMode[] modes){
		DisplayMode goodModes[]=vc.getDisplayModes();
		for(int i=0;i<modes.length; i++){
			for(int y=0;y<goodModes.length;y++){
				if(displayModesMatch(modes[i],goodModes[y])){
					return modes[i];
				}
			}
		}
		return null;
	}
	public DisplayMode getCurrentDisplayMode(){
		return vc.getDisplayMode();
	}
	
	//checks if two modes match each other
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
		if(m1.getWidth()!=m2.getWidth()||m1.getHeight()!=m2.getHeight()){
			return false;
		}
		if(m1.getBitDepth()!= DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth()!= DisplayMode.BIT_DEPTH_MULTI&&m1.getBitDepth()!=m2.getBitDepth()){
			return false;
		}
		if(m1.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate()!=m2.getRefreshRate()){
			return false;
		}
		return true;
	}
	//make frame full screen
	public void setFullScreen(DisplayMode dm){
		JFrame f =new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);
		
		if(dm!=null &&vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
			}catch(Exception ex){}
		}
		f.createBufferStrategy(2);
		
		
	}
	
	//graphics object equal to this
	public Graphics2D getGraphics(){
		Window w=vc.getFullScreenWindow();
		if(w!=null){
			BufferStrategy s =w.getBufferStrategy();
			return (Graphics2D)s.getDrawGraphics();
		} else{
			return null;
		}
	}
	//updates display
	public void update(){
		Window w=vc.getFullScreenWindow();
		if(w!=null){
			BufferStrategy s =w.getBufferStrategy();
			if(!s.contentsLost()){
				s.show();
			}
		}
	}
	//return full screen window
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	
	public int getWidth(){
		Window w =vc.getFullScreenWindow();
		if(w!=null){
			return w.getWidth();
		}
		else{
			return 0;
		}
	}
	public int getHeight(){
		Window w =vc.getFullScreenWindow();
		if(w!=null){
			return w.getHeight();
		}
		else{
			return 0;
		}
	}
	
	//get out of full screen
	public void restoreScreen(){
		Window w=vc.getFullScreenWindow();
		if(w!=null){
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	//create image compatible with monitor
	public BufferedImage createCompatibleImage(int w, int h, int t){
		Window win =vc.getFullScreenWindow();
		if(win!=null){
			GraphicsConfiguration gc =win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w,h,t);
		}
		return null;
	}
		
	
	

	
	
}
