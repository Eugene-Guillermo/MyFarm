package MyFarm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Controller of the MyFarm game.
 * The tool that interacts with the user
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class FarmController {
    /**
     * The frame, which holds the view and all the data of how the view should look like
     */
    private FarmFrame frame;
    /**
     * The model, which holds the logic and all the data of the current game state
     */
    private FarmModel model;

    /**
     * The constructor that specifies the logic and view the game will follow
     * @param frame specified view
     * @param model specified logic and game state
     */
    FarmController(FarmFrame frame, FarmModel model){
        this.frame = frame;
        this.model = model;
        frame.refresh();

        // adds the TooListener and SeedListener class as actionListener for their respective buttons
        for (int i=0;i<8;i++) {
            frame.addToolListener(new ToolListener(i), i);
            frame.addSeedListener(new SeedListener(i), i);
        }
        // adds the LotListener class as actionListener for the LotButtons
        for(int i=0;i<5;i++)
            for(int j=0;j<10;j++){
                frame.addLotListener(new LotListener(i, j), i, j);
            }
    }

    /**
     * The SeedListener is in charge of waiting for the input of the seed button
     * When seed button is pressed, will perform custom action
     * Each SeedListener is identified through its number
     *
     * @author Eugene Guillermo
     * @author Johann Uytanlet
     * @version 1.0
     */
    private class SeedListener implements ActionListener {
        /**
         * SeedListener number/identifier
         */
        int seed;

        /**
         * The constructor of SeedListener
         *
         * @param i SeedListener number
         */
        public SeedListener(int i) {
            this.seed = i;
        }

        /**
         * Attempt to plant the seed buttons species into currently selected lot
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {
            int row, col;
            row = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(0)));
            col = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(2)));

            switch (seed) {
                // Turnip
                case 0: {
                    frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Turnip"));
                    frame.refresh();
                    break;
                }
                // Carrot
                case 1: {
                    frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Carrot"));
                    frame.refresh();
                    break;
                }
                // Potato
                case 2: {
                    frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Potato"));
                    frame.refresh();
                    break;
                }
                // Rose
                case 3: {
                    frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Rose"));
                    frame.refresh();
                    break;
                }
                // Turnips
                case 4: {
                    frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Turnips"));
                    frame.refresh();
                    break;
                }
                // Sunflower
                case 5: {
                    frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Sunflower"));
                    frame.refresh();
                    break;
                }
                // Mango
                case 6: {
                    if(row - 1 >= 0 && col - 1 >= 0 && row+1 <= 4 && col+1 <=9)
                    {
                        if (model.getFarmLand()[row+1][col+1].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row+1][col-1].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row+1][col].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row][col+1].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row][col].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row][col-1].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row-1][col+1].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row-1][col].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row-1][col-1].getOccupancy().equals("Empty"))
                            frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Mango"));
                        else
                            frame.addNotif("W: Cannot plant tree due to surrounding obstacles");
                    }
                    else
                        frame.addNotif("W: Cannot plant a tree at the edge/corner of the farm land.");
                    frame.refresh();
                    break;
                }
                // Apple
                case 7: {
                    if(row - 1 >= 0 && col - 1 >= 0 && row+1 <= 4 && col+1 <=9)
                    {
                        if (model.getFarmLand()[row+1][col+1].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row+1][col-1].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row+1][col].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row][col+1].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row][col].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row][col-1].getOccupancy().equals("Empty") &&
                                model.getFarmLand()[row-1][col+1].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row-1][col].getOccupancy().equals("Empty")&&
                                model.getFarmLand()[row-1][col-1].getOccupancy().equals("Empty"))
                            frame.addNotif(model.getFarmLand()[row][col].buySeed(model.getPlayer(), "Apple"));
                        else
                            frame.addNotif("W: Cannot plant tree due to surrounding obstacles");
                    }
                    else
                        frame.addNotif("W: Cannot plant a tree at the edge/corner of the farm land.");
                    frame.refresh();
                    break;
                }
            }
        }
    }
    /**
     * The ToolListener is in charge of waiting for the input of the tool button
     * When tool button is pressed, will perform custom action
     * Each ToolListener is identified through its number
     *
     * @author Eugene Guillermo
     * @author Johann Uytanlet
     * @version 1.0
     */
    private class ToolListener implements ActionListener {
        /**
         * ToolListener number/identifier
         */
        int tool;

        /**
         * The constructor of ToolListener
         * @param i ToolListener number/identifier
         */
        public ToolListener(int i){
            this.tool = i;
        }


        /**
         * Attempt to do action of the tool
         * If the tool can be directed to a lot, then onto currently selected lot
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {
            int row, col;
            row = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(0)));
            col = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(2)));

            switch (tool) {
                // plow
                case 0:{
                    frame.addNotif(model.getFarmLand()[row][col].plow(model.getPlayer()));
                    frame.refresh();
                    break;
                }
                // Watering
                case 1:{
                    frame.addNotif(model.getFarmLand()[row][col].waterCrop(model.getPlayer()));
                    frame.refresh();
                    break;
                }
                // Fertilizer
                case 2:{
                    frame.addNotif(model.getFarmLand()[row][col].fertilizeCrop(model.getPlayer()));
                    frame.refresh();
                    break;
                }
                // Pickaxe
                case 3:{
                    frame.addNotif(model.getFarmLand()[row][col].mine(model.getPlayer()));
                    frame.refresh();
                    break;
                }
                //Shovel
                case 4:{
                    frame.addNotif(model.getFarmLand()[row][col].dig(model.getPlayer()));
                    frame.refresh();
                    break;
                }
                // Sickle
                case 5:{
                    frame.addNotif(model.getFarmLand()[row][col].harvestCrop(model.getPlayer()));
                    frame.refresh();
                    break;
                }
                //UpgradeRank
                case 6:{
                    frame.addNotif(model.getPlayer().classUpgrade());
                    frame.refresh();
                    break;
                }
                //EndDay
                case 7:{
                    // pop out message will appear at the end of each day
                    int messageType = JOptionPane.WARNING_MESSAGE;
                    String endDayWarning = model.endDayWarning();
                    if(endDayWarning.equals("There are no immediate concerns"))
                        messageType = JOptionPane.PLAIN_MESSAGE;
                    int input = JOptionPane.showConfirmDialog(frame, endDayWarning, null, JOptionPane.YES_NO_OPTION, messageType);
                    if(input == 0){
                        frame.addNotif(model.endDay());
                        frame.refresh();
                    }
                    break;
                }
            }
        }
    }

    /**
     * The LotListener is in charge of waiting for the input of the LotButton
     * When LotButton is pressed, will perform custom action
     * Each LotListener is identified through its Lot's coordinates
     */
    private class LotListener implements ActionListener{
        /**
         * Lot's coordinates
         */
        private int row, col;

        /**
         * The constructor of LotListener specifying its coordinates
         *
         * @param i row number
         * @param j column number
         */
        public LotListener(int i, int j){
            this.row = i;
            this.col = j;
        }

        /**
         * When LotButton is pressed, sets currently selected LotButton to the pressed one
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {
            model.setCurrentSelected(this.row + "-" + this.col);
            frame.refresh();
        }
    }
}
