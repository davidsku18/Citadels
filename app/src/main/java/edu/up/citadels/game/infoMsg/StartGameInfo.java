package edu.up.citadels.game.infoMsg;

/**
 * Created by bryce on 3/3/2017.
 */


/**
 * The a message from the edu.up.citadels.game to a player that edu.up.citadels.game is ready to start.
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class StartGameInfo extends GameInfo {

    // to satisfy the Serializable interface
    private static final long serialVersionUID = -6396033535388393791L;

    // an array, in order of the names of all the players playing the edu.up.citadels.game
    private String[] allPlayerNames;

    /**
     * constructor
     *
     * @param allPlayerNames
     * 		an array containing the names of all the players that are playing
     * 		the edu.up.citadels.game, in player-ID order.
     */
    public StartGameInfo(String[] allPlayerNames) {
        this.allPlayerNames = allPlayerNames;
    }

    /**
     * getter-method for the array of player names
     *
     * @return
     * 		the array of player names
     */
    public String[] getPlayerNames() {
        return allPlayerNames;
    }
}
