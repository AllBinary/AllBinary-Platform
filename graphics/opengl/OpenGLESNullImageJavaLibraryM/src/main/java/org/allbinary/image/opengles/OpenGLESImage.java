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
package org.allbinary.image.opengles;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.lcdui.Image;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.platform.graphics.PlatformBitmapBase;
import org.allbinary.platform.graphics.PlatformBitmapBaseFactory;
import org.allbinary.platform.opengles.PlatformTextureBaseFactory;
import org.allbinary.util.BasicArrayList;

public class OpenGLESImage extends Image
implements OpenGLSurfaceChangedInterface
{
    public static final BasicArrayList texture2dList = new BasicArrayList();
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    protected final PlatformTextureBaseFactory textureFactory;

    public final PlatformBitmapBase openGLBitmap;
    
    protected int textureID = -1;

    public OpenGLESImageProcessor imageProcessor = OpenGLESImageProcessor.getInstance();
    public int angle;
    public int alpha;
    
    public OpenGLESImage(final Image image, final PlatformBitmapBaseFactory bitmapFactory, 
        final PlatformTextureBaseFactory textureFactory)
    {
        //super(image);
        this.openGLBitmap = bitmapFactory.createBitmap(image);
        this.textureFactory = textureFactory;
    }
    
    public void set(GL gl) throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
 
    protected boolean initTexture(GL10 gl)
    {
        return false;
    }
    
    public void drawRegion(GL10 gl, int viewHeight, 
            float x_src, float y_src, 
            float width, float height, 
            int x, int y, int z)
    {
    }    

    public void draw(GL10 gl, int x, int y, int z)
    {
    }
    
    public static final int TYPE = 4;

    public int getType() {
        return TYPE;
    }
    
}
