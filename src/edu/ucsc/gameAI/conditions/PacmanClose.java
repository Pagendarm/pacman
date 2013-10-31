package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.*;
import pacman.game.Game;

public class PacmanClose implements ICondition {
	
	GHOST ghost;
	int radius;
	DM dm = DM.EUCLID;

	// Returns TRUE if a pacman outside r of ghost
	// otherwise FALSE
	public PacmanClose (GHOST g,int r) {
		ghost = g;
		radius = r;
	}
	
	public boolean test(Game game) 
	{
		int pindex = game.getPacmanCurrentNodeIndex();
		int gindex = game.getGhostCurrentNodeIndex(ghost);
        double dist   = game.getDistance(gindex, pindex, dm);

        return (dist < radius);
	}
}
