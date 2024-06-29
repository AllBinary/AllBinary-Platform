package org.allbinary.game.configuration.feature;

import javax.microedition.lcdui.Canvas;
import org.allbinary.game.commands.GameCommandsFactory;

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
        final GameCommandsFactory gameCommandsFactory = GameCommandsFactory.getInstance();
        canvas.addCommand(gameCommandsFactory.QUIT_COMMAND);
    }        

}
