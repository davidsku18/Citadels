package edu.up.citadels.citadels.actions;

import edu.up.citadels.citadels.CitadelsDistrictCard;
import edu.up.citadels.game.GamePlayer;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class CitadelsBuildDistrictCard extends CitadelsMoveAction
{
    private static final long serialVersionUID = 52134386L;
    private CitadelsDistrictCard cardBeingBuilt;

    /**
     * constructor for edu.up.citadels.game.GameAction
     *
     * @param player the player who created the action
     */
    public CitadelsBuildDistrictCard(GamePlayer player, CitadelsDistrictCard cardToBuild)
    {
        super(player);
        this.cardBeingBuilt = cardToBuild;
    }

    public CitadelsDistrictCard getCard()
    {
        return this.cardBeingBuilt;
    }
}
