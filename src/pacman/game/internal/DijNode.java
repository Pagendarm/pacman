package pacman.game.internal;

import pacman.game.Game;
import pacman.game.Constants.MOVE;

public class DijNode implements Comparable<DijNode>
{
    public double dist;
    public int x, y;
    public boolean visited = false;
    public int index;
    public int[] neighbors;

    public DijNode(int index, double dist, int x, int y)
    {
        this.index = index;
        this.dist = dist;
        this.x = x;
        this.y = y;
    }

    public boolean isEqual(DijNode another)
    {
        return index == another.index;
    }

    public String toString()
    {
        return ""+index;
    }

    public int compareTo(DijNode another)
    {
        if (dist < another.dist)
            return -1;
        else  if (dist > another.dist)
            return 1;

        return 0;
    }
}
