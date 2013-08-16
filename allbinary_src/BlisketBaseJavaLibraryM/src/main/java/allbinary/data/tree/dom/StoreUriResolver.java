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
package allbinary.data.tree.dom;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbFilePath;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.globals.FREEBLISKET_PATH_GLOBALS;

import allbinary.logic.control.crypt.file.CryptFileReader;
import allbinary.logic.visual.transform.info.TransformInfoHttpStoreInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class StoreUriResolver implements URIResolver
{
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.XMLLOGGING))
         {
            
            LogUtil.put(LogFactory.getInstance(
                    "attempt to use xsl:import: href=" + href +
               "\nBase= " + base +
               "\nNew path= " + fileAbPath.toString() +
               "\nNote: " + FREEBLISKET_PATH_GLOBALS.getInstance().XSLPATH + " is a urlglobal" +
               "\nRequired Extension: " + this.basicURIResolver.getExtension(),
               this, "resolve"));
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
