package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class CurrentLevelTime implements ICondition {

	Game game;
	int min, max;
	
	// Return TRUE if current time is between min/max inclusive
	// otherwise FALSE
	
	public CurrentLevelTime (Game game,int min,int max) {
		this.game = game;
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) {
		int current_time = game.getCurrentLevelTime();
		return (min <= current_time && max >= current_time);
	}

}
