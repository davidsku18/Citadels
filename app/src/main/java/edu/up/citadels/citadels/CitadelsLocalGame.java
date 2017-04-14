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
        TODO Keep in mind that this is just signaling the end of the game and does not declare a
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
        if (!(action instanceof CitadelsMoveAction)) {
            return false;
        }

        CitadelsMoveAction cma = (CitadelsMoveAction) action;
        int playerID = getPlayerIdx(cma.getPlayer());

        if (action instanceof TakeGold)
        {
            if(playerID == 0)
            {
                state.setP1Gold(state.getP1Gold() + 2);
            }else if(playerID == 1)
            {
                state.setP2Gold(state.getP2Gold() + 2);
            }else if(playerID == 2)
            {
                state.setP3Gold(state.getP3Gold() + 2);
            }
            return true;
        }


        if (action instanceof ChooseCharacterCard)
        {
            //this is just setting them to arbitrary values, we will set more later
            //for basic functionality
            //TODO

            state.setP1Character1(0);
            state.setP1Character2(1);
            state.setP2Character1(2);
            state.setP2Character2(3);
            state.setP3Character1(4);
            state.setP3Character2(5);

            state.setTurn(0);

            int[] p1Characters = new int[2];
            int[] p2Characters = new int[2];
            int[] p3Characters = new int[2]; // TODO may want to change to CharacterCards later

            // first person two choose is the person who has the crown
            // if king is equal to player 0 (player 1), then they get to choose their characters first
            if(state.getKing() == 0) {
                chosenNum = 0;
                //sets the player's characters to the chosen character card
                while (chosenNum != 2) {
                    if (chosenNum == 0) {
                        state.setP1Character1(state.chosenCharacterCard());
                        state.removeCharacterCard(state.chosenCharacterCard());
                        chosenNum++;
                        //increments chosenNum to determine whether the player has chosen their first character card
                    }
                    if (chosenNum == 1) {
                        state.setP1Character2(state.chosenCharacterCard());
                        state.removeCharacterCard(state.chosenCharacterCard());
                        chosenNum++;
                        //increments chosenNum to determine whether the player has chosen their second character card
                    }
                    return true;
                }
            }
            else if (player == 1)
            {
                chosenNum = 0;
                // could also check if character deck is null
                while (state.chosenCharacterCard() == -1 || chosenNum != 2)
                {
                    if (chosenNum == 0)
                    {
                        state.setP2Character1(state.chosenCharacterCard());
                        state.removeCharacterCard(state.chosenCharacterCard());
                        chosenNum++;
                    }
                    else if (chosenNum == 1) {
                        state.setP1Character2(state.chosenCharacterCard());
                        state.removeCharacterCard(state.chosenCharacterCard());
                        chosenNum++;
                    }
                }
                return true;
            }
            else if (player == 2)
            {
                chosenNum = 0;
                while (state.chosenCharacterCard() == -1 || chosenNum != 2)
                {
                    if (chosenNum == 0)
                    {
                        state.setP3Character1(state.chosenCharacterCard());
                        state.removeCharacterCard(state.chosenCharacterCard());
                        chosenNum++;
                    }
                    else if (chosenNum == 1) {
                        state.setP3Character2(state.chosenCharacterCard());
                        state.removeCharacterCard(state.chosenCharacterCard());
                        chosenNum++;
                    }
                }
                return true;
            }
            return true;
        }
         else if (action instanceof EndTurn)
        {
            if (state.getTurn() != 7) {
                state.setTurn(state.getTurn() + 1);
                return true;
            } else if (state.getTurn() == 7)
            {
                state.setTurn(0);
                return true;
            }
        } else if (action instanceof ChooseDistrictCard)
        {
            if(playerID == 0)
            {
                state.addToP1Hand(state.drawCard());
                state.removeDistrictCard();
            }else if(playerID == 1)
            {
                state.addToP2Hand(state.drawCard());
                state.removeDistrictCard();
            }else if(playerID == 2)
            {
                state.addToP3Hand(state.drawCard());
                state.removeDistrictCard();
            }
            return true;
        } else if (action instanceof CitadelsBuildDistrictCard)
        {
            //TODO this will build the first district card in the hand

            if(playerID == 0)
            {
                state.addToP1City(state.getP1DistrictCard(0));
                state.removeFromP1Hand(0);
            }else if(playerID == 1)
            {
                state.addToP2City(state.getP2DistrictCard(0));
                state.removeFromP2Hand(0);
            }else if(playerID == 2)
            {
                state.addToP3City(state.getP3DistrictCard(0));
                state.removeFromP3Hand(0);
            }
            return true;
        } else if (action instanceof UseSpecialAbility)
        {
            // Assassin special ability
            /*if (state.getTurn() == 1)
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
            }*/
            return true;
        }else
        {
            return false;
        }
        return true;
    }
}

