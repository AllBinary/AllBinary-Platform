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
package allbinary.media.graphics.geography.map.racetrack;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.PreLogUtil;

/**
 *
 * @author user
 * Used for generating static paths.  Use in the build game method
 */
public class RaceTracksPathPrinter {


    private void printPath(BasicArrayList list)
    {
        for (int index = 0; index < list.size(); index++)
        {
            Object object = list.get(index);
            PreLogUtil.put("pathList.add(cellPositionFactory.getInstance" + object.toString() + ");", this, "printPath");
        }
    }

    protected void printPaths(BasicArrayList list)
    {
        for (int index = 0; index < list.size(); index++)
        {
            System.out.println(" Path " + index + ": ");
            this.printPath((BasicArrayList) list.get(index));
        }
    }
}
