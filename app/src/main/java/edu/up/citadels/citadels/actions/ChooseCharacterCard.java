package edu.up.citadels.citadels.actions;

import edu.up.citadels.game.actionMsg.GameAction;
import edu.up.citadels.game.GamePlayer;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class ChooseCharacterCard extends GameAction
{
    /**
     * constructor for edu.up.citadels.game.GameAction
     *
     * @param player the player who created the action
     */
    public ChooseCharacterCard(GamePlayer player)
    {
        super(player);
    }
}
