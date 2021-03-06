package edu.up.citadels.citadels.actions;

import edu.up.citadels.game.GamePlayer;

/**
 * A UseSpecialAbility is an action that represents using specified character's
 * ability
 * External Citation
 * Date: 12 March 2017
 * Problem: Didn't know how to implement actions correctly
 * Resource:
 * https://github.com/srvegdahl/SlapJack/blob/master/app
 * /src/main/java/edu/up/cs301/slapjack/SJSlapAction.java
 * Solution: I used the example code from this post.
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 4/30/2017
 */

public class UseSpecialAbility extends CitadelsMoveAction
{
    private static final long serialVersionUID = 4532523L;
    private int character;
    /**
     * constructor for edu.up.citadels.game.GameAction
     *
     * @param player the player who created the action
     */
    public UseSpecialAbility(GamePlayer player, int initCharacter)
    {
        super(player);
        this.character = initCharacter;
    }

    /**
     * Returns the character card of the player
     * @return character
     *          The character card
     */
    public int getCharacter()
    {
        return this.character;
    }


    public boolean isUseCharacterAbility(){ return true; }

    /**
     * return whether this ability is the assassin's special ability
     *
     * @return boolean
     */
    public boolean isAssassinSpecialAbility()
    {
        return true;
    }

    /**
     * return whether this ability is the thief's special ability
     *
     * @return boolean
     */
    public boolean isThiefSpecialAability()
    {
        return true;
    }

    /**
     * return whether this ability is the magician's special ability
     *
     * @return boolean
     */
    public  boolean isMagicianSpecialAbiity()
    {
        return true;
    }

    /**
     * return whether this ability is the king's special ability
     *
     * @return boolean
     */
    public boolean isKingSpecialAbility()
    {
        return true;
    }

    /**
     * return whether this ability is the bishop's special ability
     *
     * @return boolean
     */
    public boolean isBishopSpecialAbility()
    {
        return true;
    }

    /**
     * return whether this ability is the merchant's special ability
     *
     * @return boolean
     */
    public boolean isMerchantSpecialAbility()
    {
        return true;
    }

    /**
     * return whether this ability is the architect's special ability
     *
     * @return boolean
     */
    public boolean isArchitectSpecialAbility()
    {
        return true;
    }

    /**
     * return whether this ability is the warlord's special ability
     *
     * @return boolean
     */
    public boolean isWarlordSpecialAbility()
    {
        return true;
    }




}
