package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import java.util.Vector;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class State implements IState {
	
	IAction internal_action;
	IAction exit_action;
	IAction entering_action;
	Collection <ITransition> transitions = new Vector<ITransition> ();
	
	// If no action enter NoAction
	// Empty instance
	public State () {
		
	}
	
	// Accepts a collection to initalize all the transitions of the state
	public void setTransitions (Collection <ITransition> transitions) {
		this.transitions.addAll(transitions);
	}
	
	public IAction getAction() {
		return internal_action;
	}

	public IAction getEntryAction() {
		return entering_action;
	}

	public IAction getExitAction() {
		return exit_action;
	}

	public Collection<ITransition> getTransitions() {
		return transitions;
	}

	public void setAction(IAction action) {
		internal_action = action;	
	}

	public void setEntryAction(IAction action) {
		entering_action = action;
	}

	public void setExitAction(IAction action) {
		exit_action = action;
	}


}
