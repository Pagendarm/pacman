package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;
import pacman.game.internal.Maze;
import pacman.game.internal.Node;

public class PillInRegion implements ICondition {
	
	Game game;
	int x1, y1,
        x2, y2;

	// Returns TRUE if a pill exists with [x1, y1], [x2, y2]
	// otherwise FALSE
	public PillInRegion(int x1, int y1, int x2, int y2);
	{
		this.game=game;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
	}
	
	public boolean test() 
	{
        Maze maze = game.getCurrentMaze();
        for(Node n : maze.graph) {
            if(n.pillIndex == -1) continue;
            if((n.x < x1 || n.x > x2) ||
               (n.y < y1 || n.y > y2)) continue;

            return true;
        }
		return false;
	}
}
