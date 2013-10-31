package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.*;
import pacman.game.Game;

public class SeekSelfAction implements IBinaryNode, IAction {
	
	GHOST ghost;
	
	public SeekSelfAction (GHOST ghost) {
		this.ghost = ghost;
	}
	
	public void doAction() {	} 
	
	public IAction makeDecision(Game game) {
		return this;}

	public MOVE getMove(Game game) {
		int gindex = game.getGhostCurrentNodeIndex(ghost);
		MOVE lm = game.getGhostLastMoveMade(ghost);
		DM dm = DM.EUCLID;
		return game.getNextMoveTowardsTarget(gindex, gindex, lm,dm);
	}
	
	public MOVE getMove() { return MOVE.NEUTRAL;}
	
}
