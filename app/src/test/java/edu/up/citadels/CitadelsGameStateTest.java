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
    public void testSetP2Gold() throws Exception
    {
        CitadelsGameState cgs = new CitadelsGameState();
        cgs.setP2Gold(7);
        assertEquals(cgs.getP2Gold(), 7);
    }

    @Test
    public void testGetP1Character() throws Exception
    {
        CitadelsGameState cgs = new CitadelsGameState();
        cgs.setP1Character1(4);
        assertEquals(cgs.getP1Character1(), 4);
    }
}