import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RandChar {

    private static Random Rand = new Random();

    public RandChar()
    {

    }

    public String GetRandomLetter()
    {
        String alphabet = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
        String Random_key = String.valueOf(alphabet.charAt(Rand.nextInt(alphabet.length())));
        return Random_key;
    }
    public String GetRandomNumber()
    {
        String Numbers = "123456789";
        String Random_key = String.valueOf(Numbers.charAt(Rand.nextInt(Numbers.length())));
        return Random_key;
    }
    public String GetRandomCharacter()
    {
        String Char = "&%#$/?!";
        String Random_key = String.valueOf(Char.charAt(Rand.nextInt(Char.length())));
        return Random_key;
    }

    public String GetRandomCommand()
    {
        String command = "";

        File file = new File("res/files/CommandWord");
        Scanner reader = null;
        try
        {
            reader = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error, no file found");
        }

        int R = Rand.nextInt(15);
        for(int x = 0; x <= R +1; x++)
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, "\n");
            command = tokenizer.nextToken();

        }
        return command;
    }

}
