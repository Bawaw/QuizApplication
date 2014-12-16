package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExcelPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JFileChooser fileChooser;

	public ExcelPanel(Action a) {
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		this.initFilChooser(row);
		this.AddButton(a, ++row);
	}
	
	
	private void initFilChooser(int rij){
		changeConstraints(1, 1, 0, rij);
		fileChooser=new JFileChooser();
		fileChooser.setControlButtonsAreShown(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XLS files", "xlsx");
		fileChooser.setFileFilter(filter);
		this.addToPanel(fileChooser);
		
	}
	
	private void AddButton(Action action,int rij){
		changeConstraints(1, 1, 0, rij);
		JButton j=new JButton("Read excel");
		j.addActionListener(action);
		addToPanel(j);
		
	}
	
	private void initConstraints() {
		constraints.insets = new Insets(10, 20, 10, 20);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}

	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}


	public File getSelectedFile(){
		return this.fileChooser.getSelectedFile();
	}
	
	

}
