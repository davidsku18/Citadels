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
 * External Citation
 * Date: 20 March 2017
 * Problem: Didn't know how to implement rules correctly
 * to change
 * Resource:
 * https://github.com/srvegdahl/TttGame/blob/master/app/src
 * /main/java/edu/up/cs301/tictactoe/TTTLocalGame.java
 * Solution: Used code as reference
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 4/30/2017.
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
        if (state.getTurn() == 0)
        {
            //Our turns do not go clockwise or counterclockwise, they depend on the kind and your characters' number
            if (playerIdx == 0 && playerIdx == state.getKing())
            {
                return true;
            } else if (playerIdx == 1 && playerIdx == state.getKing())
            {
                return true;
            } else if (playerIdx == 2 && playerIdx == state.getKing())
            {
                return true;
            } else
            {
                return false;
            }
        }
        if (state.getTurn() == 1)
        {
            if (playerIdx == 0 && playerIdx == state.getKing())
            {
                return true;
            } else if (playerIdx == 1 && playerIdx == state.getKing())
            {
                return true;
            } else if (playerIdx == 2 && playerIdx == state.getKing())
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 2)
        {
            if (playerIdx == 0 && state.getKing() == 2)
            {
                return true;
            } else if (playerIdx == 1 && state.getKing() == 0)
            {
                return true;
            } else if (playerIdx == 2 && state.getKing() == 1)
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 3)
        {
            if (playerIdx == 0 && state.getKing() == 1)
            {
                return true;
            } else if (playerIdx == 1 && state.getKing() == 2)
            {
                return true;
            } else if (playerIdx == 2 && state.getKing() == 0)
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 4)
        {
            if (playerIdx == 0 && state.getKing() == 0)
            {
                return true;
            } else if (playerIdx == 1 && state.getKing() == 1)
            {
                return true;
            } else if (playerIdx == 2 && state.getKing() == 2)
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 5)
        {
            if (playerIdx == 0 && state.getKing() == 2)
            {
                return true;
            } else if (playerIdx == 1 && state.getKing() == 0)
            {
                return true;
            } else if (playerIdx == 2 && state.getKing() == 1)
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 6)
        {
            if (playerIdx == 0 && state.getKing() == 1)
            {
                return true;
            } else if (playerIdx == 1 && state.getKing() == 2)
            {
                return true;
            } else if (playerIdx == 2 && state.getKing() == 0)
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 7)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 8)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 9)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 10)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 11)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 12)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 13)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 14)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else if (state.getTurn() == 15)
        {
            if (playerIdx == 0 && (state.getP1Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP1Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 1 && (state.getP2Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP2Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else if (playerIdx == 2 && (state.getP3Chars(0).getWhichCharacter() + 7 == state.getTurn() ||
                    state.getP3Chars(1).getWhichCharacter() + 7 == state.getTurn()))
            {
                return true;
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    /**
     * Checks which player has 8 districts built and ends the game
     * The winner is then determined by the player who has the highest score
     *
     * @return String
     * Specifies which player has 8 districts built
     */
    @Override
    protected String checkIfGameOver()
    {
        if (state.getTurn() == 0)
        {
            //we need to check and see if anyone has build 8 districts
            int p1Districts = state.getP1City().size();
            int p2Districts = state.getP2City().size();
            int p3Districts = state.getP3City().size();

            //who has the most points when the 8th district is built?
            int p1Score = state.getP1Score();
            int p2Score = state.getP2Score();
            int p3Score = state.getP3Score();

            //booleans for having each colored district for bonus points
            boolean p1Red = false;
            boolean p1Blue = false;
            boolean p1Green = false;
            boolean p1Yellow = false;

            boolean p2Red = false;
            boolean p2Blue = false;
            boolean p2Green = false;
            boolean p2Yellow = false;

            boolean p3Red = false;
            boolean p3Blue = false;
            boolean p3Green = false;
            boolean p3Yellow = false;

            if (p1Districts > 7 || p2Districts > 7 || p3Districts > 7)
            {
                //Checks to see if there is a districts that are red,blue,green, and yellow
                for(int i=0; i < p1Districts; i++)
                {
                    CitadelsDistrictCard temp = state.getP1City().get(i);
                    String tempColor = temp.getColorString();
                    if(tempColor.equals("Red"))
                    {
                        p1Red = true;
                    }
                    else if(tempColor.equals("Blue"))
                    {
                        p1Blue = true;
                    }
                    else if(tempColor.equals("Green"))
                    {
                        p1Green = true;
                    }
                    else if(tempColor.equals("Yellow"))
                    {
                        p1Yellow = true;
                    }
                }

                //Checks to see if there is a districts that are red,blue,green, and yellow
                for(int i=0; i < p2Districts; i++)
                {
                    CitadelsDistrictCard temp = state.getP2City().get(i);
                    String tempColor = temp.getColorString();
                    if(tempColor.equals("Red"))
                    {
                        p2Red = true;
                    }
                    else if(tempColor.equals("Blue"))
                    {
                        p2Blue = true;
                    }
                    else if(tempColor.equals("Green"))
                    {
                        p2Green = true;
                    }
                    else if(tempColor.equals("Yellow"))
                    {
                        p2Yellow = true;
                    }
                }

                //Checks to see if there is a districts that are red,blue,green, and yellow
                for(int i=0; i < p3Districts; i++)
                {
                    CitadelsDistrictCard temp = state.getP3City().get(i);
                    String tempColor = temp.getColorString();
                    if(tempColor.equals("Red"))
                    {
                        p3Red = true;
                    }
                    else if(tempColor.equals("Blue"))
                    {
                        p3Blue = true;
                    }
                    else if(tempColor.equals("Green"))
                    {
                        p3Green = true;
                    }
                    else if(tempColor.equals("Yellow"))
                    {
                        p3Yellow = true;
                    }
                }

                //Adds 3 bonus points to P1 if they have a district of each color
                if(p1Blue && p1Green && p1Red && p1Yellow)
                {
                    state.setP1Score(state.getP1Score() + 3);
                }

                //Adds 3 bonus points to P2 if they have a district of each color
                if(p2Blue && p2Green && p2Red && p2Yellow)
                {
                    state.setP2Score(state.getP2Score() + 3);
                }

                //Adds 3 bonus points to P3 if they have a district of each color
                if(p3Blue && p3Green && p3Red && p3Yellow)
                {
                    state.setP3Score(state.getP3Score() + 3);
                }

                //Checks to see who has the highest score
                if (p1Score >= p2Score && p1Score >= p3Score)
                {
                    return playerNames[0] + " has won!";
                } else if (p2Score >= p1Score && p2Score >= p3Score)
                {
                    return playerNames[1] + " has won!";
                } else if (p3Score >= p2Score && p3Score >= p1Score)
                {
                    return playerNames[2] + " has won!";
                } else
                {
                    return "There was a tie!";
                }
            } else
            {
                return null;
            }
        } else
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
        if (!(action instanceof CitadelsMoveAction))//we don't care
        {
            return false;
        }

        CitadelsMoveAction cma = (CitadelsMoveAction) action;
        int playerID = getPlayerIdx(cma.getPlayer());

        if (action instanceof TakeGold)
        {
            //Make sure the turn is above 6 (so we aren't choosing character cards)
            if (playerID == 0 && canMove(playerID) && state.getTurn() > 6)
            {
                state.setP1Gold(state.getP1Gold() + 2);
                Log.i("Player 1", "Took Gold Success");
                return true;
            } else if (playerID == 1 && canMove(playerID) && state.getTurn() > 6)
            {
                state.setP2Gold(state.getP2Gold() + 2);
                Log.i("Player 2", "Took Gold Success");
                return true;
            } else if (playerID == 2 && canMove(playerID) && state.getTurn() > 6)
            {
                state.setP3Gold(state.getP3Gold() + 2);
                Log.i("Player 3", "Took Gold Success");
                return true;
            } else
            {
                return true;
            }


        } else if (action instanceof ChooseCharacterCard)
        {
            /**
             * So in here, the king will choose a card to be removed from the deck. This is turn 0.
             * The king is decided every round so theoretically a new King will remove the card
             * every turn. Turns 0-6 are removing and choosing characters, and 7-14 are the characters,
             * so they are normal turns.
             */
            if (state.getTurn() == 0)
            {
                //Checks the player's id and if they're king
                if (playerID == 0 && playerID == state.getKing())
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setRemovedCharacter(ccc.getTheChosenCharacterCard());
                    Log.i("Player1", "Actually Removed Card");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 1 && playerID == state.getKing())
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setRemovedCharacter(ccc.getTheChosenCharacterCard());
                    state.setTurn(state.getTurn() + 1);
                    Log.i("Player2", "Actually Removed Card");
                    return true;
                } else if (playerID == 2 && playerID == state.getKing())
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setRemovedCharacter(ccc.getTheChosenCharacterCard());
                    state.setTurn(state.getTurn() + 1);
                    Log.i("Player3", "Actually Removed Card");
                    return true;
                } else
                {
                    return true;
                }
            } else if (state.getTurn() == 1)
            {
                if (playerID == 0 && playerID == state.getKing())
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 1 && playerID == state.getKing())
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setTurn(state.getTurn() + 1);
                    Log.i("Player2", "Actually Took Card");
                    return true;
                } else if (playerID == 2 && playerID == state.getKing())
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setTurn(state.getTurn() + 1);
                    Log.i("Player3", "Actually Took Card");
                    return true;
                } else
                {
                    return true;
                }
            } else if (state.getTurn() == 2)
            {
                if (playerID == 0 && state.getKing() == 2)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char1");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 1 && state.getKing() == 0)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char1");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 2 && state.getKing() == 1)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char1");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else
                {
                    return true;
                }
            } else if (state.getTurn() == 3)
            {
                if (playerID == 0 && state.getKing() == 1)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char1");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 1 && state.getKing() == 2)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char1");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 2 && state.getKing() == 0)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char1");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else
                {
                    return true;
                }
            } else if (state.getTurn() == 4)
            {
                if (playerID == 0 && state.getKing() == 0)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char2");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 1 && state.getKing() == 1)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char2");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 2 && state.getKing() == 2)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char2");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else
                {
                    return true;
                }
            } else if (state.getTurn() == 5)
            {
                if (playerID == 0 && state.getKing() == 2)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char2");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 1 && state.getKing() == 0)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char2");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else if (playerID == 2 && state.getKing() == 1)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 3", "Took Char2");
                    state.setTurn(state.getTurn() + 1);
                    return true;
                } else
                {
                    return true;
                }
            } else if (state.getTurn() == 6)
            {
                if (playerID == 0 && state.getKing() == 1)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp1Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 1", "Took Char2");
                    state.setCharacterCardFromDeck(state.getRemovedCharacter().getWhichCharacter(), state.getRemovedCharacter());
                    // If Assassin is removed
                    if (state.getCharacterDeck(state.getTurn() - 6) != null)
                    {
                        for (int i = state.getTurn() - 6; i < state.getCharacterDeck().length; ++i)
                        {
                            if (state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 7);
                                return true;
                            }
                        }
                    }
                    else
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }
                } else if (playerID == 1 && state.getKing() == 2)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp2Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    Log.i("Player 2", "Took Char2");
                    state.setCharacterCardFromDeck(state.getRemovedCharacter().getWhichCharacter(), state.getRemovedCharacter());
                    // If Assassin is removed
                    if (state.getCharacterDeck(state.getTurn() - 6) != null)
                    {
                        for (int i = state.getTurn() - 6; i < state.getCharacterDeck().length; ++i)
                        {
                            if (state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 7);
                                return true;
                            }
                        }
                    }
                    else
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }
                } else if (playerID == 2 && state.getKing() == 0)
                {
                    // Sets the player's character card to the card they chose
                    ChooseCharacterCard ccc = (ChooseCharacterCard) cma;
                    state.addp3Chars(ccc.getTheChosenCharacterCard());
                    state.removeCharacterCardFromDeck(ccc.getTheChosenCharacterCard().getWhichCharacter());
                    state.setCharacterCardFromDeck(state.getRemovedCharacter().getWhichCharacter(), state.getRemovedCharacter());
                    Log.i("Player 3", "Took Char2");

                    // If Assassin is removed
                    if (state.getCharacterDeck(state.getTurn() - 6) != null)
                    {
                        for (int i = state.getTurn() - 6; i < state.getCharacterDeck().length; ++i)
                        {
                            if (state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 7);
                                return true;
                            }
                        }
                    }
                    else
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }
                } else
                {
                    return true;
                }
            }

        }
        if (action instanceof EndTurn)
        {
            /**
             * Depending on what turn we are on, end turn will be placing the next
             * turn to one of many different things. If we are in turns 0-6, we
             * are just going up by 1. If we are 7-14, we are going up by 1
             * unless the next turn's character is null (no one selected it).
             * In this case we will just go up again. When the turn gets to
             * 14 we will just set it back to 0 and reselect characters.
             */
            state.setBuildLimit(1); //resets the build limit to 1
            if (playerID == 0 && canMove(playerID) && state.getTurn() > 6)
            {
                state.setTurnCounter(state.getTurnCounter() + 1);
                if (state.getTurnCounter() < 6)
                {
                    if (state.getCharacterDeck(state.getTurn() - 6) != null)
                    {
                        for (int i = state.getTurn() - 6; i < state.getCharacterDeck().length; ++i)
                        {
                            if (state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 7);
                                return true;
                            }
                        }
                    }
                    else if (state.getCharacterDeck(state.getTurn() - 6) == null)
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }
                } else if (state.getTurnCounter() == 6)
                {
                    if (state.getP1Chars(0).getWhichCharacter() == 3 || state.getP1Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(0);
                        Log.i("King", "0");
                    } else if (state.getP2Chars(0).getWhichCharacter() == 3 || state.getP2Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(1);
                        Log.i("King", "1");
                    } else if (state.getP3Chars(0).getWhichCharacter() == 3 || state.getP3Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(2);
                        Log.i("King", "2");
                    }
                    state.setRemovedCharacter(null);
                    state.clearallCharacters();
                    state.setTurnCounter(0);
                    state.setTurn(0);
                    Log.i("Reset", "Turn = 0");
                    state.initializeCharacterDeck();
                    Log.i("Reset", "All reset");
                    return true;
                }
            } else if (playerID == 1 && canMove(playerID) && state.getTurn() > 6)
            {
                state.setTurnCounter(state.getTurnCounter() + 1);
                if (state.getTurnCounter() < 6)
                {
                    if (state.getCharacterDeck(state.getTurn() - 6) != null)
                    {
                        for (int i = state.getTurn() - 5; i < state.getCharacterDeck().length; ++i)
                        {
                            if (state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 7);
                                return true;
                            }
                        }
                    } else if (state.getCharacterDeck(state.getTurn() - 6) == null)
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }

                } else if (state.getTurnCounter() == 6)
                {
                    if (state.getP1Chars(0).getWhichCharacter() == 3 ||
                            state.getP1Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(0);
                        Log.i("King", "0");
                    } else if (state.getP2Chars(0).getWhichCharacter() == 3 ||
                            state.getP2Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(1);
                        Log.i("King", "1");
                    } else if (state.getP3Chars(0).getWhichCharacter() == 3 ||
                            state.getP3Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(2);
                        Log.i("King", "2");
                    }
                    state.setRemovedCharacter(null);
                    state.clearallCharacters();
                    state.setTurnCounter(0);
                    state.setTurn(0);
                    Log.i("Reset", "Turn = 0");
                    state.initializeCharacterDeck();
                    Log.i("Reset", "All reset");
                    return true;
                }
            } else if (playerID == 2 && canMove(playerID) && state.getTurn() > 6)
            {
                state.setTurnCounter(state.getTurnCounter() + 1);
                if (state.getTurnCounter() < 6)
                {
                    if (state.getCharacterDeck(state.getTurn() - 6) != null)
                    {
                        for (int i = state.getTurn() - 5; i < state.getCharacterDeck().length; ++i)
                        {
                            if (state.getCharacterDeck(i) == null)
                            {
                                state.setTurn(i + 7);
                                return true;
                            }
                        }
                    } else if (state.getCharacterDeck(state.getTurn() - 6) == null )
                    {
                        state.setTurn(state.getTurn() + 1);
                        Log.i("Turncount", "+1");
                        return true;
                    }
                } else if (state.getTurnCounter() == 6)
                {
                    if (state.getP1Chars(0).getWhichCharacter() == 3 ||
                            state.getP1Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(0);
                        Log.i("King", "0");
                    } else if (state.getP2Chars(0).getWhichCharacter() == 3 ||
                            state.getP2Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(1);
                        Log.i("King", "1");
                    } else if (state.getP3Chars(0).getWhichCharacter() == 3 ||
                            state.getP3Chars(1).getWhichCharacter() == 3)
                    {
                        state.setKing(2);
                        Log.i("King", "2");
                    }
                    state.setRemovedCharacter(null);
                    state.clearallCharacters();
                    state.setTurnCounter(0);
                    state.setTurn(0);
                    Log.i("Reset", "Turn = 0");
                    state.initializeCharacterDeck();
                    Log.i("Reset", "All reset");
                    return true;
                }

            }
        } else if (action instanceof ChooseDistrictCard)
        {
            /**
             * checks the player's id and gives them a district card
             * Just take the top district card and add it to the appropriate player's hand.
             * Also, the game state checks to make sure there are cards in the deck
             */
            if (playerID == 0 && state.getTurn() > 6)
            {
                state.addToP1Hand(state.drawDistrictCard());
                return true;
            } else if (playerID == 1 && state.getTurn() > 6)
            {
                state.setAction("Player 2 Drew a District Card.");
                state.addToP2Hand(state.drawDistrictCard());
                return true;
            } else if (playerID == 2 && state.getTurn() > 6)
            {
                state.setAction("Player 3 Drew a District Card.");
                state.addToP3Hand(state.drawDistrictCard());
                return true;
            }
        } else if (action instanceof CitadelsBuildDistrictCard)
        {
            // checks the player's id build's their specified district card
            CitadelsBuildDistrictCard cbdc = (CitadelsBuildDistrictCard) cma;
            if (playerID == 0 && canMove(playerID) && state.getTurn() > 6)
            {
                /**
                 * this will ensure that the specific card is not yet in the city
                 * because we are not allowed to build more than 1 of the same district
                 */
                boolean unique = true;
                for(int i = 0; i < state.getP1City().size(); ++i)
                {
                    if(state.getP1City().get(i).getName().equals(cbdc.getCard().getName()))
                    {
                        unique = false;
                    }
                }
                if (state.getP1Hand().size() != 0 && unique)
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())//if we can afford it
                    {
                        state.addToP1City(cbdc.getCard());
                        if ((cbdc.getCard().getColorString().equals(state.getP1Chars(0).getCharacterColorString()))
                                || (cbdc.getCard().getColorString().equals(state.getP1Chars(1).getCharacterColorString())))
                        {
                            //if the color of the district corresponds to the color of your characters, you get an extra gold!
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());//update score
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());//update gold count
                        try
                        {
                            state.removeFromP1Hand(index);
                        }
                        catch (ArrayIndexOutOfBoundsException aiob)
                        {
                            Log.i("Player3", "makeMove: Array out of bounds tried to remove past 0");
                        }
                        return true;
                    } else
                    {
                        return true;
                    }
                } else
                {
                    return true;
                }
            } else if (playerID == 1 && canMove(playerID) && state.getTurn() > 6)
            {
                /**
                 * This will ensure that the specific card is not yet in the city because
                 * we are not allowed to build more than 1 of the same district
                 */
                boolean unique = true;
                for(int i = 0; i < state.getP2City().size(); ++i)
                {
                    if(state.getP2City().get(i).getName().equals(cbdc.getCard().getName()))
                    {
                        unique = false;
                    }
                }
                if (state.getP2Hand().size() != 0 && unique)
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())//make sure they can afford it
                    {
                        if ((cbdc.getCard().getColorString().equals(state.getP2Chars(0).getCharacterColorString()))
                                || (cbdc.getCard().getColorString().equals(state.getP2Chars(1).getCharacterColorString())))
                        {
                            //get an extra gold if your district and character color are the same
                            state.setP2Gold(state.getP2Gold() + 1);
                        }
                        state.setAction("Player 2 Built a " + cbdc.getCard().getName() + ".");
                        state.addToP2City(cbdc.getCard());
                        int index = state.getP2City().indexOf(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());//update score
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());//update gold
                        try
                        {
                            state.removeFromP2Hand(index);
                        }
                        catch (ArrayIndexOutOfBoundsException aiob)
                        {
                            Log.i("Player2", "makeMove: Array out of bounds tried to remove past 0");
                        }
                        return true;
                    } else
                    {
                        return true;
                    }
                } else
                {
                    return true;
                }
            } else if (playerID == 2 && canMove(playerID) && state.getTurn() > 6)
            {
                /**
                 * this will ensure that the specific card is not yet in the city
                 * because we are not allowed to build more than 1 of the same district
                 */
                boolean unique = true;
                for(int i = 0; i < state.getP3City().size(); ++i)
                {
                    if(state.getP3City().get(i).getName().equals(cbdc.getCard().getName()))
                    {
                        unique = false;
                    }
                }
                if (state.getP3Hand().size() != 0 && unique)
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())//make sre they can afford it
                    {
                        if ((cbdc.getCard().getColorString().equals(state.getP3Chars(0).getCharacterColorString()))
                                || (cbdc.getCard().getColorString().equals(state.getP3Chars(1).getCharacterColorString())))
                        {
                            state.setP3Gold(state.getP3Gold() + 1);
                        }
                        state.setAction("Player 3 Built a " + cbdc.getCard().getName() + ".");
                        state.addToP3City(cbdc.getCard());
                        int index = state.getP3City().indexOf(cbdc.getCard());
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
                    } else
                    {
                        return true;
                    }
                } else
                {
                    return true;
                }
            }
            } else if (action instanceof UseSpecialAbility)
        {
            UseSpecialAbility usa = (UseSpecialAbility) cma;
            int theCharacter = usa.getCharacter();

            // Assassin Ability
            if (state.getTurn() == 7)
            {
                if (playerID == 0 && (state.getP1Chars(0).getWhichCharacter() == 0 ||
                        state.getP1Chars(1).getWhichCharacter() == 0))
                {
                    if ((theCharacter == state.getP2Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP2Chars(1).getWhichCharacter()))
                    {
                        state.setCharacterCardFromDeck(theCharacter, state.getP1Chars(0));
                        return true;
                    } else if ((theCharacter == state.getP3Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP3Chars(1).getWhichCharacter()))
                    {
                        state.setCharacterCardFromDeck(theCharacter, state.getP1Chars(0));
                        return true;
                    }
                }
            } else if (state.getTurn() == 8)
            {
                if (playerID == 0 && (state.getP1Chars(0).getWhichCharacter() == 1 ||
                        state.getP1Chars(1).getWhichCharacter() == 1))
                {
                    if ((theCharacter == state.getP2Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP2Chars(1).getWhichCharacter()))
                    {
                        state.setP1Gold(state.getP1Gold() + state.getP2Gold());
                        state.setP2Gold(0);
                        return true;
                    } else if ((theCharacter == state.getP3Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP3Chars(1).getWhichCharacter()))
                    {
                        state.setP1Gold(state.getP1Gold() + state.getP3Gold());
                        state.setP3Gold(0);
                        return true;
                    }
                } else if (playerID == 1)
                {
                    if ((theCharacter == state.getP1Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP1Chars(1).getWhichCharacter()))
                    {
                        state.setP2Gold(state.getP2Gold() + state.getP1Gold());
                        state.setP1Gold(0);
                        return true;
                    } else if ((theCharacter == state.getP3Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP3Chars(1).getWhichCharacter()))
                    {
                        state.setP2Gold(state.getP2Gold() + state.getP3Gold());
                        state.setP3Gold(0);
                        return true;
                    }
                } else if (playerID == 2)
                {
                    if ((theCharacter == state.getP2Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP2Chars(1).getWhichCharacter()))
                    {
                        state.setP3Gold(state.getP3Gold() + state.getP2Gold());
                        state.setP2Gold(0);
                        return true;
                    } else if ((theCharacter == state.getP1Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP1Chars(1).getWhichCharacter()))
                    {
                        state.setP3Gold(state.getP3Gold() + state.getP1Gold());
                        state.setP1Gold(0);
                        return true;
                    }
                }
            } else if (state.getTurn() == 9) {//Magician Ability
                if (playerID == 0)
                {
                    if ((theCharacter == state.getP2Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP2Chars(1).getWhichCharacter()))
                    {
                        ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                        state.setP2Hand(state.getP1Hand());
                        state.setP1Hand(temp);
                        return true;
                    } else if ((theCharacter == state.getP3Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP3Chars(1).getWhichCharacter()))
                    {
                        ArrayList<CitadelsDistrictCard> temp = state.getP3Hand();
                        state.setP3Hand(state.getP1Hand());
                        state.setP1Hand(temp);
                        return true;
                    }
                } else if (playerID == 1)
                {
                    if ((theCharacter == state.getP1Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP1Chars(1).getWhichCharacter()))
                    {
                        ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                        state.setP1Hand(state.getP2Hand());
                        state.setP2Hand(temp);
                        return true;
                    } else if ((theCharacter == state.getP3Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP3Chars(1).getWhichCharacter()))
                    {
                        ArrayList<CitadelsDistrictCard> temp = state.getP3Hand();
                        state.setP3Hand(state.getP2Hand());
                        state.setP2Hand(temp);
                        return true;
                    }
                } else if (playerID == 2)
                {
                    if ((theCharacter == state.getP1Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP1Chars(1).getWhichCharacter()))
                    {
                        ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                        state.setP1Hand(state.getP3Hand());
                        state.setP3Hand(temp);
                        return true;
                    } else if ((theCharacter == state.getP2Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP2Chars(1).getWhichCharacter()))
                    {
                        ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                        state.setP2Hand(state.getP3Hand());
                        state.setP3Hand(temp);
                        return true;
                    }
                }
        }else if (state.getTurn() == 10) {
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
            } else if (state.getTurn() == 11) {
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
            } else if (state.getTurn() == 12) {
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
            } else if (state.getTurn() == 13) {
                //architect
                if (playerID == 0)
                {
                    state.setBuildLimit(3);

                    ArrayList<CitadelsDistrictCard> temp = state.getP1Hand();
                    CitadelsDistrictCard cdc = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc);
                    state.removeCard();
                    CitadelsDistrictCard cdc2 = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc2);
                    state.removeCard();
                    state.setP1Hand(temp);

                    return true;


                }
                else if (playerID == 1)
                {
                    state.setBuildLimit(3);


                    ArrayList<CitadelsDistrictCard> temp = state.getP2Hand();
                    CitadelsDistrictCard cdc = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc);
                    state.removeCard();
                    CitadelsDistrictCard cdc2 = state.getDeckOrderDistricts().get(0);
                    temp.add(cdc2);
                    state.removeCard();
                    state.setP2Hand(temp);
                    return true;
                }
                else if (playerID == 2)
                {
                    state.setBuildLimit(3);


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
            } else if (state.getTurn() == 14) {
                //warlord
                if (playerID == 0)
                {
                    for (int i = 0; i < state.getP1City().size(); ++i)
                    {
                        CitadelsDistrictCard cdc = state.getP1City().get(i);
                        if (cdc.getColorString().equals("Red")) {
                            state.setP1Gold(state.getP1Gold() + 1);
                        }
                    }
                    if((theCharacter == state.getP2Chars(0).getWhichCharacter()) || (theCharacter == state.getP2Chars(1).getWhichCharacter()) && (4 == state.getP2Chars(0).getWhichCharacter()) || (4 == state.getP2Chars(1).getWhichCharacter()))
                    {
                        int goldAmount = state.getP1Gold();
                        int cost = 0;
                        int indexOfDistrict = 0;
                        for(int i = 0; i < state.getP2City().size(); ++i)
                        {
                            if(state.getP2City().get(i).getCost()-1 < goldAmount && state.getP2City().get(i).getCost()>cost)
                            {
                                cost = state.getP2City().get(i).getCost();
                                indexOfDistrict = i;
                            }
                        }
                        state.setP1Gold(state.getP1Gold()-(state.getP2City().get(indexOfDistrict).getCost()-1));
                        state.setP2Score(state.getP2Score()-state.getP2City().get(indexOfDistrict).getCost());
                        state.removeFromP2City(indexOfDistrict);
                        return true;
                    }else if((theCharacter == state.getP3Chars(0).getWhichCharacter()) ||
                            (theCharacter == state.getP3Chars(1).getWhichCharacter()) && (4 == state.getP3Chars(0).getWhichCharacter()) || (4 == state.getP3Chars(1).getWhichCharacter()))
                    {
                        int goldAmount = state.getP1Gold();
                        int cost = 0;
                        int indexOfDistrict = 0;
                        for(int i = 0; i < state.getP3City().size(); ++i)
                        {
                            if(state.getP3City().get(i).getCost()-1 < goldAmount && state.getP3City().get(i).getCost()>cost)
                            {
                                cost = state.getP3City().get(i).getCost();
                                indexOfDistrict = i;
                            }
                        }
                        state.setP1Gold(state.getP1Gold()-(state.getP3City().get(indexOfDistrict).getCost()-1));
                        state.setP3Score(state.getP3Score()-state.getP3City().get(indexOfDistrict).getCost());
                        state.removeFromP3City(indexOfDistrict);
                        return true;
                    }
                        //return true;
                } else if (playerID == 1) {
                    for (int i = 0; i < state.getP2City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP2City().get(i);
                        if (cdc.getColorString().equals("Red")) {
                            state.setP2Gold(state.getP2Gold() + 1);
                        }
                    }
                    if ((state.getP1Chars(0).getWhichCharacter() == theCharacter || state.getP1Chars(1).getWhichCharacter() == theCharacter) && (4 == state.getP1Chars(0).getWhichCharacter()) || (4 == state.getP1Chars(1).getWhichCharacter()))
                    {
                        int goldAmount = state.getP2Gold();
                        int cost = 0;
                        int indexOfDistrict = 0;
                        for (int i = 0; i < state.getP1City().size(); ++i)
                        {
                            if (state.getP1City().get(i).getCost() - 1 < goldAmount && state.getP1City().get(i).getCost() > cost)
                            {
                                cost = state.getP1City().get(i).getCost();
                                indexOfDistrict = i;
                            }
                        }
                        state.setP2Gold(state.getP2Gold() - (state.getP1City().get(indexOfDistrict).getCost() - 1));
                        state.setP1Score(state.getP1Score() - state.getP1City().get(indexOfDistrict).getCost());
                        state.removeFromP1City(indexOfDistrict);
                        return true;
                    }
                    else if ((state.getP3Chars(0).getWhichCharacter() == theCharacter || state.getP3Chars(1).getWhichCharacter() == theCharacter) && (4 != state.getP3Chars(0).getWhichCharacter()) && (4 != state.getP3Chars(1).getWhichCharacter()))
                    {
                        int goldAmount = state.getP2Gold();
                        int cost = 0;
                        int indexOfDistrict = 0;
                        for (int i = 0; i < state.getP3City().size(); ++i)
                        {
                            if (state.getP3City().get(i).getCost() - 1 < goldAmount && state.getP3City().get(i).getCost() > cost)
                            {
                                cost = state.getP3City().get(i).getCost();
                                indexOfDistrict = i;
                            }
                        }
                        state.setP2Gold(state.getP2Gold() - (state.getP3City().get(indexOfDistrict).getCost() - 1));
                        state.setP3Score(state.getP1Score() - state.getP3City().get(indexOfDistrict).getCost());
                        state.removeFromP3City(indexOfDistrict);
                        return true;
                    }


                } else if (playerID == 2) {
                    for (int i = 0; i < state.getP3City().size(); ++i) {
                        CitadelsDistrictCard cdc = state.getP3City().get(i);
                        if (cdc.getColorString().equals("Red")) {
                            state.setP3Gold(state.getP3Gold() + 1);
                        }
                    }
                    if ((state.getP1Chars(0).getWhichCharacter() == theCharacter || state.getP1Chars(1).getWhichCharacter() == theCharacter) && (4 != state.getP1Chars(0).getWhichCharacter()) && (4 != state.getP1Chars(1).getWhichCharacter()))
                    {
                        int goldAmount = state.getP3Gold();
                        int cost = 0;
                        int indexOfDistrict = 0;
                        for (int i = 0; i < state.getP1City().size(); ++i)
                        {
                            if (state.getP1City().get(i).getCost() - 1 < goldAmount && state.getP1City().get(i).getCost() > cost)
                            {
                                cost = state.getP1City().get(i).getCost();
                                indexOfDistrict = i;
                            }
                        }
                        state.setP3Gold(state.getP3Gold() - (state.getP1City().get(indexOfDistrict).getCost() - 1));
                        state.setP1Score(state.getP1Score() - state.getP1City().get(indexOfDistrict).getCost());
                        state.removeFromP1City(indexOfDistrict);
                        return true;
                    }
                    else if ((state.getP2Chars(0).getWhichCharacter() == theCharacter || state.getP2Chars(1).getWhichCharacter() == theCharacter) && (4 != state.getP2Chars(0).getWhichCharacter()) && (4 != state.getP2Chars(1).getWhichCharacter()))
                    {
                        int goldAmount = state.getP3Gold();
                        int cost = 0;
                        int indexOfDistrict = 0;
                        for (int i = 0; i < state.getP2City().size(); ++i)
                        {
                            if (state.getP2City().get(i).getCost() - 1 < goldAmount && state.getP2City().get(i).getCost() > cost)
                            {
                                cost = state.getP2City().get(i).getCost();
                                indexOfDistrict = i;
                            }
                        }
                        state.setP3Gold(state.getP3Gold() - (state.getP2City().get(indexOfDistrict).getCost() - 1));
                        state.setP2Score(state.getP2Score() - state.getP2City().get(indexOfDistrict).getCost());
                        state.removeFromP2City(indexOfDistrict);
                        return true;
                    }
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

