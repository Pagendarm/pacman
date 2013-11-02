package pacman.entries.pacman;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import pacman.controllers.Controller;
import pacman.game.Game;
import pacman.game.internal.Dijkstra;
import pacman.game.internal.DijNode;
import pacman.game.internal.Maze;
import pacman.game.internal.Node;
import edu.ucsc.gameAI.fsm.*;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

import static pacman.game.Constants.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class MyPacMan extends Controller<MOVE>
{
    private Game game;
    private Maze maze;

    MOVE lastMove = MOVE.NEUTRAL;

    public Dijkstra dijGhost, dijPacman;
    public DijNode[] timeGraph = null;
    boolean init = false;

    Seek seek;
    Wander wander;
    State stateSeek;
    State stateWander;
    Transition transSeek;
    Transition transWander;
    Collection<ITransition> seekList;
    Collection<ITransition> wanderList;
    StateMachine fsm;

    int[] gIndex = new int[4];

    int target = -1;

	public MOVE getMove(Game game, long timeDue) 
	{
        if(!init || (maze != game.getCurrentMaze())) {
            init(game);
            setup(game);
        }

        MOVE move = MOVE.NEUTRAL;
        int pacmanIndex = game.getPacmanCurrentNodeIndex();

        IState state = fsm.getCurrentState();
        IAction act = state.getAction();

        isTargetNode isTarget = new isTargetNode(pacmanIndex, -1);
        if(isTarget.test(game)) {
            this.game = game;
            maze = game.getCurrentMaze();

            dijGhost.resetGraph();

            int index = 0;
            for(GHOST g : GHOST.values()) {
                gIndex[index] = game.getGhostCurrentNodeIndex(g);

                dijGhost.softResetGraph();

                if(act.getClass() == Seek.class) {
                    dijGhost.computeGraph(gIndex[index], false, game);
                } else {
                    dijGhost.computeGraph(gIndex[index], true, game);
                }
                index++;
            }

            dijPacman.resetGraph();
            dijPacman.computeGraph(pacmanIndex, false, game);

            timeGraph = concatGraphs(dijGhost.graph, dijPacman.graph);

            //setup(game);

            seek.update(game, maze, timeGraph);
            if(act.getClass() == Wander.class) {
                wander.update(game, maze, timeGraph);
            }
        }

        if(act.getClass() == Wander.class) {
            move = wander.getMove();
        } else {
            move = seek.getMove();
        }
        System.out.println("" + move);
        /*
           for(Iterator<IAction> iterator = actions.iterator(); iterator.hasNext();)
           {
           IAction act = iterator.next();
           if(act.getClass() == Seek.class || act.getClass() == Wander.class)
           move = act.getMove();
           }
           */

        return move;
    }

    private void init(Game game)
    {
        maze = game.getCurrentMaze();

        dijGhost = new Dijkstra();
        dijPacman = new Dijkstra();

        dijGhost.createGraph(maze.graph);
        dijPacman.createGraph(maze.graph);
    }

    private void setup(Game game)
    {
        maze = game.getCurrentMaze();

        stateSeek = new State();
        seek = new Seek(game, maze, timeGraph);
        stateSeek.setAction(seek);
        stateWander = new State();
        wander = new Wander(game, maze, timeGraph);
        stateWander.setAction(wander);

        transSeek = new Transition();
        transSeek.setCondition(new canSeek(seek));
        transSeek.setTargetState(stateSeek);

        seekList = new LinkedList<ITransition>();
        seekList.add(transSeek);

        transWander = new Transition();
        transWander.setCondition(new canWander(seek));
        transWander.setTargetState(stateWander);

        wanderList = new LinkedList<ITransition>();
        wanderList.add(transWander);

        stateSeek.setTransitions(wanderList);
        stateWander.setTransitions(seekList);

        fsm = new StateMachine();
        fsm.setCurrentState(stateSeek);

        init = true;
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
