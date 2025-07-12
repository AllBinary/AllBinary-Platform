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
package org.allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.PreLogUtil;

/**
 *
 * @author user
 * Used for generating static paths.  Use in the build game method
 */
public class RaceTracksPathPrinter {
    //protected final LogUtil logUtil = LogUtil.getInstance();



    private void printPath(BasicArrayList list)
    {
        for (int index = 0; index < list.size(); index++)
        {
            Object object = list.get(index);
            PreLogUtil.put(new StringMaker().append("pathList.add(cellPositionFactory.getInstance").append(object.toString()).append(");").toString(), this, "printPath");
        }
    }

    protected void printPaths(BasicArrayList list)
    {
        for (int index = 0; index < list.size(); index++)
        {
            System.out.println(new StringMaker().append(" Path ").append(index).append(CommonLabels.getInstance().COLON_SEP).toString());
            this.printPath((BasicArrayList) list.get(index));
        }
    }
}
