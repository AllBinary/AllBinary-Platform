package org.allbinary.logic.math;

/**
 *
 * @author user
 */
public class MathUtil
{
    private static final MathUtil instance = new MathUtil();
    
    public static MathUtil getInstance()
    {
        return instance;
    }        
    
    public int getTotalDigits(int digits)
    {
        int total = 0;
        while(digits > 0)
        {
            digits = digits / 10;
            total++;
        }
        return total;
    }
    
    public int sqrt(final int value)
    {
        if (value <= 1) 
        {
            return value;
        }

        int accumulated = value >> 1;
        int result = (accumulated + (value / accumulated)) >> 1;
        do {
            accumulated = result;
            result = (accumulated + (value / accumulated)) >> 1;

        } while (accumulated > result);
        return accumulated;
    }
    
    public int abs(int value) {
        return (value < 0) ? -value : value;
    }

    public long abs(long value) {
        return (value < 0) ? -value : value;
    }
    
    public int min(int value, int value2) {
        return (value <= value2) ? value : value2;
    }

    public int max(int value, int value2) {
        return (value >= value2) ? value : value2;
    }
    
    /*
    public static void main(String[] args)
    {
        int result;
        for(int i = 100000000; i >= 0; i=i/2)
        {
            result = MathUtil.getInstance().sqrt(i);
            System.out.println(i + " = " + result);
        }
    }
    */
}
