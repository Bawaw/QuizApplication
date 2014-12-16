package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import view.ViewException;
import domain.Answer;

public class EvaluationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel questionText,index,category,score;
	private JPanel optionPanel;
	private JLabel timeLabel;
	private JButton next,prev,stop;
	private GridBagConstraints constraints=new GridBagConstraints();
	private ButtonGroup options=new ButtonGroup();
	private Timer timer;
	private final int TIME_INTERVAL = 1000;
	private int time;
	
	public EvaluationPanel(Action a,ActionListener tickEvent) {
		setLayout(new GridBagLayout());
		initConstraints();
		int row=0;
		initTimer(tickEvent,row);
		++row;
		initindexLabel(row);
		initCatLabel(row);
		initScoreLabel(row);
		initQuestionLabel(++row);
		initOptionPanel(++row);
		initPrevButton(a,++row);
		initStopButton(a, row);
		initNextButton(a,row);
	}
	
	private void initTimer(ActionListener tickEvent, int row){
		changeConstraints(1, 1, 1, row);
		timeLabel = new JLabel("paused");
		addToPanel(timeLabel);
		timer = new Timer(TIME_INTERVAL,tickEvent);
		stopTimer();
		timer.setRepeats(true);
	}
	
	public void stopTimer(){
		this.timer.stop();
	}
	
	public void startTimer(){
		timer.start();
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
		timeLabel.setText(Integer.toString(time) +" sec");
	}

	private void initStopButton(Action a,int row){
		stop = new JButton("stop");

		changeConstraints(1, 1, 1, row);
		stop.setAction(a);
		constraints.weighty = 0.2;
		stop.setActionCommand("stop");
		stop.setText("Stop");
		addToPanel(stop);
	}
	
	private void initCatLabel(int row){
		category=new JLabel("unkown");
		changeConstraints(1,1,1,row);
		this.addToPanel(category);
	} 
	
	private void initScoreLabel(int row){
		score=new JLabel("unkown");
		changeConstraints(1,1,2,row);
		this.addToPanel(score);
	} 
	
	private void initOptionPanel(int row){
		optionPanel=new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
		changeConstraints(1,2,0,row);
		this.addToPanel(optionPanel);
	}
	
	public void update(String question,List<Answer> opt,Answer previousAnswer,boolean hasNext,boolean hasPrev,int currentQ,int totalQ,String category,int points){
		this.setTextQuestion(question);
		setPossibleOptions(opt);
		startTimer();
		reselectAnswer(previousAnswer);
		if(!hasPrev){
			this.prev.setEnabled(false);;
		}
		else{
			this.prev.setEnabled(true);
		}
		
		if(!hasNext){
			this.changeNextButton();
		}
		else{
			this.changeFinishButton();
		}
		settingIndex(currentQ,totalQ);
		settingCategory(category);
		settingScore(points);
	}
	
	
	private void settingIndex(int currentQ,int totalQ){
		String index="Question: "+ currentQ+"/"+totalQ;
		this.index.setText(index);
	}
	
	private void settingCategory(String category){
		this.category.setText(category);
	}
	
	private void settingScore(int points){
		String text="Points: "+points;
		this.score.setText(text);
	}
	
	private void initindexLabel(int row){
		index=new JLabel("1/10");
		changeConstraints(1,1,0,row);
		this.addToPanel(index);
	}
	
	private void initQuestionLabel(int row){
		questionText=new JLabel("Here comes the question");
		changeConstraints(1,2,0,row);
		this.addToPanel(questionText);
	}
	
	private void initNextButton(Action a,int row){
		next = new JButton("next");

		changeConstraints(1, 1, 2, row);
		next.setAction(a);
		constraints.weighty = 0.2;
		next.setActionCommand("next");
		next.setText("Next");
		addToPanel(next);
	}
	
	private void initPrevButton(Action a,int row){
		prev = new JButton("previous");
		constraints.weighty = 0.2;
		changeConstraints(1, 1, 0, row);
		prev.setAction(a);
		prev.setActionCommand("prev");
		prev.setText("Previous");
		addToPanel(prev);
	}
	
	public void setTextQuestion(String text){
		this.questionText.setText(text);
	}
	
	public Answer getSelectedAnswer() throws ViewException{
		try{
		Enumeration<AbstractButton> AllRadios=options.getElements();
		while(AllRadios.hasMoreElements()){
			JRadioButton radio=(JRadioButton)AllRadios.nextElement(); 
			if(radio.isSelected()){
				return new Answer(radio.getText());
			}
		}
		}
		catch(Exception ex){
			throw new ViewException(ex);
		}
		return null;
		
	}
	
	private void setPossibleOptions(List<Answer> answerList){
		optionPanel.removeAll();
		options=new ButtonGroup();
		JRadioButton jr=null;
		for(Answer a:answerList){
			jr = new JRadioButton(a.getAnswer());
			optionPanel.add(jr);
			options.add(jr);
		}
	}
	
	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
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

	private void changeNextButton(){
		this.next.setText("Finish");
		this.next.setActionCommand("finish");
	}

	private void changeFinishButton(){
		this.next.setText("Next");
		this.next.setActionCommand("next");
	}
	
	
	private void reselectAnswer(Answer previousAnswer){
		if(previousAnswer != null){
		String answerText=previousAnswer.getAnswer();
		Enumeration<AbstractButton> AllRadios=options.getElements();
		while(AllRadios.hasMoreElements()){
			JRadioButton radio=(JRadioButton)AllRadios.nextElement(); 
			if(radio.getText().equals(answerText)){
				radio.setSelected(true);
				return;
				}
			}
		}
		
		
	}
	
}
