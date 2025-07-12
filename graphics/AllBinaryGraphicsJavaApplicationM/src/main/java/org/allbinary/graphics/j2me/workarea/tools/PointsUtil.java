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
package org.allbinary.graphics.j2me.workarea.tools;

import org.allbinary.animation.VectorRotationGenerator;
import org.allbinary.graphics.GPoint;
import org.allbinary.math.AngleFactory;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class PointsUtil {

    private static final PointsUtil instance = new PointsUtil();

    /**
     * @return the instance
     */
    public static PointsUtil getInstance()
    {
        return instance;
    }

   public BasicArrayList doTransforms(BasicArrayList pointVector, Double angle, GPoint fulcrumPoint) throws Exception
   {
      return VectorRotationGenerator.getInstance().getInstance(fulcrumPoint.getX(), fulcrumPoint.getY(), pointVector, AngleFactory.getInstance().getInstance(angle.intValue()));

      //rotate points for canvas rotation
      //i would have used the transform in graphics2d but I need to export the
      //rotated point values
      //!!!!!!!!!!!!!!!!!!!!!!!!remember the parenthesis whitout them tranlation fails to work correctly
      //for some unknown reason each degree becomes 56 degrees it canvas angle is passed directly to the rotate function
      //a Java bug
      //rotate this graphic item around a translation point
      //!!!!!!!!!!!!!!!!!!!!!!!!remember the parenthesis whitout them tranlation fails to work correctly
      //graphicsPipe.translate((int) -(this.fulcrumPoint.getX()/2),(int) -(this.fulcrumPoint.getY()/2));
      //graphicsPipe.rotate(this.theta);
      //graphicsPipe.translate((int) (this.fulcrumPoint.getX()/2),(int) (this.fulcrumPoint.getY()/2));
      //return graphicsPipe.getMatrix();
   }

}
