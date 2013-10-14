package edu.ucsc.gameAI.fsm;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;


public class Transition implements ITransition {

	ICondition trigger;
	IState target_state;
	IAction trigger_action;

	// Requires a call to setCondition to set the trigger
	public Transition (IAction trigger_action,IState target_state) {
		this.trigger_action = trigger_action;
		this.target_state = target_state;
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


	public boolean isTriggered() {
		return trigger.test();
	}

}
