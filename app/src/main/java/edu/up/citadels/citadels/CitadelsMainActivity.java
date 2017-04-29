/*
 *
 * @Authors: Bryce Amato, Gavin Low, Victor Nguyen, Kurtis Davidson
 * Version: 1.1
 * Date: 02/17/2017
 *
 */

package edu.up.citadels.citadels;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import edu.up.citadels.R;
import edu.up.citadels.game.GameMainActivity;
import edu.up.citadels.game.GamePlayer;
import edu.up.citadels.game.LocalGame;
import edu.up.citadels.game.config.GameConfig;
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

    /**
     * @Author Victor Nguyen
     *
     * Implementing the method to create the floating menu
     *
     * Sources: https://developer.android.com/guide/topics/ui/menus.html#PopupMenu
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ingame, menu);
    }


    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.rules_Tab:
                View v = getLayoutInflater().inflate(R.layout.rules, null);
                //LayoutInflater rules = LayoutInflater.from(getContext());
                //rules.inflate(R.layout.rules, this);
                //LayoutInflater rules = getLayoutInflater();
                //rules.inflate(R.layout.rules, null);
                return true;

            case R.id.exitgame_Tab:
                setContentView(R.layout.game_config_main);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public GameConfig createDefaultConfig()
    {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Human Player")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsHumanPlayer(name, R.layout.activity_main);
            }});
        playerTypes.add(new GamePlayerType("Human Player No GUI")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsHumanPlayer(name, R.layout.activity_main);
            }
        });
        playerTypes.add(new GamePlayerType("Dumb Computer Player")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayerDumb(name);
            }
        });

        playerTypes.add(new GamePlayerType("Smart Computer Player")
        {
            public GamePlayer createPlayer(String name) {
                return new CitadelsComputerPlayerSmart(name);
            }
        });

        // Create a edu.up.citadels.game configuration class for Citadels
        GameConfig defaultConfig = new GameConfig(playerTypes, 3, 3, "Citadels", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 2); //2
        defaultConfig.addPlayer("Computer 2", 3);  //5

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
