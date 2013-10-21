package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class TotalTime implements ICondition {

	int min, max;
	
	// Returns TRUE if total time played is between min/max inclusive
	// otherwise FALSE
	
	public TotalTime (int min,int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) {
		int current_time = game.getTotalTime();
		return (min <= current_time && max >= current_time);
	}

}
