package domain;

import java.util.HashSet;

public class AnswerPool {
	private static AnswerPool singleton;
	private HashSet<Answer> answers;

	public AnswerPool() {
		answers = new HashSet<Answer>();
	}

	public synchronized static AnswerPool getInstance() {
		if (singleton == null) {
			singleton = new AnswerPool();
		}
		return singleton;
	}

	public HashSet<Answer> getAnswers() {
		return answers;
	}

	private void setAnswers(HashSet<Answer> answers) {
		this.answers = answers;
	}

	public void AddAnswer(Answer answer){
		if (!answers.contains(answer))
			this.getAnswers().add(answer);
	}

	public void removeAnswer(Answer answer){
		if (answers.contains(answer))
			this.getAnswers().remove(answer);
	}

}