package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class GhostEaten implements ICondition {
	
	GHOST ghost;
	
	// Returns TRUE if the ghost was eaten
	// othewise FALSE
	public GhostEaten(GHOST ghost)
	{
		this.ghost=ghost;
	}
	
	public boolean test(Game game) 
	{
		return game.wasGhostEaten(ghost);
	}
}
