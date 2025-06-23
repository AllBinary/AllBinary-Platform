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

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class TransformInfoObjectConfigNoManipulationFactory
{
    private static final TransformInfoObjectConfigNoManipulationFactory instance =
        new TransformInfoObjectConfigNoManipulationFactory();
    
    public static TransformInfoObjectConfigNoManipulationFactory getInstance() {
        return instance;
    }

   private TransformInfoObjectConfigNoManipulationFactory()
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

         return new TransformInfoObjectConfig(
                  transformInfoInterface, DomDocumentHelper.create(data));
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Could Not Load Object Config", this, "getInstance()", e));
         }
         throw e;
      }
   }            
}
