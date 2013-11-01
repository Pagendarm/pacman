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

		int index = get_lowest_population_quad (game,ghost);

		int gindex = game.getGhostCurrentNodeIndex(ghost);
		MOVE lm = game.getGhostLastMoveMade(ghost);
		DM dm = DM.EUCLID;
		return game.getNextMoveTowardsTarget(gindex, index, lm,dm);
	}

	public MOVE getMove() { return MOVE.NEUTRAL;}

	// Cycles through 4 quadrants, finds one with least number of ghosts and pacman
	// and returns the index for the center of that quad

	/* quad 1 index = 50 | quad 2 index = 80
	 * ---------------------------------------
	 * quad 3 index = 930| quad 4 index = 980
	 */

	private int get_lowest_population_quad (Game game, GHOST ghost) {
		int x1,x2,y1,y2;
		y1 = x1 = 50; y2 = x2 = 100;
		int quad[] = {0,0,0,0};

		for(GHOST g : GHOST.values()) {
			if (g != ghost) { // Doesn't count itself
				int index = game.getGhostCurrentNodeIndex(g);
				int gx = game.getNodeXCood(index);
				int gy = game.getNodeYCood(index);

				// quad 1
				if ((gx >= 0) && (gx < x1) && (gy >=0) && (gy < y1 )) {
					quad[0] += 1;
				}
				// quad 2
				else if ((gx >= x1) && (gx < x2) && (gy >=0) && (gy < y1 )) {
					quad[1] += 1;
				}
				// quad 3
				else if ((gx >= 0) && (gx < x1) && (gy >=y1) && (gy < y2 )) {
					quad[2] += 1;
				}
				// quad 4
				else quad[3] += 1;

			}
		}
		
		// Pacman
		int index = game.getPacmanCurrentNodeIndex();
		int px = game.getNodeXCood(index);
		int py = game.getNodeYCood(index);

		// quad 1
		if ((px >= 0) && (px < x1) && (py >=0) && (py < y1 )) {
			quad[0] += 1;
		}
		// quad 2
		else if ((px >= x1) && (px < x2) && (py >=0) && (py < y1 )) {
			quad[1] += 1;
		}
		// quad 3
		else if ((px >= 0) && (px < x1) && (py >=y1) && (py < y2 )) {
			quad[2] += 1;
		}
		// quad 4
		else quad[3] += 1;

		int quad_n = index_of_max(quad) +1;
		
		switch (quad_n ) {
		case 1:
			return 50;
		case 2:
			return 80;
		case 3:
			return 930;
		case 4:
			return 980;
		default:
			return 550;
		}	
	}
	
	private int index_of_max (int[] array){
		int max = Integer.MIN_VALUE;
		int index = 0;
		for (int i=0; i < array.length;i++){
			if (array[i] > max) {
				max = array[i];
				index = i;
			}
		}
		return index;
	}

}
