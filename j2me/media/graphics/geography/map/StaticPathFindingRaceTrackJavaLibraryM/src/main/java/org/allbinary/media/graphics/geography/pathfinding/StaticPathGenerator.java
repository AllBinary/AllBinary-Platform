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
package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.graphics.CellPosition;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.PathData;

/**
 *
 * @author user
 */
public class StaticPathGenerator
{
   public void init(
           BasicGeographicMap geographicMapInterface, int totalPaths)
      throws Exception
   {
   }

   protected StaticPathGenerator()
   {
       PreLogUtil.put("Using Static Path Finding", this, CommonStrings.getInstance().CONSTRUCTOR);
   }

   //Takes static paths with CellPositions and converts them
   //into static paths with GeographicMapCellPositions
   private BasicArrayList getGeographicMapCellPositionListFromBasicGeographicMapCellPositionList(
           BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory,
      BasicArrayList pathList)
      throws Exception
   {
      BasicArrayList list = new BasicArrayList();
      
      int size = pathList.size();
      
      for (int index = 0; index < size; index++)
      {
         CellPosition basicGeographicMapCellPosition =
            (CellPosition) pathList.get(index);

         GeographicMapCellPosition geographicMapCellPosition =
            geographicMapCellPositionFactory.getInstance(
            basicGeographicMapCellPosition.getColumn(),
            basicGeographicMapCellPosition.getRow());

         list.add(geographicMapCellPosition);
      }
      return list;
   }

   public BasicArrayList getInstance(
           BasicGeographicMap geographicMapInterface,
      GeographicMapCellHistory geographicMapCellHistory,
      PathFindingInfo pathFindingInfo,
      int totalPaths)
      throws Exception
   {
      try
      {
         PathCacheFactory pathCacheFactory = PathCacheFactory.getInstance();
         Integer mapIdInteger = geographicMapInterface.getAllBinaryTiledLayer().getDataId();

         BasicArrayList list = pathCacheFactory.getInstance(mapIdInteger);

         if (list == null)
         {
            list = new BasicArrayList();

            final SmallIntegerSingletonFactory smallIntegerSingletonFactory = 
                SmallIntegerSingletonFactory.getInstance();
            
            BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
               geographicMapInterface.getGeographicMapCellPositionFactory();

            int id = PathData.getInstance().OFFSET + mapIdInteger.intValue();
            
            BasicArrayList basicList = pathCacheFactory.getInstance(
               smallIntegerSingletonFactory.getInstance(id));

            int size = basicList.size();

            for (int index = 0; index < size; index++)
            {
               BasicArrayList pathList = this.getGeographicMapCellPositionListFromBasicGeographicMapCellPositionList(
                  geographicMapCellPositionFactory, (BasicArrayList) basicList.get(index));
               
               list.add(pathList);
            }

            pathCacheFactory.add(mapIdInteger, list);
            pathCacheFactory.remove(smallIntegerSingletonFactory.getInstance(id));
         }

         LogUtil.put(LogFactory.getInstance(new StringMaker().append("Using Cached Path(s): ").append(list).toString(), this, CommonStrings.getInstance().GET_INSTANCE));

         return list;

      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_INSTANCE, e));
         return new BasicArrayList();
      }
   }
}
