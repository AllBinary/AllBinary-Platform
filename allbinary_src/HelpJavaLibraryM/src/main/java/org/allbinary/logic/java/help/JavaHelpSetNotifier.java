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
package org.allbinary.logic.java.help;

import java.net.URL;

import java.util.Vector;
import javax.help.HelpSet;

public class JavaHelpSetNotifier
{
    private static Vector vector = new Vector();
    
    private JavaHelpSetNotifier()
    {
    }
    
    private static Vector get()
    {
        return vector;
    }

    public static boolean isNotified(HelpSet helpSet)
    {
        boolean isNotified = false;
        URL url = helpSet.getHelpSetURL();
        int size = vector.size();
        Object[] urlArray = vector.toArray();
        for (int i = 0; i < size; i++)
        {
            URL nextURL = (URL) urlArray[i];
            if(url.toString().compareTo(nextURL.toString()) == 0)
            {
                isNotified = true;
            }
        }
        vector.add(url);
        return isNotified;
    }    
}
