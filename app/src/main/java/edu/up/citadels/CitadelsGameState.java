package edu.up.citadels;

/**
 * Created by Victor on 2/20/2017.
 */

import java.util.ArrayList;

/**
 * Created by Victor on 2/20/2017.
 */

import Info.GameState;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/*

@Author Bryce Amato
 */

public class CitadelsGameState extends GameState
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

    private int p1Score;
    private int p2Score;
    private int p3Score;

    //gold count for all players
    private int p1Gold;
    private int p2Gold;
    private int p3Gold;

    //List of all cards in each player's hand
    private ArrayList<DistrictCard> p1HandDistricts = new ArrayList<DistrictCard>();
    private ArrayList<DistrictCard> p2HandDistricts = new ArrayList<DistrictCard>();
    private ArrayList<DistrictCard> p3HandDistricts = new ArrayList<DistrictCard>();

    // Copy of player's hand
    private ArrayList<DistrictCard> p1HandDistrictsCopy = new ArrayList<DistrictCard>();
    private ArrayList<DistrictCard> p2HandDistrictsCopy = new ArrayList<DistrictCard>();
    private ArrayList<DistrictCard> p3HandDistrictsCopy = new ArrayList<DistrictCard>();


    //which districts have been built by each player, maximum of 8
    private DistrictCard[] p1BuiltDistricts = new DistrictCard[8];
    private DistrictCard[] p2BuiltDistricts = new DistrictCard[8];
    private DistrictCard[] p3BuiltDistricts = new DistrictCard[8];

    // copy of player's city
    private DistrictCard[] p1BuiltDistrictsCopy = new DistrictCard[8];
    private DistrictCard[] p2BuiltDistrictsCopy = new DistrictCard[8];
    private DistrictCard[] p3BuiltDistrictsCopy = new DistrictCard[8];


    //there are 52 district cards in the deck
    private DistrictCard[] deckOrderDistricts = new DistrictCard[52];
    private DistrictCard[] deckOrderDistrictscopy = new DistrictCard[52];

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
    private CharacterCard[] characterDeckcopy = new CharacterCard[8];

    public CitadelsGameState()
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

        // Player 1's city
        for(int i = 0; i < p1BuiltDistricts.length; ++i)
        {
            p1BuiltDistricts[i] = null;
        }
        // Copy of player 1's city
        for (int i = 0; i<p1BuiltDistricts.length; ++i)
        {
            // Makes a new District Card of the same spot i, and then adds it to the copy
            p1BuiltDistrictsCopy[i] = new DistrictCard(p1BuiltDistricts[i]);
        }

        // Player 2's city
        for(int i = 0; i < p2BuiltDistricts.length; ++i)
        {
            p2BuiltDistricts[i] = null;
        }
        // Copy of player 2's city
        for (int i = 0; i<p2BuiltDistricts.length; ++i)
        {
            // Makes a new District Card of the same spot i, and then adds it to the copy
            p2BuiltDistrictsCopy[i] = new DistrictCard(p2BuiltDistricts[i]);
        }

        // Player 3's city
        for(int i = 0; i < p3BuiltDistricts.length; ++i)
        {
            p3BuiltDistricts[i] = null;
        }
        // Copy of player 3's city
        for (int i = 0; i<p3BuiltDistricts.length; ++i)
        {
            // Makes a new District Card of the same spot i, and then adds it to the copy
            p3BuiltDistrictsCopy[i] = new DistrictCard(p3BuiltDistricts[i]);
        }

        // Making Watchtower district cards and adding them to deck
        for (int i = 0; i < 3; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Watchtower", 0, 1);
        }

        // Making Prison district cards and adding them to deck
        for (int i = 3; i < 6; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Prison", 0, 2);
        }

        // Making Battlefield district cards and adding them to deck
        for (int i = 6; i < 9; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Battlefield", 0, 3);
        }

        // Making Fortress district cards and adding them to deck
        for (int i = 9; i < 11; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Fortress", 0, 5);
        }

        // Making Tavern district cards and adding them to deck
        for (int i = 11; i < 16; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Tavern", 1, 1);
        }

        // Making Market district cards and adding them to deck
        for (int i = 16; i < 20; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Market", 1, 2);
        }

        // Making Trading Post district cards and adding them to deck
        for (int i = 20; i < 23; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Trading Post", 1, 2);
        }

        // Making Docks district cards and adding them to deck
        for (int i = 23; i < 26; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Docks", 1, 3);
        }

        // Making Harbour district cards and adding them to deck
        for (int i = 26; i < 29; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Harbour", 1, 4);
        }

        // Making City Hall district cards and adding them to deck
        for (int i = 29; i < 31; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("City Hall", 1, 5);
        }

        // Making Temple district cards and adding them to deck
        for (int i = 31; i < 34; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Temple", 2, 1);
        }

        // Making Church district cards and adding them to deck
        for (int i = 34; i < 37; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Church", 2, 2);
        }

        // Making Monastery district cards and adding them to deck
        for (int i = 37; i < 40; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Monastery", 2, 3);
        }

        // Making Manor district cards and adding them to deck
        for ( int i = 40; i < 45; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Manor", 3, 3);
        }

        // Making Castle district cards and adding them to deck
        for (int i = 45; i < 49; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Castle", 3, 4);
        }

        // Making Palace district cards and adding them to deck
        for (int i = 49; i < 51; ++i)
        {
            deckOrderDistricts[i] = new DistrictCard("Palace", 3, 5);
        }

        shuffleDeck(deckOrderDistricts);

        // creating copy of shuffled deck
        for (int i = 0; i < 52; ++i)
        {
            deckOrderDistrictscopy[i] = new DistrictCard(deckOrderDistricts[i]);
        }

        //initializes the district cards for each player. Everyone starts with 4 cards
        for(int i = 0; i<4; ++i)
        {
            for (int x = 0; x < deckOrderDistricts.length; ++x)
            {
                if (deckOrderDistricts[x] != null)
                {
                    p1HandDistricts.add(deckOrderDistricts[x]);
                    deckOrderDistricts[x] = null;
                }
                else if (deckOrderDistricts[x] == null)
                {
                    //skip it
                }
            }
        }

        for (int i = 0; i<p1HandDistricts.size(); ++i)
        {
            // Makes a new District Card of the same spot i, and then adds it to the copy
            p1HandDistrictsCopy.add(new DistrictCard(p1HandDistricts.get(i)));
        }
        for (int i = 0; i<p1HandDistricts.size(); ++i)
        {
             // Makes a new District Card of the same spot i, and then adds it to the copy
            p1HandDistrictsCopy.add(new DistrictCard(p1HandDistricts.get(i)));
        }

        //player 2's starting district cards
        for (int i = 0; i<4; ++i)
        {
            for(int x = 0; x < deckOrderDistricts.length; ++x)
            {
                if(deckOrderDistricts[x] != null)
                {
                    p2HandDistricts.add(deckOrderDistricts[x]);
                    deckOrderDistricts[x] = null;
                }
                else if(deckOrderDistricts[x] == null)
                {
                    //skip it
                }
            }
        }
        // Player 2's hand copy
        for (int i = 0; i<p2HandDistricts.size(); ++i)
        {
            // Makes a new District Card of the same spot i, and then adds it to the copy
            p2HandDistrictsCopy.add(new DistrictCard(p2HandDistricts.get(i)));
        }


        //player 3's starting district cards
        for (int i = 0; i<4; ++i)
        {
            for(int x = 0; x < deckOrderDistricts.length; ++x)
            {
                if(deckOrderDistricts[x] != null)
                {
                    p3HandDistricts.add(deckOrderDistricts[x]);
                    deckOrderDistricts[x] = null;

                }
                else if(deckOrderDistricts[x] == null)
                {
                    //skip it
                }
            }
        }

        // Player 3's hand copy
        for (int i = 0; i<p3HandDistricts.size(); ++i)
        {
            // Makes a new District Card of the same spot i, and then adds it to the copy
            p3HandDistrictsCopy.add(new DistrictCard(p3HandDistricts.get(i)));
        }

        // Making Assassin character card
        for (int i = 1; i < 9; ++i)
        {
            if (i < 4 || i == 7)
            {
                characterDeck[i] = new CharacterCard(i,4); // non color characters
            }
            else if (i == 4)
            {
                characterDeck[i] = new CharacterCard(i, 3); // King
            }
            else if (i == 5 )
            {
                characterDeck[i] = new CharacterCard(i, 2); // Bishop
            }
            else if (i == 6)
            {
                characterDeck[i] = new CharacterCard(i, 1); // Merchant
            }
            else if (i == 8)
            {
                characterDeck[i] = new CharacterCard(i, 0); // Warlord
            }
        }

        // Creating copy of character deck
        for (int i = 1; i < 9; ++i)
        {
            characterDeckcopy[i] = new CharacterCard(characterDeck[i]);
        }


    }

    public static void shuffleDeck(DistrictCard[] deck)
    {
        Random rnd = new Random();
        for (int i = deck.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            DistrictCard a = deck[index];
            deck[index] = deck[i];
            deck[i] = a;
        }
    }
}
