/*
 *
 * @Authors: Bryce Amato, Gavin Low, Victor Nguyen, Kurtis Davidson
 * Version: 1.1
 * Date: 02/17/2017
 *
 */

package edu.up.citadels.citadels;

import java.util.ArrayList;

import edu.up.citadels.game.GameMainActivity;
import edu.up.citadels.game.config.GameConfig;
import edu.up.citadels.game.LocalGame;
import edu.up.citadels.game.GamePlayer;
import edu.up.citadels.game.config.GamePlayerType;


public class CitadelsMainActivity extends GameMainActivity
{
    public GameConfig createDefaultConfig()
    {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("human player (green)")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("human player (yellow)")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsHumanPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (normal)")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (fast)")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (slow)")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (very fast)")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("computer player (very slow)")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayer(name);
            }
        });

        // Create a edu.up.citadels.game configuration class for SlapJack
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Citadels", 2017);

        // Add the default players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 2);

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
