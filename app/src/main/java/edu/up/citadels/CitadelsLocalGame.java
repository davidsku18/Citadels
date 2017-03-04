package edu.up.citadels;

import android.util.Log;

import ActionMsg.GameAction;
import game.LocalGame;
import player.GamePlayer;

/**
 * Created by bryce on 3/3/2017.
 */

public class CitadelsLocalGame extends LocalGame
{
    //the game's state
    CitadelsGameState state;

    public CitadelsLocalGame() {
        Log.i("SJLocalGame", "creating game");
        // create the state for the beginning of the game
        state = new CitadelsGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p)
    {

    }

    @Override
    protected boolean canMove(int playerIdx)
    {
        return false;
    }

    @Override
    protected String checkIfGameOver()
    {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action)
    {
        return false;
    }
}
