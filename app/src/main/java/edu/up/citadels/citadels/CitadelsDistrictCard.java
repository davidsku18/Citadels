package edu.up.citadels.citadels;

/**
 * The district card object used for building
 *
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 4/30/2017
 */

public class CitadelsDistrictCard {

    // The district card's values
    private final String name;
    private final int color;
    private final int cost;

    /**
     * Constructor for the CitadelsDistrictCard
     * @param theCard
     *          the card's name
     * @param theColor
     *          the card's color
     * @param theCost
     *          the card's cost
     */
    public CitadelsDistrictCard(String theCard, int theColor, int theCost)
    {
        name = theCard;
        color = theColor;
        cost = theCost;
    }

    /**
     * Gets the district card's name
     * @return
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Gets the district card's color
     * @return
     */
    public int getColor()
    {
        return this.color;
    }

    /**
     * Gets the district card's info
     * @param cdc
     * @return
     */
    public String getInfo(CitadelsDistrictCard cdc)
    {
        String info = cdc.getName() + " Cost: " + cdc.getCost() + " Color: " + cdc.getColorString();
        return info;
    }

    /**
     * Gets the color of the district card
     * @return String
     *          the district card's color
     */
    public String getColorString()
    {
        if(color == 0)
        {
            return "Red";
        }
        else if(color == 1)
        {
            return "Green";
        }
        else if(color == 2)
        {
            return "Blue";
        }
        else
        {
            return "Yellow";
        }

    }

    /**
     * Gets the district card's cost
     * @return cost
     *          the district card's cost
     */
    public int getCost()
    {
        return cost;
    }
}
