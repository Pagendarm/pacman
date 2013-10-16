package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class Score implements ICondition {

	Game game;
	int min, max;
	
	// Returns TRUE if score is between min/max inclusive
	// otherwise FALSE
	
	public Score (Game game,int min,int max) {
		this.game = game;
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) {
		int current_time = game.getScore();
		return (min <= current_time && max >= current_time);
	}

}
