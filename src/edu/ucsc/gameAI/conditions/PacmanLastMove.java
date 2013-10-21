package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class PacmanLastMove implements ICondition {
	
	MOVE move;
	
	// Returns TRUE if Pacman last MOVE was move
	// otherwise FALSE
	
	public PacmanLastMove( MOVE move)
	{
		this.move = move;
	}
	
	public boolean test(Game game) 
	{
		return (game.getPacmanLastMoveMade() == move);
	}
}
