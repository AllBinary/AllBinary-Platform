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
package org.allbinary.logic.control.validate;

public class ValidatorFactory
{
   private ValidatorFactory()
   {
      
   }
/*  

   public ValidationInterface getValidatorInterface(PageContext pageContext)
   {
      try
      {
         Object[] params = new Object[3];
         Class[] classes = new Class[3];
         
         //Add param types
         classes[0] = AbeFactory.getInstance().getClass("org.allbinary.logic.visual.transform.info.ValidationInterface");
         classes[1] = this.propertiesHashMap.getClass();
         classes[2] = AbeFactory.getInstance().getClass("javax.servlet.jsp.PageContext");
         
         //Add arguments
         params[0] = (Object) this;
         params[1] = (Object) this.propertiesHashMap;
         params[2] = (Object) pageContext;
         
         return (ValidationInterface) AbeFactory.getInstance().getInstance(this.getValidationClassFile(), classes, params);
      }
      catch(LicensingException e)
      {
         String error = "Failed To Get Instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         GLOBALS2.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(error, this.getViewFile() + "->TransformInfoFactory",
            "getDomNodeInterface(HashMap, PageContext)",e);
         }
         //throw e;
         return null;
      }
      catch(Exception e)
      {
         String error = "Failed To Get Instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         GLOBALS2.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(error,this.getViewFile() + "->TransformInfoFactory",
            "getDomNodeInterface(HashMap, PageContext)",e);
         }
         return null;
      }
   }
      
   public HashMap toHashMap()
   {
      return null;
   }
   
   public java.util.Vector toVector()
   {
      return null;
   }
*/   
}