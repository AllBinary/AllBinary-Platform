/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.graphics.displayable.screen;

import org.allbinary.graphics.GPoint;

/**
 *
 * @author User
 */
public class DisplayPointScalar {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final DisplayPointScalar instance = new DisplayPointScalar();

    /**
     * @return the instance
     */
    public static DisplayPointScalar getInstance() {
        return instance;
    }
    
    public DisplayPointScalar() {
    }

    public int processX(final int value) {
        return value;
    }

    public int processY(final int value) {
        return value;
    }
    
    public GPoint process(final GPoint point) {
        return point;
        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //logUtil.put("point: " + point, this, commonStrings.PROCESS);
        //800 by 480 is the default window.  I assume it is being scaled from that initial window.
        //final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
        //final float ratio = 480f / (float) (displayInfoSingleton.getLastHeight());
        //logUtil.put("ratio: " + ratio, this, commonStrings.PROCESS);
//        return PointFactory.getInstance().getInstance(
//                //(int) (point.getX() * ratio), 
//                point.getX(),
//                //(int) (point.getY() * ratio)
//                //(int) (point.getY() * 0.28f)
//                (int) (point.getY() * 0.56f)
//        );
    }

}
