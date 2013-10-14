package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.MOVE;

public class GoRightAction implements IAction, IBinaryNode {
	
public void doAction() {	} // Does nothing: required for interface
	
	public IAction makeDecision() {return this;}
	
	public MOVE getMove() {
		return MOVE.RIGHT;
	}
	
}
