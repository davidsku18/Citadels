package edu.up.citadels.citadels;

/**
 * @author Bryce Amato
 * @author Gavin Low
 * @author Victor Nguyen
 * @author Kurtis Davidson
 * @version 3/10/2017
 */

public class CitadelsDistrictCard {


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
        return this.name;
    }

    //Get CitadelsCard Color
    public int getColor()
    {
        return this.color;
    }

    public String getInfo(CitadelsDistrictCard cdc)
    {
        String info = cdc.getName() + " Cost: " + cdc.getCost() + " Color: " + cdc.getColorString();
        return info;
    }

    //Gets DistrictCard Color in String form
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

    //Get CitadelsCard Cost
    public int getCost()
    {
        return cost;
    }
}
