
	package com.edu4java.samplegame;
	import java.awt.Color;
import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
	import javax.swing.JFrame;
	import javax.swing.JPanel;

	@SuppressWarnings("serial")
	public class Practice extends JPanel {
		int x = 100;
	    int y = 100;
	    int a=200;
	    int b=200;
	    private void moveBall() {
	    	x=100+(int)(100*(Math.random()));
	    	y=100+(int)(100*(Math.random()));
	    }
	   
	    @Override
	    public void paint(Graphics g) {
	        super.paint(g);
	        if(stat()){
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	       
			g2d.setColor(Color.green);
			
	        g2d.fillOval(x, y, 30, 30);
	        
	        }
	        
	    }
	    public boolean stat(){
	    	if(a<110){
	    		return false;
	    	}
	    	return true;
	    }
	    public static void main(String[] args) throws InterruptedException {
	        JFrame frame = new JFrame("Sample Frame");
	        Practice game = new Practice();
	        frame.add(game);
	        frame.setSize(300, 400);
	       

	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        for(int i=0; i<10; i++){
	        	
	            game.moveBall();
	            game.repaint();
	            Thread.sleep(50);
	            
	        }
	        
	       

	    }
	}

