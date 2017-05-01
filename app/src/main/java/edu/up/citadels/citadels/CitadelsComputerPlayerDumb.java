package edu.up.citadels.citadels;

import android.util.Log;

import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
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
    private CitadelsGameState savedState;


    public CitadelsComputerPlayerDumb(String initName)
    {
        super(initName);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof CitadelsGameState)) {
            return;
        }

        savedState = (CitadelsGameState) info;

        int myPlayer = savedState.getPlayer(this);
        sleep(2000);

        //random int to determine if it should draw gold or districts
        int whatToDo;

        if (myPlayer == 1)
        {
            for (int i = 0; i < savedState.getCharacterDeck().length; ++i) {
                if (savedState.getCharacterDeck(i) == null) {
                    // Do nothing
                } else if (savedState.getCharacterDeck(i) != null) {
                    game.sendAction(new ChooseCharacterCard(this, savedState.getCharacterDeck(i)));
                    Log.i("Player", "Attempt to Take Character Card");
                    break;
                }
            }
            sleep(1500);
            whatToDo = (int) (Math.random() * 2);
            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }
            sleep(1500);
            if (!savedState.getP1Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP1Hand().get(0)));
            }
            sleep(3000);
            game.sendAction(new EndTurn(this));
        } else if (myPlayer == 2)
        {
            for (int i = 0; i < savedState.getCharacterDeck().length; ++i) {
                if (savedState.getCharacterDeck(i) == null) {
                    // Do nothing
                } else if (savedState.getCharacterDeck(i) != null) {
                    game.sendAction(new ChooseCharacterCard(this, savedState.getCharacterDeck(i)));
                    Log.i("Player", "Attempt to Take Character Card");
                    break;
                }
            }
            sleep(1500);
            whatToDo = (int) (Math.random() * 2);
            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }
            sleep(1500);
            if (!savedState.getP2Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(0)));
            }
            sleep(3500);
            game.sendAction(new EndTurn(this));
        }
        else if (myPlayer == 3)
        {
            for (int i = 0; i < savedState.getCharacterDeck().length; ++i)
            {
                if (savedState.getCharacterDeck(i) == null)
                {
                // Do nothing
                }
                else if (savedState.getCharacterDeck(i) != null)
                {
                game.sendAction(new ChooseCharacterCard(this, savedState.getCharacterDeck(i)));
                Log.i("Player", "Attempt to Take Character Card");
                break;
                }
            }
            sleep(1500);
            whatToDo = (int) (Math.random() * 2);
            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }
            sleep(1500);
            if (!savedState.getP3Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(0)));
            }
            sleep(3500);
            game.sendAction(new EndTurn(this));
        }

        /*int whichCharacterToRuin = (int)(Math.random() * 3);
        if (whichCharacterToRuin == 0)
        {
            game.sendAction(new UseSpecialAbility(this, savedState.getP1Character1()));
        }else if(whichCharacterToRuin == 1)
        {
            game.sendAction(new UseSpecialAbility(this, savedState.getP2Character1()));
        }else if(whichCharacterToRuin == 2)
        {
            game.sendAction(new UseSpecialAbility(this, savedState.getP3Character1()));
        }*/

    }


    public int getPlayerNum()
    {
        return this.playerNum;
    }
}
