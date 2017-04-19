package edu.up.citadels.citadels;

import edu.up.citadels.citadels.actions.EndTurn;
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

public class CitadelsComputerPlayerDumb extends GameComputerPlayer
{
    private CitadelsGameState savedState;

    private int player;

    private int selectedCard;

    public CitadelsComputerPlayerDumb(String initName, int myNumber)
    {
        super(initName);
        this.player = myNumber;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        savedState = (CitadelsGameState) info;

        //TODO maybe check and see if I can check with player int and turn

        sleep(2000);

        int whatToDo = (int) (Math.random() * 2);
        int doOrDontBuild = (int) (Math.random() * 2 );
        int whichDistrictToBuildP2 = (int) (Math.random() * savedState.getP2Hand().size());
        int whichDistrictToBuildP3 = (int) (Math.random() * savedState.getP3Hand().size());

        if (whatToDo == 0)
        {
            game.sendAction(new ChooseDistrictCard(this));
        }
        else if (whatToDo == 1)
        {
            game.sendAction(new TakeGold(this));
        }
        else if ( doOrDontBuild == 0 )
        {
            CitadelsDistrictCard cardToBuild2 = (CitadelsDistrictCard)savedState.getP1Hand().get(whichDistrictToBuildP2);
            CitadelsDistrictCard cardToBuild3 = (CitadelsDistrictCard)savedState.getP1Hand().get(whichDistrictToBuildP3);
            if(savedState.getP2Gold() >= cardToBuild2.getCost())
            {
                game.sendAction(new CitadelsBuildDistrictCard(this, cardToBuild2));
            }
            else if (savedState.getP3Gold() >= cardToBuild2.getCost())
            {
                game.sendAction(new CitadelsBuildDistrictCard(this, cardToBuild3));
            }
        }
        else if (doOrDontBuild == 1)
        {
            //dont build anything
        }

        game.sendAction(new EndTurn(this));
    }
}
