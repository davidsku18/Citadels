package edu.up.citadels.citadels.actions;

import edu.up.citadels.citadels.CharacterCard;
import edu.up.citadels.game.GamePlayer;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class ChooseCharacterCard extends CitadelsMoveAction
{
    private static final long serialVersionUID = -7552313488624386L;
    private CharacterCard theChosenCard;
    /**
     * constructor for edu.up.citadels.game.GameAction
     *
     * @param player the player who created the action
     */
    public ChooseCharacterCard(GamePlayer player, CharacterCard initTheChosenCard)
    {
        super(player);
        this.theChosenCard = initTheChosenCard;
    }

    /**
     *
     * @return whether player has drawn gold or not
     */
    public boolean isChooseCharacterCard() { return true; }

    public CharacterCard getTheChosenCharacterCard() { return this.theChosenCard; }
}
