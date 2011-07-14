/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.logic.math;

/**
 *
 * @author user
 */
public class MathUtil {

    public static int getTotalDigits(int digits)
    {
        int total = 0;
        while(digits > 0)
        {
            digits = digits / 10;
            total++;
        }
        return total;
    }
}
