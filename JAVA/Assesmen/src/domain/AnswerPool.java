package domain;

import java.util.HashSet;

public class AnswerPool {
	private HashSet<Answer> answers;

	public AnswerPool() {
		answers = new HashSet<Answer>();
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
