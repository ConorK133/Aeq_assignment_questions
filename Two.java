import java.io.*;
import java.util.*;
class Transformer
{
    String name;
    String type;
    int str = 0;
    int intel = 0;
    int spd = 0;
    int end = 0;
    int rank = 0;
    int courage = 0;
    int fp = 0;
    int skill = 0;
    int overall = 0;
    boolean dead = false;

    Transformer(String[] input)
    {
        if (input.length > 0)
        {
            name = input[0];
            type = input[1];
            str = Integer.parseInt(input[2]);
            intel = Integer.parseInt(input[3]);
            spd = Integer.parseInt(input[4]);
            end = Integer.parseInt(input[5]);
            rank = Integer.parseInt(input[6]);
            courage = Integer.parseInt(input[7]);
            fp = Integer.parseInt(input[8]);
            skill = Integer.parseInt(input[9]);
            overall = str + intel + spd + end + fp;
        }
    }
}
public class Two
{
    static ArrayList<Transformer> autobots = new ArrayList<>();
    static ArrayList<Transformer> decepticons = new ArrayList<>();
    static int dElims = 0;
    static int aElims = 0;

    public static void main(String[] args)
    {
        String fn = "./transformers.txt";

        readTransformers(fn);

        battle(0);
    }

    static void readTransformers(String fileName)
    {
        String[] transformer;
        String temp;

        try
        {
            File file = new File(fileName);
            Scanner fileIn = new Scanner(file);

            while(fileIn.hasNextLine())
            {
                temp = fileIn.nextLine();
                transformer = temp.split(",");

                Transformer t = new Transformer(transformer);

                if (t.type.equals("A"))
                {
                    autobots.add(t);
                }
                else
                {
                    decepticons.add(t);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Could not find input file");
        }
    }

    static void battle(int index)
    {
        Transformer auto = null;
        Transformer decpt = null;

        if(autobots.size() > index)
        {
            auto = autobots.get(index);
        }
        if(decepticons.size() > index)
        {
            decpt = decepticons.get(index);
        }


        if(auto == null || decpt == null)
        {
            computeResults(index);
            return;
        }
        else if (auto.name.equals("Optimus Prime") && decpt.name.equals("Predaking"))
        {
            destroyAll();
            return;
        }
        else
        {
            fight(auto, decpt, index);
            battle(++index);
        }

    }

    static void fight(Transformer auto, Transformer decpt, int index)
    {
        primeOrPred(auto, decpt, index);
    }

    static void primeOrPred(Transformer auto, Transformer decpt, int index)
    {
        if (auto.name.equals("Optimus Prime"))
        {
            winBattle(auto.type, decpt, index);
        }
        else if(decpt.name.equals("Predaking"))
        {
            winBattle(decpt.type, auto, index);
        }
        else
        {
            courageAndStrenght(auto, decpt, index);
        }
    }

    static void courageAndStrenght(Transformer auto, Transformer decpt, int index)
    {
        int courageDiff = auto.courage - decpt.courage;
        int strDiff = auto.str - decpt.str;

        if(courageDiff >= 4 && strDiff >= 3)
        {
            winBattle(auto.type, decpt, index);
        }
        else if (courageDiff <= -4 && strDiff <= -3)
        {
            winBattle(decpt.type, auto, index);
        }
        else
        {
            skillDiff(auto, decpt, index);
        }
    }

    static void skillDiff(Transformer auto, Transformer decpt, int index)
    {
        int skillDiff = auto.skill - decpt.skill;

        if (skillDiff >= 3)
        {
            winBattle(auto.type, decpt, index);
        }
        else if (skillDiff <= -3)
        {
            winBattle(decpt.type, auto, index);
        }
        else
        {
            overall(auto, decpt, index);
        }
    }

    static void overall(Transformer auto, Transformer decpt, int index)
    {
        if (auto.overall > decpt.overall)
        {
            winBattle(auto.type, decpt, index);
        }
        else if (auto.overall < decpt.overall)
        {
            winBattle(decpt.type, auto, index);
        }
        else
        {
            drawBattle(auto,decpt, index);
        }
    }

    static void winBattle(String type, Transformer loser, int index)
    {
        loser.dead = true;
        if (type.equals("A"))
        {
            aElims++;
            decepticons.set(index, loser);
        }
        else
        {
            dElims++;
            autobots.set(index, loser);
        }
    }

    static void drawBattle(Transformer auto, Transformer decpt, int index)
    {
        auto.dead = true;
        decpt.dead = true;

        autobots.set(index, auto);
        decepticons.set(index, decpt);
    }

    static void destroyAll()
    {
        System.out.println("Optimus and Predaking have fought. The game is over");
    }

    static void computeResults(int index)
    {
        System.out.println(index + " battle");
        if (aElims > dElims)
        {
            printResults("Autobots", autobots, "Decepticons", decepticons);
        }
        else if(aElims < dElims)
        {
            printResults("Decepticons", decepticons, "Autobots", autobots);
        }
        else
        {
            System.out.print("This battle was a draw");
        }
    }
    static void printResults(String winner, ArrayList<Transformer> winnerAL, String loser, ArrayList<Transformer> loserAL)
    {
        System.out.print("Winning Team (" + winner + "):");
        for (Transformer t : winnerAL)
        {
            if (!t.dead)
            {
                System.out.print(" " + t.name + ",");
            }
        }
        System.out.println();
        System.out.print("Survivors from the losing team (" + loser + "):");
        for (Transformer t : loserAL)
        {
            if (!t.dead)
            {
                System.out.print(" " + t.name + ",");
            }
        }
    }
}