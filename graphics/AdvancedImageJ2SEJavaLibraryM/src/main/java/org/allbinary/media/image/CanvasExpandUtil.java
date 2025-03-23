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
package org.allbinary.media.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class CanvasExpandUtil {
    
    private static final CanvasExpandUtil instance = new CanvasExpandUtil();

    /**
     * @return the instance
     */
    public static CanvasExpandUtil getInstance() {
        return instance;
    }

    //private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final StringUtil stringUtil = StringUtil.getInstance();
    
    public void process(final ImageProcessorInput imageProcessorInput, final int increaseX, final int increaseY, final ImageProcessedVisitor visitor) 
        throws Exception {
        
        final ImageUtil imageUtil = ImageUtil.getInstance();
        final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();
        final BufferedImage[] subBufferedImageArray = new BufferedImage[bufferedImageArray.length];
        
        final int size = bufferedImageArray.length;
        BufferedImage bufferedImage;
        for (int index = 0; index < size; index++) {

            bufferedImage = bufferedImageArray[index];
            //LogUtil.put(LogFactory.getInstance(spriteType, this, CommonStrings.getInstance().RUN));
                
            subBufferedImageArray[index] = imageUtil.createBufferedImage(
                bufferedImage, bufferedImage.getWidth() + increaseX, bufferedImage.getHeight() + increaseY, false);

            visitor.visit(subBufferedImageArray[index], stringUtil.EMPTY_STRING, index);
            
        }
        
    }
}
