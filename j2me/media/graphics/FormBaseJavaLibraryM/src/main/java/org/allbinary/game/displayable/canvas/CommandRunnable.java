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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class CommandRunnable implements Runnable
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final CommandFormInputProcessor commandFormInputProcessor;
    private final Command command;
    
    @JsConstructor
    public CommandRunnable(
            CommandFormInputProcessor commandFormInputProcessor, Command command)
    {
        this.commandFormInputProcessor = commandFormInputProcessor;
        this.command = command;
    }

   @Override
   @JsMethod
   public void run()
   {
      try
      {
         this.logUtil.putF(this.commonStrings.START_RUNNABLE, this, this.commonStrings.RUN);

         final MyCanvas canvas = this.commandFormInputProcessor.getCanvas();

         final CommandListener commandListener = canvas.getCustomCommandListener();

         commandListener.commandAction(this.command, canvas);

         this.logUtil.putF(this.commonStrings.END_RUNNABLE, this, this.commonStrings.RUN);
         
      } catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
      }

   }
}
