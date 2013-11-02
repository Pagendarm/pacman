package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

public class NoAction implements IAction, IBinaryNode  {

	public void doAction() {}

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}

	@Override
	public MOVE getMove() {
		return MOVE.NEUTRAL;
	}

    public MOVE getMove(Game game) { return getMove(); }
}
