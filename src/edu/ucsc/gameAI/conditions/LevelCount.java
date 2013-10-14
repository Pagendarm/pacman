package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class LevelCount implements ICondition {

	Game game;
	int level;
	
	// Returns TRUE if level count is between min/max
	// otherwise FALSE
	public LevelCount (Game game,int level) {
		this.game = game;
		this.level = level;
	}
	
	public boolean test() {
		return (game.getCurrentLevel() == level);
	}

}
