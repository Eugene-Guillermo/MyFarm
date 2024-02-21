package MyFarm;

/**
 * The model of the MyFarm game
 * The logic to process what is going on in the game
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class FarmModel {
    /**
     * The current day of the game, starts at 0
     */
    private int day = 0;
    /**
     * The Player object containing and maintaining Player data
     */
    private Player player = new Player();
    /**
     * The 2d array of Lots, the "field"
     */
    private FarmLot farmLand[][] = new FarmLot[5][10];
    /**
     * currently selected lot
     */
    private String currentSelected = "0-0";

    /**
     * The Constructor for FarmModel
     *
     * @param rocks seed for rocks
     */
   public FarmModel(char[] rocks){
       int rockCounter = 0;
       for(int i=0;i<5;i++)
           for (int j=0;j<10;j++){
               farmLand[i][j] = new FarmLot();
               if(rocks[rockCounter] == '1')
                   farmLand[i][j].setOccupancy("Rock");
               rockCounter++;
           }
   }

    /**
     * The day ends.
     * All lots are aged and the day is increased by one
     *
     * @return success message of ending day
     */
    public String endDay(){
        for(int i=0;i<5;i++)
            for (int j=0;j<10;j++)
                farmLand[i][j].ageLot();
        this.day++;
        return "G: Day has passed.<br>It is now day " + this.day;
    }

    /**
     * Checks if there is no meaningful actions left that the player can do
     *
     * @return gameover message with respective reason why or NONE
     */
    public String gameOverMsg(){
        if(player.getCoin() < 50){
            Boolean gameOver = true;
            for(int i=0;i<5;i++){
                for (int j=0;j<10;j++){
                    if(!farmLand[i][j].getOccupancy().equals("Rock")) {
                        gameOver = false;
                        // breaks
                        i = 5;
                        j = 10;
                    }
                }
            }
            if(gameOver)
                return "Gameover! No meaningful action can be taken<br>All lots have rocks on them<br> Player does not enough money to mine";
        }
        if(player.getCoin() < 5 + player.getSeedDiscount()){
            Boolean gameOver = true;
            for(int i=0;i<5;i++){
                for (int j=0;j<10;j++){
                    if(!farmLand[i][j].getOccupancy().equals("Rock") &&
                            (!(farmLand[i][j].getOccupancy().equals("Crop") && farmLand[i][j].isItWithered())) &&
                            (!farmLand[i][j].getOccupancy().equals("Empty"))) {
                        gameOver = false;
                        // breaks
                        i = 5;
                        j = 10;
                    }
                }
            }

            if(gameOver)
                return "Gameover! No meaningful action can be taken<br> All lots currently do not have any growing crops to harvest.<br>Player does not have enough money<br> to plant any new crops on the available lots";
        }
        if(player.getCoin() < 7){
            Boolean gameOver = true;
            for(int i=0;i<5;i++){
                for (int j=0;j<10;j++){
                    if(!farmLand[i][j].getOccupancy().equals("Rock") && (!(farmLand[i][j].getOccupancy().equals("Crop") && farmLand[i][j].isItWithered())) && !farmLand[i][j].getOccupancy().equals("Empty")) {
                        gameOver = false;
                        // breaks
                        i = 5;
                        j = 10;
                    }
                }
            }
            if(gameOver)
                return "Gameover! No meaningful action can be taken<br>All lots have rocks, withered crops, or nothing on them<br> Player does not enough money to mine or dig";
        }
        return "NONE";
    }

    /**
     * Will inform the player the number of crops that will wither the next day
     * if not watered or fertilized
     * also informs how many crops are harvestable
     *
     * @return The warming of ending the day
     */
    public String endDayWarning(){
        int water, fertilizer, harvestable;
        water = fertilizer = harvestable = 0;
        for(int i=0;i<5;i++)
            for (int j=0;j<10;j++){
                switch (farmLand[i][j].checkStatus()){
                    case 'W':
                        water++;
                        break;
                    case 'F':
                        fertilizer++;
                        break;
                    case 'H':
                        harvestable++;
                        break;
                    default:
                }
            }
        if(water == 0 && fertilizer == 0 && harvestable == 0)
            return "There are no immediate concerns";
        else
            return String.format("There are %d crops that need water\nThere are %d crops that need fertilizer\nThere are %d crops that need to be harvest\nIf no actions are taken, they will wither on the next day.\nAre you sure you want to end day?", water, fertilizer, harvestable);
    }

    /**
     * Returns the currently selected lot coordinates through string format
     *
     * @return currently selected lot coordinates
     */
    public String getCurrentSelected() {
        return currentSelected;
    }

    /**
     * Sets the currently selected lot coordinates through string format
     * @param currentSelected currently selected lot coordinates
     */
    public void setCurrentSelected(String currentSelected) {
        this.currentSelected = currentSelected;
    }

    /**
     * Returns day "what" in the MyFarm game
     *
     * @return the current day
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the Player's stats for actions
     *
     * @return The Player object, along with its info
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the whole field of lots
     *
     * @return The whole field of lots, all 50, through 2d Array of FarmLots
     */
    public FarmLot[][] getFarmLand() {
        return this.farmLand;
    }
}
