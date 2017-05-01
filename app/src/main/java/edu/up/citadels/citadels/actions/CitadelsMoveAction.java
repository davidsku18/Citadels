package edu.up.citadels.citadels.actions;

import edu.up.citadels.game.actionMsg.GameAction;
import edu.up.citadels.game.GamePlayer;

/**
 * A CitadelsMoveAction is an action that represents an action that is done
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


public abstract class CitadelsMoveAction extends GameAction {

    private static final long serialVersionUID = 4532523L;
    /**
     * Constructor for SJMoveAction
     *
     * @param player the player making the move
     */
    public CitadelsMoveAction(GamePlayer player)
    {
        // invoke superclass constructor to set source
        super(player);
    }

    /**
     * @return
     * 		whether gold was taken or not
     */
    public boolean isTakeGold() {
        return false;
    }

    public boolean isEndTurn()
    {
        return false;
    }

    public boolean isChooseDistrictCard()
    {
        return false;
    }

    public boolean isChooseBuildDistrict()
    {
        return false;
    }

    public boolean isChooseCharacterCards()
    {
        return false;
    }

    public boolean isUseCharacterAbility(){ return false;}



}

