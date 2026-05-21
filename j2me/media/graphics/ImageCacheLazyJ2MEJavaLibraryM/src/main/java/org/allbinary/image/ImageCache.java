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
import javax.microedition.lcdui.NullImage;

import org.allbinary.J2MEUtil;
import org.allbinary.animation.image.LazyImageRotationAnimation;
import org.allbinary.canvas.GameGlobalsFactory;
import org.allbinary.canvas.Processor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.game.canvas.ABToGBUtil;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.gd.resource.GDLazyResources;
import org.allbinary.game.gd.resource.GDResources;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.system.Memory;
import org.allbinary.thread.BaseImageLoadingProcessor;
import org.allbinary.thread.ConcurrentImageLoadingProcessor;
import org.allbinary.thread.SynchObject;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class ImageCache extends ImageCacheBase {
    
    public static final ImageCache NULL_IMAGE_CACHE = new ImageCache();
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final BaseImageLoadingProcessor concurrentImageLoadingProcessor = new ConcurrentImageLoadingProcessor(this);
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final ResourceUtil resourceUtil = ResourceUtil.getInstance();

    private final GameGlobalsFactory gameGlobalsFactory = GameGlobalsFactory.getInstance();
    private final GDResources gdResources = GDResources.getInstance();
    
    public final BasicArrayList loadNowList = new BasicArrayListD();
    public final BasicArrayList loadSoonList = new BasicArrayListD();
    public final BasicArrayList loadList = new BasicArrayListD();
    //public final BasicArrayList loadImageAfterList = new BasicArrayListD();
    public final BasicArrayList loadAfterList = new BasicArrayListD();

    private final SynchObject lock = new SynchObject();
    
    private boolean firstTime = true;
    private int totalLoaded = 0;
    public boolean progressEnded = false;
    public boolean hasAnyLazyAnimationFactories = false;

    private class NotHTMLProcessor extends Processor {
        
        public void process() {

            concurrentImageLoadingProcessor.runTask();
            
        }

    }

    private class NotHTMLEndProcessor extends Processor {
        
        public void process() {

            final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
            progressCanvas.endIfPaintedSinceStart();
            
        }

    }

    private class HTMLEndProcessor extends Processor {

        public void process() {

            final int size = gdResources.currentLayoutRequiredTotal;;
            //if(size != 0) this.logUtil.putF("totalLoaded: " + totalLoaded, this, this.commonStrings.RUN);
            if (size == 0) {
                //this.logUtil.putF(new StringMaker().append("end with totalLoaded loaded: ").append(totalLoaded).append(" i:").append(size).toString(), this, this.commonStrings.RUN);
                final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
                progressCanvas.endIfPaintedSinceStart();
            } else if (totalLoaded > size / 12) {
                //this.logUtil.putF(new StringMaker().append("end with totalLoaded loaded: ").append(totalLoaded).append(" i:").append(size).toString(), this, this.commonStrings.RUN);
                final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
                progressCanvas.endIfPaintedSinceStart();
                endProcessor = new NotHTMLEndProcessor();
            }
            
        }

    }
    
    private class FirstProcessor extends Processor {
        
        public void process() {
            ImageCache.this.firstProcess();
        }

    }
    
    private Processor processor = new FirstProcessor();
    private Processor endProcessor = Processor.getInstance();
    
    public ImageCache() // CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface)
    {
    }

    public void firstProcess() {
        final LogUtil logUtil = LogUtil.getInstance();
        final boolean isHTML = J2MEUtil.isHTML();
        //logUtil.putF(new StringMaker().append("isHTML: ").append(isHTML).toString(), this, this.commonStrings.RUN);
        if (isHTML) {
            this.processor = Processor.getInstance();
            this.endProcessor = new HTMLEndProcessor();
        } else {
            //logUtil.putF("Setting processor", this, this.commonStrings.RUN);
            this.processor = new NotHTMLProcessor();
            this.endProcessor = new NotHTMLEndProcessor();
            try {
                runTask();
            } catch (Exception e) {
                logUtil.putF(this.commonStrings.EXCEPTION, this, this.commonStrings.END_METHOD_NAME);
            }
        }
    }

    //AllBinaryRendererBase3
    public void addListener(Object renderer) {
        
    }

    public void waitForLoadNow() throws Exception {
        if(this.firstTime) {
            
            //this.logUtil.putF(this.commonStrings.START, this, "waitForLoadNow");
            final ABToGBUtil abToGBUtil = ABToGBUtil.getInstance();
            final AllBinaryGameCanvas abCanvas = (AllBinaryGameCanvas) abToGBUtil.abCanvas;
            while (this.loadNowList.isEmpty() && (!abCanvas.isInitialized() || (abCanvas.isInitialized() && this.hasAnyLazyAnimationFactories)) && !this.progressEnded) {
                //this.logUtil.putF(new StringMaker().append("Still Empty: ").append(this.loadNowList.size()).append(" after: ").append(this.loadAfterList.size()).append(" all: ").append(this.loadList.size()).toString(), this, "waitForLoadNow");
                Thread.sleep(120);
            }
            this.firstTime = false;
        }
    }

    //private final String LOAD_IMAGE_FOR_ANIMATION = "loadImageForAnimation";
    private final String LOAD_IMAGE_FOR_ANIMATION = "Load Image Animation";
    public void loadImageForAnimation() throws Exception {
        LazyImageRotationAnimation lazyImageRotationAnimation = null;
        synchronized (this.lock) {
            if(this.loadNowList.isEmpty()) {
                this.endProcessor.process();
                
                if(this.loadSoonList.isEmpty()) {
                    
                    if(this.loadAfterList.isEmpty()) {
                        
                        if (this.firstTime) {
                        } else if (this.gameGlobalsFactory.newCanvas) {
                        } else //if (randomFactory.getAbsoluteNextInt(180) == 5) 
                        {
                            this.loadNextImage();
                        }

                        return;

                    } else {
                        lazyImageRotationAnimation = (LazyImageRotationAnimation) this.loadAfterList.get(0);
                        //this.logUtil.putF("loadAfterList: " + lazyImageRotationAnimation, this, this.commonStrings.RUN);
                        if (this.loadImageForLazyAnimation(lazyImageRotationAnimation)) {
                            //this.logUtil.putF("Loaded associated Animation for Game Layer that is painted", this, this.commonStrings.RUN);
                            this.loadAfterList.remove(lazyImageRotationAnimation);
                        }
                    }

                } else {
                    lazyImageRotationAnimation = (LazyImageRotationAnimation) this.loadSoonList.get(0);
                    //this.logUtil.putF("loadSoonList: " + lazyImageRotationAnimation, this, this.commonStrings.RUN);
                    if (this.loadImageForLazyAnimation(lazyImageRotationAnimation)) {
                        //this.logUtil.putF("Loaded associated Animation for Game Layer that is painted", this, this.commonStrings.RUN);
                        this.loadSoonList.remove(lazyImageRotationAnimation);
                    }
                }
                
                return;
            }
            lazyImageRotationAnimation = (LazyImageRotationAnimation) this.loadNowList.get(0);
        }
        if(this.loadImageForLazyAnimation(lazyImageRotationAnimation)) {
            //this.logUtil.putF(new StringMaker().append("loadNowList loaded: ").append(this.totalLoaded).append(" i:").append(this.loadList.size()).toString(), this, this.commonStrings.RUN);
            //final Image image = lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage();
            //this.logUtil.putF("loadNowList loaded: " + image.getName(), this, this.commonStrings.RUN);
            synchronized (this.lock) {
                this.loadNowList.remove(lazyImageRotationAnimation);
            }

            if (lazyImageRotationAnimation.layoutIndex != 0) {
                final BasicArrayList list = this.getAssociated(lazyImageRotationAnimation);
                //this.logUtil.putF("loadNowList list: " + list, this, this.commonStrings.RUN);
                synchronized (this.lock) {
                    final int size = list.size();
                    if(size > 0) {
                        //this.logUtil.putF("addAssociated: " + size, this, this.commonStrings.RUN);
                        this.loadSoonList.addAllList(list);
                    }
                }
            }

            //this.logUtil.putF("should end progress? " + this.loadNowList.size(), this, this.commonStrings.RUN);
            final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
            final boolean isHTML = J2MEUtil.isHTML();
            if (this.loadNowList.isEmpty() && (!isHTML || this.firstTime)) {
                //this.logUtil.putF("end progress", this, this.commonStrings.RUN);
                progressCanvas.endFromInitialLazyLoadingComplete();
            } else {
                if(this.totalLoaded % 10 == 0) {
                    progressCanvas.addNormalPortion(1, this.LOAD_IMAGE_FOR_ANIMATION);
                }
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
        while (!this.loadList.isEmpty() || !this.loadNowList.isEmpty()) {

            this.loadImageForAnimations();
            //this.logUtil.putF("load image", this, this.commonStrings.RUN);
            this.loadNextImage();
        }
    }
    
    public void loadImageForAnimations() throws Exception {
        while (!this.loadNowList.isEmpty()) {
            //this.logUtil.putF("load lazy animation image", this, this.commonStrings.RUN);
            this.loadImageForAnimation();
        }
    }
    
    public void loadRemainingAnimations() throws Exception {
        //this.logUtil.putF("load remaining lazy animations", this, this.commonStrings.RUN);
        while (!this.loadAfterList.isEmpty() || !this.loadNowList.isEmpty()) {
            
            while (!this.loadNowList.isEmpty()) {
                //this.logUtil.putF("load lazy animation image", this, this.commonStrings.RUN);
                this.loadImageForAnimation();
            }

            LazyImageRotationAnimation lazyImageRotationAnimation = null;
            synchronized (this.lock) {
                if(!this.loadAfterList.isEmpty())
                //this.loadImageAfterList.remove(0);
                lazyImageRotationAnimation = (LazyImageRotationAnimation) this.loadAfterList.removeAt(0);
            }

            if(lazyImageRotationAnimation != null) {
                this.loadImageForLazyAnimation(lazyImageRotationAnimation);
            }
        }
    }
    
    private boolean loadImageForLazyAnimation(final LazyImageRotationAnimation lazyImageRotationAnimation) throws Exception {
        final Image image = lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage();
        
        if(this.loadImage(image)) {
            //this.logUtil.putF(new StringMaker().append("processing loaded resource: ").append(lazyImageRotationAnimation).toString(), this, this.commonStrings.RUN);
            lazyImageRotationAnimation.setRealAnimation();
            return true;
        }
        return false;
    }

    private void loadNextImage() throws Exception {
        Image image = null;
        synchronized (this.lock) {
            if(this.loadList.size() == 0) {
                return;
            }
            image = (Image) this.loadList.removeAt(0);
        }
        this.loadImage(image);
    }

    private boolean loadImage(final Image image) throws Exception {

        if (image.isReady()) {
            
            //this.logUtil.putF(new StringMaker().append("already loaded resource image: ").append(image).append(image.getName()).toString(), this, this.commonStrings.RUN);
            return true;
            
        } else {
            
            if(image.getImage() != null) {
                //HTML only
                if(image.setReady()) {
                    this.totalLoaded++;
                    //this.logUtil.putF(new StringMaker().append("setReady resource HTML5 image: ").append(image).append(image.getName()).toString(), this, this.commonStrings.RUN);
                    return true;
                }
            } else {
                //this.logUtil.putF(new StringMaker().append("attempt loading resource image: ").append(image).append(image.getName()).toString(), this, this.commonStrings.RUN);
                final String key = image.getName();
                final Image image2 = this.creatImage(key);
                if (image2.isReady()) {
                    //this.logUtil.putF(new StringMaker().append("setImage resource Now image: ").append(image).append(image.getName()).toString(), this, this.commonStrings.RUN);
                    this.init(image, image2);
                    return true;
                } else {
                    //HTML only
                    //this.logUtil.putF(new StringMaker().append("setImage resource HTML5 image: ").append(image).append(image.getName()).toString(), this, this.commonStrings.RUN);
                    image.setImage(image2.getImage());
                }
            }
            
        }
        return false;
    }

    protected void init(final Image image, final Image image2) throws Exception {
        //this.logUtil.putF(new StringMaker().append("loading resource: ").append(image).append(image.getName()).append(commonSeps.SPACE).append(image.getWidth()).append(commonSeps.SPACE).append(image.getHeight()).toString(), this, this.commonStrings.RUN);
        image.init(image2.getImage());
        //this.logUtil.putF(new StringMaker().append("loaded resource: ").append(image).append(image.getName()).append(commonSeps.SPACE).append(image.getWidth()).append(commonSeps.SPACE).append(image.getHeight()).toString(), this, this.commonStrings.RUN);
    }

    protected Image creatImage(final String key) throws Exception {
        final InputStream inputStream = resourceUtil.getResourceAsStream(key);
        final Image image = Image.createImage(inputStream);
        image.setName(key);
        return image;
    }

    public Image get(final String caller, final int width, final int height)
        throws Exception {
        int foundIndex = this.getIndexWH(width, height);
        Image image = this.getFromAvailable(foundIndex, width, height);

        if (image == NullImage.NULL_IMAGE) {
            this.volume += width * height;
            if (this.volume > 32000) {
                //this.logUtil.putF(new StringMaker().append("Image for: ").append(caller).toString(), this, this.commonStrings.GET);
                System.gc();
                //System.gc();
                this.volume = 0;
                //this.logUtil.putF(Memory.getInfo(), this, this.commonStrings.GET);
            }

            image = this.createImage(caller, width, height);
            //this.logUtil.putF(new StringMaker().append("Image: ").append(image.getName()).toString(), this, this.commonStrings.GET);

            if (this.nextIndex > this.widths.length - 1) {
                if (foundIndex == -1) {
                    foundIndex = this.nextIndex;

                    this.widths[this.nextIndex] = width;
                    this.heights[this.nextIndex] = height;

                    this.nextIndex++;
                }

                this.listOfList[foundIndex].add(image);

            }

        }

        return image;
    }

    public Image getWithKey(final Object key) throws Exception {
        Image image = this.getImage(key);

        if (image == NullImage.NULL_IMAGE) {
            //final ResourceUtil resourceUtil = ResourceUtil.getInstance();
            //final InputStream inputStream = resourceUtil.getResourceAsStream((String) key);
            final InputStream inputStream = null;

            //if(inputStream == null) {
            //throw new RuntimeException(new StringMaker().append("Image resource is not available for key: ").append(key).toString());
            //}
            try {
                //this.logUtil.putF(Memory.getInfo(), this, this.commonStrings.GET);
                image = this.createImageFromInputStream(key, inputStream);
            } catch (Exception e) {
                this.logUtil.put("Exception: Trying Again After GC", this, this.commonStrings.GET, e);

                this.logUtil.putF(new StringMaker().append("InputStream: ").append(StringUtil.getInstance().toString(inputStream)).toString(), this, this.commonStrings.GET);
                System.gc();
                System.gc();
                this.logUtil.putF(Memory.getInfo(), this, this.commonStrings.GET);
                Thread.sleep(100);
                image = this.createImageFromInputStream(key, inputStream);
            }
            //inputStream.close();
            //Put in the name is really only for debugging
//            if(DebugFactory.getInstance() != NoDebug.getInstance())
//            {
//                this.hashtable.put(key, image);
//            }
            this.hashtable.put(key, image);
        }

//        final CommonLabels commonLabels = CommonLabels.getInstance();
//        this.logUtil.putF(this.commonLabels.WIDTH_LABEL + image.getWidth() + commonLabels.HEIGHT_LABEL + image.getHeight(), this, this.commonStrings.CONSTRUCTOR);
//        this.logUtil.putF(key + " = " + image.toString(), this, this.commonStrings.CONSTRUCTOR);
        return image;
    }

    public int getIndex(final Object key) {
        final GDResources gdResources = GDResources.getInstance();
        final String[] resourceStringArray = gdResources.resourceStringArray;
        final int size = resourceStringArray.length;
        for (int index = 0; index < size; index++) {
            if (resourceStringArray[index] == key) {
                return index;
            }
        }
        this.logUtil.putF(new StringMaker().append("unable to find key: ").append(StringUtil.getInstance().toString(key)).toString(), this, this.commonStrings.RUN);
        throw new RuntimeException();
    }

    protected Image createImageFromInputStream(final Object key, final InputStream inputStream)
        throws Exception {

        final GDLazyResources gdLazyResources = GDLazyResources.getInstance();        
        String[] resourceStringArray = gdLazyResources.requiredResourcesBeforeLoadingArray;
        final int size = resourceStringArray.length;
        for(int index = 0; index < size; index++) {
            //if(((String) key).compareTo(resourceStringArray[index]) == 0) {
            if(key == resourceStringArray[index]) {
                //this.logUtil.putF(new StringMaker().append("create now: ").append(key).toString(), this, this.commonStrings.RUN);
                return this.creatImage((String) key);
            }
        }

        this.runTask();

        final int index = this.getIndex(key);
        final int width = gdLazyResources.imageResourceWidthArray[index];
        final int height = gdLazyResources.imageResourceHeightArray[index];

        final Image image = this.createImageLater((String) key, width, height);

        synchronized (this.lock) {
            //this.logUtil.putF(new StringMaker().append("add: ").append(image.getName()).append(index).toString(), this, this.commonStrings.RUN);
            this.loadList.add(image);
        }

        return image;
    }

    protected Image createImageLater(final String key, final int width, final int height) throws Exception {
        return Image.createImageLater(key, width, height);
    }

    public BasicArrayList getAssociated(final LazyImageRotationAnimation lazyImageRotationAnimation) {
        final BasicArrayList list = new BasicArrayListD();
        
        synchronized (this.lock) {

            LazyImageRotationAnimation lazyImageRotationAnimation2 = null;
            final int size = this.loadAfterList.size();
            for(int index = 0; index < size; index++) {
                lazyImageRotationAnimation2 = (LazyImageRotationAnimation) this.loadAfterList.get(index);
                if(lazyImageRotationAnimation2.instanceId == lazyImageRotationAnimation.instanceId) {
                    list.add(lazyImageRotationAnimation2);
                }
            }
            
            final int size2 = list.size();
            for(int index = 0; index < size2; index++) {
                this.loadAfterList.remove(list.get(index));
            }
                
        }

        return list;
    }
    
    public void add(final LazyImageRotationAnimation lazyImageRotationAnimation) {
        synchronized (this.lock) {
            //this.logUtil.putF("adding to loadAfterList: " + lazyImageRotationAnimation, this, this.commonStrings.RUN);
            //this.loadImageAfterList.add(lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage());
            this.loadAfterList.add(lazyImageRotationAnimation);
        }
    }
    
    public void insertFirst(final LazyImageRotationAnimation lazyImageRotationAnimation) throws Exception {
            //final Image image = lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage();
//            if (image.getImage() != null) {
//                try {
//                    this.logUtil.putF("animation image is already loaded: " + image.getName(), this, this.commonStrings.RUN);
//                    lazyImageRotationAnimation.setRealAnimation();
//                } catch (Exception e) {
//                    this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
//                }
//            } else {

            if(this.loadNowList.contains(lazyImageRotationAnimation)) {
                
            } else {

                //this.logUtil.putF(new StringMaker().append("loadNowList insert: ").append(image).append(image.getName()).toString(), this, this.commonStrings.RUN);
            synchronized (this.lock) {
                this.loadNowList.add(lazyImageRotationAnimation);
                this.loadAfterList.remove(lazyImageRotationAnimation);
                
//                int indexOfImage = -1;
//                while((indexOfImage = this.loadImageAfterList.indexOf(lazyImageRotationAnimation.animationInterfaceFactoryInterface.getImage())) >= 0) {
//                    this.loadImageAfterList.remove(indexOfImage);
//                    final LazyImageRotationAnimation lazyImageRotationAnimation2 = (LazyImageRotationAnimation) this.loadAfterList.remove(indexOfImage);
//                    if(lazyImageRotationAnimation2 == lazyImageRotationAnimation) {
//                        this.logUtil.putF(new StringMaker().append("insert resource: ").append(lazyImageRotationAnimation2).toString(), this, this.commonStrings.RUN);
//                    } else {
//                        this.logUtil.putF(new StringMaker().append("insert duplicate resource: ").append(lazyImageRotationAnimation2).toString(), this, this.commonStrings.RUN);
//                    }
//                    this.loadNowList.add(lazyImageRotationAnimation2);
//                }

            }
                
            }

            this.runTask();

        //}
    }

    public void progressEnded() {
        this.progressEnded = true;
    }    

    public void runTask() throws Exception {
        //this.logUtil.putF(this.commonStrings.START + this.processor, this, "runTask");
        this.processor.process();
    }
    
    public void initProgress() {
        //this.logUtil.putF("reset totalFrames", this, this.commonStrings.RUN);
        
        if(this.firstTime) {
            this.firstTime = false;
        }
        
    }

    public boolean isLazy() {
        return true;
    }
    
}
