package edu.up.citadels.citadels;

import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.game.GameComputerPlayer;
import edu.up.citadels.game.infoMsg.GameInfo;
import edu.up.citadels.game.infoMsg.GameState;

/**
 * Computer player that will make guesses whenever it is its turn
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/3/2017
 *
 * TODO the way we have it right now the computer is doing multiple things every time the computer
 * TODO is called. We need to differentiate between a regular turn and a computer choosing a character card
 *
 */

public class CitadelsComputerPlayer extends GameComputerPlayer
{
    CitadelsGameState savedState;

    public CitadelsComputerPlayer(String initName)
    {
        super(initName);
    }

    private CitadelsGameState savedState;

    @Override
    protected void receiveInfo(GameInfo info)
    {
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        savedState = (CitadelsGameState)info;

        // generate random number for AI to choose to take gold or a district card
        int goldOrDist = (int)(Math.random()*2);

        if (goldOrDist == 0)
        {
            game.sendAction(new TakeGold(this));
        }
        else
        {
            game.sendAction(new ChooseDistrictCard(this));
        }

        // generate random number for AI to choice to build or not build
        int build = (int)(Math.random()*2);

        if (build == 0)
        {
            game.sendAction(new CitadelsBuildDistrictCard(this));
        }
        else
        {

        }

        int chooseCharacter = (int)((Math.random())*8);

    }
}
