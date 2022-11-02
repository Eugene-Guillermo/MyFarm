package MyFarm;

/**
 * A lot in a field needs something to be put inside.
 * This object simulates a or agriculture.
 * A FarmLot objects at any given time holds exactly one
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class Crop {
    /**
     * cropType is the type of the . Example: Is it a root ?
     * seedName is the name of the species of . Example: Is it a Turnip or a Turnips
     */
    private String cropType, seedName;
    /**
     * waterLevel is the current amount of water inside the .
     * waterNeed is the amount of water the needs to live.
     * waterBonus is the amount of water the needs to produce extra.
     * fertilizerLevel is the current amount of fertilizer inside the .
     * fertilizerNeed is the amount of fertilizer the needs to live.
     * fertilizerBonus is the amount of fertilizer the needs to produce extra.
     * age is how old the is IN DAYS.
     * tillHarvest is how many days TILL HARVEST.
     * yieldLowerBound is the lower bound of how much produce is made.
     * yieldUpperBound is the upper bound of how much produce is made.
     * sellPrice is how much is the worth per produce.
     */
    private int waterLevel, waterNeed, waterBonus, fertilizerLevel, fertilizerNeed, fertilizerBonus, age, tillHarvest,
            yieldLowerBound, yieldUpperBound, sellPrice;
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

        // Assigns stats of the based on the name given.
        switch (seedName) {
            case "Turnip": {
                this.cropType = "Root ";
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
                // seed cost is 5
                // selling price is 6
                // experience is 5
                break;
            }

            case "Carrot": {
                this.cropType = "Root ";
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
                // seed cost is 10
                // selling price is 9
                // experience is 7.5
                break;
            }

            case "Potato": {
                this.cropType = "Root ";
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
                // seed cost is 20
                // selling price is 3
                // experience is 12.5
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
                // seed cost is 5
                // selling price is 5
                // experience is 2.5
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
                // seed cost is 10
                // selling price is 9
                // experience is 5
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
                // seed cost is 20
                // selling price is 19
                // experience is 7.5
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
                // seed cost is 100
                // selling price is 8
                // experience is 25
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
                // seed cost is 200
                // selling price is 5
                // experience is 25
                break;
            }

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
            }
        }
    }

    /**
     * Ages the by one day.
     * Also checks whether the is already harvestable or withered
     */
    public void age() {
        // ages by 1 day
        this.age++;
        // if the was harvestable the other day but was not harvested
        // the becomes withered
        if (this.canHarvest) {
            this.canHarvest = false;
            this.isWithered = true;
        }

        // if the is not yet harvestable
        else {
            // decrement the number of days till it is Harvestable
            this.tillHarvest--;
            // if the number of days till harvest is 0, it is harvestable
            if (this.tillHarvest == 0) {
                // not enough water, the withers.
                if (this.waterLevel < this.waterNeed)
                    this.isWithered = true;
                // not enough fertilizer, the withers.
                else if (this.fertilizerLevel < this.fertilizerNeed)
                    this.isWithered = true;
                // sufficient levels of water and fertilizer, the is harvestable.
                else
                    this.canHarvest = true;
            }
        }
    }

    public String getCropType() {
        return cropType;
    }

    public String getSeedName() {
        return seedName;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getWaterNeed() {
        return waterNeed;
    }

    public int getWaterBonus() {
        return waterBonus;
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public int getFertilizerNeed() {
        return fertilizerNeed;
    }

    public int getFertilizerBonus() {
        return fertilizerBonus;
    }

    public int getTillHarvest() {
        return tillHarvest;
    }

    public int getYieldLowerBound() {
        return yieldLowerBound;
    }

    public int getYieldUpperBound() {
        return yieldUpperBound;
    }

    public Boolean getCanHarvest() {
        return canHarvest;
    }

    public int getSellPrice() {
        return this.sellPrice;
    }

    public Boolean getIsWithered() {
        return isWithered;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

    /**
     * Displays the statistics of the in the terminal
     */
    public void displayInfo() {
        System.out.println("______________________________________");
        System.out.println("|              Info              |");
        System.out.println("--------------------------------------");
        System.out.println(" Name: " + this.seedName);
        System.out.println(" Type: " + this.cropType);
        System.out.println("Age: " + this.age);
        System.out.println("Current Water Level / Fertilizer Level: " + this.waterLevel + " / " + this.fertilizerLevel);
        System.out.println("Water Need | Bonus Limit: " + this.waterNeed + " | " + this.waterBonus);
        System.out.println("Fertilizer Need | Bonus Limit: " + this.fertilizerNeed + " | " + this.fertilizerBonus);
        System.out.println("Days Till Harvest: " + this.tillHarvest);
        System.out.println("Possible Number of Produce: " + this.yieldLowerBound + " to " + this.yieldUpperBound);
    }
}