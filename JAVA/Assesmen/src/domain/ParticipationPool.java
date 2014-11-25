package domain;

import java.util.ArrayList;

public class ParticipationPool {
	ArrayList<Participation> participationPool;

	public ParticipationPool(ArrayList<Participation> participationPool) {
			setParticipationPool(participationPool);
	}

	public ArrayList<Participation> getParticipationPool() {
		return participationPool;
	}

	private void setParticipationPool(ArrayList<Participation> participationPool) {
		this.participationPool = participationPool;
	}
	
	private void addParticipationPool(Participation participation){
		if(participationPool.contains(participation))
			participationPool.add(participation);
	}

}
