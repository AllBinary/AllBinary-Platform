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
package org.allbinary.emulator.device;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;

import org.microemu.device.swt.SwtMutableImage;

import org.allbinary.AvianUtil;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.opengles.OpenGLCapabilities;
import org.allbinary.image.PreResourceImageUtil;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

//TTF
public class TrueTypeFontUtil extends TrueTypeFontUtilBase
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TrueTypeFontUtil instance = new TrueTypeFontUtil();

    public static TrueTypeFontUtil getInstance()
    {
        return instance;
    }

    private final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();

    private final float[] widthFloatArray;
    
    public OpenGLESImage fontImage = OpenGLESImage.NULL_OPENGL_IMAGE;

    private final float widthScale;
    
    private TrueTypeFontUtil() {
        super(2);
        
        if(AvianUtil.isAvian()) {

            widthFloatArray = new float[] {
                1504.0f, 967.0f, 527.0f, 961.0f, 957.0f, 1087.0f, 920.0f, 954.0f, 973.0f, 961.0f, 955.0f, 1296.0f, 1013.0f, 1106.0f, 1167.0f, 815.0f, 815.0f, 1216.0f, 1110.0f, 170.0f, 520.0f, 1056.0f, 815.0f, 1448.0f, 1142.0f, 1345.0f, 927.0f, 1345.0f, 1030.0f, 920.0f, 1096.0f, 1119.0f, 1219.0f, 1841.0f, 1166.0f, 1147.0f, 1005.0f, 879.0f, 965.0f, 792.0f, 964.0f, 927.0f, 753.0f, 1034.0f, 916.0f, 196.0f, 469.0f, 877.0f, 166.0f, 1563.0f, 916.0f, 1007.0f, 965.0f, 964.0f, 631.0f, 777.0f, 649.0f, 917.0f, 1026.0f, 1548.0f, 993.0f, 1028.0f, 795.0f, 241.0f, 798.0f, 241.0f, 905.0f, 1477.0f, 393.0f, 961.0f, 961.0f, 961.0f, 961.0f, 555.0f, 1029.0f, 1010.0f, 1378.0f, 952.0f, 952.0f, 952.0f, 952.0f, 463.0f, 463.0f, 926.0f, 961.0f, 491.0f, 930.0f, 457.0f, 457.0f, 644.0f, 643.0f, 326.0f, 186.0f, 1219.0f, 241.0f, 1599.0f, 961.0f, 302.0f, 711.0f, 961.0f, 961.0f, 710.0f, 141.0f, 1504.0f, 1504.0f, 1504.0f, 1504.0f, 1504.0f, 1504.0f, 1504.0f, 1504.0f
            };

        } else {
            
            widthFloatArray = new float[] {
                1504.0f, 967.0f, 527.0f, 961.0f, 957.0f, 1087.0f, 920.0f, 954.0f, 973.0f, 961.0f, 955.0f, 1296.0f, 1013.0f, 1106.0f, 1167.0f, 815.0f, 815.0f, 1216.0f, 1110.0f, 170.0f, 520.0f, 1056.0f, 815.0f, 1448.0f, 1142.0f, 1345.0f, 927.0f, 1345.0f, 1030.0f, 920.0f, 1096.0f, 1119.0f, 1219.0f, 1841.0f, 1166.0f, 1147.0f, 1005.0f, 879.0f, 965.0f, 792.0f, 964.0f, 927.0f, 753.0f, 1034.0f, 916.0f, 196.0f, 469.0f, 877.0f, 166.0f, 1563.0f, 916.0f, 1007.0f, 965.0f, 964.0f, 631.0f, 777.0f, 649.0f, 917.0f, 1026.0f, 1548.0f, 993.0f, 1028.0f, 795.0f, 241.0f, 798.0f, 241.0f, 905.0f, 1477.0f, 393.0f,                         961.0f, 555.0f, 1029.0f, 1010.0f, 1378.0f,                         952.0f, 463.0f, 463.0f, 926.0f, 961.0f, 491.0f, 930.0f, 457.0f, 457.0f, 644.0f, 643.0f, 326.0f, 186.0f, 1219.0f, 241.0f, 1599.0f, 961.0f, 302.0f, 711.0f, 961.0f, 961.0f, 710.0f, 141.0f, 1504.0f, 1504.0f
            };
            
        }
        
        this.widthScale = (this.scale == 2) ? 1.2f : 1f;
    }

    public Image getFontBitmap(final GL10 gl, final String filename, final int cellSize, final BasicColor basicColor) {
        try {
            if(this.fontImage == OpenGLESImage.NULL_OPENGL_IMAGE) {
                
                final CanvasStrings canvasStrings = CanvasStrings.getInstance();
                final OpenGLCapabilities openGLCapabilities = OpenGLCapabilities.getInstance();
                if (openGLCapabilities.isTextureSizeValid(1024) && this.scale == 2) {
                    final String FONT_ATLAS_1024 = "/font_1024.png";
                    final Image image = Image.createImage(ResourceUtil.getInstance().getResourceAsStream(FONT_ATLAS_1024));
                    image.setName(canvasStrings.FONT_ATLAS);
                    this.fontImage = (OpenGLESImage) this.preResourceImageUtil.encapsulate(image);
                    //SwtSystemFont.scale = this.scale;
                    //MyFont.getInstance().update();
                } else if (openGLCapabilities.isTextureSizeValid(512)) {
                    final String FONT_ATLAS_512 = "/font_512.png";
                    final Image image = Image.createImage(ResourceUtil.getInstance().getResourceAsStream(FONT_ATLAS_512));
                    image.setName(canvasStrings.FONT_ATLAS);
                    this.fontImage = (OpenGLESImage) this.preResourceImageUtil.encapsulate(image);
                } else {
                    final String FONT_ATLAS_512 = "/font_512.png";
                    final Image image = Image.createImage(ResourceUtil.getInstance().getResourceAsStream(FONT_ATLAS_512));
                    image.setName(canvasStrings.FONT_ATLAS);
                    this.fontImage = (OpenGLESImage) this.preResourceImageUtil.encapsulate(image);
                    //throw new RuntimeException();
                }

                return this.fontImage;
            } else {
                return this.fontImage;
            }
        } catch(Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.EXCEPTION, e);
            throw new RuntimeException();
        }
    }
    
    //This is only called from OpenGLES on Android via OpenGLESStrings
    //For SWT this just saves a copy of the existing font image.
    public void saveFontAtlasAsFile() {
        
        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //logUtil.put(commonStrings.START, this, "saveFontAtlasAsFile");
        
        final Image image = this.getFontBitmap2(null, cellSize, BasicColorFactory.getInstance().WHITE);
        final ImageLoader imageLoader = new ImageLoader();
        final ImageData imageData = ((SwtMutableImage) image).image.getImageData();
        imageData.transparentPixel = imageData.getPixel(0, 0);
        imageLoader.data = new ImageData[]{imageData};
        imageLoader.save(CommonSeps.getInstance().PERIOD + CanvasStrings.getInstance().FONT_ATLAS, SWT.IMAGE_PNG);            
    }    
    
    public Image getFontBitmap2(final String filename, final int cellSize, final BasicColor basicColor)
    {
        //TODO: Draw all Text on SWT Image.
        
        //final String GET_FONT_BITMAP = "getFontBitmap";
        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //logUtil.put(commonStrings.START, this, GET_FONT_BITMAP);
        
        final int cellsPerRow2 = CELLS_PER_ROW * 2;
        final int cellsPerRow3 = CELLS_PER_ROW * 3;
        final int cellsPerRow4 = CELLS_PER_ROW * 4;
        final int cellsPerRow5 = CELLS_PER_ROW * 5;
        final int cellsPerRow6 = CELLS_PER_ROW * 6;
        final int cellsPerRow7 = CELLS_PER_ROW * 7;

        //final Typeface typeface = Typeface.DEFAULT;
        //Typeface.createFromAsset(ResourceUtil.getInstance().getContext().getAssets(), filename);

        //Must make bitmap as texture for GL so it must be as a texture size. 
        //logUtil.put("textureSize: " + textureSize, this, GET_FONT_BITMAP);

        final Image image = Image.createImage(textureSize, textureSize);

        final Graphics graphics = image.getGraphics();
        graphics.setColor(basicColor.intValue());
        
        //logUtil.put("basicColor: " + basicColor, this, GET_FONT_BITMAP);
        //logUtil.put("graphics: " + graphics, this, GET_FONT_BITMAP);
        
        int biggestHeight = 0;
        final Rectangle bounds = new Rectangle(PointFactory.getInstance().getInstance(0, 0), cellSize, cellSize);
        int x;
        int y;
        for (int index = 0; index < size; index++)
        //for (int index = 0; index < 256; index++)
        {
            //if(pattern.length() > index) {
                characterArray[0] = pattern.charAt(index);
            //} else {
                //characterArray[0] = ' ';
            //}
            
            //paint.getTextBounds(characterArray, 0, 1, bounds);
                        
            _characterWidth[index] = bounds.getMaxX();
            if (bounds.getMaxY() - bounds.getPoint().getX() > biggestHeight)
                biggestHeight = bounds.getMaxY() - bounds.getPoint().getX();
            x = (index % CELLS_PER_ROW) * cellSize;
            x += (cellSize >> 1);
            x -= (_characterWidth[index] >> 1);
            y = -(cellSize >> 1);
            if (index >= CELLS_PER_ROW)
                y += cellSize;
            if (index >= cellsPerRow2)
                y += cellSize;
            if (index >= cellsPerRow3)
                y += cellSize;
            if (index >= cellsPerRow4)
                y += cellSize;
            if (index >= cellsPerRow5)
                y += cellSize;
            if (index >= cellsPerRow6)
                y += cellSize;
            if (index >= cellsPerRow7)
                y += cellSize;
            y += cellSize;
            y -= (cellSize >> 2);
            ////graphics.setColor(x, y, (x + y) / 2);
            ////((SwtMutableImage) image).getGc().setAlpha(255);
            ////graphics.drawLine(x, y, bounds.getWidth(), bounds.getHeight());
            ////graphics.fillRect(x, y, bounds.getWidth(), bounds.getHeight());
            ////graphics.setColor(x, y, 0);
            //graphics.drawRect(x, y, bounds.getWidth(), bounds.getHeight());
            graphics.drawChar(characterArray[0], x, y, 0);
                        
        }
//        canvas.save();
//
//        return new Image(bitmap);

        //this.saveFontAtlasAsFile();
        return image;
    }
    
    public int[] getFontWidths(String filename, int fontSize)
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        //Typeface typeface = Typeface.DEFAULT;
        //Typeface.createFromAsset(ResourceUtil.getInstance().getContext().getAssets(), filename);

        //final StringMaker stringMaker = new StringMaker();
        //final String CHAR_WIDTH = "char width: ";
        
        //final Rectangle bounds = new Rectangle(PointFactory.getInstance().getInstance(0, 0), (this.cellSize / 10) * 4, this.cellSize);
        for (int index = 0; index < size; index++)
        {
            characterArray[0] = pattern.charAt(index);
//            paint.getTextBounds(characterArray, 0, 1, bounds);

            //Avian @ symbol does not use the correct index
//            if(index == 112) {
//                _characterWidth[index] = bounds.getMaxX() + 10;
//            } else if (index < lastCapIndex)
//            {
//                if (characterArray[0] == '1' || (characterArray[0] >= '3' && characterArray[0] <= '9') || characterArray[0] == '0')
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 1;
//                } else if (characterArray[0] == '2' || characterArray[0] == ':')
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 2;
//                } else if (characterArray[0] == 'A' || characterArray[0] == 'B' || characterArray[0] == 'C' || 
//                        characterArray[0] == 'E' || characterArray[0] == 'F' || 
//                        characterArray[0] == 'H' || characterArray[0] == 'J' || 
//                        characterArray[0] == 'L' || characterArray[0] == 'M' || 
//                        characterArray[0] == 'N' || characterArray[0] == 'P' || 
//                        characterArray[0] == 'V' || 
//                        characterArray[0] == 'Q' || characterArray[0] == 'R' || characterArray[0] == 'S' || 
//                        characterArray[0] == 'U' || characterArray[0] == 'W'
//                        )
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 5;
//                } else if (characterArray[0] == 'D' || characterArray[0] == 'G' || characterArray[0] == 'O'
//                    )
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 3;
//                } else
//                {
//                    _characterWidth[index] = bounds.getMaxX();
//                }
//            } else
//            {
//                if (characterArray[0] == 'l') {
//                    _characterWidth[index] = bounds.getMaxX() - 2;
//                } else if(characterArray[0] == '@')
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 10;
//                } else if(
//                        characterArray[0] == 'd' || characterArray[0] == '!' || characterArray[0] == '|')
//                {   
//                    _characterWidth[index] = bounds.getMaxX() + 6;
//                } else if(characterArray[0] == 'm' || characterArray[0] == 'w')
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 4;
//                } else if(characterArray[0] == 'a' || characterArray[0] == 'b' || 
//                    characterArray[0] == 'k' || 
//                    characterArray[0] == 'o' || characterArray[0] == 'u')
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 3;
//                } else if( characterArray[0] == 'c' || 
//                    characterArray[0] == 'e' || characterArray[0] == 'g' || characterArray[0] == 'p' || 
//                    characterArray[0] == 'y' || characterArray[0] == 'x')
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 2;
//                } else if (characterArray[0] == 'c' || characterArray[0] == 'f' || 
//                    characterArray[0] == 'h' || characterArray[0] == 'n' || characterArray[0] == 'v')
//                {
//                    _characterWidth[index] = bounds.getMaxX() + 1;
//                } else if (characterArray[0] == 'r')
//                {
//                    _characterWidth[index] = bounds.getMaxX() - 1;
//                } else if (characterArray[0] == 'i' || characterArray[0] == 'y' || characterArray[0] == '.')
//                {
//                    _characterWidth[index] = bounds.getMaxX() - 2;
//                } else if (characterArray[0] == '.')
//                {
//                    _characterWidth[index] = bounds.getMaxX() - 6;
//                } else
//                {
//                    _characterWidth[index] = bounds.getMaxX();
//                }
//            }
 
            if(characterArray[0] == ' ') {
                _characterWidth[index] = (fontSize / 4);
            } else {
                //logUtil.put("widthFloatArray: " + widthFloatArray.length, this, commonStrings.START);
                //logUtil.put("character: " + characterArray[0], this, commonStrings.START);
                final int shortPatternIndex = this.shortPattern.indexOf(characterArray[0]);
                //logUtil.put("shortPatternIndex: " + shortPatternIndex, this, commonStrings.START);
                final int w = (int) (widthFloatArray[shortPatternIndex] / 75) + 19;
//                logUtil.put(new StringMaker().append('w').append(' ').append(characterArray[0]).append(w).append(';')
//                    //.append(_characterWidth[index])
//                    .append(bounds.getMaxX())
//                    .toString(), this, commonStrings.START);

                _characterWidth[index] = (int) w;
                
                //if(characterArray[0] == 'r' || characterArray[0] == 't' || characterArray[0] == 'i' || characterArray[0] == 'l') _characterWidth[index] -= 3;
                //if(characterArray[0] == 'E' || characterArray[0] == 'P') _characterWidth[index] += 5;
                if(characterArray[0] == '.' || characterArray[0] == '1') _characterWidth[index] += 4;
                if(characterArray[0] >= 'a' && characterArray[0] < 'k') _characterWidth[index] -= 2;
                if(characterArray[0] >= 'k' && characterArray[0] < 'v') _characterWidth[index] -= 4;
                if(characterArray[0] >= 'v' && characterArray[0] < 'z') _characterWidth[index] -= 8;
                if(characterArray[0] == 'D') _characterWidth[index] -= 4;
                if(characterArray[0] >= 'A' && characterArray[0] <= 'Z') _characterWidth[index] -= 2;
            }
            _characterWidth[index] *= this.widthScale;
            
            //stringMaker.delete(0, stringMaker.length());
            //System.out.println(stringMaker.append(index).append(CHAR_WIDTH).append(_characterWidth[index]).toString());
        }


        return _characterWidth;
    }
}