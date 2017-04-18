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

    public CitadelsComputerPlayerDumb(String initName, int myNumber)
    {
        super(initName);
        this.player = myNumber;
    }

    @Override
    protected void receiveInfo(GameInfo info)
    {
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        savedState = (CitadelsGameState)info;

       /* if(savedState.getPlayerTurn() == this.playerNum)
        {
            game.sendAction(new TakeGold(this));

            sleep(500);

            game.sendAction(new EndTurn(this));
        }*/

        game.sendAction(new TakeGold(this));

        sleep(1000);

        game.sendAction(new EndTurn(this));


        /*// generate random number for AI to choose to take gold or a district card
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
*/



        /*if(this.allPlayerNames[0].equals(this.name))
        {
            player = 0;
        }else if(this.allPlayerNames[1].equals(this.name))
        {
            player = 1;
        }else if(this.allPlayerNames[2].equals(this.name))
        {
            player = 2;
        }

        if(savedState.getTurn() == this.player)
        {
            game.sendAction(new TakeGold(this));
        }*/
    }
}
