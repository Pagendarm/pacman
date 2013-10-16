package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class CurrentPacmanNodeIndex implements ICondition {
	
	Game game;
	int num;
	
	// Returns TRUE if value is equal to num
	// otherwise FALSE
	
	public CurrentPacmanNodeIndex(Game game, int num)
	{
		this.game=game;
		this.num = num;
	}
	
	public boolean test(Game game) 
	{
		int value = game.getPacmanCurrentNodeIndex();
		return (value == num);
	}
}
