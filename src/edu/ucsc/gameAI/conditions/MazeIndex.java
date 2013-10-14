package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class MazeIndex implements ICondition {

	Game game;
	int index;
	
	public MazeIndex (Game game,int index) {
		this.game = game;
		this.index = index;
	}
	
	public boolean test() {
		return (game.getMazeIndex() == index);
	}

}
