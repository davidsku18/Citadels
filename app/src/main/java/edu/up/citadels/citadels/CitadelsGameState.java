package edu.up.citadels.citadels;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import edu.up.citadels.game.infoMsg.GameState;

/**
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class CitadelsGameState extends GameState
{

    // if king is equal to the player's number, they are the king
    private int king;

    //scores for all the players
    private int p1Score;
    private int p2Score;
    private int p3Score;

    //gold count for all players
    private int p1Gold;
    private int p2Gold;
    private int p3Gold;

    private CharacterCard noCharacter = new CharacterCard( -1, -1);

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

    //which characters are assigned to each player
    private CharacterCard p1Character1;
    private CharacterCard p1Character2;
    private CharacterCard p2Character1;
    private CharacterCard p2Character2;
    private CharacterCard p3Character1;
    private CharacterCard p3Character2;

    //if a character has been assassinated //TODO
    private boolean characterIsAlive;

    //is a player has been robbed
    private boolean characterIsStolen;

    //if a player has had their district cards swapped
    private boolean swapDistricts;

    //if a player has had districts destroyed by warlord
    private boolean destroyDistrict;

    //The chosen district card to be built
    private CitadelsDistrictCard districtCardToBeBuilt;

    //whose turn is it
    private int turn;

    //District Build Limit
    private int buildLimit;

    //shows which cards were not drawn, are face up on table
    private CharacterCard cardUp1;
    private CharacterCard cardUp2;

    //list of character cards, there are 8 total
    private CharacterCard[] characterDeck = new CharacterCard[8];

    //the character card that was chosen
    private int theChosenCharacterCard;

    private int theChosenDistrict;

/////////////////////////////Stuff to deal with player//////////////////////////////////////////////

    //Sets the turn
    public void setTurn(int x)
    {
        turn = x;
    }

    //Gets the current turn
    public int getTurn()
    {
        return this.turn;
    }

    //Gets the turn of the player based on their character
    public int getPlayerTurn()
    {
        if((turn == this.p1Character1.getWhichCharacter()) || (turn == this.p1Character2.getWhichCharacter()))
        {
            return 0;
        }else if((turn == this.p2Character1.getWhichCharacter()) || (turn == this.p2Character2.getWhichCharacter()))
        {
            return 1;
        }else if((turn == this.p3Character1.getWhichCharacter()) || (turn == this.p3Character2.getWhichCharacter()))
        {
            return 2;
        }
        else
        {
            return -1;
        }
    }

    //Sets which player is king
    public void setRollKing()
    {
        king = (int)Math.random()*2;
    }

    //Returns if the player is king or not
    public int getKing()
    {
        return king;
    } // TODO need to implement who is king and who has the king character card

    //Sets the player's chosen character card
    public void setP1Character1(CharacterCard theCard) { this.p1Character1 = theCard; }
    public void setP1Character2(CharacterCard theCard) { this.p1Character2 = theCard; }
    public void setP2Character1(CharacterCard theCard) { this.p2Character1 = theCard; }
    public void setP2Character2(CharacterCard theCard) { this.p2Character2 = theCard; }
    public void setP3Character1(CharacterCard theCard) { this.p3Character1 = theCard; }
    public void setP3Character2(CharacterCard theCard) { this.p3Character2 = theCard; }

///////////////////////////////////Deals with players stats/////////////////////////////////
    // TODO can combine CitadelsDistrictCard and removeDistrictCard
    public CitadelsDistrictCard drawCard()
    {
        return this.deckOrderDistricts.get(0);
    }

    public void removeCard()
    {
        this.deckOrderDistricts.remove(0);
    }

    public CitadelsDistrictCard drawDistrictCard()
    {
        CitadelsDistrictCard cdc = this.deckOrderDistricts.get(0);
        this.deckOrderDistricts.remove(0);
        return cdc;
    }

    //Adds district card to player's city (built)
    public void addToP1City(CitadelsDistrictCard dc)
    {
        this.p1City.add(dc);
    }
    public void addToP2City(CitadelsDistrictCard dc)
    {
        this.p2City.add(dc);
    }
    public void addToP3City(CitadelsDistrictCard dc)
    {
        this.p3City.add(dc);
    }

    //Sets the players score
    public void setP1Score(int newScore)
    {
        this.p1Score = newScore;
    }
    public void setP2Score(int newScore)
    {
        this.p2Score = newScore;
    }
    public void setP3Score(int newScore)
    {
        this.p3Score = newScore;
    }

    //Gets player's score
    public int getP1Score()
    {
        return p1Score;
    }
    public int getP2Score()
    {
        return p2Score;
    }
    public int getP3Score()
    {
        return p3Score;
    }

    //Sets the players gold
    public void setP1Gold(int newGold)
    {
        this.p1Gold = newGold;
    }
    public void setP2Gold(int newGold)
    {
        this.p2Gold = newGold;
    }
    public void setP3Gold(int newGold)
    {
        this.p3Gold = newGold;
    }

    //Gets player's gold
    public int getP1Gold()
    {
        return p1Gold;
    }
    public int getP2Gold()
    {
        return p2Gold;
    }
    public int getP3Gold()
    {
        return p3Gold;
    }

//////////////////////////////////Deals with character cards////////////////////////

    //Creathes the Character deck
    public void initializeCharacterDeck()
    {
        // Making character cards
        for (int i = 0; i < 8; ++i) {
            if (i < 3 || i == 6) {
                characterDeck[i] = new CharacterCard(i, 4); // non color characters
            } else if (i == 3) {
                characterDeck[i] = new CharacterCard(i, 3); // King
            } else if (i == 4) {
                characterDeck[i] = new CharacterCard(i, 2); // Bishop
            } else if (i == 5) {
                characterDeck[i] = new CharacterCard(i, 1); // Merchant
            } else if (i == 7) {
                characterDeck[i] = new CharacterCard(i, 0); // Warlord
            }
        }
    }

    //Get P1's Characters
    public CharacterCard getP1Character1()
    {
        return p1Character1;
    }
    public CharacterCard getP1Character2()
    {
        return p1Character2;
    }

    //Get P2's Characters
    public CharacterCard getP2Character1()
    {
        return p2Character1;
    }
    public CharacterCard getP2Character2()
    {
        return p2Character2;
    }

    //Get P3's Characters
    public CharacterCard getP3Character1()
    {
        return p3Character1;
    }
    public CharacterCard getP3Character2()
    {
        return p3Character2;
    }

    public void setChosenCharacterCardNum(int chosenCharacterCard)
    {
        //characterDeck[chosenCharacterCard] = null;
        this.theChosenCharacterCard = chosenCharacterCard;
    }
    //returns the character card that the player chose
    public int getChosenCharacterCardNum()
    {
        return this.theChosenCharacterCard;
    }

    //Removes the characterCard from te deck after a player chooses their card
    public void removeCharacterCardFromDeck(int i) { this.characterDeck[i] = null; }

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
    public CharacterCard getCharacterDeck(int i)
    {
        return characterDeck[i];
    }

////////////////////////////////////Deals with Players district cards//////////////////////

    //Deprecated for now
    public void removeDistrictCard()
    {
        this.deckOrderDistricts.remove(0);
    }

    //Adds district card to player's hand
    public void addToP1Hand(CitadelsDistrictCard dc) { this.p1Hand.add(dc); }
    public void addToP2Hand(CitadelsDistrictCard dc) { this.p2Hand.add(dc); }
    public void addToP3Hand(CitadelsDistrictCard dc) { this.p3Hand.add(dc); }

    //Removes district card from the player's hand
    public void removeFromP1Hand(int card)
    {
        this.p1Hand.remove(card);
    }
    public void removeFromP2Hand(int card)
    {
        this.p2Hand.remove(card);
    }
    public void removeFromP3Hand(int card)
    {
        this.p3Hand.remove(card);
    }

    public ArrayList<String> getP1HandNames()
    {
        ArrayList<String> names = new ArrayList<>();
        String info;
        for(int i = 0; i < this.p1Hand.size(); ++i)
        {
            info = this.p1Hand.get(i).getName() + "\nCost: " + this.p1Hand.get(i).getCost() +
                    "\nColor: " + this.p1Hand.get(i).getColorString();
            names.add(info);
        }
        return names;
    }

    //Gets the player's district cards in their hand
    public ArrayList getP1Hand()
    {
        return this.p1Hand;
    }
    public ArrayList getP2Hand()
    {
        return this.p2Hand;
    }
    public ArrayList getP3Hand()
    {
        return this.p3Hand;
    }

    //Gets the player's district card at specific index in their district cards hand
    public CitadelsDistrictCard getP1DistrictCard(int x)
    {
        return p1Hand.get(x);
    }
    public CitadelsDistrictCard getP2DistrictCard(int x)
    {
        return p2Hand.get(x);
    }
    public CitadelsDistrictCard getP3DistrictCard(int x)
    {
        return p3Hand.get(x);
    }

    public void setTheChosenDistrict(int district)
    {
        this.theChosenDistrict = district;
    }

    public int getTheChosenDistrict()
    {
        return this.theChosenDistrict;
    }

    public void removeP1BuiltDistrict()
    {
        if(this.p1City.size() > 0)
        {
            p1City.remove(0);
        }else
        {
            //do nothing
        }
    }

    public void removeP2BuiltDistrict()
    {
        if(this.p2City.size() > 0)
        {
            p2City.remove(0);
        }else
        {
            //do nothing
        }
    }

    public void removeP3BuiltDistrict()
    {
        if(this.p3City.size() > 0)
        {
            p3City.remove(0);
        }else
        {
            //do nothing
        }
    }

    //Get P1 City
    public ArrayList<CitadelsDistrictCard> getP1City()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < p1City.size(); ++i)
        {
            returnList.add(p1City.get(i));
        }
        return returnList;
    }
    public CitadelsDistrictCard getP1CityCard(int i)
    {
        return p1City.get(i);
    }

    //Get P2 City
    public ArrayList<CitadelsDistrictCard> getP2City()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < p2City.size(); ++i)
        {
            returnList.add(p2City.get(i));
        }
        return returnList;
    }
    public CitadelsDistrictCard getP2CityCard(int i)
    {
        return p2City.get(i);
    }

    //Get P3 City
    public ArrayList<CitadelsDistrictCard> getP3City()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < p3City.size(); ++i)
        {
            returnList.add(p3City.get(i));
        }
        return returnList;
    }
    public CitadelsDistrictCard getP3CityCard(int i)
    {
        return p3City.get(i);
    }

    //Get District Deck
    public ArrayList<CitadelsDistrictCard> getDeckOrderDistricts()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < deckOrderDistricts.size(); ++i)
        {
            returnList.add(deckOrderDistricts.get(i));
        }
        return returnList;
    }

    public String getP1DistrictInfo(int index)
    {
        CitadelsDistrictCard temp = p1City.get(index);
        String P1DistrictInfo = "Name: " + temp.getName() + "\n" + "Color: " + temp.getColorString()
                + "\n" + "Cost: " + temp.getCost();
        return P1DistrictInfo;
    }

    public String getP2DistrictInfo(int index)
    {
        CitadelsDistrictCard temp = p2City.get(index);
        String P2DistrictInfo = "Name: " + temp.getName() + "\n" + "Color: " + temp.getColorString()
                + "\n" + "Cost: " + temp.getCost();
        return P2DistrictInfo;
    }

    public String getP3DistrictInfo(int index)
    {
        CitadelsDistrictCard temp = p3City.get(index);
        String P3DistrictInfo = "Name: " + temp.getName() + "\n" + "Color: " + temp.getColorString()
                + "\n" + "Cost: " + temp.getCost();
        return P3DistrictInfo;
    }

    //Sets the player's hand with cards (Used at the start of game)
    public void setP1Hand(ArrayList<CitadelsDistrictCard> arrayCD)
    {
        this.p1Hand = arrayCD;
    }
    public void setP2Hand(ArrayList<CitadelsDistrictCard> arrayCD)
    {
        this.p2Hand = arrayCD;
    }
    public void setP3Hand(ArrayList<CitadelsDistrictCard> arrayCD)
    {
        this.p3Hand = arrayCD;
    }

    //Gets the cost of the character card that will be build
    public void districtCardToBuild(CitadelsDistrictCard tobeBuilt)
    {
        this.districtCardToBeBuilt = tobeBuilt;
    }

    public CitadelsDistrictCard getTheDistrictCardToBeBuilt()
    {
        return this.districtCardToBeBuilt;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////

    public int p1FindCard(CitadelsDistrictCard card)
    {
        return this.p1Hand.indexOf(card);

    }
    public int p2FindCard(int card)
    {
        return this.p2Hand.indexOf(card);

    }
    public int p3FindCard(int card)
    {
        return this.p3Hand.indexOf(card);

    }

    public CitadelsDistrictCard getTopCard()
    {
        return this.drawCard();
    }

    public CitadelsGameState()
    {
        //sets all of the built districts for each player to null because no one will start
        //with a district built

        this.buildLimit = 1;

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
        for (int i = 0; i < 4; ++i)
        {
            p1Hand.add(deckOrderDistricts.get(0));
            deckOrderDistricts.remove(0);
        }


        //player 2's starting district cards
        for (int i = 0; i < 4; ++i)
        {
            p2Hand.add(deckOrderDistricts.get(0));
            deckOrderDistricts.remove(0);
        }


        //player 3's starting district cards
        for (int i = 0; i < 4; ++i)
        {
            p3Hand.add(deckOrderDistricts.get(0));
            deckOrderDistricts.remove(0);
        }

        this.initializeCharacterDeck();

        /*//TODO take this out- I just put these in to test functionality
        this.addToP1City(this.p1Hand.get(0));
        this.addToP1City(this.p1Hand.get(1));
        this.addToP2City(this.p2Hand.get(0));
        this.addToP2City(this.p2Hand.get(1));
        this.addToP3City(this.p3Hand.get(0));
        this.addToP3City(this.p3Hand.get(1));*/

        this.setTurn(0);
        this.setRollKing();

        this.setP1Score(0);
        this.setP2Score(0);
        this.setP3Score(0);

        this.setP1Gold(2);
        this.setP2Gold(2);
        this.setP3Gold(2);

        //TODO take this out, I only put it in to test basic functionality

        //sets all character cards for players to nothing

        this.setP1Character1(noCharacter);
        this.setP1Character2(noCharacter);
        this.setP2Character1(noCharacter);
        this.setP2Character2(noCharacter);
        this.setP3Character1(noCharacter);
        this.setP3Character2(noCharacter);

    }


    public CitadelsGameState(CitadelsGameState orig)
    {
        //TODO
        //TODO  Nux includes the "new" reserved word in his copy constructor, not sure if that's immportant
        //TODO  and I cannot check right now. Just didn't want to forget.

        buildLimit = orig.buildLimit;
        king = orig.king;
        turn = orig.turn;

        p1Gold = orig.p1Gold;
        p2Gold = orig.p2Gold;
        p3Gold = orig.p3Gold;

        p1Score = orig.p1Score;
        p2Score = orig.p2Score;
        p3Score = orig.p3Score;

        p1Hand = orig.p1Hand;
        p2Hand = orig.p2Hand;
        p3Hand = orig.p3Hand;

        p1City = orig.p1City;
        p2City = orig.p2City;
        p3City = orig.p3City;

        theChosenCharacterCard = orig.theChosenCharacterCard;

        deckOrderDistricts = orig.deckOrderDistricts;
        characterDeck = orig.characterDeck;

        p1Character1 = orig.p1Character1;
        p1Character2 = orig.p1Character2;
        p2Character1 = orig.p2Character1;
        p2Character2 = orig.p2Character2;
        p3Character1 = orig.p3Character1;
        p3Character2 = orig.p3Character2;


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

    //TODO may need to change
    public void isCharacterDead(boolean kill)
    {
        this.isCharacterDead(kill);
    }

}
