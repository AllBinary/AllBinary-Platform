/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.NullImage;

import org.allbinary.graphics.form.item.ABCustomImageItem;
import org.allbinary.logic.java.character.CharArrayFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.logic.math.PrimitiveLongUtil;

/**
 *
 * @author user
 */
public class TechnologyRTSInterfaceImageItem extends ABCustomImageItem 
    implements UpdateMyFontInterface
{   
    private final RTSInterface rtsInterface;

    private final PrimitiveLongUtil primitiveLongUtil = PrimitiveLongUtil.createPowerOfTen(10000);

    private final String LEVEL = "Level";
    private final String COST = "Cost";
    private final String DOLLAR = "$";

    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    //private String costString = StringUtil.getInstance();
    private char[] costString = CharArrayFactory.getInstance().getZeroCharArray();
    private int costLength;

    //private String levelString = StringUtil.getInstance();
    private char[] levelString = CharArrayFactory.getInstance().getZeroCharArray();
    private int levelLength;

    private int adjustedCostLabelY;
    private int adjustedCostX;
    private int adjustedCostY;

    private int adjustedLevelX;
    private int adjustedLevelY;

    public TechnologyRTSInterfaceImageItem(final String label, final Image img, 
        final int layout, final String altText, final BasicColor basicColor,
        final RTSInterface rtsInterface)
            throws Exception
    {
        super(label, img, layout, altText, basicColor, 0);

        this.rtsInterface = rtsInterface;

        this.update();
    }


    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        final int fontHeight = font.getHeight();
        
        int imageHeight = 0;
        final Image image = this.getImage();
        if (image != NullImage.NULL_IMAGE)
        {
            imageHeight = image.getHeight();
        }

        this.adjustedCostLabelY = -this.yOffset + imageHeight - (3 * fontHeight);

        this.adjustedCostY = -this.yOffset + imageHeight - (2 * fontHeight);
        this.adjustedCostX = 2 + (this.DOLLAR.length() * (fontHeight - 1));

        this.adjustedLevelY = -this.yOffset + imageHeight - fontHeight;
        this.adjustedLevelX = 2 + (this.LEVEL.length() * (fontHeight - 1));
        
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
        
    /**
     * @return the rtsInterface
     */
    public RTSInterface getRtsInterface()
    {
        return this.rtsInterface;
    }

    public void update()
    {
        this.costString
                = this.primitiveLongUtil.getCharArray(
                        //this.primitiveLongUtil.getString(
                        this.getRtsInterface().getUpgradeCost());

        this.levelString
                = this.primitiveLongUtil.getCharArray(
                        //this.primitiveLongUtil.getString(
                        this.getRtsInterface().getLevel());
    }

    @Override
    public void paintXY(final Graphics graphics, final int x, final int y)
    {
        this.myFontProcessor.process(graphics);

        super.paintXY(graphics, x, y);

        int xa = x + 2;
        graphics.drawString(this.COST, xa, y + this.adjustedCostLabelY, 0);

        graphics.drawString(this.DOLLAR, xa, y + this.adjustedCostY, 0);

        graphics.drawChars(this.costString, 0, this.costLength,
                x + this.adjustedCostX, y + this.adjustedCostY, 0);

        graphics.drawString(this.LEVEL, xa, y + this.adjustedLevelY, 0);

        graphics.drawChars(this.levelString, 0, this.levelLength,
                x + this.adjustedLevelX, y + this.adjustedLevelY, 0);
    }
}
