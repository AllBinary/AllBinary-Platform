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
package org.allbinary.logic.basic.string.regex.replace;

import org.allbinary.globals.AppUrlGlobals;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogFactory;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import org.allbinary.logic.basic.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;

public class Replace
{
    private final String REPLACERS_ = "Replacers: ";
    private final String ALL = "all";
    
   private final HashMap hashMap;
   
   public Replace(final String key, final String value)
   {
      hashMap = new HashMap();
      this.hashMap.put(key, value);
      
      //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.REPLACE))
      //{
         LogUtil.put(LogFactory.getInstance(REPLACERS_ + this.hashMap.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
         //LogUtil.put(LogFactory.getInstance("Replacers: key=" + key + " value=" + value, this, CommonStrings.getInstance().CONSTRUCTOR));
      //}
   }
   
   public Replace(HashMap hashMap)
   {      
      this.hashMap = hashMap;
      
      //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.REPLACE_INFO))
      //{
         LogUtil.put(LogFactory.getInstance(REPLACERS_ + this.hashMap.toString(), this, CommonStrings.getInstance().CONSTRUCTOR));
      //}
   }
   
   public String all(String replace)
   {
      //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.REPLACE))
      //{
         LogUtil.put(LogFactory.getInstance(REPLACERS_ + this.hashMap.toString(), this, ALL));
      //}

      final StringBuffer newStringBuffer = new StringBuffer();

      int totalNumberOfReplaces = 0;
      
      final Set keySet = hashMap.keySet();
      final Iterator keyIter = keySet.iterator();
      String key;
      String value;
      while(keyIter.hasNext())
      {
         key = (String) keyIter.next();
         value = (String) hashMap.get(key);
         
         //replace.replace(key, value);

         int index = 0;
         while(index < replace.length())
         {
            newStringBuffer.delete(0, newStringBuffer.length());

            final int begin = replace.indexOf(key, index);
            if(begin != -1)
            {
                System.out.println("found key");
               final int end = begin + key.length();

               //System.out.print(replace.substring(0, begin) + "+");
               //System.out.print(value + "+");
               //System.out.println(replace.substring(end, replace.length()));
               
               newStringBuffer.append(replace.substring(0, begin));
               newStringBuffer.append(value);
               index = newStringBuffer.length();
               newStringBuffer.append(replace.substring(end, replace.length()));
               
               replace = newStringBuffer.toString();
               totalNumberOfReplaces++;
            }
            else
            {
               break;
            }
         }
      }
      
      //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.REPLACE))
      //{
         //LogUtil.put(LogFactory.getInstance("Replace Result: " + replace, this, ALL));
      //}
      
      //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.REPLACE))
      //{
         LogUtil.put(LogFactory.getInstance("Total Number Of Replaces: " + totalNumberOfReplaces, this, ALL));
      //}
      
      return replace;
   }
   
   public static void main(String args[])
   {
      try
      {
       AppUrlGlobals appUrlGlobals = new AppUrlGlobals();
       appUrlGlobals.setWebappPath("G:/mnt/bc/mydev/work/allbinary_src/ToolsJavaLibrary/tools");
       URLGLOBALS.init(appUrlGlobals);
       
         String testString="\"super gun\" big tank \"goodgunriflegun";
         Replace replace=new Replace("gun","abcdefghijklmnopqrstuxwxyzabcdefghijklmnopqrstuxwxyz");
         System.out.println("Old String: " + testString);
         testString = replace.all(testString);
         System.out.println("New String: " + testString);
         Replace replaceCat=new Replace("abcdefghijklmnopqrstuxwxyzabcdefghijklmnopqrstuxwxyz","");
         testString = replaceCat.all(testString);
         System.out.println("New String: " + testString);
      }catch(Exception e)
      {
         //System.out.println("Error: " + e);
      }
   }
   
}
