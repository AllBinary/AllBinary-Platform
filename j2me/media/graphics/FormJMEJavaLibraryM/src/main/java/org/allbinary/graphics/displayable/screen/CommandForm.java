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
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.displayable.canvas.MenuListener;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.command.MyCommandInterface;

public class CommandForm extends Form
        implements MyCommandInterface, MenuListener
{
   private Stack commandStack;
   
   public CommandForm(CommandListener commandListener, String formTitle,
           BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
   {
      super(formTitle);
      this.commandStack = new Stack();
   }

    public void open()
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "open"));
    }

    public void close() throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "close"));
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
