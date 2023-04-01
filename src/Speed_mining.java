import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLClientInfoException;
import java.util.*;
import java.util.Timer;


public class Speed_mining extends Module_Stage {

    public static int XLevel = 1;
    public static Random Rand = new Random();
    public static JLabel Source_code = new JLabel();
    String flag_solution = "";
    public static String flagcode = "";

    public Speed_mining(int level)
    {
        XLevel = level;
    }

    @Override
    public int Difficulty_level() {
        return XLevel;
    }

    public void changeview(Map stuff)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int R = Rand.nextInt(stuff.size());
                Source_code.setText(">>> " + String.valueOf(stuff.get(R)) + " <<<");
                changeview(stuff);
            }
        }, 1000);
    }

    @Override
    public JFrame Create_Module(String final_flag) {
        Map<Integer, String> RamboLabels = new HashMap<Integer, String>();
        Timer timer = new Timer();

        JFrame frame;
        GUI Window = new GUI("Speed mining module", 250, 250, Color.BLACK);
        frame = Window.Create_GUI();
        GridBagLayout a = new GridBagLayout();
        frame.setLayout(a);
        GBC gbc = new GBC();

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JSplitPane Screen = new JSplitPane(SwingConstants.HORIZONTAL, top, bottom);

        top.setLayout(a);
        bottom.setLayout(a);

        File Speedmine = new File("res/files/SpeedMine");
        Scanner reader = null;
        String present_code = "";

        Scanner flag_reader = null;
        int flag_randnum = -1;

        try
        {
            reader = new Scanner(Speedmine);
            flag_reader = new Scanner(Speedmine);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no file found");
        }

        int x = 0;
        while(reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");
            present_code = tokenizer.nextToken();

            flag_randnum++;
            RamboLabels.put(x, present_code);
            x++;
        }

        int random_F_num = Rand.nextInt(flag_randnum);
        int current_F_num = -1;
        while (flag_reader.hasNext())
        {
            String line = flag_reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");

            if(current_F_num == random_F_num)
            {
                flag_solution = tokenizer.nextToken();
            }
            current_F_num++;
        }

        changeview(RamboLabels);


        JTextField submit_code = new JTextField();
        JButton enter = new JButton("ENTER");

        top.setBackground(Color.BLACK);
        bottom.setBackground(Color.BLACK);
        Source_code.setForeground(Color.GREEN);
        Source_code.setBackground(Color.BLACK);
        submit_code.setBackground(Color.BLACK);
        submit_code.setForeground(Color.GREEN);
        Source_code.setFont(new Font("Arial", Font.PLAIN, 25));


        top.add(Source_code, gbc.gbc(0, 0, GridBagConstraints.BOTH,0 , 0, 1, 1));
        bottom.add(submit_code, gbc.gbc(0, 1, GridBagConstraints.HORIZONTAL, 0, 25, 1, 1));
        bottom.add(enter, gbc.gbc(0, 2, 0, 0, 0, 1, 1));
        frame.add(Screen, gbc.gbc(0, 0, GridBagConstraints.BOTH, 0, 0, 1, 1));

        Screen.setResizeWeight(0.9);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(flag_solution.equals(submit_code.getText()))
                {
                    System.out.println(final_flag);
                    enter.setText(final_flag);

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Key is not correct!");
                }

            }
        });


        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null); //set window in center of screen

        System.out.println("SPEED MINING: " + flag_solution);
        return frame;
    }


    @Override
    public String Gen_Flag() {
        ///#2

        String flag;
        RandChar code = new RandChar();
        flagcode = code.GetRandomLetter() + code.GetRandomNumber() + code.GetRandomNumber() + code.GetRandomLetter();
        flag = "FLAG 2 - [" + flagcode + "]";

        System.out.println(flag);
        return flag;
    }

    public String Flag_Code()
    {
        return flagcode;
    }

    @Override
    public void File_Gen() {

        File Searchfile = new File("res/files/SpeedMine");

        try {
            FileWriter writer = new FileWriter(Searchfile);
            RandChar code = new RandChar();
            int level_mod = XLevel;

            if(level_mod <= 5)
            {
                for(int x = 0; x < level_mod * 2; x++)
                {
                    StringBuilder FIN = new StringBuilder();
                    for(int i = 0; i < 5; i++)
                    {
                        FIN.append(code.GetRandomLetter());
                    }

                    writer.append(FIN + "\n");
                }
            }
            else // level > 5
            {
                for(int x = 0; x < level_mod + 10; x++)
                {
                    StringBuilder FIN = new StringBuilder();
                    for(int i = 0; i < 5; i++)
                    {
                        FIN.append(code.GetRandomLetter());
                    }

                    writer.append(FIN + "\n");
                }
            }

            writer.close();

        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }

}
