package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class LastMove implements ICondition {
	
	Game game;
	GHOST ghost;
	MOVE move;
	
	// Returns TRUE if GHOST last MOVE was move
	// otherwise FALSE
	
	public LastMove(Game game, GHOST ghost, MOVE move)
	{
		this.game = game;
		this.ghost = ghost;
		this.move = move;
	}
	
	public boolean test() 
	{
		return (game.getGhostLastMoveMade(ghost) == move);
	}
}
