package edu.up.citadels.citadels;

import edu.up.citadels.game.GameComputerPlayer;
import edu.up.citadels.game.config.GameInfo;

/**
 * Created by bryce on 3/3/2017.
 */

public class CitadelsComputerPlayer extends GameComputerPlayer
{
    public CitadelsComputerPlayer(String initName)
    {
        super(initName);
    }

    @Override
    protected void receiveInfo(GameInfo info)
    {
        //
    }
}
