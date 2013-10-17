package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;
import pacman.game.internal.Maze;
import pacman.game.internal.Node;

public class GhostInRegion implements ICondition {
	
	Game game;
	int x1, y1,
        x2, y2;

	// Returns TRUE if a pill exists with [x1, y1], [x2, y2]
	// otherwise FALSE
	public GhostInRegion(int x1, int y1, int x2, int y2);
	{
		this.game=game;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
	}
	
	public boolean test() 
	{
        for(GHOST g : GHOST.values()) {
            int ghostNode = game.getGhostCurrentNodeIndex(g);
            int nodeX = game.getNodeXCoord(pacmanNode);
            int nodeY = game.getNodeYCoord(pacmanNode);

            if((nodeX < x1 || nodeX > x2) ||
                    (nodeY < y1 || nodeY > y2)) continue;

            return true;
        }
        return false;
    }
}
