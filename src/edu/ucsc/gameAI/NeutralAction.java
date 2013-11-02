package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class NeutralAction implements IAction, IBinaryNode {

	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
	public IAction makeDecision(Game game) {
		return this;
	}

	public MOVE getMove() {
		return MOVE.NEUTRAL;
	}
	
	public MOVE getMove(Game game) {
		return MOVE.NEUTRAL;
	}
}