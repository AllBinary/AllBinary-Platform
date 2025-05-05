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
package javax.microedition.lcdui;

import javax.microedition.lcdui.Image;

/**
 *
 * @author User
 */
public class PlatformImage {

    public Object getImage(final Object graphicsSurface2) {
        return null;
    }

    public int getWidth(final Object graphicsSurface2, final int width) {
        return width;
    }

    public int getHeight(final Object graphicsSurface2, final int height) {
        return height;
    }
    
    public int getWidth(final Image image, final int width) {
        return width;
    }

    public int getHeight(final Image image, final int height) {
        return height;
    }
    
    public javax.microedition.lcdui.Graphics getGraphics(final Object graphicsSurface, final int width, final int height, final Image image) {
        return null;
    }

    public void drawImage(final Image image, final int x, final int y, int anchor, final javax.microedition.lcdui.Graphics g2, final Object g) {

    }

    public void drawRegion(final Image image, final int x_src, final int y_src, final int width,
        final int height, final int transform, final int x_dst, final int y_dst, final int anchor, final javax.microedition.lcdui.Graphics g) {

    }

    public void getRGB(final int[] argb, final int offset, final int scanlength, final int x, final int y, final int width, final int height, final Image image) {

    }

    public void setRGB(final int[] argb, final int offset, final int scanlength, final int x, final int y, final int width, final int height, final Image image) {
        
    }

    public int[] getData(final Image image) {
        return null;
    }

}
