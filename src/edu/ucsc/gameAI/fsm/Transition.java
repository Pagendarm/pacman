package edu.ucsc.gameAI.fsm;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;


public class Transition implements ITransition {

	ICondition trigger;
	IState target_state;
	IAction trigger_action;

	// Constructs empty instance
	public Transition () {
		
	}
	
	// Sets trigger condition, target_state, and trigger_action
	public Transition (IAction trigger_action,IState target_state,ICondition trigger) {
		this.trigger_action = trigger_action;
		this.target_state = target_state;
		this.trigger = trigger;
	}
	
	public IState getTargetState() {
		return target_state;
	}

	public IAction getAction() {
		return trigger_action;
	}

	public void setCondition(ICondition condition) {
		this.trigger = condition;
	}
	
	public void setTargetState(IState targetState) {
		this.target_state = targetState;
	}

	public void setAction(IAction action) {
		trigger_action = action;
	}
	
	public boolean isTriggered(Game game) {
		return trigger.test(game);
	}


}
