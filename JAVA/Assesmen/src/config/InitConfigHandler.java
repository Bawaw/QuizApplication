package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class InitConfigHandler {
	private Properties table;
	private final String defaultPath="init.dat";
	private final int defaultEvaluationSize = 5;
	private final String defaultScoreBehaviour = "MaxScoreCalculator";
	private final String defaultQuestionSelectionbehaviour = "RandomQuestions";
	
	
	
	public InitConfigHandler() {
		this.setTable(new Properties());
	}

	
	
	public Properties getTable() {
		return table;
	}

	private void setTable(Properties table) {
		this.table = table;
	}
	
	
	public String getScoreBehaviour() throws ConfigException{
		String value=null;
		try{
			FileInputStream in = new FileInputStream(defaultPath);
			this.getTable().load(in);
			in.close();
			value = this.getTable().getProperty("scoreBehaviour");
		}
		catch (IOException ex){
			throw new ConfigException(ex);
		}
		if(value == null){
			value=this.defaultScoreBehaviour;
		}
			return value;
	}

	public void saveScoreBehaviour(String scoreBehaviour) throws ConfigException{
		try{
			FileOutputStream out = new FileOutputStream(defaultPath);
			this.getTable().setProperty("scoreBehaviour", scoreBehaviour);
			this.getTable().store(out,"Admin Settings");
			out.close();
		}
		catch (IOException ex){
			throw new ConfigException(ex);
			}
	}
	
	public String getQuestionSelectionBehaviour() throws ConfigException{
		String value=null;
		try{
			FileInputStream in = new FileInputStream(defaultPath);
			this.getTable().load(in);
			in.close();
			value = this.getTable().getProperty("questionBehaviour");
		}
		catch (IOException ex){
			throw new ConfigException(ex);
		}
		if(value == null){
			value=this.defaultQuestionSelectionbehaviour;
		}
			return value;
	}
	
	public void saveQuestionSelectionBehaviour(String questionSelectionBehaviour) throws ConfigException{
		try{
			FileOutputStream out = new FileOutputStream(defaultPath);
			this.getTable().setProperty("questionBehaviour", questionSelectionBehaviour);
			this.getTable().store(out,"Admin Settings");
			out.close();
		}
		catch (IOException ex){
			throw new ConfigException(ex);
			}
	}
	
	public int getDefaultEvaluationSize() throws ConfigException{
		String value=null;
		int output=this.defaultEvaluationSize;
		try{
			FileInputStream in = new FileInputStream(defaultPath);
			this.getTable().load(in);
			in.close();
			value = this.getTable().getProperty("evaluationSize");
		}
		catch (IOException ex){
			throw new ConfigException(ex);
		}
		if(value != null){
			output=Integer.parseInt(value);
		}
		
		return output;
	}
	
	public void saveDefaultEvaluationSize(int size) throws ConfigException{
		try{
			if(size <= 0){
				throw new ConfigException("Size > 1 !");
			}
			
			FileOutputStream out = new FileOutputStream(defaultPath);
			this.getTable().setProperty("evaluationSize", String.valueOf(size));
			this.getTable().store(out,"Admin Settings");
			out.close();
		}
		catch (IOException ex){
			throw new ConfigException(ex);
			}
	}
}