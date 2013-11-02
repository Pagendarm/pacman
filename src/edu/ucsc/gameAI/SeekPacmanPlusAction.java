package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.*;
import pacman.game.Game;

public class SeekPacmanPlusAction implements IBinaryNode, IAction {
	
	GHOST ghost;
	
	public SeekPacmanPlusAction (GHOST ghost) {
		this.ghost = ghost;
	}
	
	public void doAction() {	} // Does nothing: required for interface
	
	public IAction makeDecision(Game game) {
		return this;}


	public MOVE getMove(Game game) {
		MOVE lmp = game.getPacmanLastMoveMade();
		int pindex = game.getPacmanCurrentNodeIndex();
		DM dm = DM.EUCLID;
		int gindex = game.getGhostCurrentNodeIndex(ghost);
		MOVE lm = game.getGhostLastMoveMade(ghost);
		
		double dist = game.getDistance(gindex, pindex, lm, dm);
		
		if (dist > 20) {
		switch(lmp) {
			case UP: 
				if(pindex > 100) pindex -= 100;
				break;
			case DOWN: 
				if(pindex < 900) pindex += 100;
				break;
			case LEFT: 
				if(pindex > 30) pindex -= 30;
				break;
			case RIGHT: 
				if(pindex < 970) pindex += 30;
				break;
			default:
				break;
			}
		}
		else dm = DM.MANHATTAN;
		return game.getNextMoveTowardsTarget(gindex, pindex, lm,dm);
	}
	
	public MOVE getMove() { return MOVE.NEUTRAL;}
	
}
