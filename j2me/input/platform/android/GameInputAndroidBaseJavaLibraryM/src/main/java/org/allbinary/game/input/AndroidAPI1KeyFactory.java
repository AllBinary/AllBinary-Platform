package org.allbinary.game.input;

import org.allbinary.logic.NullUtil;


public class AndroidAPI1KeyFactory extends AndroidKeyFactory
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;

    public static final AndroidAPI1KeyFactory getInstance()
    {
        if(AndroidAPI1KeyFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            AndroidAPI1KeyFactory.instance = new AndroidAPI1KeyFactory();
        }
        
        return (AndroidAPI1KeyFactory) AndroidAPI1KeyFactory.instance;
    }

    protected AndroidAPI1KeyFactory()
    {
 
    }

}
