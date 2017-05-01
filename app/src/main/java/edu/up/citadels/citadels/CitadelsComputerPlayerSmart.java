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
 * Created by bryce on 4/17/2017.
 */

public class CitadelsComputerPlayerSmart extends GameComputerPlayer
{

    private CitadelsGameState savedState;

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



        //AI attempts to stockpile 8 gold and only draws district cards when there are no district cards
        // in hand or if AI has more than 8 gold
        if(myPlayer == 1)
        {
            //pick character
            sleep(1000 + (int)(Math.random() * 2000));
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

            sleep(1000 + (int)(Math.random() * 2000));

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
            sleep(1000 + (int)(Math.random() * 2000));
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

            sleep(1000 + (int)(Math.random() * 2000));

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
            sleep(1000 + (int)(Math.random() * 2000));
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

            sleep(1000 + (int)(Math.random() * 2000));


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

    public int getPlayerNum()
    {
        return this.playerNum;
    }
}