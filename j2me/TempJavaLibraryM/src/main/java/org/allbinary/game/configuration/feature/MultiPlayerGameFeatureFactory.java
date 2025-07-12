package org.allbinary.game.configuration.feature;

public class MultiPlayerGameFeatureFactory
{
    private static final MultiPlayerGameFeatureFactory instance = new MultiPlayerGameFeatureFactory();
    
    public static MultiPlayerGameFeatureFactory getInstance()
    {
        return instance;
    }
    
    private MultiPlayerGameFeatureFactory()
    {
        
    }
    
    //final Canvas canvas
    public void addCommands(final Object object) {
    }        

}
