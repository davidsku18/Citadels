package edu.up.citadels.citadels;

import android.util.Log;

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

    public CitadelsLocalGame() {
        Log.i("Citadels", "creating edu.up.citadels");
        // create the state for the beginning of the edu.up.citadels
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
