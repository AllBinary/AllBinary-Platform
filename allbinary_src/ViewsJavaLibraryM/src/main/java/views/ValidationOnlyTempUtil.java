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
package views;

import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.communication.log.LogUtil;

//Replace when validation node is added
public class ValidationOnlyTempUtil
{
	private static final ValidationOnlyTempUtil instance = new ValidationOnlyTempUtil();
   
   private ValidationOnlyTempUtil()
   {
   }
   
   //TWB hack for no store in session for a new store
   public static String view(ValidationComponentInterface validationComponentInterface) throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("View Name: " + validationComponentInterface.getTransformInfoInterface().getName(), instance, "view()"));
         }

/*
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPEXTRAOUTPUT))
         {
            return validationComponentInterface.validationInfo();
         }
*/

         return StringUtil.getInstance().EMPTY_STRING;
      }
      catch(Exception e)
      {
         String error = "Failed to view: " + validationComponentInterface.getTransformInfoInterface().getName();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, instance, "view()", e));
         }
         throw e;
      }
   }
}
