package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class TimeOfLastGlobalReversal implements ICondition {

	int min, max;
	
	// Returns TRUE if last global reversal for ghosts inclusive
	// is between min/max
	// otherwise FALSE
	
	public TimeOfLastGlobalReversal (int min,int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) {
		int current_time = game.getTimeOfLastGlobalReversal();
		return (min <= current_time && max >= current_time);
	}

}
