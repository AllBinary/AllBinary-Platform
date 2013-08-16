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
package allbinary.game.displayable.screen;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.score.displayable.CustomTextBox;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.paint.NullPaintable;
import allbinary.graphics.paint.Paintable;
import allbinary.graphics.paint.SimpleTextPaintable;
import allbinary.input.event.VirtualKeyboardEventHandler;

public class LicenseRegistrationTextBox 
    extends CustomTextBox 
    //implements MyCommandInterface
{
    public static final Command DISPLAY_COMMAND = 
        new Command("Register", Command.SCREEN, 3);
    
    public static final Command SUBMIT_COMMAND = 
        new Command("Submit", Command.SCREEN, 3);

    private final Paintable pleaseWaitPaintable = new SimpleTextPaintable(
            CommonStrings.getInstance().PLEASE_WAIT, BasicColorFactory.getInstance().WHITE);

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
           LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "open", e));
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
           LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "open", e));
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
