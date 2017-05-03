package SteelTalons.commands;

import SteelTalons.timer.Timer;

/**
 * Created by Charlemagne Wong on 5/3/2017.
 *
 * TODO: Finish this
 */
public class TimedStepListCommand extends StepListCommand {

    private Timer timer;

    @Override
    public boolean isFinished() {
        return timer.getTime() < 0 && super.isFinished();
    }

}
