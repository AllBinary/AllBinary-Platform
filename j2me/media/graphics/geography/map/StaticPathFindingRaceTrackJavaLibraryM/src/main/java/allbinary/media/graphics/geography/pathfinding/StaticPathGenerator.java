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
package allbinary.media.graphics.geography.pathfinding;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.graphics.CellPosition;
import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import allbinary.media.graphics.geography.map.PathData;

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

         LogUtil.put(LogFactory.getInstance("Using Cached Path(s): " + list, this, CommonStrings.getInstance().GET_INSTANCE));

         return list;

      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET_INSTANCE, e));
         return new BasicArrayList();
      }
   }
}
