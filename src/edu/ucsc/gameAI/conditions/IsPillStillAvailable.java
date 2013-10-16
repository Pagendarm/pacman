package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class IsPillStillAvailable implements ICondition {
	
	Game game;
	int pill_index;
	
	// Returns TRUE if a pill at pill index is available 
	// otherwise FALSE
	public IsPillStillAvailable(Game game, int pill_index)
	{
		this.game=game;
		this.pill_index = pill_index;
	}
	
	public boolean test(Game game) 
	{
		int pill_indices[] = game.getActivePillsIndices();
		for (int i:pill_indices) {
			if (i == pill_index) return true;
		}
		return false;
	}
}
