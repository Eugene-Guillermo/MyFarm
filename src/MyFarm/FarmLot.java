package MyFarm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Farm lot object represents a single field/lot
 * This lot can grow a single object
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.3
 */
public class FarmLot {
    /**
     * The crop object inside the lot. The crop is set to nothing
     */
    private Crop crop;
    /**
     * Determines whether the lot is occupied by a "Rock", "Empty", or "Crop"
     * When a FarmLot is created, It is Empty inside the lot
     */
    private String occupancy;
    /**
     * Determines if the lot is plowed or not
     * When a FarmLot is created it is NOT plowed
     */
    private Boolean isPlowed;

    /**
     * The constructor of Farmlot.
     * The default starting settings, which is an empty unplowed lot
     */
    public FarmLot(){
        this.crop = new Crop();
        this.occupancy = "Empty";
        this.isPlowed = false;
    }

    /**
     * Checks if player can plow the current lot
     * If player is able to plow, the player will gain experience
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @param player given Player
     * @return in String form, the success or failure of the plow action
     */
    public String plow(Player player) {
        if (this.isPlowed) // if the lot is already plowed
            return "N: This lot is already plowed.";
        else if (this.occupancy.equals("Empty")){ // if the lot is empty
            this.isPlowed = true;
            player.gainExperience(0.5);
            return "G: Successfully plowed the lot, You gained 0.5 exp.";
        } else if (this.occupancy.equals("Rock")) // if the lot has a rock inside
            return "W: There is a rock in the way. You cannot plow the lot yet.";
        // if the code reaches here there is a bug
        return "C: Something went wrong";
    }

    /**
     * Checks if player can use his pickaxe on the current lot
     * If player is able to mine, the player will gain experience
     * and would destroy the rock inside the lot.
     * In exchange, the player will spend 50 coins.
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @param player given Player
     * @return in String form, the success or failure of the mine action
     */
    public String mine(Player player) {
        if (this.occupancy.equals("Rock")) { // if there is a rock in the lot
            if (player.getCoin() >= 50) { // the player has enough money
                player.spendCoin(50);
                player.gainExperience(15);
                this.occupancy = ("Empty");
                return ("G: Successfully mined the rock.<br>You used 50 coins in the process.");
            } else // the player does not have enough money
                return ("C: Not enough coins to mine this rock");
        } else { // there is no rock in the lot
            if (player.getCoin() >= 50) { // the player has enough money
                player.spendCoin(50);
                return ("W: You tried to mine. However, there is no rock to mine in this lot.<br>You used 50 coins in the process.");
            } else // the player does not have enough money
                return ("C: Not enough coins to mine");
        }
    }

    /**
     * Checks if player can use his shovel on the current lot
     * If player is able to dig, the player will gain experience
     * and would remove the withered crop inside the lot.
     * In exchange, the player will spend 7 coins.
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @param player given Player
     * @return in String form, the success or failure of the dig action
     */
    public String dig(Player player) {
        if (this.occupancy.equals("Crop") && player.getCoin() >= 7) { // if the lot has a and player has enough coins
            if (this.crop.getIsWithered()) { // crop is already withered
                this.occupancy = "Empty";
                isPlowed = false;
                crop = new Crop();
                player.spendCoin(7);
                player.gainExperience(2);
                return "G: Successfully dug the withered crop out of the lot.<br>You used 7 coins in the process.<br>You gained 2 exp.";
            } else // if is not yet withered
            {
                player.spendCoin(7);
                return "W: You tried to use the shovel.<br>However, you can't dig out this crop that is alive and well.<br>You used 7 coins in the process.";
            }
        } else if (player.getCoin() < 7) // not enough coins
            return "W: You don't have enough coins to dig.";
        else if (!this.occupancy.equals("Crop")) // there is no crop
        {
            player.spendCoin(7);
            return "W: You tried to use the shovel.<br>However, you do not find anything to dig.<br>You used 7 coins in the process.";
        }
        // if the code reaches here there is a bug
        return "C: Something went wrong";
    }

    /**
     * Checks if player can buy and plant a seed on the current lot
     * If player is able to plant, the lot would contain
     * the desired species of crop
     * In exchange, the player will spend
     * the appropriate amount of coins.
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @param player given Player
     * @param cropName species name of crop to be planted
     * @return in String form, the success or failure of the buying and planting the seed
     */
    public String buySeed(Player player, String cropName) {
        Crop temp = new Crop(cropName);

        if (this.isPlowed && this.occupancy.equals("Empty")) { // the lot is plowed and empty
            if (player.getCoin() >= temp.getSeedCost() + player.getSeedDiscount()) { // player has enough money
                this.setCrop(cropName);
                this.occupancy = "Crop";
                player.spendCoin(this.crop.getSeedCost() + player.getSeedDiscount());
                return ("G:Successfully planted a " + cropName + "<br>You spent " + (this.crop.getSeedCost() + player.getSeedDiscount()) + " coins.");
            } else { // player does not have enough money
                return "W:Insufficient coins";
            }
        } else if (this.occupancy.equals("Rock")) // the lot has a rock
              return "W:Cannot plant seed in this lot <br>This lot has a rock";
          else if (this.occupancy.equals("Crop"))  // the lot has a crop inside
              return "W:Cannot plant seed in this slot<br>This lot has a crop already";
          else if (!this.isPlowed) // the lot is not plowed
              return "W:Cannot plant seed in this lot<br>This lot needs to be plowed";
        // if the code reaches here there is a bug
        return "C: Something went wrong";
    }

    /**
     * Checks if player can use his watering can on the current lot
     * If player is able to water the lot, the player will gain experience
     * The crop of the current lot would have its water level increased
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @param player given Player
     * @return in String form, the success or failure of the water action
     */
    public String waterCrop(Player player) {
        if (this.occupancy.equals("Crop")) { // there is a crop inside the lot
            if(!this.crop.getIsWithered()) { // not withered
                player.gainExperience(0.5);
                this.crop.setWaterLevel(this.crop.getWaterLevel() + 1);
                return "G: Successfully watered the crop<br>You gained 0.5 exp.";
            } else // withered
                return "W: You tried to water a withered crop.";
        } else // there is no crop inside the lot
            return "W: There is no crop to water";
    }

    /**
     * Checks if player can use his fertilize the current lot
     * If player is able to fertilize, the player will gain experience
     * The crop of the current lot would have its fertilizer level increased
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @param player given Player
     * @return in String form, the success or failure of the fertilize action
     */
    public String fertilizeCrop(Player player) {
        if (this.occupancy.equals("Crop")) { // there is a crop inside the lot
            if (player.getCoin() >= 10) { // player has enough money
                player.spendCoin(10);
                if (this.crop.getIsWithered().equals(false)) {
                    player.gainExperience(4);
                    this.crop.setFertilizerLevel(this.crop.getFertilizerLevel() + 1);
                    return "G: Successfully fertilized the crop<br>You used 10 coins in the process.<br>You gained 4 exp.";
                } else {
                    return "C: You wastefully fertilized a withered crop<br>You used 10 coins in the process.";
                }
            } else // player does not have enough money
                return "W: You don't have enough coins to get fertilizer.";
        } else { // there is no crop inside the lot
            if (player.getCoin() >= 10) { // player has enough money
                player.spendCoin(10);
                return "W: There is no crop to fertilize, but you put fertilizer anyway<br>You used 10 coins in the process.";
            } else
                return "W: You don't have enough coins to get fertilizer.";
        }
    }

    /**
     * Checks if player can harvest a crop in the current lot
     * If player is able to harvest,
     * the player will gain experience and coins according to the crop species
     * The lot would become empty afterward
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @param player given Player
     * @return in String form, the success or failure of the harvest action
     */
    public String harvestCrop(Player player) {
        if (this.crop.getCanHarvest() && occupancy.equals("Crop")) { // the crop can be harvested
            // the random number of produce the crop will give
            int yieldNum = ThreadLocalRandom.current().nextInt(this.crop.getYieldLowerBound(), this.crop.getYieldUpperBound() + 1);

            double harvestTotal = yieldNum * (this.crop.getSellPrice() + player.getSellBonus());
            double waterBonus;
            double fertilizerBonus;
            // water level is over the bonus limit
            if (this.crop.getWaterLevel() > this.crop.getWaterBonus() + player.getWaterBonusBonus())
                waterBonus = harvestTotal * 0.2 * (this.crop.getWaterBonus() + player.getWaterBonusBonus() - 1);
            else // equal or under
                waterBonus = harvestTotal * 0.2 * (this.crop.getWaterLevel() - 1);
            // fertilizer level is over the bonus limit
            if (this.crop.getFertilizerLevel() > this.crop.getFertilizerBonus() + player.getFertilizerBonusBonus())
                fertilizerBonus = harvestTotal * 0.5 * (this.crop.getFertilizerBonus() + player.getFertilizerBonusBonus());
            else // equal or under
                fertilizerBonus = harvestTotal * 0.5 * (this.crop.getFertilizerLevel());
            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (this.crop.getCropType().equals("Flower"))
                finalHarvestPrice *= 1.1;
            player.earnCoin(finalHarvestPrice);
            player.gainExperience(this.crop.getExperience());
            double exp = this.crop.getExperience();
            this.crop = new Crop();
            this.isPlowed = false;
            this.occupancy = "Empty";
            return "G: Successfully harvested " + yieldNum + " number of crops! You gained " + finalHarvestPrice + " coins. You gained " + exp + " experience.";
        } else if (this.occupancy.equals("Empty")) // there is no crop
            return "W: There is no crop in this lot.";
        else if (this.occupancy.equals("Rock"))
            return "W: There is a rock here";
        else if (this.crop.getIsWithered() && this.occupancy.equals("Crop")) // the crop is withered
            return "C: The crop is withered";
        else // the crop is not yet ready to harvest
            return "N: This crop is still not ready for harvest.";
    }

    /**
     * Ages the FarmLot and its crop by one day.
     */
    public void ageLot() {
        if(this.occupancy.equals("Crop"))
            this.crop.age();
    }

    /**
     * Checks whether the crop would wither or is ready to harvest
     *
     * @return character representing the Crop's status
     */
    public char checkStatus() {
        // Checks if there is a crop in the lot
        if (this.occupancy.equals("Crop")) {
            if (this.crop.getTillHarvest() == 1 && this.crop.getWaterLevel() < this.crop.getWaterNeed())
                return 'W';
            if (this.crop.getTillHarvest() == 1 && this.crop.getFertilizerLevel() < this.crop.getFertilizerNeed())
                return 'F';
            if (this.crop.getCanHarvest())
                return 'H';

        }
        return 'p';
    }

    /**
     * Displays the information of the current lot, through a string
     * The String also includes HTML styling
     * This function is meant to be called by the setText() function
     * of the selectedLabel JLabel in the FarmFrame class
     *
     * @return crucial information about the current lot
     */
    public String inform() {

        if (isPlowed && (this.occupancy.equals("Empty")))
            return "<p style = \"text-align:center\">The lot is plowed and ready to plant</p></html>";
        else if (!isPlowed && (this.occupancy.equals("Empty")))
            return "<p style = \"text-align:center\">The lot is empty, but not plowed</p></html>";
        else if (this.occupancy.equals("Rock"))
            return  "<p style = \"text-align:center\">The lot has a rock</p></html>";
        else if (this.occupancy.equals("Crop"))
            return this.crop.cropInfo();
        else
            return "Something is wrong";
        // print the occupancy and farm lot status
        // check the farm lot status
    }

    /**
     * Changes the status of what is occupying the Lot
     *
     * @param occupancy Empty, Rock, or Crop
     */
    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    /**
     * This is the getter method for the occupancy attribute
     *
     * @return The given occupancy in String form
     */
    public String getOccupancy() {
        return this.occupancy;
    }

    /**
     * Returns the status of the crop whether it has withered or not
     *
     * @return true or false about its withered status
     */
    public boolean isItWithered() {
        return this.crop.getIsWithered();
    }

    /**
     * The setter function for the crop object attribute in the FarmLot
     *
     * @param name The name of the species of the crop
     */
    public void setCrop(String name) {
        this.crop.plantCrop(name);
    }

    /**
     * This function returns the name of the image
     * that the current lot should be displaying
     *
     * @return name of image
     */
    public String displayLot(){
        if(this.occupancy.equals("Empty")){
            if(isPlowed)
                return "images/plowed_soil.png";
            else
                return "images/!plowed_soil.png";
        }
        if(this.occupancy.equals("Crop")) {
            if(!this.crop.getIsWithered())
                return "images/Crops/" + this.crop.getSeedName() + this.crop.getAge() + ".png";
            else
                return "images/Crops/Withered.png";
        }
        else if(this.occupancy.equals("Rock"))
            return "images/Rocks.png";
        // if something went wrong, no image would be displayed
        return "";
    }
}