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
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author User
 */
public class SpriteSplitterUtil {
    
    private static final SpriteSplitterUtil instance = new SpriteSplitterUtil();

    /**
     * @return the instance
     */
    public static SpriteSplitterUtil getInstance() {
        return instance;
    }

    public final String ALL = "All";
    public final String DOWN = "Down";
    public final String ROW = "Row";
    
    public void process(final ImageProcessorInput imageProcessorInput, final String spriteType, final ImageProcessedVisitor visitor) throws IOException {
        
        BufferedImage generatedBufferedImage;

        final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();

        for (int index = 0; index < bufferedImageArray.length; index++) {

            LogUtil.put(LogFactory.getInstance(spriteType, this, CommonStrings.getInstance().RUN));

            if(spriteType == ALL) {
            //generatedBufferedImage = ImageJ2SERotationUtil.getInstance().getRotatedImage(
                    //bufferedImageArray[index], totalAngle.intValue());
                
            } else if(spriteType == DOWN) {
                
            } else if(spriteType == ROW) {
                
            }
            
            //visitor.visit(generatedBufferedImage, nameEnding, index);
        }
        
    }
}
