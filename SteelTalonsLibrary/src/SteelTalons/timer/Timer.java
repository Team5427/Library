package SteelTalons.timer;

/**
 * Timer class
 * 
 * @author Charlemagne Wong
 *
 */
public class Timer {

	/**
	 * Timer status
	 */
	public static final int DISABLED = 0;
	public static final int ENABLED  = 1;
	public static final int PAUSED   = 2;
	
	/**
	 * Status of timer
	 */
	private int status = DISABLED;
	
	/**
	 * Time when timer is started
	 */
	private long startTime = 0;
	
	/**
	 * Length of the timer
	 */
	private long time = 0;
	
	/**
	 * The time when the timer is paused
	 */
	private long pauseTime = 0;
	
	/**
	 * Initialize timer
	 * 
	 * @param time length of the timer
	 */
	public Timer (long time) {
		this.time = time;
	}
	
	/**
	 * Starts the timer
	 * 
	 * @return true if timer is started and false if the timer is already running
	 * 		   or an error occurs
	 */
	public boolean start() {
		switch (status) {
			case PAUSED:
				startTime = System.nanoTime() - (pauseTime - startTime);
				status = ENABLED;
				return true;
			case DISABLED:
				startTime = System.nanoTime();
				status = ENABLED;
				return true;
			case ENABLED:
				return false;
		}
		
		return false;		// Something failed
	}
	
	/**
	 * Stops the timer and cannot resume after stopping
	 */
	public void stop() {
		status = DISABLED;
	}
	
	/**
	 * Pauses the timer
	 */
	public void pause() {
		status = PAUSED;
		pauseTime = System.nanoTime();
	}
	
	/**
	 * Returns the status of the timer
	 * 
	 * @return timer status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Sets the length of the timer
	 * 
	 * @param time length of the timer
	 */
	public void setTimer(long time) {
		this.time = time;
	}
	
	/**
	 * Returns the length of the timer in nanoseconds
	 * 
	 * @return length of timer in nanoseconds
	 */
	public long getTimerLength() {
		return time;
	}
	
	/**
	 * Returns the time remaining in the timer in nanoseconds
	 * 
	 * @return time remaining in timer
	 */
	public long getTime() {
		return startTime - System.nanoTime() + time;
	}
	
	/**
	 * Returns the time remaining in the timer in milliseconds
	 * 
	 * @return time remaining in timer
	 */
	public double getTimeMilliseconds() {
		return getTime() / 1e6f;
	}
	
	/**
	 * Returns the time remaining in the timer in seconds
	 * 
	 * @return time remaining in timer
	 */
	public double getTimeSeconds() {
		return getTime() / 1e9f;
	}
}
