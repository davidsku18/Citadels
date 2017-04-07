package edu.up.citadels.citadels;

import android.util.Log;

import java.util.ArrayList;

import edu.up.citadels.citadels.actions.CitadelsBuildDistrictCard;
import edu.up.citadels.citadels.actions.ChooseCharacterCard;
import edu.up.citadels.citadels.actions.ChooseDistrictCard;
import edu.up.citadels.citadels.actions.CitadelsMoveAction;
import edu.up.citadels.citadels.actions.EndTurn;
import edu.up.citadels.citadels.actions.TakeGold;
import edu.up.citadels.citadels.actions.UseSpecialAbility;
import edu.up.citadels.game.actionMsg.GameAction;
import edu.up.citadels.game.LocalGame;
import edu.up.citadels.game.GamePlayer;

/**
 * The LocalGame class for a Citadels game. Defines and enforces
 * the game rules; handles interactions between players.
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017.
 */

public class CitadelsLocalGame extends LocalGame
{
    //the edu.up.citadels.game's state
    CitadelsGameState state;

    public CitadelsLocalGame()
    {
        Log.i("CitadelsLocalGame", "creating edu.up.citadels.game");
        // create the state for the beginning of the edu.up.citadels.game
        this.state = new CitadelsGameState();
    }
    /**
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player. If the game is not a perfect-information game
     * this method should remove any information from the game that the player is not
     * allowed to know.
     *
     * @param p
     * 			the player to notify
     */
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
    /**
     * return whether the specified player can move
     *
     * @param playerIdx
     *          The player's ID
     * @return boolean
     *          Whether the specified player's ID can perform a move
     */
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

    /**
     * Checks which player has 8 districts built and ends the game
     *
     * @return String
     *          Specifies which player has 8 districts built
     */
    @Override
    protected String checkIfGameOver()
    {
        int p1Districts = state.getP1City().size();
        int p2Districts = state.getP2City().size();
        int p3Districts = state.getP3City().size();

        /*
        Keep in mind that this is just signaling the end of the game and does not declare a
        winner yet, this is just for the most basic functionality
         */

        if(p1Districts >= 7)
        {
            return "Player 1 has built 8 districts. Game over.";
        }else if(p2Districts >= 7)
        {
            return "Player 2 has built 8 districts. Game over.";
        }else if(p3Districts >= 7)
        {
            return "Player 3 has built 8 districts. Game over.";
        }else
        {
            return null;
        }
    }

    /**
     * Makes a move on behalf the player
     *
     * @param action
     *          The move that the player has sent to the game
     * @return boolean
     *          Tells whether the move was legal or not
     */
    @Override
    protected boolean makeMove(GameAction action)
    {
        int turn = state.getTurn();
        int player;
        int kingNum = (int)Math.random()*3;

        //sets the king num
        state.setKing(kingNum);

        if (!(action instanceof CitadelsMoveAction)) {
            return false;
        }

        CitadelsMoveAction  cma = (CitadelsMoveAction) action;

        //get the index of the player making the move;
        int thisPlayerIdx = getPlayerIdx(cma.getPlayer());

        if ((turn == state.getP1Character1()) || (turn == state.getP1Character2()))
        {
            player = thisPlayerIdx;       //character belongs to player 1
        } else if ((turn == state.getP2Character1()) || (turn == state.getP2Character2()))
        {
            player = thisPlayerIdx;         //character belongs to player 2
        } else if ((turn == state.getP3Character1()) || (turn == state.getP3Character2()))
        {
            player = thisPlayerIdx;         //character belongs to player 3
        } else
        {
            player = thisPlayerIdx;        //this is if no one owns this character
        }

        if (action instanceof ChooseCharacterCard)
        {
            //this is just setting them to arbitrary values, we will set more later
            //for basic functionality
            //TODO
            /*
            state.setP1Character1(0);
            state.setP1Character2(1);
            state.setP2Character1(2);
            state.setP2Character2(3);
            state.setP3Character1(4);
            state.setP3Character2(5);
            */
            state.setTurn(0);

            int[] p1Characters = new int[2];
            int[] p2Characters = new int[2];
            int[] p3Characters = new int[2]; // TODO may want to change to CharacterCards later

            if(state.getIsKing() == player)
            {
                for (int i = 0; i < 1; ++i)
                {
                    p1Characters[i] = state.setP1Character1();
                }
            }

            return true;
        }
        else if (action instanceof TakeGold)
        {
            //this will add 2 gold to whoever the player is
            if (player == 0)
            {
                state.setP1Gold(state.getP1Gold() + 2);
                return true;
            } else if (player == 1)
            {
                state.setP2Gold(state.getP2Gold() + 2);
                return true;
            } else if (player == 2)
            {
                state.setP3Gold(state.getP3Gold() + 2);
                return true;
            } else
            {
                //do nothing because player 4 doesn't exist
                return true;
            }
        } else if (action instanceof EndTurn)
        {
            if (state.getTurn() != 8) {
                state.setTurn(state.getTurn() + 1);
                return true;
            } else if (state.getTurn() == 8)
            {
                state.setTurn(1);
                return true;
            }
        } else if (action instanceof ChooseDistrictCard)
        {
            //this will add a district card to whoever the player is // TODO in-case idx does not start at 0
            if (player == 1)
            {
                state.addToP1Hand(state.drawCard());
                state.removeDistrictCard();
                return true;
            } else if (player == 2)
            {
                state.addToP2Hand(state.drawCard());
                state.removeDistrictCard();
                return true;
            } else if (player == 3)
            {
                state.addToP3Hand(state.drawCard());
                state.removeDistrictCard();
                return true;
            } else
            {
                //do nothing because player 4 doesn't exist
                return true;
            }
        } else if (action instanceof CitadelsBuildDistrictCard) {
            //TODO this will build the first district card in the hand
            //we will implement fuller functionality later
            if (player == 1)
            {
                //TODO check and see if the player has enough gold
                state.addToP1City(state.getP1DistrictCard(1));
                state.removeFromP1Hand(1);
                return true;
            } else if (player == 2)
            {
                state.addToP2City(state.getP2DistrictCard(1));
                state.removeFromP2Hand(1);
                return true;
            } else if (player == 3)
            {
                state.addToP3City(state.getP3DistrictCard(1));
                state.removeFromP3Hand(1);
                return true;
            } else
            {
                //do nothing because player 4 doesn't exist
                return true;
            }
        } else if (action instanceof UseSpecialAbility)
        {
            // Assassin special ability
            if (state.getTurn() == 1)
            {
                if(player == 1)
                {
                    state.isCharacterDead(true);
                    return true;
                }else
                {
                    state.isCharacterDead(true);
                    return true;
                    //TODO
                }
            } else if (state.getTurn() == 2)
            {
                // Thief special ability
                if(player == 1)
                {
                    state.setP1Gold(state.getP1Gold() + state.getP2Gold());
                    state.setP2Gold(0);
                    return true;
                } else if (player == 2)
                {
                    state.setP2Gold(state.getP1Gold() + state.getP2Gold());
                    state.setP1Gold(0);
                    return true;
                } else
                {
                    state.setP3Gold(state.getP1Gold() + state.getP3Gold());
                    state.setP1Gold(0);
                    return true;
                }//TODO implement fuller method
            } else if (state.getTurn() == 3) {
                // Magician special ability
                ArrayList<CitadelsDistrictCard> p1Hand = state.getP1Hand();
                ArrayList<CitadelsDistrictCard> p2Hand = state.getP2Hand();
                ArrayList<CitadelsDistrictCard> p3Hand = state.getP3Hand();
                if (player == 1) {
                    state.setP1Hand(p2Hand);
                    state.setP2Hand(p1Hand);
                    return true;
                } else if (player == 2) {
                    state.setP1Hand(p2Hand);
                    state.setP2Hand(p1Hand);
                    return true;
                } else if (player == 3) {
                    state.setP1Hand(p3Hand);
                    state.setP3Hand(p1Hand);
                    return true;
                }
            } else if (state.getTurn() == 8)
            {
                // Warlord special ability
                if(player == 1)
                {
                    state.removeP2BuiltDistrict();
                    return true;
                } else
                {
                    state.removeP1BuiltDistrict();
                    return true;
                }

            } else if (state.getTurn() == 4)
            {
                // TODO King special ability
                if (player == 1) {
                    return true;
                } else if (player == 2)
                {
                    return true;
                } else if (player == 3)
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        }else
        {
            return false;
        }
        return true;
    }
}

