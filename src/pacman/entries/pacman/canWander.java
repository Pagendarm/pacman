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


public class canWander implements ICondition
{
    Seek seek;

    public canWander(Seek seek)
    {
        this.seek = seek;
    }

    public boolean test(Game game)
    {
        return !seek.canMove;
    }
}
