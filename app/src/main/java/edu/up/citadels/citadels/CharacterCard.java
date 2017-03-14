package edu.up.citadels.citadels;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 2/22/2017
 */

public class CharacterCard
{
    private int red = 0;    // Codes for the 4 colors
    private int green = 1;
    private int blue = 2;
    private int yellow = 3;
    private int other = 4;

    private int assassin = 1;
    private int thief = 2;
    private int magician = 3;
    private int king = 4;
    private int bishop = 5;
    private int merchant = 6;
    private int architect = 7;
    private int warlord = 8;

    /*
    private String char1 = "assassin";
    private String char2 = "thief";
    private String char3 = "magician";
    private String char4 = "king";
    private String char5 = "bishop";
    private String char6 = "merchant";
    private String char7 = "architect";
    private String char8 = "warlord";
    */

    private int color;
    private int whichCharacter;

    //regular constructor
    public CharacterCard(int initCharacter, int initColor)
    {
        this.whichCharacter = initCharacter;
        this.color = initColor;
    }

    //copy constructor
    public CharacterCard(CharacterCard orig)
    {
        this(orig.whichCharacter, orig.color);
    }

    /*
    public int getCharacter(int who)
    {
        return 1;
    }
    */

}
