package edu.up.citadels;

/**
 * Created by Victor on 2/20/2017.
 */

import java.util.ArrayList;
import java.util.List;


/*

@Author Bryce Amato
 */

public class GameState
{
    //who is playing
    private Player p1;
    private Player p2;
    private Player p3;

    // Copy of the players
    private Player p1Copy;
    private Player p2Copy;
    private Player p3Copy;

    // Player Names
    private String p1Name;
    private String p2Name;
    private String p3Name;


    //scores
    private int p1Score;
    private int p2Score;
    private int p3Score;

    //gold count for all players
    private int p1Gold;
    private int p2Gold;
    private int p3Gold;

    //List of all cards in each player's hand
    private List<DistrictCard> p1HandDistricts = new ArrayList<DistrictCard>();
    private List<DistrictCard> p2HandDistricts = new ArrayList<DistrictCard>();
    private List<DistrictCard> p3HandDistricts = new ArrayList<DistrictCard>();

    //which districts have been built by each player, maximum of 8
    private DistrictCard[] p1BuiltDistricts = new DistrictCard[8];
    private DistrictCard[] p2BuiltDistricts = new DistrictCard[8];
    private DistrictCard[] p3BuiltDistricts = new DistrictCard[8];

    //there are 52 district cards in the deck
    private DistrictCard[] deckOrderDistricts = new DistrictCard[52];

    //tells us which player is the king
    private boolean isKing;

    //which characters are assigned to each player
    private Character p1Character1;
    private Character p1Character2;
    private Character p2Character1;
    private Character p2Character2;
    private Character p3Character1;
    private Character p3Character2;

    //if a character has been assassinated
    private boolean characterIsAlive;

    //is a player has been robbed
    private boolean playerIsStolen;

    //if a player has had their district cards swapped
    private boolean swapDistricts;

    //if a player has had districts destroyed by warlord
    private boolean destroyDistrict;

    //whose turn is it
    private Character whoseTurn;

    //shows which cards were not drawn, are face up on table
    private CharacterCard cardUp1;
    private CharacterCard cardUp2;

    //list of character cards, there are 8 total
    private CharacterCard[] characterDeck = new CharacterCard[8];




    public GameState()
    {

        p1 = new Player(p1Name, 0, 2, p1.isKing());
        p1Copy = new Player(p1);

        p2 = new Player(p2Name, 0, 2, p2.isKing());
        p2Copy = new Player(p2);

        p3 = new Player(p3Name, 0, 2, p3.isKing());
        p3Copy = new Player(p3);








        double king = Math.random();


        //sets all of the built districts for each player to null because no one will start
        //with a district
        for(int i = 0; i < p1BuiltDistricts.length; ++i)
        {
            p1BuiltDistricts[i] = null;
        }
        for(int i = 0; i < p2BuiltDistricts.length; ++i)
        {
            p2BuiltDistricts[i] = null;
        }
        for(int i = 0; i < p3BuiltDistricts.length; ++i)
        {
            p3BuiltDistricts[i] = null;
        }

        //initializes the district cards for each player. Everyone starts with 4 cards
        int count = 0;
        while(count < 4)
        {
            for(int i = 0; i < deckOrderDistricts.length; ++i)
            {
                if(deckOrderDistricts[i] != null)
                {
                    p1HandDistricts.add(deckOrderDistricts[i]);
                    deckOrderDistricts[i] = null;
                    ++count;
                }
                else if(deckOrderDistricts[i] == null)
                {
                    //skip it
                }
            }
        }
        //player 2's starting district cards
        while(count < 4)
        {
            for(int i = 0; i < deckOrderDistricts.length; ++i)
            {
                if(deckOrderDistricts[i] != null)
                {
                    p2HandDistricts.add(deckOrderDistricts[i]);
                    deckOrderDistricts[i] = null;
                    ++count;
                }
                else if(deckOrderDistricts[i] == null)
                {
                    //skip it
                }
            }
        }
        //player 3's starting district cards
        while(count < 4)
        {
            for(int i = 0; i < deckOrderDistricts.length; ++i)
            {
                if(deckOrderDistricts[i] != null)
                {
                    p3HandDistricts.add(deckOrderDistricts[i]);
                    deckOrderDistricts[i] = null;
                    ++count;
                }
                else if(deckOrderDistricts[i] == null)
                {
                    //skip it
                }
            }
        }


    }

}
