/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
package org.allbinary.graphics.displayable.screen;

import java.util.Stack;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.NullCommandListener;

import org.allbinary.canvas.Processor;
import org.allbinary.game.displayable.canvas.MenuListener;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.command.MyCommandInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class CommandForm extends Form
        implements MyCommandInterface, MenuListener
{
    public static final CommandForm NULL_COMMAND_FORM = new CommandForm(
        NullCommandListener.NULL_COMMAND_LISTENER, StringUtil.getInstance().EMPTY_STRING,
           BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE);

    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Processor repaintProcessor =
            ScreenRepaintProcessorFactory.getInstance().getInstance(this);
    
   private Stack<Object> commandStack;
   
   public CommandForm(CommandListener commandListener, String formTitle,
           BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
   {
      super(formTitle);
            
      this.commandStack = new Stack<Object>();
      
       try {

           repaintProcessor.process();

       } catch (Exception e) {
           logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
       }
      
   }

    @Override
    public void open()
    {
        logUtil.put(this.commonStrings.START, this, "open");
    }

    @Override
    public void close() throws Exception
    {
        logUtil.put(this.commonStrings.START, this, commonStrings.CLOSE);
    }

    public void update() throws Exception {
        this.repaintProcessor.process();
    }
    
    @Override    
    public int getSourceId()
    {
        return 0;
    }

    @Override
    public void addCommand(Command command)
    {
       commandStack.push(command);
       super.addCommand(command);
    }
   
    @Override
   public void removeAllCommands()
   {      
      for(int index = 0; index <commandStack.size(); index++)
      {
         super.removeCommand((Command) commandStack.pop());
      }
   }
}
