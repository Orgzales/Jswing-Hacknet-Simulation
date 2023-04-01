import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Find_and_Search extends Module_Stage{

    public static Random Rand = new Random();
    public static int XLevel = 1;
    public static String fin_flag = "";
    public static JScrollPane panel;
    public static String flagcode = "";

    public Find_and_Search(int level)
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
        GUI Window = new GUI("Search & Find module", 800, 350, Color.BLACK);
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
        File Findfile = new File("res/files/FindSearch");
        Scanner reader = null;

        LinkedList<String> lines = new LinkedList<>();

        try
        {
            reader = new Scanner(Findfile);
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

            String token = tokenizer.nextToken();
            lines.add(token);

            test.append(token);
            test.append("<br/>");

        }
        test.append("</html>");

        JLabel Source_code = new JLabel(String.valueOf(test));
        panel = new JScrollPane(Source_code);
        JButton Sort_az = new JButton("SORT: A-Z");
        JButton Sort_za = new JButton("SORT: Z-A");
        JButton Sort_Long = new JButton("SORT: LONG");
        JButton Sort_Short = new JButton("SORT: SHORT");
        JButton Sort_Norm = new JButton("SORT: RANDOM");

        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.getViewport().setBackground(Color.BLACK);
        top.setBackground(Color.BLACK);
        bottom.setBackground(Color.BLACK);
        Source_code.setForeground(Color.GREEN);

        Source_code.setFont(new Font("Arial", Font.PLAIN, 20));

        top.add(Sort_az, gbc.gbc(0, 0, 0, 0, 0, 1, 1));
        top.add(Sort_za, gbc.gbc(1, 0, 0, 0, 0, 1, 1));
        top.add(Sort_Long, gbc.gbc(2, 0, 0, 0, 0, 1, 1));
        top.add(Sort_Short, gbc.gbc(3, 0, 0, 0, 0, 1, 1));
        top.add(Sort_Norm, gbc.gbc(4, 0, 0, 0, 0, 1, 1));
        bottom.add(panel, gbc.gbc(0, 1, GridBagConstraints.BOTH, 0, 25, 1, 1));


        Sort_az.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(lines);
                StringBuilder newtest = new StringBuilder();
                newtest.append("<html>");
                for(String token : lines)
                {
                    newtest.append(token);
                    newtest.append("<br/>");
                }
                newtest.append("</html>");
                Source_code.setText(String.valueOf(newtest));
            }
        });

        Sort_za.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(lines, Collections.reverseOrder());
                StringBuilder newtest = new StringBuilder();
                newtest.append("<html>");
                for(String token : lines)
                {
                    newtest.append(token);
                    newtest.append("<br/>");
                }
                newtest.append("</html>");
                Source_code.setText(String.valueOf(newtest));
            }
        });

        Sort_Long.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(lines, Comparator.comparing(String::length).reversed());
                StringBuilder newtest = new StringBuilder();
                newtest.append("<html>");
                for(String token : lines)
                {
                    newtest.append(token);
                    newtest.append("<br/>");
                }
                newtest.append("</html>");
                Source_code.setText(String.valueOf(newtest));
            }
        });

        Sort_Short.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(lines, Comparator.comparing(String::length));
                StringBuilder newtest = new StringBuilder();
                newtest.append("<html>");
                for(String token : lines)
                {
                    newtest.append(token);
                    newtest.append("<br/>");
                }
                newtest.append("</html>");
                Source_code.setText(String.valueOf(newtest));
            }
        });

        Sort_Norm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.shuffle(lines);
                StringBuilder newtest = new StringBuilder();
                newtest.append("<html>");
                for(String token : lines)
                {
                    newtest.append(token);
                    newtest.append("<br/>");
                }
                newtest.append("</html>");
                Source_code.setText(String.valueOf(newtest));
            }
        });

        Screen.setResizeWeight(0.1);

        //frame.setUndecorated(true);
        frame.setBackground(Color.BLACK);
        frame.add(Screen, gbc.gbc(0, 0, GridBagConstraints.BOTH, 0, 0, 1, 1));

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null); //set window in center of screen

        return frame;
    }

    @Override
    public String Gen_Flag() {
        String flag;
        RandChar code = new RandChar();
        flagcode = code.GetRandomLetter() + code.GetRandomNumber() + code.GetRandomLetter() + code.GetRandomLetter();
        flag = "FLAG 3 - [" + flagcode + "]";

        fin_flag = flag;

        System.out.println(fin_flag);
        return fin_flag;
        ////#3

    }

    public String Flag_Code()
    {
        return flagcode;
    }

    public String flag_line()
    {

        RandChar code = new RandChar();
        int level_mod = XLevel;

        StringBuilder FIN = new StringBuilder();
        StringBuilder searchline = new StringBuilder();
        int R = Rand.nextInt(50) + 25;
        int random_flag_place = Rand.nextInt(R -5);

        for(int i = 0; i < R; i++)
        {
            searchline.append(code.GetRandomNumber() + code.GetRandomLetter());
            if(i == random_flag_place)
            {
                searchline.append(" " + fin_flag + " ");
            }
        }

        for(int i = 0; i <= level_mod; i++)
        {
            FIN.append(code.GetRandomLetter());
        }
        FIN.append(": " + searchline);

        System.out.println(String.valueOf(FIN));
        return String.valueOf(FIN);
    }

    @Override
    public void File_Gen() {

        File Searchfile = new File("res/files/FindSearch");

        try {
            FileWriter writer = new FileWriter(Searchfile);
            RandChar code = new RandChar();
            int level_mod = XLevel;
            int flag_placeing = Rand.nextInt(XLevel * 10) ;
            String flag_line = flag_line();

            for(int x = 0; x < level_mod * 10; x++)
            {
                StringBuilder FIN = new StringBuilder();
                StringBuilder searchline = new StringBuilder();
                int R = Rand.nextInt(50) + 25;

                for(int i = 0; i < R; i++)
                {
                    searchline.append(code.GetRandomNumber() + code.GetRandomLetter());
                }

                for(int i = 0; i <= level_mod; i++)
                {
                    FIN.append(code.GetRandomLetter());
                }
                FIN.append(": " + searchline);

                if(x == flag_placeing)
                {
                    writer.append(flag_line + "\n");
                }

                writer.append(FIN + "\n");
            }


            writer.close();

        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }

    }
}
