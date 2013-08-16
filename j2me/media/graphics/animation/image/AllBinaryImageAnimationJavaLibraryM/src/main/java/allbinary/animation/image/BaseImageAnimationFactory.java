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
package allbinary.animation.image;

import javax.microedition.lcdui.Image;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.animation.Animation;
import allbinary.animation.AnimationInterfaceFactoryInterface;
import allbinary.animation.NullAnimationFactory;
import allbinary.graphics.SpacialStrings;
import allbinary.logic.math.PrimitiveIntUtil;

public class BaseImageAnimationFactory implements AnimationInterfaceFactoryInterface {

   private final Image image;
   protected int width;
   protected int height;

   private final int[] sequenceArray;

   public BaseImageAnimationFactory(Image image, int width, int height)
           throws Exception {
      this.image = image;

      this.width = width;
      this.height = height;

      this.sequenceArray = PrimitiveIntUtil.getArrayInstance();

      StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append("Image: ");
      stringBuffer.append(image.getHeight());
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
      stringBuffer.append(width);
      stringBuffer.append(CommonSeps.getInstance().SPACE);
      stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
      stringBuffer.append(height);
      
      LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
   }

   public BaseImageAnimationFactory(Image image, int[] sequenceArray, int width, int height)
           throws Exception {
      this.image = image;

      this.width = width;
      this.height = height;
      
      this.sequenceArray = sequenceArray;
   }

   public Animation getInstance() throws Exception
   {
       return NullAnimationFactory.getFactoryInstance().getInstance();
   }

    /**
     * @return the image
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * @return the sequenceArray
     */
    public int[] getSequenceArray()
    {
        return sequenceArray;
    }
}
