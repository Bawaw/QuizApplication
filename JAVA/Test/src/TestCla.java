
public class TestCla {
	public String top;
	
	public TestCla(){
		changeTop(top);
	}
	
	public void printTop(){
		System.out.println(top);
	}
	
	public void changeTop(String reference){
		reference="loly";
	}
	
}
