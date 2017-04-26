package edu.up.citadels.citadels;

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

    private int playerNum;

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

        sleep((int)(1 + Math.random() * 2000));

        //AI attempts to stockpile 8 gold and only draws district cards when there are no district cards
        // in hand or if AI has more than 8 gold
        if(myPlayer == 1)
        {
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

        sleep((int)(1 + Math.random() * 2000));

        game.sendAction(new EndTurn(this));
    }

    public int getPlayerNum()
    {
        return this.playerNum;
    }
}