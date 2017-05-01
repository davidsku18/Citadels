package edu.up.citadels.citadels.actions;

import edu.up.citadels.citadels.CitadelsDistrictCard;
import edu.up.citadels.game.actionMsg.GameAction;
import edu.up.citadels.game.GamePlayer;

/**
 * A ChooseBuildDistrictCard is an action that represents choosing a district
 * card to be built in the player's city
 * External Citation
 * Date: 12 March 2017
 * Problem: Didn't know how to implement actions correctly
 * Resource:
 * https://github.com/srvegdahl/SlapJack/blob/master/app
 * /src/main/java/edu/up/cs301/slapjack/SJSlapAction.java
 * Solution: Used the example code from this post.
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 4/30/2017
 */

public class CitadelsBuildDistrictCard extends CitadelsMoveAction
{
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
