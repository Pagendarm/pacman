package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class PowerPillWasEaten implements ICondition {
	
	Game game;
	
	// Returns TRUE if a PowerPill was eaten since last test
	// otherwise FALSE
	public PowerPillWasEaten(Game game)
	{
		this.game=game;
	}
	
	public boolean test() 
	{
		return game.wasPowerPillEaten();
	}
}
