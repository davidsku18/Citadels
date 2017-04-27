package edu.up.citadels.citadels;

import android.util.Log;

import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
import edu.up.citadels.citadels.actions.EndTurn;
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
    protected void receiveInfo(GameInfo info)
    {
        if (!(info instanceof CitadelsGameState))
        {
            return;
        }

        savedState = (CitadelsGameState) info;

        int myPlayer = savedState.getPlayer(this);
        int x = savedState.getTurn();
        game.sendAction(new ChooseCharacterCard(this, savedState.getCharacterDeck((int)Math.random()*7)));
        Log.i("Take Away", "Character Card");
        sleep(1000 + ((int) (Math.random() * 1000)));
        for (int i = 0; i < savedState.getCharacterDeck().length; ++i)
        {
            if (savedState.getCharacterDeck(i) == null)
            {
                // Do nothing
            } else if (savedState.getCharacterDeck(i) != null)
            {
                game.sendAction(new ChooseCharacterCard(this, savedState.getCharacterDeck(i)));
                Log.i("Player", "Attempt to Take Character Card");
                break;
            }
        }
        game.sendAction(new TakeGold(this));
        if (myPlayer == 1)
        {

        }
        else if (myPlayer == 2)
        {
            game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(0)));
        }
        else if (myPlayer == 3)
        {
            game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(0)));
        }
        game.sendAction(new EndTurn(this));
    }

    //returns the player number
    public int getPlayerNum()
    {
        return this.player;
    }
}
