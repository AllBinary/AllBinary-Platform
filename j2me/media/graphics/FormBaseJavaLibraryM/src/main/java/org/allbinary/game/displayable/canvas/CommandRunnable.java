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

import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

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

   @Override
   public void run()
   {
      try
      {
         this.logUtil.putF(commonStrings.START_RUNNABLE, this, commonStrings.RUN);

         final MyCanvas canvas = this.commandFormInputProcessor.getCanvas();

         final CommandListener commandListener = canvas.getCustomCommandListener();

         commandListener.commandAction(this.command, canvas);

         this.logUtil.putF(commonStrings.END_RUNNABLE, this, commonStrings.RUN);
         
      } catch (Exception e)
      {
         this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
      }

   }
}
