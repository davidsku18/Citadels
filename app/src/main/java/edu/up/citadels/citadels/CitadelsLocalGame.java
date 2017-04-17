package edu.up.citadels.citadels;

import android.util.Log;

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
        if(playerIdx == state.getPlayerTurn())
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
    protected boolean makeMove(GameAction action) {
        int turn = state.getTurn();
        int buildNum;
        int chosenNum;

        if (!(action instanceof CitadelsMoveAction)) {
            return false;
        }

        CitadelsMoveAction cma = (CitadelsMoveAction) action;
        int playerID = getPlayerIdx(cma.getPlayer());


        if (action instanceof ChooseCharacterCard) {
            // Rolls for a random number to determine who will be the king
            state.setRollKing();

            // Sets the turn to zero issuing a new round of character choosing
            state.setTurn(0);

            // first person two choose is the person who has the crown
            // if king is equal to player 0 (player 1), then they get to choose their characters first
            // state.getKing() != playerID && state.getKing() != 1 && state.getKing() == 2 || state.getKing() != playerID && state.getKing() != 2 && state.getKing() == 1 || state.getKing() == playerID
            // loop through until all players have chosen their characters
            while (state.getTurn() != 2) {
                if (state.getKing() == playerID || playerID == 0) {
                    chosenNum = 0;
                    //sets the player's characters to the chosen character card
                    while (chosenNum != 2) {
                        if (chosenNum == 0) {
                            // TODO thread needed to wait for click on the button or will just crash due to not knowing what theChosenCharacterCard is
                            state.setP1Character1(state.getChosenCharacterCard());
                            state.removeCharacterCard(state.getChosenCharacterCard());
                            chosenNum++;
                            //increments chosenNum to determine whether the player has chosen their first character card
                        }
                        if (chosenNum == 1) {
                            state.setP1Character2(state.getChosenCharacterCard());
                            state.removeCharacterCard(state.getChosenCharacterCard());
                            chosenNum++;
                            //increments chosenNum to determine whether the player has chosen their second character card
                        }
                    }
                    state.setTurn(state.getTurn()+1);
                    return true;
                } else if (state.getKing() == playerID || playerID == 1) {
                    chosenNum = 0;
                    // if chosenCharacterCard returns null have the player choose again
                    while (state.getChosenCharacterCard() == -1 || chosenNum != 2) {
                        if (chosenNum == 0) {
                            // TODO thread needed to wait for click on the button or will just crash due to not knowing what theChosenCharacterCard is
                            state.setP2Character1(state.getChosenCharacterCard());
                            state.removeCharacterCard(state.getChosenCharacterCard());
                            chosenNum++;
                        } else if (chosenNum == 1) {
                            state.setP1Character2(state.getChosenCharacterCard());
                            state.removeCharacterCard(state.getChosenCharacterCard());
                            chosenNum++;
                        }
                    }
                    state.setTurn(state.getTurn()+1);
                    return true;
                } else if (state.getKing() == playerID || playerID == 2) {
                    chosenNum = 0;
                    while (state.getChosenCharacterCard() == -1 || chosenNum != 2) {
                        if (chosenNum == 0) {
                            // TODO thread needed to wait for click on the button or will just crash due to not knowing what theChosenCharacterCard is
                            state.setP3Character1(state.getChosenCharacterCard());
                            state.removeCharacterCard(state.getChosenCharacterCard());
                            chosenNum++;
                        } else if (chosenNum == 1) {
                            state.setP3Character2(state.getChosenCharacterCard());
                            state.removeCharacterCard(state.getChosenCharacterCard());
                            chosenNum++;
                        }
                    }
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
            }
        }
        while (state.getTurn() != 7) {
            if (action instanceof TakeGold)
            {
                if (playerID == 0) {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1) {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2) {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
                return true;
            } else if (action instanceof ChooseDistrictCard)
            {
                if (playerID == 0) {
                    state.addToP1Hand(state.drawCard());
                    state.removeDistrictCard();
                    return true;
                } else if (playerID == 1) {
                    state.addToP2Hand(state.drawCard());
                    state.removeDistrictCard();
                    return true;
                } else if (playerID == 2) {
                    state.addToP3Hand(state.drawCard());
                    state.removeDistrictCard();
                    return true;
                }
                return true;
            } else if (action instanceof CitadelsBuildDistrictCard)
            {
                //TODO this will build the first district card in the hand
                if (playerID == 0) {
                    buildNum=0;
                    // allows the player to build district cards to a specific amount or until they end their turn
                    while (!(action instanceof EndTurn) || buildNum != 2 )
                    {
                        if (state.getP1Gold() >= state.getTheDistrictCardToBeBuilt().getCost())
                        {
                            state.addToP1City(state.getTheDistrictCardToBeBuilt());
                            state.removeFromP1Hand(state.getTheChosenDistrict());
                            buildNum++;
                        } else {
                            Log.i("CitadelsLocalGame", "makeMove: CitadelsBuildDistrictCard: Not enough gold for P1");
                        }
                        return true;
                    }
                } else if (playerID == 1)
                {
                    buildNum=0;
                    while (!(action instanceof EndTurn) || buildNum != 2)
                    {
                        if (state.getP2Gold() >= state.getTheDistrictCardToBeBuilt().getCost())
                        {
                            state.addToP2City(state.getTheDistrictCardToBeBuilt());
                            state.removeFromP2Hand(0);
                            buildNum++;
                        } else {
                            Log.i("CitadelsLocalGame", "makeMove: CitadelsBuildDistrictCard: Not enough gold for P2");
                        }
                        return true;
                    }
                } else if (playerID == 2)
                {
                    buildNum=0;
                    while (!(action instanceof EndTurn) || buildNum != 2)
                    {
                        if (state.getP3Gold() >= state.getTheDistrictCardToBeBuilt().getCost())
                        {
                            state.addToP3City(state.getP3DistrictCard(0));
                            state.removeFromP3Hand(0);
                            buildNum++;
                        } else {
                            Log.i("CitadelsLocalGame", "makeMove: CitadelsBuildDistrictCard: Not enough gold for P3");
                        }
                        return true;
                    }
                }
                return true;
            } else if (action instanceof UseSpecialAbility) {
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
            } else if (action instanceof EndTurn) {
                if (state.getTurn() != 7) {
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (state.getTurn() == 7) {
                    // state.setTurn(0);
                    //TODO need to adjust the turn due to while loop
                    return true;
                }
            } else {
                return false;
            }
            //increments the turn
        }
        return true;
    }
}

