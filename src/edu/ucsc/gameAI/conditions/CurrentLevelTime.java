package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class CurrentLevelTime implements ICondition {

	int min, max;
	
	// Return TRUE if current time is between min/max inclusive
	// otherwise FALSE
	
	public CurrentLevelTime (int min,int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) {
		int current_time = game.getCurrentLevelTime();
		return (min <= current_time && max >= current_time);
	}

}
