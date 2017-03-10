package edu.up.citadels.game.actionMsg;

/**
 * Created by bryce on 3/3/2017.
 */

import edu.up.citadels.game.GamePlayer;

/**
 * An action by which the player acknowledges that the edu.up.citadels.game is over.
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class GameOverAckAction extends GameAction {

    // to satisfy the Serializable interface
    private static final long serialVersionUID = 4096230060363451102L;

    /**
     * constructor
     *
     * @param p
     * 		the player to sent the action
     */
    public GameOverAckAction(GamePlayer p) {
        super(p);
    }
}
