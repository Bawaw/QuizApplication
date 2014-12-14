package view;

import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MainViewUser extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public MainViewUser(WindowListener windowListener){
		addWindowListener(windowListener);
		Dimension d=new Dimension(600,400);
		this.setTitle("USER");
		this.setMinimumSize(d);
    	this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
