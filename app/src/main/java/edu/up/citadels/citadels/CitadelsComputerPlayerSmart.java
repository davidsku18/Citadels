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
     * @param myNumber
     *          the comoputer player's number
     */
    public CitadelsComputerPlayerSmart(String initName, int myNumber)
    {
        super(initName);
        this.playerNum = myNumber;
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

        //AI attempts to stockpile 8 gold and only draws district cards when there are no district cards
        // in hand or if AI has more than 8 gold
        if(myPlayer == 1)
        {
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
                   game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP1Hand().get(i)));
                   break;
               }
           }
        }
        else if(myPlayer == 2)
        {
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
                    game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(i)));
                    break;
                }
            }
        }
        else if(myPlayer == 3)
        {
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
                    game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(i)));
                    break;
                }
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