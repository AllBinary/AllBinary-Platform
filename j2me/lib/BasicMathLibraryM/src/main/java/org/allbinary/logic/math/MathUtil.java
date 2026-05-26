package org.allbinary.logic.math;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;

/**
 *
 * @author user
 */
public class MathUtil
{
    private static final MathUtil instance = new MathUtil();
    
    public static MathUtil getInstance()
    {
        return MathUtil.instance;
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

    private final int ACCURACY = 100; //1000
    public double sqrtd(int x) {
        if (x == 0) {
            return (double) 0.0f;
        }
        if (x < 0) {
            return (double) -1.0f;
        }

        double result = (double) x;
        for (int index = 0; index < this.ACCURACY; index++) {
            result = (result + (x / result)) / 2;
        }

        return result;
    }
    
    public int abs(int value) {
        return (value < 0) ? -value : value;
    }

    public long abslong(long value) {
        return (value < 0) ? -value : value;
    }
    
    public int min(int value, int value2) {
        return (value <= value2) ? value : value2;
    }

    public int max(int value, int value2) {
        return (value >= value2) ? value : value2;
    }
    
//    public static void main(String[] args)
//    {
//        final CommonSeps commonSeps = CommonSeps.getInstance();
//        final MathUtil mathUtil = MathUtil.getInstance();
//        final StringMaker stringMaker = new StringMaker();
//        int result;
//        for(int i = 100000000; i > 0; i=i/2)
//        {
//            result = mathUtil.sqrt(i);
//            stringMaker.delete(0, stringMaker.length());
//            System.out.println(stringMaker.appendint(i).append(commonSeps.EQUALS).appendint(result).append(commonSeps.COLON).append(Double.toString(mathUtil.sqrtd(i))).append(commonSeps.COLON).append(Double.toString(Math.sqrt(i))).toString());
//        }
//    }

}
