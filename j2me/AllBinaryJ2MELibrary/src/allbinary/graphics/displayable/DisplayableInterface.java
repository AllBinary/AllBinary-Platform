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
package allbinary.graphics.displayable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

public interface DisplayableInterface
{
   void addCommand(Command cmd);
   int getHeight();
   //Ticker getTicker();
   //String getTitle();
   int getWidth();
   //boolean isShown();
   void removeCommand(Command cmd);
   void setCommandListener(CommandListener l);
   //void setTicker(Ticker ticker);
   //void setTitle(String s);
   //void sizeChanged(int w, int h);
}
