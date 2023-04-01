import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Brtue_Force extends Module_Stage{

    public static Random Rand = new Random();
    public static int XLevel = 1;
    String flag_solution = "";
    public static String flagcode = "";

    public Brtue_Force(int level)
    {
        XLevel = level;
    }

    @Override
    public int Difficulty_level() {
        return XLevel;
    }

    @Override
    public JFrame Create_Module(String final_flag) {

        JFrame frame;
        GUI Window = new GUI("Brute_force module", 800, 250, Color.BLACK);
        frame = Window.Create_GUI();
        GridBagLayout a = new GridBagLayout();
        frame.setLayout(a);
        GBC gbc = new GBC();

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JSplitPane Screen = new JSplitPane(SwingConstants.HORIZONTAL, top, bottom);

        top.setLayout(a);
        bottom.setLayout(a);

        StringBuilder test = new StringBuilder();
        File Brute_file = new File("res/files/BruteForce");
        Scanner reader = null;

        Scanner flag_reader = null;
        int flag_randnum = -1;

        try
        {
            reader = new Scanner(Brute_file);
            flag_reader = new Scanner(Brute_file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no file found");
        }

        test.append("<html>");
        while(reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");

            flag_randnum++;
            test.append(">>>         ");
            test.append(tokenizer.nextToken());
            test.append("<br/>");

        }
        test.append("</html>");

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

        JLabel Source_code = new JLabel(String.valueOf(test));
        JScrollPane vertical = new JScrollPane(Source_code);
        JTextField submit_code = new JTextField();
        JButton enter = new JButton("ENTER");

        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertical.getViewport().setBackground(Color.BLACK);
        top.setBackground(Color.BLACK);
        bottom.setBackground(Color.BLACK);
        Source_code.setForeground(Color.GREEN);
        Source_code.setBackground(Color.BLACK);
        submit_code.setBackground(Color.BLACK);
        submit_code.setForeground(Color.GREEN);

        Source_code.setFont(new Font("Arial", Font.PLAIN, 16));

        top.add(vertical, gbc.gbc(0, 0, GridBagConstraints.BOTH, 0, 0, 1, 1));
        bottom.add(submit_code, gbc.gbc(0, 1, GridBagConstraints.HORIZONTAL, 0, 25, 1, 1));
        bottom.add(enter, gbc.gbc(0, 2, 0, 0, 0, 1, 1));
        frame.add(Screen, gbc.gbc(0, 0, GridBagConstraints.BOTH, 0, 0, 1, 1));



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
                    JOptionPane.showMessageDialog(null, "Command is not correct!");
                }

            }
        });


        Screen.setResizeWeight(0.9);

        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null); //set window in center of screen

        System.out.println("BRUTE FORCE CODE: " + flag_solution);
        return frame;


    }

    @Override
    public String Gen_Flag() {
        String flag; ////#1
        RandChar code = new RandChar();
        flagcode = code.GetRandomLetter() + code.GetRandomNumber() + code.GetRandomCharacter() + code.GetRandomLetter();
        flag = "FLAG 1 - [" + flagcode + "]";

        System.out.println(flag);
        return flag;
    }

    public String Flag_Code()
    {
        return flagcode;
    }

    @Override
    public void File_Gen() {

        File brutefile = new File("res/files/BruteForce");

        try {
            FileWriter writer = new FileWriter(brutefile);
            RandChar code = new RandChar();
            int level_mod = XLevel;
            writer.append("~BRUTE FORCE CODES~");
            for(int x = 0; x < level_mod+5; x++)
            {
                StringBuilder FIN = new StringBuilder();
                if(level_mod <= 5)
                {
                    FIN.append(code.GetRandomCommand() + ".(");
                    for(int i = 0; i < 2; i++)
                    {
                        FIN.append(code.GetRandomNumber() + code.GetRandomLetter());
                    }
                    FIN.append(") >");
                    for(int i = 0; i < 1; i++)
                    {
                        FIN.append(code.GetRandomCharacter());
                    }
                    FIN.append(">");
                }
                else if(level_mod > 5 && level_mod <= 10)
                {
                    FIN.append(code.GetRandomCommand() + ".(");
                    for(int i = 0; i < 3; i++)
                    {
                        FIN.append(code.GetRandomNumber() + code.GetRandomLetter());
                    }
                    FIN.append(") >");
                    for(int i = 0; i < 2; i++)
                    {
                        FIN.append(code.GetRandomCharacter());
                    }
                    FIN.append("> + [" + code.GetRandomCommand() + "].");
                    for(int i = 0; i < 2; i++)
                    {
                        FIN.append(code.GetRandomNumber());
                    }
                }
                else ///level 10+
                {
                    FIN.append(code.GetRandomCommand() + ".(");
                    for(int i = 0; i < 3; i++)
                    {
                        FIN.append(code.GetRandomNumber() + code.GetRandomLetter());
                    }
                    FIN.append(") >");
                    for(int i = 0; i < 3; i++)
                    {
                        FIN.append(code.GetRandomCharacter());
                    }
                    FIN.append("> + [" + code.GetRandomCommand() + "].");
                    FIN.append(code.GetRandomCommand());
                    for(int i = 0; i < 3; i++)
                    {
                        FIN.append(code.GetRandomLetter());
                    }
                    FIN.append("_" + code.GetRandomLetter() + code.GetRandomNumber());
                }
                writer.append("\n" + FIN);

            }

            writer.close();

        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }

}
