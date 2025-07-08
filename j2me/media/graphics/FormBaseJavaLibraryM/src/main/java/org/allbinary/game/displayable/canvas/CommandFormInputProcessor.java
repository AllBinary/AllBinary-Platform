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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.layer.SWTUtil;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.form.CommandCurrentSelectionForm;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.input.motion.gesture.TouchMotionGestureFactory;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.media.audio.PrimaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;
import org.allbinary.thread.PrimaryThreadPool;
import org.allbinary.time.TimeDelayHelper;

//In general allow scrolling of the menu and selection the center item
public class CommandFormInputProcessor extends BasicMenuInputProcessor
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final GameInputStrings gameInputStrings = GameInputStrings.getInstance();

   private final int MOTION_GESTURE_SOURCE_ID = GameKeyEventFactory.getInstance().MOTION_GESTURE_SOURCE_ID;
    
   private final int CLICK_DELAY = 150;
   private final TimeDelayHelper clickTimeHelper = new TimeDelayHelper(CLICK_DELAY);
   private final int DOUBLE_CLICK_DELAY = 1200;
   private final TimeDelayHelper doubleClickTimeHelper = new TimeDelayHelper(DOUBLE_CLICK_DELAY);

   protected final boolean isSingleKeyProcessing = 
       InputFeatureFactory.getInstance().isSingleKeyProcessing();

   private ScrollSelectionForm form;
   private boolean hasPressed = false;

   public CommandFormInputProcessor(final BasicArrayList gameKeyEventList,
           final int playerInputId, 
           final MyCanvas gameCanvas, final ScrollSelectionForm form)
   {
      super(gameKeyEventList, playerInputId, gameCanvas);

      this.form = form;
   }

   public int processInput(final int key) throws Exception
   {
      //logUtil.put(new StringMaker().append(commonStrings.START).append("Canvas.").append(CanvasUtil.getKeyName(key)).toString(), this, GameInputStrings.getInstance().PROCESS_INPUT);

      if (key == Canvas.LEFT || key == Canvas.RIGHT || key == Canvas.UP || key == Canvas.DOWN)
      {
         //PreLogUtil.put(new StringMaker().append("Key: ").append(key).toString(), this, GameInputStrings.getInstance().PROCESS_INPUT);

         //ForcedLogUtil.log();

         PrimaryPlayerQueueFactory.getInstance().add(SelectSound.getInstance());

         this.form.processInput(key);

         return 1;
      } // else if (GameKeyUtil.isNonDirectionKey(key))
      else if (key == Canvas.FIRE)
      {
         //ForcedLogUtil.log();

         PrimaryPlayerQueueFactory.getInstance().add(
                 SelectSound.getInstance());

         // logUtil.put("Key: ").append(key, this, GameInputStrings.getInstance());
         return this.processCommand();
      }
      return 0;
   }

   private final String PROCESS_COMMAND = "processCommand";
   private int processCommand()
   {
      final CommandCurrentSelectionForm commandCurrentSelectionForm = (CommandCurrentSelectionForm) this.form;

      final Command command = commandCurrentSelectionForm.getSelectedCommand();

      //"Command: "
      logUtil.put(command.toString(), this, PROCESS_COMMAND);

      final Features features = Features.getInstance();
      final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();
        
      if(SWTUtil.isSWT && features.isFeature(openGLFeatureFactory.OPENGL) && command != GameCommandsFactory.getInstance().EXIT_COMMAND) {
          new CommandRunnable(this, command).run();
      } else {
          PrimaryThreadPool.getInstance().runTask(new CommandRunnable(this, command));
      }

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
         //PreLogUtil.put(commonStrings.START, this, GameInputStrings.getInstance().PROCESS_INPUT);

         final int motionInputsIndex = this.processMotionInputs();

         final BasicArrayList list = this.getGameKeyEventList();
         
         final int size = list.size();
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

            //logUtil.put(commonStrings.START, this, "processInput - GameKeyEvent source: ").append(gameKeyEvent.getSourceId());
            // logUtil.put("commonStrings.START_LABEL).append("Canvas." +
            // CanvasUtil.getKeyName(key), this, GameInputStrings.getInstance());
            if(gameKeyEvent.getSourceId() != MOTION_GESTURE_SOURCE_ID)
            {
                if (this.processInput(key) == 1) {
                    break;
                }
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
         logUtil.put(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(e.getMessage()).toString(), this, GameInputStrings.getInstance().PROCESS_INPUT);
         
         return -1;
      }
   }

   // AllBinaryLayerManager layerManager
   public int processMotionInputs() throws Exception
   {
       //PreLogUtil.put(commonStrings.START, this, "processMotionInputs");
       
      final int lastIndex = this.motionGestureEventList.size() - 1;

      if (lastIndex >= 0)
      {
         final MotionGestureEvent motionGestureEvent =
                 (MotionGestureEvent) this.motionGestureEventList.objectArray[lastIndex];

         this.processMotionInput(motionGestureEvent);
      }

      motionGestureEventList.clear();

      return lastIndex;
   }

   protected void processMotionInput(final MotionGestureEvent motionGestureEvent)
           throws Exception
   {
       //logUtil.put(new StringMaker().append(Thread.currentThread().getName()).append(commonStrings.START).append(motionGestureEvent).toString(), this, gameInputStrings.PROCESS_MOTION_INPUT);
       //logUtil.put(new StringMaker().append(commonStrings.START).append(motionGestureEvent).toString(), this, gameInputStrings.PROCESS_MOTION_INPUT);
       //PreLogUtil.put(commonStrings.START, this, gameInputStrings.PROCESS_MOTION_INPUT);
       
      final TouchMotionGestureFactory touchMotionGestureFactory = TouchMotionGestureFactory.getInstance();
      final MotionGestureInput motionGestureInput = motionGestureEvent.getMotionGesture();

      if (motionGestureInput == touchMotionGestureFactory.RELEASED)
      {
         final GPoint point = motionGestureEvent.getCurrentPoint();
         if (this.form.isInForm(point))
         {
            final int index = this.form.getSelectedIndex(point);

            if (index != -1)
            {
               //logUtil.put("Form Selected Index: ").append(index, this, gameInputStrings.PROCESS_MOTION_INPUT);

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
            } else {
                //logUtil.put("No Form Selected Index: ").append(index, this, gameInputStrings.PROCESS_MOTION_INPUT);
            }
         }

         // logUtil.put("No Double Press Time: ").append(this.doubleClickTimeHelper.getElapsed(), this, gameInputStrings.PROCESS_MOTION_INPUT);

         if(this.hasPressed) {
             if (!this.doubleClickTimeHelper.isTime()) {
                 logUtil.put("Double Press", this, gameInputStrings.PROCESS_MOTION_INPUT);
                 this.processCommand();
             }

             this.doubleClickTimeHelper.delay = DOUBLE_CLICK_DELAY;
             this.doubleClickTimeHelper.setStartTime();
         }
         this.hasPressed = false;
      } else if (motionGestureInput == touchMotionGestureFactory.PRESSED)
      {
         // Can't be a double click/press if dragging or other
         this.doubleClickTimeHelper.delay = 0;
         this.hasPressed = true;
      }
   }
   
    private final String NAME_LABEL = " ScrollSelectionForm: ";
    
    public String toString()
    {
        return new StringMaker()
                .append(super.toString())
                .append(NAME_LABEL)
                .append(this.form.toString())
                .toString();
    }
}
