package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class LairTime implements ICondition {
	
	GHOST ghost;
	int min;
	int max;
	
	// Returns TRUE if value is between min/max inclusive
	// otherwise FALSE
	
	public LairTime( GHOST ghost, int min, int max)
	{
		this.ghost=ghost;
		this.min = min;
		this.max = max;
	}
	
	public boolean test(Game game) 
	{
		int value = game.getGhostLairTime(ghost);
		return (value >=  min && value <= max);
	}
}
