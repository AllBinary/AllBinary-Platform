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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.paint.ColorFillPaintable;
import allbinary.game.paint.ColorFillPaintableFactory;
import allbinary.graphics.Anchor;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.draw.DrawStringUtil;
import allbinary.graphics.font.MyFont;

public class GameInputMappingInstructionsCanvas extends GameCommandCanvas
{
    public static final Command DISPLAY  = new Command("Help", Command.SCREEN, 1);
    public static final Command CLOSE  = new Command("Close", Command.SCREEN, 1);

    //Mapping
    protected String TITLE = "Input Instructions";
    
    private final String[] instructions =
    { "Add Input Mapping:",
        "1. Select the desired action by generating a currently defined input for a given action.",
        "(Example: Press the '1' key to select the Fire action)",
        "2. Make the newly desired input for the selected action.",
        "(Example: Press 'f' to add it to the selected action)",
        "Note: If the input is already mapped to another action then it will not be added. You",
        "will need to deleted from the other action before adding it to another.",
        StringUtil.getInstance().EMPTY_STRING, 
        "Remove Input Mapping:",
        "1. Select the desired action by generating a currently defined input for a given action.",
        "(Example: Press the key '1' to select the Fire action)",
        "2. Select a mapped input for the selected action.",
        "(Example: Press the key '1' to select the '1' key input for the selected Fire action)",
        "3. Press the Delete Key." };

    private ColorFillPaintable colorFillPaintable;
    
    public GameInputMappingInstructionsCanvas(CommandListener commandListener,
            AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception
    {
        super(commandListener,
                allBinaryGameLayerManager.getBackgroundBasicColor(),
            allBinaryGameLayerManager.getForegroundBasicColor());

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));

        this.colorFillPaintable = 
            ColorFillPaintableFactory.getInstance(
                    allBinaryGameLayerManager.getBackgroundBasicColor());
    }
    
    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameInputMappingInstructionsCanvas.CLOSE);

        this.setCommandListener(cmdListener);
    }

    private int anchor = Anchor.TOP_LEFT;
    
    private final DrawStringUtil drawStringUtil = DrawStringUtil.getInstance();
    
    public void paint(Graphics graphics)
    {
    	PreLogUtil.put(CommonStrings.getInstance().START, this, "paint");
    	
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        this.colorFillPaintable.paint(graphics);

        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        int halfWidth = displayInfo.getLastHalfWidth();

        int beginWidth = (graphics.getFont().stringWidth(this.TITLE) >> 1);

        graphics.setColor(this.foregroundColor);
        
        graphics.drawString(this.TITLE, halfWidth - beginWidth, charHeight, anchor);

        //int startIndex = this.helpPaintable.getInputInfo().length + 5;

        drawStringUtil.drawCenterStrings(graphics, instructions,
                displayInfo.getLastWidth(), halfWidth, 3 * charHeight);

        super.paint(graphics);
    }

}
