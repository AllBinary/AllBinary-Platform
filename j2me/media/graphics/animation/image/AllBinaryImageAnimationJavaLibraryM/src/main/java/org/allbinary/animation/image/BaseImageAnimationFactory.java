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
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.graphics.SpacialStrings;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class BaseImageAnimationFactory implements AnimationInterfaceFactoryInterface {

    private final Image image;
    protected final int width;
    protected final int height;
    protected final AnimationBehavior animationBehavior;

    private final int[] sequenceArray;

    public BaseImageAnimationFactory(final Image image, final int width, final int height, final AnimationBehavior animationBehavior)
            throws Exception {
        this(image, PrimitiveIntUtil.getArrayInstance(), width, height, animationBehavior);
    }

    public BaseImageAnimationFactory(final Image image, final int[] sequenceArray, final int width, final int height, final AnimationBehavior animationBehavior)
            throws Exception {
        this.image = image;

        this.width = width;
        this.height = height;
        
        this.animationBehavior = animationBehavior;

        this.sequenceArray = sequenceArray;

        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(image.getHeight());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(width);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(height);

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
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

    public void setInitialSize(final int width, final int height) {

    }

}
