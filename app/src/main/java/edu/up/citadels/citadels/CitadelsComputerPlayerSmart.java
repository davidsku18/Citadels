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






        //Prototype Smart AI
        if(myPlayer == 1)
        {
        }
        else if(myPlayer == 2)
        {
            sleep( 1000 + ( (int)(Math.random() * 2000) ) );
            if(savedState.getP2Gold()<3)
            {
                game.sendAction(new TakeGold(this));
            }
            else if (savedState.getP2Hand().size()>3)
            {
                game.sendAction(new TakeGold(this));
            }
            else if (savedState.getP2Hand().size()<2)
            {
                game.sendAction(new ChooseDistrictCard(this));
            }
            else
            {
                int whatToDo = (int) (Math.random() * 2);
                if (whatToDo == 0)
                {
                    game.sendAction(new ChooseDistrictCard(this));
                }
                else
                {
                    game.sendAction(new TakeGold(this));
                }

            }
            sleep( 2000 + ( (int)(Math.random() * 2000) ) );
            game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP2Hand().get(0)));
            game.sendAction(new EndTurn(this));
        }
        else if(myPlayer == 3)
        {
            sleep( 1000 + ( (int)(Math.random() * 2000) ) );
            if(savedState.getP3Gold()<3 )
            {
                game.sendAction(new TakeGold(this));
            }
            else if (savedState.getP3Hand().size()>3)
            {
                game.sendAction(new TakeGold(this));
            }
            else if (savedState.getP3Hand().size()<2)
            {
                game.sendAction(new ChooseDistrictCard(this));
            }
            else
            {
                int whatToDo = (int) (Math.random() * 2);
                if (whatToDo == 0)
                {
                    game.sendAction(new ChooseDistrictCard(this));
                }
                else
                {
                    game.sendAction(new TakeGold(this));
                }
            }
            sleep( 2000 + ( (int)(Math.random() * 2000) ) );
            game.sendAction(new CitadelsBuildDistrictCard(this, (CitadelsDistrictCard) savedState.getP3Hand().get(0)));
            game.sendAction(new EndTurn(this));
        }

/*
        if (whatToDo == 0)
        {
            game.sendAction(new ChooseDistrictCard(this));
        }
        else
        {
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

    */

    }

    public int getPlayerNum()
    {
        return this.playerNum;
    }
}