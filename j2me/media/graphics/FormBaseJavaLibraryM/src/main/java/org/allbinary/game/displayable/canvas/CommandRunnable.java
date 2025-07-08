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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.displayable.MyCanvas;

public class CommandRunnable implements Runnable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final CommandFormInputProcessor commandFormInputProcessor;
    private final Command command;
    
    public CommandRunnable(
            CommandFormInputProcessor commandFormInputProcessor, Command command)
    {
        this.commandFormInputProcessor = commandFormInputProcessor;
        this.command = command;
    }
    
   public void run()
   {
      try
      {
         logUtil.put(commonStrings.START_RUNNABLE, this, commonStrings.RUN);

         final MyCanvas canvas = this.commandFormInputProcessor.getCanvas();

         final CommandListener commandListener = canvas.getCustomCommandListener();

         commandListener.commandAction(command, canvas);

         logUtil.put(commonStrings.END_RUNNABLE, this, commonStrings.RUN);
         
      } catch (Exception e)
      {
         logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
      }

   }
}
