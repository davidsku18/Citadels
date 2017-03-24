package edu.up.citadels;

import org.junit.Test;

import edu.up.citadels.citadels.CitadelsGameState;
import edu.up.citadels.citadels.CitadelsLocalGame;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CitadelsGameStateTest
{

    @Test
    public void testGetPlayerScore() throws Exception
    {
        CitadelsGameState gameState = new CitadelsGameState();
        int testP1Score = gameState.getP1Score();
        int testP2Score = gameState.getP2Score();
        int testP3Score = gameState.getP3Score();
        assertEquals(testP1Score, 0, 0.00);
        assertEquals(testP2Score, 0, 0.00);
        assertEquals(testP3Score, 0, 0.00);
    }

    @Test
    public void testSetPlayerScore() throws Exception
    {
        CitadelsGameState gameState = new CitadelsGameState();
        gameState.setP1Score(6);
        gameState.setP2Score(5);
        gameState.setP3Score(8);
        int testP1Score = gameState.getP1Score();
        int testP2Score = gameState.getP2Score();
        int testP3Score = gameState.getP3Score();
        assertEquals(testP1Score, 6, 0.00);
        assertEquals(testP2Score, 5, 0.00);
        assertEquals(testP3Score, 8, 0.00);
    }

}