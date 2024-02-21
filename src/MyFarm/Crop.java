package MyFarm;

/**
 * There must something inside a lot in a field.
 * This object simulates a crop.
 * A FarmLot objects at any given time holds exactly one
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.3
 */
public class Crop {
    /**
     * cropType is the type of the crop. Example: Is it a root crop?
     * seedName is the name of the species of crop. Example: Is it a Turnip or a Turnips
     */
    private String cropType, seedName;
    /**
     * waterLevel is the current amount of water inside the crop.
     * waterNeed is the amount of water the crop needs to live.
     * waterBonus is the amount of water the crop needs to produce extra.
     * fertilizerLevel is the current amount of fertilizer inside the crop.
     * fertilizerNeed is the amount of fertilizer the crop needs to live.
     * fertilizerBonus is the amount of fertilizer the crop needs to produce extra.
     * age is how old the crop is IN DAYS.
     * tillHarvest is how many days TILL HARVEST.
     * yieldLowerBound is the lower bound of how much produce is made.
     * yieldUpperBound is the upper bound of how much produce is made.
     * sellPrice is how much is the worth per produce.
     */
    private int waterLevel, waterNeed, waterBonus, fertilizerLevel, fertilizerNeed, fertilizerBonus, age, tillHarvest,
            yieldLowerBound, yieldUpperBound, sellPrice, seedCost;
    /**
     * experience is the amount of experience that the crop gives to the Player when harvested
     */
    private double experience;
    /**
     * canHarvest answers if the crop CAN be HARVESTED?
     * isWithered answers if the crop IS WITHERED?
     */
    private Boolean canHarvest, isWithered;

    /**
     * Class Constructor.
     */
    public Crop() {
        this.cropType = "Nothing";
        this.seedName = "Nothing";
        this.waterLevel = 0;
        this.waterNeed = 0;
        this.waterBonus = 0;
        this.fertilizerLevel = 0;
        this.fertilizerNeed = 0;
        this.fertilizerBonus = 0;
        this.age = 0;
        this.tillHarvest = 0;
        this.canHarvest = false;
        this.isWithered = false;
        this.yieldLowerBound = 0;
        this.yieldUpperBound = 0;
        this.seedCost = 0;
        this.experience = 0;
    }

    /**
     * Class Constructor specifying the species of the .
     *
     * @param Name specified species
     */
    public Crop(String Name) {
        plantCrop(Name);
    }

    /**
     * Changes the crop to the specified species
     *
     * @param Name specified species
     */
    public void plantCrop(String Name) {
        this.seedName = Name;
        // Assigns stats of the crop based on the name given.
        switch (seedName) {
            case "Turnip": {
                this.cropType = "Root";
                this.tillHarvest = 2;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 1;
                this.waterBonus = 2;
                this.fertilizerNeed = 0;
                this.fertilizerBonus = 1;
                this.yieldLowerBound = 1;
                this.yieldUpperBound = 2;
                this.sellPrice = 6;
                this.seedCost = 5;
                this.experience = 5;
                break;
            }

            case "Carrot": {
                this.cropType = "Root";
                this.tillHarvest = 3;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 1;
                this.waterBonus = 2;
                this.fertilizerNeed = 0;
                this.fertilizerBonus = 1;
                this.yieldLowerBound = 1;
                this.yieldUpperBound = 2;
                this.sellPrice = 9;
                this.seedCost = 10;
                this.experience = 7.5;
                break;
            }

            case "Potato": {
                this.cropType = "Root";
                this.tillHarvest = 5;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 3;
                this.waterBonus = 4;
                this.fertilizerNeed = 1;
                this.fertilizerBonus = 2;
                this.yieldLowerBound = 1;
                this.yieldUpperBound = 10;
                this.sellPrice = 3;
                this.seedCost = 20;
                this.experience = 12.5;
                break;
            }

            case "Rose": {
                this.cropType = "Flower";
                this.tillHarvest = 1;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 1;
                this.waterBonus = 2;
                this.fertilizerNeed = 0;
                this.fertilizerBonus = 1;
                this.yieldLowerBound = 1;
                this.yieldUpperBound = 1;
                this.sellPrice = 5;
                this.seedCost = 5;
                this.experience = 2.5;
                break;
            }

            case "Turnips": {
                this.cropType = "Flower";
                this.tillHarvest = 2;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 2;
                this.waterBonus = 3;
                this.fertilizerNeed = 0;
                this.fertilizerBonus = 1;
                this.yieldLowerBound = 1;
                this.yieldUpperBound = 1;
                this.sellPrice = 9;
                this.seedCost = 10;
                this.experience = 5;
                break;
            }

            case "Sunflower": {
                this.cropType = "Flower";
                this.tillHarvest = 3;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 2;
                this.waterBonus = 3;
                this.fertilizerNeed = 1;
                this.fertilizerBonus = 2;
                this.yieldLowerBound = 1;
                this.yieldUpperBound = 1;
                this.sellPrice = 19;
                this.seedCost = 20;
                this.experience = 7.5;
                break;
            }

            case "Mango": {
                this.cropType = "Fruit Tree";
                this.tillHarvest = 10;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 7;
                this.waterBonus = 7;
                this.fertilizerNeed = 4;
                this.fertilizerBonus = 4;
                this.yieldLowerBound = 5;
                this.yieldUpperBound = 15;
                this.sellPrice = 8;
                this.seedCost = 100;
                this.experience = 25;
                break;
            }

            case "Apple": {
                this.cropType = "Fruit Tree";
                this.tillHarvest = 10;
                this.waterLevel = 0;
                this.fertilizerLevel = 0;
                this.waterNeed = 7;
                this.waterBonus = 7;
                this.fertilizerNeed = 5;
                this.fertilizerBonus = 5;
                this.yieldLowerBound = 10;
                this.yieldUpperBound = 15;
                this.sellPrice = 5;
                this.seedCost = 200;
                this.experience = 25;
                break;
            }
            // set the crop to Nothing
            default: {
                this.cropType = "Nothing";
                this.seedName = "Nothing";
                this.waterLevel = 0;
                this.waterNeed = 0;
                this.waterBonus = 0;
                this.fertilizerLevel = 0;
                this.fertilizerNeed = 0;
                this.fertilizerBonus = 0;
                this.age = 0;
                this.tillHarvest = 0;
                this.canHarvest = false;
                this.isWithered = false;
                this.yieldLowerBound = 0;
                this.yieldUpperBound = 0;
                this.seedCost = 0;
                this.experience = 0;
            }
        }
    }

    /**
     * Ages the crop the by one day.
     * Also checks whether the crop is already harvestable or withered
     */
    public void age() {
        // ages by 1 day
        this.age++;
        // if the crop was harvestable the other day but was not harvested
        // the becomes withered
        if (this.canHarvest) {
            this.canHarvest = false;
            this.isWithered = true;
        }

        // if the crop is not yet harvestable
        else {
            // decrement the number of days till it is Harvestable
            this.tillHarvest--;
            // if the number of days till harvest is 0, it is harvestable
            if (this.tillHarvest == 0) {
                // not enough water, the crop withers.
                if (this.waterLevel < this.waterNeed)
                    this.isWithered = true;
                    // not enough fertilizer, the crop withers.
                else if (this.fertilizerLevel < this.fertilizerNeed)
                    this.isWithered = true;
                    // sufficient levels of water and fertilizer, the crop is harvestable.
                else
                    this.canHarvest = true;
            }
        }
    }

    /**
     * This is the getter method for the amount of experience the crop gives
     *
     * @return amount of experience
     */
    public double getExperience() {
        return experience;
    }

    /**
     * This is the getter method for the cost to buy the seeds of this species of crops
     *
     * @return cost of seed
     */
    public int getSeedCost() {
        return seedCost;
    }

    /**
     * This is the getter method for the crop type
     *
     * @return The type of crop
     */
    public String getCropType() {
        return cropType;
    }

    /**
     * This is the getter method for the seed name
     *
     * @return The name of the crop
     */
    public String getSeedName() {
        return seedName;
    }

    /**
     * This is the getter method for the water level
     *
     * @return The amount of times the crop was watered
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * This is the getter method for the water need
     *
     * @return The amount of times the crop was watered needed for it to survive
     */
    public int getWaterNeed() {
        return waterNeed;
    }

    /**
     * This is the getter method for the water bonus limit
     *
     * @return The water bonus limit of the crop
     */
    public int getWaterBonus() {
        return waterBonus;
    }

    /**
     * This is the getter method for the fertilizer level
     *
     * @return The amount of times the crop has been fertilized
     */
    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    /**
     * This is the getter method for the fertilizer need
     *
     * @return The amount of fertilizer needed for the crop to survive
     */
    public int getFertilizerNeed() {
        return fertilizerNeed;
    }

    /**
     * This is the getter method for the fertilizer bonus limit
     *
     * @return The fertilizer bonus limit of the crop
     */
    public int getFertilizerBonus() {
        return fertilizerBonus;
    }

    /**
     * This is the getter method for the days till harvest
     *
     * @return The amount of days before the crop is ready for harvest
     */
    public int getTillHarvest() {
        return tillHarvest;
    }

    /**
     * This is the getter method for getting the yield lower bound
     *
     * @return The minimum amount of yield the crop can yield
     */
    public int getYieldLowerBound() {
        return yieldLowerBound;
    }

    /**
     * This is the getter method for getting the yield upper bound
     *
     * @return The maximum amount of yield the crop can yield
     */
    public int getYieldUpperBound() {
        return yieldUpperBound;
    }

    /**
     * This is the getter method for CanHarvest Boolean
     *
     * @return Whether the crop is ready for harvest or not
     */
    public Boolean getCanHarvest() {
        return canHarvest;
    }

    /**
     * This is the getter method for the selling price of the crop
     *
     * @return The amount of coins the crop is sold for
     */
    public int getSellPrice() {
        return this.sellPrice;
    }

    /**
     * This is the getter method for IsWithered boolean
     *
     * @return Whether the crop is withered or not
     */
    public Boolean getIsWithered() {
        return isWithered;
    }

    /**
     * This is the setter method for water level
     *
     * @param waterLevel The amount of times the crop has been watered
     */
    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    /**
     * This is the setter method for fertilizer level
     *
     * @param fertilizerLevel The amount of times the crop has been fertilized
     */
    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

    /**
     * This is the getter method for the age of the crop
     *
     * @return how old the crop is
     */
    public int getAge() {
        return age;
    }

    /**
     * Displays the statistics of the crop with HTML styling included through a String
     * String returned is intended to be called by the inform() function
     * that is found in the FarmLot class
     * The String also includes HTML styling
     *
     * @return in String form, the information of this crop
     */
    public String cropInfo() {

        String cropInfo = "<table style=\"width:100%; border: 1px solid;\">";
        cropInfo = cropInfo.concat("<tr><th style=\"text-align:right\">Crop</th></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Name</th><td style=\"border: 1px solid\">" + this.seedName + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Type</th><td style=\"border: 1px solid\">" + this.cropType + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Age</th><td style=\"border: 1px solid\">" + this.age + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Water Level</th><td style=\"border: 1px solid\">" + this.waterLevel + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Water Need</th><td style=\"border: 1px solid\">" + this.waterNeed + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Water Bonus Limit</th><td style=\"border: 1px solid\">" + this.waterBonus + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Fertilizer Level</th><td style=\"border: 1px solid\">" + this.fertilizerLevel + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Fertilizer Need</th><td style=\"border: 1px solid\">" + this.fertilizerNeed + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Fertilizer Bonus Limit</th><td style=\"border: 1px solid\">" + this.fertilizerBonus + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Days Till Harvest</th><td style=\"border: 1px solid\">" + this.tillHarvest + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Withered Status</th><td style=\"border: 1px solid\">" + this.getIsWithered() + "</td></tr>");
        cropInfo = cropInfo.concat("<tr><th style=\"border: 1px solid\">Possible Yield</th><td style=\"border: 1px solid\">" + this.yieldLowerBound + "-" + yieldUpperBound + "</td></tr></table></html>");
        return cropInfo;
    }
}
