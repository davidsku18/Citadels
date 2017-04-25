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
    //game state for the computer player to use
    private CitadelsGameState savedState;

    //the player number
    private int player;

    //constructor for the dumb AI player
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

        //AI sleeps for 2 to 3 seconds so it looks like it is thinking
        sleep((int)(1 + Math.random() * 2000));

        //random int to determine if it should draw gold or districts
        int whatToDo = (int) (Math.random() * 2);

        //sends the correct action to the local game
        if (whatToDo == 0)
        {
            game.sendAction(new ChooseDistrictCard(this));
        }
        else
        {
            game.sendAction(new TakeGold(this));
        }


        //Determines which AI player is which
        if(myPlayer == 1)
        {
            if(savedState.getP1Hand() != null)
            {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP1Hand().get(0)));
            }
        }else if(myPlayer == 2)
        {
            if(savedState.getP2Hand() != null)
            {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(0)));
            }
        }else if(myPlayer == 3)
        {
            if (savedState.getP3Hand() != null)
            {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(0)));
            }
        }
        game.sendAction(new EndTurn(this));
    }

    //returns the player number
    public int getPlayerNum()
    {
        return this.playerNum;
    }
}
