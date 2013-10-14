package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import java.util.Vector;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class State implements IState {
	
	IAction internal_action;
	IAction exit_action;
	IAction entering_action;
	Vector <ITransition> transitions = new Vector<ITransition> ();
	
	// If no action enter NoAction
	// Need to use setTransitions as well to create complete State instance
	public State (IAction entering_action, IAction internal_action, IAction exit_action){
		this.entering_action = entering_action;
		this.internal_action = internal_action;
		this.exit_action = exit_action;
	}
	
	// Accepts a Vector to initalize all the transitions of the state
	public void setTransitions (Vector <ITransition> transitions) {
		this.transitions.addAll(transitions);
	}
	
	public IAction getAction() {
		return internal_action;
	}

	@Override
	public IAction getEntryAction() {
		return entering_action;
	}

	@Override
	public IAction getExitAction() {
		return exit_action;
	}

	@Override
	public Collection<ITransition> getTransitions() {
		return transitions;
	}

}
