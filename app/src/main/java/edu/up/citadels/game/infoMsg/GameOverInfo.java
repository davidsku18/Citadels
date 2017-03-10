package edu.up.citadels.game.infoMsg;

/**
 * Created by bryce on 3/3/2017.
 */


import edu.up.citadels.game.config.GameInfo;

/**
 * A message from the edu.up.citadels.game to a player that tells the player that
 * the edu.up.citadels.game is over.
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class GameOverInfo extends GameInfo {

    // to satisfy the Serializable interface
    private static final long serialVersionUID = -8005304466588509849L;

    // the message that gives the edu.up.citadels.game's result
    private String message;

    /**
     * constructor
     *
     * @param msg
     * 		a message that tells the result of the edu.up.citadels.game
     */
    public GameOverInfo(String msg) {
        this.message = msg;
    }

    /**
     * getter method for the message
     *
     * @return
     * 		the message, telling the result of the edu.up.citadels.game
     */
    public String getMessage() {
        return message;
    }
}
