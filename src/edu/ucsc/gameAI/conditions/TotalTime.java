package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class TotalTime implements ICondition {

	Game game;
	int min, max;
	
	// Returns TRUE if total time played is between min/max inclusive
	// otherwise FALSE
	
	public TotalTime (Game game,int min,int max) {
		this.game = game;
		this.min = min;
		this.max = max;
	}
	
	public boolean test() {
		int current_time = game.getTotalTime();
		return (min <= current_time && max >= current_time);
	}

}
