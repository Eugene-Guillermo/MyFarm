package MyFarm;

public class Toolbar
{
    
    public void mine(Player player, FarmLot farmLand)
    {
        if(farmLand.getOccupancy().equals("Rock"))
        {
            if(player.getCoin() >= 50)
            {
                player.spendCoin(50);
                player.gainExperience(15);
                farmLand.setOccupancy("Empty");
                System.out.println("Successfully mined the rock");
            }
            else
                System.out.println("Not enough coins to mine this rock");
        }
        else
            System.out.println("There is no rock. You cannot mine this tile");

    }

    public void dig(Player player, FarmLot farmLand)
    {
        if (farmLand.getOccupancy().equals("MyFarm.Crop") && player.getCoin() >= 7)
         {
            //farmLand.beDug();
            player.spendCoin(7);
            player.gainExperience(2);
        }
        else if (player.getCoin() < 7)
            System.out.println("You don't have enough coins to dig.");
        else if (!farmLand.getOccupancy().equals("MyFarm.Crop"));
            System.out.println("What are you even trying to dig?");
        
    }
    
    public void water(Player player, FarmLot farmLand)
    {
        if(farmLand.getOccupancy().equals("Crop"))
        {
            player.gainExperience(0.5);
            //farmLand.beWatered();
            System.out.println(("Successfully watered the crop"));
        }

        else
            System.out.println("There is no crop to water");
    
    }
    
    public void fertilize (Player player, FarmLot farmLand)
    {
        if(farmLand.getOccupancy().equals("MyFarm.Crop"))
        {
            if (player.getCoin() >= 10)
            {
                player.spendCoin(10);
                player.gainExperience(4);
                //farmLand.beFertlized();
                System.out.println("Successfully fertilize the crop");
            }
            else
                System.out.println("You don't have enough coins to get fertilizer.");
            
        }
        else
            System.out.println("There is no crop to fertilize");        
    }

    public void plow (Player player, FarmLot farmLand)
    {
        if (farmLand.isItPlowed())
            System.out.println("This lot is already plowed.");
        else if (farmLand.getOccupancy().equals("Empty"))
        {
            //farmLand.bePlowed(true);
            player.gainExperience(0.5);
            System.out.println("Successfully plowed the lot");
        }
        else if (farmLand.getOccupancy().equals("Rock"))
            System.out.println("There is a rock in the way. You cannot plow the lot yet.");
    }


    public void buySeed(Player player, FarmLot farmLand)
    {
        if(farmLand.isItPlowed() && farmLand.getOccupancy().equals("Empty"))
        {

            if(player.getCoin() >= 5)
            {
                farmLand.setCrop("Turnip");
                System.out.println("Successfully planted a Turnip");
                player.spendCoin(5);
            }

            else
            {
                System.out.println("Insufficient coins");
            }
        }
        else if(!farmLand.isItPlowed())
        {
            System.out.println("Cannot plant seed in this lot");
            System.out.println("This lot needs to be plowed");
        }

        else if(farmLand.getOccupancy().equals("Rock"))
        {
            System.out.println("Cannot plant seed in this lot");
            System.out.println("This lot has a rock");
        }

        else if(farmLand.getOccupancy().equals("Crop"))
        {
            System.out.println("Cannot plant seed in this slot");
            System.out.println("This lot has a Crop already");
        }
    }

    public void harvest(Player player, FarmLot farmLand)
    {
        // other stuff that takes in inputs
        farmLand.harvest(player);
    }
    
}
