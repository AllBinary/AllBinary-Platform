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


import abcs.data.tree.dom.document.DomDocumentHelper;

import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import allbinary.logic.control.crypt.file.CryptFileReader;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

public class TransformInfoObjectConfigNoManipulationFactory
{
    private static final TransformInfoObjectConfigNoManipulationFactory instance =
        new TransformInfoObjectConfigNoManipulationFactory();

   private TransformInfoObjectConfigNoManipulationFactory()
   {
   }

   public static TransformInfoObjectConfigInterface getInstance(
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

         return new TransformInfoObjectConfig(
                  transformInfoInterface, DomDocumentHelper.create(data));
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Could Not Load Object Config", instance, "getInstance()", e));
         }
         throw e;
      }
   }            
}
