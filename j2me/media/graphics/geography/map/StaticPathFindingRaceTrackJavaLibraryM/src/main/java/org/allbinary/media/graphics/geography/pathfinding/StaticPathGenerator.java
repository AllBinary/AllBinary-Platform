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
import org.allbinary.logic.string.StringUtil;
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
           final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory,
      final BasicArrayList pathList)
      throws Exception
   {
      final BasicArrayList list = new BasicArrayList();
      
      final int size = pathList.size();
      
      CellPosition basicGeographicMapCellPosition;
      GeographicMapCellPosition geographicMapCellPosition;
      for (int index = 0; index < size; index++)
      {
         basicGeographicMapCellPosition = (CellPosition) pathList.get(index);

         geographicMapCellPosition =
            geographicMapCellPositionFactory.getInstance(
            basicGeographicMapCellPosition.getColumn(),
            basicGeographicMapCellPosition.getRow());

         list.add(geographicMapCellPosition);
      }
      return list;
   }

   public BasicArrayList getInstance(
           final BasicGeographicMap geographicMapInterface,
      final GeographicMapCellHistory geographicMapCellHistory,
      final PathFindingInfo pathFindingInfo,
      final int totalPaths)
      throws Exception
   {
      try
      {
         final PathCacheFactory pathCacheFactory = PathCacheFactory.getInstance();
         final Integer mapIdInteger = geographicMapInterface.getAllBinaryTiledLayer().getDataId();

         BasicArrayList list = pathCacheFactory.getInstance(mapIdInteger);

         if (list == null)
         {
            list = new BasicArrayList();

            final SmallIntegerSingletonFactory smallIntegerSingletonFactory = 
                SmallIntegerSingletonFactory.getInstance();
            
            final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory =
               geographicMapInterface.getGeographicMapCellPositionFactory();

            final int id = PathData.getInstance().OFFSET + mapIdInteger.intValue();
            
            final BasicArrayList basicList = pathCacheFactory.getInstance(
               smallIntegerSingletonFactory.getInstance(id));

            final int size = basicList.size();

            BasicArrayList pathList;
            for (int index = 0; index < size; index++)
            {
               pathList = this.getGeographicMapCellPositionListFromBasicGeographicMapCellPositionList(
                  geographicMapCellPositionFactory, (BasicArrayList) basicList.get(index));
               
               list.add(pathList);
            }

            pathCacheFactory.add(mapIdInteger, list);
            pathCacheFactory.remove(smallIntegerSingletonFactory.getInstance(id));
         }

         LogUtil.put(LogFactory.getInstance(new StringMaker().append("Using Cached Path(s): ").append(StringUtil.getInstance().toString(list)).toString(), this, CommonStrings.getInstance().GET_INSTANCE));

         return list;

      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_INSTANCE, e));
         return new BasicArrayList();
      }
   }
}
