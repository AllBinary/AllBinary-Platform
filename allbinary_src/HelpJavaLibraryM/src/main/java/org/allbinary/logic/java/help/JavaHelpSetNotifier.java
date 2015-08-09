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
import java.util.Iterator;
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
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {      
            URL nextURL = (URL) iterator.next();
            if(url.toString().compareTo(nextURL.toString()) == 0)
            {
                isNotified = true;
            }
        }
        vector.add(url);
        return isNotified;
    }    
}
