package SteelTalons.commands;

/**
 * Command class that runs a command only once
 * 
 * @author Charlemagne Wong
 *
 */
public abstract class SingleCommand implements Command{
	
	/**
	 * State whether command is finished
	 */
	protected boolean finished = false;
	
	/**
	 * State whether force calling command is allowed
	 */
	private boolean allowForceCall = false;
	
	/**
	 * Locks changes to allowForceCall state
	 */
	private boolean lockForceCall = true;
	
	/**
	 * Default constructor
	 */
	public SingleCommand() {
	}
	
	/**
	 * Initialize command with explicit lockForceCall state
	 * 
	 * @param lockForceCall allow modifications to allowForceCall state
	 */
	public SingleCommand(boolean lockForceCall) {
		this.lockForceCall = lockForceCall;
	}
	
	/**
	 * Initialize command with explicit force call states
	 * 
	 * @param allowForceCall state of force call
	 * @param lockForceCall allow modifications to allowForceCall state
	 */
	public SingleCommand(boolean allowForceCall, boolean lockForceCall) {
		this.allowForceCall = allowForceCall;
		this.lockForceCall = lockForceCall;
	}
	
	/**
	 * Calls the command
	 * 
	 * @return state whether call is successful
	 */
	@Override
	public boolean call() {
		if (!finished) {
			command();
			finished = true;
			return true;
		}
		
		return false;
	}

	/**
	 * Forces the command to be called, even when command has ran or call method
	 * has already ran if possible.
	 *
	 * @return state whether forceCall is successful
	 */
	public boolean forceCall() {
		if (allowForceCall) {
			command();
			return true;
		}

		return false;
	}

	/**
	 * Returns the state whether command is finished
	 *
	 * @return state if command is finished
	 */
	@Override
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Command that will run. Write commands here
	 */
	protected abstract void command();
	
	/**
	 * Enables/disables force calling if allowed (lockForceCall is false)
	 * 
	 * @param allowForceCall state for allowing force call
	 * @return true if force call state is changed and false if failed
	 */
	public boolean setForceCall(boolean allowForceCall) {
		if (!lockForceCall)
			this.allowForceCall = allowForceCall;
		
		return false;
	}
}
