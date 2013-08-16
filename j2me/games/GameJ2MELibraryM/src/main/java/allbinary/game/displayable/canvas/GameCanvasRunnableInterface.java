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
package allbinary.game.displayable.canvas;

import java.util.Hashtable;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.ItemStateListener;

import allbinary.canvas.FullScreenInterface;
import allbinary.game.state.GameStateCompositeInterface;
import allbinary.graphics.paint.PaintableInterface;
import allbinary.thread.RunnableInterface;

public interface GameCanvasRunnableInterface 
   extends RunnableInterface, PaintableInterface, ItemStateListener, FullScreenInterface, GameStateCompositeInterface
{
   String getTitle();
   
   boolean isInitialized();
   boolean isPaused();
   void unPause();
   void pause();
   boolean isHighScoreSubmitted();
   void setHighScoreSubmitted(boolean highScoreSubmitted) throws Exception;
   void setGameOver() throws Exception;
   boolean isGameOver();
   void initCommands(CommandListener cmdListener);
   Hashtable getCurrentStateHashtable()throws Exception;
   Hashtable getLoadStateHashtable()throws Exception;
   void setLoadStateHashtable(Hashtable hashtable) throws Exception;
   //void addCommand(Command command);
}
