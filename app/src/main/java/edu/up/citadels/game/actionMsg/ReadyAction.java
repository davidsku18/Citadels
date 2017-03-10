package edu.up.citadels.game.actionMsg;

/**
 * Created by bryce on 3/3/2017.
 */


import edu.up.citadels.game.GamePlayer;

/**
 * An action by which the player tells the edu.up.citadels.game its name
 * (typically the human's name, if it's a GameHumanPlayer).
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class ReadyAction extends GameAction {

    // to satisfy the Serializable interface
    private static final long serialVersionUID = -5286032209480788772L;

    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public ReadyAction(GamePlayer p) {
        super(p);
    }
}
