package edu.up.citadels;

/**
 * Created by Victor on 2/20/2017.
 */


public class Player
{
    private String playerName;
    private int playerScore;
    private int gold;
    private boolean isKing;

    public void init(String initplayer_Name, int initplayer_Score, int initgold, boolean initisKing)
    {
        this.playerName = initplayer_Name;
        this.playerScore = initplayer_Score;
        this.gold = initgold;
        this.isKing = initisKing;
    }

    public void setPlayername(String newName)
    {
        this.playerName = newName;
    }

    public String getPlayername()
    {
        return this.playerName;
    }

    public void setScore(int x)
    {
        this.playerScore = x;
    }

    public int getScore()
    {
        return this.playerScore;
    }

    public void setGold(int new_Gold)
    {
        this.gold = new_Gold;
    }

    public int getGold()

    {
        return this.gold;
    }

    public void setKing(boolean x)
    {
        isKing = x;
    }

    public boolean isKing() {
        return this.isKing;
    }
}
