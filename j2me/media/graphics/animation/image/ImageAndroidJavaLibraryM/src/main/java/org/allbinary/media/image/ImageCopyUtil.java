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
package org.allbinary.media.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.Anchor;

import org.allbinary.logic.communication.log.LogUtil;

public class ImageCopyUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ImageCopyUtil instance = new ImageCopyUtil();
    
    public static ImageCopyUtil getInstance()
    {
        return ImageCopyUtil.instance;
    }
    
    private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    //private final BasicColorSetUtil basicColorUtil = BasicColorSetUtil.getInstance();

    private ImageCopyUtil()
    {
    }
    
    private int anchor = Anchor.TOP_LEFT;

    public Image createImageForRotation(final Image originalImage)
            throws Exception
    {
        return this.createImage(originalImage);
    }
    
    // String resource,
    public Image createImage(final Image originalImage)
            throws Exception
    {
        final Image image = this.imageCreationUtil.createImageWH(
                originalImage.getWidth() , originalImage.getHeight());

        if (image.isMutable())
        {
            image.getGraphics().drawImage(originalImage, 0, 0, this.anchor);
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }

    public Image createImageScale(final Image originalImage, final float canvasScale, final boolean resize)
            throws Exception
    {
        //final CommonLabels commonLabels = CommonLabels.getInstance();
        //this.logUtil.putF(this.commonLabels + originalImage.getWidth() + commonLabels + originalImage.getHeight(), this, this.commonStrings.CONSTRUCTOR);
        
        int newWidth = (int) (originalImage.getWidth() * canvasScale);
        int newHeight = (int) (originalImage.getHeight() * canvasScale);
        
        if(resize) {
            if (newWidth < newHeight) {
                newWidth = newHeight;
            }

            if (newHeight < newWidth) {
                newHeight = newWidth;
            }
        }
        
        //this.logUtil.putF("newWidth: " + newWidth + " newHeight: " + newHeight, this, this.commonStrings.CONSTRUCTOR);
        
        final Image image = this.imageCreationUtil.createImageWH(newWidth, newHeight);

        if (image.isMutable())
        {
            final int halfWidthDelta = (newWidth - originalImage.getWidth()) / 2;
            final int halfHeightDelta = (newHeight - originalImage.getHeight()) / 2;
            //final CommonLabels commonLabels = CommonLabels.getInstance();
            //this.logUtil.putF("deltas" + commonLabels + halfWidthDelta + commonLabels + halfHeightDelta, this, this.commonStrings.CONSTRUCTOR);
            final Graphics graphics = image.getGraphics();
            graphics.drawImage(originalImage, halfWidthDelta, halfHeightDelta, this.anchor);
            //this.basicSetColorUtil.setBasicColorP(graphics, BasicColorFactory.getInstance().YELLOW);
            //graphics.drawRect(halfWidthDelta, halfHeightDelta, originalImage.getWidth(), originalImage.getHeight());
            //this.basicSetColorUtil.setBasicColorP(graphics, BasicColorFactory.getInstance().WHITE);
            //graphics.drawRect(0, 0, newWidth, newHeight);
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
}
