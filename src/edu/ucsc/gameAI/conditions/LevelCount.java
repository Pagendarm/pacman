package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class LevelCount implements ICondition {

	int level;
	
	// Returns TRUE if level count is between min/max
	// otherwise FALSE
	public LevelCount (int level) {
		this.level = level;
	}
	
	public boolean test(Game game) {
		return (game.getCurrentLevel() == level);
	}

}
