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
package org.allbinary.media.image;

import javax.microedition.lcdui.Image;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.graphics.opengles.OpenGLUtil;
import org.allbinary.image.opengles.OpenGLESDeviceImageTranslate;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.image.opengles.OpenGLESImageExclusionUtil;
import org.allbinary.image.opengles.OpenGLESImageProperties;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import org.eclipse.swt.graphics.ImageData;
import org.microemu.app.ui.swt.SwtDeviceComponent;
import org.microemu.device.swt.PostLoadSwtImmutableImageProcessor;
import org.microemu.device.swt.PostLoadSwtMutableImageProcessor;
import org.microemu.device.swt.SwtImmutableImage;
import org.microemu.device.swt.SwtMutableImage;

public class ImageCopyUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final ImageCopyUtil instance = new ImageCopyUtil();
    
    public static ImageCopyUtil getInstance()
    {
        return instance;
    }
    
    //private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    //private final BasicColorSetUtil basicColorUtil = BasicColorSetUtil.getInstance();

    private ImageCopyUtil()
    {
    }
    
    //private int anchor = Anchor.TOP_LEFT;

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();
    private final Features features = Features.getInstance();
    private final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();
  
    private final OpenGLUtil openGLUtil = OpenGLUtil.getInstance();
 
    private final OpenGLESImageExclusionUtil openGLESImageExclusionUtil = OpenGLESImageExclusionUtil.getInstance();
    
    private final String NO_COPY = "SWT should not copy images after initial loading as the alpha is not honored";
    //private final String NO_COPY2 = "JOGL support for SWT Image copy is not implemented";

    public Image createImageForRotation(final Image originalImage)
            throws Exception
    {
        if(features.isFeature(openGLFeatureFactory.OPENGL)) {
            Image image = originalImage;
            image = openGLUtil.add(image);
            return image;
        } else {
            return this.createImage(originalImage);
        }
    }

    // String resource,
    public Image createImage(final Image originalImage)
            throws Exception
    {
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            logUtil.put(NO_COPY, this, commonStrings.CONSTRUCTOR);
            return originalImage;
        }

        Image originalImage2 = originalImage;
        if(features.isFeature(openGLFeatureFactory.OPENGL)) {
            //logUtil.put(NO_COPY2, this, commonStrings.CONSTRUCTOR);
            //final CommonSeps commonSeps = CommonSeps.getInstance();
            //logUtil.put(new StringMaker().append(NO_COPY2).append(" from: ").append(originalImage.getWidth()).append(commonSeps.SPACE).append(originalImage.getHeight()).append(" to: ").append(width).append(commonSeps.SPACE).append(height).toString(), this, commonStrings.CONSTRUCTOR);
            
            if (originalImage.getType() >= OpenGLESImage.TYPE) {
                final OpenGLESImage openGLESImage = ((OpenGLESImage) originalImage);
                originalImage2 = openGLESImage.openGLBitmap.getImage();
            }
//            return originalImage;
        }
        
        //final int width = originalImage.getWidth();
        //final int height = originalImage.getHeight();
        
        //Copying images with alpha channel works, but they are Immutable
        //final Image image = Image.createImage(originalImage);

        //This creates a Mutable image, but alpha channel does not seem to work
        //final Image image0 = imageCreationUtil.getInstance(originalImage.getWidth() , originalImage.getHeight());
        //final SwtMutableImage mutableImage0 = (SwtMutableImage) image0;
        //final ImageData imageData0 = mutableImage0.img.getImageData();

        //imageData0.alpha = 1;
        
        //This one is not working
        //int whitePixel = imageData0.getPixel(0, 0); //imageData0.palette.getPixel(new RGB(255,255,255));
        //imageData0.transparentPixel = whitePixel;

        //imageData0.setAlpha(0, 0, 0);
        //Arrays.fill(imageData0.alphaData, (byte) 0);
        
        //Image image = null;

        Image image = null;
        //final Image image = imageCreationUtil.getInstance(originalImage.getWidth() , originalImage.getHeight());

        //final org.eclipse.swt.graphics.Image swtImage = mutableImage.img;
        if (originalImage2.isMutable()) {
            final SwtMutableImage originalMutableImage = (SwtMutableImage) originalImage2;

//            for(int x = 0; x < width; x++) {
//                for(int y = 0; y < height; y++) {
//                    final int pixel = originalMutableImage.img.getImageData().getPixel(x, y);
//                    final byte alpha = (byte) (pixel >> 24);
//                    BasicColor basicColor = new BasicColor(alpha, pixel);
//                    //logUtil.put(basicColor.toString(), this, commonStrings.PROCESS);
//                    if (basicColor.intValue() == -1)  {
//                        originalMutableImage.img.getImageData().setPixel(x, y, 0x00FFFFFF);
//                        //logUtil.put(new StringMaker().append("x: ").append(x).append(" y: ").append(y).toString(), this, commonStrings.PROCESS);
//                    } else {
//                        //imageData0.alphaData[(y * width) + x] = alpha;
//                        //imageData0.setAlpha(x, y, alpha);
//                    }
//
//                }
//            }
//            originalMutableImage.img.getImageData().transparentPixel = 0x00FFFFFF;

//            final SwtMutableImage originalWithCorrectedAlphaImage = new SwtMutableImage(SwtDeviceComponent.createImage(originalMutableImage.img.getImageData()));
            
//            imageData0.transparentPixel = 0x00FFFFFF;
//            image = new SwtMutableImage(SwtDeviceComponent.createImage(imageData0));
            
            image = new SwtMutableImage(SwtDeviceComponent.createImage(originalMutableImage.image.getImageData()));
              
//            for(int x = 0; x < width; x++) {
//                for(int y = 0; y < height; y++) {
//                    final int pixel = originalMutableImage.img.getImageData().getPixel(x, y);
//                    final byte alpha = (byte) (pixel >> 24);
//                    BasicColor basicColor = new BasicColor(alpha, pixel);
//                    //logUtil.put(basicColor.toString(), this, commonStrings.PROCESS);
//                    if (basicColor.intValue() == -1)  {
//                        //logUtil.put(new StringMaker().append("x: ").append(x).append(" y: ").append(y).toString(), this, commonStrings.PROCESS);
//                    } else {
//                        imageData0.alphaData[(y * width) + x] = alpha;
//                        //imageData0.setAlpha(x, y, alpha);
//                    }
//
//                }
//            }
            
//            image = new SwtMutableImage(SwtDeviceComponent.createImage(imageData0));
            
//            final SwtMutableImage mutableImage = (SwtMutableImage) image;            
//            final GC gc = mutableImage.getGc();
//
//            gc.setAntialias(SWT.ON);
//            gc.drawImage(originalWithCorrectedAlphaImage.img, 0, 0);

                        //gc.setForeground(new Color(null, basicColor.red, basicColor.green, basicColor.blue, basicColor.alpha));
                        //gc.drawPoint(x, y);

//            final int[] pixelArray = new int[width * height];
//            originalMutableImage.img.getImageData().getPixels(0, 0, width, pixelArray, 0);
//            mutableImage0.img.getImageData().setPixels(0, 0, width, pixelArray, 0);
                        
//            final int[] pixelArray = new int[originalMutableImage.img.getImageData().width * originalMutableImage.img.getImageData().height];
//            originalMutableImage.img.getImageData().getPixels(0, 0, originalMutableImage.img.getBounds().width, pixelArray, 0);
//            //mutableImage.img.getImageData().depth = originalMutableImage.img.getImageData().depth;
//            //logUtil.put("depth: " + mutableImage.img.getImageData().depth, this, commonStrings.PROCESS);
//            mutableImage.img.getImageData().setPixels(0, 0, originalMutableImage.img.getBounds().width, pixelArray, 0);
//            //mutableImage.img.getImageData().(0, 0, mutableImage.img.getBounds().width, mutableImage.img.getImageData().maskData, 0);
            
        } else {
            final SwtImmutableImage originalImmutableImage = (SwtImmutableImage) originalImage2;

//            for(int x = 0; x < width; x++) {
//                for(int y = 0; y < height; y++) {
//                    final int pixel = originalImmutableImage.img.getImageData().getPixel(x, y);
//                    final byte alpha = (byte) (pixel >> 24);
//                    BasicColor basicColor = new BasicColor(alpha, pixel);
//                    //logUtil.put(basicColor.toString(), this, commonStrings.PROCESS);
//                    if (basicColor.intValue() == -1)  {
//                        originalImmutableImage.img.getImageData().setPixel(x, y, 0x00FFFFFF);
//                        //logUtil.put(new StringMaker().append("x: ").append(x).append(" y: ").append(y).toString(), this, commonStrings.PROCESS);
//                    } else {
//                        //imageData0.alphaData[(y * width) + x] = alpha;
//                        //imageData0.setAlpha(x, y, alpha);
//                    }
//
//                }
//            }
//            originalImmutableImage.img.getImageData().transparentPixel = 0x00FFFFFF;
            
//            final SwtMutableImage originalWithCorrectedAlphaImage = new SwtMutableImage(SwtDeviceComponent.createImage(originalImmutableImage.img.getImageData()));
            
//            imageData0.transparentPixel = 0x00FFFFFF;
//            image = new SwtMutableImage(SwtDeviceComponent.createImage(imageData0));

            image = new SwtMutableImage(SwtDeviceComponent.createImage(originalImmutableImage.image.getImageData()));

//            for(int x = 0; x < width; x++) {
//                for(int y = 0; y < height; y++) {
//                    final int pixel = originalImmutableImage.img.getImageData().getPixel(x, y);
//                    final byte alpha = (byte) (pixel >> 24);
//                    BasicColor basicColor = new BasicColor(alpha, pixel);
//                    //logUtil.put(basicColor.toString(), this, commonStrings.PROCESS);
//                    if (basicColor.intValue() == -1)  {
//                        //logUtil.put(new StringMaker().append("x: ").append(x).append(" y: ").append(y).toString(), this, commonStrings.PROCESS);
//                    } else {
//                        imageData0.alphaData[(y * width) + x] = alpha;
//                        //imageData0.setAlpha(x, y, alpha);
//                        //gc.setForeground(new Color(null, basicColor.red, basicColor.green, basicColor.blue, basicColor.alpha));
//                        //gc.drawPoint(x, y);
//                    }
//                }
//            }
            
            //image = new SwtMutableImage(SwtDeviceComponent.createImage(imageData0));
            
//            final SwtMutableImage mutableImage = (SwtMutableImage) image;
//            final GC gc = mutableImage.getGc();
//            
//            gc.setAntialias(SWT.ON);
//            gc.drawImage(originalWithCorrectedAlphaImage.img, 0, 0);

                        //gc.setForeground(new Color(null, basicColor.red, basicColor.green, basicColor.blue, basicColor.alpha));
                        //gc.drawPoint(x, y);

//            final int[] pixelArray = new int[width * height];
//            originalImmutableImage.img.getImageData().getPixels(0, 0, width, pixelArray, 0);
//            mutableImage0.img.getImageData().setPixels(0, 0, width, pixelArray, 0);
                        
//            final int[] pixelArray = new int[originalImmutableImage.img.getImageData().width * originalImmutableImage.img.getImageData().height];
//            originalImmutableImage.img.getImageData().getPixels(0, 0, originalImmutableImage.img.getBounds().width, pixelArray, 0);
//            //mutableImage.img.getImageData().depth = originalMutableImage.img.getImageData().depth;
//            //logUtil.put("depth: " + mutableImage.img.getImageData().depth, this, commonStrings.PROCESS);
//            mutableImage.img.getImageData().setPixels(0, 0, originalImmutableImage.img.getBounds().width, pixelArray, 0);
            
        }        

//
//        if (image.isMutable())
//        {
//            image.getGraphics().drawImage(originalImage, 0, 0, anchor);
//            
            image = openGLUtil.add(image);
            return image;
//        }
//        else
//        {
//            throw new Exception("Not Mutable");
//        }
        
    }

    public Image createImage(final Image originalImage, final int width, final int height)
            throws Exception
    {
        return this.createImage(originalImage, width, height, true);
    }
    
    public Image createImage(final Image originalImage, final int width, final int height, final boolean mutable)
            throws Exception
    {
        Image originalImage2 = originalImage;
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            logUtil.put(NO_COPY, this, commonStrings.CONSTRUCTOR);
            return originalImage;
        }
        
        //if(originalImage instanceof OpenGLESImage) {
        if(features.isFeature(openGLFeatureFactory.OPENGL)) {
            if(openGLESImageExclusionUtil.isCustomScaling(originalImage)) {
                
                return this.createImage2(originalImage, width, height, mutable);
            }

            //logUtil.put("name: " + originalImage.getName(), this, commonStrings.CONSTRUCTOR);
            //logUtil.put(NO_COPY2, this, commonStrings.CONSTRUCTOR);
            //final CommonSeps commonSeps = CommonSeps.getInstance();
            //logUtil.put(new StringMaker().append(NO_COPY2).append(" from: ").append(originalImage.getWidth()).append(commonSeps.SPACE).append(originalImage.getHeight()).append(" to: ").append(width).append(commonSeps.SPACE).append(height).toString(), this, commonStrings.CONSTRUCTOR);
            
            if (originalImage.getType() >= OpenGLESImage.TYPE) {
                final OpenGLESImage openGLESImage = ((OpenGLESImage) originalImage);
                originalImage2 = openGLESImage.openGLBitmap.getImage();
            }
//            return originalImage;
        }

        Image image = null;
        if (originalImage2.isMutable() && mutable) {
            final SwtMutableImage originalMutableImage = (SwtMutableImage) originalImage2;
            
            if(originalMutableImage.getImage() != null) {
                final ImageData imageData = originalMutableImage.image.getImageData().scaledTo(width, height);
                image = new SwtMutableImage(SwtDeviceComponent.createImage(imageData));
            } else {
                image = new SwtMutableImage(originalMutableImage.getName(), new PostLoadSwtMutableImageProcessor(originalMutableImage));
            }
            
        } else {
            final SwtImmutableImage originalImmutableImage = (SwtImmutableImage) originalImage2;

            if(originalImmutableImage.getImage() != null) {
                final ImageData imageData = originalImmutableImage.image.getImageData().scaledTo(width, height);
                image = new SwtImmutableImage(originalImage.getName(), SwtDeviceComponent.createImage(imageData));
            } else {
                image = new SwtImmutableImage(originalImmutableImage.getName(), new PostLoadSwtImmutableImageProcessor(originalImmutableImage));
            }
        }
        
        image = openGLUtil.add(image);

        return image;
        
    }

    private final String INFORMATION = "about_";
    private final String LEADERBOARD = "leaderboard";
    //private final String GREEN_BUTTON = "green_button";
    public Image createImage2(final Image originalImage, final int width, final int height, final boolean mutable)
            throws Exception
    {
        Image originalImage2 = originalImage;
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            logUtil.put(NO_COPY, this, commonStrings.CONSTRUCTOR);
            return originalImage;
        }

        Image image = null;

        //if(originalImage instanceof OpenGLESImage) {
        if(features.isFeature(openGLFeatureFactory.OPENGL)) {
            //logUtil.put(NO_COPY2, this, commonStrings.CONSTRUCTOR);
            //final CommonSeps commonSeps = CommonSeps.getInstance();
            //logUtil.put(new StringMaker().append(NO_COPY2).append(" from: ").append(originalImage.getWidth()).append(commonSeps.SPACE).append(originalImage.getHeight()).append(" to: ").append(width).append(commonSeps.SPACE).append(height).toString(), this, commonStrings.CONSTRUCTOR);
            
            final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
            
            if (originalImage.getType() >= OpenGLESImage.TYPE) {
                //logUtil.put("TWB0: " + originalImage.getWidth() + " h: " + originalImage.getHeight(), this, commonStrings.CONSTRUCTOR);
                final int width2 = originalImage.getWidth();
                final int height2 = originalImage.getHeight();
                final OpenGLESImage openGLESImage = ((OpenGLESImage) originalImage);
                final OpenGLESImageProperties openGLESImageProperties = openGLESImage.openGLESImageProperties;
                //logUtil.put(new StringMaker().append("0sx: ").append(openGLESImageProperties.scaleX2).append(" sy: ").append(openGLESImageProperties.scaleY2).toString(), this, commonStrings.CONSTRUCTOR);
                openGLESImageProperties.scaleX = openGLESImageProperties.scaleX2 = (float) (((float) width) / openGLESImage.getWidth());
                openGLESImageProperties.scaleY = openGLESImageProperties.scaleY2 = (float) (((float) height) / openGLESImage.getHeight());
                openGLESImageProperties.scaleX = openGLESImageProperties.scaleX * 0.75f;
                openGLESImageProperties.scaleY = openGLESImageProperties.scaleY * 0.75f;
                
                if(originalImage.getHeight() % 2 != 0) {
                    openGLESImageProperties.scaleX2+= 1 / ((float) width2);
                    openGLESImageProperties.scaleY2+= 1 / ((float) height2);
                }
                
                //openGLESImageProperties.translateY = -displayInfoSingleton.getLastHeight() / 40 / openGLESImage.scaleY;
                image = openGLESImage;
                //logUtil.put(new StringMaker().append("sx: ").append(openGLESImageProperties.scaleX2).append(" sy: ").append(openGLESImageProperties.scaleY2).toString(), this, commonStrings.CONSTRUCTOR);
                //logUtil.put("TWB: " + originalImage.getWidth() + " h: " + originalImage.getHeight(), this, commonStrings.CONSTRUCTOR);
                //originalImage2 = openGLESImage.openGLBitmap.getImage();
                //logUtil.put(new StringMaker().append("OpenGLESImage sx: ").append(openGLESImageProperties.scaleX).append(" sy: ").append(openGLESImage.scaleY).append("tx: ").append(openGLESImageProperties.translateX).append(" ty: ").append(openGLESImageProperties.translateY).toString(), this, commonStrings.CONSTRUCTOR);
            } else {
                //logUtil.put("type: " + originalImage.getType(), this, commonStrings.CONSTRUCTOR);
                image = originalImage;
                final OpenGLESImage openGLESImage = (OpenGLESImage) openGLUtil.add(image);
                final OpenGLESImageProperties openGLESImageProperties = openGLESImage.openGLESImageProperties;
                openGLESImageProperties.scaleX = openGLESImageProperties.scaleX2 = (float) (((float) width) / openGLESImage.getWidth());
                openGLESImageProperties.scaleY = openGLESImageProperties.scaleY2 = (float) (((float) height) / openGLESImage.getHeight());
                
                if(image.getName().startsWith(INFORMATION)) {
                    openGLESImage.openGLESImageTranslate = new OpenGLESDeviceImageTranslate();
                    final OpenGLESDeviceImageTranslate openGLESDeviceImageTranslate = (OpenGLESDeviceImageTranslate) openGLESImage.openGLESImageTranslate;
                    openGLESDeviceImageTranslate.translateX = displayInfoSingleton.getLastWidth() / 1.4f / openGLESImageProperties.scaleX;
                    openGLESDeviceImageTranslate.translateY = displayInfoSingleton.getLastHeight() / 4 / openGLESImageProperties.scaleY;
                    openGLESImageProperties.scaleX = openGLESImageProperties.scaleX * 0.25f;
                    openGLESImageProperties.scaleY = openGLESImageProperties.scaleY * 0.25f;
                } else {
                    
//                    if (image.getName().startsWith(this.GREEN_BUTTON)) {
//                        logUtil.put(new StringMaker().append("sx: ").append(openGLESImageProperties.scaleX).append(" sy: ").append(openGLESImage.scaleY).append("tx: ").append(openGLESImageProperties.translateX).append(" ty: ").append(openGLESImageProperties.translateY).toString(), this, commonStrings.CONSTRUCTOR);
//                        if(openGLESImageProperties.scaleX == 2.0f) {
//                            openGLESImageProperties.scaleX = openGLESImageProperties.scaleX * 0.50f;
//                            openGLESImage.scaleY = openGLESImage.scaleY * 0.40f;
//                            //openGLESImageProperties.translateX = displayInfoSingleton.getLastWidth() / 5.5f / openGLESImageProperties.scaleX;
//                            openGLESImageProperties.translateY = displayInfoSingleton.getLastHeight() / 17 / openGLESImage.scaleY;
//                        }
//                        if(openGLESImageProperties.scaleX == 2.065f) {
////                            openGLESImageProperties.scaleX = openGLESImageProperties.scaleX * 0.50f;
////                            openGLESImage.scaleY = openGLESImage.scaleY * 0.40f;
//                            //openGLESImageProperties.translateX = displayInfoSingleton.getLastWidth() / 5.5f / openGLESImageProperties.scaleX;
////                            openGLESImageProperties.translateY = displayInfoSingleton.getLastHeight() / 17 / openGLESImage.scaleY;
//                        } 
//                        if(openGLESImageProperties.scaleX == 1.28f) {
////                            openGLESImageProperties.scaleX = openGLESImageProperties.scaleX * 0.50f;
//                            openGLESImage.scaleY = openGLESImage.scaleY * 0.60f;
//                            openGLESImageProperties.translateX = -displayInfoSingleton.getLastWidth() / 3f / openGLESImageProperties.scaleX;
//                            openGLESImageProperties.translateY = displayInfoSingleton.getLastHeight() / 11 / openGLESImage.scaleY;
//                        }
//                        logUtil.put(new StringMaker().append("2 sx: ").append(openGLESImageProperties.scaleX).append(" sy: ").append(openGLESImage.scaleY).append("tx: ").append(openGLESImageProperties.translateX).append(" ty: ").append(openGLESImageProperties.translateY).toString(), this, commonStrings.CONSTRUCTOR);
//                    } else {
                        openGLESImageProperties.scaleX = openGLESImageProperties.scaleX * 0.75f;
                        openGLESImageProperties.scaleY = openGLESImageProperties.scaleY * 0.75f;
//                    }
                                        
                    if(image.getName().startsWith(LEADERBOARD)) {
                        openGLESImage.openGLESImageTranslate = new OpenGLESDeviceImageTranslate();
                        final OpenGLESDeviceImageTranslate openGLESDeviceImageTranslate = (OpenGLESDeviceImageTranslate) openGLESImage.openGLESImageTranslate;
                        openGLESDeviceImageTranslate.translateX = -displayInfoSingleton.getLastWidth() / 40 / openGLESImageProperties.scaleX;
                    }
                    
                    if (
                        //image.getName().startsWith(this.GREEN_BUTTON) || 
                        image.getName().startsWith(LEADERBOARD)) {
                    } else {
                        openGLESImageProperties.scaleX = 1.0f;
                        openGLESImageProperties.scaleY = 1.0f;
                    }
                    
                }

                //logUtil.put(new StringMaker().append("sx: ").append(openGLESImageProperties.scaleX).append(" sy: ").append(openGLESImage.scaleY).append(" tx: ").append(openGLESImageProperties.translateX).append(" ty: ").append(openGLESImageProperties.translateY).toString(), this, commonStrings.CONSTRUCTOR);
                image = openGLESImage;
                //logUtil.put("TWB: " + originalImage.getWidth() + " h: " + originalImage.getHeight(), this, commonStrings.CONSTRUCTOR);
                //throw new RuntimeException();
            }

            //return originalImage;

        } else {

            if (originalImage2.isMutable() && mutable) {
                final SwtMutableImage originalMutableImage = (SwtMutableImage) originalImage2;

                if (originalMutableImage.getImage() != null) {
                    final ImageData imageData = originalMutableImage.image.getImageData().scaledTo(width, height);
                    image = new SwtMutableImage(SwtDeviceComponent.createImage(imageData));
                } else {
                    image = new SwtMutableImage(originalMutableImage.getName(), new PostLoadSwtMutableImageProcessor(originalMutableImage));
                }

            } else {
                final SwtImmutableImage originalImmutableImage = (SwtImmutableImage) originalImage2;

                if (originalImmutableImage.getImage() != null) {
                    final ImageData imageData = originalImmutableImage.image.getImageData().scaledTo(width, height);
                    image = new SwtImmutableImage(originalImage.getName(), SwtDeviceComponent.createImage(imageData));
                } else {
                    image = new SwtImmutableImage(originalImmutableImage.getName(), new PostLoadSwtImmutableImageProcessor(originalImmutableImage));
                }
            }

            image = openGLUtil.add(image);

        }
        
        return image;
        
    }

    public Image createImage(final Image originalImage, final float canvasScale, final boolean resize)
            throws Exception
    {
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            logUtil.put(NO_COPY, this, commonStrings.CONSTRUCTOR);
            return originalImage;
        }

//        final SpacialStrings spacialStrings = SpacialStrings.getInstance();
//        logUtil.put(new StringBuilder().append(spacialStrings.WIDTH_LABEL).append(originalImage.getWidth()).append(spacialStrings.HEIGHT_LABEL).append(originalImage.getHeight()).toString(), this, commonStrings.CONSTRUCTOR);
        
        int newWidth = (int) (originalImage.getWidth() * canvasScale);
        int newHeight = (int) (originalImage.getHeight() * canvasScale);
        
        if(resize) {
            if (newWidth < newHeight) {
                newWidth = newHeight;
            }

            if (newHeight < newWidth) {
                newHeight = newWidth;
            }
        }
        
        //logUtil.put("newWidth: " + newWidth + " newHeight: " + newHeight, this, commonStrings.CONSTRUCTOR);
        
        //final Image image = imageCreationUtil.getInstance(newWidth, newHeight);
        final Image image = this.createImage(originalImage, newWidth, newHeight);

        if (image.isMutable())
        {
            ImageData originalImageData = ((org.eclipse.swt.graphics.Image) originalImage.getImage()).getImageData();
            final SwtMutableImage mutableImage2 = (SwtMutableImage) image;

            final int halfWidthDelta = (newWidth - originalImage.getWidth()) / 2;
            final int halfHeightDelta = (newHeight - originalImage.getHeight()) / 2;
            
            final int[] originalPixelArray = new int[originalImage.getWidth() * originalImage.getHeight()];
            final int[] newPixelArray = new int[image.getWidth() * image.getHeight()];
            
            final int width = originalImage.getWidth();
            final int height = originalImage.getHeight();
            for (int i = 0; i < height; i++) {
                originalImageData.getPixels(0, i, width, originalPixelArray, (i * width));
            }
                        
            for(int index = halfWidthDelta; index < width; index++) {
                for(int index2 = halfHeightDelta; index2 < height; index2++) {
                    newPixelArray[index + (index2 * width)] = originalPixelArray[(index - halfWidthDelta) + ((index2 - halfHeightDelta) * width)];
                }
            }
            ((org.eclipse.swt.graphics.Image) mutableImage2.getImage()).getImageData().setPixels(0, 0, image.getWidth(), newPixelArray, 0);

//            logUtil.put(new StringMaker().append("deltas").append(spacialStrings.WIDTH_LABEL).append(halfWidthDelta).append(spacialStrings.HEIGHT_LABEL).append(halfHeightDelta).toString(), this, commonStrings.CONSTRUCTOR);
//            final Graphics graphics = image.getGraphics();
//            graphics.drawImage(originalImage, halfWidthDelta, halfHeightDelta, anchor);
            //this.basicSetColorUtil.setBasicColorP(graphics, BasicColorFactory.getInstance().YELLOW);
            //graphics.drawRect(halfWidthDelta, halfHeightDelta, originalImage.getWidth(), originalImage.getHeight());
            //this.basicSetColorUtil.setBasicColorP(graphics, BasicColorFactory.getInstance().WHITE);
            //graphics.drawRect(0, 0, newWidth, newHeight);
            
            return image;
        }
        else
        {
            throw new Exception("Not Mutable");
        }
    }
}
