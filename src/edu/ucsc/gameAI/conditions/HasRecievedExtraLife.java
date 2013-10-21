package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class HasRecievedExtraLife implements ICondition {
	
	Game game;
	int number_of_lives;
	
	// Returns TRUE if Pacman received new extra life
	// otherwise FALSE
	
	public HasRecievedExtraLife(Game game)
	{
		this.game = game;
		number_of_lives = game.getPacmanNumberOfLivesRemaining();
	}
	
	public boolean test(Game game) 
	{
		int temp = number_of_lives;
		number_of_lives = game.getPacmanNumberOfLivesRemaining();
		return (number_of_lives > temp);
	}
}
