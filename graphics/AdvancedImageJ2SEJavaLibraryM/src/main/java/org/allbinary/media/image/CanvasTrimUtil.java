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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class CanvasTrimUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final CanvasTrimUtil instance = new CanvasTrimUtil();

    /**
     * @return the instance
     */
    public static CanvasTrimUtil getInstance() {
        return instance;
    }

    //private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final StringUtil stringUtil = StringUtil.getInstance();
    
    public void process(final ImageProcessorInput imageProcessorInput, final int cutCanvasFromLeftReduction, final int cutCanvasFromTopReduction, 
        final int cutCanvasFromRightReduction, final int cutCanvasFromBottomReduction, final ImageProcessedVisitor visitor) 
        throws IOException {
        
        final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();
        final BufferedImage[] subBufferedImageArray = new BufferedImage[bufferedImageArray.length];
        
        final int size = bufferedImageArray.length;
        BufferedImage bufferedImage;
        for (int index = 0; index < size; index++) {

            bufferedImage = bufferedImageArray[index];
            //logUtil.put(spriteType, this, commonStrings.RUN);
                
            subBufferedImageArray[index] = bufferedImage.getSubimage(cutCanvasFromLeftReduction, cutCanvasFromTopReduction, 
                bufferedImage.getWidth() - cutCanvasFromLeftReduction - cutCanvasFromRightReduction, bufferedImage.getHeight() - cutCanvasFromTopReduction - cutCanvasFromBottomReduction);

            visitor.visit(subBufferedImageArray[index], stringUtil.EMPTY_STRING, index);
            
        }
        
    }
}
