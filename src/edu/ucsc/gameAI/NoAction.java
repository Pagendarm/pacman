package edu.ucsc.gameAI;

import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class NoAction implements IAction, IBinaryNode  {

	public void doAction() {}

	@Override
	public IAction makeDecision() {
		return this;
	}

}
