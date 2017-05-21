import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;



public class Tuna extends JFrame {
	
	private JTextField item1;
	private JTextField item2;
	private JTextField item3;
	private JPasswordField pass;
	
	public Tuna(){
		super("Titty bar");
		setLayout(new FlowLayout());
		item1=new JTextField(10);
		add(item1);
		item2=new JTextField("Enter text here");
		add(item2);
		item3=new JTextField("uneditable",20);
		item3.setEditable(false);
		add(item3);
		pass=new JPasswordField("mypasss");
		add(pass);
		
		thehandler hand=new thehandler();
		item1.addActionListener(hand);
		item2.addActionListener(hand);
		item3.addActionListener(hand);
		pass.addActionListener(hand);
		
	}
	
	private class thehandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			String str="";
			
			if(event.getSource()==item1){
				str=String.format("field 1: %s", event.getActionCommand());
				
			} else if(event.getSource()==item2){
				str=String.format("field 2: %S", event.getActionCommand());
			} else if(event.getSource()==item3){
				str=String.format("field 3: %S", event.getActionCommand());
			} else if(event.getSource()==pass){
				str=String.format("password field is : %S", event.getActionCommand());
			}
				
			JOptionPane.showMessageDialog(null,str);
			
				
			
		}
		
	}
	
}
