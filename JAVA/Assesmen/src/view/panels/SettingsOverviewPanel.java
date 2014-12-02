package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import view.ViewException;

public class SettingsOverviewPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JComboBox selectBeh= new JComboBox();
	private JComboBox scoreBeh= new JComboBox();
	private JSlider amount=new JSlider(1, 30);
	
	public SettingsOverviewPanel(Action action) throws ViewException {
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initListTitle(row);
		row++;

		addComboCouple("SelectBehaviour",this.getSelectBeh(),row);
		row++;
		addComboCouple("ScoreBehaviour",this.getScoreBeh(),row);
		row++;
		addSliderCouple("Number of Questions",this.getAmount(),row);
		row ++;
		AddButton(action, row);
	}

	private void initListTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Settings:"));

	}

	private void addSliderCouple(String name,JSlider j,int rij){
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel(name));
		changeConstraints(1, 1, 1, rij);
		addToPanel(j);
		changeConstraints(1, 1, 2, rij);
		JTextField jt=new JTextField("Dit veld update");
		jt.setEditable(false);
		addToPanel(jt);
	}
	
	private void AddButton(Action action,int rij){
		changeConstraints(1, 3, 0, rij);
		JButton j=new JButton("Save:");
		j.addActionListener(action);
		addToPanel(j);
		
	}
	
	private void addComboCouple(String name,JComponent j,int rij){
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel(name));
		changeConstraints(1, 2, 1, rij);
		addToPanel(j);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
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

	public JComboBox getSelectBeh() {
		return selectBeh;
	}

	public void setSelectBeh(JComboBox selectBeh) {
		this.selectBeh = selectBeh;
	}

	
	

	public JComboBox getScoreBeh() {
		return scoreBeh;
	}

	public void setScoreBeh(JComboBox scoreBeh) {
		this.scoreBeh = scoreBeh;
	}

	public JSlider getAmount() {
		return amount;
	}

	public void setAmount(JSlider amount) {
		this.amount = amount;
	}

	public void setSelectList(String[] selectList) {
		DefaultComboBoxModel model = new DefaultComboBoxModel(selectList);
		this.getSelectBeh().setModel(model);
	}

	
	public void setScoreList(String[] scoreList) {
		DefaultComboBoxModel model = new DefaultComboBoxModel(scoreList);
		this.getScoreBeh().setModel(model);
	}

	public void setAmount(int amount){
		this.getAmount().setValue(amount);
	}


	
	
	
}
