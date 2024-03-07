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
package org.allbinary.game.score;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

import javax.microedition.rms.RecordComparator;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class ScoreComparator
      implements //RecordFilter,
      RecordComparator
{
   private final boolean isHighestBest;
   
   //private String searchName;
   
   public ScoreComparator(final boolean isHighestBest)
   {
      this.isHighestBest = isHighestBest;
   }

    public long getBestScore() {
        return this.isHighestBest ? Long.MAX_VALUE : 0;
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
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "matches", e));
      }
      catch (IOException e)
      {
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "matches", e));
      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "matches", e));
      }
      return (this.searchName.equals(name));
   }
 */
   
   //Since MIPD RecordEnumerations Impls are bad I just do the ordering myself
   //Although this imp of RecordComparator works
   public int compare(final byte[] recordOne, final byte[] recordTwo)
   {
      final ByteArrayInputStream byteArrayInputStreamOne = new ByteArrayInputStream(recordOne);
      final DataInputStream inputStreamOne = new DataInputStream(byteArrayInputStreamOne);
      final ByteArrayInputStream byteArrayInputStreamTwo = new ByteArrayInputStream(recordTwo);
      final DataInputStream inputStreamTwo = new DataInputStream(byteArrayInputStreamTwo);
      
      long scoreOne = 0;
      long scoreTwo = 0;
      
      try
      {
         inputStreamOne.readUTF();
         inputStreamTwo.readUTF();
         scoreOne = inputStreamOne.readLong();
         scoreTwo = inputStreamTwo.readLong();
      }
      catch (EOFException e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "compare", e));
      }
      catch (IOException e)
      {
          final CommonStrings commonStrings = CommonStrings.getInstance();
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "compare", e));
      }
      catch (Exception e)
      {
          final CommonStrings commonStrings = CommonStrings.getInstance();
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "compare", e));
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
   public int getHighTooLow(final long scoreOne, final long scoreTwo)
   {
      if (scoreOne > scoreTwo)
      {
          //LogUtil.put(LogFactory.getInstance(scoreOne + " Follows2 " + scoreTwo, this, "compare"));
          return RecordComparator.PRECEDES;
      }
      else
         if (scoreOne < scoreTwo)
         {
            //LogUtil.put(LogFactory.getInstance(scoreOne + " Precedes2 " + scoreTwo, this, "compare"));
            return RecordComparator.FOLLOWS;
         }
         else
         {
            //LogUtil.put(LogFactory.getInstance(scoreOne + " ==2 " + scoreTwo, this, "compare"));
            return RecordComparator.EQUIVALENT;
         }
   }
   
   //low too high
   public int getLowTooHigh(final long scoreOne, final long scoreTwo)
   {
      if (scoreOne < scoreTwo)
      {
         //LogUtil.put(LogFactory.getInstance(scoreOne + " Precedes2 " + scoreTwo, this, "compare"));
         return RecordComparator.PRECEDES;
      }
      else
         if (scoreOne > scoreTwo)
         {
         //LogUtil.put(LogFactory.getInstance(scoreOne + " Follows2 " + scoreTwo, this, "compare"));
         return RecordComparator.FOLLOWS;
         }
         else
         {
         //LogUtil.put(LogFactory.getInstance(scoreOne + " ==2 " + scoreTwo, this, "compare"));
         return RecordComparator.EQUIVALENT;
         }
   }
   
}
