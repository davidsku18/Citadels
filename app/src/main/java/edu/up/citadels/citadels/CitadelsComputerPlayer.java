package edu.up.citadels.citadels;

import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.game.GameComputerPlayer;
import edu.up.citadels.game.infoMsg.GameInfo;

/**
 * Computer player that will make guesses whenever it is its turn
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/3/2017
 *
 */

public class CitadelsComputerPlayer extends GameComputerPlayer
{
    private CitadelsGameState savedState;

    public CitadelsComputerPlayer(String initName)
    {
        super(initName);
    }

    @Override
    protected void receiveInfo(GameInfo info)
    {
        // if we don't have a game-state, ignore
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        // update our state variable
        savedState = (CitadelsGameState)info;

    }
}
