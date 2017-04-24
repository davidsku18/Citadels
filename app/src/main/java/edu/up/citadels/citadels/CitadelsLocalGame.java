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

            state.setTurn(0);

            if (playerID == 0) {
                ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                state.setP1Character1(ccc.getTheChosenCharacterCard());
                state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard());

            } else if (playerID == 1) {
                ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                state.setP2Character1(ccc.getTheChosenCharacterCard());
                state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard());
            } else if (playerID == 2) {
                ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                state.setP3Character1(ccc.getTheChosenCharacterCard());
                state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard());
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
                        if((cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP1Character1())))
                            || (cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP1Character2()))))
                        {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
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
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        if((cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP2Character1())))
                                || (cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP2Character2()))))
                        {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
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
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        if((cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP3Character1())))
                                || (cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP3Character2()))))
                        {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
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
                //thief
                if(state.getTurn() == 1)
                {
                    int p1Gold = state.getP1Gold();
                    int p2Gold = state.getP2Gold();
                    int p3Gold = state.getP3Gold();
                    if(playerID == 0)
                    {
                        if(p2Gold >= p3Gold)
                        {
                            state.setP1Gold(state.getP1Gold() + state.getP2Gold());
                            state.setP2Gold(0);
                        }else if(p3Gold > p2Gold)
                        {
                            state.setP1Gold(state.getP1Gold() + state.getP3Gold());
                            state.setP3Gold(0);
                        }
                    }else if(playerID == 1)
                    {
                        if(p1Gold >= p3Gold)
                        {
                            state.setP2Gold(state.getP2Gold() + state.getP1Gold());
                            state.setP1Gold(0);
                        }else if(p3Gold > p1Gold)
                        {
                            state.setP2Gold(state.getP2Gold() + state.getP3Gold());
                            state.setP3Gold(0);
                        }
                    }else if(playerID == 2)
                    {
                        if(p2Gold >= p1Gold)
                        {
                            state.setP3Gold(state.getP3Gold() + state.getP2Gold());
                            state.setP2Gold(0);
                        }else if(p1Gold > p2Gold)
                        {
                            state.setP3Gold(state.getP3Gold() + state.getP1Gold());
                            state.setP1Gold(0);
                        }
                    }
                }else if(state.getTurn() == 2)
                {
                    //magician
                    int p1HandSize = state.getP1Hand().size();
                    int p2HandSize = state.getP2Hand().size();
                    int p3HandSize = state.getP3Hand().size();
                    if(playerID == 0)
                    {
                        if(p2HandSize >= p3HandSize)
                        {
                            ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                            state.setP2Hand(state.getP1Hand());
                            state.setP1Hand(temp);

                        }else if(p3HandSize > p2HandSize)
                        {
                            ArrayList<CitadelsDistrictCard> temp = state.getP3Hand();
                            state.setP3Hand(state.getP1Hand());
                            state.setP1Hand(temp);
                        }
                    }else if(playerID == 1)
                    {
                        if(p1HandSize >= p3HandSize)
                        {
                            ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                            state.setP1Hand(state.getP2Hand());
                            state.setP2Hand(temp);

                        }else if(p3HandSize > p1HandSize)
                        {
                            ArrayList<CitadelsDistrictCard> temp = state.getP3Hand();
                            state.setP3Hand(state.getP2Hand());
                            state.setP2Hand(temp);
                        }
                    }else if(playerID == 2)
                    {
                        if(p1HandSize >= p2HandSize)
                        {
                            ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                            state.setP1Hand(state.getP3Hand());
                            state.setP3Hand(temp);

                        }else if(p2HandSize > p1HandSize)
                        {
                            ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                            state.setP2Hand(state.getP3Hand());
                            state.setP3Hand(temp);
                        }
                    }
                }else if(state.getTurn() == 3)
                {
                    //king
                    if(playerID == 0)
                    {
                        for(int i = 0; i < state.getP1City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP1City().get(i);
                            if(cdc.getColorString().equals("Yellow"))
                            {
                                state.setP1Gold(state.getP1Gold() + 1);
                            }
                        }
                    }else if(playerID == 1)
                    {
                        for(int i = 0; i < state.getP2City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP2City().get(i);
                            if(cdc.getColorString().equals("Yellow"))
                            {
                                state.setP2Gold(state.getP2Gold() + 1);
                            }
                        }
                    }else if(playerID == 2)
                    {
                        for(int i = 0; i < state.getP3City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP3City().get(i);
                            if(cdc.getColorString().equals("Yellow"))
                            {
                                state.setP3Gold(state.getP3Gold() + 1);
                            }
                        }
                    }
                }else if(state.getTurn() == 4)
                {
                    //bishop
                    if(playerID == 0)
                    {
                        for(int i = 0; i < state.getP1City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP1City().get(i);
                            if(cdc.getColorString().equals("Blue"))
                            {
                                state.setP1Gold(state.getP1Gold() + 1);
                            }
                        }
                    }else if(playerID == 1)
                    {
                        for(int i = 0; i < state.getP2City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP2City().get(i);
                            if(cdc.getColorString().equals("Blue"))
                            {
                                state.setP2Gold(state.getP2Gold() + 1);
                            }
                        }
                    }else if(playerID == 2)
                    {
                        for(int i = 0; i < state.getP3City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP3City().get(i);
                            if(cdc.getColorString().equals("Blue"))
                            {
                                state.setP3Gold(state.getP3Gold() + 1);
                            }
                        }
                    }
                }else if(state.getTurn() == 5)
                {
                    //merchant
                    if(playerID == 0)
                    {
                        for(int i = 0; i < state.getP1City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP1City().get(i);
                            if(cdc.getColorString().equals("Green"))
                            {
                                state.setP1Gold(state.getP1Gold() + 1);
                            }
                        }
                        //merchant gets 1 extra
                        state.setP1Gold(state.getP1Gold() + 1);
                    }else if(playerID == 1)
                    {
                        for(int i = 0; i < state.getP2City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP2City().get(i);
                            if(cdc.getColorString().equals("Green"))
                            {
                                state.setP2Gold(state.getP2Gold() + 1);
                            }
                        }
                        //merchant gets 1 extra
                        state.setP2Gold(state.getP2Gold() + 1);
                    }else if(playerID == 2)
                    {
                        for(int i = 0; i < state.getP3City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP3City().get(i);
                            if(cdc.getColorString().equals("Green"))
                            {
                                state.setP3Gold(state.getP3Gold() + 1);
                            }
                        }
                        //merchant gets an extra
                        state.setP3Gold(state.getP3Gold() + 1);
                    }
                }else if(state.getTurn() == 6)
                {

                }else if(state.getTurn() == 7)
                {
                    //warlord
                    if(playerID == 0)
                    {
                        for(int i = 0; i < state.getP1City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP1City().get(i);
                            if(cdc.getColorString().equals("Red"))
                            {
                                state.setP1Gold(state.getP1Gold() + 1);
                            }
                        }
                    }else if(playerID == 1)
                    {
                        for(int i = 0; i < state.getP2City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP2City().get(i);
                            if(cdc.getColorString().equals("Red"))
                            {
                                state.setP2Gold(state.getP2Gold() + 1);
                            }
                        }
                    }else if(playerID == 2)
                    {
                        for(int i = 0; i < state.getP3City().size(); ++i)
                        {
                            CitadelsDistrictCard cdc = state.getP3City().get(i);
                            if(cdc.getColorString().equals("Red"))
                            {
                                state.setP3Gold(state.getP3Gold() + 1);
                            }
                        }
                    }
                }
                return true;
            } else
            {
                return false;
            }
            return true;
        }
    }

