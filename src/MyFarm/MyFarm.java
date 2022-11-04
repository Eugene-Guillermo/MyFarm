package MyFarm;

import java.util.Scanner;

/**
 * This is the driver function for the game of MyFarm
 * Currently there is only one FarmLot in this Farm
 * 
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.2
 */
public class MyFarm {
    public static void main(String[] args) {
        // The day of the game starting at 0
        int day = 0;
        // It is not game over YET
        boolean gameOver = false;
        // The Player of the game
        Player player;
        // The single field or Farmlot
        FarmLot farmLand = new FarmLot();
        // Scanner used to get input
        Scanner sc = new Scanner(System.in);

        // The player's input/choice
        char choice;

        // Creating Player
        System.out.println("What is the name of your Farmer?");
        String name = sc.nextLine();

        player = new Player(name);
        // while the game is not over
        while (!gameOver) {
            // every end of an action, player will get this message
            System.out.println("_______________________________________________");
            System.out.println("Good morning, " + name + "! " + "Today is day " + day + ".");
            System.out.println("__________________________");
            player.displayPlayerStatus();
            System.out.println("What do you want to do?");
            System.out.println("[M] Mine The Rock (Pickaxe)");
            System.out.println("[P] Plow the Lot (Plow)");
            System.out.println("[B] Buy seed");
            System.out.println("[W] Water the crop (Watering Can)");
            System.out.println("[F] Fertilize the crop (Fertilizer)");
            System.out.println("[H] Harvest the crop");
            System.out.println("[D] Dig the withered crop (Shovel)");
            System.out.println("[U] Upgrade farmer rank");
            System.out.println("[I] Inform about Farm Lot and Crop Status");
            System.out.println("[E] End the Day");
            System.out.println("[X] Exit the Game");

            choice = sc.next().charAt(0); // get the first char

            switch (choice) {
                // Mine
                case 'M':
                case 'm': {
                    farmLand.mine(player);
                    break;
                }

                // Plow
                case 'P':
                case 'p': {
                    farmLand.plow(player);
                    break;
                }

                case 'B':
                case 'b': {
                    farmLand.buySeed(player);
                    break;
                }

                // Water
                case 'W':
                case 'w': {
                    farmLand.waterCrop(player);
                    break;
                }

                // Fertilize
                case 'F':
                case 'f': {
                    farmLand.fertilizeCrop(player);
                    break;
                }

                // Harvest
                case 'H':
                case 'h': {
                    farmLand.harvestCrop(player);
                    break;
                }

                // Dig
                case 'D':
                case 'd': {
                    farmLand.dig(player);
                    break;
                }

                // Upgrade
                case 'U':
                case 'u': {
                    player.classUpgrade();
                    break;
                }

                // Inform
                case 'I':
                case 'i': {
                    farmLand.inform();
                    farmLand.display();
                    break;
                }

                // End Day
                case 'E':
                case 'e': {
                    day++; // day passes
                    farmLand.ageLot();
                    System.out.println("This is the start of DAY " + day);
                    farmLand.checkStatus();
                    break;
                }

                // Exit Game
                case 'X':
                case 'x': {
                    System.out.println("Thank you for playing the game.");
                    gameOver = true; // end game
                    break;
                }

                default: {
                    System.out.println("Could you please try again?");
                }
            }
            // prevents softlocks and game over checks
            if (player.getCoin() < 5 + player.getSeedDiscount()) {
                System.out.println("GAME OVER :(");
                System.out.println("You unfortunately do not possess enough coins to buy a turnip seed.");
                gameOver = true; // end game
            } if (farmLand.getOccupancy().equals("Rock") && player.getCoin() < 50) {
                System.out.println("GAME OVER :(");
                System.out.println("You unfortunately do not possess enough coins to mine the rock.");
                gameOver = true; // end game
            } if (farmLand.getOccupancy().equals("Crop") && farmLand.isItWithered() && player.getCoin() < 7) {
                System.out.println("GAME OVER :(");
                System.out.println("You unfortunately do not have enough coins to dig out the withered crop.");
                gameOver = true; // end game
            }
        }
        sc.close();
    }
}
