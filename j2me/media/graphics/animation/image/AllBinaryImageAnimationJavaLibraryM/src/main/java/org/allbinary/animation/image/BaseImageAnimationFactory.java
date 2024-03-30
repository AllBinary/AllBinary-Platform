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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.graphics.SpacialStrings;
import org.allbinary.image.AnimationFactoryImageScaleUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class BaseImageAnimationFactory implements AnimationInterfaceFactoryInterface {

    protected final AnimationFactoryImageScaleUtil animationFactoryImageScaleUtil = AnimationFactoryImageScaleUtil.getInstance();
        
    private final Image image;
    public final int width;
    public final int height;
    protected final AnimationBehaviorFactory animationBehaviorFactory;

    private final int[] sequenceArray;

    public int scaleWidth;
    public int scaleHeight;
    public int dx;
    public int dy;

    public BaseImageAnimationFactory(final Image image, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        this(image, PrimitiveIntUtil.getArrayInstance(), width, height, animationBehaviorFactory);
        
        this.dx = dx;
        this.dy = dy;
    }
    
    public BaseImageAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory) 
        throws Exception {
        this(image, sequenceArray, width, height, animationBehaviorFactory);
        
        this.dx = dx;
        this.dy = dy;
    }
    
    public BaseImageAnimationFactory(final Image image, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        this(image, PrimitiveIntUtil.getArrayInstance(), width, height, animationBehaviorFactory);
    }

    public BaseImageAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final AnimationBehaviorFactory animationBehaviorFactory)
            throws Exception {
        this.image = image;

        this.width = width;
        this.height = height;
        
        this.animationBehaviorFactory = animationBehaviorFactory;

        this.sequenceArray = sequenceArray;

    }

    public Animation getInstance() throws Exception {
        return NullAnimationFactory.getFactoryInstance().getInstance();
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
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(super.toString());
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(image.getHeight());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(width);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(height);

        return stringBuffer.toString();
    }

    public void setInitialSize(final int width, final int height) {
        this.scaleWidth = width;
        this.scaleHeight = height;
    }

}
