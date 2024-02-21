package MyFarm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The Frame of the MyFarm game.
 * The view of the user.
 *
 * @author Eugene Guillermo
 * @author Johann Uytanlet
 * @version 1.0
 */
public class FarmFrame extends JFrame {
    /**
     * The main panels of the frame
     */
    private JPanel panelNorth, panelEast, panelWest, panelSouth, panelCenter;
    /**
     * The inner panels of the main panels of the frame
     */
    private JPanel profilePanel, profileLeft, profileRight, toolbarPanel, seedsPanel, farmLotJPanel, notifsPanel;
    /**
     * The Labels of the frame
     */
    private JLabel titleLabel, dayLabel, selectedLabel, nameLabel, profileLabelRight;
    /**
     * The scroll pane for the notification panel
     */
    private JScrollPane notifsScroll;
    /**
     * The 2d array that holds the buttons that represent the 50 lots
     */
    private LotButton[][] Lot;
    /**
     * The array of buttons used for actions
     */
    private JButton[] tools, seeds;
    /**
     * The model, which holds the logic and all the data of the current game state
     */
    private FarmModel model;
    /*
        Colors
        3D3216 Dark Brown
        936E3C Brown
        EFCAA0 Beige
        D2D97A Light Green
        5A640E - Dark Green
    */

    /**
     * The constructor for the FarmFrame class
     *
     * @param model The given model
     */
    public FarmFrame(FarmModel model){
        super("MyFarm");

        super.setSize(1500, 800);
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);
        ImageIcon gameLogo = new ImageIcon (getClass().getResource("images/img.png"));
        super.setIconImage(gameLogo.getImage());
        this.model = model;
        //starting pop up asking for the player's name
        model.getPlayer().setName(JOptionPane.showInputDialog("What is your name?", null));
        // setup each of the main panels one by one
        initializeNorth();
        initializeCenter();
        initializeSouth();
        initializeEast();
        initializeWest();

        super.setVisible(true);
    }

    /**
     * Sets up the North Panel
     */
    private void initializeNorth(){
        panelNorth = new JPanel(new BorderLayout());
        
        titleLabel = new JLabel();
        titleLabel.setText("MY FARM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.white);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        dayLabel = new JLabel();
        dayLabel.setText("<html><p style = \"padding: 5px 70px; font-size:18px; text-align:center; border-width:1px; border-style:solid; border-color:#000000; background-color: #EFCAA0\">Day " + model.getDay() + "</p></html>");
        dayLabel.setHorizontalAlignment(JLabel.CENTER);
        dayLabel.setBorder(new EmptyBorder(5, 5, 5, 10));

        panelNorth.add(titleLabel, BorderLayout.NORTH);
        panelNorth.add(dayLabel, BorderLayout.CENTER);
        panelNorth.setBackground(Color.decode("#3D3216"));
        this.add(panelNorth, BorderLayout.NORTH);
    }

    /**
     * Sets up the Center Panel
     */
    private void initializeCenter(){
        panelCenter = new JPanel();
        farmLotJPanel = new JPanel(new GridLayout(5, 10, 7, 7));
        Lot = new LotButton[5][10];
        // adding all 50 buttons into the Lot panel
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                Lot[i][j] = new LotButton(i, j, this.model);
                farmLotJPanel.add(Lot[i][j], i, j);
            }
        }
        farmLotJPanel.setBackground(Color.decode("#885C3C"));
        farmLotJPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        panelCenter.add(farmLotJPanel, BorderLayout.CENTER);
        panelCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(panelCenter, BorderLayout.CENTER);
    }

    /**
     * adds an ActionListener for a certain LotButton
     * with specified coordinates
     *
     * @param actionListener the class that implements ActionListener
     * @param i specified row number
     * @param j specified column number
     */
    public void addLotListener(ActionListener actionListener, int i, int j) {
        Lot[i][j].addActionListener(actionListener);
    }

    /**
     * Sets up South Panel
     */
    private void initializeSouth(){
        panelSouth = new JPanel(new FlowLayout());
        profilePanel = new JPanel(new FlowLayout());
        profileLeft = new JPanel(new BorderLayout());
        profileRight = new JPanel();
        toolbarPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        toolbarPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        toolbarPanel.setBackground(Color.decode("#D2D97A"));
        seedsPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        seedsPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        seedsPanel.setBackground(Color.decode("#D2D97A"));
        //adds tool buttons with their respective images
        tools = new JButton[8];
        String imgName[] = {"Plow", "WateringCan", "Fertilizer", "Pickaxe", "Shovel", "Sickle", "UpgradeRank", "EndDay"};
        for(int i=0;i<8;i++){
            tools[i] = new JButton();
            tools[i].setIcon(new ImageIcon(getClass().getResource("images/" + imgName[i] + ".png")));
            toolbarPanel.add(tools[i]);
        }
        //adds seed buttons with their respective images
        seeds = new JButton[8];
        String seedImgName[] = {"Turnip", "Carrot", "Potato", "Rose", "TurnipF", "Sunflower", "Mango", "Apple"};
        for(int i=0;i<8;i++){
            seeds[i] = new JButton();
            seeds[i].setIcon(new ImageIcon(getClass().getResource("images/seeds/" + seedImgName[i] + ".png")));
            seedsPanel.add(seeds[i]);
        }
        profileLeft.setBackground(Color.decode("#D2D97A"));
        nameLabel = new JLabel();
        nameLabel.setText(model.getPlayer().getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        profileLeft.add(new JLabel(model.getPlayer().getName()), BorderLayout.NORTH);
        profileLeft.add(new JLabel(new ImageIcon(getClass().getResource("images/Farmer.png"))), BorderLayout.SOUTH);
        profileRight.setBackground(Color.decode("#D2D97A"));
        profilePanel.setBackground(Color.decode("#D2D97A"));
        profileLabelRight = new JLabel();
        profileLabelRight.setText("<html><p> " + model.getPlayer().displayPlayerStatus() + "</p></html>");
        profileRight.add(profileLabelRight);

        profilePanel.add(profileLeft);
        profilePanel.add(profileRight);
        panelSouth.add(profilePanel);
        panelSouth.add(seedsPanel);
        panelSouth.add(toolbarPanel);
        this.add(panelSouth, BorderLayout.SOUTH);
    }

    /**
     * adds an ActionListener for a certain tool button
     * @param actionListener the class that implements ActionListener
     * @param i the ith tool
     */
    public void addSeedListener(ActionListener actionListener, int i){
        seeds[i].addActionListener(actionListener);
    }

    /**
     * adds an ActionListener for a certain seed button
     * @param actionListener the class that implements ActionListener
     * @param i the ith tool
     */
    public void addToolListener(ActionListener actionListener, int i) {
            tools[i].addActionListener(actionListener);
    }


    /**
     * Sets up West Panel
     */
    private void initializeWest(){
        panelWest = new JPanel();
        panelWest.setBackground(Color.decode("#EFCAA0"));
        selectedLabel = new JLabel();
        selectedLabel.setBackground(Color.white);
        int row = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(0)));
        int col = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(2)));
        selectedLabel.setText("<html><p style = \"font-size: 12; padding: 5px 15px; width: 200px; text-align:center\">Lot  " + model.getCurrentSelected() + "</p>" + model.getFarmLand()[row][col].inform());

        panelWest.add(selectedLabel);
        this.add(panelWest, BorderLayout.WEST);
    }

    /**
     * Sets up East Panel
     */
    private void initializeEast(){
        panelEast = new JPanel();
        panelEast.setLayout(new BorderLayout());
        panelEast.setBackground(Color.decode("#EFCAA0"));
        panelEast.add(new JLabel("<html><p style = \" font-size: 16; padding: 10px 15px; width: 200px; text-align:center\">NOTIFICATIONS</p></html>"), BorderLayout.NORTH);
        notifsPanel = new JPanel();
        notifsPanel.setLayout(new BoxLayout(notifsPanel, BoxLayout.Y_AXIS));
        notifsScroll = new JScrollPane(notifsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        notifsScroll.getVerticalScrollBar().setUnitIncrement(16);
        panelEast.add(notifsScroll, BorderLayout.CENTER);
        this.add(panelEast, BorderLayout.EAST);
    }

    /**
     * adds a new notification with the a given string input
     * The string input's first character determines
     * the color of the notification
     * The string input may have HTML stylings
     *
     * @param input what the notification will show
     */
    public void addNotif(String input)
    {
        String color;
        switch(input.charAt(0))
        {
            case 'C':
            {
                // Critical
                color = "#CD5C05";
                break;
            }
            case 'N':
            {
                // Normal
                color = "#6495ED";
                break;
            }
            case 'W':
            {
                // Warning
                color = "#FFE40B";
                break;
            }
            case 'G':
            {
                // Good
                color = "#90EE09";
                break;
            }
            default:
                color = "#64950E";
        }

        JLabel notifLabels = new JLabel("<html><p style = \"padding: 20px; border-width:1px; border-style:solid; border-color:#000000; width: 200px; background-color:" + color + "\">" + input.substring(2) + "</p></html>");
        notifLabels.setOpaque(true);
        notifsPanel.add(notifLabels);
        this.revalidate();
        notifsScroll.getViewport().setViewPosition(notifLabels.getLocation());
        this.repaint();
    }

    /**
     * Refreshes the frame
     * Updates key portions of the window to keep up to date
     * With the current game state
     * Also checks if game over through model
     */
    public void refresh(){
        int row, col;
        row = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(0)));
        col = Integer.parseInt(String.valueOf(model.getCurrentSelected().charAt(2)));
        this.profileLabelRight.setText("<html><p style =\"padding:5px;\"> " + model.getPlayer().displayPlayerStatus() + "</p></html>");
        this.dayLabel.setText("<html><p style = \"padding: 5px 70px; font-size:18px; text-align:center; border-width:1px; border-style:solid; border-color:#000000; background-color: #EFCAA0\">Day " + model.getDay() + "</p></html>");
        this.selectedLabel.setText("<html><p style = \"font-size: 12; padding: 10px 15px; width: 200px; text-align:center\">Lot  " + model.getCurrentSelected() + "<br></p>" + model.getFarmLand()[row][col].inform());
        this.nameLabel.setText(model.getPlayer().getName());
        for (int i=0;i<5;i++)
            for(int j=0;j<10;j++)
                Lot[i][j].refreshImg();

        if(!model.gameOverMsg().equals("NONE")){
            this.getContentPane().removeAll();
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(250, 0, 0, 0));
            panel.setBackground(Color.decode("#CE5850"));
            panel.add(new JLabel("<html><p style = \"padding: 5px 70px; font-size:30px; color:#FFFFFF text-align:center;\">" + model.gameOverMsg() + "</p></html>"));
            this.add(panel);
        }

        this.revalidate();
        this.repaint();
    }
}
