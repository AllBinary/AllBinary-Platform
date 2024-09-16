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
package org.allbinary.image;

import java.io.InputStream;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.image.LazyImageRotationAnimation;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.game.resource.GDResources;
import org.allbinary.graphics.opengles.renderer.AllBinaryRendererBase3;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.system.Memory;
import org.allbinary.thread.ABRunnable;
import org.allbinary.thread.ImageThreadPool;
import org.allbinary.util.BasicArrayList;

public class ImageCache extends ImageCacheBase {

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final ResourceUtil resourceUtil = ResourceUtil.getInstance();

    public final BasicArrayList loadNowList = new BasicArrayList();
    public final BasicArrayList loadList = new BasicArrayList();
    public final BasicArrayList loadAfterList = new BasicArrayList();

    private final Object lock = new Object();

    private final ABRunnable runnable = new ABRunnable() {

        public void run() {
            try {
                this.setRunning(true);
                //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.RUN));

                while (loadNowList.isEmpty()) {
                    Thread.sleep(120);
                }
                
                while (!loadList.isEmpty() || !loadNowList.isEmpty()) {
                    
                    while(!loadNowList.isEmpty()) {
                        loadImageForAnimation();
                    }
                    loadImage();
                }

                loadRemainingAnimations();
                
                this.setRunning(false);

//            LogUtil.put(LogFactory.getInstance(commonStrings.END, this, commonStrings.RUN));
            } catch (Exception e) {
                this.setRunning(false);
                final CommonStrings commonStrings = CommonStrings.getInstance();
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
            }
        }

    };

    protected ImageCache() // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
    }

    public void addListener(AllBinaryRendererBase3 renderer) {
        
    }
    
    private void loadImageForAnimation() throws Exception {
        if (!loadNowList.isEmpty()) {
            LazyImageRotationAnimation lazyImageRotationAnimation = null;
            synchronized (lock) {
                lazyImageRotationAnimation = (LazyImageRotationAnimation) loadNowList.remove(0);
            }
            this.loadImageForAnimation(lazyImageRotationAnimation);
        }
    }

    private void loadRemainingAnimations() throws Exception {
        while (!this.loadAfterList.isEmpty()) {
            LazyImageRotationAnimation lazyImageRotationAnimation = null;
            synchronized (lock) {
                lazyImageRotationAnimation = (LazyImageRotationAnimation) loadAfterList.remove(0);
            }
            this.loadImageForAnimation(lazyImageRotationAnimation);
        }
    }
    
    private void loadImageForAnimation(LazyImageRotationAnimation lazyImageRotationAnimation) throws Exception {
        final Image image = lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage();
        this.loadImage(image);
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("processing loaded: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
        lazyImageRotationAnimation.setRealAnimation();
    }

    private void loadImage() throws Exception {
        Image image = null;
        synchronized (lock) {
            if(loadList.size() == 0) return;
            image = (Image) loadList.remove(0);
        }
        this.loadImage(image);
    }

    private void loadImage(final Image image) throws Exception {

        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("attempt loading: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
        if (image.getImage() == null) {
            final String key = image.getName();
            final Image image2 = this.creatImage(key);
            this.init(image, image2);
        } else {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("already loaded: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
        }
    }

    protected void init(final Image image, final Image image2) throws Exception {
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("loading: ").append(image).append(image.getName()).append(commonSeps.SPACE).append(image.getWidth()).append(commonSeps.SPACE).append(image.getHeight()).toString(), this, commonStrings.RUN));
        image.init(image2.getImage());
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("loaded: ").append(image).append(image.getName()).append(commonSeps.SPACE).append(image.getWidth()).append(commonSeps.SPACE).append(image.getHeight()).toString(), this, commonStrings.RUN));
    }

    protected Image creatImage(final String key) throws Exception {
        final InputStream inputStream = resourceUtil.getResourceAsStream(key);
        final Image image = Image.createImage(inputStream);
        image.setName(key);
        return image;
    }

    public Image get(final String caller, final int width, final int height)
        throws Exception {
        int foundIndex = this.getIndex(width, height);
        Image image = this.getFromAvailable(foundIndex, width, height);

        if (image == null) {
            volume += width * height;
            if (volume > 32000) {
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Image for: ").append(caller).toString(), this, CommonStrings.getInstance().GET));
                System.gc();
                //System.gc();
                volume = 0;
                //LogUtil.put(LogFactory.getInstance(Memory.getInfo(), this, CommonStrings.getInstance().GET));
            }

            image = this.createImage(caller, width, height);
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Image: ").append(image.getName()).toString(), this, CommonStrings.getInstance().GET));

            if (nextIndex > widths.length - 1) {
                if (foundIndex == -1) {
                    foundIndex = nextIndex;

                    widths[nextIndex] = width;
                    heights[nextIndex] = height;

                    nextIndex++;
                }

                listOfList[foundIndex].add(image);

            }

        }

        return image;
    }

    public Image get(final Object key) throws Exception {
        Image image = (Image) this.hashtable.get(key);

        if (image == null) {
            //final ResourceUtil resourceUtil = ResourceUtil.getInstance();
            //final InputStream inputStream = resourceUtil.getResourceAsStream((String) key);
            final InputStream inputStream = null;

            //if(inputStream == null) {
            //throw new RuntimeException(new StringMaker().append("Image resource is not available for key: ").append(key).toString());
            //}
            try {
                //LogUtil.put(LogFactory.getInstance(Memory.getInfo(), this, CommonStrings.getInstance().GET));
                image = this.createImage(key, inputStream);
            } catch (Exception e) {
                LogUtil.put(LogFactory.getInstance("Exception: Trying Again After GC", this, CommonStrings.getInstance().GET, e));

                LogUtil.put(LogFactory.getInstance(new StringMaker().append("InputStream: ").append(inputStream).toString(), this, CommonStrings.getInstance().GET));
                System.gc();
                System.gc();
                LogUtil.put(LogFactory.getInstance(Memory.getInfo(), this, CommonStrings.getInstance().GET));
                Thread.sleep(100);
                image = this.createImage(key, inputStream);
            }
            //inputStream.close();
            //Put in the name is really only for debugging
//            if(DebugFactory.getInstance() != NoDebug.getInstance())
//            {
//                this.hashtable.put(key, image);
//            }
            this.hashtable.put(key, image);
        }

//        final SpacialStrings spacialStrings = SpacialStrings.getInstance();
//        LogUtil.put(LogFactory.getInstance(spacialStrings.WIDTH_LABEL + image.getWidth() + spacialStrings.HEIGHT_LABEL + image.getHeight(), this, CommonStrings.getInstance().CONSTRUCTOR));
//        LogUtil.put(LogFactory.getInstance(key + " = " + image.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
        return image;
    }

    private int getIndex(final Object key) {
        final GDResources gdResources = GDResources.getInstance();
        final String[] resourceStringArray = gdResources.resourceStringArray;
        final int size = resourceStringArray.length;
        for (int index = 0; index < size; index++) {
            if (resourceStringArray[index] == key) {
                return index;
            }
        }
        throw new RuntimeException();
    }

    //TWB - Remove temp hack
    private final String ATLAS = "dungeon_b.png";
    private final String PROPS = "props.png";
    protected Image createImage(final Object key, final InputStream inputStream)
        throws Exception {
        
        if(((String) key).compareTo(ATLAS) == 0 || ((String) key).compareTo(PROPS) == 0) {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("create now: ").append(key).toString(), this, commonStrings.RUN));
            return this.creatImage((String) key);
        }

        if (!this.runnable.isRunning()) {
            ImageThreadPool.getInstance().runTask(this.runnable);
        }

        final GDResources gdResources = GDResources.getInstance();
        final int index = this.getIndex(key);
        final int width = gdResources.imageResourceWidthArray[index];
        final int height = gdResources.imageResourceHeightArray[index];

        final Image image = this.createImageLater((String) key, width, height);

        synchronized (lock) {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("add: ").append(image.getName()).append(index).toString(), this, commonStrings.RUN));
            loadList.add(image);
        }

        return image;
    }

    protected Image createImageLater(final String key, final int width, final int height) throws Exception {
        return Image.createImageLater(key, width, height);
    }
    
    public void insertFirst(final LazyImageRotationAnimation lazyImageRotationAnimation) {
            final Image image = lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage();
//            if (image.getImage() != null) {
//                try {
//                    LogUtil.put(LogFactory.getInstance("animation image is already loaded: " + image.getName(), this, commonStrings.RUN));
//                    lazyImageRotationAnimation.setRealAnimation();
//                } catch (Exception e) {
//                    LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
//                }
//            } else {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("insert: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
                        
            synchronized (lock) {            
                loadNowList.add(0, lazyImageRotationAnimation);
                this.loadAfterList.remove(lazyImageRotationAnimation);
            }

            if (!this.runnable.isRunning()) {
                ImageThreadPool.getInstance().runTask(this.runnable);
            }

        //}
    }
}
