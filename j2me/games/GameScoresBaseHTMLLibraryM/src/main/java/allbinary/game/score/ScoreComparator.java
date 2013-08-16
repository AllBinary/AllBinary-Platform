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
package allbinary.game.score;

import javax.microedition.rms.RecordComparator;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class ScoreComparator
      implements //RecordFilter,
      RecordComparator
{
   private boolean isHighestBest;
   
   //private String searchName;
   
   public ScoreComparator(boolean isHighestBest)
   {
      this.isHighestBest = isHighestBest;
   }
   
/*
   public boolean matches(byte[] candidate)
   {
      if (this.searchName == null)
      {
         return false;
      }
 
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(candidate);
      DataInputStream inputStream = new DataInputStream(byteArrayInputStream);
      String name = null;
 
      try
      {
         int score = inputStream.readLong();
         name = inputStream.readUTF();
      }
      catch (EOFException e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "matches", e));
      }
      catch (IOException e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "matches", e));
      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "matches", e));
      }
      return (this.searchName.equals(name));
   }
 */
   
   //Since MIPD RecordEnumerations Impls are bad I just do the ordering myself
   //Although this imp of RecordComparator works
   public int compare(byte[] recordOne, byte[] recordTwo)
   {
      //ByteArrayInputStream byteArrayInputStreamOne = new ByteArrayInputStream(recordOne);
      //DataInputStream inputStreamOne = new DataInputStream(byteArrayInputStreamOne);
      //ByteArrayInputStream byteArrayInputStreamTwo = new ByteArrayInputStream(recordTwo);
      //DataInputStream inputStreamTwo = new DataInputStream(byteArrayInputStreamTwo);
      
      long scoreOne = 0;
      long scoreTwo = 0;
      
      try
      {
         //inputStreamOne.readUTF();
         //inputStreamTwo.readUTF();
         //scoreOne = inputStreamOne.readLong();
         //scoreTwo = inputStreamTwo.readLong();
      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "compare", e));
      }

      if(this.isHighestBest)
      {
         return this.getHighTooLow(scoreOne, scoreTwo);
      }
      else
      {
         return this.getLowTooHigh(scoreOne, scoreTwo);
      }
   }
   
   //high too low
   public int getHighTooLow(long scoreOne, long scoreTwo)
   {
      if (scoreOne > scoreTwo)
      {
         return RecordComparator.PRECEDES;
      }
      else
         if (scoreOne < scoreTwo)
         {
         return RecordComparator.FOLLOWS;
         }
         else
         {
         return RecordComparator.EQUIVALENT;
         }
   }
   
   //low too high
   public int getLowTooHigh(long scoreOne, long scoreTwo)
   {
      if (scoreOne < scoreTwo)
      {
         //LogUtil.put(LogFactory.getInstance(scoreOne + " Precedes " + scoreTwo, this, "compare"));
         return RecordComparator.PRECEDES;
      }
      else
         if (scoreOne > scoreTwo)
         {
         //LogUtil.put(LogFactory.getInstance(scoreOne + " Follows " + scoreTwo, this, "compare"));
         return RecordComparator.FOLLOWS;
         }
         else
         {
         //LogUtil.put(LogFactory.getInstance(scoreOne + " == " + scoreTwo, this, "compare"));
         return RecordComparator.EQUIVALENT;
         }
   }
   
}
