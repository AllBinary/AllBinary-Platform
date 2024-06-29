package org.allbinary.game.configuration.feature;

import javax.microedition.lcdui.Canvas;

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
    
    public void addCommands(final Canvas canvas) {
    }        

}
