package edu.up.citadels.citadels;

import android.util.Log;

import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.UseSpecialAbility;
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

        int ability;

        int whichCharacter;

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
            ability = (int) (Math.random() * 2);
            whichCharacter = (int) (Math.random() * 8);

            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }

            if (!savedState.getP1Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP1Hand().get(0)));
            }

            try
            {
                if (savedState.getP1Chars(0) != null && savedState.getP1Chars(1) != null) {
                    if (ability == 1) {
                        if (savedState.getP1Chars(0).getWhichCharacter() == 0 || savedState.getP1Chars(1).getWhichCharacter() == 0) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP1Chars(0).getWhichCharacter() == 1 || savedState.getP1Chars(1).getWhichCharacter() == 1) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP1Chars(0).getWhichCharacter() == 2 || savedState.getP1Chars(1).getWhichCharacter() == 2) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP1Chars(0).getWhichCharacter() == 3 || savedState.getP1Chars(1).getWhichCharacter() == 3) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP1Chars(0).getWhichCharacter() == 4 || savedState.getP1Chars(1).getWhichCharacter() == 4) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP1Chars(0).getWhichCharacter() == 5 || savedState.getP1Chars(1).getWhichCharacter() == 5) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP1Chars(0).getWhichCharacter() == 6 || savedState.getP1Chars(1).getWhichCharacter() == 6) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP1Chars(0).getWhichCharacter() == 7 || savedState.getP1Chars(1).getWhichCharacter() == 7) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                    }
                }

            }
            catch (IndexOutOfBoundsException iob)
            {
                //do nothing
            }


        } else if (myPlayer == 2) {
            sleep(1000 + ((int) (Math.random() * 1000)));
            whatToDo = (int) (Math.random() * 2);
            ability = (int) (Math.random() * 2);
            whichCharacter = (int) (Math.random() * 8);

            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }

            if (!savedState.getP2Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(0)));
            }

            try {
                if (savedState.getP2Chars(0) != null && savedState.getP2Chars(1) != null) {
                    if (ability == 1) {
                        if (savedState.getP2Chars(0).getWhichCharacter() == 0 || savedState.getP2Chars(1).getWhichCharacter() == 0) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP2Chars(0).getWhichCharacter() == 1 || savedState.getP2Chars(1).getWhichCharacter() == 1) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP2Chars(0).getWhichCharacter() == 2 || savedState.getP2Chars(1).getWhichCharacter() == 2) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP2Chars(0).getWhichCharacter() == 3 || savedState.getP2Chars(1).getWhichCharacter() == 3) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP2Chars(0).getWhichCharacter() == 4 || savedState.getP2Chars(1).getWhichCharacter() == 4) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP2Chars(0).getWhichCharacter() == 5 || savedState.getP2Chars(1).getWhichCharacter() == 5) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP2Chars(0).getWhichCharacter() == 6 || savedState.getP2Chars(1).getWhichCharacter() == 6) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP2Chars(0).getWhichCharacter() == 7 || savedState.getP2Chars(1).getWhichCharacter() == 7) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException iob)
            {
                //do nothing
            }

        } else if (myPlayer == 3) {
            sleep(1000 + ((int) (Math.random() * 1000)));
            whatToDo = (int) (Math.random() * 2);
            ability = (int) (Math.random() * 2);
            whichCharacter = (int) (Math.random() * 8);

            if (whatToDo == 0) {
                game.sendAction(new ChooseDistrictCard(this));
            } else {
                game.sendAction(new TakeGold(this));
            }

            if (!savedState.getP3Hand().isEmpty()) {
                game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(0)));
            }

            try {
                if (savedState.getP3Chars(0) != null && savedState.getP3Chars(1) != null) {
                    if (ability == 1) {
                        if (savedState.getP3Chars(0).getWhichCharacter() == 0 || savedState.getP3Chars(1).getWhichCharacter() == 0) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP3Chars(0).getWhichCharacter() == 1 || savedState.getP3Chars(1).getWhichCharacter() == 1) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP3Chars(0).getWhichCharacter() == 2 || savedState.getP3Chars(1).getWhichCharacter() == 2) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP3Chars(0).getWhichCharacter() == 3 || savedState.getP3Chars(1).getWhichCharacter() == 3) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP3Chars(0).getWhichCharacter() == 4 || savedState.getP3Chars(1).getWhichCharacter() == 4) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP3Chars(0).getWhichCharacter() == 5 || savedState.getP3Chars(1).getWhichCharacter() == 5) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP3Chars(0).getWhichCharacter() == 6 || savedState.getP3Chars(1).getWhichCharacter() == 6) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        if (savedState.getP3Chars(0).getWhichCharacter() == 7 || savedState.getP3Chars(1).getWhichCharacter() == 7) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException iob)
            {
                //do nothing
            }

        }
        game.sendAction(new EndTurn(this));
    }

    public int getPlayerNum()
    {
        return this.player;
    }
}
