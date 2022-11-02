package MyFarm;

/**
 * The Player object is the object that interacts with the FarmLot to grow crops
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class Player {
    /**
     * Name of PLayer
     */
    private String name;
    /**
     * Amount of experience player has, used to calculate level
     */
    private double experience = 0;
    /**
     * The level player is at, used to check if player can "upgrade"
     */
    private int level = 1;
    /**
     * The rank of the player.
     */
    private String farmerType = "Farmer";
    /**
     * The amount of money the player has
     */
    private double objectCoin = 100;

    /**
     * Class Constructor.
     */
    public Player() {
        this.name = "Chad";
    }

    /**
     * Class Constructor specifying the name of the Player
     * 
     * @param name specified name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Gives player experience, also calculates if the player leveled up
     * 
     * @param expGained amount of experience gained
     */
    public void gainExperience(double expGained) {
        this.experience += expGained;
        this.level = (int) this.experience / 100;
    }

    /**
     * Checks if player can rank up, if player is able to, player will rank up
     */
    public void classUpgrade() {

        if (this.farmerType.equals("Farmer")) {
            if (this.level >= 5)
                if (this.objectCoin >= 200) {
                    System.out.println(this.name + ", you have successfully upgraded into a Registered Farmer!");
                    this.farmerType = "Registered Farmer";
                    this.objectCoin -= 200;
                } else
                    System.out
                            .println(this.name + ", you don't have enough coins to upgrade into a Registered Farmer.");
            else
                System.out.println(
                        this.name + ", you are currently too low of a level to upgrade into a Registered Farmer.");

        } else if (this.farmerType.equals("Registered Farmer")) {
            if (this.level >= 10) {
                if (this.objectCoin >= 300) {
                    System.out.println(this.name + ", you have successfully upgraded into a Distinguished Farmer!");
                    this.farmerType = "Distinguished Farmer";
                    this.objectCoin -= 300;
                } else
                    System.out.println(
                            this.name + ", you don't have enough coins to upgrade into a Distinguished Farmer.");
            } else
                System.out.println(
                        this.name + ", you are currently too low of a level to upgrade into a Distinguished Farmer.");

        } else if (this.farmerType.equals("Distinguished Farmer")) {
            if (this.level >= 15)
                if (this.objectCoin >= 400) {
                    System.out.println(this.name + ", you have successfully upgraded into a Legendary Farmer!");
                    this.farmerType = "Legendary Farmer";
                    this.objectCoin -= 400;
                } else
                    System.out.println(this.name + ", you don't have enough coins to upgrade into a Legendary Farmer.");
            else
                System.out.println(
                        this.name + ", you are currently too low of a level to upgrade into a Legendary Farmer.");
        } else if (this.farmerType.equals("Legendary Farmer"))
            System.out.println(this.name + ", you are already at the highest rank.");
        else
            System.out.println("Oh no! There seems to have been some kind of error.");
    }

    /**
     * Gives player money
     * 
     * @param moneyGained specified amount of money
     */
    public void earnCoin(double moneyGained) {
        this.objectCoin += moneyGained;
    }

    /**
     * Takes money from player
     * 
     * @param moneyUsed specified amount of money
     */
    public void spendCoin(double moneyUsed) {
        this.objectCoin -= moneyUsed;
    }

    /**
     * the getter function of the coin attribute
     * 
     * @return amount of money player has
     */
    public double getCoin() {
        return objectCoin;
    }

    /**
     * the getter function of the current sell bonus of the player based on player
     * rank
     * 
     * @return amount of sell bonus
     */
    public int getSellBonus() {
        if (this.farmerType.equals("Legendary Farmer"))
            return 4;
        else if (this.farmerType.equals("Distinguished Farmer"))
            return 2;
        else if (this.farmerType.equals("Registered Farmer"))
            return 1;
        else
            return 0;
    }

    /**
     * the getter function of the current bonus water bonus of the player based on
     * player rank
     * 
     * @return current bonus water bonus
     */
    public int getWaterBonusBonus() {
        if (this.farmerType.equals("Legendary Farmer"))
            return 2;
        else if (this.farmerType.equals("Distinguished Farmer"))
            return 1;
        else
            return 0;
    }

    /**
     * the getter function of the current bonus fertilizer bonus of the player based
     * on the player rank
     * 
     * @return current bonus Fertilizer bonus
     */
    public int getFertilizerBonusBonus() {
        if (this.farmerType.equals("Legendary Farmer"))
            return 1;
        else
            return 0;
    }

    /**
     * the getter function of the current seed discount of the player based on the
     * player rank
     * 
     * @return current seed discount
     */
    public int getSeedDiscount() {
        if (this.farmerType.equals("Legendary Farmer"))
            return -3;
        else if (this.farmerType.equals("Distinguished Farmer"))
            return -2;
        else if (this.farmerType.equals("Registered Farmer"))
            return -1;
        else
            return 0;

    }

    /**
     * Displays on terminal the crucial information of the player
     */
    public void displayPlayerStatus() {
        System.out.println(name);
        System.out.println("______________________________________");
        System.out.println("Farmer Rank: " + this.farmerType);
        System.out.println("Object Coins: " + this.objectCoin);
        System.out.println("Level: " + this.level + "\t" + (this.experience % 100) + "/100");
        System.out.println("The bonus water limit bonus is " + getWaterBonusBonus());
        System.out.println("The bonus fertilizer limit bonus is " + getFertilizerBonusBonus());
        System.out.println("The bonus seed discount is " + getSeedDiscount());
        System.out.println();
    }
}
