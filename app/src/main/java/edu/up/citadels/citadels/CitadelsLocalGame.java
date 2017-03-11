package edu.up.citadels.citadels;

import android.util.Log;

import edu.up.citadels.citadels.actions.BuildDistrictCard;
import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.UseSpecialAbility;
import edu.up.citadels.game.actionMsg.GameAction;
import edu.up.citadels.game.LocalGame;
import edu.up.citadels.game.GamePlayer;

/**
 * Created by bryce on 3/3/2017.
 */

public class CitadelsLocalGame extends LocalGame
{
    //the edu.up.citadels.game's state
    CitadelsGameState state;

    public CitadelsLocalGame()
    {
        Log.i("CitadelsLocalGame", "creating edu.up.citadels.game");
        // create the state for the beginning of the edu.up.citadels.game
        state = new CitadelsGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p)
    {
        if(state == null)
        {
            return;
        }

        CitadelsGameState stateForPlayer = new CitadelsGameState(state);

        p.sendInfo(stateForPlayer);
    }

    @Override
    protected boolean canMove(int playerIdx)
    {
        if(playerIdx == state.getTurn())
        {
            return true;
        }else
        {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver()
    {
        int p1Districts = state.getP1City().length;
        int p2Districts = state.getP2City().length;
        int p3Districts = state.getP3City().length;

        /*
        Keep in mind that this is just signaling the end of the game and does not declare a
        winner yet, this is just for the most basic functionality
         */

        if(state.getP1City().length >= 8)
        {
            return "Player 1 has built 8 districts. Game over.";
        }else if(state.getP2City().length >= 8)
        {
            return "Player 2 has built 8 districts. Game over.";
        }else if(state.getP3City().length >= 8)
        {
            return "Player 3 has built 8 districts. Game over.";
        }else
        {
            return null;
        }
    }

    @Override
    protected boolean makeMove(GameAction action)
    {
        int turn = state.getTurn();
        int player;
        if((turn == state.getP1Character1()) || (turn == state.getP1Character2()))
        {
            player = 1;         //character belongs to player 1
        }else if((turn == state.getP2Character1()) || (turn == state.getP2Character2()))
        {
            player = 2;         //character belongs to player 2
        }else if((turn == state.getP3Character1()) || (turn == state.getP3Character2()))
        {
            player = 3;         //character belongs to player 3
        }else
        {
            player = 4;        //this is if no one owns this character
        }

        if(action instanceof TakeGold)
        {
            //this will add 2 gold to whoever the player is
            if(player == 1)
            {
                state.setP1Score(state.getP1Score + 2);
                return true;
            }else if(player == 2)
            {
                state.setP2Score(state.getP2Score + 2);
                return true;
            }else if(player == 3)
            {
                state.setP3Score(state.getP3Score + 2);
                return true;
            }else if(player == 4)
            {
                //do nothing because player 4 doesn't exist
                return true;
            }
        }else if(action instanceof EndTurn)
        {
            if(state.getTurn() != 8)
            {
                state.setTurn(state.getTurn() + 1);
            }else if(state.getTurn() == 8)
            {
                state.setTurn(1);
            }
            return true;
        }else if(action instanceof ChooseDistrictCard)
        {
            if(player == 1)
            {
                p1Hand.add(state.drawCard);
                return true;
            }else if(player == 2)
            {
                p2Hand.add(state.drawCard);
                return true;
            }else if(player == 3)
            {
                p3Hand.add(state.drawCard);
                return true;
            }else if(player == 4)
            {
                //do nothing because player 4 doesn't exist
                return true;
            }
        }else if(action instanceof BuildDistrictCard)
        {
            //this will build the first district card in the hand
            //we will implement fuller functionality later
            if(player == 1)
            {
                p1City.add(p1Hand.get(1));
                p1Hand.remove(1);
                return true;
            }else if(player == 2)
            {
                p2City.add(p22Hand.get(1));
                p2Hand.remove(1);
                return true;
            }else if(player == 3)
            {
                p3City.add(p3Hand.get(1));
                p3Hand.remove(1);
                return true;
            }else if(player == 4)
            {
                //do nothing because player 4 doesn't exist
                return true;
            }
        }else if(action instanceof ChooseCharacterCard)
        {
            //this is just setting them to arbitrary values, we will set more later
            //for basic functionality
            state.setP1Character1(1);
            state.setP1Character2(2);
            state.setP2Character1(3);
            state.setP2Character2(4);
            state.setP3Character1(5);
            state.setP3Character2(6);
            state.setTurn(1);
            return true;
        }else if(action instanceof UseSpecialAbility)
        {
            if(player == 8)
            {

            }else if(player == 2)
            {
            }
        }
    }
}
