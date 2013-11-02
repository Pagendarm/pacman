package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Game;
import pacman.game.Constants.MOVE;

public class GoUpAction implements IAction, IBinaryNode {

public void doAction() {	} // Does nothing: required for interface
	
	public IAction makeDecision(Game game) {return this;}
	
	public MOVE getMove() {
		return MOVE.UP;
	}
	
    public MOVE getMove(Game game) { return getMove(); }
}
