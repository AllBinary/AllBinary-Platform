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
package org.allbinary.logic.math;


public class BasicDecimal
{  
   private long units;

   public BasicDecimal(BasicDecimal bigDecimal)
   {        
      this.units = bigDecimal.getUnscaled();
      this.updateScaled();
   }

   public BasicDecimal()
   {        
      this.units = 0;
      this.updateScaled();
   }

   public BasicDecimal(long units)
   {
      this.units = units;
      this.updateScaled();
   }

   /*
   public boolean isValid()
   {
      if(this.units < Long.MAX_VALUE  &&
         //Make sure that the scaled value is also valid
         (this.units >> is it positve this.scaleFactor) < Integer.MAX_VALUE)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   */

   public void set(int value)
   {
      this.units = (long) value;
      this.updateScaled();
   }

   public void set(long value)
   {
      this.units = value;
      this.updateScaled();
   }
   
   public void set(BasicDecimal bigDecimal)
   {
      this.units = bigDecimal.getUnscaled();
      this.updateScaled();
   }
   
   public void add(int value)
   {
      this.units = this.units + value;
      this.updateScaled();
   }

   public void add(long value)
   {
      this.units = this.units + value;
      this.updateScaled();
   }
   
   public void add(BasicDecimal bigDecimal)
   {
      this.units = this.units + bigDecimal.getUnscaled();
      this.updateScaled();
   }

   public void subtract(int value)
   {
      this.units = this.units - value;
      this.updateScaled();
   }

   public void subtract(long value)
   {
      this.units = this.units - value;
      this.updateScaled();
   }

   public void subtract(BasicDecimal bigDecimal)
   {
      this.units = this.units - bigDecimal.getUnscaled();
      this.updateScaled();
   }

   public void multiply(int value)
   {
      this.units = this.units * value;
      this.updateScaled();
   }

   public void multiply(long value)
   {
      this.units = this.units * value;
      this.updateScaled();
   }

   public void multiply(BasicDecimal bigDecimal)
   {
      this.units = this.units * bigDecimal.getUnscaled();
      this.updateScaled();
   }

   public void divide(int value)
   {
      this.units = this.units / value;
      this.updateScaled();
   }

   public void divide(long value)
   {
      this.units = this.units / value;
      this.updateScaled();
   }

   public void divide(BasicDecimal bigDecimal)
   {
      this.units = this.units / bigDecimal.getUnscaled();
      this.updateScaled();
   }
   
   /*
   public void add(String decimalStr)
   {
   this.updateScaled();
      this.units = this.units + this.convertToUnits(decimalStr);
   }

   public void subtract(String decimalStr)
   {
   this.updateScaled();
      this.units = this.units - this.convertToUnits(decimalStr);
   }
   
   private long convertToUnits(String decimalStr)
   {
   this.updateScaled();
      long localUnit=0;
      int index = decimalStr.indexOf(AbPathData.EXTENSION_SEP);
      
      String beforeDecimalStr = StringUtil.getInstance().EMPTY_STRING;
      String afterDecimalStr = StringUtil.getInstance().EMPTY_STRING;
      if(index < 1)
      {
         beforeDecimalStr = new String(decimalStr);
         afterDecimalStr = "00";
      }
      else
      {
         beforeDecimalStr = new String(decimalStr.substring(0,index));
         afterDecimalStr = new String(decimalStr.substring(index+1));
      }

      localUnit = new Long(new Long(decimalStr).longValue() * scaleFactor).longValue();
      localUnit += new Long(afterDecimalStr).longValue();
      return localUnit;
   }
   */

   public long getUnscaled()
   {
      return this.units;
   }

   private int scaled;
   
   private void updateScaled()
   {
       //Using shift on negative values produces undesired result
       //this.scaled = (int) (this.units >> this.getScaledFactor());
       this.scaled = (int) (this.units / factorValue);
   }
   
   public int getScaled()
   {
      return this.scaled;
   }
   
   public String toString()
   {  
       //StringMaker stringBuffer = new StringMaker();
       
       //stringBuffer.append(this.getScaled());
       //stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
       //stringBuffer.append("XXXX ");
       //stringBuffer.append(new Long(this.units).toString());

      //return stringBuffer.toString();
       
       return Long.toString(this.units);
   }

   private final int factor = ScaleFactorFactory.getInstance().DEFAULT_SCALE_FACTOR;
   private final int factorValue = ScaleFactorFactory.getInstance().DEFAULT_SCALE_VALUE;

   public int getScaledFactor()
   {
      return factor;
   }

   public int getScaledFactorValue()
   {
      return factorValue;
   }
}
