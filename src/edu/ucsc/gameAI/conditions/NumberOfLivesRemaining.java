package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class NumberOfLivesRemaining implements ICondition {
	
	int max;
	int min;
	
	// Returns TRUE if value is equal to input
	// otherwise FALSE
	
	public NumberOfLivesRemaining( int min, int max)
	{
		this.max = max;
		this.min = min;
	}
	
	public boolean test(Game game) 
	{
		int value = game.getPacmanNumberOfLivesRemaining();
		return (value >= min && value <= max);
	}
}
