package edu.up.citadels.citadels;

/**
 * The Character card object
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 4/30/2017
 */

public class CharacterCard
{
    // Ints for the 4 colors
    private int red = 0;
    private int green = 1;
    private int blue = 2;
    private int yellow = 3;
    private int other = 4;

    // Ints for the characters
    private int assassin = 0;
    private int thief = 1;
    private int magician = 2;
    private int king = 3;
    private int bishop = 4;
    private int merchant = 5;
    private int architect = 6;
    private int warlord = 7;

    // The character card's values
    private int color;
    private int whichCharacter;

    /**
     * Constructor for the character card
     * @param initCharacter
     *          the character's num
     * @param initColor
     *          the character's color
     */
    public CharacterCard(int initCharacter, int initColor)
    {
        this.whichCharacter = initCharacter;
        this.color = initColor;
    }

    /**
     * Returns the color of the character card
     * @return color
     *          the color of the character card
     */
    public int getColor()
    {
        return color;
    }

    /**
     * Returns an int which is used to specify which character
     * @return whichCharacter
     *          The character's int
     */
    public int getWhichCharacter()
    {
        return whichCharacter;
    }

    /**
     * Returns the character's name based on their
     * whichCharacter int
     * @return  name
     *          The character's name
     */
    public String getName()
    {
        String name = null;

        switch (whichCharacter)
        {
            case -1:
                name = "nothing";
                break;

            case 1:
                name =  "Assassin";
                break;

            case 2:
                name =  "Thief";
                break;

            case 3:
                name =  "Magician";
                break;

            case 4:
                name =  "King";
                break;

            case 5:
                name =  "Bishop";
                break;

            case 6:
                name =  "Merchant";
                break;

            case 7:
                name =  "Architect";
                break;

            case 8:
                name =  "Warlord";
                break;

        }
        return name;
    }
}
