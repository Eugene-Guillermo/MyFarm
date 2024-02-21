package MyFarm;

/**
 * The Player object is the object that interacts with the FarmLot to grow crops
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.3
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
    private int level = 0;
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
     * Displays the success of the action through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the addNotif() function
     * that is found in the FarmFrame class
     *
     * @return in String form, the success or failure of classUpgrade action
     */
    public String classUpgrade() {

        if (this.farmerType.equals("Farmer")) {
            if (this.level >= 5)
                if (this.objectCoin >= 200) {
                    this.farmerType = "Registered Farmer";
                    this.objectCoin -= 200;
                    return "G: " + this.name + ", you have successfully upgraded into a Registered Farmer!";
                } else
                    return "N: " + this.name + ", you don't have enough coins to upgrade into a Registered Farmer.";
            else
                return "N: " + this.name + ", you are currently too low of a level to upgrade into a Registered Farmer.";

        } else if (this.farmerType.equals("Registered Farmer")) {
            if (this.level >= 10) {
                if (this.objectCoin >= 300) {
                    this.farmerType = "Distinguished Farmer";
                    this.objectCoin -= 300;
                    return "G: " + this.name + ", you have successfully upgraded into a Distinguished Farmer!";
                } else
                    return "N: " + this.name + ", you don't have enough coins to upgrade into a Distinguished Farmer.";
            } else
                return "N: " + this.name + ", you are currently too low of a level to upgrade into a Distinguished Farmer.";

        } else if (this.farmerType.equals("Distinguished Farmer")) {
            if (this.level >= 15)
                if (this.objectCoin >= 400) {
                    this.farmerType = "Legendary Farmer";
                    this.objectCoin -= 400;
                    return "G: " + this.name + ", you have successfully upgraded into a Legendary Farmer!";
                } else
                    return "N: " + this.name + ", you don't have enough coins to upgrade into a Legendary Farmer.";
            else
                return "N: " + this.name + ", you are currently too low of a level to upgrade into a Legendary Farmer.";
        } else if (this.farmerType.equals("Legendary Farmer"))
            return "W: " + this.name + ", you are already at the highest rank.";
        else
            return "C: Oh no! There seems to have been some kind of error.";
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
     * The getter function of the coin attribute
     *
     * @return amount of money player has
     */
    public double getCoin() {
        return objectCoin;
    }

    /**
     * The getter function of the current sell bonus of the player based on player rank
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
     * The getter function of the current bonus water bonus of the player based on player rank
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
     * The getter function of the current bonus fertilizer bonus of the player
     * based on the player rank
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
     * The getter function of the current seed discount of the player
     * based on the player rank
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
     * The setter function of the Player's name
     *
     * @param name Player's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter function of the Player's name
     *
     * @return the Player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Displays the crucial information of the player through a String
     * The String also includes HTML styling
     * String returned is intended to be received by the setText() function
     * for the profileLabelRight JLabel that is found in the FarmFrame class
     *
     * @return in String form, the information of this player
     */
    public String displayPlayerStatus() {

        String playerInfo = "<table style=\"width:100%; border: 1px solid;\">";
        playerInfo = playerInfo.concat("<tr><th style=\"text-align:right\">Player Info</th></tr>");
        playerInfo = playerInfo.concat("<tr><th style=\"border: 1px solid\">Farmer Rank:</th><td style=\"border: 1px solid\">" + this.farmerType + "</td></tr>");
        playerInfo = playerInfo.concat("<tr><th style=\"border: 1px solid\">Object Coins:</th><td style=\"border: 1px solid\">" + this.objectCoin + "</td></tr>");
        playerInfo = playerInfo.concat("<tr><th style=\"border: 1px solid\">Level:</th><td style=\"border: 1px solid\">" + this.level + "     | " + (this.experience % 100) + "/100" + "</td></tr>");
        playerInfo = playerInfo.concat("<tr><th style=\"border: 1px solid\">The bonus water limit bonus is </th><td style=\"border: 1px solid\">" + this.getWaterBonusBonus() + "</td></tr>");
        playerInfo = playerInfo.concat("<tr><th style=\"border: 1px solid\">The bonus fertilizer limit bonus is</th><td style=\"border: 1px solid\">" + this.getFertilizerBonusBonus() + "</td></tr>");
        playerInfo = playerInfo.concat("<tr><th style=\"border: 1px solid\">The bonus seed discount is</th><td style=\"border: 1px solid\">" + this.getSeedDiscount() + "</td></tr>");
        return playerInfo;
    }
}
