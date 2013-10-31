package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.*;
import pacman.game.Game;

public class AvoidPacmanAction implements IBinaryNode, IAction {
	
	GHOST ghost;
	
	public AvoidPacmanAction (GHOST ghost) {
		this.ghost = ghost;
	}
	
	public void doAction() {	} // Does nothing: required for interface
	
	public IAction makeDecision(Game game) {
		return this;}

	public MOVE getMove() { return MOVE.NEUTRAL;}
	
	public MOVE getMove(Game game) {
		int pindex = game.getPacmanCurrentNodeIndex();
		int gindex = game.getGhostCurrentNodeIndex(ghost);
		MOVE lm = game.getGhostLastMoveMade(ghost);
		DM dm = DM.EUCLID;
		return game.getNextMoveAwayFromTarget(gindex, pindex, lm,dm);
	}
	
	
}
