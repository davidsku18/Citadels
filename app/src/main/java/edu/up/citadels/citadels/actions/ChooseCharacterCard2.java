package edu.up.citadels.citadels.actions;

import edu.up.citadels.citadels.CharacterCard;
import edu.up.citadels.citadels.CitadelsDistrictCard;
import edu.up.citadels.game.actionMsg.GameAction;
import edu.up.citadels.game.GamePlayer;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class ChooseCharacterCard2 extends CitadelsMoveAction
{
    private int theChosenCard;
    /**
     * constructor for edu.up.citadels.game.GameAction
     *
     * @param player the player who created the action
     */
    public ChooseCharacterCard2(GamePlayer player, int initTheChosenCard)
    {
        super(player);
        this.theChosenCard = initTheChosenCard;
    }

    /**
     *
     * @return whether player has drawn gold or not
     */
    public boolean isChooseCharacterCard2() { return true; }

    public int getTheChosenCharacterCard2() { return this.theChosenCard; }
}