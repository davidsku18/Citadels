package edu.up.citadels.game.infoMsg;

/**
 * Created by bryce on 3/3/2017.
 */


import edu.up.citadels.game.config.GameInfo;
import edu.up.citadels.game.Game;

/**
 * A message from the edu.up.citadels.game to a player that tells the player:
 * - who the edu.up.citadels.game is
 * - what the player's ID number is (i.e., player 0, player 1, etc.)
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class BindGameInfo extends GameInfo {

    // to satisfy the Serializable interface
    private static final long serialVersionUID = 8819385408185187694L;

    // the edu.up.citadels.game object
    Game game;

    // the player's ID number
    int playerNum;

    /**
     * constructor
     *
     * @param g
     * 		the edu.up.citadels.game
     * @param pn
     * 		the player number in the edu.up.citadels.game
     */
    public BindGameInfo(Game g, int pn) {
        game = g;
        playerNum = pn;
    }

    /**
     * getter method, returning the edu.up.citadels.game
     *
     * @return
     * 		the edu.up.citadels.game
     */
    public Game getGame() {
        return game;
    }

    /**
     * getter method, returning the player ID
     *
     * @return
     * 		the player ID
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * Setter method for the edu.up.citadels.game. It is expected that this method will be
     * used only by ProxyPlayer and ProxyGame objects.
     *
     * @param g
     * 		the edu.up.citadels.game
     */
    public void setGame(Game g) {
        game = g;
    }

}
