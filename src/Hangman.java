import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Hangman extends Module_Stage{

    public static Random Rand = new Random();
    public static int XLevel = 1;
    public static String flagcode = "";

    public Hangman(int level)
    {
        XLevel = level;
    }

    @Override
    public int Difficulty_level() {
        return XLevel;
    }

    @Override
    public JFrame Create_Module(String final_flag) {

        Map<String, Boolean> RamboLabels = new HashMap<String, Boolean>();
        JFrame frame;
        GUI Window = new GUI("Hangman module", 250, 800, Color.BLACK);
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
        File Hanger = new File("res/files/Hangman");
        Scanner reader = null;

        Scanner flag_reader = null;

        try
        {
            reader = new Scanner(Hanger);
            flag_reader = new Scanner(Hanger);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no file found");
        }


        test.append("$: ");
        while(reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");

            String token = tokenizer.nextToken();
            test.append(token +  " ,");

        }

        JLabel Source_code = new JLabel(String.valueOf(test));
        JScrollPane vertical = new JScrollPane(Source_code);


        JButton enter = new JButton("ENTER");
        JCheckBox[] boxes = new JCheckBox[26];
        Map<String, Boolean> Box_Boolean = new HashMap<String, Boolean>(); //for true or false values for double letters

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < 26; i++)
        {
            boxes[i] = new JCheckBox(String.valueOf(alphabet.charAt(i)));
            boxes[i + 1] = new JCheckBox(String.valueOf(alphabet.charAt(i + 1)));
            boxes[i].setBackground(Color.black);
            boxes[i+1].setBackground(Color.black);
            boxes[i].setForeground(Color.GREEN);
            boxes[i+1].setForeground(Color.GREEN);
            Box_Boolean.put(boxes[i].getText(),false);
            Box_Boolean.put(boxes[i + 1].getText(),false);
            bottom.add(boxes[i], gbc.gbc(0, i, GridBagConstraints.HORIZONTAL, 0, 10, 1, 1));
            bottom.add(boxes[i + 1], gbc.gbc(1, i, GridBagConstraints.HORIZONTAL, 0, 10, 1, 1));
            i++;
        }


        while (flag_reader.hasNext())
        {
            String line = flag_reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");
            String token_val = tokenizer.nextToken();

            if(Box_Boolean.get(token_val).equals(false))
            {
                Box_Boolean.replace(token_val, true);
            }
            else if(Box_Boolean.get(token_val).equals(true))
            {
                Box_Boolean.replace(token_val, false);
            }

        }

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean correct_boxes = false;
                for(int x = 0; x < 26; x++)
                {
                    if(boxes[x].isSelected() && Box_Boolean.get(boxes[x].getText()).equals(true)) //if all boxes are correct
                    {
                        correct_boxes = true;
                    }
                    else if(boxes[x].isSelected() && Box_Boolean.get(boxes[x].getText()).equals(false)) //if a box that isn't correct is selected
                    {
                        correct_boxes = false;
                        break;
                    }
                    else if(!boxes[x].isSelected() && Box_Boolean.get(boxes[x].getText()).equals(true)) //if a box that was correct wasn't selected
                    {
                        correct_boxes = false;
                        break;
                    }

                }
                if(correct_boxes == true)
                {
                    enter.setText(final_flag);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Keys are not correct!");
                }

            }
        });


        vertical.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        vertical.getViewport().setBackground(Color.BLACK);
        top.setBackground(Color.BLACK);
        bottom.setBackground(Color.BLACK);
        Source_code.setForeground(Color.GREEN);
        Source_code.setBackground(Color.BLACK);


        top.add(vertical, gbc.gbc(0, 0, GridBagConstraints.BOTH, 0, 0, 1, 1));
        bottom.add(enter, gbc.gbc(0, 27, 0, 0, 0, 1, 1));
        frame.add(Screen, gbc.gbc(0, 0, GridBagConstraints.BOTH, 0, 0, 1, 1));

        Screen.setResizeWeight(0.05);

        //frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null); //set window in center of screen

        return frame;

    }

    @Override
    public String Gen_Flag() {
        ///#5
        String flag;
        RandChar code = new RandChar();
        flagcode = code.GetRandomCharacter() + code.GetRandomNumber() + code.GetRandomLetter() +code.GetRandomNumber();
        flag = "FLAG 4 - [" + flagcode + "]";

        System.out.println(flag);
        return flag;
    }

    public String Flag_Code()
    {
        return flagcode;
    }

    @Override
    public void File_Gen() {

        File Searchfile = new File("res/files/Hangman");

        try {
            FileWriter writer = new FileWriter(Searchfile);
            RandChar code = new RandChar();
            int level_mod = XLevel;

            int R = Rand.nextInt(level_mod) + 4;

            for(int x = 0; x < R; x++)
            {
                writer.append(code.GetRandomLetter().toUpperCase(Locale.ROOT) + "\n");
            }

            writer.close();

        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }

}
