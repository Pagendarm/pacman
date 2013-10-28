package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class IsPowerPillStillAvailable implements ICondition {
	
	int ppill_index;
	
	// Returns TRUE if a pill at pill index is available 
	// otherwise FALSE
	public IsPowerPillStillAvailable( int ppill_index)
	{
		this.ppill_index = ppill_index;
	}
	
	public boolean test(Game game) 
	{
        return game.isPowerPillStillAvailable(ppill_index);
	}
}
