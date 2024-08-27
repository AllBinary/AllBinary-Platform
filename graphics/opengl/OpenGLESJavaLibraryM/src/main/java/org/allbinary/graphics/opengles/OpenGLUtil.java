package org.allbinary.graphics.opengles;

import javax.microedition.khronos.opengles.GL10;

import org.allbinary.device.LoadTextures;
import org.allbinary.device.OpenGLESGraphics;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.opengles.renderer.RendererStrings;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.image.opengles.OpenGLImageCache;
import org.allbinary.image.opengles.OpenGLImageCacheFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

public class OpenGLUtil
{
    private static final OpenGLUtil instance = new OpenGLUtil();

    public static OpenGLUtil getInstance()
    {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final RendererStrings renderStrings = RendererStrings.getInstance();
    
    public final BasicArrayList threadNameList = new BasicArrayList();
    public final BasicArrayList listOfList = new BasicArrayList();
    
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
            
            PreLogUtil.put(CommonLabels.getInstance().START_LABEL + OpenGLCapabilities.getInstance().toString(), this, this.renderStrings.ON_SURFACE_CREATED);

            // gl.glMatrixMode(GL10.GL_MODELVIEW);

            loadTextures.load(gl);

            OpenGLLogUtil.getInstance().logError(gl);
            
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.renderStrings.ON_SURFACE_CREATED, e));
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
        
        
        Object image;
        final int size = this.threadNameList.size();
//        final String threadName = Thread.currentThread().getName();
//        final StringMaker stringMaker = new StringMaker();
//        LogUtil.put(LogFactory.getInstance(stringMaker.append("size: ").append(size).append(threadName).toString(), this, this.renderStrings.ON_SURFACE_CHANGED));

//        final String IMAGE = "image: ";
//        final String THREAD = "thread: ";
        for(int index = 0; index < size; index++) {
//            stringMaker.delete(0, stringMaker.length());
//            LogUtil.put(LogFactory.getInstance(stringMaker.append(THREAD).append(this.threadNameList.get(index)).toString(), this, this.renderStrings.ON_SURFACE_CHANGED));
            final BasicArrayList list = (BasicArrayList) this.listOfList.get(index);
            final int size2 = list.size();
            for(int index2 = 0; index2 < size2; index2++) {
                image = list.get(index2);
//                stringMaker.delete(0, stringMaker.length());
//                LogUtil.put(LogFactory.getInstance(stringMaker.append(index2).append(IMAGE).append(image).append(CommonSeps.getInstance().SPACE).append(threadName).toString(), this, this.renderStrings.ON_SURFACE_CHANGED));
                ((OpenGLESImage) image).set(gl);
            }
        }

    }
}
