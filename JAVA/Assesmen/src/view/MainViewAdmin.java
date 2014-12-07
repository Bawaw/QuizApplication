package view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.AbstractTestAction;


public class MainViewAdmin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;

	public MainViewAdmin(List<AbstractTestAction> actions){
		createMenuBar(actions);
		this.setTitle("ADMIN");
		Dimension d=new Dimension(600,400);
		this.setMinimumSize(d);
    	this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createMenuBar(List<AbstractTestAction> actions) {
		menuBar = new JMenuBar();
		for(AbstractTestAction action : actions){
			JMenuItem menuItem = new JMenuItem(action);
			menuBar.add(menuItem);
		}
		setJMenuBar(menuBar);
	}

	public void setMainPanel(JPanel mainPanel) {
		setContentPane(mainPanel);
	}
}
