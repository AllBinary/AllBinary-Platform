/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.animation.text;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.TextField;

import org.allbinary.graphics.form.item.CustomTextBox;
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.game.canvas.ABToGBUtil;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.ScaleProperties;

/**
 *
 * @author User
 */
public class CustomTextBoxIndexedAnimationFactory
    implements AnimationInterfaceFactoryInterface {
    protected final LogUtil logUtil = LogUtil.getInstance();


    //private final CommonStrings commonStrings = CommonStrings.getInstance();

    public ScaleProperties scaleProperties;
    private final int maxLength;

    public CustomTextBoxIndexedAnimationFactory(final int fontSize, final int maxLength) {
        
        //logUtil.putF(this.commonStrings.START + fontSize, this, this.commonStrings.CONSTRUCTOR);
        
        this.scaleProperties = new ScaleProperties();
        this.scaleProperties.scaleHeight = fontSize;
        this.maxLength = maxLength;
    }
    
    @Override
    public Animation getInstance(final int instanceId) throws Exception {

        //logUtil.put(this.commonStrings.START, this, this.commonStrings.PROCESS);
        
        final ABToGBUtil abToGBUtil = ABToGBUtil.getInstance();
        final AllBinaryGameCanvas abCanvas = (AllBinaryGameCanvas) abToGBUtil.abCanvas;
        
        final Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, this.scaleProperties.scaleHeight);
        
        //this.getHighScoresArray(), highScore, 
        final CustomTextBox customTextBox = new CustomTextBox2(
            abCanvas.getCustomCommandListener(),
            StringUtil.getInstance().EMPTY_STRING,
            StringUtil.getInstance().EMPTY_STRING,
            this.maxLength, TextField.ANY, font,
            abCanvas.getLayerManager().getBackgroundBasicColor(),
            abCanvas.getLayerManager().getForegroundBasicColor());

        return new CustomTextBoxIndexedAnimation(customTextBox);
    }

    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {
        final int fontSize = this.scaleProperties.scaleHeight;
        this.scaleProperties = scaleProperties;
        if(this.scaleProperties.scaleHeight < 6) {
            this.scaleProperties.scaleHeight = fontSize;
        }
    }

}
