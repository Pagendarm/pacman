package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class GhostEatScore implements ICondition {

	int min, max;
	
	// Returns TRUE if ghost eat score
	// is between min/max inclusive
	// otherwise FALSE
	
	public GhostEatScore (int min,int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) {
		int current_value = game.getGhostCurrentEdibleScore();
		return (min <= current_value && max >= current_value);
	}

}
