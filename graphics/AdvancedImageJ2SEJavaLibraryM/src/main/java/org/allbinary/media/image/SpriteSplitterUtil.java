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
import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringMaker;
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

    private final ImageUtil imageUtil = ImageUtil.getInstance();

    public final String DIRECTIONAL_ANIMATIONS = "Directional DLRU";
    public final String HORIZONTAL_ANIMATIONS = "Horizontal Animations";
    //public final String VERTICLE_ANIMATIONS = "Verticle Animations";
    
    private final String[] ROW_NAME = {
        "idle",
        "walk",
        "attack",
        "defeat"
    };

    private final String[] DIRECTION_NAME = {
        "down",
        "left",
        "right",
        "up"
    };
    
    public void process(final ImageProcessorInput imageProcessorInput, final int totalFrames, final int totalAnimations, final String spriteType, final ImageProcessedVisitor visitor) throws IOException {
        
        BufferedImage bufferedImage;
        BufferedImage generatedBufferedImage;

        final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();

        for (int index = 0; index < bufferedImageArray.length; index++) {

            bufferedImage = bufferedImageArray[index];
            LogUtil.put(LogFactory.getInstance(spriteType, this, CommonStrings.getInstance().RUN));

            if(spriteType == HORIZONTAL_ANIMATIONS) {
                
//                final int cellHeight = bufferedImage.getHeight() / totalAnimations;
//                final int cellWidth = bufferedImage.getWidth() / totalFrames;
//                
//                final int columns = totalAnimations;
//                final int rows = totalFrames;
//                
//                String nameEnding = null;
//                for(int index2 = 0; index2 < rows; index2++) {
//                    for(int index3 = 0; index3 < columns; index3++) {
//                        generatedBufferedImage = this.imageUtil.create(cellWidth, cellHeight);
//                        nameEnding = new StringMaker().append(ROW_NAME[index2]).append(CommonSeps.getInstance().UNDERSCORE).append(index3).toString();
//                        visitor.visit(generatedBufferedImage, nameEnding, index);
//                    }
//                }
//                
//                for(int index2 = 0; index2 < rows; index2++) {
//                    generatedBufferedImage = this.imageUtil.create(bufferedImage.getWidth(), cellHeight);
//                    nameEnding = new StringMaker().append(ROW_NAME[index2]).toString();
//                    visitor.visit(generatedBufferedImage, nameEnding, index);
//                }
                
//            } else if(spriteType == VERTICLE_ANIMATIONS) {
                
//                final int cellHeight = bufferedImage.getHeight() / totalAnimations;
//                final int cellWidth = bufferedImage.getWidth() / totalFrames;
//                
//                generatedBufferedImage = this.imageUtil.create(cellWidth, cellHeight);
//                
//                visitor.visit(generatedBufferedImage, nameEnding, index);
//                
//                generatedBufferedImage = this.imageUtil.create(bufferedImage.getHeight(), cellWidth);
//                
//                visitor.visit(generatedBufferedImage, nameEnding, index);

            } else if(spriteType == this.DIRECTIONAL_ANIMATIONS) {
                
                final int totalFrames2 = 4;
                final int cellHeight = bufferedImage.getHeight() / totalFrames2;
                final int cellWidth = bufferedImage.getWidth() / totalAnimations;
                
                final int columns = totalAnimations;
                final int rows = totalFrames2;
                
                String nameEnding = null;
                int x = 0;
                int y = 0;
                for(int index2 = 0; index2 < rows; index2++) {
                    y = cellHeight * index2;
                    for(int index3 = 0; index3 < columns; index3++) {
                        x = cellWidth * index3;
                        generatedBufferedImage = bufferedImage.getSubimage(x, y, cellWidth, cellHeight);
                        nameEnding = new StringMaker().append(DIRECTION_NAME[index2]).append(CommonSeps.getInstance().UNDERSCORE).append(index3).toString();
                        visitor.visit(generatedBufferedImage, nameEnding, index);
                    }
                }
                
                for(int index2 = 0; index2 < rows; index2++) {
                    y = cellHeight * index2;
                    generatedBufferedImage = bufferedImage.getSubimage(0, y, bufferedImage.getWidth(), cellHeight);
                    nameEnding = new StringMaker().append(DIRECTION_NAME[index2]).toString();
                    visitor.visit(generatedBufferedImage, nameEnding, index);
                }
                
            }
            
        }
        
    }
}
