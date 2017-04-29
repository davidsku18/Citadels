package edu.up.citadels.citadels.actions;

import edu.up.citadels.game.GamePlayer;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class ChooseDistrictCard extends CitadelsMoveAction
{
    private static final long serialVersionUID = 2321488624386L;
    /**
     * constructor for edu.up.citadels.game.GameAction
     *
     * @param player the player who created the action
     */
    public ChooseDistrictCard(GamePlayer player)
    {
        super(player);
    }

    public boolean isChooseDistrictCard()
    {
        return true;
    }
}
