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

public class Wander implements IAction
{
    private Game game;
    private Maze maze;

    MOVE lastMove = MOVE.NEUTRAL;

    public DijNode[] graph;

    int[] gIndex = new int[4];
    int target = -1;

    public Wander(Game game, Maze maze, DijNode[] graph)
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

        isTargetNode isTarget = new isTargetNode(pacmanIndex, -1);
        if(isTarget.test(game)) {
            //if(true) {
            int index = 0;
            for(GHOST g : GHOST.values())
                gIndex[index++] = game.getGhostCurrentNodeIndex(g);

            int easiness = Integer.MIN_VALUE;
            int pathScore = 0;

            boolean foundPath = false;
            boolean notCornered = false;

            for(int i = 0; i < graph[pacmanIndex].neighbors.length; i++) {
                int last = pacmanIndex;
                int curr = graph[last].neighbors[i];

                boolean valid = true;
                boolean powerPill = false;

                int score = 0;

                for(int j : gIndex) {
                    if(j == curr)
                        valid = false;
                }

                while(valid) {
                    isTargetNode itn = new isTargetNode(curr, -1);
                    if(!itn.test(game)) break;

                    for(int j = 0; j < graph[curr].neighbors.length; j++) {
                        if(graph[curr].neighbors[j] != last) {
                            last = curr;
                            curr = graph[curr].neighbors[j];
                            break;
                        }
                    }

                    int pillIndex = game.getPillIndex(curr);
                    if(pillIndex != -1 && game.isPillStillAvailable(pillIndex)) score++;

                    for(int j : gIndex) {
                        if(j == curr) {
                            valid = false;
                        }
                    }

                    if(game.getPowerPillIndex(curr) != -1)
                        powerPill = true;
                }

                isCornered iscornered = new isCornered(graph, graph[curr], last, 5);
                boolean cornered = iscornered.test(game);
                if(graph[curr].dist <= 1) valid = false;

                if(valid) {
                    int n = graph[pacmanIndex].neighbors[i];
                    MOVE m = game.getNextMoveTowardsTarget(pacmanIndex, n, DM.PATH);
                    if(m.opposite() == lastMove) {
                        if(graph[curr].dist > 0) {
                            graph[curr].dist -= 100;
                            if(graph[curr].dist < 1) graph[curr].dist = 1;
                        }
                    }
                    if(!notCornered || (!cornered && notCornered)) {
                        if((!foundPath || !powerPill)) {
                            if((graph[curr].dist > easiness) || (score > pathScore)) {
                                foundPath = true;
                                notCornered = !cornered;
                                pathScore = score;

                                easiness = (int)Math.round(graph[curr].dist);
                                target = curr;

                                if(powerPill)
                                    easiness = 1;

                                lastMove = m;
                            }
                        }
                    }
                }
            }
        }

        if(game.getCurrentMaze().graph[pacmanIndex].allNeighbourhoods.get(lastMove) == null)
            lastMove = MOVE.NEUTRAL;

        if(target != -1)
            move = game.getApproximateNextMoveTowardsTarget(pacmanIndex, target, lastMove, DM.PATH);

        lastMove = move;
        return move;
    }
}
