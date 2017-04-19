/*
 *
 * @Authors: Bryce Amato, Gavin Low, Victor Nguyen, Kurtis Davidson
 * Version: 1.1
 * Date: 02/17/2017
 *
 */

package edu.up.citadels.citadels;

import java.util.ArrayList;

import edu.up.citadels.R;
import edu.up.citadels.game.GameMainActivity;
import edu.up.citadels.game.config.GameConfig;
import edu.up.citadels.game.LocalGame;
import edu.up.citadels.game.GamePlayer;
import edu.up.citadels.game.config.GamePlayerType;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */
public class CitadelsMainActivity extends GameMainActivity
{
    public static final int PORT_NUMBER = 4753;

    public GameConfig createDefaultConfig()
    {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("human player")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsHumanPlayer(name, R.layout.activity_main);
            }});
        playerTypes.add(new GamePlayerType("human player")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsHumanPlayer(name, R.layout.activity_main);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (dumb) 1")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayerDumb(name, 1);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (dumb) 2")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayerDumb(name, 2);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (smart) 1")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayerSmart(name, 1);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (smart) 2")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayerSmart(name, 2);
            }
        });

        // Create a edu.up.citadels.game configuration class for Citadels
        GameConfig defaultConfig = new GameConfig(playerTypes, 3, 3, "Citadels", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 2);
        defaultConfig.addPlayer("Computer2", 5);

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Guest", "", 1);

        //done!
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {
        return new CitadelsLocalGame();
    }
}
