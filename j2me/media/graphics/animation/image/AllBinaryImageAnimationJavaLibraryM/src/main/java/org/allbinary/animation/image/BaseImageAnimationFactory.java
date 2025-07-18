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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.image.AnimationFactoryImageScaleUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.ScaleProperties;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;

public class BaseImageAnimationFactory implements AnimationInterfaceFactoryInterface {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    protected final AnimationFactoryImageScaleUtil animationFactoryImageScaleUtil = AnimationFactoryImageScaleUtil.getInstance();
        
    private final Image image;
    
    public final AnimationBehaviorFactory animationBehaviorFactory;

    private final int[] sequenceArray;

    protected final AnimationFactoryInitializationVisitor animationFactoryInitializationVisitor;

    protected ScaleProperties scaleProperties = ScaleProperties.instance;

    public BaseImageAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        this(image, PrimitiveIntUtil.getArrayInstance(), width, height, animationBehaviorFactory);
        
        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        
    }
    
    public BaseImageAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) 
        throws Exception {
        this(image, sequenceArray, width, height, animationBehaviorFactory);
        
        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        
    }
    
    public BaseImageAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        this(image, PrimitiveIntUtil.getArrayInstance(), width, height, animationBehaviorFactory);
    }

    public BaseImageAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        
        this.animationFactoryInitializationVisitor = new AnimationFactoryInitializationVisitor();
        
        this.image = image;

        this.animationFactoryInitializationVisitor.width = width;
        this.animationFactoryInitializationVisitor.height = height;
        
        this.animationBehaviorFactory = animationBehaviorFactory;

        this.sequenceArray = sequenceArray;

    }

    public Animation getInstance(final int instanceId) throws Exception {
        return NullAnimationFactory.getFactoryInstance().getInstance(instanceId);
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return the sequenceArray
     */
    public int[] getSequenceArray() {
        return sequenceArray;
    }

    public String toString() {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        final CommonLabels commonLabels = CommonLabels.getInstance();
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(super.toString());
        stringBuffer.append(commonLabels.HEIGHT_LABEL);
        stringBuffer.append(image.getHeight());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(this.animationFactoryInitializationVisitor.toString());

        return stringBuffer.toString();
    }

    public void setInitialScale(final ScaleProperties scaleProperties) {
        
        this.scaleProperties = scaleProperties;
        
        //Temp hack
        if(this.scaleProperties.shouldScale) {
            this.scaleProperties.scaleWidth = (int) (this.animationFactoryInitializationVisitor.width * this.scaleProperties.scaleX);
            this.scaleProperties.scaleHeight = (int) (this.animationFactoryInitializationVisitor.height * this.scaleProperties.scaleY);
            //logUtil.put("hack: " + this.image.getName() + this.scaleProperties.toString(), this, commonStrings.PROCESS);
            //logUtil.put(this.scaleProperties.toString(), this, commonStrings.PROCESS);
        } else {
            //logUtil.put("else: " + this.image.getName() + this.scaleProperties.toString(), this, commonStrings.PROCESS);
        }
        
    }

    /**
     * @return the animationFactoryInitializationVisitor
     */
    public AnimationFactoryInitializationVisitor getAnimationFactoryInitializationVisitor() {
        return animationFactoryInitializationVisitor;
    }
    
    /**
     * @return the scaleProperties
     */
    public ScaleProperties getScaleProperties() {
        return scaleProperties;
    }
    
}
