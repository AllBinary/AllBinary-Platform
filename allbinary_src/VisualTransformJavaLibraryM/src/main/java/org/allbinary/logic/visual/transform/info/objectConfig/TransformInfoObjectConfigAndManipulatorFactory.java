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
package org.allbinary.logic.visual.transform.info.objectConfig;

import org.w3c.dom.Document;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;


public class TransformInfoObjectConfigAndManipulatorFactory
    implements TransformInfoObjectConfigAndManipulatorFactoryInterface
{
    private static final TransformInfoObjectConfigAndManipulatorFactory instance =
        new TransformInfoObjectConfigAndManipulatorFactory();

    /**
     * @return the instance
     */
    public static TransformInfoObjectConfigAndManipulatorFactory getInstance()
    {
        return instance;
    }

   private TransformInfoObjectConfigAndManipulatorFactory()
   {
   }

   public TransformInfoObjectConfigInterface getInstance(
       final AbeClientInformationInterface abeClientInformation,
       final TransformInfoInterface transformInfoInterface,
       final AbPath objectConfigFileAbPath)
         throws Exception
   {
      try
      {
         String data = new CryptFileReader(
               TransformInfoObjectConfigData.getInstance().UNCRYPTED_EXTENSION,
               TransformInfoObjectConfigData.getInstance().ENCRYPTED_EXTENSION).get(
               objectConfigFileAbPath);

         return this.getInstance(
             abeClientInformation, transformInfoInterface, DomDocumentHelper.create(data));
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Could Not Load Object Config", getInstance(), "getInstance()", e));
         }
         throw e;
      }
   }

   public TransformInfoObjectConfigInterface getInstance(
       final AbeClientInformationInterface abeClientInformation,
         TransformInfoInterface transformInfoInterface)
         throws Exception
   {
      try
      {
         //Document objectConfigDocument = transformInfoInterface.getObjectConfig();
         //DynamicObjectData.NAME
         
          StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
          
         //Maybe not the best way to create the correct object
         if(transformInfoInterface != null &&
               !stringValidationUtil.isEmpty(transformInfoInterface.getStoreName()))
         {
            return (TransformInfoObjectConfigInterface)
            new GenericStoreTransformInfoObjectConfig(abeClientInformation, transformInfoInterface);
         }
         else
         {
            return (TransformInfoObjectConfigInterface)
            new TransformInfoObjectConfig(transformInfoInterface);
         }
      }
      catch(Exception e)
      {
         String error = "Failed To Get Instance: ";

         if(transformInfoInterface != null)
         {
             error = error + transformInfoInterface.getName();
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, getInstance(), "getInstance()", e));
         }
         throw e;
      }
   }
   
   public TransformInfoObjectConfigInterface getInstance(
       final AbeClientInformationInterface abeClientInformation, 
       final TransformInfoInterface transformInfoInterface, final Document document)
         throws Exception
   {
      try
      {
          StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
          
         //Maybe not the best way to create the correct object
         if(transformInfoInterface != null &&
               !stringValidationUtil.isEmpty(transformInfoInterface.getStoreName()))
         {            
            return (TransformInfoObjectConfigInterface)
            new GenericStoreTransformInfoObjectConfig(
                abeClientInformation, transformInfoInterface, document);
         }
         else
         {
            return (TransformInfoObjectConfigInterface)
            new TransformInfoObjectConfig(
                  transformInfoInterface, document);
         }
      }
      catch(Exception e)
      {
         String error = "Failed To Get Instance: ";

         if(transformInfoInterface != null)
         {
             error = error + transformInfoInterface.getName();
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, getInstance(), "getInstance(document)", e));
         }
         throw e;
      }
   }
   
   /*
   public static TransformInfoObjectConfigInterface getInstance(
         TransformInfoInterface transformInfoInterface, String name, String type)
         throws Exception
   {
      try
      {
         //Maybe not the best way to create the correct object
         if(transformInfoInterface != null &&
               !stringValidationUtil.isEmpty(transformInfoInterface.getStoreName()))
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                  org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY))
            {
               LogUtil.put(LogFactory.getInstance("Creating GenericStoreTransformInfoObjectConfig",
                     instance, "getInstance()"));
            }
            
            return (TransformInfoObjectConfigInterface)
            new GenericStoreTransformInfoObjectConfig(
                  transformInfoInterface, name, type);
         }
         else
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                  org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORY))
            {
               LogUtil.put(LogFactory.getInstance("Creating TransformInfoObjectConfig",
                     instance, "getInstance()"));
            }
            
            return (TransformInfoObjectConfigInterface)
            new TransformInfoObjectConfig(transformInfoInterface, name, type);
         }
      }
      catch(Exception e)
      {
         String error = "Failed To Get Instance: ";

         if(transformInfoInterface != null)
         {
             error = error + transformInfoInterface.getName();
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(
                error, instance, "getInstance(name,type)", e));
         }
         throw e;
      }
   }
   */
}
