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
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.ScaleProperties;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;

public class BaseImageAnimationFactory implements AnimationInterfaceFactoryInterface {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public static BaseImageAnimationFactory createFactoryBase(final Image image, final int[] sequenceArray, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        return new BaseImageAnimationFactory(image, sequenceArray, width, height, 0, 0, animationBehaviorFactory);
    }

    protected final AnimationFactoryImageScaleUtil animationFactoryImageScaleUtil = AnimationFactoryImageScaleUtil.getInstance();
        
    private final Image image;
    
    public final AnimationBehaviorFactory animationBehaviorFactory;

    private final int[] sequenceArray;

    protected final AnimationFactoryInitializationVisitor animationFactoryInitializationVisitor;

    protected ScaleProperties scaleProperties = ScaleProperties.instance;

    public BaseImageAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        this.animationFactoryInitializationVisitor = new AnimationFactoryInitializationVisitor();

        this.image = image;

        this.animationFactoryInitializationVisitor.width = width;
        this.animationFactoryInitializationVisitor.height = height;

        this.animationBehaviorFactory = animationBehaviorFactory;

        this.sequenceArray = sequenceArray;
        
        this.animationFactoryInitializationVisitor.dx = dx;
        this.animationFactoryInitializationVisitor.dy = dy;
        
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception {
        return NullAnimationFactory.getFactoryInstance().getInstance(instanceId);
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * @return the sequenceArray
     */
    public int[] getSequenceArray() {
        return this.sequenceArray;
    }

    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {
        
        this.scaleProperties = scaleProperties;
        
        //Temp hack
        if(this.scaleProperties.shouldScale) {
            this.scaleProperties.scaleWidth = (int) (this.animationFactoryInitializationVisitor.width * this.scaleProperties.scaleX);
            this.scaleProperties.scaleHeight = (int) (this.animationFactoryInitializationVisitor.height * this.scaleProperties.scaleY);
            //this.logUtil.putF("hack: " + this.image.getName() + this.scaleProperties.toString(), this, this.commonStrings.PROCESS);
            //this.logUtil.putF(this.scaleProperties.toString(), this, this.commonStrings.PROCESS);
        } else {
            //this.logUtil.putF("else: " + this.image.getName() + this.scaleProperties.toString(), this, this.commonStrings.PROCESS);
        }
        
    }

    /**
     * @return the animationFactoryInitializationVisitor
     */
    public AnimationFactoryInitializationVisitor getAnimationFactoryInitializationVisitorP() {
        return this.animationFactoryInitializationVisitor;
    }
    
    /**
     * @return the scaleProperties
     */
    public ScaleProperties getScalePropertiesP() {
        return this.scaleProperties;
    }
    
    public String toString() {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        final CommonLabels commonLabels = CommonLabels.getInstance();
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(super.toString());
        stringBuffer.append(commonLabels.HEIGHT_LABEL);
        stringBuffer.appendint(this.image.getHeight());
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(this.animationFactoryInitializationVisitor.toString());

        return stringBuffer.toString();
    }

}
