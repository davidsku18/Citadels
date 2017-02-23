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

    // Creates the Player
    public Player(String initplayer_Name, int initplayer_Score, int initgold, boolean initisKing)
    {
        this.playerName = initplayer_Name;
        this.playerScore = initplayer_Score;
        this.gold = initgold;
        this.isKing = initisKing;
    }

    // Copies the player
    public Player (Player orig)
    {
        this.playerName = orig.playerName;
        this.playerScore = orig.playerScore;
        this.gold = orig.gold;
        this.isKing = orig.isKing;
    }


    // Getters and Setters for player variables
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
        this.isKing = x;
    }

    public boolean isKing() {
        return this.isKing;
    }
}
