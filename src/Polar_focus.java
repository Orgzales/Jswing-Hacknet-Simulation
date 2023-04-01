import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class Polar_focus extends Module_Stage{

    public static JFrame frame;
    public static Random Rand = new Random();
    public static int XLevel = 1;
    public static Map<Integer, Integer> Ramintmap = new HashMap<Integer, Integer>();
    public static Map<Integer, Boolean> RamboLabels = new HashMap<Integer, Boolean>();
    public static String flagcode = "";

    public Polar_focus(int level)
    {
        XLevel = level;
    }

    @Override
    public int Difficulty_level() {
        return XLevel;
    }

    public void POP_UP(String final_flag)
    {

        int R = Rand.nextInt(Ramintmap.size());
        int timepop = Rand.nextInt(Rand.nextInt(3000) + 1000);
        Timer timer = new Timer();
        Timer timelimit = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                JFrame popup_frame;
                GUI Window = new GUI("!!!!!!!!!!", 200, 100, Color.BLACK);
                popup_frame = Window.Create_GUI();


                JButton pop = new JButton(String.valueOf(Ramintmap.get(R)));
                popup_frame.add(pop);


                pop.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(RamboLabels.get(Ramintmap.get(R)))
                        {
                            timer.cancel();
                            timelimit.cancel();
                            pop.setText(final_flag);

                        }
                        else
                        {
                            timer.cancel();
                            timelimit.cancel();
                            popup_frame.dispose();
                            JOptionPane.showMessageDialog(frame, "uncorrect, stay unseen or you will be caught"); //add time punishment later
                            frame.setVisible(true);
                        }

                    }
                });

                int timeclose = Rand.nextInt(Rand.nextInt(4000) + 2000);

                timelimit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        popup_frame.dispose();
                        POP_UP(final_flag);
                    }
                }, timeclose);

                popup_frame.setLocation(Rand.nextInt(800) + 100, Rand.nextInt(500) + 200); //set window in center of screen
                popup_frame.setVisible(true);


            }
        }, timepop);



    }

    @Override
    public JFrame Create_Module(String final_flag) {

        GUI Window = new GUI("Polar Focus module", 250, 600, Color.BLACK);
        frame = Window.Create_GUI();
        GridBagLayout a = new GridBagLayout();
        frame.setLayout(a);
        GBC gbc = new GBC();

        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JSplitPane Screen = new JSplitPane(SwingConstants.HORIZONTAL, top, bottom);
        StringBuilder data = new StringBuilder();

        top.setLayout(a);
        bottom.setLayout(a);

        File POlar = new File("res/files/PolarFocus");
        Scanner reader = null;
        String present_code = "";


        try
        {
            reader = new Scanner(POlar);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no file found");
        }

        int x = 0;
        data.append("<html>");
        boolean prevent_error = true;
        while(reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");
            present_code = tokenizer.nextToken();
            data.append(">>> $c: " + present_code);


            int R = Rand.nextInt(10);


            if(R > 5)
            {
                Ramintmap.put(x, Integer.valueOf(present_code));
                RamboLabels.put(Integer.valueOf(present_code), true);
                data.append(" | Good");
            }
            else if (prevent_error)
            {
                prevent_error = false;
                Ramintmap.put(x, Integer.valueOf(present_code));
                RamboLabels.put(Integer.valueOf(present_code), true);
                data.append(" | Good");
            }
            else
            {
                Ramintmap.put(x, Integer.valueOf(present_code));
                RamboLabels.put(Integer.valueOf(present_code), false);
                data.append(" | Bad");
            }
            data.append("<br/>");

            x++;
        }
        data.append("</html>");



        JButton Start = new JButton("START");
        JLabel Source_code = new JLabel(String.valueOf(data));

        top.setBackground(Color.BLACK);
        bottom.setBackground(Color.BLACK);
        Source_code.setForeground(Color.GREEN);
        Source_code.setBackground(Color.BLACK);


        top.add(Source_code, gbc.gbc(0, 0, GridBagConstraints.BOTH,0 , 0, 1, 1));
        bottom.add(Start, gbc.gbc(0, 2, GridBagConstraints.BOTH, 0, 0, 1, 1));
        frame.add(Screen, gbc.gbc(0, 0, GridBagConstraints.BOTH, 0, 0, 1, 1));

        Screen.setResizeWeight(0.9);

        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                POP_UP(final_flag);
                frame.setVisible(false);
            }
        });


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
        flagcode = code.GetRandomLetter() + code.GetRandomLetter() + code.GetRandomNumber() + code.GetRandomCharacter();
        flag = "FLAG 5 - [" + flagcode + "]";

        System.out.println(flag);
        return flag;
    }

    public String Flag_Code()
    {
        return flagcode;
    }

    @Override
    public void File_Gen() {
        File Searchfile = new File("res/files/PolarFocus");

        try {
            FileWriter writer = new FileWriter(Searchfile);
            RandChar code = new RandChar();
            int level_mod = XLevel;

            for(int x = 0; x < level_mod + 3; x++)
            {
                StringBuilder FIN = new StringBuilder();

                for(int i = 0; i < 5; i++)
                {
                    FIN.append(code.GetRandomNumber());
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
