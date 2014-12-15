package domain;

import java.util.LinkedList;

public class ParticipationPool {
	LinkedList<Participation> participationPool;

	public ParticipationPool(LinkedList<Participation> participationPool) {
			setParticipationPool(participationPool);
	}

	public LinkedList<Participation> getParticipationPool() {
		return participationPool;
	}

	private void setParticipationPool(LinkedList<Participation> participationPool) {
		this.participationPool = participationPool;
	}
	
	public void addParticipation(Participation participation){
		if(!participationPool.contains(participation))
			participationPool.add(0,participation);
	}
}
