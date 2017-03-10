package edu.up.citadels.citadels;

/**
 * Created by Victor on 2/20/2017.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<CitadelsDistrictCard> p1City = new ArrayList<CitadelsDistrictCard>();
    private ArrayList<CitadelsDistrictCard> p2City = new ArrayList<CitadelsDistrictCard>();
    private ArrayList<CitadelsDistrictCard> p3City = new ArrayList<CitadelsDistrictCard>();


    //there are 52 district cards in the deck
    private ArrayList<CitadelsDistrictCard> deckOrderDistricts = new ArrayList<CitadelsDistrictCard>();

    //tells us which player is the king
    private boolean isKing;

    //which characters are assigned to each player
    private int p1Character1;
    private int p1Character2;
    private int p2Character1;
    private int p2Character2;
    private int p3Character1;
    private int p3Character2;

    //if a character has been assassinated
    private boolean characterIsAlive;

    //is a player has been robbed
    private boolean characterIsStolen;

    //if a player has had their district cards swapped
    private boolean swapDistricts;

    //if a player has had districts destroyed by warlord
    private boolean destroyDistrict;

    //whose turn is it

    private int turn;

    //shows which cards were not drawn, are face up on table
    private CharacterCard cardUp1;
    private CharacterCard cardUp2;

    //list of character cards, there are 8 total
    private CharacterCard[] characterDeck = new CharacterCard[8];


    public void P1DrawCard()
    {
        p1Hand.add(deckOrderDistricts.get(0));
        deckOrderDistricts.remove(0);
    }

    public void P2DrawCard()
    {
        p2Hand.add(deckOrderDistricts.get(0));
        deckOrderDistricts.remove(0);
    }

    public void p3DrawCard()
    {
        p3Hand.add(deckOrderDistricts.get(0));
        deckOrderDistricts.remove(0);
    }

    public void p1BuildCard(int card)
    {
        p1Hand.remove(card);
        p1City.add(p1Hand.get(card));
    }
    public void p2BuildCard(int card)
    {
        p2Hand.remove(card);
        p2City.add(p2Hand.get(card));
    }

    public void p3BuildCard(int card)
    {
        p3Hand.remove(card);
        p3City.add(p3Hand.get(card));
    }


    public void setP1Score(int newScore)
    {
        p1Score = newScore;
    }

    public void setP2Score(int newScore)
    {
        p2Score = newScore;
    }

    public void setP3Score(int newScore)
    {
        p3Score = newScore;
    }



    public void setP1Gold(int newGold)
    {
        p1Gold = newGold;
    }

    public void setP2Gold(int newGold)
    {
        p2Gold = newGold;
    }

    public void setP3Gold(int newGold)
    {
        p3Gold = newGold;
    }

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
            deckOrderDistricts.add(new CitadelsDistrictCard("Watchtower", 0, 1));
        }

        // Making Prison district cards and adding them to deck
        for (int i = 3; i < 6; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Prison", 0, 2));
        }

        // Making Battlefield district cards and adding them to deck
        for (int i = 6; i < 9; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Battlefield", 0, 3));
        }

        // Making Fortress district cards and adding them to deck
        for (int i = 9; i < 11; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Fortress", 0, 5));
        }

        // Making Tavern district cards and adding them to deck
        for (int i = 11; i < 16; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Tavern", 1, 1));
        }

        // Making Market district cards and adding them to deck
        for (int i = 16; i < 20; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Market", 1, 2));
        }

        // Making Trading Post district cards and adding them to deck
        for (int i = 20; i < 23; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Trading Post", 1, 2));
        }

        // Making Docks district cards and adding them to deck
        for (int i = 23; i < 26; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Docks", 1, 3));
        }

        // Making Harbour district cards and adding them to deck
        for (int i = 26; i < 29; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Harbour", 1, 4));
        }

        // Making City Hall district cards and adding them to deck
        for (int i = 29; i < 31; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("City Hall", 1, 5));
        }

        // Making Temple district cards and adding them to deck
        for (int i = 31; i < 34; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Temple", 2, 1));
        }

        // Making Church district cards and adding them to deck
        for (int i = 34; i < 37; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Church", 2, 2));
        }

        // Making Monastery district cards and adding them to deck
        for (int i = 37; i < 40; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Monastery", 2, 3));
        }

        // Making Manor district cards and adding them to deck
        for ( int i = 40; i < 45; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Manor", 3, 3));
        }

        // Making Castle district cards and adding them to deck
        for (int i = 45; i < 49; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Castle", 3, 4));
        }

        // Making Palace district cards and adding them to deck
        for (int i = 49; i < 52; ++i)
        {
            deckOrderDistricts.add(new CitadelsDistrictCard("Palace", 3, 5));
        }

        Collections.shuffle(deckOrderDistricts);

        //player 1's starting district cards
        for (int i = 0; i<4; ++i)
        {
            p1Hand.add(deckOrderDistricts.get(0));
            deckOrderDistricts.remove(0);
        }


        //player 2's starting district cards
        for (int i = 0; i<4; ++i)
        {
            p2Hand.add(deckOrderDistricts.get(0));
            deckOrderDistricts.remove(0);
        }



        //player 3's starting district cards
        for (int i = 0; i<4; ++i)
        {
            p3Hand.add(deckOrderDistricts.get(0));
            deckOrderDistricts.remove(0);
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

        for (int i = 0; i<p1City.size(); ++i)
        {
            this.p1City.add(new CitadelsDistrictCard(orig.p1City.get(i)));
        }

        for (int i = 0; i<p2City.size(); ++i)
        {
            this.p2City.add(new CitadelsDistrictCard(orig.p2City.get(i)));
        }


        for (int i = 0; i<p3City.size(); ++i)
        {
            this.p3City.add(new CitadelsDistrictCard(orig.p3City.get(i)));
        }


        for (int i = 0; i<9; ++ i)
        {
            this.characterDeck[i] = new CharacterCard(orig.characterDeck[i]);
        }

        for (int i = 0; i<deckOrderDistricts.size(); ++i)
        {
            this.deckOrderDistricts.add(new CitadelsDistrictCard(orig.deckOrderDistricts.get(i)));
        }
    }

    //Get P1 Score
    public int getP1Score()
    {
        return p1Score;
    }

    //Get P2 Score
    public int getP2Score()
    {
        return p2Score;
    }

    //Get P3 Score
    public int getP3Score()
    {
        return p3Score;
    }

    //Get P1 Gold
    public int getP1Gold()
    {
        return p1Gold;
    }

    //Get P2 Gold
    public int getP2Gold()
    {
        return p2Gold;
    }

    //Get P3 Gold
    public int getP3Gold()
    {
        return p3Gold;
    }

    //Get P1 Hand
    public ArrayList getP1Hand()
    {
        return p1Hand;
    }

    //Get P2 Hand
    public ArrayList getP2Hand()
    {
        return p2Hand;
    }

    //Get P3 Hand
    public ArrayList getP3Hand()
    {
        return p3Hand;
    }

    //Get P1 City
    public CitadelsDistrictCard[] getP1City()
    {
        return p1City;
    }

    //Get P2 City
    public CitadelsDistrictCard[] getP2City()
    {
        return p2City;
    }

    //Get P3 City
    public CitadelsDistrictCard[] getP3City()
    {
        return p3City;
    }

    //Get District Deck
    public CitadelsDistrictCard[] getDeckOrderDistricts()
    {
        return deckOrderDistricts;
    }

    //Get turn
    public int getTurn()
    {
        return Turn;
    }

    //Returns if the player is king or not
    public boolean getIsKing()
    {
        return isKing;
    }

    //Get P1Character 1
    public int getP1Character1()
    {
        return p1Character1;
    }

    //Get P1Character 2
    public int getP1Character2()
    {
        return p1Character2;
    }

    //Get P2Character 1
    public int getP2Character1()
    {
        return p2Character1;
    }

    //Get P2Character 2
    public int getP2Character2()
    {
        return p2Character2;
    }

    //Get P3Character 1
    public int getP3Character1()
    {
        return p3Character1;
    }

    //Get P3Character 2
    public int getP3Character2()
    {
        return p3Character2;
    }

    //Returns if Character is alive
    public boolean getCharacterIsAlive()
    {
        return characterIsAlive;
    }

    //Returns if Character has been stolen from
    public boolean getCharacterIsStolen()
    {
        return characterIsStolen;
    }

    //Returns if districts are swapped
    public boolean getSwapDistricts()
    {
        return swapDistricts;
    }

    //Get face up card 1
    public CharacterCard getCardUp1()
    {
        return cardUp1;
    }

    //Get face up card 2
    public CharacterCard getCardUp2()
    {
        return cardUp2;
    }

    //Get character card deck
    public CharacterCard[] getCharacterDeck()
    {
        return characterDeck;
    }

}
