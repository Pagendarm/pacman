package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class NoPowerPillAvailable implements ICondition {

	int ppill_index;

	// Returns TRUE if a power pill is available 
	// otherwise FALSE
	public NoPowerPillAvailable()
	{
	}

	public boolean test(Game game) 
	{
		int[] pp = game.getActivePillsIndices();
		for (int i=0;i<4;i++){
			if (game.isPowerPillStillAvailable(pp[i])) return false;
		}
		return true;
	}
}
