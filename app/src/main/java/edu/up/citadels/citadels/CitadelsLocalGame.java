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
     * @param p the player to notify
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p)
    {
        if (state == null)
        {
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
        if (playerIdx == state.getTurn())
        {
            return true;
        } else
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
    protected String checkIfGameOver()
    {
        if (state.getTurn() == 14)
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
                } else if (p2Score >= p1Score && p2Score >= p3Score)
                {
                    return "PLAYER 2 HAS WON! BUMMER.";
                } else if (p3Score >= p2Score && p3Score >= p1Score)
                {
                    return "PLAYER 3 HAS WON! BUMMER.";
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
        if (!(action instanceof CitadelsMoveAction))
        {
            return false;
        }

        CitadelsMoveAction cma = (CitadelsMoveAction) action;
        int playerID = getPlayerIdx(cma.getPlayer());
        if (this.canMove(getPlayerIdx(cma.getPlayer())))
        {
            playerID = 0;
        } else if (this.canMove(getPlayerIdx(cma.getPlayer())))
        {
            playerID = 1;
        } else if (this.canMove(getPlayerIdx(cma.getPlayer())))
        {
            playerID = 2;
        } else
        {
            playerID = -1;
        }

        if (action instanceof TakeGold)
        {
            if (state.getTurn() == 7)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            } else if (state.getTurn() == 8)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            } else if (state.getTurn() == 9)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            } else if (state.getTurn() == 10)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            }
            else if (state.getTurn() == 11)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            }
            else if (state.getTurn() == 12)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            } else if (state.getTurn() == 13)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            } else if (state.getTurn() == 14)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    state.setP1Gold(state.getP1Gold() + 2);
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    state.setP2Gold(state.getP2Gold() + 2);
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    state.setP3Gold(state.getP3Gold() + 2);
                    return true;
                }
            }
        }
        else if (action instanceof ChooseCharacterCard)
        {
            if (state.getTurn() == 0)
            {
                if (playerID == state.getKing())
                {
                    if (playerID == 0)
                    {
                        return true;
                    } else if (playerID == 1)
                    {
                        return true;
                    } else if (playerID == 2)
                    {
                        return true;
                    }
                }
            } else if (state.getTurn() == 1)
            {
                if (playerID == state.getKing())
                {
                    if (playerID == 0)
                    {
                        return true;
                    } else if (playerID == 1)
                    {
                        return true;
                    } else if (playerID == 2)
                    {
                        return true;
                    }
                }
            } else if (state.getTurn() == 2)
            {
                if (playerID == 0 && state.getKing() == 3)
                {
                    return true;
                } else if (playerID == 1 && state.getKing() == 1)
                {
                    return true;
                } else if (playerID == 2 && state.getKing() == 2)
                {
                    return true;
                }
            } else if (state.getTurn() == 3)
            {
                if (playerID == 0 && state.getKing() == 2)
                {
                    return true;
                } else if (playerID == 1 && state.getKing() == 3)
                {
                    return true;
                } else if (playerID == 2 && state.getKing() == 1)
                {
                    return true;
                }
            } else if (state.getTurn() == 4)
            {
                if (playerID == 0 && state.getKing() == 1)
                {
                    return true;
                } else if (playerID == 1 && state.getKing() == 2)
                {
                    return true;
                } else if (playerID == 2 && state.getKing() == 3)
                {
                    return true;
                }
            } else if (state.getTurn() == 5)
            {
                if (playerID == 0 && state.getKing() == 3)
                {
                    return true;
                } else if (playerID == 1 && state.getKing() == 1)
                {
                    return true;
                } else if (playerID == 2 && state.getKing() == 2)
                {
                    return true;
                }
            } else if (state.getTurn() == 6)
            {
                if (playerID == 0 && state.getKing() == 2)
                {
                    return true;
                } else if (playerID == 1 && state.getKing() == 3)
                {
                    return true;
                } else if (playerID == 2 && state.getKing() == 1)
                {
                    return true;
                }
            }
        }
        else if (action instanceof EndTurn)
        {
            if (state.getTurn() == 7)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 8)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 9)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 10)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 11)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 12)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 13)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 14)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            }
            checkIfGameOver();
            if (state.getTurn() != 14)
            {
                state.setTurn(state.getTurn() + 1);
                return true;
            } else if (state.getTurn() == 14)
            {
                state.setTurn(0);
                return true;
            }
        }
        else if (action instanceof ChooseDistrictCard)
        {
            if (state.getTurn() == 7)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 8)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 9)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 10)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 11)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 12)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 13)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            } else if (state.getTurn() == 14)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    return true;
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    return true;
                }
            }
            return true;
        }
        else if (action instanceof CitadelsBuildDistrictCard)
        {
            CitadelsBuildDistrictCard cbdc = (CitadelsBuildDistrictCard) cma;
            if (state.getTurn() == 7)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }
                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

            } else if (state.getTurn() == 8)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                }
            } else if (state.getTurn() == 9)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }
                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                }
            } else if (state.getTurn() == 10)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                }
            } else if (state.getTurn() == 11)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                }
            } else if (state.getTurn() == 12)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                }
            } else if (state.getTurn() == 13)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                }
            } else if (state.getTurn() == 14)
            {
                if (playerID == 0 && (state.getP1Character1() + 7 == state.getTurn() || state.getP1Character2() + 7 == state.getTurn()))
                {
                    if (state.getP1Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP1City(cbdc.getCard());
                        int index = state.p1FindCard(cbdc.getCard());
                        state.setP1Score(state.getP1Score() + cbdc.getCard().getCost());
                        state.setP1Gold(state.getP1Gold() - cbdc.getCard().getCost());
                        state.removeFromP1Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 1 && (state.getP2Character1() + 7 == state.getTurn() || state.getP2Character2() + 7 == state.getTurn()))
                {
                    if (state.getP2Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP2City(cbdc.getCard());
                        int index = state.p2FindCard(cbdc.getCard());
                        state.setP2Score(state.getP2Score() + cbdc.getCard().getCost());
                        state.setP2Gold(state.getP2Gold() - cbdc.getCard().getCost());
                        state.removeFromP2Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }

                } else if (playerID == 2 && (state.getP3Character1() + 7 == state.getTurn() || state.getP3Character2() + 7 == state.getTurn()))
                {
                    if (state.getP3Gold() >= cbdc.getCard().getCost())
                    {
                        state.addToP3City(cbdc.getCard());
                        int index = state.p3FindCard(cbdc.getCard());
                        state.setP3Score(state.getP3Score() + cbdc.getCard().getCost());
                        state.setP3Gold(state.getP3Gold() - cbdc.getCard().getCost());
                        state.removeFromP3Hand(index);
                        return true;
                    } else
                    {
                        return true;
                    }
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
            } else
            {
                return false;
            }
            return true;
        }
}


