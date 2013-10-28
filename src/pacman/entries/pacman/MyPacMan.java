package pacman.entries.pacman;

import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.internal.Dijkstra;
import pacman.game.internal.DijNode;

import static pacman.game.Constants.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class MyPacMan extends Controller<MOVE>
{
    public Dijkstra dijGhost, dijPacman;
    public DijNode[] timeGraph = null;
    boolean init = false;

    int target = -1;

	public MOVE getMove(Game game, long timeDue) 
	{
        if(!init) {
            dijGhost = new Dijkstra();
            dijPacman = new Dijkstra();

            dijGhost.createGraph(game.getCurrentMaze().graph);
            dijPacman.createGraph(game.getCurrentMaze().graph);

            init = true;
        }

        MOVE move = MOVE.NEUTRAL;
        int pacmanIndex = game.getPacmanCurrentNodeIndex();

        int current = game.getPacmanCurrentNodeIndex();
        if(game.isJunction(current)) {
        //if(true) {
            dijGhost.resetGraph();
            int gIndex[] = new int[4];
            int index = 0;
            for(GHOST g : GHOST.values()) {
                gIndex[index] = game.getGhostCurrentNodeIndex(g);

                dijGhost.softResetGraph();
                dijGhost.computeGraph(gIndex[index++], true, game);
            }

            dijPacman.resetGraph();
            dijPacman.computeGraph(pacmanIndex, false, game);

            timeGraph = concatGraphs(dijPacman.graph, dijGhost.graph);

            int easiness = Integer.MIN_VALUE;

            for(int i = 0; i < timeGraph[pacmanIndex].neighbors.length; i++) {
                int lastIndex = pacmanIndex;
                int neighborIndex = timeGraph[lastIndex].neighbors[i];
                boolean valid = true;

                while(valid && timeGraph[neighborIndex].neighbors.length < 3) {
                    for(int j = 0; j < timeGraph[neighborIndex].neighbors.length; j++) {
                        if(timeGraph[neighborIndex].neighbors[j] != lastIndex) {
                            lastIndex = neighborIndex;
                            neighborIndex = timeGraph[neighborIndex].neighbors[j];
                            break;
                        }
                    }

                    for(int j = 0; j < 4; j++) {
                        if(gIndex[j] == neighborIndex) {
                            valid = false;
                        }
                    }
                }

                boolean cornered = cornered(timeGraph[neighborIndex], lastIndex);

                if(valid && ((timeGraph[neighborIndex].dist > easiness) || !cornered)) {
                    easiness = (int)Math.round(timeGraph[neighborIndex].dist);
                    if(!cornered) easiness = Integer.MAX_VALUE;
                    target = neighborIndex;
                }
            }

        }

        if(target != -1)
            move = game.getNextMoveTowardsTarget(pacmanIndex, target, DM.PATH);

		return move;
	}

    private boolean cornered(DijNode n, int lastIndex)
    {
        for(int i : n.neighbors) {
            if((timeGraph[i].dist > n.dist))
                return false;
        }
        return true;
    }

    private DijNode[] concatGraphs(DijNode[] g1, DijNode[] g2)
    {
        int size = g1.length;
        DijNode[] newGraph = new DijNode[size];

        for(int i = 0; i < size; i++) {
            newGraph[i] = new DijNode(i, g2[i].dist - g1[i].dist, g1[i].x, g1[i].y);
            newGraph[i].neighbors = g1[i].neighbors;
        }

        return newGraph;
    }
}
