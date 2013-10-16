package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class CurrentGhostNodeIndex implements ICondition {
	
	GHOST ghost;
	int input;
	
	// Returns TRUE if value is equal to input
	// otherwise FALSE
	
	public CurrentGhostNodeIndex(GHOST ghost, int input)
	{
		this.ghost=ghost;
		this.input = input;
	}
	
	public boolean test(Game game) 
	{
		int value = game.getGhostCurrentNodeIndex(ghost);
		return (value == input);
	}
}
