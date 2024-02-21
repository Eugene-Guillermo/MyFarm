package MyFarm;

import javax.swing.*;
import java.awt.*;

/**
 * A JButton specializing in representing a single lot in the field
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class LotButton extends JButton{
    /**
     * The coordinates of the LotButton on the field
     */
    private int row, col;
    /**
     * The image being displayed on the LotButton
     */
    private ImageIcon img;
    /**
     * The model, contains the logic for image changing.
     */
    private FarmModel model;

    /**
     * Constructor for LotButton
     * @param i- row number
     * @param j - column number
     * @param model - Game logic
     */
    public LotButton(int i, int j, FarmModel model){
        this.row = i;
        this.col = j;
        this.model = model;
        this.img = new ImageIcon (getClass().getResource(model.getFarmLand()[row][col].displayLot()));
        this.setIcon(img);
        this.setPreferredSize(new Dimension(64, 64));
    }

    /**
     * Updates the image of the LotButton
     */
    public void refreshImg(){
        this.img = new ImageIcon (getClass().getResource(model.getFarmLand()[row][col].displayLot()));
        this.setIcon(this.img);
    }

    /**
     * row dash column, in string format
     * Example 2-3
     * @return the Coordinates of the lot
     */
    public String getCoordinate(){
        return this.row + "-" + this.col;
    }
}
