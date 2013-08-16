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
package allbinary.logic.visual.transform.info.objectConfig;

import org.w3c.dom.Document;

import abcs.data.tree.dom.document.DomDocumentHelper;

import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import allbinary.logic.control.crypt.file.CryptFileReader;

import allbinary.logic.visual.transform.info.TransformInfoInterface;


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
         TransformInfoInterface transformInfoInterface,
         AbPath objectConfigFileAbPath)
         throws Exception
   {
      try
      {
         String data = new CryptFileReader(
               TransformInfoObjectConfigData.getInstance().UNCRYPTED_EXTENSION,
               TransformInfoObjectConfigData.getInstance().ENCRYPTED_EXTENSION).get(
               objectConfigFileAbPath);

         return this.getInstance(
            transformInfoInterface, DomDocumentHelper.create(data));
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Could Not Load Object Config", getInstance(), "getInstance()", e));
         }
         throw e;
      }
   }

   public TransformInfoObjectConfigInterface getInstance(
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
            new GenericStoreTransformInfoObjectConfig(transformInfoInterface);
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, getInstance(), "getInstance()", e));
         }
         throw e;
      }
   }
   
   public TransformInfoObjectConfigInterface getInstance(
         TransformInfoInterface transformInfoInterface, Document document)
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
                  transformInfoInterface, document);
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
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
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                  abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
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
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                  abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORY))
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(
                error, instance, "getInstance(name,type)", e));
         }
         throw e;
      }
   }
   */
}
