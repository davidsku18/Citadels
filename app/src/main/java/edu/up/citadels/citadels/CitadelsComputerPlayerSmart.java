package edu.up.citadels.citadels;

import android.util.Log;

import java.io.Serializable;

import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.UseSpecialAbility;
import edu.up.citadels.game.GameComputerPlayer;
import edu.up.citadels.game.infoMsg.GameInfo;

/**
 * Computer player that will try to win the game
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

public class CitadelsComputerPlayerSmart extends GameComputerPlayer implements Serializable
{
    private CitadelsGameState savedState;

    private int playerNum;

    /**
     * The smart computer player's constructor
     * @param initName
     *          the computer player's name
     */
    public CitadelsComputerPlayerSmart(String initName)
    {
        super(initName);
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

        int ability;

        int whichCharacter;

        //pick character
        sleep((int)(1 + Math.random() * 2000));
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

        //AI attempts to stockpile 8 gold and only draws district cards when there are no district cards
        // in hand or if AI has more than 8 gold
        if(myPlayer == 1)
        {
            ability = (int) (Math.random() * 2);
            whichCharacter = (int) (Math.random() * 8);

            if((savedState.getP1Gold() < 8) && (savedState.getP1Hand().size() != 0))
            {
                game.sendAction(new TakeGold(this));
            }
            else
            {
                game.sendAction(new ChooseDistrictCard(this));
            }

            sleep((int)(1 + Math.random() * 2000));

            for(int i = 0; i < savedState.getP1Hand().size(); i++)
           {
               CitadelsDistrictCard temp = (CitadelsDistrictCard) savedState.getP1Hand().get(i);
               if (temp.getCost() >= 3)
               {
                   game.sendAction(new CitadelsBuildDistrictCard(this,
                           (CitadelsDistrictCard) savedState.getP1Hand().get(i)));
                   break;
               }
           }

            try
            {
                if (savedState.getTurn() >= 7) {
                    if (ability == 1) {
                        //assassin
                        if (savedState.getP1Chars(0).getWhichCharacter() == 0 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 0) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //thief
                        if (savedState.getP1Chars(0).getWhichCharacter() == 1 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 1) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //magician
                        if (savedState.getP1Chars(0).getWhichCharacter() == 2 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 2) {
                            // if the AI knows if the other players have an assassin or thief use the ability
                            if (savedState.getTurn() >= 9)
                            {
                                if (savedState.getP1Chars(0).getWhichCharacter() == 0 ||
                                        savedState.getP2Chars(1).getWhichCharacter() == 0 ||
                                        savedState.getP2Chars(0).getWhichCharacter() == 1 ||
                                        savedState.getP2Chars(1).getWhichCharacter() == 1)

                                {
                                    game.sendAction(new UseSpecialAbility(this, 1));
                                }
                                if (savedState.getP2Chars(0).getWhichCharacter() == 0 ||
                                        savedState.getP3Chars(1).getWhichCharacter() == 0 ||
                                        savedState.getP3Chars(0).getWhichCharacter() == 1 ||
                                        savedState.getP3Chars(1).getWhichCharacter() == 1)
                                {
                                    game.sendAction(new UseSpecialAbility(this, 1));
                                }

                            }
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //king
                        if (savedState.getP1Chars(0).getWhichCharacter() == 3 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 3) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //bishop
                        if (savedState.getP1Chars(0).getWhichCharacter() == 4 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 4) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //merchant
                        if (savedState.getP1Chars(0).getWhichCharacter() == 5 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 5) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //architect
                        if (savedState.getP1Chars(0).getWhichCharacter() == 6 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 6) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //warlord
                        if (savedState.getP1Chars(0).getWhichCharacter() == 7 ||
                                savedState.getP1Chars(1).getWhichCharacter() == 7) {
                            if (savedState.getP2Score() >= savedState.getP1Score())
                            {
                                game.sendAction(new UseSpecialAbility(this,
                                        savedState.getP1Chars(0).getWhichCharacter()));
                            }
                            if (savedState.getP3Score() >= savedState.getP1Score())
                            {
                                game.sendAction(new UseSpecialAbility(this,
                                        savedState.getP1Chars(0).getWhichCharacter()));
                            }
                        }
                    }
                }

            }
            catch (IndexOutOfBoundsException iob)
            {
                //do nothing
            }

        }
        else if(myPlayer == 2)
        {
            ability = (int) (Math.random() * 2);
            whichCharacter = (int) (Math.random() * 8);

            if((savedState.getP2Gold() < 8) && (savedState.getP2Hand().size() != 0))
            {
                game.sendAction(new TakeGold(this));
            }
            else
            {
                game.sendAction(new ChooseDistrictCard(this));
            }

            sleep((int)(1 + Math.random() * 2000));

            for(int i = 0; i < savedState.getP2Hand().size(); i++)
            {
                CitadelsDistrictCard temp = (CitadelsDistrictCard) savedState.getP2Hand().get(i);
                if (temp.getCost() >= 3)
                {
                    game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard)
                            savedState.getP2Hand().get(i)));
                    break;
                }
            }

            try {
                if (savedState.getTurn() >= 7) {
                    if (ability == 1) {
                        //assassin
                        if (savedState.getP2Chars(0).getWhichCharacter() == 0 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 0) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //thief
                        if (savedState.getP2Chars(0).getWhichCharacter() == 1 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 1) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //magician
                        if (savedState.getP2Chars(0).getWhichCharacter() == 2 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 2) {
                            // if the AI knows if the other players have an assassin or thief use the ability
                            if (savedState.getTurn() >= 9)
                            {
                                if (savedState.getP1Chars(0).getWhichCharacter() == 0 ||
                                        savedState.getP1Chars(1).getWhichCharacter() == 0 ||
                                        savedState.getP1Chars(0).getWhichCharacter() == 1 ||
                                        savedState.getP1Chars(1).getWhichCharacter() == 1)

                                {
                                    game.sendAction(new UseSpecialAbility(this, 1));
                                }
                                if (savedState.getP3Chars(0).getWhichCharacter() == 0 ||
                                        savedState.getP3Chars(1).getWhichCharacter() == 0 ||
                                        savedState.getP3Chars(0).getWhichCharacter() == 1 ||
                                        savedState.getP3Chars(1).getWhichCharacter() == 1)
                                {
                                    game.sendAction(new UseSpecialAbility(this, 1));
                                }

                            }
                        }
                        //king
                        if (savedState.getP2Chars(0).getWhichCharacter() == 3 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 3) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //bishop
                        if (savedState.getP2Chars(0).getWhichCharacter() == 4 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 4) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //merchant
                        if (savedState.getP2Chars(0).getWhichCharacter() == 5 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 5) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //architect
                        if (savedState.getP2Chars(0).getWhichCharacter() == 6 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 6) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //warlord
                        if (savedState.getP2Chars(0).getWhichCharacter() == 7 ||
                                savedState.getP2Chars(1).getWhichCharacter() == 7) {
                            if (savedState.getP1Score() >= savedState.getP2Score())
                            {
                                game.sendAction(new UseSpecialAbility(this,
                                        savedState.getP1Chars(0).getWhichCharacter()));
                            }
                            if (savedState.getP2Score() >= savedState.getP2Score())
                            {
                                game.sendAction(new UseSpecialAbility(this,
                                        savedState.getP1Chars(0).getWhichCharacter()));
                            }
                        }
                    }
                }
            }
            catch (IndexOutOfBoundsException iob)
            {
                //do nothing
            }

        }
        else if(myPlayer == 3)
        {
            ability = (int) (Math.random() * 2);
            whichCharacter = (int) (Math.random() * 8);

            if((savedState.getP3Gold() < 8) && (savedState.getP3Hand().size() != 0))
            {
                game.sendAction(new TakeGold(this));
            }
            else
            {
                game.sendAction(new ChooseDistrictCard(this));
            }

            sleep((int)(1 + Math.random() * 2000));


            for(int i = 0; i < savedState.getP3Hand().size(); i++)
            {
                CitadelsDistrictCard temp = (CitadelsDistrictCard) savedState.getP3Hand().get(i);
                if (temp.getCost() >= 3)
                {
                    game.sendAction(new CitadelsBuildDistrictCard(this,
                            (CitadelsDistrictCard) savedState.getP3Hand().get(i)));
                    break;
                }
            }

            try {
                if (savedState.getTurn() >= 7) {
                    if (ability == 1) {
                        //assassin
                        if (savedState.getP3Chars(0).getWhichCharacter() == 0 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 0) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //thief
                        if (savedState.getP3Chars(0).getWhichCharacter() == 1 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 1) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //magician
                        if (savedState.getP3Chars(0).getWhichCharacter() == 2 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 2) {
                            // if the AI knows if the other players have an assassin or thief use the ability
                            if (savedState.getTurn() >= 9)
                            {
                                if (savedState.getP1Chars(0).getWhichCharacter() == 0 ||
                                        savedState.getP1Chars(1).getWhichCharacter() == 0 ||
                                        savedState.getP1Chars(0).getWhichCharacter() == 1 ||
                                        savedState.getP1Chars(1).getWhichCharacter() == 1)

                                {
                                    game.sendAction(new UseSpecialAbility(this, 1));
                                }
                                if (savedState.getP3Chars(0).getWhichCharacter() == 0 ||
                                        savedState.getP2Chars(1).getWhichCharacter() == 0 ||
                                        savedState.getP2Chars(0).getWhichCharacter() == 1 ||
                                        savedState.getP2Chars(1).getWhichCharacter() == 1)
                                {
                                    game.sendAction(new UseSpecialAbility(this, 1));
                                }
                            }
                        }
                        //king
                        if (savedState.getP3Chars(0).getWhichCharacter() == 3 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 3) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //bishop
                        if (savedState.getP3Chars(0).getWhichCharacter() == 4 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 4) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //merchant
                        if (savedState.getP3Chars(0).getWhichCharacter() == 5 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 5) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //architect
                        if (savedState.getP3Chars(0).getWhichCharacter() == 6 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 6) {
                            game.sendAction(new UseSpecialAbility(this, whichCharacter));
                        }
                        //warlord
                        if (savedState.getP3Chars(0).getWhichCharacter() == 7 ||
                                savedState.getP3Chars(1).getWhichCharacter() == 7) {
                            if (savedState.getP1Score() >= savedState.getP3Score())
                            {
                                game.sendAction(new UseSpecialAbility(this,
                                        savedState.getP1Chars(0).getWhichCharacter()));
                            }
                            if (savedState.getP2Score() >= savedState.getP3Score())
                            {
                                game.sendAction(new UseSpecialAbility(this,
                                        savedState.getP1Chars(0).getWhichCharacter()));
                            }
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

    /**
     * Gets the player's num
     * @return player num
     *          the player's num
     */
    public int getPlayerNum()
    {
        return this.playerNum;
    }
}