package player;

import android.view.View;

import Info.GameInfo;
import game.GameMainActivity;

/**
 * Created by bryce on 3/3/2017.
 */

public class CitadelsHumanPlayer extends GameHumanPlayer
{
    String name;

    public CitadelsHumanPlayer(String initName)
    {
           super(initName);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }
}
