package edu.up.citadels.citadels.actions;

import edu.up.citadels.game.GamePlayer;

/**
 * A TakeGold is an action that represents the user taking gold
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

public class TakeGold extends CitadelsMoveAction
{
    private static final long serialVersionUID = 528372L;
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
