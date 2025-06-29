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

import org.allbinary.string.CommonStrings;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;

public class RaceTrackGeographicMapCellType extends GeographicMapCellType
{
   /*
   public long northTravelCostBetweenArray[][] = {
       //MAX 
       { max, max, max, max, max, max, max, max },
       { 0, 0, max, max, max, max, max, max },
       { 0, max, 0, max, max, max, max, max },
       { 0, 0, 0, max, max, max, 0, 0 },
       { 0, 0, 0, 0, max, max, 0, 0 },
       { 0, max, max, max, 0, max, max, max },
       { 0, 0, 0, max, max, max, 0, 0 },
       { 0, 0, 0, max, max, max, max, 0 }
    };

    public long southTravelCostBetweenArray[][] = {
       { max, max, max, max, max, max, max, max },
       { 0, 0, max, 0, 0, max, 0, 0 },
       { 0, max, 0, 0, 0, max, 0, 0 },
       { 0, max, max, 0, max, max, max, max },
       { 0, max, max, max, 0, max, max, max },
       { 0, max, max, max, max, 0, max, max },
       { 0, max, max, 0, 0, max, 0, 0 },
       { 0, max, max, 0, 0, max, max, 0 }
    };

    public long eastTravelCostBetweenArray[][] = {
       { max, max, max, max, max, max, max, max },
       { 0, 0, max, max, max, max, max, max },
       { 0, 0, 0, 0, max, 0, max, max },
       { 0, max, max, 0, max, max, max, max },
       { 0, 0, max, 0, 0, 0, max, max },
       { 0, 0, max, 0, max, 0, max, max },
       { 0, max, max, max, max, max, 0, max },
       { 0, max, max, max, max, max, max, 0 }
    };

    public long westTravelCostBetweenArray[][] = {
       { max, max, max, max, max, max, max, max },
       { 0, 0, 0, max, 0, 0, max, max },
       { 0, max, 0, max, max, max, max, max },
       { 0, max, 0, 0, 0, 0, max, max },
       { 0, max, max, max, 0, max, max, max },
       { 0, max, 0, max, 0, 0, max, max },
       { 0, max, max, max, max, max, 0, max },
       { 0, max, max, max, max, max, max, 0 }
    };   
   */
   
   /*
   public long northTravelCostBetweenArray[][] = {
      //MAX 
      { max, max, max, max, max, max, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, 0, 0, max, max, max, 0, 0 },
      { 0, 0, 0, max, max, max, 0, 0 },
      { 0, max, max, max, max, max, max, max },
      { 0, 0, 0, max, max, max, 0, 0 },
      { 0, 0, 0, max, max, max, max, max }
   };

   public long southTravelCostBetweenArray[][] = {
      { max, max, max, max, max, max, max, max },
      { 0, max, max, 0, 0, max, 0, 0 },
      { 0, max, max, 0, 0, max, 0, 0 },
      { 0, max, max, max, max, max, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, max, max, 0, 0, max, 0, 0 },
      { 0, max, max, 0, 0, max, max, max }
   };

   public long eastTravelCostBetweenArray[][] = {
      { max, max, max, max, max, max, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, 0, max, 0, max, 0, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, 0, max, 0, max, 0, max, max },
      { 0, 0, max, 0, max, max, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, max, max, max, max, max, max, max }
   };

   public long westTravelCostBetweenArray[][] = {
      { max, max, max, max, max, max, max, max },
      { 0, max, 0, max, 0, 0, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, max, 0, max, 0, 0, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, max, 0, max, 0, max, max, max },
      { 0, max, max, max, max, max, max, max },
      { 0, max, max, max, max, max, max, max }
   };   
   */
   
    public final String name;
    private final int travelCost;

   public RaceTrackGeographicMapCellType(final int type, final int travelCost)
   {
      super(type);
      this.travelCost = travelCost;
      final CommonStrings commonStrings = CommonStrings.getInstance();
      this.name = commonStrings.UNKNOWN;
   }

   public RaceTrackGeographicMapCellType(final String name, final int type, final int travelCost)
   {
      super(type);
      this.name = name;
      this.travelCost = travelCost;
   }
   

   //public void init()
   //{
   /*   
      String message = 
         "0 = Empty to Road: " + RaceTrackGeographicMapCellType.getTravelCostBetween(
         Direction.UP,
         (RaceTrackGeographicMapCellType) raceTrackGeographicMapCellTypeFactory.EMPTY_CELL_TYPE,
         RaceTrackGeographicMapCellType.BOTTOM_LEFT_TURN_ROAD_CELL_TYPE);
      LogUtil.put(LogFactory.getInstance(message, this, commonStrings.INIT));

      message = 
         "max = Road to Empty: " + RaceTrackGeographicMapCellType.getTravelCostBetween(
         Direction.UP,
         RaceTrackGeographicMapCellType.BOTTOM_LEFT_TURN_ROAD_CELL_TYPE,
         (RaceTrackGeographicMapCellType) raceTrackGeographicMapCellTypeFactory.EMPTY_CELL_TYPE);
      LogUtil.put(LogFactory.getInstance(message, this, commonStrings.INIT));

      message = 
         "0 = BOTTOM_LEFT_TURN EAST to BOTTOM_RIGHT_TURN: " + RaceTrackGeographicMapCellType.getTravelCostBetween(
         Direction.RIGHT,
         RaceTrackGeographicMapCellType.BOTTOM_LEFT_TURN_ROAD_CELL_TYPE,
         RaceTrackGeographicMapCellType.BOTTOM_RIGHT_TURN_ROAD_CELL_TYPE);
      LogUtil.put(LogFactory.getInstance(message, this, commonStrings.INIT));
      
      message = 
         "max = BOTTOM_LEFT_TURN WEST to BOTTOM_RIGHT_TURN: " + RaceTrackGeographicMapCellType.getTravelCostBetween(
         Direction.LEFT,
         RaceTrackGeographicMapCellType.BOTTOM_LEFT_TURN_ROAD_CELL_TYPE,
         RaceTrackGeographicMapCellType.BOTTOM_RIGHT_TURN_ROAD_CELL_TYPE);
      LogUtil.put(LogFactory.getInstance(message, this, commonStrings.INIT));

      message = 
         "max = HORIZONTAL (Any Direction) to VERTICAL: " + RaceTrackGeographicMapCellType.getTravelCostBetween(
         Direction.LEFT,
         RaceTrackGeographicMapCellType.HORIZONTAL_STRAIGHT_ROAD_CELL_TYPE,
         RaceTrackGeographicMapCellType.VERTICAL_STRAIGHT_ROAD_CELL_TYPE);
      LogUtil.put(LogFactory.getInstance(message, this, commonStrings.INIT));
    */
   //}
   
   public int getTravelCost()
   {
      return travelCost;
   }
   
   /*
   public long getTravelCostBetween(Direction direction,
      RaceTrackGeographicMapCellType raceGameGeographicMapCellType,
      RaceTrackGeographicMapCellType borderingRaceGameGeographicMapCellType)
      //throws Exception
   {

      int type = raceGameGeographicMapCellType.getType().intValue();
      int bordering = borderingRaceGameGeographicMapCellType.getType().intValue();

      switch(direction.intValue())
      {
         //West
         case 0:
            return westTravelCostBetweenArray[bordering][type];
         //East
         case 1:
            return eastTravelCostBetweenArray[bordering][type];
         //South
         case 2:
            return southTravelCostBetweenArray[bordering][type];
         //North
         case 3:
            return northTravelCostBetweenArray[bordering][type];
         default:
            return -1;
            //throw new Exception("Only Four Directions");
      }
   }
   */
}
