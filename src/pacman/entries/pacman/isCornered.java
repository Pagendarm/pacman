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

public class isCornered implements ICondition
{
    DijNode[] graph;
    DijNode n;
    int lastIndex;
    int rec;

    public isCornered(DijNode[] graph, DijNode n, int lastIndex, int rec)
    {
        this.graph = graph;
        this.n = n;
        this.lastIndex = lastIndex;
        this.rec = rec;
    }

    public boolean test(Game game)
    {
        if(rec <= 0) return true;
        if(n.dist < 1) return true;

        int[] gIndex = new int[4];
        int index = 0;
        for(GHOST g : GHOST.values())
            gIndex[index++] = game.getGhostCurrentNodeIndex(g);

        for(int i : n.neighbors) {
            if((graph[i].dist >= n.dist))
                return false;
            else if(i != lastIndex) {
                int curr = i, last = n.index;
                boolean valid = graph[i].dist > 0;

                while(valid) {
                    for(int j = 0; j < graph[curr].neighbors.length; j++) {
                        if(graph[curr].neighbors[j] != last) {
                            last = curr;
                            curr = graph[curr].neighbors[j];
                            break;
                        }
                    }

                    for(int j : gIndex) {
                        if(j == curr) {
                            valid = false;
                        }
                    }

                    if(graph[curr].dist < 1) valid = false;

                    isTargetNode itn = new isTargetNode(curr, -1);
                    if(itn.test(game)) {
                        isCornered cornered = new isCornered(graph, graph[curr], last, rec-1);
                        if(!cornered.test(game)) return false;
                    }
                }
            }
        }
        return true;
    }
}
