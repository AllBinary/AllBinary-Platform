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

import allbinary.animation.Animation;

/**
 *
 * @author user
 */
public class BottomToTopImageAnimationFactory
    extends BaseImageAnimationFactory
{
   public BottomToTopImageAnimationFactory(
       Image image)//, int width, int height)
           throws Exception {
       super(image, image.getWidth(), image.getHeight());//, width, height);
   }

   public Animation getInstance() throws Exception
   {
       return new BottomToTopImageAnimation(this.getImage());
   }
}
