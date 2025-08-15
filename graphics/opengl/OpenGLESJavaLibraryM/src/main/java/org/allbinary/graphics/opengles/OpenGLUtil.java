package org.allbinary.graphics.opengles;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.device.LoadTextures;
import org.allbinary.device.OpenGLESGraphics;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.opengles.renderer.RendererStrings;
import org.allbinary.image.PreResourceImageUtil;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.image.opengles.OpenGLImageCache;
import org.allbinary.image.opengles.OpenGLImageCacheFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.SynchObject;
import org.allbinary.util.BasicArrayList;

public class OpenGLUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final OpenGLUtil instance = new OpenGLUtil();

    public static OpenGLUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final RendererStrings renderStrings = RendererStrings.getInstance();
    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    protected final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();

    private final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();

    private final SynchObject lockObject = new SynchObject();
    private final SynchObject object = new SynchObject();
    private final BasicArrayList list = new BasicArrayList();
    
    public final BasicArrayList runnableList = new BasicArrayList();

    private boolean created = false;
    
    public void onSurfaceCreated(final GL10 gl) {
        try {
            if(!created) {
                created = true;
                PreLogUtil.put(CommonLabels.getInstance().START_LABEL + OpenGLCapabilities.getInstance().toString(), this, this.renderStrings.ON_SURFACE_CREATED);    
            }

        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, this.renderStrings.ON_SURFACE_CREATED, e);
        }
    }

    public void onSurfaceCreated(final GL10 gl, final LoadTextures loadTextures) {
        try {
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

            // gl.glMatrixMode(GL10.GL_MODELVIEW);
            loadTextures.load(gl);

            OpenGLLogUtil.getInstance().logError(gl);

        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, this.renderStrings.ON_SURFACE_CREATED, e);
        }
    }
    
    private boolean surfaceCreatedAndInitialized = false;

    public void onSurfaceChanged(final GL10 gl, final OpenGLESGraphics graphics) throws Exception {
        if (!surfaceCreatedAndInitialized) {
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

        this.processRunnables();
        this.set(gl);

    }

    public Image add(final Image image) {

        final Image encapsulateImage = preResourceImageUtil.encapsulate(image);
        if (encapsulateImage != image) {

//            final Thread thread = Thread.currentThread();
//            logUtil.put(new StringMaker().append("add image ").append(thread.getName()).toString(), this, this.renderStrings.ON_SURFACE_CHANGED);

            synchronized(object) {
                list.add(encapsulateImage);
            }

            //logUtil.put(new StringMaker().append("add image: ").append(encapsulateImage).append(CommonSeps.getInstance().SPACE).append(thread.getName()).toString(), this, this.renderStrings.ON_SURFACE_CHANGED);
            return encapsulateImage;
        }

        return image;
    }

    public void clear() {

//        final Thread thread = Thread.currentThread();
//        logUtil.put(new StringMaker().append("clear: ").append(thread.getName()).toString(), this, RendererStrings.getInstance().ON_SURFACE_CHANGED);
        synchronized(object) {
            list.clear();
        }
    }

    public void add(final Runnable runnable) {
        
        synchronized (lockObject) {
            
            //logUtil.put("try to add", this, RendererStrings.getInstance().ON_SURFACE_CHANGED);
            if(!this.runnableList.contains(runnable)) {
                //logUtil.put("add", this, RendererStrings.getInstance().ON_SURFACE_CHANGED);
                this.runnableList.add(runnable);
                displayInfoSingleton.add(this.canvasStrings.SCALED_IMAGES);
            }
        }
        
    }

    private void processRunnables() {
        
        synchronized (lockObject) {
            
            Runnable runnable;
            final int size = this.runnableList.size();
            //logUtil.put(new StringMaker().append("size: ").append(size).toString(), this, RendererStrings.getInstance().ON_SURFACE_CHANGED);
            for (int index = 0; index < size; index++) {
                runnable = (Runnable) this.runnableList.get(index);
                runnable.run();
            }

            this.runnableList.clear();

        }

    }

    private void set(GL10 gl) throws Exception {
        
        final int size = this.list.size();
        
//        final Thread thread = Thread.currentThread();
//        final StringMaker stringMaker = new StringMaker();
//        final String THREAD = "thread: ";
//        stringMaker.delete(0, stringMaker.length());
//        logUtil.put(stringMaker.append("size: ").append(THREAD).append(size).append(thread.getName()).toString(), this, this.renderStrings.ON_SURFACE_CHANGED);

//        final String IMAGE = "image: ";

        Object image;
        for (int index2 = 0; index2 < size; index2++) {
            image = this.list.get(index2);
//            stringMaker.delete(0, stringMaker.length());
//            logUtil.put(stringMaker.append(index2).append(IMAGE).append(image).append(CommonSeps.getInstance().SPACE).append(thread.getName()).toString(), this, this.renderStrings.ON_SURFACE_CHANGED);
            ((OpenGLESImage) image).set(gl);
        }

    }
    
}
