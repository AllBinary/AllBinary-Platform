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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class SpriteSplitterUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final SpriteSplitterUtil instance = new SpriteSplitterUtil();

    /**
     * @return the instance
     */
    public static SpriteSplitterUtil getInstance() {
        return instance;
    }

    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    //private final ImageUtil imageUtil = ImageUtil.getInstance();

    public final String[] ANIMATIONS_LABELS = {
        "Row Total:", "Column Total:",
        "Column Total:", "Row Total:",
        "Column Total:", "Row Total:"
    };
    public final String DIRECTIONAL_ANIMATIONS = "Directional DLRU";
    public final String HORIZONTAL_ANIMATIONS = "Horizontal Animations";
    public final String HORIZONTAL_SPRITE = "Horizontal A=R=Y F=C=X";
    //public final String VERTICLE_ANIMATIONS = "Verticle Animations";
    
    private final String[] ROW_NAME = {
        "idle",
        "walk",
        "attack",
        "defeat"
    };

    private final String _ROW = "_row";
    
    public void process(final ImageProcessorInput imageProcessorInput, final int totalFrames, final int totalAnimations, 
        final int widthReduction, final int heightReduction, final int increaseWidth, final int increaseHeight, final String spriteType, final ImageProcessedVisitor visitor) 
        throws Exception {
        
        BufferedImage bufferedImage;
        BufferedImage[][] generatedBufferedImageArray;

        final BufferedImage[] bufferedImageArray = imageProcessorInput.getBufferedImageArray();

        for (int index = 0; index < bufferedImageArray.length; index++) {

            bufferedImage = bufferedImageArray[index];
            logUtil.put(spriteType, this, commonStrings.RUN);

            if(spriteType == HORIZONTAL_SPRITE) {

                final int cellHeight = bufferedImage.getHeight() / totalAnimations;
                final int cellWidth = bufferedImage.getWidth() / totalFrames;
                
                final int columns = totalFrames;
                final int rows = totalAnimations;
                
                generatedBufferedImageArray = new BufferedImage[rows][columns];
                
                logUtil.put("Processing Individual Cells columns: " + columns + " rows: " + rows, this, commonStrings.RUN);
                logUtil.put("Processing Individual Cells cellHeight: " + cellHeight + " cellWidth: " + cellWidth, this, commonStrings.RUN);

                final ImageUtil imageUtil = ImageUtil.getInstance();

                String nameEnding = null;
                int x = 0;
                int y = 0;
                for(int index2 = 0; index2 < rows; index2++) {
                    y = cellHeight * index2;
                    for(int index3 = 0; index3 < columns; index3++) {
                        x = cellWidth * index3;
                        generatedBufferedImageArray[index2][index3] = bufferedImage.getSubimage(
                            x + widthReduction, y + heightReduction, 
                            cellWidth - (widthReduction * 2), cellHeight - (heightReduction * 2));
                        if(increaseWidth != 0 || increaseHeight != 0) {
                        generatedBufferedImageArray[index2][index3] = imageUtil.createBufferedImage(
                            generatedBufferedImageArray[index2][index3], cellWidth + increaseWidth, cellHeight + increaseHeight, false, true);
                        }
                        nameEnding = new StringMaker().append(index2).append(commonSeps.UNDERSCORE).append(index3).toString();
                        visitor.visit(generatedBufferedImageArray[index2][index3], nameEnding, index);
                    }
                }

                logUtil.put("Processing Rows from Cells", this, commonStrings.RUN);
                
                final ImageUnifierProperties imageUnifierProperties = new ImageUnifierProperties();
                
                imageUnifierProperties.setRows(Integer.valueOf(1));
                imageUnifierProperties.setColumns(Integer.valueOf(columns));
                
                final ImageUnifierCell imageUnifierCell = new ImageUnifierCell(
                    Integer.valueOf(cellWidth - (2 * widthReduction)),Integer.valueOf(cellHeight - (2 * heightReduction)));
                imageUnifierProperties.setImageUnifierCell(imageUnifierCell);
                
                for(int index2 = 0; index2 < rows; index2++) {
                    y = cellHeight * index2;
                    final BufferedImage[] tempBufferedImageArray = new BufferedImage[columns];
                    for(int index3 = 0; index3 < columns; index3++) {
                        //Join columns
                        tempBufferedImageArray[index3] = generatedBufferedImageArray[index2][index3];
                        //generatedBufferedImage = bufferedImage.getSubimage(0, y, bufferedImage.getWidth(), cellHeight);
                        nameEnding = new StringMaker().append(index2).append(commonSeps.UNDERSCORE).append(1).append(_ROW).toString();
                    }
                    
                    final BufferedImage generatedBufferedImage = ImageUnifierUtil.getInstance().getImage(tempBufferedImageArray, imageUnifierProperties);
                    visitor.visit(generatedBufferedImage, nameEnding, index);
                }

            } else if(spriteType == HORIZONTAL_ANIMATIONS) {
                
//                final int cellHeight = bufferedImage.getHeight() / totalAnimations;
//                final int cellWidth = bufferedImage.getWidth() / totalFrames;
//                
//                final int columns = totalAnimations;
//                final int rows = totalFrames;
//                
//                generatedBufferedImageArray = new BufferedImage[rows][columns];
//                
//                String nameEnding = null;
//                int x = 0;
//                int y = 0;
//                for(int index2 = 0; index2 < rows; index2++) {
//                    y = cellHeight * index2;
//                    for(int index3 = 0; index3 < columns; index3++) {
//                        x = cellWidth * index3;
//                        generatedBufferedImageArray[index2][index3] = bufferedImage.getSubimage(
//                            x + widthReduction, y + heightReduction, 
//                            cellWidth - (widthReduction * 2), cellHeight - (heightReduction * 2));
//                        nameEnding = new StringMaker().append(ROW_NAME[index2]).append(commonSeps.UNDERSCORE).append(index3).toString();
//                        visitor.visit(generatedBufferedImageArray[index2][index3], nameEnding, index);
//                    }
//                }
//
//                final ImageUnifierProperties imageUnifierProperties = new ImageUnifierProperties();
//                
//                imageUnifierProperties.setRows(Integer.valueOf(1));
//                imageUnifierProperties.setColumns(Integer.valueOf(columns));
//                
//                final ImageUnifierCell imageUnifierCell = new ImageUnifierCell(
//                    Integer.valueOf(cellWidth - (2 * widthReduction)),Integer.valueOf(cellHeight - (2 * heightReduction)));
//                imageUnifierProperties.setImageUnifierCell(imageUnifierCell);
//                
//                for(int index2 = 0; index2 < rows; index2++) {
//                    y = cellHeight * index2;
//                    final BufferedImage[] tempBufferedImageArray = new BufferedImage[columns];
//                    for(int index3 = 0; index3 < columns; index3++) {
//                        //Join columns
//                        tempBufferedImageArray[index3] = generatedBufferedImageArray[index2][index3];
//                        //generatedBufferedImage = bufferedImage.getSubimage(0, y, bufferedImage.getWidth(), cellHeight);
//                        nameEnding = new StringMaker().append(DIRECTION_NAME[index2]).append(commonSeps.UNDERSCORE).append(1).append(_ROW).toString();
//                    }
//                    
//                    final BufferedImage generatedBufferedImage = ImageUnifierUtil.getInstance().getImage(tempBufferedImageArray, imageUnifierProperties);
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
                
                generatedBufferedImageArray = new BufferedImage[rows][columns];

                logUtil.put("Processing Individual Cells for each Direction", this, commonStrings.RUN);
                
                String nameEnding = null;
                int x = 0;
                int y = 0;
                for(int index2 = 0; index2 < rows; index2++) {
                    y = cellHeight * index2;
                    for(int index3 = 0; index3 < columns; index3++) {
                        x = cellWidth * index3;
                        generatedBufferedImageArray[index2][index3] = bufferedImage.getSubimage(
                            x + widthReduction, y + heightReduction, 
                            cellWidth - (widthReduction * 2), cellHeight - (heightReduction * 2));
                        if(increaseWidth != 0 || increaseHeight != 0) {
                        generatedBufferedImageArray[index2][index3] = ImageUtil.getInstance().createBufferedImage(
                            generatedBufferedImageArray[index2][index3], cellWidth + increaseWidth, cellHeight + increaseHeight, false, true);
                        }
                        nameEnding = new StringMaker().append(this.commonStrings.DIRECTION_NAME[index2]).append(commonSeps.UNDERSCORE).append(index3).toString();
                        visitor.visit(generatedBufferedImageArray[index2][index3], nameEnding, index);
                    }
                }
                
                final ImageUnifierProperties imageUnifierProperties = new ImageUnifierProperties();
                
                imageUnifierProperties.setRows(Integer.valueOf(1));
                imageUnifierProperties.setColumns(Integer.valueOf(columns));
                
                final ImageUnifierCell imageUnifierCell = new ImageUnifierCell(
                    Integer.valueOf(cellWidth - (2 * widthReduction)) + increaseWidth,Integer.valueOf(cellHeight - (2 * heightReduction) + increaseHeight));
                imageUnifierProperties.setImageUnifierCell(imageUnifierCell);
                
                logUtil.put("Processing Rows from Cells for each Direction", this, commonStrings.RUN);
                
                for(int index2 = 0; index2 < rows; index2++) {
                    y = cellHeight * index2;
                    final BufferedImage[] tempBufferedImageArray = new BufferedImage[columns];
                    for(int index3 = 0; index3 < columns; index3++) {
                        //Join columns
                        tempBufferedImageArray[index3] = generatedBufferedImageArray[index2][index3];
                        //generatedBufferedImage = bufferedImage.getSubimage(0, y, bufferedImage.getWidth(), cellHeight);
                        nameEnding = new StringMaker().append(this.commonStrings.DIRECTION_NAME[index2]).append(commonSeps.UNDERSCORE).append(1).append(_ROW).toString();
                    }
                    
                    final BufferedImage generatedBufferedImage = ImageUnifierUtil.getInstance().getImage(tempBufferedImageArray, imageUnifierProperties);
                    visitor.visit(generatedBufferedImage, nameEnding, index);
                }
                
            }
            
        }
        
    }
}
