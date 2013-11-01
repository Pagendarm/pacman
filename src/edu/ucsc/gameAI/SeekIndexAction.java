package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.*;
import pacman.game.Game;

public class SeekIndexAction implements IBinaryNode, IAction {
	
	GHOST ghost;
	int index;
	
	public SeekIndexAction (GHOST ghost, int index) {
		this.ghost = ghost;
		this.index = index;
	}
	
	public void doAction() {	} 
	
	public IAction makeDecision(Game game) {
		return this;}

	public MOVE getMove(Game game) {
		int gindex = game.getGhostCurrentNodeIndex(ghost);
		MOVE lm = game.getGhostLastMoveMade(ghost);
		DM dm = DM.PATH;
		return game.getNextMoveTowardsTarget(gindex, index, lm,dm);
	}
	
	public MOVE getMove() { return MOVE.NEUTRAL;}
	
}
