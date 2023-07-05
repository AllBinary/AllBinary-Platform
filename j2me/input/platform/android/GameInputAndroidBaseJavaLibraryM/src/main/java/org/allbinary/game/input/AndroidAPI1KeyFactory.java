package org.allbinary.game.input;


public class AndroidAPI1KeyFactory extends AndroidKeyFactory
{
    private static final AndroidAPI1KeyFactory SINGLETON = new AndroidAPI1KeyFactory();

    protected AndroidAPI1KeyFactory()
    {
 
    }
    
    public static final AndroidAPI1KeyFactory getInstance()
    {
        return SINGLETON;
    }

}
