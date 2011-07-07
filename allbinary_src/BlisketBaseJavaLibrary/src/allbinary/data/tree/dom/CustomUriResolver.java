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

import abcs.logic.basic.path.AbFilePath;
import abcs.logic.basic.path.AbPath;
import abcs.logic.basic.path.AbPathData;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.control.crypt.file.CryptFileReader;
import allbinary.logic.visual.transform.info.template.TransformInfoTemplateData;

public class CustomUriResolver implements URIResolver
{
   private String path;
   private BasicUriResolver basicURIResolver;

   public CustomUriResolver(String path, BasicUriResolver basicURIResolver)
   {
      this.path = path;
      this.basicURIResolver = basicURIResolver;
   }

   public Source resolve(String href, String base) throws TransformerException
   {
      try
      {
         AbPath fileAbPath = (AbPath) 
             new AbFilePath(path + AbPathData.getInstance().SEPARATOR + href);

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            abcs.logic.communication.log.config.type.LogConfigType.XMLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(
                    "attempt to use xsl:import: href=" + href +
               "\nBase= " + base +
               "\nNew path= " + fileAbPath.toString() +
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
      return path;
   }
}
