package edu.up.citadels;

import ActionMsg.GameAction;
import player.GamePlayer;

/**
 * Created by gavinlow on 3/6/17.
 */

public class DrawDistrictCard extends GameAction
{
    /**
     * constructor for game.GameAction
     *
     * @param player the player who created the action
     */
    public DrawDistrictCard(GamePlayer player)
    {
        super(player);
    }
}
