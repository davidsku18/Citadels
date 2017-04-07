package edu.up.citadels.citadels;

import java.util.ArrayList;

/**
 *
 *
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 2/20/2017.
 */


public class CitadelsPlayer
{
    private String playerName;
    private int playerNum;
    private int playerScore;
    private int gold;
    private boolean isKing;
    private ArrayList<CitadelsDistrictCard> playerHand = new ArrayList<CitadelsDistrictCard>();
    private ArrayList<CitadelsDistrictCard> playerCity = new ArrayList<CitadelsDistrictCard>();

    // Creates the CitadelsPlayer
    public CitadelsPlayer(String initPlayerName, int initPlayerNum, int initPlayerScore, int initGold, boolean initIsKing)
    {
        this.playerName = initPlayerName;
        this.playerNum = initPlayerNum;
        this.playerScore = initPlayerScore;
        this.gold = initGold;
        this.isKing = initIsKing;
    }

    // Copies the player
    public CitadelsPlayer(CitadelsPlayer orig)
    {
        this.playerName = orig.playerName;
        this.playerNum = orig.playerNum;
        this.playerScore = orig.playerScore;
        this.gold = orig.gold;
        this.isKing = orig.isKing;
    }

    // Adds district from hand to city
    public void buildDistrict(CitadelsDistrictCard card)
    {
            this.playerCity.add(card);
            this.playerHand.remove(card);
    }

    // Removes card from city
    public void destroyDistrict(CitadelsDistrictCard card)
    {
            this.playerCity.remove(card);
    }


    // Add card to hand
    public void addDistrict(CitadelsDistrictCard card)
    {
            this.playerHand.add(card);
    }

    // Removes card from hand
    public void removeDistrict(CitadelsDistrictCard card)
    {
            this.playerHand.remove(card);
    }

    // Getters and Setters for player variables
    public void setplayerName(String newName)
    {
        this.playerName = newName;
    }

    public String getplayerName()
    {
        return this.playerName;
    }

    // public int setPlayerNum(int playerIdx) { this.playerNum = playerIdx; }

    public int getPlayerNum() { return this.playerNum; }

    public void setScore(int x)
    {
        this.playerScore = x;
    }

    public int getScore()
    {
        return this.playerScore;
    }

    public void setGold(int newGold)
    {
        this.gold = newGold;
    }

    public int getGold()

    {
        return this.gold;
    }

    public void setKing(boolean x)
    {
        this.isKing = x;
    }

    public boolean isKing()
    {
        return this.isKing;
    }
}