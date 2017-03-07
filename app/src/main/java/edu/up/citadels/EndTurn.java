package edu.up.citadels;

import ActionMsg.GameAction;
import player.GamePlayer;

/**
 * Created by gavinlow on 3/6/17.
 */

public class EndTurn extends GameAction
{
    /**
     * constructor for game.GameAction
     *
     * @param player the player who created the action
     */
    public EndTurn(GamePlayer player)
    {
        super(player);
    }
}
