package edu.up.citadels.citadels;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class CitadelsDistrictCard {

    private int red = 0;    // Codes for the 4 colors
    private int green = 1;
    private int blue = 2;
    private int yellow = 3;

    protected final int tavern = 1;     // Codes for the cost of each card
    protected final int market = 2;
    protected final int tradingPost = 2;
    protected final int docks = 3;
    protected final int harbor = 4;
    protected final int townHall = 5;
    protected final int temple = 1;
    protected final int church = 2;
    protected final int monastery = 3;
    protected final int cathedral = 5;
    protected final int watchtower= 1;
    protected final int prison= 2;
    protected final int battlefield = 3;
    protected final int fortress = 5;
    protected final int manor = 3;
    protected final int castle = 4;
    protected final int palace = 5;
    protected final int hauntedCity = 2;
    protected final int keep = 3;
    protected final int laboratory = 5;
    protected final int smithy = 5;
    protected final int graveyard = 5;
    protected final int observatory = 5;
    protected final int schoolOfMagic = 6;
    protected final int library = 6;
    protected final int greatWall = 6;
    protected final int university = 8;
    protected final int dragonGate = 8;

    private final String name;
    private final int color;
    private final int cost;

    //original constructor
    public CitadelsDistrictCard(String theCard, int theColor, int theCost)
    {
        name = theCard;
        color = theColor;
        cost = theCost;
    }

    //copy constructor
    public CitadelsDistrictCard(CitadelsDistrictCard orig)
    {
        this(orig.name, orig.color, orig.cost);
    }

    //Getter Methods

    //Get CitadelsCard Name
    public String getName()
    {
        return name;
    }

    //Get CitadelsCard Color
    public int getColor()
    {
        return color;
    }

    //Get CitadelsCard Cost
    public int getCost()
    {
        return cost;
    }
}
