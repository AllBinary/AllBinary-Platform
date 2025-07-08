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
package org.allbinary.data.tree.dom;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.path.AbFilePath;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;

import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpStoreInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class StoreUriResolver implements URIResolver
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private BasicUriResolver basicURIResolver;
   
   private TransformInfoInterface parentTransformInfoInterface;
   
   public StoreUriResolver(TransformInfoInterface parentTransformInfoInterface, BasicUriResolver basicURIResolver)
   {
      this.parentTransformInfoInterface = parentTransformInfoInterface;
      this.basicURIResolver = basicURIResolver;
   }
   
   public Source resolve(String href, String base) throws TransformerException
   {
      try
      {
         TransformInfoHttpStoreInterface transformInfoHttpStoreInterface =
            (TransformInfoHttpStoreInterface) parentTransformInfoInterface;
         
         AbPath fileAbPath = (AbPath) new AbFilePath(
            URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH +
            transformInfoHttpStoreInterface.getStoreName() +
            AbPathData.getInstance().SEPARATOR + href);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XMLLOGGING))
         {
            
            logUtil.put(
                    "attempt to use xsl:import: href=" + href +
               "\nBase= " + base +
               "\nNew path= " + fileAbPath.toString() +
               "\nNote: " + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH + " is a urlglobal" +
               "\nRequired Extension: " + this.basicURIResolver.getExtension(),
               this, "resolve");
         }
         
         return new StreamSource(new CryptFileReader(
            TransformInfoTemplateData.getInstance().UNCRYPTED_EXTENSION,
            TransformInfoTemplateData.getInstance().ENCRYPTED_EXTENSION).getInputStream(fileAbPath));
      }
      catch(TransformerException e)
      {
         throw e;
      }
      catch(Exception e)
      {
         throw new TransformerException(e);
      }
   }
   
   public String toString()
   {
      try
      {
         return URLGLOBALS.getMainPath() + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH +
            "{Store Name if Any}/{import url}";
      }
      catch(Exception e)
      {
         //Log Error
         return "StoreUriResolver - Does not work without webapp path should be changed";
      }
   }
}
