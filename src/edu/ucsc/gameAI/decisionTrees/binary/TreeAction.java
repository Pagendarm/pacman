package edu.ucsc.gameAI.decisionTrees.binary;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import pacman.game.Game;


/**
 * A reference implementation for the BinaryDecision class.
 * 
 * @author Chase Cummings
 */
public class TreeAction implements IBinaryNode {

    private BinaryDecision decision;

	/**
	 * Recurses through the binary tree until a leaf/action node is reached.
	 * @return The terminal Action of evaluate the binary decision tree.
	 */

    public void setDecision(BinaryDecision d)
    {
        decision = d;
    }

	public IAction makeDecision(Game game)
    {
        return decision.makeDecision(game);
    }
}
