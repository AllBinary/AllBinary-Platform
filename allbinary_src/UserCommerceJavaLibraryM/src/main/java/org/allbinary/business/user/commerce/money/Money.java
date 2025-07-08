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
package org.allbinary.business.user.commerce.money;

import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;

public class Money
{   
   private Long units;
   private final String DEFAULT_CURRENCY = "USD";
   
      //   String dollarStr=stringUtil.EMPTY_STRING;;
    //  String centsStr=stringUtil.EMPTY_STRING;;

//            dollarStr=new String(usDollarStr.substring(0,index));
  //    centsStr=new String(usDollarStr.substring(index+1));

   public Money(Money money)
   {        
      this.units = new Long(money.getUnits());
   }
   
   public Money()
   {        
      this.units = new Long(0);
   }

   public Money(String usDollarStr)// throws MoneyException
   {      
      if(usDollarStr != null && StringValidationUtil.getInstance().isNumber(usDollarStr))
      {
         this.units = new Long(convertUsdToUnits(usDollarStr));
      }
     // else throw new MoneyException();
   }

   public Money(long units)
   {
      this.units = new Long(units);
   }

   public boolean isValid()
   {
      if(this.units!=null && this.units.longValue()>0 && 
         this.units.longValue() < Long.MAX_VALUE)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /*
   public Money(String money, String currencyType)
   {
   }
   */
   
   /*
   public Money get(String currencyType)
   {
   }
   
   public void setCurrency(String currency)
   {
   }
   */

   public void add(Money moreMoney)
   {
      this.add(moreMoney.toString());
   }
   
   public void add(String usDollarStr)
   {
      this.units = new Long(this.units.longValue() + convertUsdToUnits(usDollarStr));
   }

   public void subtract(String usDollarStr)
   {
      this.units = new Long(this.units.longValue() - convertUsdToUnits(usDollarStr));
   }

   public void multiply(Float multiplier)
   {
      this.units = new Long(new Float(this.units.longValue()*multiplier.floatValue()).longValue());
   }

   public void multiply(int multiplier)
   {
      this.units = new Long(this.units.longValue()*multiplier);
   }

   /*
   public void add(String money, String currencyType)
   {
   }

   public void subtract(String money, String currencyType)
   {
   }

   public void multiply(String money, String currencyType)
   {
   }
   */
   
   private String convertUnitsTo(String currency)
   {
	   final StringBuffer stringBuffer = new StringBuffer();
	   
      if(currency.compareTo(DEFAULT_CURRENCY)==0)
      {
         long dollar = units.longValue()/100;
         long cents = units.longValue() - (dollar*100);
         //if(mark==true) value += "$";
         
         stringBuffer.append(new Long(dollar).toString());
         stringBuffer.append(AbPathData.getInstance().EXTENSION_SEP);
         
         if(cents <10)
         {
        	 stringBuffer.append("0");
         }
         
         stringBuffer.append(new Long(cents).toString());
      }

      return stringBuffer.toString();      
   }
   
   private long convertUsdToUnits(String usDollarStr)
   {
      long localUnit=0;
      int index = usDollarStr.indexOf(AbPathData.getInstance().EXTENSION_SEP);
      
      final StringUtil stringUtil = StringUtil.getInstance();
      String dollarStr = stringUtil.EMPTY_STRING;
      String centsStr = stringUtil.EMPTY_STRING;
      if(index < 1)
      {
         dollarStr = new String(usDollarStr);
         centsStr = "00";
      }
      else
      {
         dollarStr = new String(usDollarStr.substring(0,index));
         centsStr = new String(usDollarStr.substring(index+1));
      }

      localUnit = new Long(new Long(dollarStr).longValue()*100).longValue();
      localUnit += new Long(centsStr).longValue();
      return localUnit;
   }
   
   /*
   public String getUsDollar()
   {            
      return new String(convertUnitsToSetCurrency(false));
   }
   */
   
   public long getUnits()
   {
      return units.longValue();
   }

   public Long getUnitsLong()
   {
      return units;
   }
   
   public String toString()
   {            
      return new String(convertUnitsTo(DEFAULT_CURRENCY));
   }
}
