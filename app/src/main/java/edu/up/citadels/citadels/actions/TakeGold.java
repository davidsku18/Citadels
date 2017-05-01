package edu.up.citadels.citadels.actions;

import edu.up.citadels.game.GamePlayer;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class TakeGold extends CitadelsMoveAction
{
    private static final long serialVersionUID = 5L;
    /**
     * constructor for edu.up.citadels.game.GameAction
     *
     * @param player the player who created the action
     */
    public TakeGold(GamePlayer player)
    {
        super(player);
    }

    /**
     *
     * @return whether player has drawn gold or not
     */
    public boolean drawnGold()
    {
        return true;
    }
}
