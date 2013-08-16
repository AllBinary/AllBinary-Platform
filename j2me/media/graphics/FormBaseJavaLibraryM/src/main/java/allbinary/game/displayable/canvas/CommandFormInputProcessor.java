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
package allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.configuration.feature.InputFeatureFactory;
import allbinary.game.input.GameInputStrings;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.graphics.GPoint;
import allbinary.graphics.displayable.MyCanvas;
import allbinary.graphics.form.CommandCurrentSelectionForm;
import allbinary.graphics.form.ScrollSelectionForm;
import allbinary.input.motion.gesture.MotionGestureInput;
import allbinary.input.motion.gesture.TouchMotionGestureFactory;
import allbinary.input.motion.gesture.observer.MotionGestureEvent;
import allbinary.media.audio.PrimaryPlayerQueueFactory;
import allbinary.media.audio.SelectSound;
import allbinary.thread.PrimaryThreadPool;
import allbinary.time.TimeDelayHelper;

//In general allow scrolling of the menu and selection the center item
public class CommandFormInputProcessor extends BasicMenuInputProcessor
{
   private final int CLICK_DELAY = 150;
   private TimeDelayHelper clickTimeHelper = new TimeDelayHelper(CLICK_DELAY);
   private final int DOUBLE_CLICK_DELAY = 1200;
   private TimeDelayHelper doubleClickTimeHelper =
           new TimeDelayHelper(DOUBLE_CLICK_DELAY);

   protected final boolean isSingleKeyProcessing = 
       InputFeatureFactory.getInstance().isSingleKeyProcessing();

   private ScrollSelectionForm form;

   public CommandFormInputProcessor(BasicArrayList gameKeyEventList,
           MyCanvas gameCanvas, ScrollSelectionForm form)
   {
      super(gameKeyEventList, gameCanvas);

      this.form = form;
   }

   public int processInput(int key) throws Exception
   {
      // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + "Canvas." +
      // CanvasUtil.getKeyName(key), this, GameInputStrings.getInstance()));

      if (key == Canvas.LEFT || key == Canvas.RIGHT || key == Canvas.UP || key == Canvas.DOWN)
      {
          //PreLogUtil.put("Key: " + key, this, GameInputStrings.getInstance());

         //ForcedLogUtil.log();

         PrimaryPlayerQueueFactory.getInstance().add(
                 SelectSound.getInstance());

         this.form.processInput(key);

         return 1;
      } // else if (GameKeyUtil.isNonDirectionKey(key))
      else if (key == Canvas.FIRE)
      {
         //ForcedLogUtil.log();

         PrimaryPlayerQueueFactory.getInstance().add(
                 SelectSound.getInstance());

         // LogUtil.put(LogFactory.getInstance("Key: " + key, this, GameInputStrings.getInstance()));
         return this.processCommand();
      }
      return 0;
   }

   private final String PROCESS_COMMAND = "processCommand";
   private int processCommand()
   {
      CommandCurrentSelectionForm commandCurrentSelectionForm = (CommandCurrentSelectionForm) this.form;

      final Command command = commandCurrentSelectionForm.getSelectedCommand();

      //"Command: "
      LogUtil.put(LogFactory.getInstance(command.toString(), this, PROCESS_COMMAND));

      PrimaryThreadPool.getInstance().runTask(new CommandRunnable(this, command));

      if (command == GameCommandsFactory.getInstance().QUIT_COMMAND)
      {
         return -1;
      } else
      {
         return 1;
      }
   }

   public int processInput() throws Exception
   {
      try
      {
         //PreLogUtil.put(CommonStrings.getInstance().START, this, GameInputStrings.getInstance());

         int motionInputsIndex = this.processMotionInputs();

         BasicArrayList list = this.getGameKeyEventList();
         
         int size = list.size();
         int key = 0;

         GameKeyEvent gameKeyEvent;
         
         for (int index = 0; index < size; index++)
         {
            gameKeyEvent = (GameKeyEvent) list.objectArray[index];
            key = gameKeyEvent.getKey();

            /*
             * if (key == Canvas.UP || key == Canvas.DOWN || key ==
             * Canvas.RIGHT || key == Canvas.LEFT) {
             * this.addForRemoval(gameKeyEvent); }
             */

            // LogUtil.put(LogFactory.getInstance("CommonStrings.getInstance().START_LABEL + "Canvas." +
            // CanvasUtil.getKeyName(key), this, GameInputStrings.getInstance()));
            if (this.processInput(key) == 1)
            {
               break;
            }
         }

         this.clear();

         /*
          * if (isSingleKeyProcessing) { this.clear(); } else {
          * this.update(); }
          */

         if (size > 0 || motionInputsIndex >= 0)
         {
            return 1;
         } else
         {
            return -1;
         }
      } catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION_LABEL + e.getMessage(), this, GameInputStrings.getInstance().PROCESS_INPUT));
         
         return -1;
      }
   }

   // AllBinaryLayerManager layerManager
   public int processMotionInputs() throws Exception
   {
       //PreLogUtil.put(CommonStrings.getInstance().START, this, "processMotionInputs");
       
      int lastIndex = this.motionGestureEventList.size() - 1;

      if (lastIndex >= 0)
      {
         MotionGestureEvent motionGestureEvent =
                 (MotionGestureEvent) this.motionGestureEventList.objectArray[lastIndex];

         this.processMotionInput(motionGestureEvent);
      }

      motionGestureEventList.clear();

      return lastIndex;
   }

   protected void processMotionInput(MotionGestureEvent motionGestureEvent)
           throws Exception
   {
       //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "processMotionInput"));
       //PreLogUtil.put(CommonStrings.getInstance().START, this, "processMotionInput");
       
      MotionGestureInput motionGestureInput =
              motionGestureEvent.getMotionGesture();

      if (motionGestureInput == TouchMotionGestureFactory.getInstance().RELEASED)
      // ||
      // motionGestureInput ==
      // TouchMotionGestureFactory.getInstance().PRESSED)
      {
         GPoint point = motionGestureEvent.getCurrentPoint();
         if (this.form.isInForm(point))
         {
            int index = this.form.getSelectedIndex(point);

            if (index != -1)
            {
               //LogUtil.put(LogFactory.getInstance("Form Selected Index: " + index, this, "processMotionInput"));

               PrimaryPlayerQueueFactory.getInstance().add(
                       SelectSound.getInstance());

               if (index == this.form.getSelectedIndex())
               {
                  if (this.clickTimeHelper.isTime())
                  {
                     this.processCommand();
                  }
               } else
               {
                  this.form.setSelectedIndex(index);
               }
            }
         }

         // LogUtil.put(LogFactory.getInstance("No Double Press Time: " + this.doubleClickTimeHelper.getElapsed(), this, "processMotionInput"));

         if (!this.doubleClickTimeHelper.isTime())
         {
             LogUtil.put(LogFactory.getInstance("Double Press", this, "processMotionInput"));
             this.processCommand();
         }

         this.doubleClickTimeHelper.setDelay(DOUBLE_CLICK_DELAY);
         this.doubleClickTimeHelper.setStartTime();
      } else
      {
         // Can't be a double click/press if dragging or other
         this.doubleClickTimeHelper.setDelay(0);
      }
   }
}
