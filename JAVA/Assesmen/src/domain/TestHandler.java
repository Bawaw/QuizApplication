package domain;

public class TestHandler {
	private Evaluation test;
	private final int SCOREON=20;
	
	public TestHandler(Evaluation test) throws DomainException {
		this.setTest(test);
	}

	public Evaluation getTest() {
		return test;
	}

	private void setTest(Evaluation test) throws DomainException {
		if(test==null){
			throw new DomainException("Test cannot be null");
		}
		this.test = test;
	}

	public int getScoreOn(){
		return this.SCOREON;
	}
}
