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
package org.allbinary.logic.communication.http;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import javax.servlet.http.HttpServletRequest;

public class AcceptableResponseGenerator
{
    private static final AcceptableResponseGenerator instance = new AcceptableResponseGenerator();

   private AcceptableResponseGenerator()
   {
   }
   
   public static String get(HttpServletRequest httpServletRequest) throws Exception
   {
      try
      {
    	  AcceptableResponseUtil acceptableResponseUtil =
    		  AcceptableResponseUtil.getInstance();
    	  
         String acceptable = httpServletRequest.getHeader("accept");
         String result = acceptableResponseUtil.getTagName(0);

         if(  org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTP))
         {
            LogUtil.put(LogFactory.getInstance("Request Type: " + acceptable, instance, "get"));
         }
         
         if(acceptable!=null)
         {
        	 int size = acceptableResponseUtil.size();
            for(int index = 0; index < size; index++)
            {
               //int beginIndex = acceptable.indexOf(AcceptableResponse.get(index));
               //if(beginIndex >=0)
               if(acceptable.compareTo(acceptableResponseUtil.get(index)) == 0)
               {
                  result = acceptableResponseUtil.getTagName(index);
               }
            }
         }
         
         if(  org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTP))
         {
            LogUtil.put(LogFactory.getInstance("Response Type: " + result, instance, "get"));
         }
         
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to get AcceptableResponse Type";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().HTTPERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, instance, "get", e));
         }
         throw e;
      }
   }
}
