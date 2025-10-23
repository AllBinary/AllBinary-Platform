/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;

import org.allbinary.logic.java.character.CharArrayFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.input.motion.button.CommonButtons;

public class UpgradableRTSLayerHudPaintable 
    extends SelectionHudPaintable
{
    private static final UpgradableRTSLayerHudPaintable instance = 
        new UpgradableRTSLayerHudPaintable();
    
    public static UpgradableRTSLayerHudPaintable getInstance()
    {
        return instance;
    }

    //private final String PERCENT_COMPLETE = "% Complete";
    private final String PERCENT = "%";

    private CollidableDestroyableDamageableLayer rtsLayer = CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER;

    protected int costY;
    protected int costY1;
    
    private int percentCompleteX2;
    
    private RTSLayerCompositePaintable rtsLayerCompositePaintableLateInit;
    
    private UpgradableRTSLayerHudPaintable()
    {
    }

    @Override
    public void update()
    {
        super.update();
        
        final MyFont myFont = MyFont.getInstance();

        final int charHeight = myFont.DEFAULT_CHAR_HEIGHT;

        this.costY = (y + CommonButtons.getInstance().STANDARD_BUTTON_SIZE);
        this.costY1 = (y + CommonButtons.getInstance().STANDARD_BUTTON_SIZE - (charHeight));
        /*
         * probably not needed anymore
        if(!AndroidUtil.isAndroid())
        {
            this.costY1 = this.costY;
        } else
        {
            this.costY1 = (y + TouchButtonInput.STANDARD_BUTTON_SIZE - (charHeight));
        }
        */

        this.percentCompleteX2 = this.imageX + CommonButtons.getInstance().STANDARD_BUTTON_SIZE - myFont.charWidth();
        
        final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
                
        //!AndroidUtil.isAndroid()
        if(displayInfoSingleton.getLastWidth() > 320)
        {
            this.rtsLayerCompositePaintableLateInit = new UpgradableWideRTSLayerPaintable(this);
        }
        else
        {
            this.rtsLayerCompositePaintableLateInit = new RTSLayerCompositePaintable(this);
        }        
    }    
    
    @Override
    public void updateSelectionInfo()
    {
        final RTSLayer rtsLayer = (RTSLayer) this.getRtsLayer();
        this.rtsLayerCompositePaintableLateInit.update(rtsLayer);
        
        this.setAnimationInterface(rtsLayer.getVerticleBuildAnimationInterface());

        this.setName(rtsLayer.getName());
    }
    
    private int percentComplete;
    private int percentCompleteX;
    //private String percentComplete = StringUtil.getInstance();
    private char[] percentCompleteArray = CharArrayFactory.getInstance().getZeroCharArray();
    private int currentTotalDigits;
    
    @Override
    public void updateInfo()
    {
        final RTSLayer rtsLayer = (RTSLayer) this.getRtsLayer();
        this.percentComplete = rtsLayer.getPercentComplete();

        if (percentComplete < 10)
        {
            this.percentCompleteX = 32;
        } else if (percentComplete < 100)
        {
            this.percentCompleteX = 24;
        } else
        {
            this.percentCompleteX = 16;
        }
        
        this.percentCompleteArray =
            this.getPrimitiveLongUtil().getCharArray(percentComplete);
            //this.primitiveLongUtil.getString(pc);

        this.currentTotalDigits = 
            this.getPrimitiveLongUtil().getCurrentTotalDigits();
    }
    
    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);

        this.rtsLayerCompositePaintableLateInit.paint(graphics);
        
        graphics.drawChars(
                percentCompleteArray, 0, this.currentTotalDigits, 
                this.imageX + this.percentCompleteX, costY, 0);

        //37
        //(myFont.DEFAULT_CHAR_WIDTH * 4)
        graphics.drawString(this.PERCENT, this.percentCompleteX2, costY, 0);

        /*
        int pc = this.getRtsLayer().getPercentComplete();
        String percentComplete = this.primitiveLongUtil.getString(pc);
        if (pc < 10)
        {
            graphics.drawString(percentComplete, this.imageX + 2, costY, 0);
        } else if (pc < 100)
        {
            graphics.drawString(percentComplete, this.imageX - 6, costY, 0);
        } else
        {
            graphics.drawString(percentComplete, this.imageX - 14, costY, 0);
        }

        graphics.drawString(this.PERCENT_COMPLETE, this.imageX + 7, costY, 0);
        */

        // graphics.drawString(this.upgradeCost, 98, 70 + ((size + 1) *
        // myFont.DEFAULT_CHAR_HEIGHT), 0);
        // graphics.drawString(this.downGradeCost, 98, 70 + ((size + 2) *
        // myFont.DEFAULT_CHAR_HEIGHT), 0);

        this.getAnimationInterface().paint(graphics, this.imageX, y);
    }
    
    public void setRtsLayer(RTSLayer rtsLayer)
    {
        this.rtsLayer = rtsLayer;
    }

    protected CollidableDestroyableDamageableLayer getRtsLayer()
    {
        return rtsLayer;
    }
}
