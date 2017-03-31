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
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        savedState = (CitadelsGameState)info;

        // generate random number for AI to choice to take gold or a district card
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




        // if we don't have a game-state, ignore
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        // update our state variable
        savedState = (CitadelsGameState)info;

    }
}
