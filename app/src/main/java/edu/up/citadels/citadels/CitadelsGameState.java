package edu.up.citadels.citadels;

/**
 * Created by Victor on 2/20/2017.
 */

import java.util.ArrayList;
import java.util.Random;

import edu.up.citadels.game.infoMsg.GameState;

/**
 * Created by Victor on 2/20/2017.
 */

/*

@Author Bryce Amato
 */

public class CitadelsGameState extends GameState
{



    private int p1Score;
    private int p2Score;
    private int p3Score;

    //gold count for all players
    private int p1Gold;
    private int p2Gold;
    private int p3Gold;

    //List of all cards in each player's hand
    private ArrayList<CitadelsDistrictCard> p1Hand = new ArrayList<CitadelsDistrictCard>();
    private ArrayList<CitadelsDistrictCard> p2Hand = new ArrayList<CitadelsDistrictCard>();
    private ArrayList<CitadelsDistrictCard> p3Hand = new ArrayList<CitadelsDistrictCard>();


    //which districts have been built by each player, maximum of 8
    private CitadelsDistrictCard[] p1City = new CitadelsDistrictCard[8];
    private CitadelsDistrictCard[] p2City = new CitadelsDistrictCard[8];
    private CitadelsDistrictCard[] p3City = new CitadelsDistrictCard[8];


    //there are 52 district cards in the deck
    private CitadelsDistrictCard[] deckOrderDistricts = new CitadelsDistrictCard[52];

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


    public CitadelsGameState()
    {

        double king = Math.random();


        //sets all of the built districts for each player to null because no one will start
        //with a district built

        // CitadelsPlayer 1's city
        for(int i = 0; i < p1City.length; ++i)
        {
            p1City[i] = null;
        }

        // CitadelsPlayer 2's city
        for(int i = 0; i < p2City.length; ++i)
        {
            p2City[i] = null;
        }


        // CitadelsPlayer 3's city
        for(int i = 0; i < p3City.length; ++i)
        {
            p3City[i] = null;
        }


        // Making Watchtower district cards and adding them to deck
        for (int i = 0; i < 3; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Watchtower", 0, 1);
        }

        // Making Prison district cards and adding them to deck
        for (int i = 3; i < 6; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Prison", 0, 2);
        }

        // Making Battlefield district cards and adding them to deck
        for (int i = 6; i < 9; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Battlefield", 0, 3);
        }

        // Making Fortress district cards and adding them to deck
        for (int i = 9; i < 11; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Fortress", 0, 5);
        }

        // Making Tavern district cards and adding them to deck
        for (int i = 11; i < 16; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Tavern", 1, 1);
        }

        // Making Market district cards and adding them to deck
        for (int i = 16; i < 20; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Market", 1, 2);
        }

        // Making Trading Post district cards and adding them to deck
        for (int i = 20; i < 23; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Trading Post", 1, 2);
        }

        // Making Docks district cards and adding them to deck
        for (int i = 23; i < 26; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Docks", 1, 3);
        }

        // Making Harbour district cards and adding them to deck
        for (int i = 26; i < 29; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Harbour", 1, 4);
        }

        // Making City Hall district cards and adding them to deck
        for (int i = 29; i < 31; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("City Hall", 1, 5);
        }

        // Making Temple district cards and adding them to deck
        for (int i = 31; i < 34; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Temple", 2, 1);
        }

        // Making Church district cards and adding them to deck
        for (int i = 34; i < 37; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Church", 2, 2);
        }

        // Making Monastery district cards and adding them to deck
        for (int i = 37; i < 40; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Monastery", 2, 3);
        }

        // Making Manor district cards and adding them to deck
        for ( int i = 40; i < 45; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Manor", 3, 3);
        }

        // Making Castle district cards and adding them to deck
        for (int i = 45; i < 49; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Castle", 3, 4);
        }

        // Making Palace district cards and adding them to deck
        for (int i = 49; i < 52; ++i)
        {
            deckOrderDistricts[i] = new CitadelsDistrictCard("Palace", 3, 5);
        }

        shuffleDeck(deckOrderDistricts);



        //initializes the district cards for each player. Everyone starts with 4 cards
        for(int i = 0; i<4; ++i)
        {
            for (int x = 0; x < deckOrderDistricts.length; ++x)
            {
                if (deckOrderDistricts[x] != null)
                {
                    p1Hand.add(deckOrderDistricts[x]);
                    deckOrderDistricts[x] = null;
                }
                else if (deckOrderDistricts[x] == null)
                {
                    //skip it
                }
            }
        }

        //player 1's starting district cards
        for (int i = 0; i<4; ++i)
        {
            for(int x = 0; x < deckOrderDistricts.length; ++x)
            {
                if(deckOrderDistricts[x] != null)
                {
                    p1Hand.add(deckOrderDistricts[x]);
                    deckOrderDistricts[x] = null;
                }
                else if(deckOrderDistricts[x] == null)
                {
                    //skip it
                }
            }
        }


        //player 2's starting district cards
        for (int i = 0; i<4; ++i)
        {
            for(int x = 0; x < deckOrderDistricts.length; ++x)
            {
                if(deckOrderDistricts[x] != null)
                {
                    p2Hand.add(deckOrderDistricts[x]);
                    deckOrderDistricts[x] = null;
                }
                else if(deckOrderDistricts[x] == null)
                {
                    //skip it
                }
            }
        }



        //player 3's starting district cards
        for (int i = 0; i<4; ++i)
        {
            for(int x = 0; x < deckOrderDistricts.length; ++x)
            {
                if(deckOrderDistricts[x] != null)
                {
                    p3Hand.add(deckOrderDistricts[x]);
                    deckOrderDistricts[x] = null;

                }
                else if(deckOrderDistricts[x] == null)
                {
                    //skip it
                }
            }
        }



        // Making character cards
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



    }

    public static void shuffleDeck(CitadelsDistrictCard[] deck)
    {
        Random rnd = new Random();
        for (int i = deck.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            CitadelsDistrictCard a = deck[index];
            deck[index] = deck[i];
            deck[i] = a;
        }
    }
    public CitadelsGameState(CitadelsGameState orig)
    {
        this.p1Gold = orig.p1Gold;
        this.p2Gold = orig.p2Gold;
        this.p3Gold = orig.p3Gold;

        this.p1Score = orig.p1Score;
        this.p2Score = orig.p2Score;
        this.p3Score = orig.p3Score;

        for (int i = 0; i<p1Hand.size(); ++i)
        {
            this.p1Hand.add(new CitadelsDistrictCard(orig.p1Hand.get(i)));
        }

        for (int i = 0; i<p2Hand.size(); ++i)
        {
            this.p2Hand.add(new CitadelsDistrictCard(orig.p2Hand.get(i)));
        }


        for (int i = 0; i<p3Hand.size(); ++i)
        {
            this.p3Hand.add(new CitadelsDistrictCard(orig.p3Hand.get(i)));
        }


        for (int i = 0; i<9; ++ i)
        {
            this.characterDeck[i] = new CharacterCard(orig.characterDeck[i]);
        }

        for (int i = 0; i<52; ++i)
        {
            this.deckOrderDistricts[i] = new CitadelsDistrictCard(orig.deckOrderDistricts[i]);
        }
    }
}
