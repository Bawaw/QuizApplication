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
import javax.swing.event.ChangeListener;

import view.ViewException;
import controller.JsliderAmountAction;

public class SettingsOverviewPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JComboBox<String> selectBeh= new JComboBox<String>();
	private JComboBox<String> scoreBeh= new JComboBox<String>();
	private JSlider amount=new JSlider(1, 30);
	private JTextField jTextField=new JTextField();
	
	public SettingsOverviewPanel(Action action,ChangeListener changeListener) throws ViewException {
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initListTitle(row);
		row++;

		addComboCouple("SelectBehaviour",this.getSelectBeh(),row);
		row++;
		addComboCouple("ScoreBehaviour",this.getScoreBeh(),row);
		row++;
		addSliderCouple("Number of Questions",this.getAmount(),this.getjTextField(),row,changeListener);
		row ++;
		AddButton(action, row);
	}

	private void initListTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Settings:"));

	}

	private void addSliderCouple(String name,JSlider j,JTextField jt,int rij,ChangeListener changeListener){
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel(name));
		changeConstraints(1, 1, 1, rij);
		j.addChangeListener(changeListener);
		addToPanel(j);
		changeConstraints(1, 1, 2, rij);
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

	private int getComboBoxIndex(String[] list,String item){

		for(int i=0;i<list.length;i++){
			if(list[i].equals(item)){
				return i;
			}
		}
		return 0;
	}
	
	public JComboBox<String> getSelectBeh() {
		return selectBeh;
	}

	public void setSelectBeh(JComboBox<String> selectBeh) {
		this.selectBeh = selectBeh;
	}

	
	public JTextField getjTextField() {
		return jTextField;
	}

	public void setjTextField(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	public JComboBox<String> getScoreBeh() {
		return scoreBeh;
	}

	public void setScoreBeh(JComboBox<String> scoreBeh) {
		this.scoreBeh = scoreBeh;
	}

	public JSlider getAmount() {
		return amount;
	}

	public void setAmount(JSlider amount) {
		this.amount = amount;
	}

	public void setSelectList(String[] selectList,String current) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(selectList);
		this.getSelectBeh().setModel(model);
		int index=getComboBoxIndex(selectList, current);
		this.getSelectBeh().setSelectedIndex(index);
	}

	
	public void setScoreList(String[] scoreList,String current) {
		DefaultComboBoxModel model = new DefaultComboBoxModel(scoreList);
		this.getScoreBeh().setModel(model);
		int index=getComboBoxIndex(scoreList, current);
		this.getScoreBeh().setSelectedIndex(index);
	}

	public void setAmount(int amount){
		this.getAmount().setValue(amount);
		setAmountField(amount);
	}


	public void setAmountField(int amount){
		this.getjTextField().setText(Integer.toString(amount));
	}
	
	public int getNumberOfQuestions(){
		return this.getAmount().getValue();
	}
	
	public String getSelectionBehaviour(){
		return (String)this.getSelectBeh().getSelectedItem();
	}
	
	public String getScoreBehaviour(){
		return (String)this.getScoreBeh().getSelectedItem();
	}
}