package edu.up.citadels.citadels;

import android.util.Log;

import edu.up.citadels.citadels.actions.ChooseCharacterCard2;
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

public class CitadelsLocalGame extends LocalGame {
    //the edu.up.citadels.game's state
    CitadelsGameState state;

    public CitadelsLocalGame() {
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
     * @param p the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        if (state == null) {
            return;
        }

        CitadelsGameState stateForPlayer = new CitadelsGameState(state);

        p.sendInfo(stateForPlayer);
    }

    /**
     * return whether the specified player can move
     *
     * @param playerIdx The player's ID
     * @return boolean
     * Whether the specified player's ID can perform a move
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if (playerIdx == state.getPlayerTurn()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks which player has 8 districts built and ends the game
     *
     * @return String
     * Specifies which player has 8 districts built
     */
    @Override
    protected String checkIfGameOver() {
        if (state.getTurn() == 5) {
            int p1Districts = state.getP1City().size();
            int p2Districts = state.getP2City().size();
            int p3Districts = state.getP3City().size();

            int p1Score = state.getP1Score();
            int p2Score = state.getP2Score();
            int p3Score = state.getP3Score();

            if (p1Districts > 7 || p2Districts > 7 || p3Districts > 7) {

                if (p1Score >= p2Score && p1Score >= p3Score) {
                    return "PLAYER 1 HAS WON! CONGRATULATIONS!";
                } else if (p2Score >= p1Score && p2Score >= p3Score) {
                    return "PLAYER 2 HAS WON! BUMMER.";
                } else if (p3Score >= p2Score && p3Score >= p1Score) {
                    return "PLAYER 3 HAS WON! BUMMER.";
                } else {
                    return "There was a tie!";
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Makes a move on behalf the player
     *
     * @param action The move that the player has sent to the game
     * @return boolean
     * Tells whether the move was legal or not
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if (!(action instanceof CitadelsMoveAction)) {
            return false;
        }

        CitadelsMoveAction cma = (CitadelsMoveAction) action;
        int playerID = getPlayerIdx(cma.getPlayer());

        if (action instanceof TakeGold) {
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
        } else if (action instanceof ChooseCharacterCard) {

            if (playerID == 0 && state.getTurn() == 0)
            {
                ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                state.setP1Character1(ccc.getTheChosenCharacterCard1());
                if (ccc.getTheChosenCharacterCard1() == -1) {

                }
                else
                {
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard1());
                }
            } else if (playerID == 1 && state.getTurn() == 2)
            {
                ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                state.setP2Character1(ccc.getTheChosenCharacterCard1());
                state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard1());
            } else if (playerID == 2)
            {
                ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                state.setP3Character1(ccc.getTheChosenCharacterCard1());
                state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard1());
            }
        } else if (action instanceof ChooseCharacterCard2) {

            state.setTurn(0);

            if (playerID == 0 && state.getTurn() == 1)
            {
                ChooseCharacterCard2 ccc = (ChooseCharacterCard2) cma;
                state.setP1Character2(ccc.getTheChosenCharacterCard2());
                if(ccc.getTheChosenCharacterCard2() == -1)
                {

                }
                else
                {
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard2());
                }

            } else if (playerID == 1)
            {
                ChooseCharacterCard2 ccc = (ChooseCharacterCard2) cma;
                state.setP2Character2(ccc.getTheChosenCharacterCard2());
                state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard2());
            } else if (playerID == 2)
            {
                ChooseCharacterCard2 ccc = (ChooseCharacterCard2) cma;
                state.setP3Character2(ccc.getTheChosenCharacterCard2());
                state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard2());
            }
        }
        if (action instanceof EndTurn)
        {
            if(state.getTurn() < 5)
            {
                state.setTurn(state.getTurn() + 1);
                return true;
            }else if(state.getTurn() == 5)
            {
                state.setTurn(0);
                return true;
            }
        } else if (action instanceof ChooseDistrictCard) {
            if (playerID == 0) {
                state.addToP1Hand(state.drawDistrictCard());
                return true;
            } else if (playerID == 1)
            {
                state.setAction("Player 2 Drew a District Card.");
                state.addToP2Hand(state.drawDistrictCard());
                return true;
            } else if (playerID == 2)
            {
                state.setAction("Player 3 Drew a District Card.");
                state.addToP3Hand(state.drawDistrictCard());
                return true;
            }
            //return true;
        } else if (action instanceof CitadelsBuildDistrictCard)
        {
            CitadelsBuildDistrictCard cbdc = (CitadelsBuildDistrictCard) cma;
            if (playerID == 0)
            {
                if(state.getP1Hand().size() != 0) {
                    if (state.getP1Gold() >= cbdc.getCard().getCost()) {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else {
                        return true;
                    }
                }else{  return true;    }
            } else if (playerID == 1)
            {
                if(state.getP2Hand().size() != 0)
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost()) {
                        state.setAction("Player 2 Built a " + cbdc.getCard().getName() + ".");
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else {
                        return true;
                    }
                }else{  return true;    }
            } else if (playerID == 2)
            {
                if(state.getP3Hand().size() != 0)
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost()) {
                        state.setAction("Player 3 Built a " + cbdc.getCard().getName() + ".");
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else {
                        return true;
                    }
                }else{  return true;    }
            }
            } else if (action instanceof UseSpecialAbility)
            {
                return true;
            } else
            {
                return false;
            }
            return true;
        }
    }

