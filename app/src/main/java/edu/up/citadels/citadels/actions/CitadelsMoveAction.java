package edu.up.citadels.citadels.actions;

import edu.up.citadels.game.actionMsg.GameAction;
import edu.up.citadels.game.GamePlayer;

/**
 * Created by kurtisdavidson on 3/31/17.
 */


public abstract class CitadelsMoveAction extends GameAction {

    private static final long serialVersionUID = -3107100271012188849L;

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
     * 		whether the move was a slap
     */
    public boolean isTakeGold() {
        return true;
    }

    /**
     * @return
     * 		whether the move was a "play"
     */
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

