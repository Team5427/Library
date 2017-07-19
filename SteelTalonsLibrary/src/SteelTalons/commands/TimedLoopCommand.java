package SteelTalons.commands;

import SteelTalons.timer.Timer;

/**
 * Command class that runs the command in a loop
 * 
 * @author Charlemagne Wong
 *
 */
public abstract class TimedLoopCommand implements Command {

	/**
	 * Timer to determine if command is finished
	 */
	private Timer timer = null;
	
	/**
	 * State if initialize method has been called
	 */
	private boolean initialized = false;
	
	/**
	 * State whether to call initialize when command is called
	 */
	private boolean callInitialize = true;
	
	/**
	 * Prevents calls to command when time runs out
	 */
	private boolean safeStop = true;
	
	/**
	 * Initialize TimerLoopCommand with timer
	 * 
	 * @param timer to determine if command is called
	 */
	public TimedLoopCommand(Timer timer) {
		this.timer = timer;
	}
	
	public TimedLoopCommand (long time) {
		timer = new Timer(time);
	}
	
	/**
	 * Calls the command
	 * 
	 * @return true if command ran successfully, false otherwise
	 */
	@Override
	public final boolean call() {
		if (!isFinished() || !safeStop) {
			if (!initialized && callInitialize)
				initialize();
			
			loop();
			return true;
		}

		return false;
	}

	/**
	 * Initializes the command
	 */
	protected abstract void initialize();
	
	/**
	 * Loops command
	 */
	protected abstract void loop();
	
	/**
	 * Command is not finished unless timer runs out.
	 * Designed to throw NullPointerException when no timer is given
	 *
	 * @return true if command is finished, false if not
	 */
	@Override
	public boolean isFinished() {
		return timer.getTime() < 0;
	}
	
	/**
	 * Sets the timer
	 * 
	 * @param timer Timer for command to use
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	/**
	 * Sets the timer
	 * 
	 * @param timer length of timer
	 */
	public void setTimer(long timer) {
		this.timer = new Timer(timer);
	}
	
	/**
	 * Returns the timer
	 * 
	 * @return timer
	 */
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 * Force start the timer
	 * 
	 * @return true if timer started successfully, false if otherwise
	 * 		   method would return false only if timer is null.
	 */
	public final boolean start() {
		if (timer != null) {
			timer.start();
			return true;
		}
		
		return false;
	}
	
	/**
	 * Resets timer
	 */
	public void reset() {
		if (timer != null)
			timer.stop();
		
		initialized = false;
	}

	/**
	 * Returns state of whether to call initialization
	 *
	 * @return state to allow call initialization
	 */
	public boolean isCallInitialize() {
		return callInitialize;
	}

	/**
	 * Set state whether to call initialization
	 *
	 * @param callInitialize state to set the call initialization
	 */
	public void setCallInitialize(boolean callInitialize) {
		this.callInitialize = callInitialize;
	}

	public boolean isSafeStop() {
		return safeStop;
	}

	public void setSafeStop(boolean safeStop) {
		this.safeStop = safeStop;
	}
}