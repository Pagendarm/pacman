package pacman.entries.ghosts;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedList;

import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.fsm.IState;
import edu.ucsc.gameAI.fsm.IStateMachine;
import edu.ucsc.gameAI.fsm.ITransition;
import edu.ucsc.gameAI.fsm.State;
import edu.ucsc.gameAI.fsm.StateMachine;
import edu.ucsc.gameAI.fsm.Transition;
import edu.ucsc.gameAI.*;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);

	IStateMachine pinkyFSM;
	IStateMachine inkyFSM;
	IStateMachine blinkyFSM;
	IStateMachine sueFSM;

	int radius = 35;

	// Create initial FSM's
	public MyGhosts () {

		for(GHOST ghost : GHOST.values()) {
			switch (ghost){
			case BLINKY:
				blinkyFSM = create_cautious_fsm(ghost);
				break;
			case PINKY:
				pinkyFSM = create_ambush_fsm(ghost);
				break;
			case INKY:
				inkyFSM = create_ambush_fsm(ghost);
				break;
			case SUE:
				sueFSM = create_hunter_fsm(ghost);
				break;

			default: break;
			}	
		}

	}

	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();

		for(GHOST ghost : GHOST.values())	//for each ghost
		{
			Collection <IAction> pinkyACT,inkyACT,blinkyACT,sueACT;
			pinkyACT=inkyACT=blinkyACT=sueACT=null;
			// Update fsm's
			switch (ghost){
			case BLINKY:
				blinkyACT = blinkyFSM.update(game);
				break;
			case PINKY:
				pinkyACT= pinkyFSM.update(game);
				break;
			case INKY:
				inkyACT = inkyFSM.update(game);
				break;
			case SUE:
				sueACT = sueFSM.update(game);

				break;

			default: break;
			}

			if(game.doesGhostRequireAction(ghost))		//if ghost requires an action
			{	

				switch (ghost){
				case PINKY:
					if (pinkyACT != null && pinkyACT.iterator().hasNext())
						myMoves.put(ghost,pinkyACT.iterator().next().getMove(game));
					break;
				case INKY:
					if (inkyACT != null && inkyACT.iterator().hasNext())
						myMoves.put(ghost,inkyACT.iterator().next().getMove(game));
					break;
				case BLINKY:
					if (blinkyACT != null && blinkyACT.iterator().hasNext())
						myMoves.put(ghost,blinkyACT.iterator().next().getMove(game));
					break;
				case SUE:
					if (sueACT != null && sueACT.iterator().hasNext())
						myMoves.put(ghost,sueACT.iterator().next().getMove(game));
					break;

				default: break;
				}

			}
		}


		return myMoves;
	}

	private IStateMachine create_hunter_fsm (GHOST ghost) {
		// States
		IState stateScatter = new State();
		IState stateHunt = new State();

		//  transition to hunt from scatter or avoid
		ITransition toHunt = new Transition();
		toHunt.setCondition(new IsNotEdible(ghost));
		toHunt.setTargetState(stateHunt);

		//  transition to scatter
		ITransition toScatter = new Transition();
		toScatter.setCondition(new IsEdible(ghost));
		toScatter.setTargetState(stateScatter);

		// set actions for states
		stateHunt.setAction(new SeekPacmanAction(ghost));
		stateScatter.setAction(new AvoidPacmanAction(ghost));

		// create transition lists
		Collection<ITransition> listthunt = new LinkedList<ITransition>();
		listthunt.add(toScatter);

		Collection<ITransition> listtscatter = new LinkedList<ITransition>();
		listtscatter.add(toHunt);

		// add transition lists
		stateHunt.setTransitions(listthunt);
		stateScatter.setTransitions(listtscatter);

		// create fsm
		IStateMachine fsm = new StateMachine();
		fsm.setCurrentState(stateHunt);

		return fsm;

	}

	private IStateMachine create_ambush_fsm (GHOST ghost) {
		// States
		IState stateScatter = new State();
		IState stateHunt = new State();
		IState stateZone = new State();
		IState stateAvoid = new State();

		//  transition to zone from scatter
		ITransition toZonefromScatter = new Transition();
		toZonefromScatter.setCondition(new IsNotEdible(ghost));
		toZonefromScatter.setTargetState(stateZone);

		//  transition to zone from avoid
		ITransition toZonefromAvoid = new Transition();
		toZonefromAvoid.setCondition(new IsNotEdible(ghost));
		toZonefromAvoid.setTargetState(stateZone);

		//  transition to avoid
		ITransition toAvoid = new Transition();
		toAvoid.setCondition(new IsEdible(ghost));
		toAvoid.setTargetState(stateScatter);

		// transition to zone from hunt
		ITransition toZonefromHunt = new Transition();
		toZonefromHunt.setCondition(new PacmanFar(ghost,radius));
		toZonefromHunt.setTargetState(stateZone);

		// transition to hunt from zone
		ITransition toHuntfromZone = new Transition();
		toHuntfromZone.setCondition(new PacmanClose(ghost,radius));
		toHuntfromZone.setTargetState(stateHunt);

		//  transition to scatter from avoid
		int r = 20;
		ITransition toScatterfromAvoid = new Transition();
		toScatterfromAvoid.setCondition(new PacmanClose(ghost,r));
		toScatterfromAvoid.setTargetState(stateScatter);

		// set actions for states
		stateHunt.setAction(new SeekPacmanPlusAction(ghost));
		stateScatter.setAction(new AvoidPacmanAction(ghost));
		stateZone.setAction(new SpreadoutAction(ghost));
		stateAvoid.setAction(new AvoidPacmanPlusAction(ghost));

		// transition to zone after eating pacman
		ITransition reset = new Transition();
		reset.setCondition(new PacmanWasEaten());
		reset.setTargetState(stateZone);

		// create transition lists
		Collection<ITransition> listthunt = new LinkedList<ITransition>();
		listthunt.add(toZonefromHunt);
		listthunt.add(toAvoid);
		listthunt.add(reset);

		Collection<ITransition> listtscatter = new LinkedList<ITransition>();
		listtscatter.add(toZonefromScatter);

		Collection<ITransition> listtzone = new LinkedList<ITransition>();
		listtzone.add(toHuntfromZone);
		listtzone.add(toAvoid);

		Collection<ITransition> listtavoid = new LinkedList<ITransition>();
		listtavoid.add(toScatterfromAvoid);
		listtavoid.add(toZonefromAvoid);

		// add transition lists
		stateHunt.setTransitions(listthunt);
		stateScatter.setTransitions(listtscatter);
		stateZone.setTransitions(listtzone);
		stateAvoid.setTransitions(listtavoid);

		// create fsm
		IStateMachine fsm = new StateMachine();
		fsm.setCurrentState(stateZone);

		return fsm;
	}

	// This FSM act afraid regaurdless of edible UNTIL
	// 1. Close enough (but closer then other ghosts) to pacman OR
	// 2. No PowerPills Exist
	private IStateMachine create_cautious_fsm (GHOST ghost) {
		// States
		IState stateScatter = new State();
		IState stateHunt = new State();
		IState stateEdible = new State();

		// transition to edible
		ITransition toEdible = new Transition();
		toEdible.setCondition(new IsEdible(ghost));
		toEdible.setTargetState(stateEdible);

		// transition from edible bc safe
		ITransition fromEdibletoScatter = new Transition();
		fromEdibletoScatter.setCondition(new IsNotEdible(ghost));
		fromEdibletoScatter.setTargetState(stateScatter);

		// transition to scatter after eating pacman
		ITransition reset = new Transition();
		reset.setCondition(new PacmanWasEaten());
		reset.setTargetState(stateScatter);

		//  transition to hunt from scatter bc NoPP
		ITransition toHuntfromScatter = new Transition();
		toHuntfromScatter.setCondition(new NumPowerPillAvailable(2));
		toHuntfromScatter.setTargetState(stateHunt);

		//  transition to hunt from scatter bc close
		ITransition toHuntbecauseNear = new Transition();
		int r = radius - 20; // Shorter (more cautious)
		if (r < 20) r = 20;
		toHuntbecauseNear.setCondition(new PacmanClose(ghost, r)); 
		toHuntbecauseNear.setTargetState(stateHunt);

		// set actions for states
		stateHunt.setAction(new SeekPacmanAction(ghost));
		stateScatter.setAction(new AvoidPacmanAction(ghost));
		stateEdible.setAction(new AvoidPacmanAction(ghost));

		// create transition lists
		Collection<ITransition> listtscatter = new LinkedList<ITransition>();
		listtscatter.add(toHuntfromScatter);
		listtscatter.add(toEdible);
		listtscatter.add(toHuntbecauseNear);

		Collection<ITransition> listtedible = new LinkedList<ITransition>();
		listtedible.add(fromEdibletoScatter);
		listtedible.add(reset);

		Collection<ITransition> listthunt = new LinkedList<ITransition>();
		listthunt.add(toEdible);
		listthunt.add(reset);

		// add transition lists
		stateScatter.setTransitions(listtscatter);
		stateEdible.setTransitions(listtedible);
		stateHunt.setTransitions(listthunt);

		// create fsm
		IStateMachine fsm = new StateMachine();
		fsm.setCurrentState(stateScatter);

		return fsm;

	}
}