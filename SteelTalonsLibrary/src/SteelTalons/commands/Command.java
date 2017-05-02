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
	public boolean call();
	
	public boolean isFinished();
}
