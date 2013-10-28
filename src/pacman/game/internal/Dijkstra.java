package pacman.game.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.PriorityQueue;

import pacman.game.Game;
import pacman.game.Constants.*;

/*
 * This class is used to compute the shortest path for the ghosts: as these may not reverse, one cannot use 
 * a simple look-up table. Instead, we use the pre-computed shortest path distances as an admissable
 * heuristic. Although AStar needs to be run every time a path is to be found, it is very quick and does
 * not expand too many nodes beyond those on the optimal path.
 */
public class Dijkstra
{
	public DijNode[] graph;
	
	public void createGraph(Node[] nodes)
	{
		graph = new DijNode[nodes.length];
		
		//create graph
		for(int i=0;i<nodes.length;i++)
			graph[i]=new DijNode(nodes[i].nodeIndex, Double.MAX_VALUE, nodes[i].x, nodes[i].y);
		
		//add neighbours
		for(int i=0;i<nodes.length;i++)
		{	
			EnumMap<MOVE,Integer> neighborhood=nodes[i].neighbourhood;
			MOVE[] moves=MOVE.values();

            int[] neighbors = new int[neighborhood.size()];
            int index = 0;
			
			for(int j=0;j<moves.length;j++)
				if(neighborhood.containsKey(moves[j]))
                    neighbors[index++] = neighborhood.get(moves[j]);

            graph[i].neighbors = neighbors;
		}
	}

    public synchronized void computeGraph(int s, boolean ghost, Game game)
    {
        DijNode start=graph[s];
        start.dist = 0;

        PriorityQueue<DijNode> open = new PriorityQueue<DijNode>();

        if(ghost) {
            start.visited = true;

            MOVE lastMove = MOVE.NEUTRAL;

            for(GHOST g : GHOST.values()) {
                if(game.getGhostCurrentNodeIndex(g) == s) {
                    lastMove = game.getGhostLastMoveMade(g);
                    break;
                }
            }

            for(int i : start.neighbors) {
                MOVE m = game.getNextMoveTowardsTarget(s, i, DM.MANHATTAN);
                if(m.opposite() != lastMove) {
                    DijNode n = graph[i];
                    n.dist = 1;

                    open.add(n);
                }
            }
        } else {
            open.add(start);
        }

        while(!open.isEmpty()) {
            DijNode currentNode = open.poll();
            currentNode.visited = true;

            for(int i : currentNode.neighbors) {
                DijNode neighbor = graph[i];

                if(!neighbor.visited) {
                    double dist = currentNode.dist + 1.0;
                    if(dist < neighbor.dist)
                        neighbor.dist = dist;

                    open.add(neighbor);
                }
            }
        }
    }

    public DijNode[] getGraph()
    {
        return graph;
    }

    public void resetGraph()
    {
        for(DijNode node : graph) {
            node.dist=Double.MAX_VALUE;
            node.visited = false;
        }
    }

    public void softResetGraph()
    {
        for(DijNode node : graph)
            node.visited = false;
    }
}
