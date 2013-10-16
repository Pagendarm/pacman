package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import java.util.Vector;

import pacman.game.Game;
import edu.ucsc.gameAI.fsm.State;
import edu.ucsc.gameAI.fsm.ITransition;
import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {
	

	// List of states for the machine
	Vector<State> states;
	
	IState initial_state;
	IState current_state;
	
	// Accepts a Vector of states, assumes 1st in Vector is initial 
	public StateMachine (Vector<State> states) {
		this.states = states;
		this.current_state = this.initial_state = states.firstElement();
	}
	
	// Accepts a Vector of states, and an index of the initial
	public StateMachine (Vector<State> states, int initial_index) {
		if (initial_index > states.size()) throw new IndexOutOfBoundsException();
		this.current_state = this.initial_state = states.elementAt(initial_index);
	}
	
	public Collection<IAction> update(Game game) {
		
		ITransition triggered_transition = null;
		
		// Check transitions from current state
		for (ITransition T :current_state.getTransitions()) {
			if (T.isTriggered(game)) {
				triggered_transition = T;
			}
		}
		
		Vector<IAction> actions = new Vector<IAction>(); // Collection to return
		
		if (triggered_transition != null) {
			IState target_state = triggered_transition.getTargetState();
			
			// Update list of actions to transition
			actions.add(current_state.getExitAction());
			actions.add(triggered_transition.getAction());
			actions.add(target_state.getEntryAction());
			
			// Update current_state
			current_state = (State) target_state;
			
			return actions;
			
		}
		else {
			actions.add(current_state.getAction());
			return actions;
		}

	}

	public IState getCurrentState() {
		return current_state;
	}

	public void setCurrentState(IState state) {
		current_state = state;
	}

}
