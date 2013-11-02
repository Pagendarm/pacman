package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.*;
import pacman.game.Game;

public class AvoidPacmanPlusAction implements IBinaryNode, IAction {

	GHOST ghost;

	public AvoidPacmanPlusAction (GHOST ghost) {
		this.ghost = ghost;
	}

	public void doAction() {	} // Does nothing: required for interface

	public IAction makeDecision(Game game) {
		return this;}

	public MOVE getMove() { return MOVE.NEUTRAL;}

	public MOVE getMove(Game game) {
		int [] quad = {0,0,0,0};
		int x1,x2,y1,y2;
		y1 = x1 = 50; y2 = x2 = 100;

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

		int [] results = make_index_array(quad);	 
		int rand =  (int) Math.random()*3;
		
		int goto_index = results[rand];
		int gindex = game.getGhostCurrentNodeIndex(ghost);
		MOVE lm = game.getGhostLastMoveMade(ghost);
		DM dm = DM.EUCLID;
		return game.getNextMoveTowardsTarget(gindex, goto_index, lm,dm);
	}
	
	private int[] make_index_array (int[] quad ){
		
		int[] index_array = new int [3];
		int index = 0;
		
		for (int i =0; i<quad.length;i++){
			if (quad[i] == 1) index = i;
		}
		
		switch (index) {
		case 0:
			index_array[0] = 80;
			index_array[1]= 930;
			index_array[2] = 980;
			break;
		case 1:
			index_array[0] = 50;
			index_array[1]= 930;
			index_array[2] = 980;
			break;
		case 2:
			index_array[0] = 50;
			index_array[1]= 80;
			index_array[2] = 980;
			break;
		case 3:
			index_array[0] = 50;
			index_array[1]= 80;
			index_array[2] = 930;
			break;
		default: 
			index_array[0] = index_array[1]= index_array[2] = 550;
			break;
		}
		
		return index_array;
		
	}


}
