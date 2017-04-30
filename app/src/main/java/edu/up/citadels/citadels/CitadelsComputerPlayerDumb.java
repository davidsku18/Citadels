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
 * External Citation
 * Date: 20 March 2017
 * Problem: Could not get AI to take an action and to sleep
 * Resource:
 * https://github.com/srvegdahl/TttGame/blob/master/app
 * /src/main/java/edu/up/cs301/tictactoe/TTTComputerPlayer1.java
 * Solution: Used as reference
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 4/30/2017
 *
 * TODO the way we have it right now the computer is doing multiple things every time the computer
 * TODO is called. We need to differentiate between a regular turn and a computer choosing a character card
 *
 */

public class CitadelsComputerPlayerDumb extends GameComputerPlayer
{
    private CitadelsGameState savedState;

    private int player;

    /**
     * The dumb computer player's constructor
     * @param initName
     * @param myNumber
     */
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
        int x = savedState.getTurn();

        //sleep((int) (1 + Math.random() * 2000));

        //random int to determine if it should draw gold or districts
        int whatToDo;


        for (int i = 0; i < savedState.getCharacterDeck().length; ++i) {
            if (savedState.getCharacterDeck(i) == null) {
                // Do nothing
            } else if (savedState.getCharacterDeck(i) != null) {
                game.sendAction(new ChooseCharacterCard(this, savedState.getCharacterDeck(i)));
                Log.i("Player", "Attempt to Take Character Card");
                break;
            }
        }
        if (myPlayer == 1) {
            sleep(1000 + ((int) (Math.random() * 1000)));
            whatToDo = (int) (Math.random() * 2);
            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }

            if (!savedState.getP1Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP1Hand().get(0)));
            }
        } else if (myPlayer == 2) {
            sleep(1000 + ((int) (Math.random() * 1000)));
            whatToDo = (int) (Math.random() * 2);
            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }

            if (!savedState.getP2Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(0)));
            }
        } else if (myPlayer == 3) {
            sleep(1000 + ((int) (Math.random() * 1000)));
            whatToDo = (int) (Math.random() * 2);
            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }

            if (!savedState.getP3Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(0)));
            }
        }

        int whichCharacterToRuin = (int)(Math.random() * 3);

        game.sendAction(new EndTurn(this));
    }

    public int getPlayerNum()
    {
        return this.player;
    }
}
