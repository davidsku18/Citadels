package edu.up.citadels.game.config;

/**
 * Created by bryce on 3/3/2017.
 */

import java.io.Serializable;

import edu.up.citadels.game.Game;

/**
 * An informational message that is sent from the edu.up.citadels.game to a player.  The
 * most common information will probably be the state of the edu.up.citadels.game (GameState),
 * but there are also other informational items send to a player (e.g.,
 * telling what his player number is, that his last move was illegal).
 * <P>
 * Several "generic" of GameInfo classes are already defined.  These
 * include BindGameInfo and IllegalMoveInfo.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */
public abstract class GameInfo implements Serializable {
    /**
     * satisfy the Serializable interface
     */
    private static final long serialVersionUID = 29062013L;

    /**
     * Sets the edu.up.citadels.game attribute (if applicable) of the GameInfo object.
     * The intent is that this be method be called only by ProxyGame and
     * ProxyPlayer.
     *
     * @param g
     * 		the edu.up.citadels.game to which the objects 'edu.up.citadels.game' attribute is to be set
     */
    public void setGame(Game g) {
        // the default behavior is to do nothing, as it is expected that most
        // GameInfo objects do not contain edu.up.citadels.game information.
    }

}