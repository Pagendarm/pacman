package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class PillWasEaten implements ICondition {
	
	
	// Returns TRUE if a Pill was eaten since last test
	// otherwise FALSE
	public PillWasEaten(){}
	
	public boolean test (Game game) 
	{
		return game.wasPillEaten();
	}

}
