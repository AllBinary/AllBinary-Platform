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
package org.allbinary.game.displayable.screen;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.form.item.CustomTextBox;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.SimpleTextPaintable;
import org.allbinary.input.event.VirtualKeyboardEventHandler;

public class LicenseRegistrationTextBox 
    extends CustomTextBox 
    //implements MyCommandInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public static final Command DISPLAY_COMMAND = 
        new Command("Register", Command.SCREEN, 3);
    
    public static final Command SUBMIT_COMMAND = 
        new Command("Submit", Command.SCREEN, 3);

    private final Paintable pleaseWaitPaintable = new SimpleTextPaintable(
            commonStrings.PLEASE_WAIT, BasicColorFactory.getInstance().WHITE);

    private Paintable paintable = NullPaintable.getInstance();

   public LicenseRegistrationTextBox(CommandListener cmdListener, 
           BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
   throws Exception
   {
      super(cmdListener, "Enter Registration Code:", StringUtil.getInstance().EMPTY_STRING, 60, TextField.ANY,
               backgrounBasicColor, foregroundBasicColor);
   }

   public void initCommands(CommandListener cmdListener)
   {
       this.removeAllCommands();

       this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);
       this.addCommand(GameCommandsFactory.getInstance().TOGGLE_KEYBOARD);
       this.addCommand(SUBMIT_COMMAND);

       this.setCommandListener(cmdListener);
   }
   
   public void open()
   {
       try
       {

           VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
               VirtualKeyboardEventHandler.getInstance();

           virtualKeyboardEventHandler.fireEvent(virtualKeyboardEventHandler.SHOW_EVENT);

       } catch (Exception e)
       {
           logUtil.put(commonStrings.EXCEPTION, this, "open", e);
       }
       
       super.open();
       
       this.paintable = NullPaintable.getInstance();
       this.repaint();
   }

   public void close() throws Exception
   {
       try
       {
           VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
               VirtualKeyboardEventHandler.getInstance();

           virtualKeyboardEventHandler.fireEvent(
                   virtualKeyboardEventHandler.HIDE_EVENT);
           
       } catch (Exception e)
       {
           logUtil.put(commonStrings.EXCEPTION, this, "open", e);
       }

       this.paintable = this.pleaseWaitPaintable;
       this.repaint();

       super.close();
   }
      
   public void paint(Graphics graphics)
   {
       super.paint(graphics);
       this.paintable.paint(graphics);
   }
   
   public void submit()
   {
       CommandListener commandListener = this.getCustomCommandListener();
       
       commandListener.commandAction(SUBMIT_COMMAND, this);
   }
}
