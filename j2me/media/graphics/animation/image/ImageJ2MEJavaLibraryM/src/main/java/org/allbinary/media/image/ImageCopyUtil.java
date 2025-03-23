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

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class ImageCopyUtil
{
    private static final ImageCopyUtil instance = new ImageCopyUtil();
    
    public static ImageCopyUtil getInstance()
    {
        return instance;
    }
    
    private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    //private final BasicColorSetUtil basicColorUtil = BasicColorSetUtil.getInstance();

    private ImageCopyUtil()
    {
    }
    
    private int anchor = Anchor.TOP_LEFT;
    private final GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();
    private final Features features = Features.getInstance();
    private final String NO_COPY = "J2ME does not need to copy images after initial loading";
    
    public Image createImageForRotation(final Image originalImage)
            throws Exception
    {
        return this.createImage(originalImage);
    }
    
    // String resource,
    public Image createImage(final Image originalImage)
            throws Exception
    {
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            LogUtil.put(LogFactory.getInstance(NO_COPY, this, CommonStrings.getInstance().CONSTRUCTOR, new Exception()));
            return originalImage;
        }
        
        final Image image = imageCreationUtil.getInstance(
                originalImage.getWidth() , originalImage.getHeight());

        if (image.isMutable())
        {
            image.getGraphics().drawImage(originalImage, 0, 0, anchor);
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }

    public Image createImage(final Image originalImage, final float canvasScale, final boolean resize)
            throws Exception
    {
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            LogUtil.put(LogFactory.getInstance(NO_COPY, this, CommonStrings.getInstance().CONSTRUCTOR, new Exception()));
            return originalImage;
        }
        
        //final SpacialStrings spacialStrings = SpacialStrings.getInstance();
        //LogUtil.put(LogFactory.getInstance(spacialStrings + originalImage.getWidth() + spacialStrings + originalImage.getHeight(), this, CommonStrings.getInstance().CONSTRUCTOR));
        
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
        
        //LogUtil.put(LogFactory.getInstance("newWidth: " + newWidth + " newHeight: " + newHeight, this, CommonStrings.getInstance().CONSTRUCTOR));
        
        final Image image = imageCreationUtil.getInstance(newWidth, newHeight);

        if (image.isMutable())
        {
            final int halfWidthDelta = (newWidth - originalImage.getWidth()) / 2;
            final int halfHeightDelta = (newHeight - originalImage.getHeight()) / 2;
            //final SpacialStrings spacialStrings = SpacialStrings.getInstance();
            //LogUtil.put(LogFactory.getInstance("deltas" + spacialStrings + halfWidthDelta + spacialStrings + halfHeightDelta, this, CommonStrings.getInstance().CONSTRUCTOR));
            final Graphics graphics = image.getGraphics();
            graphics.drawImage(originalImage, halfWidthDelta, halfHeightDelta, anchor);
            //this.basicSetColorUtil.setBasicColor(graphics, BasicColorFactory.getInstance().YELLOW);
            //graphics.drawRect(halfWidthDelta, halfHeightDelta, originalImage.getWidth(), originalImage.getHeight());
            //this.basicSetColorUtil.setBasicColor(graphics, BasicColorFactory.getInstance().WHITE);
            //graphics.drawRect(0, 0, newWidth, newHeight);
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
}
