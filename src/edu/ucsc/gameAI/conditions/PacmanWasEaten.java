package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class PacmanWasEaten implements ICondition {
	
	// Returns TRUE if a Pacman was eaten since last test
	// otherwise FALSE
	public PacmanWasEaten()
	{}
	
	public boolean test(Game game) 
	{
		return game.wasPacManEaten();
	}
}
