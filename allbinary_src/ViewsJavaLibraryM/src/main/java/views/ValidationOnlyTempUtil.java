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

import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;

import allbinary.logic.control.validate.ValidationComponentInterface;

import abcs.logic.communication.log.LogUtil;

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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("View Name: " + validationComponentInterface.getTransformInfoInterface().getName(), instance, "view()"));
         }

/*
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPEXTRAOUTPUT))
         {
            return validationComponentInterface.validationInfo();
         }
*/

         return StringUtil.getInstance().EMPTY_STRING;
      }
      catch(Exception e)
      {
         String error = "Failed to view: " + validationComponentInterface.getTransformInfoInterface().getName();
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, instance, "view()", e));
         }
         throw e;
      }
   }
}
