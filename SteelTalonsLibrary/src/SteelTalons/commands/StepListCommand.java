package SteelTalons.commands;

import java.util.ArrayList;

/**
 * Created by Charlemagne Wong on 5/3/2017.
 *
 * Allows multiple commands to run in steps until they are finished
 */
public class StepListCommand implements Command
{
    /**
     * List of commands
     */
    private ArrayList<Command> commandList = new ArrayList<>();

    /**
     * Resets command list
     */
    private int index = 0;

    /**
     * When calling the call() method, the method might determine that the command
     * is not finished yet, but when the command is actually called, it will return
     * false. This can be caused when the call() method of the command is actually
     * called, the command has already finished and returns false. Setting the
     * iterateFailedCall state to true will iterate to the next command and tries
     * calling the following command.
     */
    private boolean iterateFailedCall = false;

    /**
     * Default initialization
     */
    public StepListCommand() {
        System.err.println("Remind Charlie to write this (or you can write it yourself!)");
    }

    /**
     * Initializes with ArrayList of commands
     *
     * @param commandList ArrayList of commands
     */
    public StepListCommand(ArrayList<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * Initializes with list of commands
     *
     * @param commandArr list of commands
     */
    public StepListCommand(Command... commandArr) {
        commandList = new ArrayList<>(commandArr.length);

        for (Command c : commandArr)
            commandList.add(c);
    }

    /**
     * Calls the command
     *
     * @return true when command is called successfully
     */
    @Override
    public boolean call() {
        if (index < commandList.size()) {
            if (commandList.get(index).isFinished()) {
                index++;
                if (index >= commandList.size()) {
                    return false;
                }
            }

            boolean success = commandList.get(index).call();

            if (!success && iterateFailedCall) {
                return call();
            }

            return success;
        }

        return false;
    }

    /**
     * Returns the state if command has finished running
     *
     * @return whether command has finished running
     */
    @Override
    public boolean isFinished() {
        return (commandList == null || index >= commandList.size());
    }

    /**
     * Returns command list arraylist
     *
     * @return command list
     */
    public ArrayList<Command> getCommandList() {
        return commandList;
    }

    /**
     * Set the command list
     *
     * @param commandList new command list
     */
    public void setCommandList(ArrayList<Command> commandList) {
        this.commandList = commandList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isIterateFailedCall() {
        return iterateFailedCall;
    }

    public void setIterateFailedCall(boolean iterateFailedCall) {
        this.iterateFailedCall = iterateFailedCall;
    }

    /**
     * Resets the index counter to 0 to start the command from beginning
     *
     * WARNING: This does not guarantee that the commands in the list will run.
     *          Some commands might need to be reset individually or some may
     *          not be able to reset at all.
     */
    public void reset() {
        index = 0;
    }
}
