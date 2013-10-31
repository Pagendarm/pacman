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
	
	int radius = 20;
	
	// Create initial FSM's
	public MyGhosts () {
		
		for(GHOST ghost : GHOST.values()) {
			switch (ghost){
			case PINKY:
				pinkyFSM = create_fsm(ghost);
				break;
			case INKY:
				inkyFSM = create_fsm(ghost);
				break;
			case BLINKY:
				blinkyFSM = create_fsm(ghost);
				break;
			case SUE:
				sueFSM = create_fsm(ghost);
				break;
			
			default: break;
			}	
		}
		
	}
	
	private IStateMachine create_fsm (GHOST ghost) {
		// States
		IState stateScatter = new State();
		IState stateHunt = new State();
		IState stateZone = new State();
		
		//  transition to zone from scatter
		ITransition toZonefromScatter = new Transition();
		toZonefromScatter.setCondition(new IsNotEdible(ghost));
		toZonefromScatter.setTargetState(stateZone);
		toZonefromScatter.setAction(new SpreadoutAction(ghost));
		
		//  transition to scatter
		ITransition toScatter = new Transition();
		toScatter.setCondition(new IsEdible(ghost));
		toScatter.setTargetState(stateScatter);
		
		// transition to zone from hunt
		ITransition toZonefromHunt = new Transition();
		toZonefromHunt.setCondition(new PacmanFar(ghost,radius));
		toZonefromHunt.setTargetState(stateZone);
		toZonefromHunt.setAction(new SpreadoutAction(ghost));
	
		// transition to hunt from zone
		ITransition toHuntfromZone = new Transition();
		toHuntfromZone.setCondition(new PacmanClose(ghost,radius));
		toHuntfromZone.setTargetState(stateHunt);
		
		// set actions for states
		stateHunt.setAction(new SeekPacmanAction(ghost));
		stateScatter.setAction(new AvoidPacmanAction(ghost));
		stateZone.setAction(new SpreadoutAction(ghost));
		
		// create transition lists
		Collection<ITransition> listthunt = new LinkedList<ITransition>();
		listthunt.add(toZonefromHunt);
		listthunt.add(toScatter);
		
		Collection<ITransition> listtscatter = new LinkedList<ITransition>();
		listtscatter.add(toZonefromScatter);
		
		Collection<ITransition> listtzone = new LinkedList<ITransition>();
		listtzone.add(toHuntfromZone);
		listtzone.add(toScatter);
		
		// add transition lists
		stateHunt.setTransitions(listthunt);
		stateScatter.setTransitions(listtscatter);
		stateZone.setTransitions(listtzone);
		
		// create fsm
		IStateMachine fsm = new StateMachine();
		fsm.setCurrentState(stateHunt);
		
		return fsm;
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
			case PINKY:
				pinkyACT= pinkyFSM.update(game);
				System.out.println ("DEBUG: "+pinkyFSM.getCurrentState().getAction());
				System.out.println ("DEBUG: "+game.getNodeXCood(game.getGhostCurrentNodeIndex(ghost)));
				break;
			case INKY:
				inkyACT = inkyFSM.update(game);
				break;
			case BLINKY:
				blinkyACT = blinkyFSM.update(game);
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
}