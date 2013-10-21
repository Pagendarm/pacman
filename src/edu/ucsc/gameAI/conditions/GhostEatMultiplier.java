package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class GhostEatMultiplier implements ICondition {

	int min, max;
	
	// Returns TRUE if game eat multiplier (ghost score / 200)
	// is between min/max
	// otherwise FALSE
	
	public GhostEatMultiplier (int min,int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) {
		int current_value = game.getGhostCurrentEdibleScore();
		int multiplier = current_value / 200;
		return (min < multiplier && max > multiplier);
	}

}
