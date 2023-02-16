/**
 * Copyright 2011 The PlayN Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.html;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.media.image.ImageScaleUtil;

/**
 *
 * @author user
 */
public class Canvas2dFontUtil
{
    private static final Canvas2dFontUtil instance = new Canvas2dFontUtil();

    /**
     * @return the instance
     */
    public static Canvas2dFontUtil getInstance()
    {
        return instance;
    }
    
    //private final CanvasElement canvasElement = Document.get().createElement("canvas").<CanvasElement>cast();
    //private final Context2d context2d = canvasElement.getContext2d();
    
    //34 1 23 23
    //2011
    public final String pattern = " 0123456789     ABCDEFGHIJKLM   NOPQRSTUVWXYZ   abcdefghijklm   nopqrstuvwxyz   .?!$%`¬\"£^&*    ()_+-=[]{};'#   :~,/<>\\|©® @";
    
    public final float[] SCALE_ARRAY = new float[5];
    public final int[] FONT_SIZE_TO_SCALE_INDEX_ARRAY = new int[49];
    
    private Canvas2dFontUtil()
    {
        final int size = this.FONT_SIZE_TO_SCALE_INDEX_ARRAY.length;
        
        for(int index = 0; index < size; index++) {
            if (index < 16) {
                this.FONT_SIZE_TO_SCALE_INDEX_ARRAY[index] = 0;
            } else if (index <= 19) {
                this.FONT_SIZE_TO_SCALE_INDEX_ARRAY[index] = 1;
            } else if (index <= 23) {
                this.FONT_SIZE_TO_SCALE_INDEX_ARRAY[index] = 2;
            } else if (index <= 27) {
                this.FONT_SIZE_TO_SCALE_INDEX_ARRAY[index] = 3;
            } else if (index <= 48) {
                this.FONT_SIZE_TO_SCALE_INDEX_ARRAY[index] = 4;
            }
        }
    }
    
    public final int[] charWidthArray = new int[125];
    
    public javax.microedition.lcdui.Image getFontBitmap(
            final Object objectUnused, final int fontSizeUnused, final int cellSizeUnused, final int cellsPerRowUnused, final BasicColor basicColor)
    {
        //HtmlGraphicsGL htmlGraphicsGL = (HtmlGraphicsGL) object;
        
        //htmlGraphicsGL.createTexture(canvasElement);
        //context2d.

        final Image image = Image.createImage(128, 128);

        final Graphics graphics = image.getGraphics();

        graphics.setColor(basicColor.intValue());

        int y = 8;
        
        final String NUMBERS = " 0123456789";

        for(int index = 0; index < 10; index++)
        {
            graphics.drawString(NUMBERS.substring(index, index + 1), index * 8, y, 0);
        }

        y += 16;

        final String CAPS_1 = "ABCDEFGHIJKLM";
        
        for(int index = 0; index < 13; index++)
        {
            graphics.drawString(CAPS_1.substring(index, index + 1), index * 8, y, 0);
        }

        y += 16;

        final String CAPS_2 = "NOPQRSTUVWXYZ";
        
        for(int index = 0; index < 13; index++)
        {
            graphics.drawString(CAPS_2.substring(index, index + 1), index * 8, y, 0);
        }

        y += 16;

        final String LOWERCASE_1 = "abcdefghijklm";
        
        for(int index = 0; index < 13; index++)
        {
            graphics.drawString(LOWERCASE_1.substring(index, index + 1), index * 8, y, 0);
        }

        y += 16;

        final String LOWERCASE_2 = "nopqrstuvwxyz";
        
        for(int index = 0; index < 13; index++)
        {
            graphics.drawString(LOWERCASE_2.substring(index, index + 1), index * 8, y, 0);
        }

        y += 16;

        final String SPECIAL_1 = ".?!$%`¬\"£^&*";
        
        for(int index = 0; index < 13; index++)
        {
            graphics.drawString(SPECIAL_1.substring(index, index + 1), index * 8, y, 0);
        }

        y += 16;

        final String SPECIAL_2 = "()_+-=[]{};'#";
        
        for(int index = 0; index < 13; index++)
        {
            graphics.drawString(SPECIAL_2.substring(index, index + 1), index * 8, y, 0);
        }

        y += 16;

        final String SPECIAL_3 = ":~,/<>\\|©®";

        for(int index = 0; index < 12; index++)
        {
            graphics.drawString(SPECIAL_3.substring(index, index + 1), (index * 8), y, 0);
        }

        return image;
    }

    public Image getAtImage(final BasicColor basicColor) {

        final String SPECIAL_4 = "@";
        //graphics.drawString(SPECIAL_4, (11 * 8) - 2, y, 0);
        final Image atImage = javax.microedition.lcdui.Image.createImage(16, 16);
        final Graphics graphics2 = atImage.getGraphics();
        graphics2.setColor(basicColor.intValue());
        graphics2.drawString(SPECIAL_4, 1, 7, 0);
        
        return atImage;
    }

    public int getSize()
    {
        return 8;
    }
}
