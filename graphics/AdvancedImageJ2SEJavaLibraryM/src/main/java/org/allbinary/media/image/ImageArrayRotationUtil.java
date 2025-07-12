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
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class ImageArrayRotationUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final ImageArrayRotationUtil instance = new ImageArrayRotationUtil();

    /**
     * @return the instance
     */
    public static ImageArrayRotationUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private final String TOTAL_ANGLE = "totalAngle: ";

    public void process(final ImageProcessorInput imageProcessorInput, final String input, final ImageProcessedVisitor visitor) throws IOException {
        
        Integer totalAngle;
        if (input == this.commonStrings.UP) {
            totalAngle = Integer.valueOf(-90);
        } else if (input == this.commonStrings.DOWN) {
            totalAngle = Integer.valueOf(90);
        } else {
            totalAngle = Integer.valueOf(input);
        }
        
        BufferedImage generatedBufferedImage;

        final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();

        for (int index = 0; index < bufferedImageArray.length; index++) {

            logUtil.put(TOTAL_ANGLE + totalAngle, this, commonStrings.RUN);

            generatedBufferedImage = ImageJ2SERotationUtil.getInstance().getRotatedImage(
                    bufferedImageArray[index], totalAngle.intValue());
            
            visitor.visit(generatedBufferedImage, input, index);
        }
        
    }
}
