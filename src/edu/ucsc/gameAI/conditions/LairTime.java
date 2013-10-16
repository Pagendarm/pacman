package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class LairTime implements ICondition {
	
	Game game;
	GHOST ghost;
	int min;
	int max;
	
	// Returns TRUE if value is between min/max inclusive
	// otherwise FALSE
	
	public LairTime(Game game, GHOST ghost, int min, int max)
	{
		this.game=game;
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
