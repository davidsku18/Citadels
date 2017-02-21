package edu.up.citadels;

/**
 * Created by Victor on 2/20/2017.
 */

public class Player
{
    private String player_Name;
    private int player_Score;
    private int gold;
    private boolean hasCrown;

    public void setPlayer_Name(String newName)
    {
        this.player_Name = newName;
    }

    public String getPlayer_Name()
    {
        return this.player_Name;
    }

    public void setPlayer_Score(int x)
    {
        this.player_Score = x;
    }

    public int getPlayer_Score()
    {
        return this.player_Score;
    }

    public void setGold(int new_Gold)
    {
        this.gold = new_Gold;
    }

    public int getGold()
    {
        return this.gold;
    }

    public boolean isHasCrown() {
        return this.hasCrown;
    }
}
