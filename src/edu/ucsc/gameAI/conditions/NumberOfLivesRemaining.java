package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class NumberOfLivesRemaining implements ICondition {
	
	int input;
	
	// Returns TRUE if value is equal to input
	// otherwise FALSE
	
	public NumberOfLivesRemaining( int input)
	{
		this.input = input;
	}
	
	public boolean test(Game game) 
	{
		int value = game.getPacmanNumberOfLivesRemaining();
		return (value == input);
	}
}
