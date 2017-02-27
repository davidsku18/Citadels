package edu.up.citadels;

import java.util.ArrayList;

/**
 * Created by Victor on 2/20/2017.
 */


public class Player
{
    private String playerName;
    private int playerScore;
    private int gold;
    private boolean isKing;
    private ArrayList<DistrictCard> playerHand = new ArrayList<DistrictCard>();
    private ArrayList<DistrictCard> playerCity = new ArrayList<DistrictCard>();

    // Creates the Player
    public Player(String initplayerName, int initplayerScore, int initgold, boolean initisKing)
    {
        this.playerName = initplayerName;
        this.playerScore = initplayerScore;
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

    // Adds district from hand to city
    public void buildDistrict(DistrictCard card)
    {
            this.playerCity.add(card);
            this.playerHand.remove(card);
    }

    // Removes card from city
    public void destroyDistrict(DistrictCard card)
    {
            this.playerCity.remove(card);
    }


    // Add card to hand
    public void addDistrict(DistrictCard card)
    {
            this.playerHand.add(card);
    }

    // Removes card from hand
    public void removeDistrict(DistrictCard card)
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
