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
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.mapping.InputMappingInterface;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.game.input.mapping.PersistentInputMapping;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.paint.ColorFillBasePaintable;
import org.allbinary.game.paint.ColorFillPaintableFactory;
import org.allbinary.game.paint.help.HelpPaintable;
import org.allbinary.game.paint.help.InputMappingHelpPaintable;
import org.allbinary.graphics.paint.ProcessPaintable;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.util.BasicArrayList;

public class GameInputMappingCanvas extends GameCommandCanvas
implements InputMappingInterface
{
   
    private final ProcessPaintable paintable;

    public static final String NAME = "GameInputMappingCanvas";
    
    //Input Mapping
    public static final Command DISPLAY  = new Command("Controls", Command.SCREEN, 2);
    public static final Command DEFAULT  = new Command("Default", Command.SCREEN, 2);
    
    private final InputMappingHelpPaintable helpPaintable;
    
    protected final ColorFillBasePaintable colorFillPaintable;
    
    private final PersistentInputMapping inputMapping;

    private final GameKey NONE = GameKeyFactory.getInstance().NONE;
    
    private final AbeClientInformationInterface abeClientInformation;
    
    private GameKey selectedGameKey = this.NONE;
    private Input selectedInput = this.NONE;
    
    public GameInputMappingCanvas(final AbeClientInformationInterface abeClientInformation,
        final CommandListener commandListener,
        final AllBinaryGameLayerManager allBinaryGameLayerManager,
        final HelpPaintable helpPaintable) throws Exception
    {
        super(commandListener, NAME,
                allBinaryGameLayerManager.getBackgroundBasicColor(),
                allBinaryGameLayerManager.getForegroundBasicColor());

        this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);

        if(helpPaintable == null)
        {
        	throw new Exception("Help Paintable Exception");
        }
    
        this.abeClientInformation = abeClientInformation;
        
        this.helpPaintable = (InputMappingHelpPaintable) helpPaintable;
        
        this.inputMapping = PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance();
        
  //      DemoGameMidletEventHandler.getInstance().fireEvent(
    ///            new DemoGameMidletEvent(this,
       //                 DemoGameMidletStateFactory
         //                       .getInstance().START_INPUT_MAPPING));
     
        this.paintable = new ProcessPaintable();
            //TouchButtonsPaintableCompositeFactory.getInstance(
              //      this, allBinaryGameLayerManager.getForegroundBasicColor());
        
        this.colorFillPaintable = 
            ColorFillPaintableFactory.getInstance().getInstance(
                    allBinaryGameLayerManager.getBackgroundBasicColor(), false);
    }

    @Override
    public void close() throws Exception
    {
        super.close();
        
        this.paintable.process();
        
     //   DemoGameMidletEventHandler.getInstance().fireEvent(
       //         new DemoGameMidletEvent(this,
         //               DemoGameMidletStateFactory
           //                     .getInstance().START_DEMO));
        
        this.selectedGameKey = this.NONE;
        this.selectedInput = this.NONE;
        
        this.update();
    }
    
    @Override
    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);
        this.addCommand(GameInputMappingCanvas.DEFAULT);
        this.addCommand(GameInputMappingInstructionsCanvas.DISPLAY);

        this.setCommandListener(cmdListener);
    }

    @Override
    public void keyPressed(final int keyCode)
    {
        this.keyPressedByDevice(keyCode, 0);
    }
    
    @Override
    public void keyReleased(final int keyCode)
    {
        this.keyReleasedByDevice(keyCode, 0);
    }

    @Override
    public void keyRepeated(final int keyCode)
    {
        this.keyRepeatedByDevice(keyCode, 0);
    }
    
    @Override
    public void keyPressedByDevice(final int keyCode, final int deviceId)
    {
        // this.logUtil.putF(this.commonStrings.START, this, gameInputStrings.KEY_PRESSED);        
        this.addGameKeyEvent(keyCode, false);

        super.keyPressedByDevice(keyCode, 0);
    }

    private final InputFactory inputFactory = InputFactory.getInstance();
    
    private void addGameKeyEvent(final int keyCode, final boolean repeated)
    {
        try
        {
            this.logUtil.putF(new StringMaker().append("Raw Device Key Code: ").append(Integer.toHexString(keyCode)).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);

            GameKey gameKey = this.inputToGameKeyMapping.getInstanceForCanvas(this, keyCode);

            //TWB - Hack for MicroEmulator negative values on directional controls
            Input input = this.inputFactory.getInstanceById(keyCode);

            this.process(gameKey, input);
        }
        catch (Exception e)
        {
            this.logUtil.put("Key Event Error", this, this.gameInputStrings.ADD_KEY_EVENT, e);
        }
    }
    
    @Override
    public void process(final GameKey gameKey, final Input input) throws Exception
    {
        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Start Passed GameKey: ");
        stringBuffer.append(this.stringUtil.toString(gameKey));
        stringBuffer.append(" Input: ");
        stringBuffer.append(this.stringUtil.toString(input));
        
        this.logUtil.putF(stringBuffer.toString(), this, this.commonStrings.PROCESS);
        
        if (this.selectedGameKey != this.NONE)
        {
            this.gameActionCrud(gameKey, input);
        }
        else
        {
            this.setSelectedAction(gameKey);
        }
    }

    private void setSelectedAction(final GameKey gameKey)
    {
        this.logUtil.putF(new StringMaker().append("Selected GameKey: ").append(this.stringUtil.toString(gameKey)).toString(), this, "setSelectedAction");
        
        this.selectedGameKey = gameKey;
        this.selectedInput = this.NONE;
        this.helpPaintable.update(this.selectedGameKey, this.selectedInput);
        this.repaintBehavior.onChangeRepaint(this);
    }

    private void gameActionCrud(final GameKey gameKey, final Input input) throws Exception
    {
        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("Start GameKey: ");
        stringBuffer.append(this.stringUtil.toString(this.selectedGameKey));
        stringBuffer.append(" Input: ");
        stringBuffer.append(this.stringUtil.toString(this.selectedInput));
        
        this.logUtil.putF(stringBuffer.toString(), this, "gameActionCrud");

        // If action is selected and input is not then select the input
        // if it is mapped for the selected action
        if (this.selectedInput == this.NONE)
        {
            BasicArrayList list = this.inputMapping.getInputMapping().getMappedInput(this.selectedGameKey);
            boolean isInputAlreadyMappedToSelectedAction = list.contains(input);
            
            if (isInputAlreadyMappedToSelectedAction)
            {
                this.logUtil.putF(new StringMaker().append("Already Mapped Input: ").append(this.stringUtil.toString(input)).toString(), this, "gameActionCrud");

                this.selectedInput = input;
                this.helpPaintable.update(this.selectedGameKey, this.selectedInput);
                this.repaintBehavior.onChangeRepaint(this);
            }
            else
            // Add new mapping
            {
                this.addNewMapping(gameKey, input);
            }
        }
        else
        // If deleting a current mapping
        if (this.inputMapping.isDelete(input))
        {
            this.deleteCurrentMapping();
        }
        else
        {
            this.setSelectedAction(gameKey);
        }
    }

    private void addNewMapping(final GameKey gameKey, final Input input) throws Exception
    {
        final String METHOD_NAME = "addNewMapping";
            
        //this.logUtil.putF(this.commonStrings.START_LABEL).append("Dissallow if ").append(input).append(" is in { ").append(AndroidKeyFactory.getInstance().MENU).append(" }", this, "addNewMapping");
        this.logUtil.putF(this.commonStrings.START, this, METHOD_NAME);

        boolean isInputAlreadyMapped = this.inputMapping.getInputMapping().isMapped(input);

        if (!isInputAlreadyMapped && !this.inputMapping.isSystemInput(input))
        {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Add Key Mapping : GameKey: ");
            stringBuffer.append(this.stringUtil.toString(this.selectedGameKey));
            stringBuffer.append(" Input: ");
            stringBuffer.append(this.stringUtil.toString(this.selectedInput));
            
            this.logUtil.putF(stringBuffer.toString(), this, METHOD_NAME);
            
            this.inputMapping.getInputMapping().add(this.selectedGameKey, input);
            this.selectedInput = input;
            this.update();
        }
        else
        {
            this.logUtil.putF("Unable to add Mapping since one already exists or is MENU, HOME, or BACK key and setting selected action to what it is already mapped to", this, METHOD_NAME);
            this.setSelectedAction(gameKey);
        }
    }

    private void deleteCurrentMapping() throws Exception
    {
        final String METHOD_NAME = "deleteCurrentMapping";

        BasicArrayList list = this.inputMapping.getInputMapping().getMappedInput(this.selectedGameKey);

        if (list.size() > 1)
        {
            final StringMaker stringBuffer = new StringMaker();

            stringBuffer.append("Start GameKey: ");
            stringBuffer.append("Remove Key Mapping: GameKey: ");
            stringBuffer.append(this.stringUtil.toString(this.selectedGameKey));
            stringBuffer.append(" Input: ");
            stringBuffer.append(this.stringUtil.toString(this.selectedInput));
            
            this.logUtil.putF(stringBuffer.toString(), this, METHOD_NAME);

            this.inputMapping.getInputMapping().remove(this.selectedGameKey, this.selectedInput);
            this.selectedInput = this.NONE;
            this.update();
        }
        else
        {
            this.logUtil.putF("Can't Remove Last Key Mapping", this, METHOD_NAME);
        }
    }
    
    public void setDefault() throws Exception
    {
        this.inputMapping.setDefault(this.abeClientInformation);
                //(InputToGameKeyMapping) PlatformInputMappingFactory.getInstance());
        this.helpPaintable.update(this.NONE, this.NONE);
        this.repaintBehavior.onChangeRepaint(this);
    }
    
    @Override
    public void update() throws Exception
    {
        this.inputMapping.update(this.abeClientInformation);
                //(InputToGameKeyMapping) PlatformInputMappingFactory.getInstance());
        this.helpPaintable.update(this.selectedGameKey, this.selectedInput);
        this.repaintBehavior.onChangeRepaint(this);
    }

    @Override
    public void paint(final Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);
        
        this.helpPaintable.paint(graphics);
        
        this.paintable.paint(graphics);

        super.paint(graphics);
    }
}
