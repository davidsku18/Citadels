package edu.up.citadels.game;

/**
 * Created by bryce on 3/3/2017.
 */

import edu.up.citadels.game.actionMsg.GameAction;

/**
 * To support remote play, this edu.up.citadels.game framework has two types of Games: local
 * games and remote games that are represented by a proxy. Both types adhere to
 * this common interface.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 * @see LocalGame
 * @see ProxyGame
 */

public interface Game {

    /**
     * starts the edu.up.citadels.game
     *
     * @param players
     * 			the players who are in the edu.up.citadels.game
     */
    public abstract void start(GamePlayer[] players);

    /**
     * sends the given action to the Game object.
     *
     * @param action
     *            the action to send
     */
    public abstract void sendAction(GameAction action);

}