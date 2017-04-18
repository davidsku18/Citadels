package edu.up.citadels.citadels;

import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.game.GameComputerPlayer;
import edu.up.citadels.game.infoMsg.GameInfo;

/**
 * Created by bryce on 4/17/2017.
 */

public class CitadelsComputerPlayerSmart extends GameComputerPlayer
{
    private CitadelsGameState savedState;

    private int player;

    public CitadelsComputerPlayerSmart(String initName, int myNumber)
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

        sleep(500);

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