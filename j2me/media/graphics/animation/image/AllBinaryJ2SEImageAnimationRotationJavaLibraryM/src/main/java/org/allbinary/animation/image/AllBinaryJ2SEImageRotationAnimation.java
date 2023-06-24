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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.math.AngleInfo;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.media.image.ImageModifierUtil;
import org.allbinary.media.image.ImageRotationUtil;

public class AllBinaryJ2SEImageRotationAnimation 
extends AllBinaryImageBaseRotationAnimation
{
    private final ImageRotationUtil imageRotationUtil = ImageRotationUtil.getInstance();
    private final ImageModifierUtil imageModifierUtil = ImageModifierUtil.getInstanceOrCreate();

    //private final int width;
    //private final int height;

    //private final int halfWidth;
    //private final int halfHeight;
    
    //private final float increment;
    
    private final Image originalImage;
    
    private final Image[] twoImages = new Image[2];
    
    private Image imageToShow;
    private int bufferedImageIndex;
    
    private ModifierBaseProcessor alphaProcessor = ModifierBaseProcessor.getInstance();
    private ModifierBaseProcessor setColorProcessor = ModifierBaseProcessor.getInstance();
    private ModifierBaseProcessor changeColorProcessor = ModifierBaseProcessor.getInstance();
    
    protected AllBinaryJ2SEImageRotationAnimation(
            final Image originalImage, final Image image,
            final AngleInfo angleInfo, final short totalAngle) throws Exception
    {
        super(image, angleInfo, totalAngle);

        this.originalImage = originalImage;
        
        //this.width = image.getWidth();
        //this.height = image.getHeight();
        
        //this.halfWidth = (this.getImage().getWidth() >> 1);
        //this.halfHeight = (this.getImage().getHeight() >> 1);
        
        //this.increment = (short)(this.angleInfo.getAngleIncrementInfo().getAngleIncrement() * 2.44);
        
        this.imageToShow = image;
        this.twoImages[0] = image;
        this.twoImages[1] = ImageCopyUtil.getInstance().createImage(image);
        
        //LogUtil.put(LogFactory.getInstance(this.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
    }

    public void setBasicColor(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColor() == null || this.getBasicColor().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.setBasicColor(basicColor);
        
        if(changed) {
            this.setColorProcessor = SetColorProcessor.getInstance();
            this.updateImage();
        }
    }

    public void changeBasicColor(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColor() == null || this.getBasicColor().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.changeBasicColor(basicColor);
        
        if(changed) {
            this.changeColorProcessor = ChangeColorProcessor.getInstance();
            this.updateImage();
        }
    }
    
    public void setAlpha(final int alpha) {
        
        boolean changed = false;
        if(this.alpha != alpha) {
            changed = true;
        }
        
        super.setAlpha(alpha);

        if(changed) {
            this.alphaProcessor = AlphaProcessor.getInstance();
            this.updateImage();
        }
    }
    
    public void nextRotation()
    {
        super.nextRotation();
        this.updateImage();
    }

    public void previousRotation()
    {
        super.previousRotation();
        this.updateImage();
    }

    private void updateImage() {

        this.imageRotationUtil.rotateImage(originalImage, this.twoImages[this.bufferedImageIndex], this.angleInfo.getAngle() + 90);
        this.alphaProcessor.update(imageModifierUtil, null, this.twoImages[this.bufferedImageIndex], 0, this.alpha);
        this.setColorProcessor.update(imageModifierUtil, null, this.twoImages[this.bufferedImageIndex], 0, this.basicColor);
        this.changeColorProcessor.update(imageModifierUtil, null, this.twoImages[this.bufferedImageIndex], 0, this.changeBasicColor);
        this.swap();
    }

    public void setFrame(final int index)
    {
        //LogUtil.put(LogFactory.getInstance("index: " + index, this, "setRotation"));

        //final int currentFrame = this.circularIndexUtil.getIndex();
        //LogUtil.put(LogFactory.getInstance("currentFrame: " + currentFrame, this, "setRotation"));
        
        super.setFrame(index);

        this.updateImage();
    }
    
    public void swap() {
        
        this.imageToShow = this.twoImages[this.bufferedImageIndex];

        if(this.bufferedImageIndex == 0) {
            this.bufferedImageIndex = 1;
        } else {
            this.bufferedImageIndex = 0;
        }
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawImage(this.imageToShow, x, y, anchor);
    }
    
}