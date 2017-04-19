package edu.up.citadels.citadels;

import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
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
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        savedState = (CitadelsGameState) info;

        int myPlayer = savedState.getPlayer(this);

        sleep((int)(1 + Math.random() * 2000));

        int whatToDo = (int) (Math.random() * 2);

        if (whatToDo == 0) {
            game.sendAction(new ChooseDistrictCard(this));
        } else {
            game.sendAction(new TakeGold(this));
        }

        if(myPlayer == 1)
        {
            game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP1Hand().get(0)));
        }else if(myPlayer == 2)
        {
            game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(0)));

        }else if(myPlayer == 3)
        {
            game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(0)));
        }

        game.sendAction(new EndTurn(this));
    }

    public int getPlayerNum()
    {
        return this.playerNum;
    }
}
