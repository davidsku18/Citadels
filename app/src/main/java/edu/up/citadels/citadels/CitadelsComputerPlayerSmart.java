package edu.up.citadels.citadels;

import edu.up.citadels.citadels.actions.ChooseDistrictCard;
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
        if (!(info instanceof CitadelsGameState))
        {
            return;
        }

        savedState = (CitadelsGameState) info;

      /*  //TODO maybe check and see if I can check with player int and turn

        int whatToDo = (int) (Math.random() * 2);

        if (whatToDo == 0)
        {
            game.sendAction(new ChooseDistrictCard(this));
        }else
        {
            game.sendAction(new TakeGold(this));
        }
        sleep(1000);*/
        game.sendAction(new TakeGold(this));

        game.sendAction(new EndTurn(this));

    }
}