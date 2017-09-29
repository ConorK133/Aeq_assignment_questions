public class One
{
    static int total = 0;
    public static void main(String[] args)
    {
        int[] seq = {2, 6, 7, 6, 3, 4, 5, 6, 3};
        if (seq.length > 0)
        {
            total++;
            calcCastles(seq);
        }

        System.out.println("Total Castles: " + total);
    }
    static public void calcCastles(int[] seq)
    {
        if (seq[0] > seq[1])
        {
            getValley(seq, 0);
        }
        else
        {
            getPeak(seq, 0);
        }
    }

    static void getPeak(int[] seq, int index)
    {
        if ((index + 1) < seq.length)
        {
            if (seq[index] < seq[index+1] || seq[index] == seq[index+1])
            {
                getPeak(seq, index+1);
            }
            else
            {
                total = total + 1;
                getValley(seq, index + 1);
            }
        }
        else
        {
            total++;
            return;
        }
    }

    static void getValley(int[] seq, int index)
    {
        if ((index + 1) < seq.length)
        {
            if (seq[index] > seq[index+1] || seq[index] == seq[index+1])
            {
                getValley(seq, index+1);
            }
            else
            {
                total = total + 1;
                getPeak(seq, index + 1);
            }
        }
        else
        {
            total++;
            return;
        }
    }
}