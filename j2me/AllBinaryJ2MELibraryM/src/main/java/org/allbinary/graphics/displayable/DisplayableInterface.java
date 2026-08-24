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
package org.allbinary.graphics.displayable;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import jsinterop.annotations.JsMethod;


@JsType
public interface DisplayableInterface
{
   @JsMethod
   void addCommand(Command cmd);
   @JsMethod
   int getHeight();
   //Ticker getTicker();
   //String getTitle();
   @JsMethod
   int getWidth();
   //boolean isShown();
   @JsMethod
   void removeCommand(Command cmd);
   @JsMethod
   void setCommandListener(CommandListener l);
   //void setTicker(Ticker ticker);
   //void setTitle(String s);
   //void sizeChanged(int w, int h);
}
