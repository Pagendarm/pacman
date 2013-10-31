package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.*;
import pacman.game.Game;

public class SpreadoutAction implements IBinaryNode, IAction {
	
	GHOST ghost;
	
	public SpreadoutAction (GHOST ghost) {
		this.ghost = ghost;
	}
	
	public void doAction() {	} // Does nothing: required for interface
	
	public IAction makeDecision(Game game) {
		return this;}

	public MOVE getMove(Game game) {
		GHOST notg;
		if (ghost==GHOST.PINKY) notg = GHOST.BLINKY;
		else if (ghost==GHOST.BLINKY) notg = GHOST.SUE;
		else if (ghost==GHOST.SUE) notg = GHOST.INKY;
		else notg = GHOST.PINKY;
		
		int gindex = game.getGhostCurrentNodeIndex(ghost);
		int notgindex = game.getGhostCurrentNodeIndex(notg);
		MOVE lm = game.getGhostLastMoveMade(ghost);
		DM dm = DM.EUCLID;
		return game.getNextMoveAwayFromTarget(gindex, notgindex, lm,dm);
	}
	
	public MOVE getMove() { return MOVE.NEUTRAL;}

}
