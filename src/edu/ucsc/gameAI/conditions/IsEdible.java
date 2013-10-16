package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class IsEdible implements ICondition {
	
	GHOST ghost;
	
	// Returns TRUE if ghost is edible
	public IsEdible( GHOST ghost)
	{
		this.ghost=ghost;
	}
	
	public boolean test(Game game) 
	{
		return game.isGhostEdible(ghost);
	}
}
