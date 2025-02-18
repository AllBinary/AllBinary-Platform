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
    
    //final Canvas canvas
    public void addCommands(final Object object) {
        final GameCommandsFactory gameCommandsFactory = GameCommandsFactory.getInstance();
        final Canvas canvas = (Canvas) object;
        canvas.addCommand(gameCommandsFactory.QUIT_COMMAND);
    }        

}
