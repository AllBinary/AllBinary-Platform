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
package org.allbinary.game.displayable.canvas;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.ItemStateListener;

import org.allbinary.game.state.GameStateCompositeInterface;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.thread.RunnableInterface;
import jsinterop.annotations.JsMethod;
import org.allbinary.util.ABHashtable;


@JsType
public interface GameCanvasRunnableInterface 
   extends RunnableInterface, PaintableInterface, ItemStateListener, GameStateCompositeInterface
{
   @JsMethod
   String getTitle();
   
   @JsMethod
   boolean isInitialized();
   @JsMethod
   boolean isPaused();
   @JsMethod
   void unPause();
   @JsMethod
   void pause();
   @JsMethod
   boolean isHighScoreSubmitted();
   @JsMethod
   void setHighScoreSubmitted(boolean highScoreSubmitted) throws Exception;
   @JsMethod
   void processGameOver() throws Exception;
   @JsMethod
   boolean isGameOver();
   @JsMethod
   void initCommands(CommandListener cmdListener);
   @JsMethod
   ABHashtable getCurrentStateHashtable()throws Exception;
   @JsMethod
   ABHashtable getLoadStateHashtable()throws Exception;
   @JsMethod
   void setLoadStateHashtable(ABHashtable hashtable) throws Exception;
   //void addCommand(Command command);
   
   @JsMethod
   int getType();
}
