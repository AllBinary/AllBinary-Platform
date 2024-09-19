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
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.game.resource.GDResources;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.system.Memory;
import org.allbinary.thread.BaseImageLoadingProcessor;
import org.allbinary.thread.ConcurrentImageLoadingProcessor;
import org.allbinary.thread.SimpleImageLoadingProcessor;
import org.allbinary.util.BasicArrayList;

public class ImageCache extends ImageCacheBase {

    private final BaseImageLoadingProcessor baseImageLoadingProcessor;
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final ResourceUtil resourceUtil = ResourceUtil.getInstance();

    public final BasicArrayList loadNowList = new BasicArrayList();
    public final BasicArrayList loadList = new BasicArrayList();
    //public final BasicArrayList loadImageAfterList = new BasicArrayList();
    public final BasicArrayList loadAfterList = new BasicArrayList();

    private final Object lock = new Object();

    protected ImageCache() // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
        BaseImageLoadingProcessor baseImageLoadingProcessor = 
            null; 
            //BaseImageLoadingProcessor.getInstance();
        
        final Features features = Features.getInstance();
        final boolean isHTML = features.isDefault(HTMLFeatureFactory.getInstance().HTML);
        
        if(isHTML) {
            baseImageLoadingProcessor = new SimpleImageLoadingProcessor(this);
        } else {
            baseImageLoadingProcessor = new ConcurrentImageLoadingProcessor(this);
        }
        
        this.baseImageLoadingProcessor = baseImageLoadingProcessor;
    }

    //AllBinaryRendererBase3
    public void addListener(Object renderer) {
        
    }

    public void waitForLoadNow() throws Exception {
        while (loadNowList.isEmpty()) {
            Thread.sleep(120);
        }
    }
    
    public void loadImageForAnimation() throws Exception {
        LazyImageRotationAnimation lazyImageRotationAnimation = null;
        synchronized (lock) {
            if(loadNowList.isEmpty()) return;
            lazyImageRotationAnimation = (LazyImageRotationAnimation) loadNowList.get(0);
        }
        if(this.loadImageForAnimation(lazyImageRotationAnimation)) {
            synchronized (lock) {
                loadNowList.remove(lazyImageRotationAnimation);
            }
        }
    }
    
//    private void loadImageForAnimation() throws Exception {
//        LazyImageRotationAnimation lazyImageRotationAnimation = null;
//        synchronized (lock) {
//            lazyImageRotationAnimation = (LazyImageRotationAnimation) loadNowList.remove(0);
//        }
//        this.loadImageForAnimation(lazyImageRotationAnimation);
//    }

    public void loadImages() throws Exception {
        while (!loadList.isEmpty() || !loadNowList.isEmpty()) {

            loadImageForAnimations();
            //LogUtil.put(LogFactory.getInstance("load image", this, commonStrings.RUN));
            loadImage();
        }
    }
    
    public void loadImageForAnimations() throws Exception {
        while (!loadNowList.isEmpty()) {
            //LogUtil.put(LogFactory.getInstance("load lazy animation image", this, commonStrings.RUN));
            loadImageForAnimation();
        }
    }
    
    public void loadRemainingAnimations() throws Exception {
        //LogUtil.put(LogFactory.getInstance("load remaining lazy animations", this, commonStrings.RUN));
        while (!this.loadAfterList.isEmpty() || !loadNowList.isEmpty()) {
            
            while (!loadNowList.isEmpty()) {
                //LogUtil.put(LogFactory.getInstance("load lazy animation image", this, commonStrings.RUN));
                loadImageForAnimation();
            }
            
            LazyImageRotationAnimation lazyImageRotationAnimation = null;
            synchronized (lock) {
                //this.loadImageAfterList.remove(0);
                lazyImageRotationAnimation = (LazyImageRotationAnimation) loadAfterList.remove(0);
            }
            this.loadImageForAnimation(lazyImageRotationAnimation);
        }
    }
    
    private boolean loadImageForAnimation(final LazyImageRotationAnimation lazyImageRotationAnimation) throws Exception {
        final Image image = lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage();
        
        if(this.loadImage(image)) {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("processing loaded resource: ").append(lazyImageRotationAnimation).toString(), this, commonStrings.RUN));
            lazyImageRotationAnimation.setRealAnimation();
            return true;
        }
        return false;
    }

    private void loadImage() throws Exception {
        Image image = null;
        synchronized (lock) {
            if(loadList.size() == 0) return;
            image = (Image) loadList.remove(0);
        }
        this.loadImage(image);
    }

    private boolean loadImage(final Image image) throws Exception {

        if (image.isReady()) {
            
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("already loaded resource image: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
            return true;
            
        } else {
            
            if(image.getImage() != null) {
                //HTML only
                if(image.setReady()) {
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append("setReady resource HTML5 image: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
                    return true;
                }
            } else {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append("attempt loading resource image: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
                final String key = image.getName();
                final Image image2 = this.creatImage(key);
                if (image2.isReady()) {
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append("setImage resource Now image: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
                    this.init(image, image2);
                    return true;
                } else {
                    //HTML only
                    LogUtil.put(LogFactory.getInstance(new StringMaker().append("setImage resource HTML5 image: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
                    image.setImage(image2.getImage());
                }
            }
            
        }
        return false;
    }

    protected void init(final Image image, final Image image2) throws Exception {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("loading resource: ").append(image).append(image.getName()).append(commonSeps.SPACE).append(image.getWidth()).append(commonSeps.SPACE).append(image.getHeight()).toString(), this, commonStrings.RUN));
        image.init(image2.getImage());
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append("loaded resource: ").append(image).append(image.getName()).append(commonSeps.SPACE).append(image.getWidth()).append(commonSeps.SPACE).append(image.getHeight()).toString(), this, commonStrings.RUN));
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

        this.baseImageLoadingProcessor.runTask();

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
    
    public void add(final LazyImageRotationAnimation lazyImageRotationAnimation) {
        synchronized (lock) {
            //this.loadImageAfterList.add(lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage());
            this.loadAfterList.add(lazyImageRotationAnimation);
        }
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
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("insert resource: ").append(image).append(image.getName()).toString(), this, commonStrings.RUN));
                        
            synchronized (lock) {        
                this.loadNowList.add(lazyImageRotationAnimation);
                this.loadAfterList.remove(lazyImageRotationAnimation);
                
//                int indexOfImage = -1;
//                while((indexOfImage = this.loadImageAfterList.indexOf(lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage())) >= 0) {
//                    this.loadImageAfterList.remove(indexOfImage);
//                    final LazyImageRotationAnimation lazyImageRotationAnimation2 = (LazyImageRotationAnimation) this.loadAfterList.remove(indexOfImage);
//                    if(lazyImageRotationAnimation2 == lazyImageRotationAnimation) {
//                        LogUtil.put(LogFactory.getInstance(new StringMaker().append("insert resource: ").append(lazyImageRotationAnimation2).toString(), this, commonStrings.RUN));
//                    } else {
//                        LogUtil.put(LogFactory.getInstance(new StringMaker().append("insert duplicate resource: ").append(lazyImageRotationAnimation2).toString(), this, commonStrings.RUN));
//                    }
//                    this.loadNowList.add(lazyImageRotationAnimation2);
//                }

            }

            this.baseImageLoadingProcessor.runTask();

        //}
    }
}
