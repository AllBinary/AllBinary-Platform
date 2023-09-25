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
import org.allbinary.media.image.ImageJ2SERotationUtil;

/**
 *
 * @author User
 */
public class ImageArrayRotationUtil {

    /**
     * @return the instance
     */
    public static ImageArrayRotationUtil getInstance() {
        return instance;
    }

    private static final ImageArrayRotationUtil instance = new ImageArrayRotationUtil();

    public void process(final ImageProcessorInput imageProcessorInput, final Integer totalAngle, final ImageProcessedVisitor visitor) throws IOException {
        BufferedImage generatedBufferedImage;

        final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();

        for (int index = 0; index < bufferedImageArray.length; index++) {

            LogUtil.put(LogFactory.getInstance("totalAngle: " + totalAngle, this, CommonStrings.getInstance().RUN));

            generatedBufferedImage = ImageJ2SERotationUtil.getInstance().getRotatedImage(
                    bufferedImageArray[index], totalAngle.intValue());
            
            visitor.visit(generatedBufferedImage, index);
        }
        
    }
}
