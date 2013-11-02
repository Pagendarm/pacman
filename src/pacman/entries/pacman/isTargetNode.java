package pacman.entries.pacman;

import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.internal.Dijkstra;
import pacman.game.internal.DijNode;
import pacman.game.internal.Maze;
import pacman.game.internal.Node;
import edu.ucsc.gameAI.fsm.*;
import edu.ucsc.gameAI.ICondition;

import static pacman.game.Constants.*;

public class isTargetNode implements ICondition
{
    int node = -1;
    int target = -1;

    public isTargetNode(int node, int target)
    {
        this.node = node;
        this.target = target;
    }

    public boolean test(Game game)
    {
        boolean isTarget = game.isJunction(node);

        if(node < 0) return true;
        else if(node == target) return true;
        return isTarget;
        /*

        //if(game.getLevel() == 1 && node == )
        //return true;

        int[] powerIndices = game.getPowerPillIndices();
        for(int i : powerIndices) {
        if(node == i) {
        isTarget = true;
        break;
        }
        }

        return isTarget;
        */
    }
}
