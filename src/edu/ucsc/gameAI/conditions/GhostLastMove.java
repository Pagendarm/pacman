package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class GhostLastMove implements ICondition {
	
	Game game;
	GHOST ghost;
	MOVE move;
	
	// Returns TRUE if GHOST last MOVE was move
	// otherwise FALSE
	
	public GhostLastMove(Game game, GHOST ghost, MOVE move)
	{
		this.game = game;
		this.ghost = ghost;
		this.move = move;
	}
	
	public boolean test(Game game) 
	{
		return (game.getGhostLastMoveMade(ghost) == move);
	}
}
