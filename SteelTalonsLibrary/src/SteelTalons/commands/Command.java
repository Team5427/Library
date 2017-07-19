package SteelTalons.commands;

/**
 * Command parent class
 * 
 * @author Charlemane Wong
 *
 */
public interface Command {

	/**
	 * Calls the command
	 */
	boolean call();

	/**
	 * Returns the state whether command is finished
	 *
	 * @return state if command is finished
	 */
	boolean isFinished();
}
