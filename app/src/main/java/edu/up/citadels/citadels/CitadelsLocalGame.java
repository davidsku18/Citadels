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
    protected boolean canMove(int playerIdx)
    {
        if(state.getTurn() == 0)
        {
            if (playerIdx == 0 && playerIdx == state.getKing())
            {
                return true;
            }
            else if (playerIdx == 1 && playerIdx == state.getKing())
            {
                return true;
            }
            else if (playerIdx == 2 && playerIdx == state.getKing())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 1)
        {
            if (playerIdx == 0 && state.getKing()== 2)
            {
                return true;
            }
            else if (playerIdx == 1 && state.getKing() == 0)
            {
                return true;
            }
            else if (playerIdx == 2 && state.getKing() == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 2)
        {
            if (playerIdx == 0 && state.getKing()== 1)
            {
                return true;
            }
            else if (playerIdx == 1 && state.getKing() == 2)
            {
                return true;
            }
            else if (playerIdx == 2 && state.getKing() == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 3)
        {
            if (playerIdx == 0 && state.getKing()== 0)
            {
                return true;
            }
            else if (playerIdx == 1 && state.getKing() == 1)
            {
                return true;
            }
            else if (playerIdx == 2 && state.getKing() == 2)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 4)
        {
            if (playerIdx == 0 && state.getKing()== 2)
            {
                return true;
            }
            else if (playerIdx == 1 && state.getKing() == 0)
            {
                return true;
            }
            else if (playerIdx == 2 && state.getKing() == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 5)
        {
            if (playerIdx == 0 && state.getKing()== 1)
            {
                return true;
            }
            else if (playerIdx == 1 && state.getKing() == 2)
            {
                return true;
            }
            else if (playerIdx == 2 && state.getKing() == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 6)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter()+ 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 7)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 8)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 9)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 10)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 11)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 12)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 13)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(state.getTurn() == 14)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP1Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP2Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 6 == state.getTurn() || state.getP3Chars(1).getWhichCharacter() + 6 == state.getTurn()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        else
        {
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
        if (state.getTurn() == 0)
        {
            int p1Districts = state.getP1City().size();
            int p2Districts = state.getP2City().size();
            int p3Districts = state.getP3City().size();

            int p1Score = state.getP1Score();
            int p2Score = state.getP2Score();
            int p3Score = state.getP3Score();

            if (p1Districts > 7 || p2Districts > 7 || p3Districts > 7)
            {

                if (p1Score >= p2Score && p1Score >= p3Score)
                {
                    return "PLAYER 1 HAS WON! CONGRATULATIONS!";
                }
                else if (p2Score >= p1Score && p2Score >= p3Score)
                {
                    return "PLAYER 2 HAS WON! BUMMER.";
                }
                else if (p3Score >= p2Score && p3Score >= p1Score)
                {
                    return "PLAYER 3 HAS WON! BUMMER.";
                }
                else
                {
                    return "There was a tie!";
                }
            }
            else
            {
                return null;
            }
        }
        else
        {
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
    protected boolean makeMove(GameAction action)
    {
        if (!(action instanceof CitadelsMoveAction))
        {
            return false;
        }

        CitadelsMoveAction cma = (CitadelsMoveAction) action;
        int playerID = getPlayerIdx(cma.getPlayer());

        if (action instanceof TakeGold)
        {
            if(playerID == 0 && canMove(playerID) && state.getTurn()>5)
            {
                state.setP1Gold(state.getP1Gold() + 2);
                Log.i("Player 1" ,"Took Gold Success");
                return true;
            }
            else if(playerID == 1 && canMove(playerID) && state.getTurn()>5)
            {
                state.setP2Gold(state.getP2Gold() + 2);
                Log.i("Player 2" ,"Took Gold Success");
                return true;
            }
            else if(playerID == 2 && canMove(playerID) && state.getTurn()>5)
            {
                state.setP3Gold(state.getP3Gold() + 2);
                Log.i("Player 3" ,"Took Gold Success");
                return true;
            }
            else
            {
                return true;
            }


        }
        else if (action instanceof ChooseCharacterCard)
        {
            if(state.getTurn() == 0)
            {
                if (playerID == 0 && playerID == state.getKing())
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.setP1Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char1 & Turn = 1");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 1 && playerID == state.getKing())
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.setP2Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setTurn(state.getTurn()+1);
                    Log.i("Player2", "Actually Took Card");
                    return true;
                }
                else if (playerID == 2 && playerID == state.getKing())
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.setP3Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setTurn(state.getTurn()+1);
                    Log.i("Player3", "Actually Took Card");
                    return true;
                }
                else
                {
                    return true;
                }
            }
            else if(state.getTurn() == 1)
            {
                if (playerID == 0 && state.getKing()== 2)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.setP1Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char1");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 1 && state.getKing() == 0)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.setP2Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char1");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 2 && state.getKing() == 1)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.setP3Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char1");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else
                {
                    return true;
                }
            }
            else if(state.getTurn() == 2)
            {
                if (playerID == 0 && state.getKing()== 1)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.setP1Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char1");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 1 && state.getKing() == 2)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.setP2Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char1");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 2 && state.getKing() == 0)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.setP3Character1(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char1");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else
                {
                    return true;
                }
            }
            else if(state.getTurn() == 3)
            {
                if (playerID == 0 && state.getKing()== 0)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.setP1Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 1 && state.getKing() == 1)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.setP2Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 2 && state.getKing() == 2)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.setP3Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else
                {
                    return true;
                }
            }
            else if(state.getTurn() == 4)
            {
                if (playerID == 0 && state.getKing()== 2)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.setP1Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 1 && state.getKing() == 0)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.setP2Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 2 && state.getKing() == 1)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.setP3Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else
                {
                    return true;
                }
            }
            else if(state.getTurn() == 5)
            {
                if (playerID == 0 && state.getKing()== 1)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.setP1Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 1 && state.getKing() == 2)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.setP2Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else if (playerID == 2 && state.getKing() == 0)
                {
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.setP3Character2(ccc.getTheChosenCharacterCard().getWhichCharacter(), ccc.getTheChosenCharacterCard().getColor());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char2");
                    state.setTurn(state.getTurn()+1);
                    return true;
                }
                else
                {
                    return true;
                }
            }

        }
        if (action instanceof EndTurn)
        {

            if(playerID == 0 && canMove(playerID) && state.getTurn()>5)
            {
                state.setTurnCounter(state.getTurnCounter()+1);
                if (state.getTurnCounter()<6)
                {
                    if (state.getCharacterDeck(state.getTurn()-5)!=null)
                    {
                        for(int i = state.getTurn() - 5; i<state.getCharacterDeck().length; ++i)
                        {
                            if(state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 6);
                                return true;
                            }
                        }
                    }
                    else if(state.getCharacterDeck(state.getTurn()-5) == null)
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }
                }
                else if(state.getTurnCounter()==6)
                {
                    state.clearallCharacters();
                    state.setTurnCounter(0);
                    state.setTurn(0);
                    Log.i("Reset", "Turn = 0");
                    state.initializeCharacterDeck();
                    Log.i("Reset", "All reset");
                    return true;
                }
            }
            else if(playerID == 1 && canMove(playerID) && state.getTurn()>5)
            {
                state.setTurnCounter(state.getTurnCounter()+1);
                if (state.getTurnCounter()<6)
                {
                    if (state.getCharacterDeck(state.getTurn()-5)!=null)
                    {
                        for(int i = state.getTurn() - 5; i<state.getCharacterDeck().length; ++i)
                        {
                            if(state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 6);
                                return true;
                            }
                        }
                    }
                else if(state.getCharacterDeck(state.getTurn()-5) == null)
                {
                    state.setTurn(state.getTurn() + 1);
                    Log.i("Turncount", "+1");
                    return true;
                }

                }
                else if(state.getTurnCounter()==6)
                {
                    state.clearallCharacters();
                    state.setTurnCounter(0);
                    state.setTurn(0);
                    Log.i("Reset", "Turn = 0");
                    state.initializeCharacterDeck();
                    Log.i("Reset", "All reset");
                    return true;
                }
            }
            else if(playerID == 2 && canMove(playerID) && state.getTurn()>5)
            {
                state.setTurnCounter(state.getTurnCounter()+1);
                if (state.getTurnCounter()<6)
                {
                    if (state.getCharacterDeck(state.getTurn()-5)!=null)
                    {
                        for(int i = state.getTurn() - 5; i<state.getCharacterDeck().length; ++i)
                        {
                            if(state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 6);
                                return true;
                            }
                        }
                    }
                    else if(state.getCharacterDeck(state.getTurn()-5) == null)
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }
                }
                else if(state.getTurnCounter()==6)
                {
                    state.clearallCharacters();
                    state.setTurnCounter(0);
                    state.setTurn(0);
                    Log.i("Reset", "Turn = 0");
                    state.initializeCharacterDeck();
                    Log.i("Reset", "All reset");
                    return true;
                }

            }
        }
        else if (action instanceof ChooseDistrictCard) {
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
        }else if (action instanceof CitadelsBuildDistrictCard)
        {
            CitadelsBuildDistrictCard cbdc = (CitadelsBuildDistrictCard) cma;
            if (playerID == 0 && canMove(playerID) && state.getTurn()>5)
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
                        try
                        {
                            state.removeFromP1Hand(index);
                        }
                        catch (ArrayIndexOutOfBoundsException aiob)
                        {
                            Log.i("Player3", "makeMove: Array out of bounds tried to remove past 0");
                        }
                        return true;
                    } else {
                        return true;
                    }
                }else{  return true;    }
            }
            else if (playerID == 1 && canMove(playerID) && state.getTurn()>5)
            {
                if(state.getP2Hand().size() != 0)
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        if((cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP2Character1())))
                                || (cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP2Character2()))))
                        {
                            state.setP2Gold(state.getP2Gold() + 1);
                        }
                        state.setAction("Player 2 Built a " + cbdc.getCard().getName() + ".");
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        try
                        {
                            state.removeFromP2Hand(index);
                        }
                        catch (ArrayIndexOutOfBoundsException aiob)
                        {
                            Log.i("Player2", "makeMove: Array out of bounds tried to remove past 0");
                        }
                        return true;
                    } else {
                        return true;
                    }
                }else{  return true;    }
            }
            else if (playerID == 2 && canMove(playerID) && state.getTurn()>5)
            {
                if(state.getP3Hand().size() != 0)
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        if((cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP3Character1())))
                                || (cbdc.getCard().getColorString().equals(state.getCharacterColor(state.getP3Character2()))))
                        {
                            state.setP3Gold(state.getP3Gold() + 1);
                        }
                        state.setAction("Player 3 Built a " + cbdc.getCard().getName() + ".");
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        try
                        {
                            state.removeFromP3Hand(index);
                        }
                        catch (ArrayIndexOutOfBoundsException aiob)
                        {
                            Log.i("Player3", "makeMove: Array out of bounds tried to remove past 0");
                        }
                        return true;
                    } else {
                        return true;
                    }
                }else{  return true;    }
            }
            } else if (action instanceof UseSpecialAbility) {
            //TODO: Change the state turn numbers to current turn number + 1 when victor implements the remove character from deck as first turn!!!!!
            UseSpecialAbility usa = (UseSpecialAbility) cma;
            int theCharacter = usa.getCharacter();

            if (state.getTurn() == 6) //Assassin ability
            {
                //TODO implement a way to skip the turn of the player who is killed
            }

            if (state.getTurn() == 7) //thief ability
            {
                if (playerID == 0 && (state.getP1Chars(0).getWhichCharacter() == 1) || (state.getP1Chars(1).getWhichCharacter() == 1)) {
                    if ((theCharacter == state.getP2Chars(0).getWhichCharacter()) || (theCharacter == state.getP2Chars(1).getWhichCharacter())) {
                        state.setP1Gold(state.getP1Gold() + state.getP2Gold());
                        state.setP2Gold(0);
                        return true;
                    } else if ((theCharacter == state.getP3Chars(0).getWhichCharacter()) || (theCharacter == state.getP3Chars(1).getWhichCharacter())) {
                        state.setP1Gold(state.getP1Gold() + state.getP3Gold());
                        state.setP3Gold(0);
                        return true;
                    }
                } else if (playerID == 1 && (state.getP2Chars(0).getWhichCharacter() == 1) || (state.getP2Chars(1).getWhichCharacter() == 1)) {
                    if ((theCharacter == state.getP1Chara1().getWhichCharacter()) || (theCharacter == state.getP1Chara2().getWhichCharacter())) {
                        state.setP2Gold(state.getP2Gold() + state.getP1Gold());
                        state.setP1Gold(0);
                        return true;
                    } else if ((theCharacter == state.getP3Chara1().getWhichCharacter()) || (theCharacter == state.getP3Chara2().getWhichCharacter())) {
                        state.setP2Gold(state.getP2Gold() + state.getP3Gold());
                        state.setP3Gold(0);
                        return true;
                    }
                } else if (playerID == 2 && (state.getP3Chars(0).getWhichCharacter() == 1) || (state.getP3Chars(1).getWhichCharacter() == 1)) {
                    if ((theCharacter == state.getP2Chara1().getWhichCharacter()) || (theCharacter == state.getP2Chara2().getWhichCharacter())) {
                        state.setP3Gold(state.getP3Gold() + state.getP2Gold());
                        state.setP2Gold(0);
                        return true;
                    } else if ((theCharacter == state.getP1Chara1().getWhichCharacter()) || (theCharacter == state.getP1Chara2().getWhichCharacter())) {
                        state.setP3Gold(state.getP3Gold() + state.getP1Gold());
                        state.setP1Gold(0);
                        return true;
                    }
                } else {
                    return true;
                }
            }

            if (state.getTurn() == 8) //Magician Ability
                if (playerID == 0) {
                    //TODO go in here and find which character was selected, make sure it isn't null, find who owns it
                    if ((theCharacter == state.getP2Chara1().getWhichCharacter()) || (theCharacter == state.getP2Chara2().getWhichCharacter())) {
                        ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                        state.setP2Hand(state.getP1Hand());
                        state.setP1Hand(temp);
                        return true;
                    } else if ((theCharacter == state.getP3Chara1().getWhichCharacter()) || (theCharacter == state.getP3Chara2().getWhichCharacter())) {
                        ArrayList<CitadelsDistrictCard> temp = state.getP3Hand();
                        state.setP3Hand(state.getP1Hand());
                        state.setP1Hand(temp);
                        return true;
                    }
                } else if (playerID == 1) {
                    if ((theCharacter == state.getP1Chara1().getWhichCharacter()) || (theCharacter == state.getP1Chara2().getWhichCharacter())) {
                        ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                        state.setP1Hand(state.getP2Hand());
                        state.setP2Hand(temp);
                        return true;
                    } else if ((theCharacter == state.getP3Chara1().getWhichCharacter()) || (theCharacter == state.getP3Chara2().getWhichCharacter())) {
                        ArrayList<CitadelsDistrictCard> temp = state.getP3Hand();
                        state.setP3Hand(state.getP2Hand());
                        state.setP2Hand(temp);
                        return true;
                    }
                } else if (playerID == 2) {
                    if ((theCharacter == state.getP1Chara1().getWhichCharacter()) || (theCharacter == state.getP1Chara2().getWhichCharacter())) {
                        ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                        state.setP1Hand(state.getP3Hand());
                        state.setP3Hand(temp);
                        return true;
                    } else if ((theCharacter == state.getP2Chara1().getWhichCharacter()) || (theCharacter == state.getP2Chara2().getWhichCharacter())) {
                        ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                        state.setP2Hand(state.getP3Hand());
                        state.setP3Hand(temp);
                        return true;
                    }
                } else {
                    return true;
                }
            if (state.getTurn() == 9) {
                //king
                if (playerID == 0) {
                    for (int i = 0; i < state.getP1City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP1City().get(i);
                        if (cdc.getColorString().equals("Yellow")) {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
                    }
                    return true;
                } else if (playerID == 1) {
                    for (int i = 0; i < state.getP2City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP2City().get(i);
                        if (cdc.getColorString().equals("Yellow")) {
                            state.setP2Gold(state.getP2Gold() + 1);
                        }
                    }
                    return true;
                } else if (playerID == 2) {
                    for (int i = 0; i < state.getP3City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP3City().get(i);
                        if (cdc.getColorString().equals("Yellow")) {
                            state.setP3Gold(state.getP3Gold() + 1);
                        }
                    }
                    return true;
                }
            } else if (state.getTurn() == 10) {
                //bishop
                if (playerID == 0) {
                    for (int i = 0; i < state.getP1City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP1City().get(i);
                        if (cdc.getColorString().equals("Blue")) {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
                    }
                    return true;
                } else if (playerID == 1) {
                    for (int i = 0; i < state.getP2City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP2City().get(i);
                        if (cdc.getColorString().equals("Blue")) {
                            state.setP2Gold(state.getP2Gold() + 1);
                        }
                    }
                    return true;
                } else if (playerID == 2) {
                    for (int i = 0; i < state.getP3City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP3City().get(i);
                        if (cdc.getColorString().equals("Blue")) {
                            state.setP3Gold(state.getP3Gold() + 1);
                        }
                    }
                    return true;
                }
            } else if (state.getTurn() == 11) {
                //merchant
                if (playerID == 0) {
                    for (int i = 0; i < state.getP1City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP1City().get(i);
                        if (cdc.getColorString().equals("Green")) {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
                    }
                    //merchant gets 1 extra
                    state.setP1Gold(state.getP1Gold() + 1);
                    return true;
                } else if (playerID == 1) {
                    for (int i = 0; i < state.getP2City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP2City().get(i);
                        if (cdc.getColorString().equals("Green")) {
                            state.setP2Gold(state.getP2Gold() + 1);
                        }
                    }
                    //merchant gets 1 extra
                    state.setP2Gold(state.getP2Gold() + 1);
                    return true;
                } else if (playerID == 2) {
                    for (int i = 0; i < state.getP3City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP3City().get(i);
                        if (cdc.getColorString().equals("Green")) {
                            state.setP3Gold(state.getP3Gold() + 1);
                        }
                    }
                    //merchant gets one extra
                    state.setP3Gold(state.getP3Gold() + 1);
                    return true;
                }
            } else if (state.getTurn() == 12) {
                //architect
                if (playerID == 0) {
                    ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                    CitadelsDistrictCard cdc = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc);
                    state.removeCard();
                    CitadelsDistrictCard cdc2 = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc2);
                    state.removeCard();
                    state.setP1Hand(temp);

                    //TODO determine how to know when 3 districts are built and set the build limit back to 1
                    //allows architect to build 3 districts... possibly move to after choosing architect card
                    // seems a tad not intuitive here... must use ability before building districts
                    //state.setBuildLimit(3);
                    return true;


                } else if (playerID == 1) {
                    ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                    CitadelsDistrictCard cdc = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc);
                    state.removeCard();
                    CitadelsDistrictCard cdc2 = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc2);
                    state.removeCard();
                    state.setP2Hand(temp);
                    return true;
                } else if (playerID == 2) {
                    ArrayList<CitadelsDistrictCard> temp = state.getP3Hand();
                    CitadelsDistrictCard cdc = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc);
                    state.removeCard();
                    CitadelsDistrictCard cdc2 = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc2);
                    state.removeCard();
                    state.setP3Hand(temp);
                    return true;
                }
            } else if (state.getTurn() == 13) {
                //warlord
                if (playerID == 0) {
                    //TODO determine which district is chosen to be destroyed and make sure it's not a bishop district
                    for (int i = 0; i < state.getP1City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP1City().get(i);
                        if (cdc.getColorString().equals("Red")) {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
                    }
                    return true;
                } else if (playerID == 1) {
                    for (int i = 0; i < state.getP2City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP2City().get(i);
                        if (cdc.getColorString().equals("Red")) {
                            state.setP2Gold(state.getP2Gold() + 1);
                        }
                    }
                    return true;
                } else if (playerID == 2) {
                    for (int i = 0; i < state.getP3City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP3City().get(i);
                        if (cdc.getColorString().equals("Red")) {
                            state.setP3Gold(state.getP3Gold() + 1);
                        }
                    }
                    return true;
                }
            }
            return true;
        }
        else
        {
            return false;
        }
        return true;
    }
}

