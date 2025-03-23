/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
 *
 */
package org.allbinary.graphics.displayable.screen;

import java.util.Stack;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;

import org.allbinary.canvas.Processor;
import org.allbinary.game.displayable.canvas.MenuListener;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.command.MyCommandInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class CommandForm extends Form
        implements MyCommandInterface, MenuListener
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Processor repaintProcessor =
            ScreenRepaintProcessorFactory.getInstance().getInstance(this);
    
   private Stack commandStack;
   
   public CommandForm(CommandListener commandListener, String formTitle,
           BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
   {
      super(formTitle);
            
      this.commandStack = new Stack();
      
       try {

           repaintProcessor.process();

       } catch (Exception e) {
           LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
       }
      
   }

    public void open()
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "open"));
    }

    public void close() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "close"));
    }

    public void update() throws Exception {
        this.repaintProcessor.process();
    }
        
    public int getSourceId()
    {
        return 0;
    }

    public void addCommand(Command command)
    {
       commandStack.push(command);
       super.addCommand(command);
    }
   
   public void removeAllCommands()
   {      
      for(int index = 0; index <commandStack.size(); index++)
      {
         super.removeCommand((Command) commandStack.pop());
      }
   }
}
