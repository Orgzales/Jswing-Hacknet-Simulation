import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.util.*;
import java.awt.*;
import java.util.Timer;
import javax.swing.*;


public class Main {

    private static Random Rand = new Random();
    public static JFrame Player_Bar = new JFrame(); //the controls for the buttons of the game
    public static Timer timer = new Timer();
    public static long highestscore = 0;
    public static long player_highscore = 0;

    public static int Player_CPU = 1;            //better cpu level, more time to work with
    public static int Player_Ram = 1;            //better ram level, more atttempts for passwords
    public static double CPU_price = 75.0;
    public static double RAM_price = 50.0;

    public static int level_number = 1;
    public static double Player_money = 0;
    public static double time_limit = 10.0; //30 seconds amount  10x 30 secs = 5 min
    public static double clock_time = time_limit;
    public static int Default_lives = 2;
    public static int Player_lives = Default_lives + Player_Ram;
    public static JLabel lives;


    public static Brtue_Force BruteLevel = new Brtue_Force(1); //flag 1
    public static Speed_mining SpeedLevel = new Speed_mining(1); //flag 2
    public static Find_and_Search FindLevel = new Find_and_Search(1); //flag 3
    public static Hangman HangLevel = new Hangman(1); //flag 4
    public static Polar_focus FocusLevel = new Polar_focus(1); //flag 5

    public static JFrame BruteForce_level = BruteLevel.Create_Module(BruteLevel.Gen_Flag());
    public static JFrame SpeedMining_level = SpeedLevel.Create_Module(SpeedLevel.Gen_Flag());
    public static JFrame FindSearch_level = FindLevel.Create_Module(FindLevel.Gen_Flag());
    public static JFrame Polarfoucs_level = FocusLevel.Create_Module(FocusLevel.Gen_Flag());
    public static JFrame Hangman_level = HangLevel.Create_Module(HangLevel.Gen_Flag());
    public static JFrame Final_Flag_level = Final_Flag();

    public static JFrame message = new JFrame();

    public static String FINAL_PASSWORD = "";


    public static ImageIcon tutorial_icon = new ImageIcon("res/pics/Tutorialpic.jpg");
    public static ImageIcon stroe_icon = new ImageIcon("res/pics/Storepic.jpg");


    public static void New_level_gen()
    {
        System.out.println("-------------NEW LEVEL---------------");
        BruteLevel = new Brtue_Force(level_number); //flag 1
        SpeedLevel = new Speed_mining(level_number); //flag 2
        FindLevel = new Find_and_Search(level_number); //flag 3
        HangLevel = new Hangman(level_number); //flag 4
        FocusLevel = new Polar_focus(level_number); //flag 5

        BruteLevel.File_Gen();
        SpeedLevel.File_Gen();
        FindLevel.File_Gen();
        HangLevel.File_Gen();
        FocusLevel.File_Gen();

        BruteForce_level = BruteLevel.Create_Module(BruteLevel.Gen_Flag());
        SpeedMining_level = SpeedLevel.Create_Module(SpeedLevel.Gen_Flag());
        FindSearch_level = FindLevel.Create_Module(FindLevel.Flag_Code());
        Polarfoucs_level = FocusLevel.Create_Module(FocusLevel.Gen_Flag());
        Hangman_level = HangLevel.Create_Module(HangLevel.Gen_Flag());
        Final_Flag_level = Final_Flag();



        Player_lives = Default_lives + Player_Ram;
        Create_password();

        if(level_number <= 5)
            time_limit = 10.0 * Player_CPU;
        else if(level_number > 5 && level_number <= 10)
            time_limit = 5.0 * Player_CPU;
        else //level number 10++
            time_limit = 3.0 * Player_CPU;

        clock_time = time_limit;


    }

    public static void Make_Player_Bar()
    {
        GUI Window = new GUI("Command Center", 1200, 200, Color.BLACK);
        Player_Bar = Window.Create_GUI();

        GridBagLayout a = new GridBagLayout();
        Player_Bar.setLayout(a);
        GBC gbc = new GBC();


        Player_Bar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Player_Bar.setUndecorated(false); // get rid of top bar use later
        Player_Bar.setLocationRelativeTo(null);
        Player_Bar.setVisible(true);

        ImageIcon BruteForceIcon = new ImageIcon("res/pics/bruteforceicon.jpg");
        ImageIcon SpeedMineIcon = new ImageIcon("res/pics/speedminingicon.jpg");
        ImageIcon FindSearchIcon = new ImageIcon("res/pics/SearchandFindicon.jpg");
        ImageIcon HangmanIcon = new ImageIcon("res/pics/Hangmanicon.jpg");
        ImageIcon PolarFocusIcon = new ImageIcon("res/pics/polarfocusicon.jpg");
        ImageIcon PasswordIcon = new ImageIcon("res/pics/passwordicon.jpg");

        JButton Brute_Force = new JButton(BruteForceIcon);
        JButton Speed_Mining = new JButton(SpeedMineIcon);
        JButton Find_Search = new JButton(FindSearchIcon);
        JButton Hangman = new JButton(HangmanIcon);
        JButton PolarFocus = new JButton(PolarFocusIcon);
        JButton Password = new JButton(PasswordIcon);

        Brute_Force.setBackground(Color.BLACK);
        Speed_Mining.setBackground(Color.BLACK);
        Find_Search.setBackground(Color.BLACK);
        Hangman.setBackground(Color.BLACK);
        PolarFocus.setBackground(Color.BLACK);
        Password.setBackground(Color.BLACK);

        Player_Bar.add(Brute_Force, gbc.gbc(1, 0, GridBagConstraints.HORIZONTAL, 0, 10, 0, 1));
        Player_Bar.add(Speed_Mining, gbc.gbc(2, 0, GridBagConstraints.HORIZONTAL, 0, 10, 0, 1));
        Player_Bar.add(Find_Search, gbc.gbc(3, 0, GridBagConstraints.HORIZONTAL, 0, 10, 0, 1));
        Player_Bar.add(Hangman, gbc.gbc(4, 0, GridBagConstraints.HORIZONTAL, 0, 10, 0, 1));
        Player_Bar.add(PolarFocus, gbc.gbc(5, 0, GridBagConstraints.HORIZONTAL, 0, 10, 0, 1));
        Player_Bar.add(Password, gbc.gbc(6, 0, GridBagConstraints.HORIZONTAL, 0, 10, 0, 1));

        Brute_Force.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!BruteForce_level.isVisible())
                    BruteForce_level.setVisible(true);
                else if(BruteForce_level.isVisible())
                    BruteForce_level.setVisible(false);
            }
        });

        Speed_Mining.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!SpeedMining_level.isVisible())
                    SpeedMining_level.setVisible(true);
                else if(SpeedMining_level.isVisible())
                    SpeedMining_level.setVisible(false);
            }
        });

        Find_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!FindSearch_level.isVisible())
                    FindSearch_level.setVisible(true);
                else if(FindSearch_level.isVisible())
                    FindSearch_level.setVisible(false);
            }
        });

        Hangman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Hangman_level.isVisible())
                    Hangman_level.setVisible(true);
                else if(Hangman_level.isVisible())
                    Hangman_level.setVisible(false);
            }
        });

        PolarFocus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Polarfoucs_level.isVisible())
                    Polarfoucs_level.setVisible(true);
                else if(Polarfoucs_level.isVisible())
                    Polarfoucs_level.setVisible(false);
            }
        });

        Password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Final_Flag_level.isVisible())
                    Final_Flag_level.setVisible(true);
                else if(Final_Flag_level.isVisible())
                    Final_Flag_level.setVisible(false);
            }
        });

    }


    public static void main(String[] args)
    {
        GameOver(null, null);
        Main_menu();
    }


    public static void Main_menu()
    {
        JFrame Main;
        GUI Window = new GUI("Stay Unseen", 1200, 800, Color.BLACK);
        Main = Window.Create_GUI();

        GridBagLayout a = new GridBagLayout();
        Main.setLayout(a);
        GBC gbc = new GBC();
        JButton Start_Game_Button = new JButton("START GAME");
        JButton Scores_button = new JButton("HIGHSCORES");
        JButton Store_button = new JButton("STORE");
        JButton Tutorial_Button= new JButton("TUTORIAL");
        JButton Exit_Button = new JButton("RETURN TO DESKTOP");

        Start_Game_Button.setBackground(Color.GREEN);
        Scores_button.setBackground(Color.GREEN);
        Store_button.setBackground(Color.GREEN);
        Tutorial_Button.setBackground(Color.GREEN);
        Exit_Button.setBackground(Color.GREEN);


        JLabel Title = new JLabel(new ImageIcon("res/pics/Title.jpg"));
        Main.add(Title);
        Main.add(Start_Game_Button, gbc.gbc(0, 1, GridBagConstraints.BOTH, 800, 100, 0, 2));
        Main.add(Scores_button, gbc.gbc(0, 2, GridBagConstraints.BOTH, 800, 100, 0, 2));
        Main.add(Store_button, gbc.gbc(0, 3, GridBagConstraints.BOTH, 800, 100, 0, 2));
        Main.add(Tutorial_Button, gbc.gbc(0, 4, GridBagConstraints.BOTH, 800, 100, 0, 2));
        Main.add(Exit_Button, gbc.gbc(0, 5, GridBagConstraints.BOTH, 800, 100, 0, 2));

        JLabel Aurther = new JLabel("Created By: Orion G.");
        Aurther.setHorizontalAlignment(SwingConstants.RIGHT);
        Aurther.setVerticalAlignment(SwingConstants.TOP);
        Main.add(Aurther, gbc.gbc(0, 6, GridBagConstraints.BOTH, 200, 100, 0, 2));


        Start_Game_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextLevel();
                Main.dispose();
            }
        });

        /////////////////////if else for continue game

        Scores_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Highscores();
            }
        });

        Store_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "", "STORE", 0, stroe_icon );
            }
        });

        Tutorial_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "", "TUTORIAL", 0, tutorial_icon );

            }
        });

        Exit_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.dispose();
            }
        });

        Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main.setLocationRelativeTo(null); //set window in center of screen
        Main.setVisible(true);


    }



    public static void nextLevel()
    {
        message = new JFrame();
        JOptionPane.showMessageDialog(message, "Level " + String.valueOf(level_number) + "\n" +
                "Your Balance: $" + String.valueOf(Player_money) + "\n" +
                "Time You have: " + String.valueOf(clock_time) + " mins\n" +
                "Are you ready to start?" );

        New_level_gen();
        Make_Player_Bar();

        GUI Window = new GUI("Time till caught", 200, 100, Color.BLACK);
        message = Window.Create_GUI();

        GridBagLayout a = new GridBagLayout();
        message.setLayout(a);
        GBC gbc = new GBC();

        JLabel level_title = new JLabel("Level: " + String.valueOf(level_number));
        JLabel time_left = new JLabel("Time Left: " + String.valueOf(clock_time) + " Min(s)");
        lives = new JLabel("Attempts Left: " + String.valueOf(Player_lives));
        JLabel Balance = new JLabel("Balance: $" + String.valueOf(Player_money));

        level_title.setForeground(Color.green);
        time_left.setForeground(Color.GREEN);
        lives.setForeground(Color.green);
        Balance.setForeground(Color.GREEN);

        message.add(level_title, gbc.gbc(0, 1, GridBagConstraints.BOTH, 0, 25, 0, 1));
        message.add(time_left, gbc.gbc(0, 2, GridBagConstraints.BOTH, 0, 25, 0, 1));
        message.add(lives, gbc.gbc(0, 3, GridBagConstraints.BOTH, 0, 25, 0, 1));
        message.add(Balance, gbc.gbc(0, 4, GridBagConstraints.BOTH, 0, 25, 0, 1));

        message.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        message.setLocationRelativeTo(null); //set window in center of screen
        message.setVisible(true);

        timer = new Timer();
        timer.schedule( new TimerTask()
        {
            public void run()
            {
                System.out.println("30 Seconds Later");
                clock_time = clock_time - 0.5;
                time_left.setText("Time Left: " + String.valueOf(clock_time) + " Min(s)");
                if (--time_limit < 1) {
                    System.out.println("time is up");
                    timer.cancel(); // Count down ten times, then cancel if it is 5 min
                }
            }
        }, 30000, 30000 );

    }

    public static void Create_password()
    {
        FINAL_PASSWORD = BruteLevel.Flag_Code() + SpeedLevel.Flag_Code() + FindLevel.Flag_Code() + HangLevel.Flag_Code() + FocusLevel.Flag_Code();
        System.out.println(FINAL_PASSWORD);
    }

    public static JFrame Final_Flag()
    {
        JFrame frame;
        GUI Window = new GUI("Password Crack module", 250, 200, Color.BLACK);
        frame = Window.Create_GUI();
        GridBagLayout a = new GridBagLayout();
        frame.setLayout(a);
        GBC gbc = new GBC();

        JLabel title = new JLabel("~PASSWORD CRACK~");
        JTextField submit_password = new JTextField();
        JButton test = new JButton("TEST PASSWORD");

        title.setForeground(Color.GREEN);
        submit_password.setBackground(Color.BLACK);
        submit_password.setForeground(Color.GREEN);

        frame.add(title, gbc.gbc(0, 0, GridBagConstraints.CENTER, 0, 0, 1, 1));
        frame.add(submit_password, gbc.gbc(0, 1, GridBagConstraints.CENTER, 200, 10, 1, 1));
        frame.add(test, gbc.gbc(0, 2, GridBagConstraints.NONE, 0, 0, 1, 1));

        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(FINAL_PASSWORD.equals(submit_password.getText()))
                {
                    timer.cancel();
                    Player_Bar.dispose();
                    BruteForce_level.dispose();
                    SpeedMining_level.dispose();
                    FindSearch_level.dispose();
                    Polarfoucs_level.dispose();
                    Hangman_level.dispose();
                    Final_Flag_level.dispose();
                    message.dispose();

                    double money = clock_time * level_number + level_number;
                    Player_money = Player_money + money;
                    level_number++;
                    JOptionPane.showMessageDialog(frame, "Level " + String.valueOf(level_number - 1) + " is completed, You've stole enough money to keep you going\n" +
                            "Amount of money stole: $" + String.valueOf(money) + "\n" +
                            "Current Balance: $" + String.valueOf(Player_money));
                    int choice = JOptionPane.showConfirmDialog(frame,"Do you wanna upgrade?", "next level", JOptionPane.YES_NO_OPTION);
                    if(choice == JOptionPane.YES_OPTION)
                    {
                        Store_interface();
                    }
                    else
                    {
                        nextLevel();
                    }



                }
                else
                {
                    Player_lives--;
                    lives.setText("Attempts Left: " + String.valueOf(Player_lives));
                    if(Player_lives <= 0)
                    {
                        JOptionPane.showMessageDialog(frame, "~GAMEOVER!!!~\n" +
                                "You've been caught trying to hack into this account to steal money, Police is on the way and your IP is being tracked");
                        player_highscore = Long.valueOf((100000 * level_number) + (50000 * Player_CPU) + (50000 * Player_Ram) + Long.valueOf((long) Player_money));
                        player_highscore = player_highscore + Long.valueOf(Rand.nextInt(1000));
                        String name = JOptionPane.showInputDialog(frame, "HIGHSCORE: " + String.valueOf(player_highscore) + "\n" +
                                "Please enter a name");
                        GameOver(player_highscore, name); /////////////put highcore
                        Main_menu();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "PASSWORD IS WRONG, BE CAREFUL");
                }

            }
        });

        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null); //set window in center of screen

        return frame;
    }


    public static void Highscores()
    {
        //Collections.sort(leaderboard, Comparator.reverseOrder());

        quick_sort(leader_board, 0, leader_board.length -1);
        StringBuilder stuff = new StringBuilder();
        for(int x = leader_board.length -1; x > 0; x--)
            stuff.append(String.valueOf(leader_board[x].longValue()) + " :: " + mapnames.get(leader_board[x].longValue()) + "\n");
        JOptionPane.showMessageDialog(new JFrame(), "~HIGHSCORES~\n" + stuff);
    }

    public static void quick_sort(Long[] leader_board, int lower, int higher){
        if(lower < higher){
            int x = split(leader_board, lower, higher);
            quick_sort(leader_board, lower, x-1);
            quick_sort(leader_board, x+1, higher);
        }
    }

    public static int split(Long[] leader_board, int lower, int higher){
        int x = lower;
        for(int i=lower+1; i <= higher; i++)
            if(leader_board[i] < leader_board[lower])
                switch_value(leader_board, ++x, i);

        switch_value(leader_board, lower, x);
        return x;
    }

    static void switch_value(Long[] leader_board, int lower, int stop){
        Long stuff = leader_board[lower];
        leader_board[lower] = leader_board[stop];
        leader_board[stop] = stuff;
    }



    public static void Store_interface()
    {
        JFrame frame;
        GUI Window = new GUI("Password Crack module", 300, 300, Color.BLACK);
        frame = Window.Create_GUI();
        GridBagLayout a = new GridBagLayout();
        frame.setLayout(a);
        GBC gbc = new GBC();

        JLabel title = new JLabel("~UPGRADES~");
        JLabel balance = new JLabel("Balance: $" + String.valueOf(Player_money));
        JLabel CPU_Title = new JLabel("CPU UPGRADE: ");
        JLabel RAM_Title = new JLabel("RAM UPGRADE: ");
        JButton CPU_Upgrade = new JButton("BUY $" + String.valueOf(CPU_price));
        JButton RAM_Upgrade = new JButton("BUY $" + String.valueOf(RAM_price));
        JButton Continue = new JButton("Continue");

        title.setForeground(Color.GREEN);
        balance.setForeground(Color.GREEN);
        CPU_Title.setForeground(Color.GREEN);
        RAM_Title.setForeground(Color.GREEN);
        CPU_Upgrade.setForeground(Color.GREEN);
        RAM_Upgrade.setForeground(Color.GREEN);
        CPU_Upgrade.setBackground(Color.BLACK);
        RAM_Upgrade.setBackground(Color.black);

        frame.add(title, gbc.gbc(1, 0, GridBagConstraints.CENTER, 0, 0, 1, 1));
        frame.add(balance, gbc.gbc(1, 1, GridBagConstraints.CENTER, 0, 0, 1, 1));
        frame.add(CPU_Title, gbc.gbc(0, 2, GridBagConstraints.EAST, 0, 0, 1, 1));
        frame.add(CPU_Upgrade, gbc.gbc(2, 2, GridBagConstraints.HORIZONTAL, 0, 0, 2, 1));
        frame.add(RAM_Title, gbc.gbc(0, 3, GridBagConstraints.EAST, 0, 0, 1, 1));
        frame.add(RAM_Upgrade, gbc.gbc(2, 3, GridBagConstraints.HORIZONTAL, 0, 0, 2, 1));
        frame.add(Continue, gbc.gbc(1, 4, GridBagConstraints.CENTER, 0, 0, 1, 1));

        CPU_Upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Player_money > CPU_price)
                {
                    Player_money = Player_money - CPU_price;
                    CPU_price = CPU_price + 75.0;
                    CPU_Upgrade.setText("BUY $" + String.valueOf(CPU_price));
                    balance.setText("Balance: $" + String.valueOf(Player_money));
                    Player_CPU++;
                    JOptionPane.showMessageDialog(frame, "CPU UPGRADED to Lv." + String.valueOf(Player_CPU));
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "NOT ENOUGH MONEY");
                }

            }
        });

        RAM_Upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Player_money > RAM_price)
                {
                    Player_money = Player_money - RAM_price;
                    RAM_price = RAM_price + 50.0;
                    RAM_Upgrade.setText("BUY $" + String.valueOf(RAM_price));
                    balance.setText("Balance: $" + String.valueOf(Player_money));
                    Player_Ram++;
                    JOptionPane.showMessageDialog(frame, "RAM UPGRADED to Lv." + String.valueOf(Player_Ram));
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "NOT ENOUGH MONEY");
                }
            }
        });

        Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                nextLevel();
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null); //set window in center of screen

    }

    public static Long[] leader_board = new Long[0];
    public static Map<Long, String> mapnames = new HashMap<Long, String>();

    public static void GameOver(Long score, String name)
    {
        timer.cancel();
        Player_Bar.dispose();
        BruteForce_level.dispose();
        SpeedMining_level.dispose();
        FindSearch_level.dispose();
        Polarfoucs_level.dispose();
        Hangman_level.dispose();
        Final_Flag_level.dispose();
        message.dispose();

        level_number = 1;
        New_level_gen();
        CPU_price = 75.0;
        RAM_price = 50.0;
        Player_money = 0;
        Player_CPU = 1;
        Player_Ram = 1;

        FileWriter highscore_names = null;
        File pastscores = new File("res/files/highscores");
        Scanner reader = null;
        StringBuilder stuff = new StringBuilder();


        try
        {
            reader = new Scanner(pastscores);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no file found");
        }
        int size = 0;

        while (reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");
            size++;
        }

        try
        {
            reader = new Scanner(pastscores);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no file found");
        }

        if(score != null || name != null) //bypass the initial start of the code
            size++;

        leader_board = new Long[size];
        int x = 0;
        while (reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            String Number = tokenizer.nextToken();
            String Word = tokenizer.nextToken();
            stuff.append(Number + "," + Word + "\n");
            leader_board[x] = (Long.valueOf(Number));
            mapnames.put(Long.valueOf(Number), Word);
            x++;
        }

        if (score != null || name != null)
        {
            if(name.equals(""))
            {
                name = "no name";
            }
            stuff.append(String.valueOf(score) + "," + name + "\n");
            leader_board[x] = (score);
            mapnames.put(score, name);
        }

        try
        {
            highscore_names = new FileWriter("res/files/highscores");
            highscore_names.append(stuff);
            highscore_names.close();
        }
        catch (IOException e)
        {
            System.out.println("Error");
        }

    }



}
