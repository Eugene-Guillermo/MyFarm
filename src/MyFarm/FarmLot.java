package MyFarm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Farmlot object represents a single field/lot
 * This lot can grow a single  object 
 * 
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class FarmLot
{
    /**
     * The crop object inside the lot. The fault is set to nothing
     */
    private Crop crop = new Crop();
    /**
     * Determines whether the lot is occupied by a "Rock", "Empty", or "Crop"
     * When a FarmLot is created, there is a Rock inside the lot
     */
    private String occupancy = "Rock";
    /**
     * Determines if the lot is plowed or not
     * When a FarmLot is created it is NOT plowed
     */
    private Boolean isPlowed = false;

    /**
     * This is the setter method for the occupancy attribute
     * @param occupancy This determines what the lot is occupied with.
     */
    public void setOccupancy(String occupancy)
    {
        this.occupancy = occupancy;
    }
    
    /**
     * This is the getter method for the occupancy attribute
     * @return The given occupancy in String form
     */
    public String getOccupancy()
    {
        return this.occupancy;
    }

    /**
     * Determines if the given Player object can plow This FarmLot
     * The player uses his Plow.
     * @param player given Player
     */
    public void plow (Player player)
    {
        if (this.isPlowed) // if the lot is already plowed
            System.out.println("This lot is already plowed.");
        else if (this.occupancy.equals("Empty")) // if the lot is empty
        {
            this.isPlowed = true;
            player.gainExperience(0.5);
            System.out.println("Successfully plowed the lot");
        }
        else if (this.occupancy.equals("Rock")) // if the lot has a rock inside
            System.out.println("There is a rock in the way. You cannot plow the lot yet.");
    }

    /**
     * The getter function for the isPlowed attribute
     * @return the true or false whether the lot Is Plowed
     */
    public Boolean isItPlowed ()
    {
        return this.isPlowed;
    }

    /**
     * Determines if the given Player object can dig This FarmLot
     * The Player uses his shovel.
     * @param player given Player
     */
    public void dig(Player player)
    {
        if (this.occupancy.equals("Crop") && player.getCoin() >= 7) // if the lot has a  and player has enough coins
        {
            if (this.crop.getIsWithered()) // crop is already withered
            {
                this.occupancy = "Empty";
                isPlowed = false;
                crop = new Crop();
                System.out.println("Successfully dug the withered crop out of the lot");
                player.spendCoin(7);
                player.gainExperience(2);
            }
            else // if  is not yet withered
                System.out.println("You can't dig out this crop that is alive and well.");

        }
        else if (player.getCoin() < 7) // not enough coins
            System.out.println("You don't have enough coins to dig.");
        else if (!this.occupancy.equals("Crop")) // there is no crop
            System.out.println("What are you even trying to dig?");
    }

     /**
      * Determines if the given Player object can mine This FarmLot
      * The Player uses his pickaxe.
      * @param player given Player
      */
    public void mine(Player player)
    {
        if(this.occupancy.equals("Rock")) // if there is a rock in the lot
        {
            if(player.getCoin() >= 50) // the player has enough money
            {
                player.spendCoin(50);
                player.gainExperience(15);
                this.occupancy = ("Empty");
                System.out.println("Successfully mined the rock");
            }
            else // the player does not have enough money
                System.out.println("Not enough coins to mine this rock");
        }
        else // there is no rock in the lot
            System.out.println("There is no rock to mine in this lot.");
    }

    /**
     * Determines if the given Player object can buy a seed and plant it in This FarmLot
     * @param player given Player
     */
    public void buySeed(Player player)
    {
        if(this.isPlowed && this.occupancy.equals("Empty")) // the lot is plowed and empty
        {
            if (player.getCoin() >= 5 + player.getSeedDiscount()) // player has enough money
            {
                this.setCrop("Turnip");
                System.out.println("Successfully planted a Turnip");
                player.spendCoin(5 + player.getSeedDiscount());
                this.occupancy = "Crop";
            }
            else // player does not have enough money
            {
                System.out.println("Insufficient coins");
            }
        }
        else if(this.occupancy.equals("Rock")) // the lot has a rock
        {
            System.out.println("Cannot plant seed in this lot");
            System.out.println("This lot has a rock");
        }
        else if(this.occupancy.equals("Crop")) // the lot has a crop inside
        {
            System.out.println("Cannot plant seed in this slot");
            System.out.println("This lot has a crop already");
        }
        else if(!this.isPlowed) // the lot is not plowed
        {
            System.out.println("Cannot plant seed in this lot");
            System.out.println("This lot needs to be plowed");
        }
    }

    /**
     * Determines if the given Player object can water the  in This FarmLot
     * The player uses his watering can.
     * @param player given Player
     */
    public void water(Player player)
    {
        if (this.occupancy.equals("Crop")) // there is a crop inside the lot
        {
            player.gainExperience(0.5);
            this.crop.setWaterLevel(this.crop.getWaterLevel() + 1);
            System.out.println(("Successfully watered the crop"));
        }
        else // there is no crop inside the lot
            System.out.println("There is no crop to water");

    }

    /**
     * Determines if the given Player object can fertilize the  in This FarmLot
     * The player uses his fertilizer.
     * @param player given Player
     */
    public void fertilize (Player player)
    {
        if (this.occupancy.equals("Crop")) // there is a crop inside the lot
        {
            if (player.getCoin() >= 10) // player has enough money
            {
                player.spendCoin(10);
                player.gainExperience(4);
                this.crop.setFertilizerLevel(this.crop.getFertilizerLevel() + 1);
                    System.out.println("Successfully fertilized the crop");
            }
            else // player does not have enough money
                System.out.println("You don't have enough coins to get fertilizer.");
        }
        else // there is no  inside the lot
            System.out.println("There is no crop to fertilize");
    }

    /**
     * The setter function for the crop object attribute in the FarmLot
     * @param name The name of the species of the crop
     */
    public void setCrop(String name) {
        this.crop.plantCrop(name);
    }

    /**
     * Determines if the player can harvest a crop inside this farm lot
     * @param player given Player
     */
    public void harvest(Player player)
    {
        if (this.crop.getCanHarvest() && occupancy.equals("Crop")) // the crop can be harvested
        {
            // the random number of produce the crop will give
            int yieldNum = ThreadLocalRandom.current().nextInt(this.crop.getYieldLowerBound(), this.crop.getYieldUpperBound());
            double harvestTotal = yieldNum * (this.crop.getSellPrice() + player.getSellBonus());
            double waterBonus;
            double fertilizerBonus;
            if(this.crop.getWaterLevel() > this.crop.getWaterBonus() + player.getWaterBonusBonus()) // water level is over the bonus limit
                waterBonus = harvestTotal * 0.2 * (this.crop.getWaterBonus() + player.getWaterBonusBonus() - 1);
            else // equal or under
                waterBonus = harvestTotal * 0.2 * (this.crop.getWaterLevel() - 1);

            if(this.crop.getFertilizerLevel() > this.crop.getFertilizerBonus() + player.getFertilizerBonusBonus()) // fertilizer level is over the bonus limit
                fertilizerBonus = harvestTotal * 0.5 * (this.crop.getFertilizerBonus() + player.getFertilizerBonusBonus() - 1);
            else // equal or under
                fertilizerBonus = harvestTotal * 0.5 * (this.crop.getFertilizerLevel() - 1);
            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (this.crop.getCropType().equals("Flower"))
                finalHarvestPrice *= 1.1;
            player.earnCoin(finalHarvestPrice);
            this.crop = new Crop();
            this.isPlowed = false;
            this.occupancy = "Empty";
            System.out.println("Successfully harvested " + yieldNum + " number of crops! You gained " + finalHarvestPrice + " coins.");
        }
        else if (this.occupancy.equals("Empty")) // there is no crop
            System.out.println("There is no crop in this lot.");
        else if (this.occupancy.equals("Rock"))
            System.out.println("There is a rock here");
        else if (this.crop.getIsWithered() && this.occupancy.equals("Crop")) // the crop is withered
            System.out.println("The crop is withered");
        else // the crop is not yet ready to harvest
            System.out.println("This crop is still not ready for harvest.");
    }

    /**
     * Ages the FarmLot and its  object by one day.
     */
    public void ageLot()
    {
        this.crop.age();
    }

    /**
     * Displays on the terminal crucial information of the FarmLot to the player.
     */
    public void inform()
    {
        if(isPlowed && (this.occupancy.equals("Empty")))
            System.out.println("The lot is plowed and ready to plant");
        else if (!isPlowed && (this.occupancy.equals("Empty")))
            System.out.println("The lot is empty, but not plowed");
        else if (this.occupancy.equals("Rock"))
            System.out.println("The lot has a rock");
        else if(this.occupancy.equals("Crop"))
            this.crop.displayInfo();
        else
            System.out.println("Something is wrong");
        // print the occupancy and farm lot status
        // check the farm lot status
    }

    /**
     * Displays on the terminal crucial information about the  inside the FarmLot to the player.
     */
    public void checkStatus()
    {
        // Checks if there is a crop in the lot
        if (this.crop.getSeedName().equals(""))
        {
            if (this.crop.getTillHarvest() == 1 && this.crop.getWaterLevel() < this.crop.getWaterNeed())
                System.out.println("NOTIF: The  needs more water or else it'll wither tomorrow!");
            if (this.crop.getTillHarvest() == 1 && this.crop.getFertilizerLevel() < this.crop.getFertilizerNeed())
                System.out.println("NOTIF: The  needs more fertilizer or else it'll wither tomorrow!");
            if (this.crop.getCanHarvest())
                System.out.println("NOTIF: The  can be harvested! Please harvest it in the day or else it will wither!!");
            if (this.crop.getIsWithered())
                System.out.println("NOTIF: The  has withered. It seems it needs to be dug out.");
        }
    }

    /**
     * Displays on the terminal the ASCII art of the  inside the FarmLot to the player.
     */
    public void display()
    {
        if (this.occupancy.equals("Crop")) // there is a crop inside the lot
        {
            System.out.println("________________"); 
            System.out.println("|              |");
            System.out.println("| ..        .. |");
            System.out.println("| \\ \\      / / |");
            System.out.println("|   \\  )  ( /  |");
            System.out.println("|     |   |    |");
            System.out.println("|    /      \\  |");
            System.out.println("|   ( Crop  )  |");
            System.out.println("|    (     )   |");
            System.out.println("______\\___/_____");
        } 
        else if (this.occupancy.equals("Empty")) // the lot is empty
        {
            if (this.isPlowed) // the lot is plowed
            {
                System.out.println("________________");
                System.out.println("|| || || || || |");
                System.out.println("|| || || || || |");
                System.out.println("|| || || || || |");
                System.out.println("|| || || || || |");
                System.out.println("|| || || || || |");
                System.out.println("|| || || || || |");
                System.out.println("|| || || || || |");
                System.out.println("|| || || || || |");
                System.out.println("________________");
            }
            else // the lot is not plowed
            {
                System.out.println("________________"); 
                System.out.println("|              |");
                System.out.println("|              |");
                System.out.println("|              |");
                System.out.println("|              |");
                System.out.println("|              |");
                System.out.println("|              |");
                System.out.println("|              |");
                System.out.println("|              |");
                System.out.println("________________");
            }
        } 
        else if (this.occupancy.equals("Rock")) // the lot has a rock
        {
            System.out.println("________________"); 
            System.out.println("|              |");
            System.out.println("|    .--.      |");
            System.out.println("|  /      \\    |");
            System.out.println("| (  ROCK  )   |");
            System.out.println("|   \\  _ /     |");
            System.out.println("|              |");
            System.out.println("|              |");
            System.out.println("|              |");
            System.out.println("________________");
        }
    }
}

