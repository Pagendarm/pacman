package pacman.entries.pacman;

import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.internal.Dijkstra;
import pacman.game.internal.DijNode;
import pacman.game.internal.Maze;
import pacman.game.internal.Node;
import edu.ucsc.gameAI.fsm.*;
import edu.ucsc.gameAI.IAction;

import static pacman.game.Constants.*;

public class Seek implements IAction
{
    private Game game;
    private Maze maze;

    public DijNode[] graph;

    int[] gIndex = new int[4];
    int target = -1;

    boolean canMove = true;

    public Seek(Game game, Maze maze, DijNode[] graph)
    {
        this.game = game;
        this.graph = graph;
        this.maze = maze;
    }

    public void update(Game game, Maze maze, DijNode[] graph)
    {
        this.game = game;
        this.graph = graph;
        this.maze = maze;
    }

    public void doAction() { };

    public MOVE getMove() { return getMove(this.game); };
    public MOVE getMove(Game game)
    {
        MOVE move = MOVE.NEUTRAL;
        int pacmanIndex = game.getPacmanCurrentNodeIndex();

        isTargetNode isTarget = new isTargetNode(pacmanIndex, target);
        if(isTarget.test(game)) {
            //if(true) {
            int index = 0;
            for(GHOST g : GHOST.values())
                gIndex[index++] = game.getGhostCurrentNodeIndex(g);

            int easiness = Integer.MIN_VALUE;
            int pathScore = 0;

            boolean foundPath = false;
            boolean notCornered = false;

            for(int i = graph.length - 1; i >= 0; i--) {
                if(graph[i].dist > 1) {
                    int pillIndex = game.getPillIndex(i);
                    if(pillIndex != -1 && game.isPillStillAvailable(pillIndex)) {
                        isCornered cornered = new isCornered(graph, graph[i], -1, 5);
                        if(cornered.test(game)) {
                            target = i;
                            foundPath = true;
                            break;
                        }
                    }
                }
            }

            canMove = foundPath;
        }

        move = game.getNextMoveTowardsTarget(pacmanIndex, target, DM.PATH);
        return move;
    }
}
