package edu.up.citadels.game.actionMsg;

/**
 * Created by bryce on 3/3/2017.
 */


import edu.up.citadels.game.util.GameTimer;

/**
 * An action generated by a time that tells the edu.up.citadels.game that its
 * clock has 'ticked'.
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class TimerAction extends GameAction {

    // to satisfy the Serializable interface
    private static final long serialVersionUID = -8093996755313861636L;

    // the timer that caused this action
    private GameTimer myTimer;

    /**
     * constructor
     *
     * @param timer
     * 		the timer that caused this action
     */
    public TimerAction(GameTimer timer) {
        super(null); // (there is no player associated with this action)
        myTimer = timer;
    }

    /** getter method for the timer
     *
     * @return
     * 		the timer that caused this action
     */
    public GameTimer getTimer() {
        return myTimer;
    }
}