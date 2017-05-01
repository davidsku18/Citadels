package edu.up.citadels.citadels;


import java.util.ArrayList;
import java.util.Collections;

import edu.up.citadels.game.infoMsg.GameState;

/**
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 4/30/2017
 */

public class CitadelsGameState extends GameState
{
    // Special ability Text
    private String specialAbility = null;

    // if king is equal to the player's number, they are the king
    private int king;


    private int turnCounter;

    private ArrayList<CharacterCard> p1Chars = new ArrayList<CharacterCard>();
    private ArrayList<CharacterCard> p2Chars = new ArrayList<CharacterCard>();
    private ArrayList<CharacterCard> p3Chars = new ArrayList<CharacterCard>();

    //scores for all the players
    private int p1Score;
    private int p2Score;
    private int p3Score;

    //gold count for all players
    private int p1Gold;
    private int p2Gold;
    private int p3Gold;

    private String action;

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
    private int p1Character1;
    private int p1Character2;
    private int p2Character1;
    private int p2Character2;
    private int p3Character1;
    private int p3Character2;

    private CharacterCard p1Chara1;
    private CharacterCard p1Chara2;
    private CharacterCard p2Chara1;
    private CharacterCard p2Chara2;
    private CharacterCard p3Chara1;
    private CharacterCard p3Chara2;

    private CharacterCard removedCharacter;

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

    /**
     * Returns the turnCounter
     * @return turnCounter
     *          the turn
     */
    public int getTurnCounter()
    {
        return this.turnCounter;
    }

    /**
     * Sets the turnCounter
     * @param i
     *          sets the turn to i
     */
    public void setTurnCounter(int i)
    {
        this.turnCounter = i;
    }

    /**
     * Sets the turn
     * @param x
     *          the new turn
     */
    public void setTurn(int x)
    {
        this.turn = x;
    }

    /**
     * Gets the current turn
     * @return
     */
    public int getTurn()
    {
        return this.turn;
    }

    /**
     * Sets the king to a random number
     */
    public void setRollKing()
    {
        this.king = (int)Math.random()*3;
    }

    /**
     * Sets the king to a specified int
     * @param x
     */
    public void setKing(int x)
    {
        this.king = x;
    }

    /**
     * Gets the king's
     * @return king
     *          the current king
     */
    public int getKing()
    {
        return king;
    } // TODO need to implement who is king and who has the king character card

    /**
     * Gets player 1's first character
     * @return p1Character1
     *          the player's first character
     */
    public int getP1Character1()
    {
        return p1Character1;
    }

    /**
     * Gets player 1's second character
     * @return p1Character2
     *          the player's second character
     */
    public int getP1Character2()
    {
        return p1Character2;
    }

    /**
     * Gets player 2's first character
     * @return p2Character1
     *          the player's first character
     */
    public int getP2Character1()
    {
        return p2Character1;
    }

    /**
     * Gets player 2's second character
     * @return p2Character2
     *          the player's second character
     */
    public int getP2Character2()
    {
        return p2Character2;
    }

    /**
     * Gets player 3's first character
     * @return p3Character1
     *          the player's first character
     */
    public int getP3Character1()
    {
        return p3Character1;
    }

    /**
     * Gets player 3's second character
     * @return p3Character2
     *          the player's second character
     */
    public int getP3Character2()
    {
        return p3Character2;
    }

    /**
     * Sets player 1's character 1 to the chosen character
     * @param name
     *          the character's name
     * @param color
     *          the character's color
     */
    public void setP1Character1(int name, int color)
    {
        this.p1Chara1 = new CharacterCard(name, color);
    }

    /**
     * Sets player 1's character 2 to the chosen character
     * @param name
     *          the character's name
     * @param color
     *          the character's color
     */
    public void setP1Character2(int name, int color)
    {
        this.p1Chara2 = new CharacterCard(name, color);
    }

    /**
     * Sets player 2's character 1 to the chosen character
     * @param name
     *          the character's name
     * @param color
     *          the character's color
     */
    public void setP2Character1(int name, int color)
    {
        this.p2Chara1 = new CharacterCard(name, color);
    }

    /**
     * Sets player 2's character 2 to the chosen character
     * @param name
     *          the character's name
     * @param color
     *          the character's color
     */
    public void setP2Character2(int name, int color)
    {
        this.p2Chara2 = new CharacterCard(name, color);
    }

    /**
     * Sets player 3's character 1 to the chosen character
     * @param name
     *          the character's name
     * @param color
     *          the character's color
     */
    public void setP3Character1(int name, int color)
    {
        this.p3Chara1 = new CharacterCard(name, color);
    }

    /**
     * Sets player 3's character 2 to the chosen character
     * @param name
     *          the character's name
     * @param color
     *          the character's color
     */
    public void setP3Character2(int name, int color)
    {
        this.p3Chara2 = new CharacterCard(name, color);
    }

///////////////////////////////////Deals with players stats/////////////////////////////////
    // TODO can combine CitadelsDistrictCard and removeDistrictCard

    /**
     * Draws a card from the district's deck and returns it
     * @return CitadelsDistrictCard
     *          the top most card
     */
    public CitadelsDistrictCard drawCard()
    {
        return this.deckOrderDistricts.get(0);
    }

    /**
     * Removes a card from the district deck
     */
    public void removeCard()
    {
        this.deckOrderDistricts.remove(0);
    }

    /**
     * Draws a district card and removes it from the deck
     * @return  CitadelsDistrictCard
     *          the top most card
     */
    public CitadelsDistrictCard drawDistrictCard()
    {
        CitadelsDistrictCard cdc = this.deckOrderDistricts.get(0);
        this.deckOrderDistricts.remove(0);
        return cdc;
    }

    /**
     * Adds district card to player 1's city (built)
     * @param dc
     *          the district card to be built from the hand
     */
    public void addToP1City(CitadelsDistrictCard dc)
    {
        this.p1City.add(dc);
    }

    /**
     * Adds district card to player 2's city (built)
     * @param dc
     *          the district card to be built from the hand
     */
    public void addToP2City(CitadelsDistrictCard dc)
    {
        this.p2City.add(dc);
    }

    /**
     * Adds district card to player 3's city (built)
     * @param dc
     *          the district card to be built from the hand
     */
    public void addToP3City(CitadelsDistrictCard dc)
    {
        this.p3City.add(dc);
    }

    /**
     * Sets the score of player 1
     * @param newScore
     *          the new score that will be set
     */
    public void setP1Score(int newScore)
    {
        this.p1Score = newScore;
    }

    /**
     * Sets the score of player 2
     * @param newScore
     *          the new score that will be set
     */
    public void setP2Score(int newScore)
    {
        this.p2Score = newScore;
    }

    /**
     * Sets the score of player 3
     * @param newScore
     *          the new score that will be set
     */
    public void setP3Score(int newScore)
    {
        this.p3Score = newScore;
    }

    /**
     * Gets player 1's score
     * @return p1score
     *          the players current score
     */
    public int getP1Score()
    {
        return p1Score;
    }

    /**
     * Gets player 2's score
     * @return p2score
     *          the players current score
     */
    public int getP2Score()
    {
        return p2Score;
    }

    /**
     * Gets player 3's score
     * @return p3score
     *          the players current score
     */
    public int getP3Score()
    {
        return p3Score;
    }

    /**
     * Sets player 1's gold
     * @param newGold
     *          the new gold amount
     */
    public void setP1Gold(int newGold)
    {
        this.p1Gold = newGold;
    }

    /**
     * Sets player 2's gold
     * @param newGold
     *          the new gold amount
     */
    public void setP2Gold(int newGold)
    {
        this.p2Gold = newGold;
    }

    /**
     * Sets player 3's gold
     * @param newGold
     *          the new gold amount
     */
    public void setP3Gold(int newGold)
    {
        this.p3Gold = newGold;
    }

    /**
     * Gets player 1's gold
     * @return p1Gold
     *          the current gold count
     */
    public int getP1Gold()
    {
        return p1Gold;
    }

    /**
     * Gets player 2's gold
     * @return p2Gold
     *          the current gold count
     */
    public int getP2Gold()
    {
        return p2Gold;
    }

    /**
     * Gets player 3's gold
     * @return p3Gold
     *          the current gold count
     */
    public int getP3Gold()
    {
        return p3Gold;
    }

    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Gets the action string that is being done by the player
     * @return action
     *          the current action that the player is doing
     */
    public String getAction()
    {
        return this.action;
    }

    /**
     * Sets the action string
     * @param initAction
     *          the action
     */
    public void setAction(String initAction)
    {
        this.action = initAction;
    }


    //////////////////////////////////Deals with character cards////////////////////////

    /**
     * Returns the character deck
     * @return returnList
     *          the current character deck
     */
    public CharacterCard[] getCharacterDeck()
    {
        CharacterCard[] returnList = new CharacterCard[8];
        for (int i = 0; i < characterDeck.length; ++i)
        {
            returnList[i] = characterDeck[i];
        }
        return returnList;
    }

    /**
     * Gets the character card from the character deck and
     * returns the character card at the specified index
     * @param x
     *          the index of the character deck
     * @return characterDeck
     *          the character card in the character deck
     */
    public CharacterCard getCharacterDeck(int x) { return characterDeck[x]; }

    /**
     * Creates the Character deck
     */
    public void initializeCharacterDeck()
    {
        // Making character cards
        for (int i = 0; i < 8; ++i)
        {
            if (i < 3 || i == 6)
            {
                characterDeck[i] = new CharacterCard(i, 4); // non color characters
            } else if (i == 3)
            {
                characterDeck[i] = new CharacterCard(i, 3); // King
            } else if (i == 4)
            {
                characterDeck[i] = new CharacterCard(i, 2); // Bishop
            } else if (i == 5)
            {
                characterDeck[i] = new CharacterCard(i, 1); // Merchant
            } else if (i == 7)
            {
                characterDeck[i] = new CharacterCard(i, 0); // Warlord
            }
        }
    }

    /**
     * Gets player 1's characters
     * @return p1Chars
     *          player 1's characters
     */
    public ArrayList<CharacterCard> getP1Chars()
    {
        return p1Chars;
    }

    /**
     * Gets player 2's characters
     * @return p1Chars
     *          player 2's characters
     */
    public ArrayList<CharacterCard> getP2Chars()
    {
        return p2Chars;
    }

    /**
     * Gets player 3's characters
     * @return p1Chars
     *          player 3's characters
     */
    public ArrayList<CharacterCard> getP3Chars()
    {
        return p3Chars;
    }

    /**
     * Adds a character card to player 1's character card array
     * @param card
     *          the character to add
     */
    public void addp1Chars(CharacterCard card)
    {
        p1Chars.add(card);
    }

    /**
     * Adds a character card to player 2's character card array
     * @param card
     *          the character to add
     */
    public void addp2Chars(CharacterCard card)
    {
        p2Chars.add(card);
    }

    /**
     * Adds a character card to player 3's character card array
     * @param card
     *          the character to add
     */
    public void addp3Chars(CharacterCard card)
    {
        p3Chars.add(card);
    }

    /**
     * Clears all the characters for all players
     */
    public void clearallCharacters()
    {
        p1Chars.clear();
        p2Chars.clear();
        p3Chars.clear();
    }

    /**
     * Gets the removed character
     * @return  removedCharacter
     *          the character to be removed
     */
    public CharacterCard getRemovedCharacter()
    {
        return this.removedCharacter;
    }

    /**
     * Sets the character to be removed
     * @param c
     *          the character to remove
     */
    public void setRemovedCharacter(CharacterCard c)
    {
        this.removedCharacter = c;
    }

    /**
     * Resets the removed character
     */
    public void resetRemovedCharacter()
    {
        this.removedCharacter = null;
    }

    /**
     * Gets player 1's specified character from their character array
     * @param i
     *          the index of the array
     * @return p1Chars
     *          the player's characters
     */
    public CharacterCard getP1Chars(int i)
    {
        return p1Chars.get(i);
    }

    /**
     * Gets player 2's specified character from their character array
     * @param i
     *          the index of the array
     * @return p2Chars
     *          the player's characters
     */
    public CharacterCard getP2Chars(int i)
    {
        return p2Chars.get(i);
    }

    /**
     * Gets player 3's specified character from their character array
     * @param i
     *          the index of the array
     * @return p3Chars
     *          the player's characters
     */
    public CharacterCard getP3Chars(int i)
    {
        return p3Chars.get(i);
    }

    /**
     * Gets player 1's character 1
     * @return plChara1
     *          the player's character
     */
    public CharacterCard getP1Chara1()
    {
        return p1Chara1;
    }

    /**
     * Gets player 1's character 2
     * @return plChara2
     *          the player's character
     */
    public CharacterCard getP1Chara2()
    {
        return p1Chara2;
    }

    /**
     * Gets player 2's character 1
     * @return p2Chara1
     *          the player's character
     */
    public CharacterCard getP2Chara1()
    {
        return p2Chara1;
    }

    /**
     * Gets player 2's character 2
     * @return p2Chara2
     *          the player's character
     */
    public CharacterCard getP2Chara2()
    {
        return p2Chara2;
    }

    /**
     * Gets player 3's character 1
     * @return p3Chara1
     *          the player's character
     */
    public CharacterCard getP3Chara1()
    {
        return p3Chara1;
    }

    /**
     * Gets player 3's character 2
     * @return p3Chara2
     *          the player's character
     */
    public CharacterCard getP3Chara2()
    {
        return p3Chara2;
    }

    /**
     * Gets the character's color
     * @param character
     *          the character card
     * @return color
     *          the color
     */
    public String getCharacterColor(int character)
    {
        String color = null;
        if (character == 3)
        {
            color = "Yellow";
        } else if (character == 4)
        {
            color = "Blue";
        } else if (character == 5)
        {
            color = "Green";
        } else if (character == 7)
        {
            color = "Red";
        }
        return color;
    }

    /**
     * Allows the dumb AI to know what player it is
     * @param ccpd
     *          The CitadelsComputerPlayerDumb
     * @return int
     *          the player num
     */
    public int getPlayer(CitadelsComputerPlayerDumb ccpd)
    {
        int num = ccpd.getPlayerNum();
        if (num == 0)
        {
            return 1;
        } else if (num == 1)
        {
            return 2;
        } else if (num == 2)
        {
            return 3;
        } else
        {
            return -1;
        }
    }

    /**
     * Allows the smart AI to know what player it is
     * @param ccps
     *          The CitadelsComputerPlayerSmart
     * @return int
     *          the player num
     */
    public int getPlayer(CitadelsComputerPlayerSmart ccps)
    {
        int num = ccps.getPlayerNum();
        if (num == 0)
        {
            return 1;
        } else if (num == 1)
        {
            return 2;
        } else if (num == 2)
        {
            return 3;
        } else
        {
            return -1;
        }
    }

    /**
     * Removes the characterCard from the deck after a player chooses their card
     * @param i
     *          the chosen character card to be removed from the character deck
     */
    public void removeCharacterCardFromDeck(int i)
    {
        this.characterDeck[i] = null;
    }

    /**
     * Sets the characterCard from the deck after a player chooses
     * @param i
     *          the chosen character card to be set from the character deck
     */
    public void setCharacterCardFromDeck(int i, CharacterCard c)
    {
        this.characterDeck[i] = c;
    }

    ////////////////////////////////////Deals with Players district cards//////////////////////

    /**
     * Adds district card to player 1's hand
     * @param dc
     *          The district card to be added to the hand
     */
    public void addToP1Hand(CitadelsDistrictCard dc) { this.p1Hand.add(dc); }

    /**
     * Adds district card to player 2's hand
     * @param dc
     *          The district card to be added to the hand
     */
    public void addToP2Hand(CitadelsDistrictCard dc) { this.p2Hand.add(dc); }

    /**
     * Adds district card to player 3's hand
     * @param dc
     *          The district card to be added to the hand
     */
    public void addToP3Hand(CitadelsDistrictCard dc) { this.p3Hand.add(dc); }

    /**
     * Removes a district card from player 1's hand
     * @param card
     *          The card to be removed
     */
    public void removeFromP1Hand(int card) { this.p1Hand.remove(card); }

    /**
     * Removes a district card from player 1's hand
     * @param card
     *          The card to be removed
     */

    /**
     * Removes a district card from player 2's hand
     * @param card
     *          The card to be removed
     */
    public void removeFromP2Hand(int card) { this.p2Hand.remove(card); }

    /**
     * Removes a district card from player 3's hand
     * @param card
     *          The card to be removed
     */
    public void removeFromP3Hand(int card) { this.p3Hand.remove(card); }

    /**
     * Gets player 1's district cards in their hand and displays their info
     * @return names
     *          the info of the district card in the player's hand
     */
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

    /**
     * Gets the player 1's district cards in their hand
     * @return p1Hand
     *          the player's hand
     */
    public ArrayList getP1Hand()
    {
        return this.p1Hand;
    }

    /**
     * Gets the player 2's district cards in their hand
     * @return p2Hand
     *          the player's hand
     */
    public ArrayList getP2Hand()
    {
        return this.p2Hand;
    }

    /**
     * Gets the player 3's district cards in their hand
     * @return p3Hand
     *          the player's hand
     */
    public ArrayList getP3Hand()
    {
        return this.p3Hand;
    }

    /**
     * Get's player 1's City
     * @return returnList
     *          The player's built district
     */
    public ArrayList<CitadelsDistrictCard> getP1City()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < p1City.size(); ++i)
        {
            returnList.add(p1City.get(i));
        }
        return returnList;
    }

    /**
     * Gets player 1's City card
     * @param i
     *          the num in the player's hand that represents the card selected
     * @return
     *          the district card
     */
    public CitadelsDistrictCard getP1CityCard(int i)
    {
        return p1City.get(i);
    }

    /**
     * Get's player 2's City
     * @return returnList
     *          The player's built district
     */
    public ArrayList<CitadelsDistrictCard> getP2City()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < p2City.size(); ++i)
        {
            returnList.add(p2City.get(i));
        }
        return returnList;
    }

    /**
     * Gets player 2's City card
     * @param i
     *          the num in the player's hand that represents the card selected
     * @return
     *          the district card
     */
    public CitadelsDistrictCard getP2CityCard(int i)
    {
        return p2City.get(i);
    }

    /**
     * Get's player 3's City
     * @return returnList
     *          The player's built district
     */
    public ArrayList<CitadelsDistrictCard> getP3City()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < p3City.size(); ++i)
        {
            returnList.add(p3City.get(i));
        }
        return returnList;
    }

    /**
     * Gets player 3's City card
     * @param i
     *          the num in the player's hand that represents the card selected
     * @return
     *          the district card
     */
    public CitadelsDistrictCard getP3CityCard(int i)
    {
        return p3City.get(i);
    }

    /**
     * Gets the district deck
     * @return returnList
     *          The districts in the deck
     */
    public ArrayList<CitadelsDistrictCard> getDeckOrderDistricts()
    {
        ArrayList<CitadelsDistrictCard> returnList = new ArrayList<CitadelsDistrictCard>();
        for (int i = 0; i < deckOrderDistricts.size(); ++i)
        {
            returnList.add(deckOrderDistricts.get(i));
        }
        return returnList;
    }

    /**
     * Gets player 1's built district info
     * @param index
     *          The index of the player's city
     * @return P1DistrictInfo
     *          The player's info for the district
     */
    public String getP1DistrictInfo(int index)
    {
        CitadelsDistrictCard temp = p1City.get(index);
        String P1DistrictInfo = "Name: " + temp.getName() + "\n" + "Color: " + temp.getColorString()
                + "\n" + "Cost: " + temp.getCost();
        return P1DistrictInfo;
    }

    /**
     * Gets player 2's built district info
     * @param index
     *          The index of the player's city
     * @return P2DistrictInfo
     *          The player's info for the district
     */
    public String getP2DistrictInfo(int index)
    {
        CitadelsDistrictCard temp = p2City.get(index);
        String P2DistrictInfo = "Name: " + temp.getName() + "\n" + "Color: " + temp.getColorString()
                + "\n" + "Cost: " + temp.getCost();
        return P2DistrictInfo;
    }

    /**
     * Gets player 3's built district info
     * @param index
     *          The index of the player's city
     * @return P3DistrictInfo
     *          The player's info for the district
     */
    public String getP3DistrictInfo(int index)
    {
        CitadelsDistrictCard temp = p3City.get(index);
        String P3DistrictInfo = "Name: " + temp.getName() + "\n" + "Color: " + temp.getColorString()
                + "\n" + "Cost: " + temp.getCost();
        return P3DistrictInfo;
    }

    /**
     * Sets the player 1's hand with cards (Used at the start of game)
     * @param arrayCD
     *          The array of the district card for the player
     */
    public void setP1Hand(ArrayList<CitadelsDistrictCard> arrayCD)
    {
        this.p1Hand = arrayCD;
    }

    /**
     * Sets the player 2's hand with cards (Used at the start of game)
     * @param arrayCD
     *          The array of the district card for the player
     */
    public void setP2Hand(ArrayList<CitadelsDistrictCard> arrayCD)
    {
        this.p2Hand = arrayCD;
    }

    /**
     * Sets the player 3's hand with cards (Used at the start of game)
     * @param arrayCD
     *          The array of the district card for the player
     */
    public void setP3Hand(ArrayList<CitadelsDistrictCard> arrayCD)
    {
        this.p3Hand = arrayCD;
    }

    /**
     * Initializes the district deck and shuffles it
     */
    public void initializeDistrictDeck() {
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
            deckOrderDistricts.add(new CitadelsDistrictCard("Harbor", 1, 4));
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
    }

    /**
     * Finds player 1's card
     * @param card
     *          The card to be used
     * @return p1Hand
     *          The index of the card
     */
    public int p1FindCard(CitadelsDistrictCard card)
    {
        return this.p1Hand.indexOf(card);

    }

    /**
     * Finds player 2's card
     * @param card
     *          The card to be used
     * @return p2Hand
     *          The index of the card
     */
    public int p2FindCard(CitadelsDistrictCard card)
    {
        return this.p2Hand.indexOf(card);

    }

    /**
     * Finds player 3's card
     * @param card
     *          The card to be used
     * @return p3Hand
     *          The index of the card
     */
    public int p3FindCard(CitadelsDistrictCard card)
    {
        return this.p3Hand.indexOf(card);

    }

    /**
     * Gets the top card of the district card deck
     * @return CitadelsDistrictCard
     *          The top most card
     */
    public CitadelsDistrictCard getTopCard()
    {
        return this.drawCard();
    }

    /**
     * gets the current build limit for the turn
     * @return buildLimit
     *          The abmount of districts that can be built in a turn
     */
    public int getBuildLimit()
    {
        return this.buildLimit;
    }

    /**
     * Sets the buildLimit to account for the architect card
     * @param newLimit
     *          the new limit
     */
    public void setBuildLimit(int newLimit)
    {
        this.buildLimit = newLimit;
    }

    /**
     * Sets the initial values for all the player
     */
    public CitadelsGameState()
    {
        this.setTurn(0);
        this.setTurnCounter(0);
        this.setBuildLimit(1);

        this.initializeDistrictDeck();
        this.initializeCharacterDeck();

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

        this.setRollKing();

        // sets the player's initial score values
        this.setP1Score(0);
        this.setP2Score(0);
        this.setP3Score(0);

        // sets the player's initial gold values
        this.setP1Gold(2);
        this.setP2Gold(2);
        this.setP3Gold(2);
    }

    /**
     * External Citation
     * Date: 30 April 2017
     * Problem: Could not create GameState properly
     * Resource:
     *          https://github.com/srvegdahl/SlapJack/blob/master
     *          /app/src/main/java/edu/up/cs301/slapjack/SJState.java
     * Solution: Used
     */
    public CitadelsGameState(CitadelsGameState orig)
    {
        this.buildLimit = orig.buildLimit;
        this.king = orig.king;
        this.turn = orig.turn;

        this.p1Gold = orig.p1Gold;
        this.p2Gold = orig.p2Gold;
        this.p3Gold = orig.p3Gold;

        this.p1Score = orig.p1Score;
        this.p2Score = orig.p2Score;
        this.p3Score = orig.p3Score;

        this.p1Hand = orig.p1Hand;
        this.p2Hand = orig.p2Hand;
        this.p3Hand = orig.p3Hand;

        this.p1City = orig.p1City;
        this.p2City = orig.p2City;
        this.p3City = orig.p3City;

        this.theChosenCharacterCard = orig.theChosenCharacterCard;

        this.deckOrderDistricts = orig.deckOrderDistricts;
        this.characterDeck = orig.characterDeck;

        this.p1Chara1 = orig.p1Chara1;
        this.p1Chara2 = orig.p1Chara2;
        this.p2Chara1 = orig.p2Chara1;
        this.p2Chara2 = orig.p2Chara2;
        this.p3Chara1 = orig.p3Chara1;
        this.p3Chara2 = orig.p3Chara2;

        this.p1Chars = orig.p1Chars;
        this.p2Chars = orig.p2Chars;
        this.p3Chars = orig.p3Chars;

        this.turnCounter = orig.turnCounter;

    }

    public void removeFromP2City(int indexOfDistrict)
    {
    }

    public void removeFromP3City(int indexOfDistrict)
    {
    }
}