package domain;

public interface Subject {
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void nodifyObserver(Object args);
}
