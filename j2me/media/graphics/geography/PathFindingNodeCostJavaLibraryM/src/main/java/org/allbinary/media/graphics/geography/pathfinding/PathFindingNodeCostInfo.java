/*
* AllBinary Open License Version 1
* Copyright (c) 2006 AllBinary
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

public class PathFindingNodeCostInfo implements Comparable<PathFindingNodeCostInfo>
{    
    private final PathFindingNodeCostInfoData pathFindingNodeCostInfoData = PathFindingNodeCostInfoData.getInstance();
    
   public long costFromStart;
   public long costToEnd;
   
   //Add coming from costing
   public long totalCost;
   
   public PathFindingNodeCostInfo(final long costFromStart, final long costToGoal) throws Exception //, long totalCost)
   {
       this.costFromStart = costFromStart;
       this.costToEnd = costToGoal;

      //this.setTotalCost(totalCost);
      this.setTotalCost();
   }
      
   public void setCostToEnd(final long costToEnd)
   {
      this.costToEnd = costToEnd;
   }
   
   public void setTotalCost(final long totalCost)
   {
      this.totalCost = totalCost;
   }
   
   public void setTotalCost() throws Exception
   {
      this.totalCost = costFromStart + costToEnd;
      if(this.totalCost > pathFindingNodeCostInfoData.MAX_NODE_COST)
      {
         throw new Exception("Max Cost Exceeded");
      }
   }

   //this is used to accumulate cost for my first path finding library
   //but for newer libraries it is not so I added the add so if using the
   //older library check this method
   public void setCostFromStart(final long cost)
   {
      //this.costFromStart += cost;
       this.costFromStart = cost;
   }

   public void addCostFromStart(final long cost)
   {
       this.costFromStart += cost;
   }

    @Override
    public int compareTo(PathFindingNodeCostInfo pathFindingNodeCostInfo) {
        return Long.compare(this.totalCost, pathFindingNodeCostInfo.totalCost);
    }

   public String toString()
   {
      final StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append(this.getClass().getName());
      stringBuffer.append(": ");
      stringBuffer.append("CostFromStart: ");
      stringBuffer.append(this.costFromStart);
      stringBuffer.append(" CostToEnd: ");
      stringBuffer.append(this.costToEnd);
      stringBuffer.append(" TotalCost: ");
      stringBuffer.append(this.totalCost);
      
      return stringBuffer.toString();
   }
}
