package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class NumberOfLiveRemaining implements ICondition {
	
	Game game;
	int min;
	int max;
	
	// Returns TRUE if value is between min/max inclusive
	// otherwise FALSE
	
	public NumberOfLiveRemaining(Game game, int min, int max)
	{
		this.game=game;
		this.min = min;
		this.max = max;
	}
	
	public boolean test() 
	{
		int value = game.getPacmanNumberOfLivesRemaining();
		return (value >=  min && value <= max);
	}
}
