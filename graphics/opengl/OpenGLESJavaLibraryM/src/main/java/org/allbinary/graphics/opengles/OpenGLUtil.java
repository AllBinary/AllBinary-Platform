package org.allbinary.graphics.opengles;

import javax.microedition.khronos.opengles.GL10;

import org.allbinary.device.LoadTextures;
import org.allbinary.device.OpenGLESGraphics;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.image.opengles.OpenGLImageCache;
import org.allbinary.image.opengles.OpenGLImageCacheFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class OpenGLUtil
{
    private static final OpenGLUtil instance = new OpenGLUtil();

    public static OpenGLUtil getInstance()
    {
        return instance;
    }

    public final BasicArrayList list = new BasicArrayList();
    
    public void onSurfaceCreated(final GL10 gl, final LoadTextures loadTextures)
    {
        try
        {
            //gl.glHint(GL10.GL_FOG_HINT, GL10.GL_FASTEST);
            //gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_FASTEST);
            //gl.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_FASTEST);
            //gl.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_FASTEST);

            //gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

            //gl.glHint(GL10.GL_FOG_HINT, GL10.GL_NICEST);
            //gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST);
            //gl.glHint(GL10.GL_POINT_SMOOTH_HINT, GL10.GL_NICEST);
            //gl.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_NICEST);

            //gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
            
            PreLogUtil.put(CommonLabels.getInstance().START_LABEL + OpenGLCapabilities.getInstance().toString(), this, "onSurfaceCreated");

            // gl.glMatrixMode(GL10.GL_MODELVIEW);

            loadTextures.load(gl);

            OpenGLLogUtil.getInstance().logError(gl);
            
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "onSurfaceCreated", e));
        }
    }

    private boolean surfaceCreatedAndInitialized = false;
    
    public void onSurfaceChanged(final GL10 gl, final OpenGLESGraphics graphics) throws Exception
    {
        if (!surfaceCreatedAndInitialized)
        {
            graphics.init();
            surfaceCreatedAndInitialized = true;
        }

        //Seems that mutable images must be reloaded on graphics change?
        graphics.update();

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        progressCanvas.update(graphics);

//        final ChoiceGroupImageUtil choiceGroupImageUtil = ChoiceGroupImageUtil.getInstance();
//        choiceGroupImageUtil.init();
//        choiceGroupImageUtil.update(graphics);

        ((OpenGLImageCache) OpenGLImageCacheFactory.getInstance()).update(gl);
        
        final int size = this.list.size();
        Object image;
        for(int index = 0; index < size; index++) {
            image = this.list.get(index);
            ((OpenGLESImage) image).set(gl);
        }
        
        this.list.clear();

    }
}
