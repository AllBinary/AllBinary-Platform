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

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.form.item.CustomImageItem;
import org.allbinary.logic.java.character.CharArrayFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.math.PrimitiveLongUtil;

/**
 *
 * @author user
 */
public class TechnologyRTSInterfaceImageItem extends CustomImageItem
{
    private final MyFont myFont = MyFont.getInstance();
    
    private final RTSInterface rtsInterface;

    private final int adjustedCostLabelY;
    private final int adjustedCostX;
    private final int adjustedCostY;

    //private String costString = StringUtil.getInstance();
    private char[] costString
            = CharArrayFactory.getInstance().getZeroCharArray();
    private int costLength;

    private final int adjustedLevelX;
    private final int adjustedLevelY;

    //private String levelString = StringUtil.getInstance();
    private char[] levelString
            = CharArrayFactory.getInstance().getZeroCharArray();
    private int levelLength;

    private final PrimitiveLongUtil primitiveLongUtil = new PrimitiveLongUtil(10000);

    private final String LEVEL = "Level";
    private final String COST = "Cost";
    private final String DOLLAR = "$";

    public TechnologyRTSInterfaceImageItem(
            String label, Image img, int layout, String altText, BasicColor basicColor,
            RTSInterface rtsInterface)
            throws Exception
    {
        super(label, img, layout, altText, basicColor);

        this.rtsInterface = rtsInterface;

        final int DEFAULT_CHAR_HEIGHT = myFont.DEFAULT_CHAR_HEIGHT;

        int imageHeight = 0;

        final Image image = this.getImage();
        if (image != null)
        {
            imageHeight = image.getHeight();
        }

        this.adjustedCostLabelY
                = -yOffset + imageHeight - (3 * DEFAULT_CHAR_HEIGHT);

        this.adjustedCostY
                = -yOffset + imageHeight - (2 * DEFAULT_CHAR_HEIGHT);
        this.adjustedCostX
                = 2 + (DOLLAR.length() * (DEFAULT_CHAR_HEIGHT - 1));

        this.adjustedLevelY
                = -yOffset + imageHeight - DEFAULT_CHAR_HEIGHT;
        this.adjustedLevelX
                = 2 + (LEVEL.length() * (DEFAULT_CHAR_HEIGHT - 1));

        this.update();
    }

    /**
     * @return the rtsInterface
     */
    public RTSInterface getRtsInterface()
    {
        return rtsInterface;
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
    public void paint(Graphics graphics, int x, int y)
    {
        super.paint(graphics, x, y);

        int xa = x + 2;
        graphics.drawString(COST, xa, y + this.adjustedCostLabelY, 0);

        graphics.drawString(DOLLAR, xa, y + this.adjustedCostY, 0);

        graphics.drawChars(costString, 0, this.costLength,
                x + this.adjustedCostX, y + this.adjustedCostY, 0);

        graphics.drawString(LEVEL, xa, y + this.adjustedLevelY, 0);

        graphics.drawChars(levelString, 0, this.levelLength,
                x + this.adjustedLevelX, y + this.adjustedLevelY, 0);
    }
}
