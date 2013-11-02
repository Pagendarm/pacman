package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

public class NumPowerPillAvailable implements ICondition {

	int ppill_index;
	int N = 0;
	// Returns TRUE if <= N number of power pills are available 
	// otherwise FALSE
	public NumPowerPillAvailable(int n)
	{
		N = n;
	}

	public boolean test(Game game) 
	{
		int num = 0;
		
		int[] pp = game.getActivePillsIndices();
		for (int i=0;i<pp.length;i++){
			if (game.isPowerPillStillAvailable(pp[i])) num++;
		}
		return (num <= N);
	}
}
