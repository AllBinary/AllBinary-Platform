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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class AllBinaryRendererBase3 {
    
    public final BasicArrayList list = new BasicArrayList();
    
    protected void update(final GL10 gl) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START_LABEL + list, this, commonStrings.UPDATE));
        
        while(list.size() > 0)
        {
            ((OpenGLESImage) list.remove(0)).set(gl);
        }
    }
    
    public void add(final Image image) {
        if(image != null) {
            this.list.add(image);
        } else {
            LogUtil.put(LogFactory.getInstance("Image was null", this, CommonStrings.getInstance().ADD, new Exception()));
        }
    }
    
}
