package MyFarm;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This is the driver function for the game of MyFarm
 * Through File I/O, gets seed for rocks
 * Holds the model, frane, and controller of the game
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class FarmMain {

    public static void main(String[] args) {
        // contains 1's and 0's if a lot has a rock or not
        char[] rocks = new char[50];
        try {
            // Creates a reader using the FileReader
            BufferedReader input = new BufferedReader(new FileReader("src/MyFarm/Rocks.txt"));
            // Reads characters
            rocks = input.readLine().toCharArray();
            // Closes the reader
            input.close();
        }
        // if the seed cannot be read, auto sets all the lots to have rocks
        catch(Exception e) {
            for(int i=0;i<50;i++)
                rocks[i] = '1';
            e.getStackTrace();
        }

        // MVC
        FarmModel model = new FarmModel(rocks);
        FarmFrame frame = new FarmFrame(model);
        FarmController controller = new FarmController(frame, model);
    }
}
