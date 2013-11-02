package edu.ucsc.gameAI.fsm;

import java.util.Collection;
import java.util.Vector;

import pacman.game.Game;
import edu.ucsc.gameAI.fsm.State;
import edu.ucsc.gameAI.fsm.ITransition;
import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {
	
	
	IState initial_state;
	IState current_state;
	
	// Empty state machine
	public StateMachine (){
	}
	
	public Collection<IAction> update(Game game) {
		
		ITransition triggered_transition = null;
		
		// Check transitions from current state
		for (ITransition T :current_state.getTransitions()) {
			if (T.isTriggered(game)) {
				triggered_transition = T;
			}
		}
		
		Collection<IAction> actions = new Vector<IAction>(); // Collection to return
		
		if (triggered_transition != null) {
			IState target_state = triggered_transition.getTargetState();
			
			// Update list of actions to transition
			if (current_state.getExitAction() != null)
			actions.add(current_state.getExitAction());

			if (triggered_transition.getAction() != null)
			actions.add(triggered_transition.getAction());
			
			if (target_state.getEntryAction() != null)
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
