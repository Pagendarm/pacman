package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class MazeIndex implements ICondition {

	int index;
	
	// Returns TRUE if maze index is equal to index
	// otherwise FALSE
	
	public MazeIndex (int index) {
		this.index = index;
	}
	
	public boolean test(Game game) {
		return (game.getMazeIndex() == index);
	}

}
