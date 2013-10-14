package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class PacmanWasEaten implements ICondition {
	
	Game game;
	int number_of_lives;
	
	// Returns TRUE if a Pacman was eaten since last test
	// otherwise FALSE
	public PacmanWasEaten(Game game)
	{
		this.game=game;
		this.number_of_lives = game.getPacmanNumberOfLivesRemaining();
	}
	
	public boolean test() 
	{
		int temp = number_of_lives;
		number_of_lives = game.getPacmanNumberOfLivesRemaining();
		return number_of_lives > temp;
	}
}
