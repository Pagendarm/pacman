package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class IsPowerPillStillAvailable implements ICondition {
	
	Game game;
	int ppill_index;
	
	// Returns TRUE if a pill at pill index is available 
	// otherwise FALSE
	public IsPowerPillStillAvailable(Game game, int ppill_index)
	{
		this.game=game;
		this.ppill_index = ppill_index;
	}
	
	public boolean test() 
	{
		int ppill_indices[] = game.getPowerPillIndices();
		for (int i:ppill_indices) {
			if (i == ppill_index) return true;
		}
		return false;
	}
}
