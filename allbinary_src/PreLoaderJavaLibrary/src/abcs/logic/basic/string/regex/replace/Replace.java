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
package abcs.logic.basic.string.regex.replace;

import abcs.globals.AppUrlGlobals;
import abcs.globals.URLGLOBALS;
import abcs.logic.communication.log.LogFactory;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

import abcs.logic.communication.log.LogUtil;

public class Replace
{
   private HashMap hashMap;
   
   public Replace(String key, String value)
   {
      hashMap = new HashMap();
      this.hashMap.put(key,value);      
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.REPLACE))
      {
         LogUtil.put(LogFactory.getInstance("Replacers: " + this.hashMap.toString(),this,"all()"));
         //LogUtil.put(LogFactory.getInstance("Replacers: key=" + key + " value=" + value,this,"all()"));
      }
   }
   
   public Replace(HashMap hashMap)
   {      
      this.hashMap=hashMap;
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.REPLACE_INFO))
      {
         LogUtil.put(LogFactory.getInstance("Replacers: " + this.hashMap.toString(),this,"all()"));
      }
   }
   
   public String all(String replace)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.REPLACE))
      {
         LogUtil.put(LogFactory.getInstance("Replacers: " + this.hashMap.toString(),this,"all()"));
      }

      StringBuffer newStringBuffer = new StringBuffer();

      int totalNumberOfReplaces = 0;
      
      Set keySet = hashMap.keySet();
      Iterator keyIter = keySet.iterator();
      while(keyIter.hasNext())
      {
         String key = (String) keyIter.next();
         String value = (String) hashMap.get(key);
         
         //replace.replace(key, value);

         int index = 0;
         while(index < replace.length())
         {
            newStringBuffer.delete(0, newStringBuffer.length());

            int begin = replace.indexOf(key,index);
            //found key
            if(begin!=-1)
            {               
               int end = begin + key.length();
               
               //System.out.print(replace.substring(0,begin) + "+");
               //System.out.print(value + "+");
               //System.out.println(replace.substring(end,replace.length()));
               
               newStringBuffer.append(replace.substring(0,begin));
               newStringBuffer.append(value);
               index = newStringBuffer.length();
               newStringBuffer.append(replace.substring(end,replace.length()));
               
               replace = newStringBuffer.toString();
               totalNumberOfReplaces++;
            }
            else
            {
               break;
            }
         }
      }
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.REPLACE))
      {
         LogUtil.put(LogFactory.getInstance("Replace Result: " + replace,this,"all()"));
      }
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.REPLACE))
      {
         LogUtil.put(LogFactory.getInstance("Total Number Of Replaces: " + totalNumberOfReplaces, this, "all()"));
      }
      
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
