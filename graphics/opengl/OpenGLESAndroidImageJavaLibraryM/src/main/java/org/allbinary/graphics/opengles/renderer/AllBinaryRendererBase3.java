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
package org.allbinary.graphics.opengles.renderer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class AllBinaryRendererBase3 {
    
    public final BasicArrayList list = new BasicArrayList();
    
    protected void update(final GL10 gl) throws Exception
    {
        //logUtil.put(CommonLabels.getInstance().START_LABEL + list, this, commonStrings.UPDATE);
        
        while(list.size() > 0)
        {
            final OpenGLESImage openGLESImage = (OpenGLESImage) list.remove(0);
            if(openGLESImage != null) {
                openGLESImage.set(gl);
            }
        }
    }
    
    public void add(final Image image) {
        this.list.add(image);
    }
    
}
