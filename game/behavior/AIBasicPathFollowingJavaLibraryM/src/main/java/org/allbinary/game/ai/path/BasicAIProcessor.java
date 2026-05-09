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
package org.allbinary.game.ai.path;

import org.allbinary.util.BasicArrayList;

import org.allbinary.direction.Direction;
import org.allbinary.direction.DirectionFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapDirectionUtil;
import org.allbinary.media.graphics.geography.pathfinding.GeographicMapCellPathHistoryInfo;

public class BasicAIProcessor
{
   private String name;
   
   private GeographicMapCellHistory geographicMapCellHistory;
   private BasicArrayList geographicMapCellPositionBasicArrayList;
   
   private GeographicMapCellPathHistoryInfo geographicMapCellPathHistoryInfo;
   
   private BasicDirectionAIHelper basicAI;
   //private int numberOfInterationsOffPath;

   //private int MAX_SKIP;

   public BasicAIProcessor(final String name, 
      final GeographicMapCellHistory geographicMapCellHistory,
      final GeographicMapCellPathHistoryInfo geographicMapCellPathHistoryInfo, 
      final BasicArrayList chosenPathList,
      final AngleInfo angleInfo, final int seed)
      throws Exception
   {
      //For Kotlin - START
      this.name = name;
      this.geographicMapCellHistory = geographicMapCellHistory;
      this.geographicMapCellPositionBasicArrayList = chosenPathList;
      this.geographicMapCellPathHistoryInfo = geographicMapCellPathHistoryInfo;
      //For Kotlin - END

      this.setName(name);
      this.setNewPath(geographicMapCellHistory, chosenPathList);

      this.basicAI = new BasicDirectionAIHelper(this.getName(), angleInfo);
   }

   private void setNewPath(
      GeographicMapCellHistory geographicMapCellHistory,
      BasicArrayList chosenPathList)
   {
      this.setGeographicMapCellHistory(geographicMapCellHistory);

      this.geographicMapCellPositionBasicArrayList = chosenPathList;

      //MAX_SKIP = this.geographicMapCellPositionBasicArrayList.size()/12;

      this.init();
   }

   public void init()
   {
       this.geographicMapCellPathHistoryInfo =
           new GeographicMapCellPathHistoryInfo();
       
       this.geographicMapCellPathHistoryInfo.setPreviousOnPathGeographicMapCellPosition(
          (GeographicMapCellPosition) this.geographicMapCellPositionBasicArrayList.get(
          this.geographicMapCellPositionBasicArrayList.size() - 1));

       this.geographicMapCellPathHistoryInfo.setNextOnPathGeographicMapCellPosition(
          (GeographicMapCellPosition) this.geographicMapCellPositionBasicArrayList.get(
          this.geographicMapCellPositionBasicArrayList.size() - 1));

       this.update();
   }
   
   private final GeographicMapDirectionUtil geographicMapDirectionUtil = GeographicMapDirectionUtil.getInstance();
   
   private int goTowardNextChosenOnPathPosition(GeographicMapCellPosition currentGeographicMapCellPosition)
      throws Exception
   {
      int keyDirection = -1;

      //this.logUtil.putF(this.getName() + " Off Path - Trying to get back on the path", this, this.commonStrings.PROCESS);

      GeographicMapCellPosition goToGeographicMapCellPosition =
         this.geographicMapCellPathHistoryInfo.getNextChosenOnPathGeographicMapCellPosition();

      Direction geographicMapDirectionData =
         this.geographicMapDirectionUtil.getDirectionFromCellPositionToAdjacentCellPosition(
         currentGeographicMapCellPosition, goToGeographicMapCellPosition);

      if (geographicMapDirectionData == DirectionFactory.getInstance().NOT_BORDERED_WITH)
      {
         geographicMapDirectionData =
            this.geographicMapDirectionUtil.getDirectionFromCellPositionToCellPosition(
            currentGeographicMapCellPosition, goToGeographicMapCellPosition);
      }

      keyDirection = this.basicAI.getAIKeyPressedFromDirection(
         geographicMapDirectionData);

      /*
      if(numberOfInterationsOffPath > 3)
      {
      this.logUtil.putF(this.getName() + " Off Path - for a while", this, this.commonStrings.PROCESS);
      keyDirection = this.randomDirectionGenerator.get();
      numberOfInterationsOffPath--;
      }
      else
      {
      numberOfInterationsOffPath++;
      }
       */

      return keyDirection;
   }

   private void update()
   {
      // int unvisitedIndex = this.geographicMapCellHistory.getFirstUnvisitedIndex();

      this.geographicMapCellPathHistoryInfo.setNextUnvisitedOnPathGeographicMapCellPosition(
         this.getGeographicMapCellHistory().getFirstUnvisited() 
         //this.geographicMapCellPositionBasicArrayList.get(unvisitedIndex)
          
         //this.geographicMapCellPositionBasicArrayList.get(unvisitedIndex)
         );

      /*
      int index = this.geographicMapCellPositionBasicArrayList.indexOf(
      currentGeographicMapCellPosition);
      
      //Assumes the track is a loop
      this.circularIndexUtil
      if(index == this.geographicMapCellPositionBasicArrayList.size() - 1)
      {
      index = 0;
      }
      else
      {
      index++;
      }
      
      this.geographicMapCellPathHistoryInfo.setNextOnPathGeographicMapCellPosition(
      (GeographicMapCellPosition)
      this.geographicMapCellPositionBasicArrayList.get(index));
       */

      this.geographicMapCellPathHistoryInfo.setNextChosenOnPathGeographicMapCellPosition(
         this.geographicMapCellPathHistoryInfo.getNextUnvisitedOnPathGeographicMapCellPosition());

   /* Not Working- just goes back far enough and starts infinite loop
   //allow skipping for AI
   if(index <= unvisitedIndex + MAX_SKIP)
   {
   this.geographicMapCellPathHistoryInfo.setNextChosenOnPathGeographicMapCellPosition(
   this.geographicMapCellPathHistoryInfo.getNextOnPathGeographicMapCellPosition()
   );
   }
   else
   {
   this.geographicMapCellPathHistoryInfo.setNextChosenOnPathGeographicMapCellPosition(
   this.geographicMapCellPathHistoryInfo.getNextUnvisitedOnPathGeographicMapCellPosition()
   );
   }
    */
   }

   public int processAI(AllBinaryLayerManager myManager,
      GeographicMapCellPosition currentGeographicMapCellPosition)
      throws Exception
   {
      int keyDirection = -1;

      if (this.geographicMapCellPositionBasicArrayList.contains(
         currentGeographicMapCellPosition))
      {
         //numberOfInterationsOffPath = 0;

         this.update();

         keyDirection = this.goTowardNextChosenOnPathPosition(
            currentGeographicMapCellPosition);

         this.geographicMapCellPathHistoryInfo.setPreviousOnPathGeographicMapCellPosition(
            currentGeographicMapCellPosition);
      }
      else
      {
         keyDirection = this.goTowardNextChosenOnPathPosition(
            currentGeographicMapCellPosition);
      }
      return keyDirection;
   }

   /*
   GeographicMapCellPosition previousGeographicMapCellPosition = 
   this.geographicMapCellPathHistoryInfo.getPreviousGeographicMapCellPosition();
   
   if(currentGeographicMapCellPosition != previousGeographicMapCellPosition)
   {
   //this.logUtil.putF(this.getName() + " currentGeographicMapCellPosition: " + currentGeographicMapCellPosition, this, this.commonStrings.PROCESS);
   
   keyDirection = this.update(currentGeographicMapCellPosition);
   
   this.geographicMapCellPathHistoryInfo.setPreviousGeographicMapCellPosition(
   currentGeographicMapCellPosition);
   }
   else
   {
   keyDirection = this.goTowardLastOnPathPosition(
   currentGeographicMapCellPosition);
   }
    */
   
   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public GeographicMapCellPathHistoryInfo getGeographicMapCellPathHistoryInfo()
   {
      return this.geographicMapCellPathHistoryInfo;
   }

   public void setGeographicMapCellPathHistoryInfo(GeographicMapCellPathHistoryInfo geographicMapCellPathHistoryInfo)
   {
      this.geographicMapCellPathHistoryInfo = geographicMapCellPathHistoryInfo;
   }

   public GeographicMapCellHistory getGeographicMapCellHistory()
   {
      return this.geographicMapCellHistory;
   }

   public void setGeographicMapCellHistory(GeographicMapCellHistory geographicMapCellHistory)
   {
      this.geographicMapCellHistory = geographicMapCellHistory;
   }
}
